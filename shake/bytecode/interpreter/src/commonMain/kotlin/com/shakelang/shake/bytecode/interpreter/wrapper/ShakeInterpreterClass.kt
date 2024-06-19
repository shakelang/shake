package com.shakelang.shake.bytecode.interpreter.wrapper

import com.shakelang.shake.bytecode.interpreter.ShakeInterpreter
import com.shakelang.shake.bytecode.interpreter.format.Class
import com.shakelang.shake.bytecode.interpreter.format.descriptor.PathDescriptor
import com.shakelang.shake.bytecode.interpreter.format.descriptor.TypeDescriptor
import com.shakelang.shake.bytecode.interpreter.format.pool.ConstantPool

object ClassRegister {

    private val classes = mutableListOf<ShakeInterpreterClass>()

    fun registerClass(clazz: ShakeInterpreterClass): Int {
        classes.add(clazz)
        return classes.size - 1
    }

    fun getClass(id: Int): ShakeInterpreterClass = classes[id]
}

interface ShakeInterpreterClass {
    val interpreter: ShakeInterpreter
    val storage: Class
    val qualifiedName: String
    val simpleName: String
    val isStatic: Boolean
    val pkg: ShakeInterpreterPackage
    val constantPool: ConstantPool

    /**
     * The methods, fields and subclasses of this class
     * @deprecated This method loads all classes from the package, which is not needed in most cases.
     *             It will impact performance, especially if you have a big library.
     */
    @Deprecated(
        "This method loads all classes from the package, which is not needed in most cases. " +
            "It will impact performance, especially if you have a big library.",
    )
    fun getAllMethods(): List<ShakeInterpreterMethod>

    /**
     * The methods, fields and subclasses of this class
     * @deprecated This method loads all classes from the package, which is not needed in most cases.
     *             It will impact performance, especially if you have a big library.
     */
    @Deprecated(
        "This method loads all classes from the package, which is not needed in most cases. " +
            "It will impact performance, especially if you have a big library.",
    )
    fun getAllFields(): List<ShakeInterpreterField>

    /**
     * The methods, fields and subclasses of this class
     * @deprecated This method loads all classes from the package, which is not needed in most cases.
     *             It will impact performance, especially if you have a big library.
     */
    @Deprecated(
        "This method loads all classes from the package, which is not needed in most cases. " +
            "It will impact performance, especially if you have a big library.",
    )
    fun getAllSubclasses(): List<ShakeInterpreterClass>

    val sizeInMemory: Long
    val staticSizeInMemory: Long
    val staticLocation: Long

    val classRegistryIndex: Int

    fun getClass(descriptor: PathDescriptor): ShakeInterpreterClass?
    fun getClass(descriptor: String): ShakeInterpreterClass? = getClass(PathDescriptor.parse(descriptor))
    fun getMethod(descriptor: PathDescriptor): ShakeInterpreterMethod?
    fun getMethod(descriptor: String): ShakeInterpreterMethod? = getMethod(PathDescriptor.parse(descriptor))
    fun getField(descriptor: PathDescriptor): ShakeInterpreterField
    fun getField(descriptor: String): ShakeInterpreterField = getField(PathDescriptor.parse(descriptor))

    fun getDirectChildClass(name: String): ShakeInterpreterClass?
    fun getDirectChildMethod(name: String): ShakeInterpreterMethod?
    fun getDirectChildField(name: String): ShakeInterpreterField?

    fun getStorageOfField(name: String): Long

    fun createInstanceInMemory(): Long {
        val pointer = interpreter.malloc.malloc(sizeInMemory)

        // put in header
        interpreter.globalMemory.setInt(pointer, classRegistryIndex)

        return pointer
    }

    @Suppress("DeprecatedCallableAddReplaceWith")
    companion object {
        fun of(
            storage: Class,
            classpath: ShakeInterpreterClasspath,
            parentPath: String,
            constantPool: ConstantPool,
            pkg: ShakeInterpreterPackage,
        ): ShakeInterpreterClass {
            val thisParentPath = "$parentPath${storage.name}:"

            return object : ShakeInterpreterClass {

                // These fields can be directly used from the map; they involve no
                // expensive logic to load them, so they are not loaded lazily.

                override val interpreter: ShakeInterpreter
                    get() = classpath.interpreter
                override val storage: Class = storage
                override val simpleName: String = storage.name
                override val qualifiedName: String = "$parentPath$simpleName"
                override val isStatic: Boolean = storage.isStatic
                override val constantPool: ConstantPool = constantPool
                override val pkg: ShakeInterpreterPackage = pkg
                override val classRegistryIndex: Int

                val memoryMap: LongArray
                override val sizeInMemory: Long

                val staticMemoryMap: LongArray
                override val staticSizeInMemory: Long

                override val staticLocation: Long

                init {
                    classRegistryIndex = ClassRegister.registerClass(this)
                    var size = 8L // 8 bytes for header
                    memoryMap = storage.fields.filter {
                        !it.isStatic
                    }.map {
                        val start = size
                        size += TypeDescriptor.parse(it.type).byteSize
                        start
                    }.toLongArray()
                    sizeInMemory = size

                    size = 4
                    staticMemoryMap = storage.fields.filter {
                        it.isStatic
                    }.map {
                        val start = size
                        size += TypeDescriptor.parse(it.type).byteSize
                        start
                    }.toLongArray()
                    staticSizeInMemory = size

                    // Let's create our static location
                    this.staticLocation = interpreter.malloc.malloc(staticSizeInMemory)
                }

                // Specification of the dynamic loading system:
                // Please read this before you try to understand the code below!
                //
                // The following code has the functionality of dynamically loading the stuff we
                // actually need.
                // We don't want to load all classes/methods/fields at the start,
                // if we have a big library/classpath, and a very small program.
                //
                // But we also need to cache loaded classes/methods/fields.
                // For this reason, we use a list of nulls, and if we load a class/method/field,
                // we replace the null with the loaded class/method/field.
                // We don't have lists of the formats here, (as in the ShakePackage implementation), because
                // we can't declare a class/method/field twice.
                // We have private methods to load classes/methods/fields (loadClass, loadMethod,
                // loadField).
                // These methods load the class/method/field from the map, save it into
                // our cache list and return it.
                // And we have private methods to get them (getClass, getMethod, getField).
                // These methods check if the class/method/field is already loaded, and if not, they call
                // the load methods.
                // Both load and get methods should return null if the class/method/field is not
                // found.
                // To resolve the index of a class/method/field by their name (in the case of a method
                // we need to include the parameters),
                // we have the resolveClassIndex, resolveMethodIndex
                // and resolveFieldIndex methods.
                // They look for them inside the map format and
                // return the indexes they are mapped to.
                // The indexes will depend on the order the storages are loaded in.

                val subclassList: MutableList<ShakeInterpreterClass?> = mutableListOf()
                val methodList: MutableList<ShakeInterpreterMethod?> = mutableListOf()
                val fieldList: MutableList<ShakeInterpreterField?> = mutableListOf()

                @Deprecated(
                    "This method loads all classes from the package, which is not needed in most cases. It will impact performance, especially if you have a big library.",
                )
                override fun getAllMethods() = methodList.indices.map { getMethod(it) }

                @Deprecated(
                    "This method loads all classes from the package, which is not needed in most cases. It will impact performance, especially if you have a big library.",
                )
                override fun getAllFields() = fieldList.indices.map { getField(it) }

                @Deprecated(
                    "This method loads all classes from the package, which is not needed in most cases. It will impact performance, especially if you have a big library.",
                )
                override fun getAllSubclasses() = subclassList.indices.map { getClass(it) }

                init {
                    for (c in storage.subClasses) subclassList.add(null)
                    for (m in storage.methods) methodList.add(null)
                    for (f in storage.fields) fieldList.add(null)
                }

                fun loadClass(index: Int): ShakeInterpreterClass {
                    val c = storage.subClasses[index]
                    val cls = of(c, classpath, thisParentPath, constantPool, pkg)
                    subclassList[index] = cls
                    return cls
                }

                fun loadMethod(index: Int): ShakeInterpreterMethod {
                    val m = storage.methods[index]
                    val method = ShakeInterpreterMethod.of(m, classpath, thisParentPath, constantPool, pkg, this)
                    methodList[index] = method
                    return method
                }

                fun loadField(index: Int): ShakeInterpreterField {
                    val f = storage.fields[index]
                    val mapValue = if (f.isStatic) staticMemoryMap[index] else memoryMap[index]
                    val field = ShakeInterpreterField.of(
                        f,
                        classpath,
                        thisParentPath,
                        constantPool,
                        pkg,
                        mapValue,
                        staticLocation,
                    )
                    fieldList[index] = field
                    return field
                }

                fun getClass(index: Int): ShakeInterpreterClass = subclassList[index] ?: loadClass(index)

                fun getMethod(index: Int): ShakeInterpreterMethod = methodList[index] ?: loadMethod(index)

                fun getField(index: Int): ShakeInterpreterField = fieldList[index] ?: loadField(index)

                fun loadAllSubclasses() {
                    for (i in storage.subClasses.indices) loadClass(i)
                }

                fun loadAllMethods() {
                    for (i in storage.methods.indices) loadMethod(i)
                }

                fun loadAllFields() {
                    for (i in storage.fields.indices) loadField(i)
                }

                fun resolveClassIndex(name: String): Int {
                    for (i in storage.subClasses.indices) {
                        if (storage.subClasses[i].name == name) return i
                    }
                    return -1
                }

                fun resolveMethodIndex(name: String): Int {
                    for (i in storage.methods.indices) {
                        if (storage.methods[i].qualifiedName == name) return i
                    }
                    return -1
                }

                fun resolveFieldIndex(name: String): Int {
                    for (i in storage.fields.indices) {
                        if (storage.fields[i].name == name) return i
                    }
                    return -1
                }

                fun getParentClassFor(descriptor: PathDescriptor): ShakeInterpreterClass? {
                    // We don't care about the package, we just try to find the parent class of the
                    // required element.
                    // We also don't care about the type of the element.

                    var current: ShakeInterpreterClass? = this
                    for (e in descriptor.classEntities.drop(1)) {
                        current = current?.getDirectChildClass(e)
                    }
                    return current
                }

                override fun getClass(descriptor: PathDescriptor): ShakeInterpreterClass? {
                    val parent = getParentClassFor(descriptor)
                    return parent?.getDirectChildClass(descriptor.entity)
                }

                override fun getMethod(descriptor: PathDescriptor): ShakeInterpreterMethod? {
                    val parent = getParentClassFor(descriptor)
                    return parent?.getDirectChildMethod(descriptor.entity)
                }

                override fun getField(descriptor: PathDescriptor): ShakeInterpreterField {
                    val parent = getParentClassFor(descriptor)
                    return parent?.getDirectChildField(descriptor.entity) ?: throw NullPointerException()
                }

                override fun getDirectChildClass(name: String): ShakeInterpreterClass? {
                    val index = resolveClassIndex(name)
                    return if (index == -1) null else getClass(index)
                }

                override fun getDirectChildMethod(name: String): ShakeInterpreterMethod? {
                    val index = resolveMethodIndex(name)
                    return if (index == -1) null else getMethod(index)
                }

                override fun getDirectChildField(name: String): ShakeInterpreterField? {
                    val index = resolveFieldIndex(name)
                    return if (index == -1) null else getField(index)
                }

                override fun getStorageOfField(name: String): Long {
                    val index = resolveFieldIndex(name)
                    return if (index == -1) -1 else memoryMap[index].toLong()
                }
            }
        }
    }
}

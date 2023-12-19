package com.shakelang.shake.bytecode.interpreter

import com.shakelang.shake.bytecode.interpreter.format.Class
import com.shakelang.shake.bytecode.interpreter.format.Field
import com.shakelang.shake.bytecode.interpreter.format.Method
import com.shakelang.shake.bytecode.interpreter.format.StorageFormat
import com.shakelang.shake.bytecode.interpreter.format.descriptor.MethodDescriptor
import com.shakelang.shake.bytecode.interpreter.format.descriptor.PathDescriptor
import com.shakelang.shake.bytecode.interpreter.format.descriptor.TypeDescriptor

interface ShakeClasspath {
    val packages: List<ShakeInterpreterPackage>

    fun getPackage(descriptor: PathDescriptor): ShakeInterpreterPackage?
    fun getPackage(descriptor: String): ShakeInterpreterPackage? = getPackage(PathDescriptor.parse(descriptor))

    fun getClass(descriptor: PathDescriptor): ShakeInterpreterClass? = getPackage(descriptor)?.getClass(descriptor)
    fun getClass(descriptor: String): ShakeInterpreterClass? = getClass(PathDescriptor.parse(descriptor))

    fun getMethod(descriptor: PathDescriptor): ShakeInterpreterMethod? = getPackage(descriptor)?.getMethod(descriptor)
    fun getMethod(descriptor: String): ShakeInterpreterMethod? = getMethod(PathDescriptor.parse(descriptor))

    fun getField(descriptor: PathDescriptor): ShakeInterpreterField? = getPackage(descriptor)?.getField(descriptor)
    fun getField(descriptor: String): ShakeInterpreterField? = getField(PathDescriptor.parse(descriptor))

    fun load(storage: StorageFormat)

    companion object {
        fun create(): ShakeClasspath {
            return object : ShakeClasspath {

                override val packages: MutableList<ShakeInterpreterPackage> = mutableListOf()

                override fun getPackage(descriptor: PathDescriptor): ShakeInterpreterPackage? {
                    val search = descriptor.packageEntities
                    return packages.find { it.storages[0].packageName == search[0] }
                }

                override fun load(storage: StorageFormat) {
                    val pkg = getPackage(PathDescriptor.parse(storage.packageName))
                    if (pkg != null) pkg.load(storage)
                    else packages.add(ShakeInterpreterPackage.of(listOf(storage), this))
                }
            }
        }
    }
}

interface ShakeInterpreterPackage {

    // This is a list of storages, because we could potentially have two libraries that add classes to
    // the same package.
    val storages: List<StorageFormat>

    fun getClass(descriptor: PathDescriptor): ShakeInterpreterClass?
    fun getClass(descriptor: String): ShakeInterpreterClass? = getClass(PathDescriptor.parse(descriptor))
    fun getMethod(descriptor: PathDescriptor): ShakeInterpreterMethod?
    fun getMethod(descriptor: String): ShakeInterpreterMethod? = getMethod(PathDescriptor.parse(descriptor))
    fun getField(descriptor: PathDescriptor): ShakeInterpreterField
    fun getField(descriptor: String): ShakeInterpreterField = getField(PathDescriptor.parse(descriptor))

    fun getDirectChildClass(name: String): ShakeInterpreterClass?
    fun getDirectChildMethod(name: String): ShakeInterpreterMethod?
    fun getDirectChildField(name: String): ShakeInterpreterField?

    fun load(storage: StorageFormat)

    companion object {
        fun of(storages: List<StorageFormat>, classpath: ShakeClasspath): ShakeInterpreterPackage {
            return object : ShakeInterpreterPackage {

                // We use a mutable list here, because we want to be able to add storages later
                // and not just at the initial load.
                // The only reason we would need to add storages later is if we load a library
                // later during runtime (e.g. a plugin) and it adds classes to the package we
                // already have from another library.
                // For this (quite rare) case we need to be able to add storages later.
                override val storages: MutableList<StorageFormat> = mutableListOf()


                // Specification of the dynamic loading system:
                // Please read this before you try to understand the code below.
                //
                // The following code has the functionality of dynamically loading the stuff we
                // actually need.
                // We don't want to load all classes/methods/fields at the start,
                // if we have a big library/classpath, and a very small program.
                //
                // But we also need to cache loaded classes/methods/fields.
                // For this reason, we use a list of nulls, and if we load a class/method/field,
                // we replace the null with the loaded class/method/field.
                // We also have lists of the formats here.
                // Their main purpose is to map an index
                // to a class/method/field.
                // This is needed because we can load multiple storages.
                // If we take the index of the class/method/field in the storage, we
                // could run into cases, where we try to map two different classes/methods/fields
                // to the same index.
                // We also have private methods to load classes/methods/fields (loadClass, loadMethod,
                // loadField).
                // These methods load the class/method/field from the storage save it into
                // our cache list and return it.
                // And we have private methods to get them (getClass, getMethod, getField).
                // These
                // methods check if the class/method/field is already loaded, and if not, they call
                // the load methods.
                // Both load and get methods should return null if the class/method/field is not
                // found.
                // To resolve the index of a class/method/field by their name (in the case of a method
                // we need to include the parameters),
                // we have the resolveClassIndex, resolveMethodIndex
                // and resolveFieldIndex methods.
                // They look for them inside the storage format and
                // return the indexes they are mapped to.
                // The indexes will depend on the order the storages are loaded in.

                val classList: MutableList<ShakeInterpreterClass?> = mutableListOf()
                val methodList: MutableList<ShakeInterpreterMethod?> = mutableListOf()
                val fieldList: MutableList<ShakeInterpreterField?> = mutableListOf()

                val classFormatList: MutableList<Class> = mutableListOf()
                val methodFormatList: MutableList<Method> = mutableListOf()
                val fieldFormatList: MutableList<Field> = mutableListOf()

                init {
                    for (s in storages) addStorageFormat(s)
                }

                fun loadClass(index: Int): ShakeInterpreterClass? {
                    val c = storages[index].classes[0]
                    val cls = ShakeInterpreterClass.of(c, classpath)
                    classList[index] = cls
                    return cls
                }

                fun loadMethod(index: Int): ShakeInterpreterMethod? {
                    val m = storages[index].methods[0]
                    val method = ShakeInterpreterMethod.of(m, classpath)
                    methodList[index] = method
                    return method
                }

                fun loadField(index: Int): ShakeInterpreterField? {
                    val f = storages[index].fields[0]
                    val field = ShakeInterpreterField.of(f)
                    fieldList[index] = field
                    return field
                }

                fun getClass(index: Int): ShakeInterpreterClass? {
                    return classList[index] ?: loadClass(index)
                }

                fun getMethod(index: Int): ShakeInterpreterMethod? {
                    return methodList[index] ?: loadMethod(index)
                }

                fun getField(index: Int): ShakeInterpreterField? {
                    return fieldList[index] ?: loadField(index)
                }

                fun loadAllClasses() {
                    for (i in storages.indices) loadClass(i)
                }

                fun loadAllMethods() {
                    for (i in storages.indices) loadMethod(i)
                }

                fun loadAllFields() {
                    for (i in storages.indices) loadField(i)
                }

                fun resolveClassIndex(name: String): Int {
                    for (i in storages.indices) {
                        if (storages[i].classes[0].name == name) return i
                    }
                    throw NullPointerException("Class $name not found!")
                }

                fun resolveMethodIndex(name: String): Int {
                    for (i in storages.indices) {
                        if (storages[i].methods[0].name == name) return i
                    }
                    throw NullPointerException("Method $name not found!")
                }

                fun resolveFieldIndex(name: String): Int {
                    for (i in storages.indices) {
                        if (storages[i].fields[0].name == name) return i
                    }
                    throw NullPointerException("Field $name not found!")
                }

                fun addStorageFormat(storage: StorageFormat) {
                    this.storages.add(storage)
                    for(c in storage.classes) classFormatList.add(c)
                    for(m in storage.methods) methodFormatList.add(m)
                    for(f in storage.fields) fieldFormatList.add(f)
                }

                override fun getClass(descriptor: PathDescriptor): ShakeInterpreterClass? {
                    if (descriptor.classEntities.isNotEmpty()) {
                        val clazz = getDirectChildClass(descriptor.classEntities[0])
                        return clazz?.getClass(descriptor)
                    }
                    return getDirectChildClass(descriptor.entity)
                }

                override fun getMethod(descriptor: PathDescriptor): ShakeInterpreterMethod? {
                    if (descriptor.classEntities.isNotEmpty()) {
                        val clazz = getDirectChildClass(descriptor.classEntities[0])
                        return clazz?.getMethod(descriptor)
                    }
                    return getDirectChildMethod(descriptor.entity)
                }

                override fun getField(descriptor: PathDescriptor): ShakeInterpreterField {
                    if (descriptor.classEntities.isNotEmpty()) {
                        val clazz = getDirectChildClass(descriptor.classEntities[0])
                        return clazz?.getField(descriptor) ?: throw NullPointerException()
                    }
                    return getDirectChildField(descriptor.entity) ?: throw NullPointerException()
                }

                override fun getDirectChildClass(name: String): ShakeInterpreterClass? {
                    return getClass(this.resolveClassIndex(name))
                }

                override fun getDirectChildMethod(name: String): ShakeInterpreterMethod? {
                    return getMethod(this.resolveMethodIndex(name))
                }

                override fun getDirectChildField(name: String): ShakeInterpreterField? {
                    return getField(this.resolveFieldIndex(name))
                }

                override fun load(storage: StorageFormat) {
                    addStorageFormat(storage)
                }
            }
        }
    }
}

interface ShakeInterpreterClass {
    val storage: Class
    val qualifiedName: String
    val simpleName: String
    val isStatic: Boolean

    fun getClass(descriptor: PathDescriptor): ShakeInterpreterClass?
    fun getClass(descriptor: String): ShakeInterpreterClass? = getClass(PathDescriptor.parse(descriptor))
    fun getMethod(descriptor: PathDescriptor): ShakeInterpreterMethod?
    fun getMethod(descriptor: String): ShakeInterpreterMethod? = getMethod(PathDescriptor.parse(descriptor))
    fun getField(descriptor: PathDescriptor): ShakeInterpreterField
    fun getField(descriptor: String): ShakeInterpreterField = getField(PathDescriptor.parse(descriptor))

    fun getDirectChildClass(name: String): ShakeInterpreterClass?
    fun getDirectChildMethod(name: String): ShakeInterpreterMethod?
    fun getDirectChildField(name: String): ShakeInterpreterField?

    companion object {
        fun of(storage: Class, classpath: ShakeClasspath): ShakeInterpreterClass {
            return object : ShakeInterpreterClass {

                // These fields can be directly used from the storage; they involve no
                // expensive logic to load them, so they are not loaded lazily.

                override val storage: Class = storage
                override val qualifiedName: String = storage.name
                override val simpleName: String = storage.name
                override val isStatic: Boolean = storage.isStatic

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
                // These methods load the class/method/field from the storage save it into
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
                // They look for them inside the storage format and
                // return the indexes they are mapped to.
                // The indexes will depend on the order the storages are loaded in.

                val subclassList: MutableList<ShakeInterpreterClass?> = mutableListOf()
                val methodList: MutableList<ShakeInterpreterMethod?> = mutableListOf()
                val fieldList: MutableList<ShakeInterpreterField?> = mutableListOf()

                init {
                    for (c in storage.subClasses) subclassList.add(ShakeInterpreterClass.of(c, classpath))
                    for (m in storage.methods) methodList.add(ShakeInterpreterMethod.of(m, classpath))
                    for (f in storage.fields) fieldList.add(ShakeInterpreterField.of(f))
                }

                fun loadClass(index: Int): ShakeInterpreterClass? {
                    val c = storage.subClasses[index]
                    val cls = ShakeInterpreterClass.of(c, classpath)
                    subclassList[index] = cls
                    return cls
                }

                fun loadMethod(index: Int): ShakeInterpreterMethod? {
                    val m = storage.methods[index]
                    val method = ShakeInterpreterMethod.of(m, classpath)
                    methodList[index] = method
                    return method
                }

                fun loadField(index: Int): ShakeInterpreterField? {
                    val f = storage.fields[index]
                    val field = ShakeInterpreterField.of(f)
                    fieldList[index] = field
                    return field
                }

                fun getClass(index: Int): ShakeInterpreterClass? {
                    return subclassList[index] ?: loadClass(index)
                }

                fun getMethod(index: Int): ShakeInterpreterMethod? {
                    return methodList[index] ?: loadMethod(index)
                }

                fun getField(index: Int): ShakeInterpreterField? {
                    return fieldList[index] ?: loadField(index)
                }

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
                    throw NullPointerException("Class $name not found in ${this.qualifiedName}!")
                }

                fun resolveMethodIndex(name: String): Int {
                    for (i in storage.methods.indices) {
                        if (storage.methods[i].name == name) return i
                    }
                    throw NullPointerException("Method $name not found in ${this.qualifiedName}!")
                }

                fun resolveFieldIndex(name: String): Int {
                    for (i in storage.fields.indices) {
                        if (storage.fields[i].name == name) return i
                    }
                    throw NullPointerException("Field $name not found in ${this.qualifiedName}!")
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
                    return getClass(this.resolveClassIndex(name))
                }

                override fun getDirectChildMethod(name: String): ShakeInterpreterMethod? {
                    return getMethod(this.resolveMethodIndex(name))
                }

                override fun getDirectChildField(name: String): ShakeInterpreterField? {
                    return getField(this.resolveFieldIndex(name))
                }
            }
        }
    }
}

interface ShakeInterpreterMethod {
    val storage: Method
    val qualifiedName: String
    val simpleName: String
    val isStatic: Boolean
    val returnType: ShakeInterpreterType
    val parameters: List<ShakeInterpreterType>
    val code: ByteArray

    companion object {
        fun of(storage: Method, classpath: ShakeClasspath): ShakeInterpreterMethod {

            val attributes = storage.attributes
            val code = attributes.find { it.name == "Code" }?.let { it as com.shakelang.shake.bytecode.interpreter.format.attribute.CodeAttribute }
            val qualifiedName = storage.qualifiedName
            val parsed = MethodDescriptor.parse(qualifiedName)

            return object : ShakeInterpreterMethod {

                override val storage: Method = storage
                override val qualifiedName: String = storage.name
                override val simpleName: String = storage.name
                override val isStatic: Boolean = storage.isStatic
                override val returnType: ShakeInterpreterType = ShakeInterpreterType.of(parsed.returnType, classpath)
                override val parameters: List<ShakeInterpreterType> = parsed.parameters.map { ShakeInterpreterType.of(it, classpath) }
                override val code: ByteArray get() = code?.code ?: throw NullPointerException("Method ${this.qualifiedName} has no code!")

            }
        }
    }
}

interface ShakeInterpreterField {
    val storage: Field
    val qualifiedName: String
    val simpleName: String
    val isStatic: Boolean
    val type: ShakeInterpreterType

    companion object {
        fun of(storage: Field): ShakeInterpreterField {
            TODO()
        }
    }
}

open class ShakeInterpreterType(
    val name: String,
    val type: Type,
) {

    enum class Type {
        INT,
        FLOAT,
        DOUBLE,
        LONG,
        SHORT,
        BYTE,
        CHAR,
        BOOLEAN,
        VOID,
        STRING,
        OBJECT,
        ARRAY
    }

    class ArrayType(val arrayType: ShakeInterpreterType) : ShakeInterpreterType(
        "[${arrayType.name}",
        Type.ARRAY,
    )

    class ObjectType(val objectType: ShakeInterpreterClass) : ShakeInterpreterType(
        objectType.qualifiedName,
        Type.OBJECT,
    )



    companion object {

        val INT = ShakeInterpreterType("int", Type.INT)
        val FLOAT = ShakeInterpreterType("float", Type.FLOAT)
        val DOUBLE = ShakeInterpreterType("double", Type.DOUBLE)
        val LONG = ShakeInterpreterType("long", Type.LONG)
        val SHORT = ShakeInterpreterType("short", Type.SHORT)
        val BYTE = ShakeInterpreterType("byte", Type.BYTE)
        val CHAR = ShakeInterpreterType("char", Type.CHAR)
        val BOOLEAN = ShakeInterpreterType("boolean", Type.BOOLEAN)
        val VOID = ShakeInterpreterType("void", Type.VOID)

        fun of(storage: TypeDescriptor, classpath: ShakeClasspath): ShakeInterpreterType {
            return when (storage) {
                is TypeDescriptor.ByteType -> BYTE
                is TypeDescriptor.CharType -> CHAR
                is TypeDescriptor.DoubleType -> DOUBLE
                is TypeDescriptor.FloatType -> FLOAT
                is TypeDescriptor.IntType -> INT
                is TypeDescriptor.LongType -> LONG
                is TypeDescriptor.ShortType -> SHORT
                is TypeDescriptor.BooleanType -> BOOLEAN
                is TypeDescriptor.VoidType -> VOID
                is TypeDescriptor.ArrayType -> ArrayType(of(storage.type, classpath))
                is TypeDescriptor.ObjectType -> ObjectType(classpath.getClass(storage.className)!!)
                else -> throw IllegalArgumentException("Unknown type: ${storage::class.simpleName}")
            }
        }
    }
}

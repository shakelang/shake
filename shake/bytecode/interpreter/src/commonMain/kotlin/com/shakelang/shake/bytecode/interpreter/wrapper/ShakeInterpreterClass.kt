package com.shakelang.shake.bytecode.interpreter.wrapper

import com.shakelang.shake.bytecode.interpreter.format.Class
import com.shakelang.shake.bytecode.interpreter.format.descriptor.PathDescriptor

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
        fun of(storage: Class, classpath: ShakeClasspath, parentPath: String): ShakeInterpreterClass {

            val thisParentPath = "$parentPath${storage.name}:"

            return object : ShakeInterpreterClass {

                // These fields can be directly used from the storage; they involve no
                // expensive logic to load them, so they are not loaded lazily.

                override val storage: Class = storage
                override val simpleName: String = storage.name
                override val qualifiedName: String = "$parentPath$simpleName"
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
                    for (c in storage.subClasses) subclassList.add(null)
                    for (m in storage.methods) methodList.add(null)
                    for (f in storage.fields) fieldList.add(null)
                }

                fun loadClass(index: Int): ShakeInterpreterClass {
                    val c = storage.subClasses[index]
                    val cls = ShakeInterpreterClass.of(c, classpath, thisParentPath)
                    subclassList[index] = cls
                    return cls
                }

                fun loadMethod(index: Int): ShakeInterpreterMethod {
                    val m = storage.methods[index]
                    val method = ShakeInterpreterMethod.of(m, classpath, thisParentPath)
                    methodList[index] = method
                    return method
                }

                fun loadField(index: Int): ShakeInterpreterField {
                    val f = storage.fields[index]
                    val field = ShakeInterpreterField.of(f, classpath, thisParentPath)
                    fieldList[index] = field
                    return field
                }

                fun getClass(index: Int): ShakeInterpreterClass {
                    return subclassList[index] ?: loadClass(index)
                }

                fun getMethod(index: Int): ShakeInterpreterMethod {
                    return methodList[index] ?: loadMethod(index)
                }

                fun getField(index: Int): ShakeInterpreterField {
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
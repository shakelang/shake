package com.shakelang.shake.bytecode.interpreter.wrapper

import com.shakelang.shake.bytecode.interpreter.format.Class
import com.shakelang.shake.bytecode.interpreter.format.Field
import com.shakelang.shake.bytecode.interpreter.format.Method
import com.shakelang.shake.bytecode.interpreter.format.StorageFormat
import com.shakelang.shake.bytecode.interpreter.format.descriptor.PathDescriptor

interface ShakeInterpreterPackage {

    // This is a list of storages, because we could potentially have two libraries that add classes to
    // the same package.
    val storages: List<StorageFormat>

    val name: String

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

                override val name: String = storages[0].packageName
                val parentPath = "$name/"

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

                fun loadClass(index: Int): ShakeInterpreterClass {
                    val c = storages[index].classes[0]
                    val cls = ShakeInterpreterClass.of(c, classpath, parentPath)
                    classList[index] = cls
                    return cls
                }

                fun loadMethod(index: Int): ShakeInterpreterMethod {
                    val m = storages[index].methods[0]
                    val method = ShakeInterpreterMethod.of(m, classpath, parentPath)
                    methodList[index] = method
                    return method
                }

                fun loadField(index: Int): ShakeInterpreterField {
                    val f = storages[index].fields[0]
                    val field = ShakeInterpreterField.of(f, classpath, parentPath)
                    fieldList[index] = field
                    return field
                }

                fun getClass(index: Int): ShakeInterpreterClass {
                    return classList[index] ?: loadClass(index)
                }

                fun getMethod(index: Int): ShakeInterpreterMethod {
                    return methodList[index] ?: loadMethod(index)
                }

                fun getField(index: Int): ShakeInterpreterField {
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
                    for(c in storage.classes) {
                        classFormatList.add(c)
                        classList.add(null)
                    }
                    for(m in storage.methods) {
                        methodFormatList.add(m)
                        methodList.add(null)
                    }
                    for(f in storage.fields) {
                        fieldFormatList.add(f)
                        fieldList.add(null)
                    }
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
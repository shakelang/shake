package com.shakelang.shake.bytecode.interpreter

import com.shakelang.shake.bytecode.interpreter.format.Class
import com.shakelang.shake.bytecode.interpreter.format.Field
import com.shakelang.shake.bytecode.interpreter.format.Method
import com.shakelang.shake.bytecode.interpreter.format.StorageFormat
import com.shakelang.shake.bytecode.interpreter.format.descriptor.MethodDescriptor
import com.shakelang.shake.bytecode.interpreter.format.descriptor.PathDescriptor
import com.shakelang.shake.bytecode.interpreter.format.descriptor.TypeDescriptor

interface ShakePackage {
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

    companion object {
        fun of(storages: List<StorageFormat>): ShakePackage {
            return object : ShakePackage {

                override val storages: MutableList<StorageFormat> = mutableListOf()

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
                    val cls = ShakeInterpreterClass.of(c)
                    classList[index] = cls
                    return cls
                }

                fun loadMethod(index: Int): ShakeInterpreterMethod? {
                    val m = storages[index].methods[0]
                    val method = ShakeInterpreterMethod.of(m)
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
        fun of(storage: Class): ShakeInterpreterClass {
            return object : ShakeInterpreterClass {

                override val storage: Class = storage
                override val qualifiedName: String = storage.name
                override val simpleName: String = storage.name
                override val isStatic: Boolean = storage.isStatic

                val subclassList: MutableList<ShakeInterpreterClass?> = mutableListOf()
                val methodList: MutableList<ShakeInterpreterMethod?> = mutableListOf()
                val fieldList: MutableList<ShakeInterpreterField?> = mutableListOf()

                init {
                    for (c in storage.subClasses) subclassList.add(ShakeInterpreterClass.of(c))
                    for (m in storage.methods) methodList.add(ShakeInterpreterMethod.of(m))
                    for (f in storage.fields) fieldList.add(ShakeInterpreterField.of(f))
                }

                fun loadClass(index: Int): ShakeInterpreterClass? {
                    val c = storage.subClasses[index]
                    val cls = ShakeInterpreterClass.of(c)
                    subclassList[index] = cls
                    return cls
                }

                fun loadMethod(index: Int): ShakeInterpreterMethod? {
                    val m = storage.methods[index]
                    val method = ShakeInterpreterMethod.of(m)
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
        fun of(storage: Method): ShakeInterpreterMethod {

            val attributes = storage.attributes
            val code = attributes.find { it.name == "Code" }?.let { it as com.shakelang.shake.bytecode.interpreter.format.attribute.CodeAttribute }
            val qualifiedName = storage.qualifiedName
            val parsed = MethodDescriptor.parse(qualifiedName)

            return object : ShakeInterpreterMethod {

                override val storage: Method = storage
                override val qualifiedName: String = storage.name
                override val simpleName: String = storage.name
                override val isStatic: Boolean = storage.isStatic
                override val returnType: ShakeInterpreterType = ShakeInterpreterType.of(parsed.returnType)
                override val parameters: List<ShakeInterpreterType> = parsed.parameters.map { ShakeInterpreterType.of(it) }
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
    val subType: ShakeInterpreterType? = null,
    val classType: ShakeInterpreterClass? = null
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
        STRING,
        OBJECT,
        ARRAY,
        VOID
    }

    companion object {
        fun of(storage: TypeDescriptor): ShakeInterpreterType {
            TODO()
        }
    }
}

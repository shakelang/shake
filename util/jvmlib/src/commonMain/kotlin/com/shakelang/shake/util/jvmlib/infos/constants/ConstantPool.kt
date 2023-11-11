package com.shakelang.shake.util.jvmlib.infos.constants

import com.shakelang.shake.util.io.streaming.input.DataInputStream
import com.shakelang.shake.util.io.streaming.output.DataOutputStream
import com.shakelang.shake.util.jvmlib.infos.ClassInfo
import com.shakelang.shake.util.shason.json

class ConstantPool(val constants: MutableList<ConstantInfo>) : MutableList<ConstantInfo> by constants, ConstantUser {

    constructor() : this(mutableListOf())

    init {
        constants.forEach { it.init(this) }
    }

    private lateinit var clazz: com.shakelang.shake.util.jvmlib.infos.ClassInfo
    val classInfo: com.shakelang.shake.util.jvmlib.infos.ClassInfo get() = clazz
    override val uses: Array<ConstantInfo> = users.map { it.uses.toList() }.flatten().toTypedArray()
    override val users: Array<ConstantUser>
        get() = constants.filter { it is ConstantUser }.map { it as ConstantUser }.toTypedArray()

    constructor(constants: Array<ConstantInfo>) : this(constants.toMutableList())

    override fun get(index: Int): ConstantInfo = if (index == 0) ConstantNullPointer.INSTANCE else constants[index - 1]
    operator fun get(index: UShort): ConstantInfo = get(index.toInt())

    fun toJson(): List<Map<String, Any>> {
        return this.constants.map { it.toJson() }
    }

    fun init(clazz: com.shakelang.shake.util.jvmlib.infos.ClassInfo) {
        this.clazz = clazz
    }

    override fun toString(): String {
        return json.stringify(this.toJson())
    }

    fun getUtf8(nameIndex: Int) = this[nameIndex].toUtf8()
    fun getClass(nameIndex: Int) = this[nameIndex].toClass()
    fun getClassRef(nameIndex: Int) = this[nameIndex].toClass()
    fun getFieldRef(nameIndex: Int) = this[nameIndex].toFieldRef()
    fun getMethodRef(nameIndex: Int) = this[nameIndex].toMethodRef()
    fun getInterfaceMethodRef(nameIndex: Int) = this[nameIndex].toInterfaceMethodRef()
    fun getString(nameIndex: Int) = this[nameIndex].toStringRef()
    fun getInteger(nameIndex: Int) = this[nameIndex].toInteger()
    fun getFloat(nameIndex: Int) = this[nameIndex].toFloat()
    fun getLong(nameIndex: Int) = this[nameIndex].toLong()
    fun getDouble(nameIndex: Int) = this[nameIndex].toDouble()
    fun getNameAndType(nameIndex: Int) = this[nameIndex].toNameAndType()
    fun getMethodHandle(nameIndex: Int) = this[nameIndex].toMethodHandle()
    fun getMethodType(nameIndex: Int) = this[nameIndex].toMethodType()
    fun getInvokeDynamic(nameIndex: Int) = this[nameIndex].toInvokeDynamic()

    fun getUtf8(nameIndex: UShort) = this.getUtf8(nameIndex.toInt())
    fun getClass(nameIndex: UShort) = this.getClass(nameIndex.toInt())
    fun getClassRef(nameIndex: UShort) = this.getClass(nameIndex.toInt())
    fun getFieldRef(nameIndex: UShort) = this.getFieldRef(nameIndex.toInt())
    fun getMethodRef(nameIndex: UShort) = this.getMethodRef(nameIndex.toInt())
    fun getInterfaceMethodRef(nameIndex: UShort) = this.getInterfaceMethodRef(nameIndex.toInt())
    fun getString(nameIndex: UShort) = this.getString(nameIndex.toInt())
    fun getInteger(nameIndex: UShort) = this.getInteger(nameIndex.toInt())
    fun getFloat(nameIndex: UShort) = this.getFloat(nameIndex.toInt())
    fun getLong(nameIndex: UShort) = this.getLong(nameIndex.toInt())
    fun getDouble(nameIndex: UShort) = this.getDouble(nameIndex.toInt())
    fun getNameAndType(nameIndex: UShort) = this.getNameAndType(nameIndex.toInt())
    fun getMethodHandle(nameIndex: UShort) = this.getMethodHandle(nameIndex.toInt())
    fun getMethodType(nameIndex: UShort) = this.getMethodType(nameIndex.toInt())
    fun getInvokeDynamic(nameIndex: UShort) = this.getInvokeDynamic(nameIndex.toInt())

    fun findUtf8(name: String): ConstantUtf8Info? {
        for (constant in constants) {
            if (constant is ConstantUtf8Info) {
                if (constant.value == name) {
                    return constant
                }
            }
        }
        return null
    }

    fun findClass(value: ConstantUtf8Info): ConstantClassInfo? {
        for (constant in constants) {
            if (constant is ConstantClassInfo) {
                if (constant.value == value) {
                    return constant
                }
            }
        }
        return null
    }

    fun findClass(value: String): ConstantClassInfo? {
        for (constant in constants) {
            if (constant is ConstantClassInfo) {
                if (constant.value.value == value) {
                    return constant
                }
            }
        }
        return null
    }

    fun findFieldRef(classRef: ConstantClassInfo, nameTypeRef: ConstantNameAndTypeInfo): ConstantFieldrefInfo? {
        for (constant in constants) {
            if (constant is ConstantFieldrefInfo) {
                if (constant.classRef === classRef && constant.nameTypeRef === nameTypeRef) {
                    return constant
                }
            }
        }
        return null
    }

    fun findFieldRef(classRef: ConstantClassInfo, name: String, descriptor: String): ConstantFieldrefInfo? {
        for (constant in constants) {
            if (constant is ConstantFieldrefInfo) {
                if (constant.classRef === classRef &&
                    constant.nameTypeRef.name.value == name &&
                    constant.nameTypeRef.type.value == descriptor
                ) {
                    return constant
                }
            }
        }
        return null
    }

    fun findFieldRef(classRef: String, name: String, descriptor: String): ConstantFieldrefInfo? {
        for (constant in constants) {
            if (constant is ConstantFieldrefInfo) {
                if (constant.classRef.value.value == classRef &&
                    constant.nameTypeRef.name.value == name &&
                    constant.nameTypeRef.type.value == descriptor
                ) {
                    return constant
                }
            }
        }
        return null
    }

    fun findMethodRef(classRef: ConstantClassInfo, nameTypeRef: ConstantNameAndTypeInfo): ConstantMethodrefInfo? {
        for (constant in constants) {
            if (constant is ConstantMethodrefInfo) {
                if (constant.classRef == classRef && constant.nameTypeRef == nameTypeRef) {
                    return constant
                }
            }
        }
        return null
    }

    fun findMethodRef(classRef: ConstantClassInfo, name: String, descriptor: String): ConstantMethodrefInfo? {
        for (constant in constants) {
            if (constant is ConstantMethodrefInfo) {
                if (constant.classRef == classRef &&
                    constant.nameTypeRef.name.value == name &&
                    constant.nameTypeRef.type.value == descriptor
                ) {
                    return constant
                }
            }
        }
        return null
    }

    fun findMethodRef(classRef: String, name: String, descriptor: String): ConstantMethodrefInfo? {
        for (constant in constants) {
            if (constant is ConstantMethodrefInfo) {
                if (constant.classRef.value.value == classRef &&
                    constant.nameTypeRef.name.value == name &&
                    constant.nameTypeRef.type.value == descriptor
                ) {
                    return constant
                }
            }
        }
        return null
    }

    fun findInterfaceMethodRef(
        classRef: ConstantClassInfo,
        nameTypeRef: ConstantNameAndTypeInfo
    ): ConstantInterfaceMethodrefInfo? {
        for (constant in constants) {
            if (constant is ConstantInterfaceMethodrefInfo) {
                if (constant.classRef == classRef && constant.nameTypeRef == nameTypeRef) {
                    return constant
                }
            }
        }
        return null
    }

    fun findInterfaceMethodRef(
        classRef: ConstantClassInfo,
        name: String,
        descriptor: String
    ): ConstantInterfaceMethodrefInfo? {
        for (constant in constants) {
            if (constant is ConstantInterfaceMethodrefInfo) {
                if (constant.classRef == classRef &&
                    constant.nameTypeRef.name.value == name &&
                    constant.nameTypeRef.type.value == descriptor
                ) {
                    return constant
                }
            }
        }
        return null
    }

    fun findInterfaceMethodRef(classRef: String, name: String, descriptor: String): ConstantInterfaceMethodrefInfo? {
        for (constant in constants) {
            if (constant is ConstantInterfaceMethodrefInfo) {
                if (constant.classRef.value.value == classRef &&
                    constant.nameTypeRef.name.value == name &&
                    constant.nameTypeRef.type.value == descriptor
                ) {
                    return constant
                }
            }
        }
        return null
    }

    fun findString(value: ConstantUtf8Info): ConstantStringInfo? {
        for (constant in constants) {
            if (constant is ConstantStringInfo) {
                if (constant.string == value) {
                    return constant
                }
            }
        }
        return null
    }

    fun findString(value: String): ConstantStringInfo? {
        for (constant in constants) {
            if (constant is ConstantStringInfo) {
                if (constant.string.value == value) {
                    return constant
                }
            }
        }
        return null
    }

    fun findInteger(value: Int): ConstantIntegerInfo? {
        for (constant in constants) {
            if (constant is ConstantIntegerInfo) {
                if (constant.value == value) {
                    return constant
                }
            }
        }
        return null
    }

    fun findFloat(value: Float): ConstantFloatInfo? {
        for (constant in constants) {
            if (constant is ConstantFloatInfo) {
                if (constant.value == value) {
                    return constant
                }
            }
        }
        return null
    }

    fun findLong(value: Long): ConstantLongInfo? {
        for (constant in constants) {
            if (constant is ConstantLongInfo) {
                if (constant.value == value) {
                    return constant
                }
            }
        }
        return null
    }

    fun findDouble(value: Double): ConstantDoubleInfo? {
        for (constant in constants) {
            if (constant is ConstantDoubleInfo) {
                if (constant.value == value) {
                    return constant
                }
            }
        }
        return null
    }

    fun findNameAndType(name: ConstantUtf8Info, descriptor: ConstantUtf8Info): ConstantNameAndTypeInfo? {
        for (constant in constants) {
            if (constant is ConstantNameAndTypeInfo) {
                if (constant.name == name && constant.type == descriptor) {
                    return constant
                }
            }
        }
        return null
    }

    fun findNameAndType(name: String, descriptor: String): ConstantNameAndTypeInfo? {
        for (constant in constants) {
            if (constant is ConstantNameAndTypeInfo) {
                if (constant.name.value == name && constant.type.value == descriptor) {
                    return constant
                }
            }
        }
        return null
    }

    fun findMethodHandle(referenceKind: Byte, reference: ConstantInfo): ConstantMethodHandleInfo? {
        for (constant in constants) {
            if (constant is ConstantMethodHandleInfo) {
                if (constant.referenceKind == referenceKind && constant.reference == reference) {
                    return constant
                }
            }
        }
        return null
    }

    fun findMethodType(descriptor: ConstantUtf8Info): ConstantMethodTypeInfo? {
        for (constant in constants) {
            if (constant is ConstantMethodTypeInfo) {
                if (constant.descriptor == descriptor) {
                    return constant
                }
            }
        }
        return null
    }

    fun findMethodType(descriptor: String): ConstantMethodTypeInfo? {
        for (constant in constants) {
            if (constant is ConstantMethodTypeInfo) {
                if (constant.descriptor.value == descriptor) {
                    return constant
                }
            }
        }
        return null
    }

    fun findInvokeDynamic(bootstrapMethod: UShort, nameAndType: ConstantNameAndTypeInfo): ConstantInvokeDynamicInfo? {
        for (constant in constants) {
            if (constant is ConstantInvokeDynamicInfo) {
                if (constant.bootstrapMethodAttributeIndex == bootstrapMethod && constant.nameAndType == nameAndType) {
                    return constant
                }
            }
        }
        return null
    }

    fun findInvokeDynamic(bootstrapMethod: UShort, name: String, type: String): ConstantInvokeDynamicInfo? {
        for (constant in constants) {
            if (constant is ConstantInvokeDynamicInfo) {
                if (constant.bootstrapMethodAttributeIndex == bootstrapMethod &&
                    constant.nameAndType.name.value == name &&
                    constant.nameAndType.type.value == type
                ) {
                    return constant
                }
            }
        }
        return null
    }

    fun expectUtf8(value: String): ConstantUtf8Info =
        findUtf8(value) ?: throw Exception("No utf8 constant found for value: $value")

    fun expectClass(value: ConstantUtf8Info): ConstantClassInfo =
        findClass(value) ?: throw Exception("No class constant found for value: $value")

    fun expectClass(value: String): ConstantClassInfo =
        findClass(value) ?: throw Exception("No class constant found for value: $value")

    fun expectFieldRef(classRef: String, name: String, descriptor: String): ConstantFieldrefInfo =
        findFieldRef(classRef, name, descriptor)
            ?: throw Exception("No fieldref constant found for classRef: $classRef, name: $name, descriptor: $descriptor")

    fun expectMethodRef(classRef: String, name: String, descriptor: String): ConstantMethodrefInfo =
        findMethodRef(classRef, name, descriptor)
            ?: throw Exception("No methodref constant found for classRef: $classRef, name: $name, descriptor: $descriptor")

    fun expectInterfaceMethodRef(classRef: String, name: String, descriptor: String): ConstantInterfaceMethodrefInfo =
        findInterfaceMethodRef(classRef, name, descriptor)
            ?: throw Exception("No interface methodref constant found for classRef: $classRef, name: $name, descriptor: $descriptor")

    fun expectString(value: String): ConstantStringInfo =
        findString(value) ?: throw Exception("No string constant found for value: $value")

    fun expectInteger(value: Int): ConstantIntegerInfo =
        findInteger(value) ?: throw Exception("No integer constant found for value: $value")

    fun expectFloat(value: Float): ConstantFloatInfo =
        findFloat(value) ?: throw Exception("No float constant found for value: $value")

    fun expectLong(value: Long): ConstantLongInfo =
        findLong(value) ?: throw Exception("No long constant found for value: $value")

    fun expectDouble(value: Double): ConstantDoubleInfo =
        findDouble(value) ?: throw Exception("No double constant found for value: $value")

    fun expectNameAndType(name: String, descriptor: String): ConstantNameAndTypeInfo = findNameAndType(name, descriptor)
        ?: throw Exception("No name and type constant found for name: $name, descriptor: $descriptor")

    fun expectNameAndType(name: ConstantUtf8Info, descriptor: ConstantUtf8Info): ConstantNameAndTypeInfo =
        findNameAndType(name, descriptor)
            ?: throw Exception("No name and type constant found for name: $name, descriptor: $descriptor")

    fun expectMethodHandle(referenceKind: Byte, reference: ConstantInfo): ConstantMethodHandleInfo =
        findMethodHandle(referenceKind, reference)
            ?: throw Exception("No method handle constant found for referenceKind: $referenceKind, reference: $reference")

    fun expectMethodType(descriptor: ConstantUtf8Info): ConstantMethodTypeInfo =
        findMethodType(descriptor) ?: throw Exception("No method type constant found for descriptor: $descriptor")

    fun expectInvokeDynamic(bootstrapMethod: UShort, nameAndType: ConstantNameAndTypeInfo): ConstantInvokeDynamicInfo =
        findInvokeDynamic(bootstrapMethod, nameAndType)
            ?: throw Exception("No invoke dynamic constant found for bootstrapMethod: $bootstrapMethod, nameAndType: $nameAndType")

    override fun add(element: ConstantInfo): Boolean {
        val ret = constants.add(element)
        if (ret) element.init(this)
        return ret
    }

    override fun clear() {
        constants.clear()
    }

    @Deprecated("DON'T USE THIS METHOD, IT CORRUPTS THE CLASS FILE")
    fun clean() {
        lateinit var unused: List<ConstantInfo>
        do {
            val used = classInfo.uses
            unused = filter { !used.contains(it) }
            removeAll(unused)
        } while (unused.isNotEmpty())
    }

    fun dump(out: DataOutputStream) {
        out.writeUnsignedShort((constants.size + 1).toUShort())
        for (constant in constants) {
            constant.dump(out)
        }
    }

    companion object {
        fun fromStream(stream: DataInputStream): ConstantPool {
            val constantPoolCount = stream.readUnsignedShort()
            val constants = Array(constantPoolCount.toInt() - 1) { ConstantInfo.fromStream(stream) }
            return ConstantPool(constants)
        }
    }
}

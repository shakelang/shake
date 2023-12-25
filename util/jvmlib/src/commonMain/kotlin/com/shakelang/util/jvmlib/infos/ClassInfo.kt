package com.shakelang.util.jvmlib.infos

import com.shakelang.util.io.streaming.input.DataInputStream
import com.shakelang.util.io.streaming.output.DataOutputStream
import com.shakelang.util.io.streaming.output.OutputStream
import com.shakelang.shake.util.jvmlib.infos.attributes.AttributeMap
import com.shakelang.shake.util.jvmlib.infos.constants.ConstantClassInfo
import com.shakelang.shake.util.jvmlib.infos.constants.ConstantInfo
import com.shakelang.shake.util.jvmlib.infos.constants.ConstantPool
import com.shakelang.shake.util.jvmlib.infos.fields.FieldList
import com.shakelang.shake.util.jvmlib.infos.methods.MethodList
import com.shakelang.shake.util.shason.json

class ClassInfo(
    val minorVersion: UShort,
    val majorVersion: UShort,
    val constantPool: ConstantPool,
    val accessFlags: UShort,
    val thisClass: ConstantClassInfo,
    val superClass: ConstantClassInfo,
    val interfaces: com.shakelang.shake.util.jvmlib.infos.InterfaceList,
    val fieldInfos: FieldList,
    val methodInfos: MethodList,
    val attributeInfos: AttributeMap
) {

    val isPublic: Boolean
        get() = accessFlags.toInt() and 0x0001 != 0

    val isFinal: Boolean
        get() = accessFlags.toInt() and 0x0010 != 0

    val isSuper: Boolean
        get() = accessFlags.toInt() and 0x0020 != 0

    val isInterface: Boolean
        get() = accessFlags.toInt() and 0x0200 != 0

    val isAbstract: Boolean
        get() = accessFlags.toInt() and 0x0400 != 0

    val isSynthetic: Boolean
        get() = accessFlags.toInt() and 0x1000 != 0

    val isAnnotation: Boolean
        get() = accessFlags.toInt() and 0x2000 != 0

    val isEnum: Boolean
        get() = accessFlags.toInt() and 0x4000 != 0

    val name: String get() = thisClass.className

    val users
        get() = arrayOf(
            *constantPool.users,
            interfaces,
            *fieldInfos.users,
            *methodInfos.users,
            *attributeInfos.users
        )

    val uses: Array<ConstantInfo>
        get() = arrayOf(
            constantPool[1],
            constantPool[constantPool.size],
            thisClass,
            superClass,
            *constantPool.uses,
            *interfaces.uses,
            *fieldInfos.uses,
            *methodInfos.uses,
            *attributeInfos.uses
        )

    init {
        constantPool.init(this)
        interfaces.init(this)
        fieldInfos.init(this)
        methodInfos.init(this)
        attributeInfos.init(this)
    }

    fun toJson() = mapOf(
        "minorVersion" to minorVersion,
        "majorVersion" to majorVersion,
        "constantPool" to constantPool.toJson(),
        "accessFlags" to accessFlags,
        "thisClass" to thisClass.toJson(),
        "superClass" to superClass.toJson(),
        "interfaces" to interfaces.toJson(),
        "fieldInfos" to fieldInfos.toJson(),
        "methodInfos" to methodInfos.toJson(),
        "attributeInfos" to attributeInfos.toJson()
    )

    override fun toString() = json.stringify(toJson())

    fun dump(out: OutputStream) = this.dump(DataOutputStream(out))
    fun dump(out: DataOutputStream) {
        out.writeInt(com.shakelang.shake.util.jvmlib.infos.ClassInfo.Companion.magic)
        out.writeUnsignedShort(minorVersion)
        out.writeUnsignedShort(majorVersion)
        constantPool.dump(out)
        out.writeUnsignedShort(accessFlags)
        out.writeUnsignedShort(thisClass.index)
        out.writeUnsignedShort(superClass.index)
        interfaces.dump(out)
        fieldInfos.dump(out)
        methodInfos.dump(out)
        attributeInfos.dump(out)
    }

    companion object {

        const val magic: Int = 0xcafebabe.toInt()

        fun fromStream(stream: DataInputStream): com.shakelang.shake.util.jvmlib.infos.ClassInfo {
            val magic = stream.readUnsignedInt()
            if (magic.toLong() != 0xcafebabe) throw IllegalArgumentException("Invalid magic number 0x${magic.toString(16)}")
            val minorVersion = stream.readUnsignedShort()
            val majorVersion = stream.readUnsignedShort()
            val constantPool = ConstantPool.fromStream(stream)
            val accessFlags = stream.readUnsignedShort()
            val thisClassIndex = stream.readUnsignedShort()
            val thisClass = constantPool.getClass(thisClassIndex)
            val superClassIndex = stream.readUnsignedShort()
            val superClass = constantPool.getClass(superClassIndex)
            val interfaces = com.shakelang.shake.util.jvmlib.infos.InterfaceList.fromStream(constantPool, stream)
            val fieldInfos = FieldList.fromStream(constantPool, stream)
            val methodInfos = MethodList.fromStream(constantPool, stream)
            val attributeInfos = AttributeMap.fromStream(constantPool, stream)

            return com.shakelang.shake.util.jvmlib.infos.ClassInfo(
                minorVersion,
                majorVersion,
                constantPool,
                accessFlags,
                thisClass,
                superClass,
                interfaces,
                fieldInfos,
                methodInfos,
                attributeInfos
            )
        }
    }
}

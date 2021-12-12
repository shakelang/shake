package io.github.shakelang.jvmlib.infos

import io.github.shakelang.jvmlib.infos.attributes.AttributeMap
import io.github.shakelang.jvmlib.infos.constants.ConstantClassInfo
import io.github.shakelang.jvmlib.infos.constants.ConstantInfo
import io.github.shakelang.jvmlib.infos.constants.ConstantPool
import io.github.shakelang.jvmlib.infos.fields.FieldList
import io.github.shakelang.jvmlib.infos.methods.MethodList
import io.github.shakelang.parseutils.streaming.input.DataInputStream
import io.github.shakelang.parseutils.streaming.output.DataOutputStream
import io.github.shakelang.parseutils.streaming.output.OutputStream
import io.github.shakelang.shason.json

class ClassInfo(
    val minorVersion: UShort,
    val majorVersion: UShort,
    val constantPool: ConstantPool,
    val accessFlags: UShort,
    val thisClass: ConstantClassInfo,
    val superClass: ConstantClassInfo,
    val interfaces: InterfaceList,
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

    val users get() = arrayOf(
        *constantPool.users,
        interfaces,
        *fieldInfos.users,
        *methodInfos.users,
        *attributeInfos.users
    )

    val uses: Array<ConstantInfo>
        get() = arrayOf(
        *constantPool.uses,
        interfaces.uses,
        *fieldInfos.uses,
        *methodInfos.uses,
        *attributeInfos.uses
    ) as Array<ConstantInfo>

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
        "interfaces" to interfaces,
        "fieldInfos" to fieldInfos.toJson(),
        "methodInfos" to methodInfos.toJson(),
        "attributeInfos" to attributeInfos.toJson()
    )

    override fun toString() = json.stringify(toJson())

    fun dump(out: OutputStream) = this.dump(DataOutputStream(out))
    fun dump(out: DataOutputStream) {
        println(magic.toUInt().toString(16))
        out.writeInt(magic)
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

        fun fromStream(stream : DataInputStream): ClassInfo {
            val magic = stream.readUnsignedInt()
            if(magic.toLong() != 0xcafebabe) throw IllegalArgumentException("Invalid magic number 0x${magic.toString(16)}")
            val minorVersion = stream.readUnsignedShort()
            val majorVersion = stream.readUnsignedShort()
            val constantPool = ConstantPool.fromStream(stream)
            val accessFlags = stream.readUnsignedShort()
            val thisClassIndex = stream.readUnsignedShort()
            val thisClass = constantPool.getClass(thisClassIndex)
            val superClassIndex = stream.readUnsignedShort()
            val superClass = constantPool.getClass(superClassIndex)
            val interfaces = InterfaceList.fromStream(constantPool, stream)
            val fieldInfos = FieldList.fromStream(constantPool, stream)
            val methodInfos = MethodList.fromStream(constantPool, stream)
            val attributeInfos = AttributeMap.fromStream(constantPool, stream)

            return ClassInfo(
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
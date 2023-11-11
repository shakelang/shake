package com.shakelang.shake.util.jvmlib.infos.constants

import com.shakelang.shake.util.io.streaming.input.DataInputStream
import com.shakelang.shake.util.io.streaming.input.InputStream
import com.shakelang.shake.util.io.streaming.input.dataStream
import com.shakelang.shake.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.shake.util.io.streaming.output.DataOutputStream
import com.shakelang.shake.util.jvmlib.infos.ClassInfo
import com.shakelang.shake.util.shason.json

abstract class ConstantInfo {

    private lateinit var pool: ConstantPool
    val constantPool: ConstantPool get() = pool
    val classInfo: com.shakelang.shake.util.jvmlib.infos.ClassInfo get() = pool.classInfo

    open val index: UShort get() = (constantPool.indexOf(this) + 1).toUShort()

    abstract val tag: Byte
    abstract val tagName: String

    open fun toJson(): Map<String, Any> = mapOf("tag_type" to tagName, "tag" to tag.toInt())
    override fun toString(): String = json.stringify(toJson())

    fun toUtf8(): ConstantUtf8Info = this as ConstantUtf8Info
    fun toInteger(): ConstantIntegerInfo = this as ConstantIntegerInfo
    fun toFloat(): ConstantFloatInfo = this as ConstantFloatInfo
    fun toLong(): ConstantLongInfo = this as ConstantLongInfo
    fun toDouble(): ConstantDoubleInfo = this as ConstantDoubleInfo
    fun toClass(): ConstantClassInfo = this as ConstantClassInfo
    fun toStringRef(): ConstantStringInfo = this as ConstantStringInfo
    fun toFieldRef(): ConstantFieldrefInfo = this as ConstantFieldrefInfo
    fun toMethodRef(): ConstantMethodrefInfo = this as ConstantMethodrefInfo
    fun toInterfaceMethodRef(): ConstantInterfaceMethodrefInfo = this as ConstantInterfaceMethodrefInfo
    fun toNameAndType(): ConstantNameAndTypeInfo = this as ConstantNameAndTypeInfo
    fun toMethodHandle(): ConstantMethodHandleInfo = this as ConstantMethodHandleInfo
    fun toMethodType(): ConstantMethodTypeInfo = this as ConstantMethodTypeInfo
    fun toInvokeDynamic(): ConstantInvokeDynamicInfo = this as ConstantInvokeDynamicInfo

    val isUsed: Boolean get() = isUsed(classInfo.uses)
    fun isUsed(used: Array<ConstantInfo>): Boolean = used.contains(this)

    open fun init(pool: ConstantPool) {
        this.pool = pool
    }

    open fun toBytes(): ByteArray {
        val arr = ByteArrayOutputStream()
        val out = DataOutputStream(arr)
        this.dump(out)
        return arr.toByteArray()
    }

    abstract fun dump(out: DataOutputStream)

    companion object {

        fun fromStream(stream: DataInputStream): ConstantInfo {
            val tag = stream.readByte()
            return when (tag) {
                ConstantTags.CONSTANT_UTF8 -> ConstantUtf8Info.contentsFromStream(stream)
                ConstantTags.CONSTANT_INTEGER -> ConstantIntegerInfo.contentsFromStream(stream)
                ConstantTags.CONSTANT_FLOAT -> ConstantFloatInfo.contentsFromStream(stream)
                ConstantTags.CONSTANT_LONG -> ConstantLongInfo.contentsFromStream(stream)
                ConstantTags.CONSTANT_DOUBLE -> ConstantDoubleInfo.contentsFromStream(stream)
                ConstantTags.CONSTANT_CLASS -> ConstantClassInfo.contentsFromStream(stream)
                ConstantTags.CONSTANT_STRING -> ConstantStringInfo.contentsFromStream(stream)
                ConstantTags.CONSTANT_FIELD_REF -> ConstantFieldrefInfo.contentsFromStream(stream)
                ConstantTags.CONSTANT_METHOD_REF -> ConstantMethodrefInfo.contentsFromStream(stream)
                ConstantTags.CONSTANT_INTERFACE_METHOD_REF -> ConstantInterfaceMethodrefInfo.contentsFromStream(stream)
                ConstantTags.CONSTANT_NAME_AND_TYPE -> ConstantNameAndTypeInfo.contentsFromStream(stream)
                ConstantTags.CONSTANT_METHOD_HANDLE -> ConstantMethodHandleInfo.contentsFromStream(stream)
                ConstantTags.CONSTANT_METHODTYPE -> ConstantMethodTypeInfo.contentsFromStream(stream)
                ConstantTags.CONSTANT_INVOKE_DYNAMIC -> ConstantInvokeDynamicInfo.contentsFromStream(stream)
                else -> throw IllegalArgumentException("Unknown tag $tag")
            }
        }

        fun fromStream(stream: InputStream): ConstantInfo {
            return fromStream(stream.dataStream)
        }

        fun fromBytes(bytes: ByteArray): ConstantInfo {
            return fromStream(bytes.dataStream())
        }
    }
}

fun <K, V> Map<K, V>.with(k: K, v: V): Map<K, V> {
    val map = mutableMapOf<K, V>()
    map.putAll(this)
    map[k] = v
    return map.toMap()
}

object ConstantTags {
    const val CONSTANT_UTF8: Byte = 1
    const val CONSTANT_INTEGER: Byte = 3
    const val CONSTANT_FLOAT: Byte = 4
    const val CONSTANT_LONG: Byte = 5
    const val CONSTANT_DOUBLE: Byte = 6
    const val CONSTANT_CLASS: Byte = 7
    const val CONSTANT_STRING: Byte = 8
    const val CONSTANT_FIELD_REF: Byte = 9
    const val CONSTANT_METHOD_REF: Byte = 10
    const val CONSTANT_INTERFACE_METHOD_REF: Byte = 11
    const val CONSTANT_NAME_AND_TYPE: Byte = 12
    const val CONSTANT_METHOD_HANDLE: Byte = 15
    const val CONSTANT_METHODTYPE: Byte = 16
    const val CONSTANT_INVOKE_DYNAMIC: Byte = 18
}

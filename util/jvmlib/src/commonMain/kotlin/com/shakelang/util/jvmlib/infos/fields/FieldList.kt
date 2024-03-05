package com.shakelang.util.jvmlib.infos.fields

import com.shakelang.util.io.streaming.input.bytes.DataInputStream
import com.shakelang.util.io.streaming.input.bytes.InputStream
import com.shakelang.util.io.streaming.input.bytes.dataStream
import com.shakelang.util.io.streaming.output.bytes.ByteArrayOutputStream
import com.shakelang.util.io.streaming.output.bytes.DataOutputStream
import com.shakelang.util.io.streaming.output.bytes.OutputStream
import com.shakelang.util.jvmlib.infos.constants.ConstantInfo
import com.shakelang.util.jvmlib.infos.constants.ConstantPool
import com.shakelang.util.jvmlib.infos.constants.ConstantUser
import com.shakelang.util.shason.json

class FieldList(val fields: List<FieldInfo>) : List<FieldInfo> by fields, ConstantUser {

    override val uses: Array<ConstantInfo> get() = fields.map { it.uses.toList() }.flatten().toTypedArray()
    override val users = fields.map { it.users.toList() }.flatten().toTypedArray()

    private lateinit var clazz: com.shakelang.util.jvmlib.infos.ClassInfo

    constructor(fields: Array<FieldInfo>) : this(fields.toList())

    fun init(clazz: com.shakelang.util.jvmlib.infos.ClassInfo) {
        this.clazz = clazz
        this.fields.forEach { it.init(clazz) }
    }

    fun toJson() = fields.map { it.toJson() }
    override fun toString() = json.stringify(toJson())

    fun dump(out: DataOutputStream) {
        out.writeUnsignedShort(size.toUShort())
        fields.forEach { it.dump(out) }
    }

    fun dump(out: OutputStream) {
        dump(DataOutputStream(out))
    }

    fun toBytes(): ByteArray {
        val out = ByteArrayOutputStream()
        dump(out)
        return out.toByteArray()
    }

    companion object {
        fun fromStream(pool: ConstantPool, stream: DataInputStream): FieldList {
            val count = stream.readUnsignedShort()
            val fields = Array(count.toInt()) { FieldInfo.fromStream(pool, stream) }
            return FieldList(fields)
        }

        fun fromStream(pool: ConstantPool, stream: InputStream): FieldList {
            return fromStream(pool, stream.dataStream)
        }

        fun fromBytes(pool: ConstantPool, bytes: ByteArray): FieldList {
            val stream = bytes.dataStream()
            val list = fromStream(pool, stream)
            if (stream.available() > 0) throw IllegalArgumentException("Not all bytes have been used")
            return list
        }
    }
}

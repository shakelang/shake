package com.shakelang.shake.util.jvmlib.infos.methods

import com.shakelang.shake.util.io.streaming.input.DataInputStream
import com.shakelang.shake.util.io.streaming.input.InputStream
import com.shakelang.shake.util.io.streaming.input.dataStream
import com.shakelang.shake.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.shake.util.io.streaming.output.DataOutputStream
import com.shakelang.shake.util.io.streaming.output.OutputStream
import com.shakelang.shake.util.jvmlib.infos.constants.ConstantInfo
import com.shakelang.shake.util.jvmlib.infos.constants.ConstantPool
import com.shakelang.shake.util.jvmlib.infos.constants.ConstantUser
import com.shakelang.shake.util.shason.json

class MethodList(val methods: List<MethodInfo>) : List<MethodInfo> by methods, ConstantUser {

    override val uses: Array<ConstantInfo> get() = methods.map { it.uses.toList() }.flatten().toTypedArray()
    override val users = methods.map { it.users.toList() }.flatten().toTypedArray()

    private lateinit var clazz: com.shakelang.shake.util.jvmlib.infos.ClassInfo

    constructor(methods: Array<MethodInfo>) : this(methods.toList())

    fun init(clazz: com.shakelang.shake.util.jvmlib.infos.ClassInfo) {
        this.clazz = clazz
        this.methods.forEach { it.init(clazz) }
    }

    fun toJson() = methods.map { it.toJson() }
    override fun toString() = json.stringify(toJson())

    fun dump(out: DataOutputStream) {
        out.writeUnsignedShort(size.toUShort())
        methods.forEach { it.dump(out) }
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
        fun fromStream(pool: ConstantPool, stream: DataInputStream): MethodList {
            val count = stream.readUnsignedShort()
            val fields = Array(count.toInt()) { MethodInfo.fromStream(pool, stream) }
            return MethodList(fields)
        }

        fun fromStream(pool: ConstantPool, stream: InputStream): MethodList {
            return fromStream(pool, stream.dataStream)
        }

        fun fromBytes(pool: ConstantPool, bytes: ByteArray): MethodList {
            val stream = bytes.dataStream()
            val list = fromStream(pool, stream)
            if (stream.available() > 0) throw IllegalArgumentException("Not all bytes have been used")
            return list
        }
    }
}

package com.shakelang.shake.bytecode.interpreter.wrapper

import com.shakelang.shake.bytecode.interpreter.ShakeInterpreter
import com.shakelang.shake.bytecode.interpreter.format.Field
import com.shakelang.shake.bytecode.interpreter.format.descriptor.TypeDescriptor
import com.shakelang.shake.bytecode.interpreter.format.pool.ConstantPool

interface ShakeInterpreterField {
    val storage: Field
    val qualifiedName: String
    val simpleName: String
    val isStatic: Boolean
    val type: ShakeInterpreterType
    val pkg: ShakeInterpreterPackage
    val constantPool: ConstantPool
    val interpreter: ShakeInterpreter
    val relativeLocation: Long
    val staticLocation: Long

    fun getStaticValue(size: Long) = interpreter.globalMemory.getBytes(staticLocation, size)
    fun getStaticValue(size: Int) = interpreter.globalMemory.getBytes(staticLocation, size.toLong())
    fun setStaticValue(value: ByteArray) = interpreter.globalMemory.setBytes(staticLocation, value)
    fun getStaticByte() = interpreter.globalMemory.getByte(staticLocation)
    fun setStaticByte(value: Byte) = interpreter.globalMemory.setByte(staticLocation, value)
    fun getStaticShort() = interpreter.globalMemory.getShort(staticLocation)
    fun setStaticShort(value: Short) = interpreter.globalMemory.setShort(staticLocation, value)
    fun getStaticInt() = interpreter.globalMemory.getInt(staticLocation)
    fun setStaticInt(value: Int) = interpreter.globalMemory.setInt(staticLocation, value)
    fun getStaticLong() = interpreter.globalMemory.getLong(staticLocation)
    fun setStaticLong(value: Long) = interpreter.globalMemory.setLong(staticLocation, value)
    fun getStaticFloat() = interpreter.globalMemory.getFloat(staticLocation)
    fun setStaticFloat(value: Float) = interpreter.globalMemory.setFloat(staticLocation, value)
    fun getStaticDouble(): Double = interpreter.globalMemory.getDouble(staticLocation)
    fun setStaticDouble(value: Double) = interpreter.globalMemory.setDouble(staticLocation, value)
    fun getStaticChar() = interpreter.globalMemory.getChar(staticLocation)
    fun setStaticChar(value: Char) = interpreter.globalMemory.setChar(staticLocation, value)
    fun getStaticBoolean() = interpreter.globalMemory.getBoolean(staticLocation)
    fun setStaticBoolean(value: Boolean) = interpreter.globalMemory.setBoolean(staticLocation, value)
    fun getStaticReference() = interpreter.globalMemory.getLong(staticLocation)
    fun setStaticReference(value: Long) = interpreter.globalMemory.setLong(staticLocation, value)

    fun getVirtualValue(virtualObject: Long, size: Long) = interpreter.globalMemory.getBytes(virtualObject + relativeLocation, size)
    fun getVirtualValue(virtualObject: Long, size: Int) = interpreter.globalMemory.getBytes(virtualObject + relativeLocation, size.toLong())
    fun setVirtualValue(virtualObject: Long, value: ByteArray) = interpreter.globalMemory.setBytes(virtualObject + relativeLocation, value)
    fun getVirtualByte(virtualObject: Long) = interpreter.globalMemory.getByte(virtualObject + relativeLocation)
    fun setVirtualByte(virtualObject: Long, value: Byte) = interpreter.globalMemory.setByte(virtualObject + relativeLocation, value)
    fun getVirtualShort(virtualObject: Long) = interpreter.globalMemory.getShort(virtualObject + relativeLocation)
    fun setVirtualShort(virtualObject: Long, value: Short) = interpreter.globalMemory.setShort(virtualObject + relativeLocation, value)
    fun getVirtualInt(virtualObject: Long) = interpreter.globalMemory.getInt(virtualObject + relativeLocation)
    fun setVirtualInt(virtualObject: Long, value: Int) = interpreter.globalMemory.setInt(virtualObject + relativeLocation, value)
    fun getVirtualLong(virtualObject: Long) = interpreter.globalMemory.getLong(virtualObject + relativeLocation)
    fun setVirtualLong(virtualObject: Long, value: Long) = interpreter.globalMemory.setLong(virtualObject + relativeLocation, value)
    fun getVirtualFloat(virtualObject: Long) = interpreter.globalMemory.getFloat(virtualObject + relativeLocation)
    fun setVirtualFloat(virtualObject: Long, value: Float) = interpreter.globalMemory.setFloat(virtualObject + relativeLocation, value)
    fun getVirtualDouble(virtualObject: Long): Double = interpreter.globalMemory.getDouble(virtualObject + relativeLocation)
    fun setVirtualDouble(virtualObject: Long, value: Double) = interpreter.globalMemory.setDouble(virtualObject + relativeLocation, value)
    fun getVirtualChar(virtualObject: Long) = interpreter.globalMemory.getChar(virtualObject + relativeLocation)
    fun setVirtualChar(virtualObject: Long, value: Char) = interpreter.globalMemory.setChar(virtualObject + relativeLocation, value)
    fun getVirtualBoolean(virtualObject: Long) = interpreter.globalMemory.getBoolean(virtualObject + relativeLocation)
    fun setVirtualBoolean(virtualObject: Long, value: Boolean) = interpreter.globalMemory.setBoolean(virtualObject + relativeLocation, value)
    fun getVirtualReference(virtualObject: Long) = interpreter.globalMemory.getLong(virtualObject + relativeLocation)
    fun setVirtualReference(virtualObject: Long, value: Long) = interpreter.globalMemory.setLong(virtualObject + relativeLocation, value)

    companion object {
        fun of(
            storage: Field,
            classpath: ShakeInterpreterClasspath,
            parentPath: String,
            constantPool: ConstantPool,
            pkg: ShakeInterpreterPackage,
            relativeLocation: Long,
            staticLocation: Long,
        ): ShakeInterpreterField {
            return object : ShakeInterpreterField {
                override val interpreter: ShakeInterpreter
                    get() = classpath.interpreter
                override val storage: Field = storage
                override val simpleName: String = storage.name
                override val qualifiedName: String = "$parentPath$simpleName"
                override val isStatic: Boolean = storage.isStatic
                override val type: ShakeInterpreterType =
                    ShakeInterpreterType.of(TypeDescriptor.parse(storage.type), classpath)
                override val constantPool: ConstantPool = constantPool
                override val pkg: ShakeInterpreterPackage = pkg
                override val relativeLocation: Long = relativeLocation
                override val staticLocation: Long = staticLocation
            }
        }
    }
}

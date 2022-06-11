package io.github.shakelang.shake.processor.map

import io.github.shakelang.parseutils.bytes.toBytes
import io.github.shakelang.parseutils.streaming.output.ByteArrayOutputStream
import io.github.shakelang.parseutils.streaming.output.OutputStream

class ShakeMap(

    val constants: Array<ShakeMapConstant>,
    val packages: Array<ShakeMapPackage>,
    val classes: Array<ShakeMapClass>,
    val methods: Array<ShakeMapMethod>,
    val fields: Array<ShakeMapField>,
    val project_packages: Array<Int>,
    val project_classes: Array<Int>,
    val project_methods: Array<Int>,
    val project_fields: Array<Int>,

) {

    val magic = 0x3082d75f
    val version = 0x00000001

}

interface ShakeMapConstant {
    val type: ShakeClassConstantTag

    fun getBytes(): ByteArray
    fun write(stream: OutputStream) = stream.write(getBytes())
}

class ShakeUtf8Constant(val value: String) : ShakeMapConstant {
    override val type = ShakeClassConstantTag.UTF8
    override fun getBytes(): ByteArray {
        val stream = ByteArrayOutputStream()
        write(stream)
        return stream.toByteArray()
    }

    override fun write(stream: OutputStream) {
        stream.write(type.TAG.toBytes())
        stream.write(value.length.toBytes())
        stream.write(value.toBytes())
    }
}

enum class ShakeClassConstantTag(val TAG: Byte) {
    UTF8(0x01),
    ;
}

class ShakeMapPackage(
    val name: Int,
    val package_references: Array<Int>,
    val class_references: Array<Int>,
    val method_references: Array<Int>,
    val field_references: Array<Int>,
) {
    fun getBytes(): ByteArray {
        val stream = ByteArrayOutputStream()
        write(stream)
        return stream.toByteArray()
    }

    fun write(stream: OutputStream) {
        stream.write(name.toBytes())
        stream.write(package_references.size.toBytes())
        package_references.forEach { stream.write(it.toBytes()) }
        stream.write(class_references.size.toBytes())
        class_references.forEach { stream.write(it.toBytes()) }
        stream.write(method_references.size.toBytes())
        method_references.forEach { stream.write(it.toBytes()) }
        stream.write(field_references.size.toBytes())
        field_references.forEach { stream.write(it.toBytes()) }
    }
}

class ShakeMapClass(
    val name: Int,
    val attributes: Byte,
    val super_class: Int,
    val interface_references: Array<Int>,
    val subclass_references: Array<Int>,
    val method_references: Array<Int>,
    val field_references: Array<Int>
) {
    val isStatic = attributes.bit7
    val isFinal = attributes.bit6
    val isPublic = attributes.bit4
    val isProtected = attributes.bit3
    val isPrivate = attributes.bit2
    val isAbstract = attributes.bit1

    fun getBytes(): ByteArray {
        val stream = ByteArrayOutputStream()
        write(stream)
        return stream.toByteArray()
    }

    fun write(stream: OutputStream) {
        stream.write(name.toBytes())
        stream.write(attributes.toBytes())
        stream.write(super_class.toBytes())
        stream.write(interface_references.size.toBytes())
        interface_references.forEach { stream.write(it.toBytes()) }
        stream.write(subclass_references.size.toBytes())
        subclass_references.forEach { stream.write(it.toBytes()) }
        stream.write(method_references.size.toBytes())
        method_references.forEach { stream.write(it.toBytes()) }
        stream.write(field_references.size.toBytes())
        field_references.forEach { stream.write(it.toBytes()) }
    }
}

class ShakeMapMethod(
    val name: Int,
    val attributes: Byte,
    val return_type: Int,
    val parameter_names: Array<Int>,
    val parameter_types: Array<Int>,
) {
    val isStatic = attributes.bit7
    val isFinal = attributes.bit6
    val isPublic = attributes.bit4
    val isProtected = attributes.bit3
    val isPrivate = attributes.bit2
    val isAbstract = attributes.bit1

    fun getBytes(): ByteArray {
        val stream = ByteArrayOutputStream()
        write(stream)
        return stream.toByteArray()
    }

    fun write(stream: OutputStream) {
        stream.write(name.toBytes())
        stream.write(attributes.toBytes())
        stream.write(return_type.toBytes())
        stream.write(parameter_names.size.toBytes())
        parameter_names.forEach { stream.write(it.toBytes()) }
        stream.write(parameter_types.size.toBytes())
        parameter_types.forEach { stream.write(it.toBytes()) }
    }
}

class ShakeMapField(
    val name: Int,
    val attributes: Byte,
    val type: Int,
) {
    val isStatic = attributes.bit7
    val isFinal = attributes.bit6
    val isConst = attributes.bit5
    val isPublic = attributes.bit4
    val isProtected = attributes.bit3
    val isPrivate = attributes.bit2
    val isAbstract = attributes.bit1

    fun getBytes(): ByteArray {
        val stream = ByteArrayOutputStream()
        write(stream)
        return stream.toByteArray()
    }

    fun write(stream: OutputStream) {
        stream.write(name.toBytes())
        stream.write(attributes.toBytes())
        stream.write(type.toBytes())
    }
}

val Int.bit0: Boolean get() = (this and 0x01) != 0
val Int.bit1: Boolean get() = (this and 0x02) != 0
val Int.bit2: Boolean get() = (this and 0x04) != 0
val Int.bit3: Boolean get() = (this and 0x08) != 0
val Int.bit4: Boolean get() = (this and 0x10) != 0
val Int.bit5: Boolean get() = (this and 0x20) != 0
val Int.bit6: Boolean get() = (this and 0x40) != 0
val Int.bit7: Boolean get() = (this and 0x80) != 0

val Byte.bit0: Boolean get() = this.toInt().bit0
val Byte.bit1: Boolean get() = this.toInt().bit1
val Byte.bit2: Boolean get() = this.toInt().bit2
val Byte.bit3: Boolean get() = this.toInt().bit3
val Byte.bit4: Boolean get() = this.toInt().bit4
val Byte.bit5: Boolean get() = this.toInt().bit5
val Byte.bit6: Boolean get() = this.toInt().bit6
val Byte.bit7: Boolean get() = this.toInt().bit7
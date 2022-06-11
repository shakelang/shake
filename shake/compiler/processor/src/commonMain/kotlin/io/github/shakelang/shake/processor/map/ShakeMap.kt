package io.github.shakelang.shake.processor.map

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
}

class ShakeUtf8Constant(val value: String) : ShakeMapConstant {
    override val type = ShakeClassConstantTag.UTF8
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
)

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
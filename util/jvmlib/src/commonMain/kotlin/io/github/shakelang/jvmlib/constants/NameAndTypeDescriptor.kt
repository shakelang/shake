package io.github.shakelang.jvmlib.constants

class NameAndTypeDescriptor(val value: Int, val type: Int) : JavaClassConstant() {

    override val tag: Byte = 12
    override fun toJson() = super.toJson().with("name", value).with("type", type)
    override val name: String get() = "NameAndTypeDescriptor"
    fun getNameValue(pool: Array<JavaClassConstant>): JavaClassConstant = pool[value-1]
    fun typeValue(pool: Array<JavaClassConstant>): JavaClassConstant = pool[type-1]

}
package io.github.shakelang.jvmlib.constants

class CONSTANT_Class(val value: Int) : CONSTANT() {

    override val tag: Byte = 7
    override fun toJson() = super.toJson().with("value", value)
    override val type: String get() = "CONSTANT_Class"
    fun getClassValue(pool: Array<CONSTANT>) = pool[value-1] as CONSTANT_Utf8

}
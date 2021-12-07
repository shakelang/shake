package io.github.shakelang.jvmlib.constants

class CONSTANT_NameAndType(val value: Int, val name: Int) : CONSTANT() {

    override val tag: Byte = 12
    override fun toJson() = super.toJson().with("name", value).with("type", name)
    override val type: String get() = "CONSTANT_NameAndType"
    fun getNameValue(pool: Array<CONSTANT>): CONSTANT = pool[value-1]
    fun typeValue(pool: Array<CONSTANT>): CONSTANT = pool[name-1]

}
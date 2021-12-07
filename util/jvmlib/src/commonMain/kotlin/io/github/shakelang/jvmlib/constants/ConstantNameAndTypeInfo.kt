package io.github.shakelang.jvmlib.constants

class ConstantNameAndTypeInfo(val value: Int, val name: Int) : CONSTANT() {

    override val tag: Byte get() = ConstantNameAndTypeInfo.tag
    override val type: String get() = ConstantNameAndTypeInfo.name

    override fun toJson() = super.toJson().with("name", value).with("type", name)
    fun getNameValue(pool: Array<CONSTANT>): CONSTANT = pool[value-1]
    fun typeValue(pool: Array<CONSTANT>): CONSTANT = pool[name-1]

    companion object {
        const val name = "ConstantNameAndTypeInfo"
        const val tag = ConstantTags.CONSTANT_NAME_AND_TYPE
    }

}
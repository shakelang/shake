package io.github.shakelang.jvmlib.constants

class ConstantStringInfo(val value: Int) : CONSTANT() {

    override val tag: Byte get() = ConstantStringInfo.tag
    override val type: String get() = name

    override fun toJson() = super.toJson().with("value", value)
    fun getValue(pool: ConstantPool) = pool[value] as ConstantUtf8Info

    companion object {
        const val name = "ConstantStringInfo"
        const val tag = ConstantTags.CONSTANT_STRING
    }

}
package io.github.shakelang.jvmlib.constants

class ConstantClassInfo(val value: Int) : CONSTANT() {

    override val tag: Byte get() = ConstantClassInfo.tag
    override val type: String get() = name
    override fun toJson() = super.toJson().with("value", value)
    fun getClassValue(pool: Array<CONSTANT>) = pool[value-1] as ConstantUtf8Info

    companion object {
        const val name = "ConstantClassInfo"
        const val tag = ConstantTags.CONSTANT_CLASS
    }

}
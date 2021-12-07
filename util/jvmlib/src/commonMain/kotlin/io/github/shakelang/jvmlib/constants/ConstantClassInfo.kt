package io.github.shakelang.jvmlib.constants

class ConstantClassInfo(val value: Int) : CONSTANT() {

    override val tag: Byte get() = ConstantClassInfo.tag
    override val type: String get() = name
    override fun toJson() = super.toJson().with("value", value)

    fun getValue(pool: ConstantPool) = pool[value] as ConstantUtf8Info

    companion object {
        const val name = "ConstantClassInfo"
        const val tag = ConstantTags.CONSTANT_CLASS
    }

}
package io.github.shakelang.jvmlib.constants

class ConstantNameAndTypeInfo(val value: Int, val name: Int) : ConstantInfo() {

    override val tag: Byte get() = ConstantNameAndTypeInfo.tag
    override val type: String get() = ConstantNameAndTypeInfo.name

    override fun toJson() = super.toJson().with("name", value).with("type", name)
    fun getName(pool: ConstantPool) = pool[name] as ConstantUtf8Info
    fun getValue(pool: ConstantPool) = pool[value] as ConstantUtf8Info

    companion object {
        const val name = "ConstantNameAndTypeInfo"
        const val tag = ConstantTags.CONSTANT_NAME_AND_TYPE
    }

}
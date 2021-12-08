package io.github.shakelang.jvmlib.constants

class ConstantMethodTypeInfo(val index: Int) : ConstantInfo() {

    override val tag: Byte get() = ConstantMethodrefInfo.tag
    override val type: String get() = ConstantMethodrefInfo.name
    override fun toJson() = super.toJson().with("index", index)

    fun getIndex(pool: ConstantPool) = pool[index] as ConstantMethodTypeInfo
    fun getValue(pool: ConstantPool) = pool[index] as ConstantMethodTypeInfo
    fun getType(pool: ConstantPool) = pool[index] as ConstantMethodTypeInfo

    companion object {
        const val name = "ConstantMethodTypeInfo"
        const val tag = ConstantTags.CONSTANT_METHODTYPE
    }

}
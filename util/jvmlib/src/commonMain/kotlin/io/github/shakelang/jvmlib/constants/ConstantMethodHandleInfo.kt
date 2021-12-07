package io.github.shakelang.jvmlib.constants

class ConstantMethodHandleInfo(val name: Byte, val index: Int) : CONSTANT() {

    override val tag: Byte get() = ConstantMethodHandleInfo.tag
    override val type: String get() = ConstantMethodHandleInfo.name
    override fun toJson() = super.toJson().with("type", name).with("index", index)

    fun getIndex(pool: ConstantPool) = pool[index]
    fun getValue(pool: ConstantPool) = pool[index]


    companion object {
        const val name = "ConstantMethodHandleInfo"
        const val tag = ConstantTags.CONSTANT_METHOD_HANDLE
    }

}
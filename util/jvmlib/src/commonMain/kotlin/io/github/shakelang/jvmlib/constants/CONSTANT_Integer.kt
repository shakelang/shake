package io.github.shakelang.jvmlib.constants

class CONSTANT_Integer(val value: Int) : CONSTANT() {

    override val tag: Byte get() = 3
    override fun toJson() = super.toJson().with("value", value)
    override val type: String get() = "CONSTANT_Integer"

}
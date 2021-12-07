package io.github.shakelang.jvmlib.constants

class IdentifyModule(val identifyModule: Short) : JavaClassConstant() {

    override val tag: Byte = 19
    override fun toJson() = super.toJson().with("identify_module", identifyModule)
    override val name: String get() = "IdentifyModule"

}
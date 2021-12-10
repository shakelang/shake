package io.github.shakelang.jvmlib.infos.constants

class IdentifyModule(val identifyModule: Short) : ConstantInfo() {

    override val tag: Byte = 19
    override fun toJson() = super.toJson().with("identify_module", identifyModule)
    override val type: String get() = "IdentifyModule"

}
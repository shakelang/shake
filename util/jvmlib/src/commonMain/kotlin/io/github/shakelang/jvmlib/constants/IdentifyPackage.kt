package io.github.shakelang.jvmlib.constants

class IdentifyPackage(val identifyPackage: Short) : JavaClassConstant() {

    override val tag: Byte = 20
    override fun toJson() = super.toJson().with("identify_package", identifyPackage)
    override val name: String get() = "IdentifyPackage"

}
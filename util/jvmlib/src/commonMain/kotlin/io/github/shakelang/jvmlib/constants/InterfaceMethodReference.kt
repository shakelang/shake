package io.github.shakelang.jvmlib.constants

class InterfaceMethodReference(val classRef: Int, val nameTypeRef: Int) : JavaClassConstant() {

    override val tag: Byte = 10
    override fun toJson() = super.toJson().with("classRef", classRef).with("nameTypeRef", nameTypeRef)
    override val name: String get() = "InterfaceMethodReference"

}
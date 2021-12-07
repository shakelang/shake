package io.github.shakelang.jvmlib.constants

class MethodReference(val classRef: Int, val nameTypeRef: Int) : JavaClassConstant() {

    override val tag: Byte = 10
    override fun toJson() = super.toJson().with("classRef", classRef).with("nameTypeRef", nameTypeRef)
    override val name: String get() = "MethodReference"

}
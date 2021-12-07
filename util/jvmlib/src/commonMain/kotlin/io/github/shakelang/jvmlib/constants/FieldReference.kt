package io.github.shakelang.jvmlib.constants

class FieldReference(val classRef: Int, val nameTypeRef: Int) : JavaClassConstant() {

    override val tag: Byte = 9
    override fun toJson() = super.toJson().with("classRef", classRef).with("nameTypeRef", nameTypeRef)
    override val name: String get() = "FieldReference"

}
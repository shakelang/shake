package io.github.shakelang.jvmlib.constants

class ClassReference(private val value: Int) : JavaClassConstant() {

    override val tag: Byte = 6
    override fun toJson() = super.toJson().with("value", value)
    override val name: String get() = "ClassReference"
    fun getClassValue(pool: Array<JavaClassConstant>) = pool[value-1] as JavaClassStringConstant

}
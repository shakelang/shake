package io.github.shakelang.shake.processor.program

open class ShakeConstructor (
    val clazz: ShakeClass,
    val body: String,
    val isStrict: Boolean,
    val isPrivate: Boolean,
    val isProtected: Boolean,
    val isPublic: Boolean,
    val name: String? = null
) {
    lateinit var parameters: List<ShakeParameter>
        private set

    constructor(
        clazz: ShakeClass,
        parameters: List<ShakeParameter>,
        body: String,
        isStrict: Boolean,
        isPrivate: Boolean,
        isProtected: Boolean,
        isPublic: Boolean,
        name: String? = null
    ): this(clazz, body, isStrict, isPrivate, isProtected, isPublic, name) {
        this.parameters = parameters
    }

    fun lateinitParameterTypes(names: List<String>): List<(ShakeType) -> ShakeType> {
        this.parameters = names.map {
            ShakeParameter(it)
        }
        return this.parameters.map {
            it.lateinitType()
        }
    }
}
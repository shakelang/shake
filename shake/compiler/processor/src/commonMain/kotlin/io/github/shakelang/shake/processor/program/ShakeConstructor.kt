package io.github.shakelang.shake.processor.program

open class ShakeConstructor (
    val name: String?,
    val body: String,
    val isStrict: Boolean,
    val isPrivate: Boolean,
    val isProtected: Boolean,
    val isPublic: Boolean,
) {
    lateinit var parameters: List<ShakeParameter>
        private set

    constructor(
        name: String,
        parameters: List<ShakeParameter>,
        body: String,
        isStrict: Boolean,
        isPrivate: Boolean,
        isProtected: Boolean,
        isPublic: Boolean
    ): this(name, body, isStrict, isPrivate, isProtected, isPublic) {
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
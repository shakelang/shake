package io.github.shakelang.shake.processor

class ShakeClassField (
    name: String,
    isStatic: Boolean,
    isFinal: Boolean,
    isAbstract: Boolean,
    isPrivate: Boolean,
    isProtected: Boolean,
    isPublic: Boolean,
): ShakeField(
    name,
    isStatic,
    isFinal,
    isAbstract,
    isPrivate,
    isProtected,
    isPublic
) {
    lateinit var clazz: ShakeClass
        private set
}
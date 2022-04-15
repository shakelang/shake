package io.github.shakelang.shake.processor.program

class ShakeClassMethod (
    name: String,
    body: String,
    isStatic: Boolean,
    isFinal: Boolean,
    isAbstract: Boolean,
    isSynchronized: Boolean,
    isStrict: Boolean,
    isPrivate: Boolean,
    isProtected: Boolean,
    isPublic: Boolean,
) : ShakeMethod(
    name,
    body,
    isStatic,
    isFinal,
    isAbstract,
    isSynchronized,
    isStrict,
    isPrivate,
    isProtected,
    isPublic
) {
    lateinit var clazz: ShakeClass
        private set
}
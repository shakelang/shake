package io.github.shakelang.shake.processor

abstract class ShakeType (
    val name: String,
) {
    abstract val kind: Kind

    enum class Kind {
        PRIMITIVE,
        OBJECT,
        ARRAY,
    }
    enum class PrimitiveType {
        BOOLEAN,
        BYTE,
        CHAR,
        SHORT,
        INT,
        LONG,
        FLOAT,
        DOUBLE,
        UNSIGNED_BYTE,
        UNSIGNED_SHORT,
        UNSIGNED_INT,
        UNSIGNED_LONG,
        VOID,
    }
    open class Primitive (
        name: String,
        val type: PrimitiveType,
    ) : ShakeType(name) {
        override val kind: Kind
            get() = Kind.PRIMITIVE
    }

    class Object (
        name: String,
        val clazz: ShakeClass
    ) : ShakeType(name) {
        override val kind: Kind
            get() = Kind.OBJECT
    }

    class Array (
        name: String,
        val elementType: ShakeType
    ) : ShakeType(name) {
        override val kind: Kind
            get() = Kind.ARRAY
    }

    object Primitives {
        val BOOLEAN = Primitive("boolean", PrimitiveType.BOOLEAN)
        val BYTE = Primitive("byte", PrimitiveType.BYTE)
        val CHAR = Primitive("char", PrimitiveType.CHAR)
        val SHORT = Primitive("short", PrimitiveType.SHORT)
        val INT = Primitive("int", PrimitiveType.INT)
        val LONG = Primitive("long", PrimitiveType.LONG)
        val FLOAT = Primitive("float", PrimitiveType.FLOAT)
        val DOUBLE = Primitive("double", PrimitiveType.DOUBLE)
        val UNSIGNED_BYTE = Primitive("unsigned byte", PrimitiveType.UNSIGNED_BYTE)
        val UNSIGNED_SHORT = Primitive("unsigned short", PrimitiveType.UNSIGNED_SHORT)
        val UNSIGNED_INT = Primitive("unsigned int", PrimitiveType.UNSIGNED_INT)
        val UNSIGNED_LONG = Primitive("unsigned long", PrimitiveType.UNSIGNED_LONG)
        val VOID = Primitive("void", PrimitiveType.VOID)
    }

    companion object {
        fun array(elementType: ShakeType): ShakeType {
            return Array("${elementType.name}[]", elementType)
        }
        fun objectType(clazz: ShakeClass): ShakeType {
            return Object(clazz.name, clazz)
        }
    }
}
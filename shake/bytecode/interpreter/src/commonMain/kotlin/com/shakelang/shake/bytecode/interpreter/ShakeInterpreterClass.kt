package com.shakelang.shake.bytecode.interpreter

object ClassRegister {

    private val classes = mutableMapOf<String, ShakeInterpreterClass>()

    fun registerClass(name: String, clazz: ShakeInterpreterClass) {
        classes[name] = clazz
    }
    fun getClass(name: String): ShakeInterpreterClass? = classes[name]

}

class ShakeInterpreterClass (
    val methods : List<ShakeInterpreterMethod>,
    val fields : List<ShakeInterpreterField>,
) {

}

class ShakeInterpreterMethod (
    val qualifiedName: String,
    val isStatic: Boolean
) {

}

class ShakeInterpreterField (val name: String) {
}

open class ShakeInterpreterType (
    val name: String,
    val type: Type,
    val subType : ShakeInterpreterType? = null,
    val classType: ShakeInterpreterClass? = null
) {

    enum class Type {
        INT,
        FLOAT,
        DOUBLE,
        LONG,
        SHORT,
        BYTE,
        CHAR,
        BOOLEAN,
        STRING,
        OBJECT,
        ARRAY,
        VOID
    }

}
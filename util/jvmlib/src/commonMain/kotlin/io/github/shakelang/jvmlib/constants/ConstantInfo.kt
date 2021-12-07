package io.github.shakelang.jvmlib.constants

import io.github.shakelang.shason.json


abstract class CONSTANT {
    abstract val tag: Byte
    abstract val type: String

    open fun toJson(): Map<String, Any> = mapOf("tag_type" to type, "tag" to tag)
    override fun toString(): String = json.stringify(toJson())

}

fun <K, V> Map<K, V>.with(k: K, v: V): Map<K, V>{
    val map = mutableMapOf<K, V>()
    map.putAll(this)
    map[k] = v
    return map.toMap()
}

object ConstantTags {
    const val CONSTANT_UTF8 : Byte = 1
    const val CONSTANT_INTEGER : Byte  = 3
    const val CONSTANT_FLOAT : Byte  = 4
    const val CONSTANT_LONG : Byte  = 5
    const val CONSTANT_DOUBLE : Byte  = 6
    const val CONSTANT_CLASS : Byte  = 7
    const val CONSTANT_STRING : Byte  = 8
    const val CONSTANT_FIELD_REF : Byte  = 9
    const val CONSTANT_METHOD_REF : Byte  = 10
    const val CONSTANT_INTERFACE_METHOD_REF : Byte  = 11
    const val CONSTANT_NAME_AND_TYPE : Byte  = 12
    const val CONSTANT_METHOD_HANDLE : Byte  = 15
    const val CONSTANT_METHODTYPE : Byte  = 16
    const val CONSTANT_INVOKE_DYNAMIC : Byte  = 18
}
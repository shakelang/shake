package io.github.shakelang.jvmlib.constants

import io.github.shakelang.shason.json


abstract class JavaClassConstant() {
    abstract val tag: Byte
    abstract val name: String

    open fun toJson(): Map<String, Any> = mapOf("tag_type" to name, "tag" to tag)
    override fun toString(): String = json.stringify(toJson())

}

fun <K, V> Map<K, V>.with(k: K, v: V): Map<K, V>{
    val map = mutableMapOf<K, V>()
    map.putAll(this)
    map[k] = v
    return map.toMap()
}

package com.shakelang.shake.util.changelog
import com.shakelang.shake.util.shason.elements.JsonElement
import com.shakelang.shake.util.shason.elements.JsonObject
import com.shakelang.shake.util.shason.json

fun tagRef(name: String): String {
    return "refs/tags/$name"
}

class TagStash (
    val name: String,
) {
    fun toObject(): Any {
        return mapOf(
            "name" to name,
        )
    }

    override fun toString(): String {
        return json.stringify(toObject())
    }

    companion object {
        fun fromObject(obj: JsonObject): TagStash {
            if (!obj.containsKey("name")) throw IllegalArgumentException("TagStash object has no name")
            val name = obj["name"]!!
            if(!name.isJsonPrimitive() || !name.toJsonPrimitive().isString()) throw IllegalArgumentException("TagStash name is not a string")
            return TagStash(name.toJsonPrimitive().toStringElement().value)
        }
    }
}

class TagStashList (
    val tags: MutableList<TagStash>,
) {

    fun add(tag: TagStash) {
        tags.add(tag)
    }

    fun remove(tag: TagStash) {
        tags.remove(tag)
    }

    fun remove(name: String) {
        tags.removeIf { it.name == name }
    }

    fun contains(tag: TagStash): Boolean {
        return tags.contains(tag)
    }

    fun contains(name: String): Boolean {
        return tags.any { it.name == name }
    }

    operator fun get(name: String): TagStash? {
        return tags.find { it.name == name }
    }

    operator fun get(id: Int): TagStash? {
        return tags[id]
    }

    fun clear() {
        tags.clear()
    }

    fun toObject(): Any {
        return mapOf(
            "tags" to tags.map { it.toObject() }
        )
    }

    override fun toString(): String {
        return json.stringify(toObject())
    }

    companion object {
        fun fromObject(obj: JsonObject): TagStashList {
            if (!obj.containsKey("tags")) throw IllegalArgumentException("TagStashList object has no tags")
            val tags = obj["tags"]!!
            if(!tags.isJsonArray()) throw IllegalArgumentException("TagStashList tags is not an array")
            return TagStashList(tags.toJsonArray().map {
                if(!it.isJsonObject()) throw IllegalArgumentException("TagStashList tags array contains non object")
                TagStash.fromObject(it.toJsonObject())
            }.toMutableList())
        }

        fun fromObject(obj: JsonElement): TagStashList {
            if(!obj.isJsonObject()) throw IllegalArgumentException("TagStashList object is not an object")
            return fromObject(obj.toJsonObject())
        }

        fun empty(): TagStashList {
            return TagStashList(mutableListOf())
        }
    }
}

fun Changelog.readStash(): TagStashList {
    val file = project.file(".changelog/stash.json")
    if(!file.exists()) return TagStashList.empty()
    return TagStashList.fromObject(json.parse(file.readText()))
}

fun Changelog.writeStash(stash: TagStashList) {
    val file = project.file(".changelog/stash.json")
    file.writeText(stash.toString())
}
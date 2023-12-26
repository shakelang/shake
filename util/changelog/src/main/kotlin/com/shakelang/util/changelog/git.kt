package com.shakelang.util.changelog

import com.shakelang.util.shason.elements.JsonElement
import com.shakelang.util.shason.elements.JsonObject
import com.shakelang.util.shason.json
import java.io.BufferedReader
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.*

fun tagRef(name: String): String {
    return "refs/tags/$name"
}

class TagStash(
    val name: String
) {
    fun toObject(): Any {
        return mapOf(
            "name" to name
        )
    }

    override fun toString(): String {
        return json.stringify(toObject())
    }

    companion object {
        fun fromObject(obj: JsonObject): TagStash {
            if (!obj.containsKey("name")) throw IllegalArgumentException("TagStash object has no name")
            val name = obj["name"]!!
            if (!name.isJsonPrimitive() || !name.toJsonPrimitive()
                .isString()
            ) {
                throw IllegalArgumentException("TagStash name is not a string")
            }
            return TagStash(name.toJsonPrimitive().toStringElement().value)
        }
    }
}

class TagStashList(
    val tags: MutableList<TagStash>
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
            if (!tags.isJsonArray()) throw IllegalArgumentException("TagStashList tags is not an array")
            return TagStashList(
                tags.toJsonArray().map {
                    if (!it.isJsonObject()) throw IllegalArgumentException("TagStashList tags array contains non object")
                    TagStash.fromObject(it.toJsonObject())
                }.toMutableList()
            )
        }

        fun fromObject(obj: JsonElement): TagStashList {
            if (!obj.isJsonObject()) throw IllegalArgumentException("TagStashList object is not an object")
            return fromObject(obj.toJsonObject())
        }

        fun empty(): TagStashList {
            return TagStashList(mutableListOf())
        }
    }
}

fun Changelog.readStash(): TagStashList {
    val file = project.file(".changelog/stash.json")
    if (!file.exists()) return TagStashList.empty()
    return TagStashList.fromObject(json.parse(file.readText()))
}

fun Changelog.writeStash(stash: TagStashList) {
    val file = project.file(".changelog/stash.json")
    file.writeText(stash.toString())
}

class ReleaseTag(
    val tagName: String,
    val sha: String,
    val version: Version,
    val project: ProjectStructure,
    val timestamp: Date
) {
    override fun toString(): String {
        return json.stringify(
            mapOf(
                "tagName" to tagName,
                "sha" to sha,
                "version" to version.toString(),
                "project" to project.path,
                "timestamp" to timestamp.time
            )
        )
    }
}

fun Changelog.getLocalGitTags(): List<ParsedGitTag> {
    val rt = Runtime.getRuntime()
    val process = rt.exec(arrayOf("git", "show-ref", "--tags"), null, this.project.file("."))
    return parseGit(getOutputLines(process))
}

fun parseGit(list: List<String>) = list.map {
    // remove annotated tag suffix (^{})
    it.replace("\\^\\{\\}\$".toRegex(), "")
}.map {
    ParsedGitTag.parse(it)
}

val dateFormat = SimpleDateFormat("HH:mm:ss Z")
fun Changelog.getTimestampForTag(tag: ParsedGitTag): Date {
    val rt = Runtime.getRuntime()
    val process =
        rt.exec(arrayOf("git", "log", "-n", "1", "--pretty=format:\"%ci\"", tag.name), null, this.project.file("."))
    val lines = parseGit(getOutputLines(process))
    if (lines.size != 1) throw IllegalArgumentException("Invalid git log output")

    return dateFormat.parse(lines[0].name.replace("\"", ""))
}

data class ParsedGitTag(
    val name: String,
    val sha: String
) {

    override fun equals(other: Any?): Boolean {
        if (other !is ParsedGitTag) return false
        return this.sha == other.sha
    }

    override fun hashCode(): Int {
        return sha.hashCode()
    }

    companion object {
        fun parse(line: String): ParsedGitTag {
            val parts = line.split("\\s+".toRegex(), 2)

            if (parts.size < 2) {
                throw IllegalArgumentException("Invalid git tag line: $line")
            }

            return ParsedGitTag(parts[1], parts[0])
        }
    }
}

fun getOutputLines(process: Process): List<String> {
    val reader = BufferedReader(InputStreamReader(process.inputStream))
    val lines = mutableListOf<String>()

    reader.useLines { it.forEach { line -> lines.add(line) } }

    return lines
}

fun extractPathAndVersionFromTag(tagName: String): Pair<String, String> {
    val regex = Regex("""refs/tags/release/(.+?)/v(.+)""")
    return regex.find(tagName)?.let { matchResult ->
        val path = matchResult.groupValues[1]
        val version = matchResult.groupValues[2]
        path to version
    } ?: throw IllegalArgumentException("Invalid tag name: $tagName")
}

fun Changelog.getAllTags(): List<ReleaseTag> {
    val structure = this.readStructure()
    return getLocalGitTags().filter { it.name.startsWith("refs/tags/release/") }.map {
        val name = it.name
        val (path, version) = extractPathAndVersionFromTag(name)
        val prjPath = ":${path.replace("/", ":")}"
        ReleaseTag(
            name,
            it.sha,
            Version.fromString(version),
            structure.find { struct ->
                struct.path == prjPath
            } ?: throw IllegalArgumentException("Invalid path in tag-name: \"$name\" (Extracted Path: \"$prjPath\")"),
            getTimestampForTag(it)
        )
    }
}

fun Changelog.getChangesSinceTag(tag: String): List<String> {
    val rt = Runtime.getRuntime()
    val process = rt.exec(arrayOf("git", "diff", "--name-only", tag), null, this.project.file("."))
    val lines = getOutputLines(process)
    return lines.map { it.replace("\"", "") }
}

fun Changelog.dirChangedSinceTag(tag: String, dir: String): Boolean {
    val rt = Runtime.getRuntime()
    val process = rt.exec(arrayOf("git", "diff", "--name-only", tag, "--", dir), null, this.project.file("."))
    val lines = getOutputLines(process)
    return lines.isNotEmpty()
}

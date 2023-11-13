package com.shakelang.shake.util.changelog

import com.shakelang.shake.util.shason.elements.JsonObject
import com.shakelang.shake.util.shason.json

class ChangelogVersion (
    val version: Version,
    val changes: List<String>
) {
    fun toObject(): Map<String, Any> {
        val map = mutableMapOf<String, Any>()
        map["version"] = version.toString()
        map["changes"] = changes
        return map
    }

    companion object {
        fun fromObject(obj: JsonObject): ChangelogVersion {
            if (!obj.containsKey("version")) throw IllegalArgumentException("ChangelogVersion object has no version")
            if (!obj.containsKey("changes")) throw IllegalArgumentException("ChangelogVersion object has no changes")

            val versionElement = obj["version"]!!
            val changesElement = obj["changes"]!!

            if (!versionElement.isJsonPrimitive() || !versionElement.toJsonPrimitive().isString())
                throw IllegalArgumentException("ChangelogVersion version is not a string")
            if (!changesElement.isJsonArray())
                throw IllegalArgumentException("ChangelogVersion changes is not an array")

            val version = Version.fromString(versionElement.toJsonPrimitive().toStringElement().value)
            val changes = changesElement.toJsonArray().map { it.toJsonPrimitive().toStringElement().value }

            return ChangelogVersion(version, changes)
        }
    }
}

class PackageChangelog (
    val path: String,
    val name: String,
    val description: String,
    val versions: List<ChangelogVersion>
) {

    fun toObject(): Map<String, Any> {
        val map = mutableMapOf<String, Any>()
        map["path"] = path
        map["name"] = name
        map["description"] = description
        map["versions"] = versions.map { it.toObject() }
        return map
    }

    companion object {
        fun fromObject(obj: JsonObject): PackageChangelog {
            if (!obj.containsKey("path")) throw IllegalArgumentException("PackageChangelog object has no path")
            if (!obj.containsKey("name")) throw IllegalArgumentException("PackageChangelog object has no name")
            if (!obj.containsKey("description")) throw IllegalArgumentException("PackageChangelog object has no description")
            if (!obj.containsKey("versions")) throw IllegalArgumentException("PackageChangelog object has no versions")

            val pathElement = obj["path"]!!
            val nameElement = obj["name"]!!
            val descriptionElement = obj["description"]!!
            val versionsElement = obj["versions"]!!

            if (!pathElement.isJsonPrimitive() || !pathElement.toJsonPrimitive().isString())
                throw IllegalArgumentException("PackageChangelog path is not a string")
            if (!nameElement.isJsonPrimitive() || !nameElement.toJsonPrimitive().isString())
                throw IllegalArgumentException("PackageChangelog name is not a string")
            if (!descriptionElement.isJsonPrimitive() || !descriptionElement.toJsonPrimitive().isString())
                throw IllegalArgumentException("PackageChangelog description is not a string")
            if (!versionsElement.isJsonArray())
                throw IllegalArgumentException("PackageChangelog versions is not an array")

            val path = pathElement.toJsonPrimitive().toStringElement().value
            val name = nameElement.toJsonPrimitive().toStringElement().value
            val description = descriptionElement.toJsonPrimitive().toStringElement().value
            val versions = versionsElement.toJsonArray().map { ChangelogVersion.fromObject(it.toJsonObject()) }

            return PackageChangelog(path, name, description, versions)
        }
    }
}

class ChangelogMap (
    val packages: List<PackageChangelog>
) {

    fun toObject(): Map<String, Any> {
        val map = mutableMapOf<String, Any>()
        map["packages"] = packages.map { it.toObject() }
        return map
    }

    companion object {
        fun fromObject(obj: JsonObject): ChangelogMap {
            if (!obj.containsKey("packages")) throw IllegalArgumentException("ChangelogMap object has no packages")

            val packagesElement = obj["packages"]!!

            if (!packagesElement.isJsonArray())
                throw IllegalArgumentException("ChangelogMap packages is not an array")

            val packages = packagesElement.toJsonArray().map { PackageChangelog.fromObject(it.toJsonObject()) }

            return ChangelogMap(packages)
        }

        fun fromString(string: String): ChangelogMap {
            val obj = json.parse(string)
            if (!obj.isJsonObject()) throw IllegalArgumentException("ChangelogMap is not a json object")
            return fromObject(obj.toJsonObject())
        }
    }
}

fun Changelog.readMap(): ChangelogMap {
    val file = project.file(".changelog/changelogs.json")
    if (!file.exists()) return ChangelogMap(emptyList())
    return ChangelogMap.fromString(file.readText())
}

fun Changelog.writeMap(map: ChangelogMap) {
    val file = project.file(".changelog/changelogs.json")
    file.writeText(json.stringify(map.toObject(), true))
}
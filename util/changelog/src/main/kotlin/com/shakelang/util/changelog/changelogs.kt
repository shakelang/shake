package com.shakelang.util.changelog

import com.shakelang.util.markdown.MutableMarkdownDocument
import com.shakelang.util.shason.elements.JsonObject
import com.shakelang.util.shason.json

class ChangelogVersion(
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

            if (!versionElement.isJsonPrimitive() || !versionElement.toJsonPrimitive().isString()) {
                throw IllegalArgumentException("ChangelogVersion version is not a string")
            }
            if (!changesElement.isJsonArray()) {
                throw IllegalArgumentException("ChangelogVersion changes is not an array")
            }

            val version = Version.fromString(versionElement.toJsonPrimitive().toStringElement().value)
            val changes = changesElement.toJsonArray().map { it.toJsonPrimitive().toStringElement().value }

            return ChangelogVersion(version, changes)
        }
    }
}

class PackageChangelog(
    val path: String,
    val name: String,
    val description: String,
    val versions: MutableList<ChangelogVersion> = mutableListOf()
) {

    val folderPath: String
        get() = path.replace(":", "/").substring(1)

    val latestRelease: ChangelogVersion?
        get() {
            if (versions.isEmpty()) return null
            return versions.last()
        }

    val changedSinceLastRelease: Boolean
        get() {
            val latestRelease = latestRelease ?: return true
            return Changelog.instance.dirChangedSinceTag(
                tagRef("release/$folderPath/v${latestRelease.version}"),
                folderPath
            )
        }

    val latestReleaseVersion: Version
        get() {
            val latestRelease = latestRelease ?: return Version(0, 0, 0, "SNAPSHOT")
            return latestRelease.version
        }

    val latestReleaseWasSnapshot: Boolean
        get() {
            return latestReleaseVersion.suffix != ""
        }

    fun addVersion(version: ChangelogVersion) = versions.add(version)
    fun add(version: ChangelogVersion) = addVersion(version)

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

            if (!pathElement.isJsonPrimitive() || !pathElement.toJsonPrimitive().isString()) {
                throw IllegalArgumentException("PackageChangelog path is not a string")
            }
            if (!nameElement.isJsonPrimitive() || !nameElement.toJsonPrimitive().isString()) {
                throw IllegalArgumentException("PackageChangelog name is not a string")
            }
            if (!descriptionElement.isJsonPrimitive() || !descriptionElement.toJsonPrimitive().isString()) {
                throw IllegalArgumentException("PackageChangelog description is not a string")
            }
            if (!versionsElement.isJsonArray()) {
                throw IllegalArgumentException("PackageChangelog versions is not an array")
            }

            val path = pathElement.toJsonPrimitive().toStringElement().value
            val name = nameElement.toJsonPrimitive().toStringElement().value
            val description = descriptionElement.toJsonPrimitive().toStringElement().value
            val versions = versionsElement.toJsonArray().map { ChangelogVersion.fromObject(it.toJsonObject()) }

            return PackageChangelog(path, name, description, versions.toMutableList())
        }
    }
}

class ChangelogMap(
    val packages: MutableList<PackageChangelog>
) {

    fun toObject(): Map<String, Any> {
        val map = mutableMapOf<String, Any>()
        map["packages"] = packages.map { it.toObject() }
        return map
    }

    fun add(it: PackageChangelog) = packages.add(it)

    companion object {
        fun fromObject(obj: JsonObject): ChangelogMap {
            if (!obj.containsKey("packages")) throw IllegalArgumentException("ChangelogMap object has no packages")

            val packagesElement = obj["packages"]!!

            if (!packagesElement.isJsonArray()) {
                throw IllegalArgumentException("ChangelogMap packages is not an array")
            }

            val packages = packagesElement.toJsonArray().map { PackageChangelog.fromObject(it.toJsonObject()) }

            return ChangelogMap(packages.toMutableList())
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
    if (!file.exists()) {
        return ChangelogMap(
            readStructure().map {
                PackageChangelog(
                    it.path,
                    it.name,
                    it.description
                )
            }.toMutableList()
//        mutableListOf()
        )
    }
    return ChangelogMap.fromString(file.readText())
}

fun Changelog.writeMap(map: ChangelogMap) {
    val file = project.file(".changelog/changelogs.json")
    file.writeText(json.stringify(map.toObject(), true))
}

fun Changelog.renderChangelog(map: PackageChangelog): String {
    return MutableMarkdownDocument {
        h1(map.name)
        h2("Description")
        p(map.description)
        h2("Versions")
        map.versions.forEach { version ->
            h3(version.version.toString())
            version.changes.forEach { change ->
                p(change)
            }
        }
    }.render()
}

fun Changelog.renderChangelog(map: ChangelogMap) {
    map.packages.forEach { pkg ->
        // get project for path
        val project = Changelog.instance.project.project(pkg.path)
        val file = project.file("CHANGELOG.md")
        if (file.canonicalFile.name != "CHANGELOG.md") {
            file.canonicalFile.delete()
        }
        file.writeText(renderChangelog(pkg))
    }
}

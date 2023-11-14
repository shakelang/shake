package com.shakelang.shake.util.changelog

import com.shakelang.shake.util.shason.elements.JsonObject
import com.shakelang.shake.util.shason.json
import org.gradle.api.Project
import java.util.*

class ProjectStructure(
    val path: String,
    val name: String,
    val version: Version,
    val description: String,
    val author: String,
    val license: String,
    val dependencies: List<String>
) {
    val toJsonObject
        get() = mapOf(
            "path" to path,
            "name" to name,
            "version" to version.toString(),
            "description" to description,
            "author" to author,
            "license" to license,
            "dependencies" to dependencies
        )

    override fun toString(): String {
        return json.stringify(toJsonObject, true)
    }

    companion object {
        fun fromJsonObject(structure: JsonObject): ProjectStructure {
            if (!structure.containsKey("path")) throw IllegalArgumentException("Structure does not contain key 'path'")
            if (!structure.containsKey("version")) throw IllegalArgumentException("Structure does not contain key 'version'")
            if (!structure.containsKey("description")) throw IllegalArgumentException("Structure does not contain key 'description'")
            if (!structure.containsKey("author")) throw IllegalArgumentException("Structure does not contain key 'author'")
            if (!structure.containsKey("license")) throw IllegalArgumentException("Structure does not contain key 'license'")
            if (!structure.containsKey("dependencies")) throw IllegalArgumentException("Structure does not contain key 'dependencies'")

            val path = structure["path"]!!
            val name =
                if (structure.containsKey("name") && structure["name"]!!.isJsonPrimitive() &&
                    structure["name"]!!.toJsonPrimitive().isString()
                ) {
                    structure["name"]!!.toJsonPrimitive().toStringElement().value
                } else {
                    ""
                }
            val version = structure["version"]!!
            val description = structure["description"]!!
            val author = structure["author"]!!
            val license = structure["license"]!!
            val dependencies = structure["dependencies"]!!

            if (!(path.isJsonPrimitive()) || !path.toJsonPrimitive()
                    .isString()
            ) {
                throw IllegalArgumentException("Structure key 'path' is not a string")
            }
            if (!(version.isJsonPrimitive()) || !version.toJsonPrimitive()
                    .isString()
            ) {
                throw IllegalArgumentException("Structure key 'version' is not a string")
            }
            if (!(description.isJsonPrimitive()) || !description.toJsonPrimitive()
                    .isString()
            ) {
                throw IllegalArgumentException("Structure key 'description' is not a string")
            }
            if (!(author.isJsonPrimitive()) || !author.toJsonPrimitive()
                    .isString()
            ) {
                throw IllegalArgumentException("Structure key 'author' is not a string")
            }
            if (!(license.isJsonPrimitive()) || !license.toJsonPrimitive()
                    .isString()
            ) {
                throw IllegalArgumentException("Structure key 'license' is not a string")
            }
            if (!(dependencies.isJsonArray())) throw IllegalArgumentException("Structure key 'dependencies' is not a array")

            return ProjectStructure(
                path.toJsonPrimitive().toStringElement().value,
                name,
                Version.fromString(version.toJsonPrimitive().toStringElement().value),
                description.toJsonPrimitive().toStringElement().value,
                author.toJsonPrimitive().toStringElement().value,
                license.toJsonPrimitive().toStringElement().value,
                dependencies.toJsonArray().map {
                    if (!(it.isJsonPrimitive()) || !it.toJsonPrimitive()
                            .isString()
                    ) {
                        throw IllegalArgumentException("Structure key 'dependencies' is not a string")
                    }
                    it.toJsonPrimitive().toStringElement().value
                }
            )
        }
    }
}

class ChangelogStructure(
    val lastUpdate: Date,
    val projects: List<ProjectStructure>
) {
    fun toJsonObject(): Map<String, Any?> {
        return mapOf(
            "projects" to projects.map {
                it.toJsonObject
            },
            "lastUpdate" to lastUpdate.time
        )
    }

    override fun toString(): String {
        return json.stringify(toJsonObject(), true)
    }

    companion object {

        fun fromJsonObject(structure: JsonObject): ChangelogStructure {
            if (!structure.containsKey("projects")) throw IllegalArgumentException("Structure does not contain key 'projects'")
            if (!structure.containsKey("lastUpdate")) throw IllegalArgumentException("Structure does not contain key 'lastUpdate'")

            val projects = structure["projects"]!!
            val lastUpdate = structure["lastUpdate"]!!

            if (!(projects.isJsonArray())) throw IllegalArgumentException("Structure key 'projects' is not a array")
            if (!(lastUpdate.isJsonPrimitive()) || !lastUpdate.toJsonPrimitive()
                    .isInt()
            ) {
                throw IllegalArgumentException("Structure key 'lastUpdate' is not a number")
            }

            return ChangelogStructure(
                Date(lastUpdate.toJsonPrimitive().toInt().value),
                projects.toJsonArray().map {
                    if (!(it.isJsonObject())) throw IllegalArgumentException("Structure key 'projects' is not a object")
                    ProjectStructure.fromJsonObject(it.toJsonObject())
                }
            )
        }

        fun fromString(structure: String): ChangelogStructure {
            val result = json.parse(structure)
            if (!(result.isJsonObject())) throw IllegalArgumentException("Structure is not a object")
            return fromJsonObject(result.toJsonObject())
        }
    }
}

fun Changelog.readStructure() = project.subprojects.map {
    ProjectStructure(
        it.path,
        it.group.toString() + ":" + it.name,
        try {
            Version.fromString(it.version.toString())
        } catch (e: Exception) {
            project.logger.warn("Could not read version of project ${it.path}, using 0.1.0-SNAPSHOT instead: ${e.message}")
            Version.fromString("0.1.0-SNAPSHOT")
        },
        it.description ?: "",
        it.group.toString(),
        null ?: "",
        it.dependencies.toString().split(",")
    )
}

fun Changelog.newStructure() {
    val structure = ChangelogStructure(
        Date(),
        readStructure()
    )
    project.file(".changelog/structure.json").writeText(structure.toString())
}

fun Changelog.writeStructure(structure: ChangelogStructure) {
    project.file(".changelog/structure.json").writeText(structure.toString())
}

fun Changelog.updateStructure() {
    project.file(".changelog/structure.json").writeText(
        ChangelogStructure(
            Date(),
            readStructure()
        ).toString()
    )
}

fun Changelog.readStructureFile(): ChangelogStructure {
    return ChangelogStructure.fromString(
        project.file(".changelog/structure.json").readText()
    )
}

fun Project.resolveVersion(): Version =
    Changelog.instance.readStructureFile().projects.find {
        it.path == project.path
    }?.version ?: Version.fromString("0.1.0-SNAPSHOT")

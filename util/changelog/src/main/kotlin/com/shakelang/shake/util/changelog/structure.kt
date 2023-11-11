package com.shakelang.shake.util.changelog

import com.shakelang.shake.util.shason.json
import org.gradle.api.Project
import java.util.*


class ProjectStructure(
    val path: String,
    val version: Version,
    val description: String?,
    val author: String,
    val license: String?,
    val dependencies: List<String>
) {
    val toJsonObject: Map<String, Any?> = mapOf(
        "path" to path,
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

        fun fromJsonObject(structure: Map<String, Any?>): ProjectStructure {
            return ProjectStructure(
                structure["name"] as String,
                Version.fromString(structure["version"] as String),
                structure["description"] as String,
                structure["author"] as String,
                structure["license"] as String,
                structure["dependencies"] as List<String>
            )
        }

        fun fromString(structure: String): ProjectStructure {
            return fromJsonObject(json.parse(structure) as Map<String, Any>)
        }
    }
}

class ChangelogStructure(
    val lastUpdate: Date,
    val projects: List<ProjectStructure>
) {
    fun toJsonObject(): Map<String, Any?> {
        return mapOf(
            "projects" to projects
        )
    }

    override fun toString(): String {
        return json.stringify(toJsonObject(), true)
    }

    companion object {

        fun fromJsonObject(structure: Map<String, Any?>): ChangelogStructure {
            return ChangelogStructure(
                Date(structure["lastUpdate"] as Long),
                (structure["projects"] as List<Map<String, Any?>>).map {
                    ProjectStructure.fromJsonObject(it)
                }
            )
        }

        fun fromString(structure: String): ChangelogStructure {
            val result = json.parse(structure)
            if (result !is Map<*, *>) throw IllegalArgumentException("Structure is not a json object")
            return fromJsonObject(result as Map<String, Any>)
        }

    }
}

fun readStructure(project: Project) = project.subprojects.map {
    ProjectStructure(
        it.name,
        Version.fromString(it.version.toString()),
        it.description,
        it.group.toString(),
        null,
        it.dependencies.toString().split(",")
    )
}


fun newChangelog(project: Project) {
    val structure = ChangelogStructure(
        Date(),
        readStructure(project.rootProject)
    )
    project.rootProject.file(".changelog/structure.json").writeText(structure.toString())
}

fun updateChangelog(project: Project) {
    val structure = ChangelogStructure.fromString(
        project.rootProject.file(".changelog/structure.json").readText()
    )
    structure.projects.forEach {
        val projectStructure = ProjectStructure(
            it.path,
            Version.fromString(project.version.toString()),
            it.description,
            it.author,
            it.license,
            it.dependencies
        )
        project.rootProject.file(".changelog/${it.path}.json").writeText(projectStructure.toString())
    }
    project.rootProject.file(".changelog/structure.json").writeText(structure.toString())
}

fun readChangelog(project: Project): ChangelogStructure {
    return ChangelogStructure.fromString(
        project.rootProject.file(".changelog/structure.json").readText()
    )
}

fun changelogVersion(project: Project): Version
    = readChangelog(project).projects.find {
        it.path == project.path
    }?.version ?: Version.fromString("0.1.0-SNAPSHOT")

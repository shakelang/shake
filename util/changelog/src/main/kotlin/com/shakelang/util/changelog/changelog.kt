package com.shakelang.util.changelog

import com.shakelang.shake.util.changelog.tasks.*
import com.shakelang.util.changelog.tasks.*
import org.gradle.api.Plugin
import org.gradle.api.Project

var Project.private: Boolean
    get() {
        if (!extensions.extraProperties.has("private")) {
            return true
        }
        return extensions.extraProperties.get("private") as Boolean
    }
    set(value) {
        extensions.extraProperties.set("private", value)
    }

var Project.public: Boolean
    get() = !private
    set(value) {
        private = !value
    }

class Changelog : Plugin<Project> {

    init {
        try {
            instance = this
        } catch (e: Exception) {
            throw IllegalStateException("Changelog already initialized")
        }
    }

    lateinit var project: Project
        private set

    override fun apply(project: Project) {
        this.project = project

        if (project != project.rootProject) throw Exception("Changelog plugin can only be applied to root project")

        project.tasks.create("initChangelog", InitChangelogTask::class.java)
        project.tasks.create("bump", BumpTask::class.java)
        project.tasks.create("version", VersionTask::class.java)
        project.tasks.create("createTags", VersionTagsTask::class.java)
        project.tasks.create("resolveProjectDependencies", ResolveProjectDependenciesTask::class.java)
        project.tasks.create("resolveProjectDependents", ResolveProjectDependentsTask::class.java)

        project.afterEvaluate {
            project.tasks.create("changelog", ChangelogCliTask::class.java)
        }

        project.allprojects.forEach {
            it.afterEvaluate {
                it.tasks.create("resolveDependencies", DependencyResolveTreeTask::class.java)
                it.tasks.create("printDependencies", PrintDependencyTreeTask::class.java)
                it.tasks.create("resolveDirectDependents", ResolveDirectDependentsTask::class.java)
                it.tasks.create("printDependentsTree", PrintDirectDependentsTask::class.java)
                it.tasks.create("resolveAllDependents", ResolveAllDependentsTask::class.java)
                it.tasks.create("printAllDependents", PrintAllDependentsTask::class.java)
            }
        }
    }

    companion object {
        lateinit var instance: Changelog
            private set
    }
}

data class TagCreationInfo(val project: ProjectStructure, val version: Version, val message: String)
typealias TagCreation = (TagCreationInfo) -> String

package com.shakelang.shake.util.changelog

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction

fun Project.hasDependencyConfiguration(name: String) =
    configurations.names.contains(name)

interface Dependency {
    val group: String
    val name: String
    val version: String
    val dependencies: List<Dependency>

    fun isProjectDependency() = this is ProjectDependency
    fun isExternalDependency() = this is ExternalDependency

    fun asProjectDependency() = this as ProjectDependency
    fun asExternalDependency() = this as ExternalDependency

    override fun equals(other: Any?): Boolean
}

class ProjectDependency(
    override val group: String,
    override val name: String,
    override val version: String,
    override val dependencies: List<Dependency>,
    val subproject: Project
) : Dependency {
    override fun toString(): String {
        return "$group:$name:$version (project)"
    }

    override fun equals(other: Any?): Boolean {
        if (other !is ProjectDependency) return false
        return other.subproject == subproject
    }

    override fun hashCode(): Int {
        var result = group.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + version.hashCode()
        result = 31 * result + dependencies.hashCode()
        result = 31 * result + subproject.hashCode()
        return result
    }
}

class ExternalDependency(
    override val group: String,
    override val name: String,
    override val version: String,
    override val dependencies: List<Dependency>
) : Dependency {
    override fun toString(): String {
        return "$group:$name:$version (external)"
    }

    override fun equals(other: Any?): Boolean {
        if (other !is ExternalDependency) return false
        return other.group == group && other.name == name && other.version == version
    }

    override fun hashCode(): Int {
        var result = group.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + version.hashCode()
        result = 31 * result + dependencies.hashCode()
        return result
    }
}

open class DependencyResolveTreeTask : DefaultTask() {
    init {
        group = "changelog"
        description = "Resolve the dependency tree of the project"

        // Dependencies for this task
        concatDependencies(
            project.resolveDirectDependencies("implementation"),
            project.resolveDirectDependencies("commonMainImplementation"),
            project.resolveDirectDependencies("jvmMainImplementation"),
            project.resolveDirectDependencies("jsMainImplementation"),
            project.resolveDirectDependencies("androidMainImplementation"),
            project.resolveDirectDependencies("iosMainImplementation"),
            project.resolveDirectDependencies("linuxMainImplementation"),
            project.resolveDirectDependencies("macosMainImplementation"),
            project.resolveDirectDependencies("mingwMainImplementation"),
            project.resolveDirectDependencies("nativeMainImplementation"),
        ).filter { it.isProjectDependency() }.forEach {
            dependsOn("${it.asProjectDependency().subproject.path}:resolveDependencies")
        }
    }

    @TaskAction
    open fun resolveTree() {
        val dependencies = concatDependencies(
            project.resolveDependencies("implementation"),
            project.resolveDependencies("commonMainImplementation"),
            project.resolveDependencies("jvmMainImplementation"),
            project.resolveDependencies("jsMainImplementation"),
            project.resolveDependencies("androidMainImplementation"),
            project.resolveDependencies("iosMainImplementation"),
            project.resolveDependencies("linuxMainImplementation"),
            project.resolveDependencies("macosMainImplementation"),
            project.resolveDependencies("mingwMainImplementation"),
            project.resolveDependencies("nativeMainImplementation"),
        )
        project.extensions.extraProperties.set("dependencies", dependencies)
    }

    private fun concatDependencies(
        vararg dependencies: List<Dependency>,
    ): List<Dependency> {
        val result = mutableListOf<Dependency>()
        dependencies.forEach { list ->
            list.forEach {
                if (!result.any { dep -> dep == it }) {
                    result.add(it)
                }
            }
        }
        return result
    }

    private fun Project.resolveDirectDependencies(sourceSet: String): List<Dependency> {
        if (!hasDependencyConfiguration(sourceSet))
            return emptyList()
        val dependencies = project.configurations.getByName(sourceSet).dependencies
        val deps = mutableListOf<Dependency>()
        dependencies.forEach { dep ->
            // Check if it is a project dependency
            if (dep is org.gradle.api.artifacts.ProjectDependency) {
                dependsOn("${dep.dependencyProject.path}:resolveDependencies")
                deps.add(
                    ProjectDependency(
                        dep.dependencyProject.path,
                        dep.dependencyProject.name,
                        dep.dependencyProject.version.toString(),
                        emptyList(),
                        dep.dependencyProject
                    )
                )
            } else
                deps.add(
                    ExternalDependency(
                        dep.group ?: "unspecified",
                        dep.name,
                        dep.version ?: "unspecified",
                        emptyList()
                    )
                )

        }

        return deps
    }

    private fun Project.resolveDependencies(sourceSet: String): List<Dependency> {

        println(project.configurations.names)

        if (!hasDependencyConfiguration(sourceSet))
            return emptyList()

        val dependencies = project.configurations.getByName(sourceSet).dependencies
        val deps = mutableListOf<Dependency>()
        dependencies.forEach { dep ->
            // Check if it is a project dependency

            if (dep is org.gradle.api.artifacts.ProjectDependency) {

                println(dep.dependencyProject.extensions.extraProperties.get("dependencies"))

                deps.add(
                    ProjectDependency(
                        dep.dependencyProject.path,
                        dep.dependencyProject.name,
                        dep.dependencyProject.version.toString(),
                        dep.dependencyProject.extensions.extraProperties.get("dependencies") as List<Dependency>,
                        dep.dependencyProject
                    )
                )
            }
            else
                deps.add(
                    ExternalDependency(
                        dep.group ?: "unspecified",
                        dep.name,
                        dep.version ?: "unspecified",
                        emptyList()
                    )
                )

        }

        return deps
    }
}

open class PrintDependencyTreeTask : DefaultTask() {
    init {
        group = "changelog"
        description = "Print the dependency tree of the project"
        this.dependsOn("resolveDependencies")
    }

    @TaskAction
    open fun printDependencyTree() {
        val dependencies = project.extensions.extraProperties.get("dependencies") as List<*>
        println("Dependencies for ${project.path}:")
        dependencies.forEach { (it as Dependency).printTree() }
    }
}

fun Dependency.printTree(indent: Int = 0) {
    val indentString = " ".repeat(indent)
    println("$indentString- $this")
    dependencies.forEach { it.printTree(indent + 2) }
}
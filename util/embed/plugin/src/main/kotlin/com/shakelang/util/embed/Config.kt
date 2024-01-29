package com.shakelang.util.embed

import org.gradle.api.Project
import org.gradle.api.provider.Property

/**
 * Map containing configurations for each project (for multi-project builds)
 */
private val embedConfigurationMap = mutableMapOf<Project, EmbedConfigurationOuter>()

/**
 * Get the [EmbedConfigurationOuter] for the given [project]
 *
 * @param project The project to get the configuration for
 * @return The [EmbedConfigurationOuter] for the given [project]
 */
fun embedConfigurationFor(project: Project): EmbedConfigurationOuter {
    val config = embedConfigurationMap[project]
    if (config != null) return config
    val newConfig = EmbedConfigurationOuter(project)
    embedConfigurationMap[project] = newConfig
    return newConfig
}

class EmbedConfiguration(val project: Project, val outer: EmbedConfigurationOuter) {
    val source = mutableListOf<String>()
    val baseDir: Property<String> = project.objects.property(String::class.java)
    val distPackage: Property<String> = project.objects.property(String::class.java)
    val distName: Property<String> = project.objects.property(String::class.java)
    fun embed(vararg sources: String) = source.addAll(sources)
}

class EmbedConfigurationOuter(val project: Project) {

    val configurations = mutableListOf<EmbedConfiguration>()
    fun embedConfiguration(fn: EmbedConfiguration.() -> Unit) {
        val configuration = EmbedConfiguration(project, this)
        fn(configuration)
    }
}

fun Project.embedConfiguration(fn: EmbedConfiguration.() -> Unit) = embedConfigurationFor(this).embedConfiguration(fn)

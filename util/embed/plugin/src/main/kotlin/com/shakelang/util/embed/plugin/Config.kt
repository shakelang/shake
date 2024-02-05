package com.shakelang.util.embed.plugin

import org.gradle.api.Project
import org.gradle.api.provider.Property
import java.io.File

/**
 * Map containing configurations for each project (for multi-project builds)
 */
private val embedExtensionMap = mutableMapOf<Project, EmbedExtension>()

/**
 * Get the [EmbedExtension] for the given [project]
 *
 * @param project The project to get the configuration for
 * @return The [EmbedExtension] for the given [project]
 */
fun getEmbedExtension(project: Project): EmbedExtension {
    val config = embedExtensionMap[project]
    if (config != null) return config
    val newConfig = EmbedExtension(project)
    embedExtensionMap[project] = newConfig
    return newConfig
}

class EmbedConfiguration(val project: Project, val outer: EmbedExtension) {
    val source = mutableListOf<String>()
    val sourceSetLocation: Property<String> = project.objects.property(String::class.java)
    val sourceSet: Property<String> = project.objects.property(String::class.java)
    val baseDir: Property<String> = project.objects.property(String::class.java)
    val distPackage: Property<String> = project.objects.property(String::class.java)
    val distName: Property<String> = project.objects.property(String::class.java)
    var dist: File? = null
        private set

    fun embed(vararg sources: String) = source.addAll(sources)

    init {
        sourceSetLocation.convention("src")
        sourceSet.convention("main")
        baseDir.convention("resources")
    }

    companion object {
        fun initDist(config: EmbedConfiguration, dist: File) {
            config.dist = dist
        }
    }
}

class EmbedExtension(val project: Project) {

    val configurations = mutableListOf<EmbedConfiguration>()
    fun configuration(fn: EmbedConfiguration.() -> Unit) {
        val configuration = EmbedConfiguration(project, this)
        fn(configuration)
        configurations.add(configuration)
    }
}

fun Project.configuration(fn: EmbedConfiguration.() -> Unit) = getEmbedExtension(this).configuration(fn)

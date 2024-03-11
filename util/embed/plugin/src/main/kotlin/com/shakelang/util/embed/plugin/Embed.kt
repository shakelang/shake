package com.shakelang.util.embed.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@Suppress("unused")
open class Embed : Plugin<Project> {
    override fun apply(project: Project) {
        val extension = getEmbedExtension(project)
        project.extensions.add("embed", extension)

        project.afterEvaluate {
            val config = getEmbedExtension(project)

            // Check which kotlin plugin is applied
            if (
                project.plugins.hasPlugin("org.jetbrains.kotlin.multiplatform")
            ) {
                val kotlinMultiplatform = project.pluginManager.findPlugin("org.jetbrains.kotlin.multiplatform")

                // Get sourceSets from kotlin multiplatform plugin
                val kotlin = project.extensions.findByName("kotlin") as KotlinMultiplatformExtension

                val sourceSets = kotlin.sourceSets

                // map configurations to sourceSets
                val sourceSetMap = sourceSets.associateWith { sourceSet ->
                    config.configurations.filter { configuration ->
                        configuration.sourceSet.get() == sourceSet.name
                    }
                }

                config.configurations.forEach {
                    if (sourceSets.findByName(it.sourceSet.get()) == null) {
                        throw IllegalArgumentException("SourceSet ${it.sourceSet.get()} not found")
                    }
                }

                sourceSetMap.forEach { sourceSetEntry ->
                    // We need to create a sourceSet for each configuration
                    val sourceSet = sourceSetEntry.key
                    val configurations = sourceSetEntry.value

                    configurations.forEachIndexed { index, embedConfiguration ->

                        val generatedSourceSetBaseName = "embed${sourceSet.name.capitalize()}"
                        val generatedSourceSetName = if (configurations.size == 1) {
                            generatedSourceSetBaseName
                        } else {
                            "$generatedSourceSetBaseName$index"
                        }

                        val outFolder = project.buildDir.resolve("generated/embed/$generatedSourceSetName")

                        // Create a task for generating the files
                        val taskName = "generate${generatedSourceSetName.capitalize()}Files"
                        val task = project.tasks.register(taskName, FileBuilder::class.java, sourceSet.name)

                        EmbedConfiguration.initDist(embedConfiguration, outFolder)

                        project.repositories.mavenCentral()

                        sourceSet.dependencies {
                            if (project.rootProject.subprojects.any { prj ->
                                    prj.path == ":util:embed:api"
                                }
                            ) {
                                implementation(project(":util:embed:api"))
                            } else {
                                implementation("com.shakelang.util.embed:api:0.1.0")
                            }
                        }
                        sourceSet.kotlin.srcDir(task.get().outputs)

                        // Execute the configureTask before the compileKotlin task
                    }
                }
            }

            if (
                project.plugins.hasPlugin("org.jetbrains.kotlin.jvm")
            ) {
                val kotlinJvm = project.pluginManager.findPlugin("org.jetbrains.kotlin.jvm")
                println("Kotlin JVM plugin found: $kotlinJvm")
                TODO("Embed does not support the JVM plugin yet")
            }

            if (
                project.plugins.hasPlugin("org.jetbrains.kotlin.js")
            ) {
                val kotlinJs = project.pluginManager.findPlugin("org.jetbrains.kotlin.js")
                println("Kotlin JS plugin found: $kotlinJs")
                TODO("Embed does not support the JS plugin yet")
            }

            if (
                project.plugins.hasPlugin("org.jetbrains.kotlin.native")
            ) {
                val kotlinNative = project.pluginManager.findPlugin("org.jetbrains.kotlin.native")
                println("Kotlin Native plugin found: $kotlinNative")
                TODO("Embed does not support the Native plugin yet")
            }

            if (
                project.plugins.hasPlugin("org.jetbrains.kotlin.android")
            ) {
                val kotlinAndroid = project.pluginManager.findPlugin("org.jetbrains.kotlin.android")
                println("Kotlin Android plugin found: $kotlinAndroid")
                TODO("Embed does not support the Android plugin yet")
            }
        }
    }
}

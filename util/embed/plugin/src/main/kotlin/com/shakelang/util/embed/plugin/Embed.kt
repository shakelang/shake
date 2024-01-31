package com.shakelang.util.embed.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@Suppress("unused")
open class Embed : Plugin<Project> {
    override fun apply(project: Project) {
        val fileBuilder = project.tasks.register("embedGenerateSources", FileBuilder::class.java)

        project.afterEvaluate {
            val config = embedConfigurationFor(project)

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

                sourceSetMap.forEach {
                    // We need to create a sourceSet for each configuration
                    val sourceSet = it.key
                    val configurations = it.value
                    configurations.forEachIndexed { index, embedConfiguration ->

                        val generatedSourceSetBaseName = "embed${sourceSet.name.capitalize()}"
                        val generatedSourceSetName = if (configurations.size == 1) {
                            generatedSourceSetBaseName
                        } else {
                            "$generatedSourceSetBaseName$index"
                        }

                        val outFolder = project.buildDir.resolve("generated/embed/$generatedSourceSetName")

                        EmbedConfiguration.initDist(embedConfiguration, outFolder)

                        val generatedSourceSet = sourceSets.create(generatedSourceSetName)
                        generatedSourceSet.kotlin.srcDir(outFolder)
                        project.repositories.mavenCentral()
                        generatedSourceSet.dependencies {
                            if (project.rootProject.subprojects.any { prj ->
                                    prj.path == ":util:embed:api"
                                }
                            ) {
                                implementation(project(":util:embed:api"))
                            } else {
                                implementation("com.shakelang.util.embed:api:0.1.0")
                            }
                        }

                        sourceSet.dependsOn(generatedSourceSet)
                    }
                }
            }

            if (
                project.plugins.hasPlugin("org.jetbrains.kotlin.jvm")
            ) {
                val kotlinJvm = project.pluginManager.findPlugin("org.jetbrains.kotlin.jvm")
                println("Kotlin JVM plugin found: $kotlinJvm")
            }

            if (
                project.plugins.hasPlugin("org.jetbrains.kotlin.js")
            ) {
                val kotlinJs = project.pluginManager.findPlugin("org.jetbrains.kotlin.js")
                println("Kotlin JS plugin found: $kotlinJs")
            }

            if (
                project.plugins.hasPlugin("org.jetbrains.kotlin.native")
            ) {
                val kotlinNative = project.pluginManager.findPlugin("org.jetbrains.kotlin.native")
                println("Kotlin Native plugin found: $kotlinNative")
            }

            if (
                project.plugins.hasPlugin("org.jetbrains.kotlin.android")
            ) {
                val kotlinAndroid = project.pluginManager.findPlugin("org.jetbrains.kotlin.android")
                println("Kotlin Android plugin found: $kotlinAndroid")
            }
        }
    }
}

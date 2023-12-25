package com.shakelang.util.sarifmerge.tasks

import com.shakelang.util.sarifmerge.SarifLoader
import com.shakelang.util.shason.json
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class SarifMergeTask : DefaultTask() {

    init {
        group = "verification"
        description = "Merges sarif reports"

        project.allprojects.forEach { it ->
            this@SarifMergeTask.dependsOn("${it.path}:collectSarifReports")
        }
    }

    @TaskAction
    fun mergeSarifReports() {
        println("Merging sarif reports...")
        val pattern = "reports/sarifmerge-input/**/*.sarif"

        // find all files matching the pattern
        val files =
            project.fileTree(mapOf("dir" to "build/reports/sarifmerge-input", "include" to listOf("**/*.sarif"))).files

        val loader = SarifLoader()
        loader.addFiles(files)

        val merged = loader.generateFiles(20, 25000)

        project.file("build/reports/sarifmerge").mkdirs()

        merged.forEachIndexed { index, file ->
            project.file("build/reports/sarifmerge/$index.sarif").writeText(json.stringify(file, indent = 2))
            project.logger.info("Generated build/reports/sarifmerge/$index.sarif")
        }
    }
}

package com.shakelang.util.sarifmerge

import com.shakelang.util.sarifmerge.tasks.CopySarifReportsTask
import com.shakelang.util.sarifmerge.tasks.SarifMergeTask
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
open class SarifMerge : Plugin<Project> {
    override fun apply(project: Project) {
        project.allprojects.forEach {
            it.tasks.register("collectSarifReports", CopySarifReportsTask::class.java)
        }
        project.tasks.register("sarifmerge", SarifMergeTask::class.java)
    }
}

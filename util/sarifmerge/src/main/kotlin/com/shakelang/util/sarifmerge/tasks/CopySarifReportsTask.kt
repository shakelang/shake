package com.shakelang.util.sarifmerge.tasks

import org.gradle.api.tasks.Copy

open class CopySarifReportsTask : Copy() {
    init {
        group = "verification"
        description = "Copies ktlint reports to build directory"
        this.from("${project.buildDir}/reports/") {
            include("**/*.sarif")
            exclude {
                it.path.contains("sarifmerge")
            }
        }
        this.into("${project.rootProject.buildDir}/reports/sarifmerge-input/")
    }
}

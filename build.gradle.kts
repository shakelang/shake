import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

group = "io.github.shakelang.shake"
version = "0.1.0"
description = "Shake"

dependencies {
    kover(project(":util:colorlib"))
    kover(project(":util:common-io"))
    kover(project(":util:environment"))
    kover(project(":util:jvmlib"))
    kover(project(":util:logger"))
    kover(project(":util:parseutils"))
    kover(project(":util:primitives"))
    kover(project(":util:shason"))
    kover(project(":util:testlib"))

    kover(project(":shake:compiler:lexer"))
    kover(project(":shake:compiler:parser"))
    kover(project(":shake:compiler:shakelib"))

    kover(project(":shake:compiler:jsgenerator"))
    kover(project(":shake:compiler:processor"))
    kover(project(":shake:shasambly:shasambly"))
    kover(project(":shake:shasambly:shasp"))
    kover(project(":shake:shasambly:shastools"))
//    kover(project(":shake:shasambly:java-dist"))
}

plugins {
    id("org.jetbrains.dokka")
    kotlin("multiplatform") apply false
    id("org.jetbrains.kotlinx.kover")
    id("org.jlleitschuh.gradle.ktlint") version "11.6.1"
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint") // Version should be inherited from parent
    repositories {
        mavenLocal()
        mavenCentral()
    }

    ktlint {
        ignoreFailures.set(true)
        reporters {
            reporter(ReporterType.PLAIN)
            reporter(ReporterType.HTML)
            reporter(ReporterType.SARIF)
        }
    }

    tasks.create<Copy>("copyKtlintReports") {
        group = "verification"
        description = "Copies ktlint reports to build directory"
        dependsOn("ktlintCheck")
        from("$buildDir/reports/ktlint/ktlintKotlinScriptCheck/ktlintKotlinScriptCheck.sarif")
        rename { "${path.replace(":", "-").substring(1)}.sarif" }
        destinationDir = File("${rootProject.buildDir}/reports/ktlint/ktlintKotlinScriptCheck/")
    }
}

ktlint {
    ignoreFailures.set(true)
    reporters {
        reporter(ReporterType.PLAIN)
        reporter(ReporterType.HTML)
        reporter(ReporterType.SARIF)
    }
}

tasks.create("lint") {
    group = "verification"
    description = "Runs ktlintCheck on all subprojects and copies the reports to build directory"
    dependsOn("ktlintCheck")
    subprojects.forEach {
        dependsOn("${it.path}:ktlintCheck")
    }

    doLast {
        println("Ktlint reports copied to ${rootProject.buildDir}/reports/ktlint/ktlintKotlinScriptCheck/")
    }
}

repositories {
    mavenLocal()
    mavenCentral()
}

fun findDokkaHtmlProjects(): List<Project> =
    subprojects.filter {
        !it.tasks.none { task -> "dokkaHtml" == task.name }
    }

fun findDokkaGfmProjects(): List<Project> =
    subprojects.filter {
        !it.tasks.none { task -> "dokkaGfm" == task.name }
    }

tasks.dokkaHtml.configure {

    outputDirectory.set(buildDir.resolve("docs/html"))

    dependsOn("copyDokkaHtml")

    doFirst {
        val dokkaProjects = findDokkaHtmlProjects()

        dokkaProjects.forEach {
            println("Copying html documentation of module ${it.name}...")
            copy {
                from(fileTree("${it.name}/build/docs/html"))
                into(layout.buildDirectory.dir("docs/html/modules/${it.name}"))
            }
        }

        dokkaSourceSets {
            dokkaProjects.forEach {
                dokkaSourceSets.create(it.name) {
                    sourceRoots.setFrom("${it.name}/src/commonMain")
                }
            }
        }
    }
}

tasks.dokkaGfm.configure {

    outputDirectory.set(buildDir.resolve("docs/markdown"))

    dependsOn("copyDokkaGfm")

    doFirst {
        val dokkaProjects = findDokkaGfmProjects()

        dokkaProjects.forEach {
            println("Copying markdown documentation of module ${it.name}...")
            copy {
                from(fileTree("${it.name}/build/docs/html"))
                into(layout.buildDirectory.dir("docs/html/modules/${it.name}"))
            }
        }

        dokkaSourceSets {
            dokkaProjects.forEach {
                dokkaSourceSets.create(it.name) {
                    sourceRoots.setFrom("${it.name}/src/commonMain")
                }
            }
        }
    }
}

tasks.register("dokka") {
    group = "documentation"
    dependsOn("dokkaHtml", "dokkaGfm")
}

tasks.register<TestReport>("genReport") {
    group = "verification"
    val testTasks = allprojects.flatMap { it.tasks.withType(Test::class) }
    dependsOn(testTasks)
    destinationDirectory.set(file("${layout.buildDirectory}/reports/tests"))
    reportOn(testTasks)
}

tasks.register("copyDokkaGfm") {
    group = "documentation"
    findDokkaGfmProjects().forEach {
        dependsOn("${it.path}:dokkaGfm")
    }
}

tasks.register("copyDokkaHtml") {
    group = "documentation"
    findDokkaHtmlProjects().forEach {
        dependsOn("${it.path}:dokkaHtml")
    }
}

val testAggregate = tasks.register<TestReport>("testAggregate") {
    group = "verification"
//
//    subprojects.forEach {
//        if (it.tasks.none { task -> "allTests" == task.name }) return@forEach
//        dependsOn("${it.path}:allTests")
//    }
    destinationDirectory.set(file("$buildDir/reports/tests/aggregate"))
    reportOn(subprojects.flatMap { it.tasks.withType<Test>() })
}

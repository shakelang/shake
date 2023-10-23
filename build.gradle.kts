group = "io.github.shakelang.shake"
version = "0.1.0"
description = "Shake"

dependencies {

    kover(project(":util:testlib"))
    kover(project(":util:colorlib"))
    kover(project(":util:parseutils"))
    kover(project(":util:shason"))
    kover(project(":util:jvmlib"))
    kover(project(":shake:compiler:lexer"))
    kover(project(":shake:compiler:parser"))
    kover(project(":shake:compiler:processor"))
    kover(project(":shake:shasambly:shastools"))
    kover(project(":shake:shasambly:shasambly"))
    kover(project(":shake:shasambly:shasp"))

}

plugins {
    id("org.jetbrains.dokka")
    kotlin("multiplatform") apply false
    id("org.jetbrains.kotlinx.kover")
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
    destinationDir = file("${layout.buildDirectory}/reports/tests")
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
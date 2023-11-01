import conventions.dependencies

group = projectName("compiler.interpreter")
version = "0.1.0"
description = "interpreter"

plugins {
    id("conventions.all")
}

kotlin {
    dependencies {
        common {
            implementation(project(":util:parseutils"))
            implementation(project(":util:shason"))
            implementation(project(":shake:compiler:lexer"))
            implementation(project(":shake:compiler:parser"))
            implementation(project(":shake:compiler:processor"))
            implementation(project(":shake:compiler:jsgenerator"))
            implementation(project(":shake:compiler:interpreter"))
            testImplementation(kotlin("test"))
        }
        jvm {
            implementation("org.reflections:reflections:0.9.12")
        }
    }
}

tasks.test {
    useJUnitPlatform()

    testLogging.showExceptions = true
    maxHeapSize = "1G"
    // ignoreFailures = true
    filter {
        includeTestsMatching("io.github.shakelang.shake.*")
    }
}

val projectName = name
tasks.named<Jar>("jvmJar") {
    archiveBaseName.set("shake-$projectName")
}
tasks.named<Jar>("jsJar") {
    archiveBaseName.set("shake-$projectName")
}
tasks.named<Jar>("metadataJar") {
    archiveBaseName.set("shake-$projectName")
}
import com.github.gradle.node.task.NodeTask
import com.shakelang.util.changelog.public
import com.shakelang.util.changelog.resolveVersion
import com.shakelang.util.embed.plugin.Embed
import com.shakelang.util.embed.plugin.getEmbedExtension
import conventions.dependencies
import conventions.projectGroup

plugins {
    id("conventions.all")
    id("conventions.publishing")
    id("io.kotest.multiplatform")
    id("com.github.node-gradle.node")
}

public = true
group = projectGroup("shake.compiler")
version = resolveVersion()
description = "Utilities for parsing stuff with kotlin"

repositories {
    mavenCentral()
}

kotlin {
    dependencies {
        implementation(project(":util:parseutils"))
        implementation(project(":util:shason"))
        implementation(project(":util:logger"))
        implementation(project(":shake:compiler:lexer"))
        testImplementation(kotlin("stdlib"))
        testImplementation(project(":util:common-io"))
        kotest()
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

apply<Embed>()

getEmbedExtension(project).configuration {
    sourceSet.set("commonTest")
    distPackage.set("com.shakelang.shake.parser.test")
    distName.set("ShakeParserTestResources")
    embed("**/*")
}

// tasks.withType(FileBuilder::class).configureEach {
//    dependsOn("generateTests")
// }

val generateTests by tasks.registering(NodeTask::class) {
    group = "build"
    description = "Generates the test files for the parser"
    dependsOn("npmInstall")
    script.set(file("test-generator/index.js"))
    inputs.dir(file("test-generator"))
    outputs.dir(file("src/commonTest/resources/generated-tests"))
    args.set(listOf())
}

val cleanGeneratedTests by tasks.registering(Delete::class) {
    group = "build"
    description = "Cleans the generated test files"
    delete(file("src/commonTest/resources/generated-tests"))
}

tasks.getByName("clean").dependsOn(cleanGeneratedTests)

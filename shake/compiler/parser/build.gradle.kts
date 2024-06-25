import com.github.gradle.node.task.NodeTask
import com.shakelang.util.changelog.public
import com.shakelang.util.changelog.resolveVersion
import com.shakelang.util.embed.plugin.Embed
import com.shakelang.util.embed.plugin.getEmbedExtension
import conventions.dependencies
import conventions.projectGroup

plugins {
    id("conventions.all")
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
        implementation(project(":util:common-io"))
        implementation(project(":util:parseutils"))
        implementation(project(":util:shason"))
        implementation(project(":util:logger"))
        implementation(project(":shake:compiler:lexer"))
        testImplementation(kotlin("stdlib"))
        testImplementation(project(":util:testlib"))
        kotest()
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

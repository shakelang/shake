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
    distName.set("ShakeParserTestInput")
    embed("**/*.shake")
}

getEmbedExtension(project).configuration {
    sourceSet.set("commonTest")
    distPackage.set("com.shakelang.shake.parser.test")
    distName.set("ShakeParserTestOutput")
    embed("**/*.json")
}

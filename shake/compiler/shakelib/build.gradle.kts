import com.shakelang.util.changelog.resolveVersion
import com.shakelang.util.embed.plugin.Embed
import com.shakelang.util.embed.plugin.getEmbedExtension
import conventions.projectGroup

plugins {
    id("conventions.all")
//    id("com.shakelang.util.embed.plugin") version "0.1.0"
}

repositories {
    mavenLocal()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

group = projectGroup("shake.compiler")
version = resolveVersion()
description = "Utilities for parsing stuff with kotlin"

val projectName = name

apply<Embed>()

getEmbedExtension(project).configuration {
    sourceSet.set("commonMain")
    distPackage.set("com.shakelang.shake.shakelib")
    distName.set("ShakeLib")
    embed("**/*.shake")
}

val generateLibrary by tasks.registering {
    group = "build"
    description = "Generates the shake library"

    dependsOn(":shake:compiler:shakelib:generator:run")
}

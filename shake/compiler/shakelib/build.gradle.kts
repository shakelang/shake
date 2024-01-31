import com.shakelang.util.changelog.resolveVersion
import com.shakelang.util.embed.plugin.Embed
import conventions.projectGroup

plugins {
    id("conventions.all")
    id("conventions.publishing")
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
//
// embedConfiguration {
//    sourceSet.set("commonMain")
//    distPackage.set("com.shakelang.shake.shakelib")
//    distName.set("ShakeLib")
//    embed("**/*.shake")
// }

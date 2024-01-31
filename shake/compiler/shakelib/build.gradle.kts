import com.shakelang.util.changelog.resolveVersion
import com.shakelang.util.embed.plugin.Embed
import com.shakelang.util.embed.plugin.embedConfiguration
import conventions.projectGroup
import dev.icerock.gradle.MRVisibility

plugins {
    id("conventions.all")
    id("conventions.publishing")
    id("dev.icerock.mobile.multiplatform-resources")
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

multiplatformResources {
    multiplatformResourcesPackage = "com.shakelang.shake.shakelib"
    multiplatformResourcesClassName = "ShakeLib"
    iosBaseLocalizationRegion = "en"
    multiplatformResourcesSourceSet = "commonMain"
    multiplatformResourcesVisibility = MRVisibility.Public
    disableStaticFrameworkWarning = true
}

embedConfiguration {
    sourceSet.set("commonMain")
    distPackage.set("com.shakelang.shake.shakelib")
    distName.set("ShakeLib")
    embed("**/*.shake")
}

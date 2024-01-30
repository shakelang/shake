// import com.shakelang.util.changelog.resolveVersion
// import com.shakelang.util.embed.plugin.embedConfiguration
import conventions.dependencies
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
// version = resolveVersion()
description = "Utilities for parsing stuff with kotlin"

val projectName = name

multiplatformResources {
    multiplatformResourcesPackage = "com.shakelang.shake.shakelib"
    multiplatformResourcesClassName = "ShakeLib"
    iosBaseLocalizationRegion = "en"
    multiplatformResourcesSourceSet = "commonMain"
    multiplatformResourcesVisibility = MRVisibility.Public
    disableStaticFrameworkWarning = true
}
//
// embedConfiguration {
//    distPackage.set("com.shakelang.shake.shakelib")
//    distName.set("ShakeLib")
//    baseDir.set(projectDir.path + "/src/commonMain/resources")
//    embed("**/*.shake")
// }

kotlin {
    dependencies {
        implementation("dev.icerock.moko:resources:0.23.0")
        implementation("dev.icerock.moko:resources-compose:0.23.0") // for compose multiplatform
        testImplementation("dev.icerock.moko:resources-test:0.23.0")
    }
}
listOf(
    "compileKotlinJs",
    "compileKotlinJvm",
    "jsProcessResources",
    "jvmProcessResources",
).forEach {
    tasks.named(it) {
        dependsOn("generateMRcommonMain")
    }
}

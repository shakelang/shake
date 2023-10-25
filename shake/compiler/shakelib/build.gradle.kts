import dev.icerock.gradle.MRVisibility
import io.github.shakelang.shake.conventions.mpp.dependencies

plugins {
    id("io.github.shakelang.shake.conventions.mpp.all")
    id("io.github.shakelang.shake.conventions.mpp.publishing")
    id("dev.icerock.mobile.multiplatform-resources") version "0.23.0"
}

group = "io.github.shakelang.shake.shakelib"
version = "0.1.0"
description = "Utilities for parsing stuff with kotlin"

val projectName = name

multiplatformResources {
    multiplatformResourcesPackage = "io.github.shakelang.shake.shakelib"
    multiplatformResourcesClassName = "ShakeLib"
    iosBaseLocalizationRegion = "en"
    multiplatformResourcesSourceSet = "commonMain"
    multiplatformResourcesVisibility = MRVisibility.Public
    disableStaticFrameworkWarning = true
}

kotlin {
    dependencies {
        implementation("dev.icerock.moko:resources:0.23.0")
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
import com.shakelang.shake.util.changelog.resolveVersion
import conventions.KOTEST_VERSION
import conventions.dependencies
import conventions.projectGroup

plugins {
    id("conventions.all")
    id("conventions.publishing")
    id("io.kotest.multiplatform")
}

group = projectGroup("compiler.parser")
version = resolveVersion()
description = "Utilities for parsing stuff with kotlin"

repositories {
    mavenCentral()
}

kotlin {
    dependencies {
        implementation(project(":util:parseutils"))
        implementation(project(":util:shason"))
        implementation(project(":shake:compiler:lexer"))
        testImplementation(kotlin("stdlib"))
        testImplementation("io.kotest:kotest-framework-engine:$KOTEST_VERSION")
        testImplementation("io.kotest:kotest-assertions-core:$KOTEST_VERSION")
        testImplementation("io.kotest:kotest-property:$KOTEST_VERSION")

        jvmTest {
            implementation("io.kotest:kotest-runner-junit5-jvm:5.8.0")
        }
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
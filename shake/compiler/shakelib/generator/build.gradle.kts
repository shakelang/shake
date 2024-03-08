import com.shakelang.util.changelog.private

plugins {
    kotlin("jvm")
    application
}

private = true

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":shake:shakespeare"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("com.shakelang.shake.shakelib.generator.GeneratorKt")
}

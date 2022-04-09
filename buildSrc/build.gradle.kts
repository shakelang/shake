plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenLocal()
    mavenCentral()
}

dependencies {
    compile("org.jetbrains.dokka:dokka-gradle-plugin:1.4.32")
    compile("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")
}
import conventions.dependencies

// group = projectName("bin")
version = "0.1.0"
description = "interpreter"

plugins {
    id("conventions.all")
}

kotlin {
    dependencies {
        common {
            implementation(project(":util:commander"))
            implementation(project(":util:common-io"))
            implementation(project(":util:embed:api"))
            implementation(project(":util:logger"))
            implementation(project(":util:parseutils"))
            implementation(project(":util:shason"))

            implementation(project(":shake:compiler:lexer"))
            implementation(project(":shake:compiler:parser"))
            implementation(project(":shake:compiler:processor"))
            implementation(project(":shake:compiler:jsgenerator"))
            implementation(project(":shake:compiler:shakelib"))
            implementation(project(":shake:compiler:processor"))

            implementation(project(":shake:bytecode:conventions"))
            implementation(project(":shake:bytecode:interpreter"))
            implementation(project(":shake:bytecode:generator"))

            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1-Beta")
            testImplementation(kotlin("test"))
        }
    }
}

/*
val projectName = name
tasks.named<Jar>("jvmJar") {
    archiveBaseName.set("shake-$projectName")
}
tasks.named<Jar>("jsJar") {
    archiveBaseName.set("shake-$projectName")
}
tasks.named<Jar>("metadataJar") {
    archiveBaseName.set("shake-$projectName")
}
*/

import com.shakelang.util.changelog.resolveVersion
import conventions.dependencies
import conventions.projectGroup

group = projectGroup("shake.compiler")
version = resolveVersion()
description = "js-generator"
java.sourceCompatibility = JavaVersion.VERSION_1_8

plugins {
    id("conventions.all")
}

kotlin {
    dependencies {
        implementation(project(":util:parseutils"))
        implementation(project(":util:shason"))
        implementation(project(":shake:compiler:lexer"))
        implementation(project(":shake:compiler:parser"))
        implementation(project(":shake:compiler:processor"))
        testImplementation(kotlin("test"))
    }
}

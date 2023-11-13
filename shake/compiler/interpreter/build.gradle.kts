import com.shakelang.shake.util.changelog.resolveVersion
import conventions.dependencies
import conventions.projectGroup

group = projectGroup("compiler.interpreter")
version = resolveVersion()
description = "interpreter"

repositories {
    mavenLocal()
    mavenCentral()
}

kotlin {
    dependencies {
        common {
            implementation(project(":util:parseutils"))
            implementation(project(":shake:compiler:lexer"))
            implementation(project(":shake:compiler:parser"))
            testImplementation(kotlin("test"))
        }
        jvm {
            implementation("org.reflections:reflections:0.9.12")
        }
    }
}

tasks.test {
    useJUnitPlatform()

    testLogging.showExceptions = true
    maxHeapSize = "1G"
    // ignoreFailures = true
    filter {
        includeTestsMatching("com.shakelang.shake.*")
    }
}

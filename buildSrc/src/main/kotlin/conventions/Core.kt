package conventions

import gradle.kotlin.dsl.accessors._11c752c08fee604d934630adf5f25d89.kotlin
import groovy.lang.Closure
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.getting
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import java.io.File

fun Project.useKotest() {
    apply(plugin = "io.kotest.multiplatform")
    kotlin.dependencies.kotest()
    tasks.withType(Test::class.java).configureEach {
        useJUnitPlatform()
    }
}

fun KotlinMultiplatformExtension.dependencies(configure: KotlinMultiplatformExtensionDependencies.() -> Unit) {
    configure(dependencies)
}

val KotlinMultiplatformExtension.dependencies: KotlinMultiplatformExtensionDependencies
    get() = KotlinMultiplatformExtensionDependencies(this)

class KotlinMultiplatformExtensionDependencies(extension: KotlinMultiplatformExtension) {
    val commonMain: KotlinSourceSet by extension.sourceSets.getting
    val commonTest: KotlinSourceSet by extension.sourceSets.getting
    val jvmMain: KotlinSourceSet by extension.sourceSets.getting {
        dependsOn(commonMain)
    }
    val jvmTest: KotlinSourceSet by extension.sourceSets.getting {
        dependsOn(commonTest)
    }
    val jsMain: KotlinSourceSet by extension.sourceSets.getting {
        dependsOn(commonMain)
    }
    val jsTest: KotlinSourceSet by extension.sourceSets.getting {
        dependsOn(commonTest)
    }
    // val nativeMain: KotlinSourceSet by extension.sourceSets.getting
    // val nativeTest: KotlinSourceSet by extension.sourceSets.getting

    val javascriptMain get() = jsMain
    val javascriptTest get() = jsTest

    val common = KotlinSourceType(commonMain, commonTest)
    val jvm = KotlinSourceType(jvmMain, jvmTest)
    val js = KotlinSourceType(jsMain, jsTest)

    fun kotest() {
        testImplementation("io.kotest:kotest-framework-engine:$KOTEST_VERSION")
        testImplementation("io.kotest:kotest-assertions-core:$KOTEST_VERSION")
        testImplementation("io.kotest:kotest-property:$KOTEST_VERSION")

        jvmTest {
            implementation("io.kotest:kotest-runner-junit5-jvm:5.8.0")
        }
    }
    // val native = KotlinSourceType(nativeMain, nativeTest)

    fun commonMain(configure: KotlinDependencyHandler.() -> Unit) {
        commonMain.dependencies {
            configure(this)
        }
    }

    fun commonTest(configure: KotlinDependencyHandler.() -> Unit) {
        commonTest.dependencies {
            configure(this)
        }
    }

    fun jvmMain(configure: KotlinDependencyHandler.() -> Unit) {
        jvmMain.dependencies {
            configure(this)
        }
    }

    fun jvmTest(configure: KotlinDependencyHandler.() -> Unit) {
        jvmTest.dependencies {
            configure(this)
        }
    }

    fun jsMain(configure: KotlinDependencyHandler.() -> Unit) {
        jsMain.dependencies {
            configure(this)
        }
    }

    fun jsTest(configure: KotlinDependencyHandler.() -> Unit) {
        jsTest.dependencies {
            configure(this)
        }
    }
    // fun nativeMain(configure: KotlinSourceSet.() -> Unit) = nativeMain.configure()
    // fun nativeTest(configure: KotlinSourceSet.() -> Unit) = nativeTest.configure()

    fun common(configure: KotlinSourceType.() -> Unit) = common.configure()
    fun jvm(configure: KotlinSourceType.() -> Unit) = jvm.configure()
    fun js(configure: KotlinSourceType.() -> Unit) = js.configure()

    fun main(configure: KotlinDependencyHandler.() -> Unit) =
        common.main(configure)

    fun test(configure: KotlinDependencyHandler.() -> Unit) =
        common.test(configure)

    fun implementation(dependencyNotation: Any): Dependency? =
        common.implementation(dependencyNotation)

    fun implementation(
        dependencyNotation: String,
        configure: ExternalModuleDependency.() -> Unit,
    ): ExternalModuleDependency = common.implementation(dependencyNotation, configure)

    fun <T : Dependency> implementation(dependency: T, configure: T.() -> Unit): T =
        common.implementation(dependency, configure)

    fun implementation(dependencyNotation: String, configure: Closure<*>): ExternalModuleDependency =
        common.implementation(dependencyNotation, configure)

    fun compileOnly(dependencyNotation: Any): Dependency? =
        common.compileOnly(dependencyNotation)

    fun compileOnly(
        dependencyNotation: String,
        configure: ExternalModuleDependency.() -> Unit,
    ): ExternalModuleDependency = common.compileOnly(dependencyNotation, configure)

    fun <T : Dependency> compileOnly(dependency: T, configure: T.() -> Unit): T =
        common.compileOnly(dependency, configure)

    fun testImplementation(dependencyNotation: Any): Dependency? = common.testImplementation(dependencyNotation)

    fun testImplementation(
        dependencyNotation: String,
        configure: ExternalModuleDependency.() -> Unit,
    ): ExternalModuleDependency = common.testImplementation(dependencyNotation, configure)

    fun <T : Dependency> testImplementation(dependency: T, configure: T.() -> Unit): T =
        common.testImplementation(dependency, configure)

    fun testImplementation(dependencyNotation: String, configure: Closure<*>): ExternalModuleDependency =
        common.testImplementation(dependencyNotation, configure)

    fun <T : Dependency> testImplementation(dependency: T, configure: Closure<*>): T =
        common.testImplementation(dependency, configure)

    fun kotlin(simpleModuleName: String): ExternalModuleDependency =
        common.kotlin(simpleModuleName)

    fun kotlin(simpleModuleName: String, version: String?): ExternalModuleDependency =
        common.kotlin(simpleModuleName, version)

    fun project(path: String, configuration: String? = null): ProjectDependency =
        common.project(path, configuration)

    fun project(notation: Map<String, Any?>): ProjectDependency =
        common.project(notation)

    @Deprecated(
        "Declaring NPM dependency without version is forbidden",
        ReplaceWith("npm(name, version)"),
    )
    @Suppress("deprecation")
    fun npm(name: String): Dependency = common.npm(name)

    fun npm(
        name: String,
        version: String,
    ): Dependency = common.npm(name, version)

    fun npm(
        name: String,
        directory: File,
        generateExternals: Boolean,
    ): Dependency = common.npm(name, directory, generateExternals)

    fun npm(
        name: String,
        directory: File,
    ): Dependency = common.npm(name, directory)

    fun npm(
        directory: File,
        generateExternals: Boolean,
    ): Dependency = common.npm(directory, generateExternals)

    fun npm(
        directory: File,
    ): Dependency = common.npm(directory)

    fun devNpm(
        name: String,
        version: String,
    ): Dependency = common.devNpm(name, version)

    fun devNpm(
        name: String,
        directory: File,
    ): Dependency = common.devNpm(name, directory)

    fun devNpm(
        directory: File,
    ): Dependency = common.devNpm(directory)

    fun optionalNpm(
        name: String,
        version: String,
        generateExternals: Boolean,
    ): Dependency = common.optionalNpm(name, version, generateExternals)

    fun optionalNpm(
        name: String,
        version: String,
    ): Dependency = common.optionalNpm(name, version)

    fun optionalNpm(
        name: String,
        directory: File,
        generateExternals: Boolean,
    ): Dependency = common.optionalNpm(name, directory, generateExternals)

    fun optionalNpm(
        name: String,
        directory: File,
    ): Dependency = common.optionalNpm(name, directory)

    fun optionalNpm(
        directory: File,
        generateExternals: Boolean,
    ): Dependency = common.optionalNpm(directory, generateExternals)

    fun optionalNpm(
        directory: File,
    ): Dependency = common.optionalNpm(directory)

    fun peerNpm(
        name: String,
        version: String,
    ): Dependency = common.peerNpm(name, version)
}

class KotlinSourceType(
    val main: KotlinSourceSet,
    val test: KotlinSourceSet,
) {
    operator fun invoke(configure: KotlinSourceType.() -> Unit) = this.configure()

    fun main(configure: KotlinDependencyHandler.() -> Unit) =
        main.dependencies { configure(this) }

    fun test(configure: KotlinDependencyHandler.() -> Unit) =
        test.dependencies { configure(this) }

    fun implementation(dependencyNotation: Any): Dependency? =
        ret { main.dependencies { it(implementation(dependencyNotation)) } }

    fun implementation(
        dependencyNotation: String,
        configure: ExternalModuleDependency.() -> Unit,
    ): ExternalModuleDependency = ret { main.dependencies { it(implementation(dependencyNotation, configure)) } }

    fun <T : Dependency> implementation(dependency: T, configure: T.() -> Unit): T =
        ret { main.dependencies { it(implementation(dependency, configure)) } }

    fun implementation(dependencyNotation: String, configure: Closure<*>): ExternalModuleDependency =
        ret { main.dependencies { it(implementation(dependencyNotation, configure)) } }

    fun compileOnly(dependencyNotation: Any): Dependency? =
        ret { main.dependencies { it(compileOnly(dependencyNotation)) } }

    fun compileOnly(
        dependencyNotation: String,
        configure: ExternalModuleDependency.() -> Unit,
    ): ExternalModuleDependency = ret { main.dependencies { it(compileOnly(dependencyNotation, configure)) } }

    fun <T : Dependency> compileOnly(dependency: T, configure: T.() -> Unit): T =
        ret { main.dependencies { it(compileOnly(dependency, configure)) } }

    fun testImplementation(dependencyNotation: Any): Dependency? =
        ret { test.dependencies { it(implementation(dependencyNotation)) } }

    fun testImplementation(
        dependencyNotation: String,
        configure: ExternalModuleDependency.() -> Unit,
    ): ExternalModuleDependency = ret { test.dependencies { it(implementation(dependencyNotation, configure)) } }

    fun <T : Dependency> testImplementation(dependency: T, configure: T.() -> Unit): T =
        ret { test.dependencies { it(implementation(dependency, configure)) } }

    fun testImplementation(dependencyNotation: String, configure: Closure<*>): ExternalModuleDependency =
        ret { test.dependencies { it(implementation(dependencyNotation, configure)) } }

    fun <T : Dependency> testImplementation(dependency: T, configure: Closure<*>): T =
        ret { test.dependencies { it(implementation(dependency, configure)) } }

    fun kotlin(simpleModuleName: String): ExternalModuleDependency =
        ret { main.dependencies { it(kotlin(simpleModuleName)) } }

    fun kotlin(simpleModuleName: String, version: String?): ExternalModuleDependency =
        ret { main.dependencies { it(kotlin(simpleModuleName, version)) } }

    fun project(path: String, configuration: String? = null): ProjectDependency =
        ret { main.dependencies { it(project(path, configuration)) } }

    fun project(notation: Map<String, Any?>): ProjectDependency = ret { main.dependencies { it(project(notation)) } }

    @Deprecated(
        "Declaring NPM dependency without version is forbidden",
        ReplaceWith("npm(name, version)"),
    )
    @Suppress("deprecation")
    fun npm(name: String): Dependency = ret { main.dependencies { it(npm(name)) } }

    fun npm(
        name: String,
        version: String,
    ): Dependency = ret { main.dependencies { it(npm(name, version)) } }

    fun npm(
        name: String,
        directory: File,
        generateExternals: Boolean,
    ): Dependency = ret { main.dependencies { it(npm(name, directory, generateExternals)) } }

    fun npm(
        name: String,
        directory: File,
    ): Dependency = ret { main.dependencies { it(npm(name, directory)) } }

    fun npm(
        directory: File,
        generateExternals: Boolean,
    ): Dependency = ret { main.dependencies { it(npm(directory, generateExternals)) } }

    fun npm(
        directory: File,
    ): Dependency = ret { main.dependencies { it(npm(directory)) } }

    fun devNpm(
        name: String,
        version: String,
    ): Dependency = ret { main.dependencies { it(devNpm(name, version)) } }

    fun devNpm(
        name: String,
        directory: File,
    ): Dependency = ret { main.dependencies { it(devNpm(name, directory)) } }

    fun devNpm(
        directory: File,
    ): Dependency = ret { main.dependencies { it(devNpm(directory)) } }

    fun optionalNpm(
        name: String,
        version: String,
        generateExternals: Boolean,
    ): Dependency = ret { main.dependencies { it(optionalNpm(name, version, generateExternals)) } }

    fun optionalNpm(
        name: String,
        version: String,
    ): Dependency = ret { main.dependencies { it(optionalNpm(name, version)) } }

    fun optionalNpm(
        name: String,
        directory: File,
        generateExternals: Boolean,
    ): Dependency = ret { main.dependencies { it(optionalNpm(name, directory, generateExternals)) } }

    fun optionalNpm(
        name: String,
        directory: File,
    ): Dependency = ret { main.dependencies { it(optionalNpm(name, directory)) } }

    fun optionalNpm(
        directory: File,
        generateExternals: Boolean,
    ): Dependency = ret { main.dependencies { it(optionalNpm(directory, generateExternals)) } }

    fun optionalNpm(
        directory: File,
    ): Dependency = ret { main.dependencies { it(optionalNpm(directory)) } }

    fun peerNpm(
        name: String,
        version: String,
    ): Dependency = ret { main.dependencies { it(peerNpm(name, version)) } }
}

fun <T> ret(task: ((T) -> T) -> Unit): T {
    var ret: T? = null
    task {
        ret = it
        it
    }
    return ret!!
}

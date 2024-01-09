package conventions

import org.gradle.api.Project

class PublishScopesConfig(val project: Project) {
    val sonartype = project.objects.property(Boolean::class.java)
    val githubPackages = project.objects.property(Boolean::class.java)
    val mavenLocal = project.objects.property(Boolean::class.java)
    val gradlePluginPortal = project.objects.property(Boolean::class.java)
    var configured = false
        private set

    init {
        sonartype.set(false)
        githubPackages.set(false)
        mavenLocal.set(false)
        gradlePluginPortal.set(false)
    }

    fun all() {
        sonartype.set(true)
        githubPackages.set(true)
        mavenLocal.set(true)
        gradlePluginPortal.set(true)
    }

    fun sonartype() {
        sonartype.set(true)
    }

    fun githubPackages() {
        githubPackages.set(true)
    }

    fun mavenLocal() {
        mavenLocal.set(true)
    }

    fun gradlePluginPortal() {
        gradlePluginPortal.set(true)
    }

    fun none() {
        sonartype.set(false)
        githubPackages.set(false)
        mavenLocal.set(false)
        gradlePluginPortal.set(false)
    }

    fun sonartype(block: () -> Unit) {
        sonartype.set(true)
        block()
        sonartype.set(false)
    }

    fun githubPackages(block: () -> Unit) {
        githubPackages.set(true)
        block()
        githubPackages.set(false)
    }

    fun mavenLocal(block: () -> Unit) {
        mavenLocal.set(true)
        block()
        mavenLocal.set(false)
    }

    fun gradlePluginPortal(block: () -> Unit) {
        gradlePluginPortal.set(true)
        block()
        gradlePluginPortal.set(false)
    }

    operator fun invoke(block: PublishScopesConfig.() -> Unit) {
        block()
    }
}

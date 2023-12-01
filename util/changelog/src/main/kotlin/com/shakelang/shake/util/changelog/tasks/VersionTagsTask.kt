package com.shakelang.shake.util.changelog.tasks

import com.shakelang.shake.util.changelog.Changelog
import com.shakelang.shake.util.changelog.readStash
import com.shakelang.shake.util.changelog.tagRef
import com.shakelang.shake.util.changelog.writeStash
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input

open class VersionTagsTask : DefaultTask() {

    @Input
    val push: Property<Boolean> = project.objects.property(Boolean::class.java)

    @Input
    val origin: Property<String> = project.objects.property(String::class.java)

    init {
        group = "changelog"
        description = "Creates git tags for stashed versions"
        this.dependsOn("initChangelog")

        push.convention(true)
        origin.convention("origin")
    }

    @org.gradle.api.tasks.TaskAction
    open fun versionTags() {
        val stash = Changelog.instance.readStash()

        val rt = Runtime.getRuntime()

        stash.tags.forEach {
            println("Creating tag ${it.name}...")

            val tagCreation = rt.exec(arrayOf("git", "tag", it.name, "-m", "Release ${it.name}"))

            val exitCode = tagCreation.waitFor()
            if (exitCode != 0) {
                println("Failed to create tag ${it.name}")
                while (tagCreation.errorStream.available() > 0)
                    System.err.print(tagCreation.errorStream.read().toChar())

                return@forEach
            }

            if (push.get()) {
                println("Pushing tag ${it.name}...")
                val pushTag = rt.exec(arrayOf("git", "push", origin.get(), tagRef(it.name)))
                val exitCode2 = pushTag.waitFor()

                if (exitCode2 != 0) {
                    println("Failed to push tag ${it.name}")
                    return@forEach
                }
            }
        }

        stash.clear()
        Changelog.instance.writeStash(stash)
    }
}

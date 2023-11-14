package com.shakelang.shake.util.changelog

import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.Input
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import kotlin.math.max

class Changelog : Plugin<Project> {

    init {
        try {
            instance = this
        } catch (e: Exception) {
            throw IllegalStateException("Changelog already initialized")
        }
    }

    lateinit var project: Project
        private set

    override fun apply(project: Project) {
        this.project = project
        project.tasks.create("initChangelog", InitChangelogTask::class.java)
        project.tasks.create("bump", BumpTask::class.java)
        project.tasks.create("version", VersionTask::class.java)
        project.tasks.create("createTags", VersionTags::class.java)
    }

    companion object {
        lateinit var instance: Changelog
            private set
    }
}

open class InitChangelogTask : DefaultTask() {

    init {
        group = "changelog"
        description = "Initializes the changelog plugin"
    }

    @org.gradle.api.tasks.TaskAction
    open fun initChangelog() {
        val changelog = project.file(".changelog")
        if (!changelog.exists()) {
            project.logger.info("Creating .changelog directory")
            changelog.mkdirs()
        }

        val structure = project.file(".changelog/structure.json")
        if (!structure.exists()) {
            project.logger.info("Creating .changelog/structure.json file")
            Changelog.instance.newStructure()
        }

        project.logger.info("Updating .changelog/structure.json file")
        Changelog.instance.updateStructure()
    }
}

open class BumpTask : DefaultTask() {

    init {
        group = "changelog"
        description = "Initializes a new version"
        this.dependsOn("initChangelog")
    }

    @org.gradle.api.tasks.TaskAction
    open fun bump() {
        val messageValue: String? = project.findProperty("message")?.toString()
        val projectArray = (project.findProperty("packages")?.toString())?.split(",")
        val typeValue: String? = project.findProperty("type")?.toString()

        if (typeValue != "major" && typeValue != "minor" && typeValue != "patch") {
            logger.error("Please provide a type with -Ptype=\"major|minor|patch\"")
            throw Exception("Please provide a type with -Ptype=\"major|minor|patch\"")
        }

        val type = BumpType.fromString(typeValue)

        if (messageValue == null) {
            logger.error("Please provide a message with -Pmessage=\"Your message\"")
            throw Exception("Please provide a message with -Pmessage=\"Your message\"")
        }

        val message = messageValue

        if (projectArray.isNullOrEmpty()) {
            logger.error("Please provide a project with -Ppackages=\"project1,project2\"")
            throw Exception("Please provide a project with -Ppackages=\"project1,project2\"")
        }

        val allProjects = Changelog.instance.readStructureFile().projects

        // get projects
        val projects = projectArray.map { project ->
            allProjects.find { it.path == project }
                ?: throw Exception("Project $project not found")
        }

        logger.info("Bumping version with type $typeValue and message $messageValue")
        logger.info("Projects: ${projects.joinToString(", ") { it.path }}")

        val bump = Bump(type, message, projects.map { it.path })
        val bumpFile = Changelog.instance.readBumpFile()
        bumpFile.add(bump)
        Changelog.instance.writeBumpFile(bumpFile)
    }
}

data class TagCreationInfo(val project: ProjectStructure, val version: Version, val message: String)
typealias TagCreation = (TagCreationInfo) -> String

open class VersionTask : DefaultTask() {

    private var tagFormat: TagCreation = {
        "${it.project.name}@${it.version}"
    }

    fun tagFormat(tagFormat: TagCreation) {
        this.tagFormat = tagFormat
    }

    init {
        group = "changelog"
        description = "Prints the current version"
        this.dependsOn("initChangelog")
    }

    @org.gradle.api.tasks.TaskAction
    open fun version() {
        val structureFile = Changelog.instance.readStructureFile()
        val bumpFile = Changelog.instance.readBumpFile()
        val mapFile = Changelog.instance.readMap()

        val changelog = structureFile.projects.associate { it.path to mutableListOf<String>() }
        val bumpTypes = mutableMapOf<String, BumpType>()

        bumpFile.bumps.forEach { bump ->
            bump.paths.forEach { path ->
                changelog[path]?.add(bump.message)
                bumpTypes[path] = BumpType.fromInt(max(bumpTypes[path]?.toInt() ?: -1, bump.type.toInt()))
            }
        }

        bumpFile.bumps.clear()
        val tagFormat = this.tagFormat

        val stash = Changelog.instance.readStash()
        
        changelog.entries.forEach { (path, messages) ->
            val bumpType = bumpTypes[path]

            if (messages.isEmpty() || bumpType == null) return@forEach

            // Update version
            val prj = structureFile.projects.find { it.path == path }!!
            val version = prj.version
            when (bumpType) {
                BumpType.MAJOR -> version.incrementMajor()
                BumpType.MINOR -> version.incrementMinor()
                BumpType.PATCH -> version.incrementPatch()
            }

            val tagName = tagFormat(TagCreationInfo(prj, version, messages.joinToString("\n")))

            // stash tag
            stash.add(TagStash(tagName))
            

            // Add map entry
            var it = mapFile.packages.find { it.path == path }
            if (it == null) {
                it = PackageChangelog(
                    path,
                    path,
                    description
                )
                mapFile.add(it)
            }

            it.add(ChangelogVersion(version, messages))
        }

        // save files
        Changelog.instance.writeBumpFile(bumpFile)
        Changelog.instance.writeMap(mapFile)
        Changelog.instance.writeStructure(structureFile)
        Changelog.instance.writeStash(stash)

        // render new changelog files
        Changelog.instance.renderChangelog(mapFile)
    }
}

open class VersionTags : DefaultTask() {

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

            val tagCreation = rt.exec(arrayOf("git", "tag", tagRef(it.name), "-m", "Release ${it.name}"))
            val exitCode = tagCreation.waitFor()
            if (exitCode != 0) {
                println("Failed to create tag ${it.name}")
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

fun main() {
    val options = Changelog.instance.readStructureFile().projects.map { it.path }.toTypedArray()

    val checkboxList = CheckboxList(options)

    while (true) {
        checkboxList.draw()
        val keyStroke = readKeyStroke()

        if (keyStroke.keyType == KeyType.Enter) {
            println("Selected options: ${checkboxList.getSelectedOptions().joinToString(", ")}")
            break
        }

        checkboxList.handleInput(keyStroke)
    }
}

class CheckboxList(private val options: Array<String>) {
    private var selectedIndices = mutableSetOf<Int>()
    private var currentIndex = 0

    fun handleInput(keyStroke: KeyStroke) {
        when (keyStroke.keyType) {
            KeyType.ArrowUp -> moveUp()
            KeyType.ArrowDown -> moveDown()
            KeyType.Character -> toggleCheckbox()
            else -> {
            }
        }
    }

    private fun moveUp() {
        currentIndex = (currentIndex - 1 + options.size) % options.size
    }

    private fun moveDown() {
        currentIndex = (currentIndex + 1) % options.size
    }

    private fun toggleCheckbox() {
        if (currentIndex in selectedIndices) {
            selectedIndices.remove(currentIndex)
        } else {
            selectedIndices.add(currentIndex)
        }
    }

    fun draw() {
        options.forEachIndexed { index, option ->
            val checkbox = if (index in selectedIndices) "[*]" else "[ ]"
            val displayText = "$checkbox $option"
            println(displayText)
        }
    }

    fun getSelectedOptions(): List<String> {
        return selectedIndices.map { options[it] }
    }
}

fun readKeyStroke(): KeyStroke {
    val console = System.console()

    if (console != null) {
        // Reading a single character without echoing it to the console
        val input = console.reader().read()
        return KeyStroke.fromString(input.toString())
    }

    val reader = BufferedReader(InputStreamReader(System.`in`))

    try {
        val input = reader.read()
        return KeyStroke.fromString(input.toString())
    } catch (e: IOException) {
        throw RuntimeException("Error reading input", e)
    }
}

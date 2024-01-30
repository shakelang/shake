package com.shakelang.util.embed.plugin

import com.shakelang.util.embed.api.EmbedFolder
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeSpec
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.nio.file.*

open class FileBuilder : DefaultTask() {

    @OutputDirectory
    val outputDir = project.objects.directoryProperty()

    init {
        outputDir.convention(project.layout.buildDirectory.dir("generated/embed"))
    }

    @TaskAction
    open fun build() {
        val config = embedConfigurationFor(project)

        for (configuration in config.configurations) {
            val baseDir = configuration.baseDir.get()
            val distPackage = configuration.distPackage.get()
            val distName = configuration.distName.get()

            val paths = PatternMatcher(project.file(baseDir).toPath()).match(configuration.source)

            // Let's read all the files into byte arrays
            val files = mutableMapOf<String, ByteArray>()
            val baseDirPath = Paths.get(baseDir)
            for (path in paths) {
                val bytes = Files.readAllBytes(path)
                val relativePath = baseDirPath.relativize(path).toString()
                files[relativePath] = bytes
            }

            // Generate the output file using kotlin poet
            val file = FileSpec.builder(distPackage, distName)

            val folder = TypeSpec.objectBuilder(distName)
            folder.superclass(EmbedFolder::class)
            folder.addSuperclassConstructorParameter("%S", distName)
            folder.addSuperclassConstructorParameter("null")
            folder.addSuperclassConstructorParameter("emptyMap()")
            // init block

            val initBlock = CodeBlock.builder()

            for ((path, bytes) in files) {
                println("Adding file $path")
                // Let's add the file
                initBlock.addStatement("this.insert(%S, %S)", path, bytes.decodeToString())
            }

            folder.addInitializerBlock(initBlock.build())

            file.addType(folder.build())

            // Generate the file
            val fileName = "$distName.kt"
            val filePath = Paths.get(outputDir.get().asFile.absolutePath, fileName)
            Files.createDirectories(filePath.parent)
            Files.write(filePath, file.build().toString().encodeToByteArray())
        }
    }
}

class PatternMatcher(
    val basePath: Path,
) {

    val fs = FileSystems.getDefault()

    fun match(pattern: String): Set<Path> {
        val pathMatcher = fs.getPathMatcher("glob:$pattern")
        val paths = mutableSetOf<Path>()

        fun walkFolder(file: File) {
            if (file.isDirectory) {
                for (child in file.listFiles()!!) {
                    walkFolder(child)
                }
            } else {
                val path = file.toPath()
                if (pathMatcher.matches(path) && !paths.contains(path)) {
                    paths.add(path)
                }
            }
        }

        walkFolder(basePath.toFile())

        return paths
    }

    fun match(patterns: List<String>): Set<Path> {
        val paths = mutableSetOf<Path>()
        for (pattern in patterns) {
            for (path in match(pattern)) {
                if (!paths.contains(path)) {
                    paths.add(path)
                }
            }
        }
        return paths
    }
}

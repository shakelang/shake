package com.shakelang.util.embed.plugin

import com.shakelang.util.embed.api.EmbedFolder
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.nio.file.*
import javax.inject.Inject

open class FileBuilder
@Inject
constructor(
    @Input
    val sourceSet: String,
) : DefaultTask() {

    @OutputDirectory
    val outputDir = project.objects.directoryProperty()

    @Input
    val maxStatements = project.objects.property(Int::class.java)

    @Input
    val maxBytes = project.objects.property(Int::class.java)

    init {
        description = "Builds the embedded files for the $sourceSet source set"
        group = "build"
        outputDir.convention(project.layout.buildDirectory.dir("generated/embed"))
        maxStatements.convention(1024)
        maxBytes.convention(1024 * 1024)
    }

    @TaskAction
    open fun build() {
        val config = getEmbedExtension(project)

        for (configuration in config.configurations) {
            if (configuration.sourceSet.get() != sourceSet) continue

            logger.info("Building files for ${configuration.sourceSet.get()}")

            val baseDir = Paths.get(
                project.projectDir.absolutePath,
                configuration.sourceSetLocation.get(),
                configuration.sourceSet.get(),
                configuration.baseDir.get(),
            ).toFile().absolutePath

            val distPackage = configuration.distPackage.get()
            val distName = configuration.distName.get()
            val sourceSet = configuration.sourceSet.get()

            val paths = PatternMatcher(Paths.get(baseDir)).match(configuration.source)

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

            val maxStatements = this.maxStatements.get()
            val maxBytes = this.maxBytes.get()
            var statementCounter = 0
            var byteCounter = 0
            var functionId = 0
            var functionBlock = CodeBlock.builder()

            fun storeFunction() {
                val name = "init$functionId"
                val function = FunSpec.builder(name)
                function.addCode(functionBlock.build())
                functionBlock = CodeBlock.builder()
                functionId++
                initBlock.addStatement("this.$name()")
                folder.addFunction(function.build())
                statementCounter = 0
                byteCounter = 0
            }

            for ((path, bytes) in files) {
                statementCounter += 1
                byteCounter += bytes.size

                if (statementCounter > 1 && (statementCounter > maxStatements || byteCounter > maxBytes)) {
                    storeFunction()
                }

                functionBlock.addStatement("EmbedFolder.insert(this, %S, %S)", path, bytes.decodeToString())
            }

            storeFunction()

            folder.addInitializerBlock(initBlock.build())

            file.addType(folder.build())

            // Generate the file
            val fileName = "$distName.kt"
            val filePath = Paths.get((configuration.dist ?: throw Error("Dist not initialized")).absolutePath, fileName)
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

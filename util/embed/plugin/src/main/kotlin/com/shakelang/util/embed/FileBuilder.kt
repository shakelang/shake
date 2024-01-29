package com.shakelang.util.embed

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.net.URI
import java.nio.file.*
import java.nio.file.attribute.BasicFileAttributes

class FileBuilder : DefaultTask() {

    @TaskAction
    fun build() {
        val config = embedConfigurationFor(project)

        for (configuration in config.configurations) {
            val baseDir = configuration.baseDir.get()
            val distPackage = configuration.distPackage.get()
            val distName = configuration.distName.get()

            val paths = PatternMatcher(baseDir).match(configuration.source)

            // Let's read all the files into byte arrays
            val files = mutableMapOf<String, ByteArray>()
            val baseDirPath = Paths.get(baseDir)
            for (path in paths) {
                val bytes = Files.readAllBytes(path)
                val relativePath = baseDirPath.relativize(path).toString()
                files[relativePath] = bytes
            }
        }
    }
}

class PatternMatcher(
    val basePath: URI,
) {

    constructor(basePath: String) : this(URI(basePath))

    val fs = FileSystems.getFileSystem(basePath)

    fun match(pattern: String): Set<Path> {
        val pathMatcher = fs.getPathMatcher("glob:$pattern")
        val paths = mutableSetOf<Path>()

        Files.walkFileTree(
            fs.rootDirectories.first(),
            setOf(),
            1,
            object : SimpleFileVisitor<Path>() {
                override fun visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult {
                    if (pathMatcher.matches(file) && !paths.contains(file)) {
                        paths.add(file)
                    }
                    return FileVisitResult.CONTINUE
                }
            },
        )

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

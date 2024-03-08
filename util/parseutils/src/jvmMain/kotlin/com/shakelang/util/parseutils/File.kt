package com.shakelang.util.parseutils

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter

@Suppress("unused")
actual class File actual constructor(
    actual val path: String,
) {

    private val file: java.io.File = java.io.File(path)

    actual val absolutePath: String
        get() = this.file.absolutePath

    actual val name: String
        get() = file.name

    actual val isAbsolute: Boolean
        get() = file.isAbsolute

    actual val isFile: Boolean
        get() = this.file.exists() && this.file.isFile

    actual val isDirectory: Boolean
        get() = this.file.exists() && this.file.isDirectory

    actual val parent: File
        get() = File(this.file.parent)

    actual val parentPath: String
        get() = this.file.parent

    actual val contents: CharArray
        get() {
            val reader = BufferedReader(FileReader(this.file))
            val chars = CharArray(this.file.length().toInt()) // FIXME files that are longer than 2^31 (integers limit)
            for (i in chars.indices) chars[i] = reader.read().toChar()
            reader.close()
            return chars
        }

    actual val contentsString: String
        get() = contents.concatToString()

    actual val exists: Boolean = this.file.exists()

    actual fun mkdir() {
        this.file.mkdir()
    }

    actual fun mkdirs() {
        this.file.mkdirs()
    }

    actual fun write(content: String) {
        val writer = BufferedWriter(FileWriter(file))
        writer.write(content)
        writer.close()
    }

    actual fun write(content: CharArray) {
        val writer = BufferedWriter(FileWriter(file))
        writer.write(content)
        writer.close()
    }
}

actual fun resourceFile(path: String): String = File::class.java.classLoader
    .getResourceAsStream(path)
    ?.reader()
    ?.readText()
    ?: throw Error("Can't find resource '$path'")

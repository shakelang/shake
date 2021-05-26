package com.github.nsc.de.shake.util

import java.io.BufferedReader
import java.io.FileReader

@Suppress("unused")
actual class File(
    actual val path: String
) {

    private val file: java.io.File = java.io.File(path)

    actual val absolutePath: String
        get() = this.file.absolutePath

    actual val name: String
        get() = file.name

    actual val isAbsolute: Boolean
        get() = file.isAbsolute

    actual val isFile: Boolean
        get() = this.file.isFile

    actual val isDirectory: Boolean
        get() = this.file.isDirectory

    actual val parent: File
        get() = File(this.file.parent)

    actual val parentPath: String
        get() = this.file.parent

    actual val contents: CharArray
        get() {
            val reader = BufferedReader(FileReader(this.file))
            val chars = CharArray(this.file.length().toInt()) // FIXME files that are longer than 2^31 (integer limit)
            for (i in chars.indices) chars[i] = reader.read().toChar()
            return chars
        }

    actual val contentsString: String
        get() = contents.concatToString()

}
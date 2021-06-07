package com.github.shakelang.shake.util

expect class File(
    path: String
) {

    val path: String
    val name: String
    val absolutePath: String
    val isAbsolute: Boolean
    val isFile: Boolean
    val isDirectory: Boolean
    val parent: File
    val parentPath: String
    val contents: CharArray
    val contentsString: String

    fun mkdir()
    fun mkdirs()
    fun write(content: String)
    fun write(content: CharArray)

}
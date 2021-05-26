package com.github.nsc.de.shake.util

expect class File {

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


}
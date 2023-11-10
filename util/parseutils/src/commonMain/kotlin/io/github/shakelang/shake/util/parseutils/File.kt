package io.github.shakelang.shake.util.parseutils

/**
 * An abstract File implementation that works on different environments
 */
expect class File

/**
 * Constructor for [File] class
 *
 * @param path the path of the file
 */(

    path: String

) {

    /**
     * The path of the [File]
     */
    val path: String

    /**
     * The name of the [File]
     */
    val name: String

    /**
     * The absolute path of the [File]
     */
    val absolutePath: String

    /**
     * Is the file path absolute?
     */
    val isAbsolute: Boolean

    /**
     * Is the [File] a file and not a directory
     */
    val isFile: Boolean

    /**
     * Is the [File] a directory and not a fine
     */
    val isDirectory: Boolean

    /**
     * The parent [File]
     */
    val parent: File

    /**
     * The parent file's path
     */
    val parentPath: String

    /**
     * The contents of the [File] as a [CharArray]
     */
    val contents: CharArray

    /**
     * The contents of the [File] as a [String]
     */
    val contentsString: String

    /**
     * Does the file exist? ([Boolean] value)
     */
    val exists: Boolean

    /**
     * Create the file as directory
     */
    fun mkdir()

    /**
     * Create the file as directory and it's parent directories
     */
    fun mkdirs()

    /**
     * Write a string to the file
     */
    fun write(content: String)

    /**
     * Write a char array
     */
    fun write(content: CharArray)
}

expect fun resourceFile(path: String): String

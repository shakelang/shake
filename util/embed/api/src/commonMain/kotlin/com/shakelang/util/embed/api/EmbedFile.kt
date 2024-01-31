package com.shakelang.util.embed.api

import com.shakelang.util.io.streaming.input.countingStream
import com.shakelang.util.io.streaming.input.dataStream
import com.shakelang.util.io.streaming.input.inputStream

/**
 * A virtual file in the file tree
 * @since 0.1.0
 * @version 0.1.1
 */
open class EmbedFile(

    /**
     * The name of the file
     * @since 0.1.0
     */
    final override val name: String,

    /**
     * The parent of the file
     * @since 0.1.0
     */
    final override val parent: EmbedFolder,

    /**
     * The contents of the file
     * @since 0.1.0
     */
    val contents: ByteArray,
) : EmbedFileEntity {

    /**
     * The path of the file
     * @since 0.1.0
     */
    override val path: String = "${parent.path}/$name"

    /**
     * If the entity is a file
     * @since 0.1.0
     */
    override val isFile: Boolean get() = true

    /**
     * If the entity is a folder
     * @since 0.1.0
     */
    override val isDirectory: Boolean get() = false

    /**
     * Get the contents of the file as a string
     * @since 0.1.0
     */
    fun contentsAsString(): String = contents.decodeToString()

    /**
     * Get the contents of the file as a byte array
     * @since 0.1.0
     */
    fun contentsAsByteArray(): ByteArray = contents

    /**
     * Get the contents of the file as a stream
     * @since 0.1.1
     */
    fun stream() = contents.inputStream()

    /**
     * Get the contents of the file as a stream
     * @since 0.1.1
     */
    fun inputStream() = contents.inputStream()

    /**
     * Get the contents of the file as a data input stream
     * @since 0.1.1
     */
    fun dataStream() = contents.dataStream()

    /**
     * Get the contents of the file as a counting input stream
     * @since 0.1.1
     */
    fun countingInputStream() = contents.countingStream()
}

/**
 * Create a file
 * @since 0.1.0
 */
fun file(name: String, contents: ByteArray, parent: EmbedFolder) = EmbedFile(name, parent, contents)

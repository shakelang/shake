/**
 * @file EmbedFileEntity.kt
 * A very basic virtual file tree
 */
package com.shakelang.util.embed.api

/**
 * An entity in the file tree (either a file or a folder)
 * @since 0.1.0
 * @version 0.1.1
 * @see EmbedFile
 * @see EmbedFolder
 */
interface EmbedFileEntity {

    /**
     * The name of the file or folder
     * @since 0.1.0
     */
    val name: String

    /**
     * The path of the file or folder
     * @since 0.1.0
     */
    val path: String

    /**
     * If the entity is a file
     * @since 0.1.0
     */
    val isFile: Boolean

    /**
     * If the entity is a folder
     * @since 0.1.0
     */
    val isDirectory: Boolean

    /**
     * The parent of the entity
     * @since 0.1.0
     */
    val parent: EmbedFolder?

    /**
     * Convert the entity to a [EmbedFile]
     * @since 0.1.0
     */
    fun toFile(): EmbedFile = this as EmbedFile

    /**
     * Convert the entity to a [EmbedFolder]
     * @since 0.1.0
     */
    fun toFolder(): EmbedFolder = this as EmbedFolder
}

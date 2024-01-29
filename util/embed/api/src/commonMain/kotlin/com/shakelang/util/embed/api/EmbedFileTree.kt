package com.shakelang.util.embed.api

class EmbedFileTree

interface EmbedFileEntity {
    val name: String
    val path: String
    val isFile: Boolean
    val isDirectory: Boolean
    val mutable: Boolean get() = false
    val parent: EmbedFolder?

    fun toFile(): EmbedFile = this as EmbedFile
    fun toFolder(): EmbedFolder = this as EmbedFolder
}

class EmbedFolder(
    override val name: String,
    override val parent: EmbedFolder?,
    files: Map<String, EmbedFileEntity>,
) : EmbedFileEntity {
    val children: MutableMap<String, EmbedFileEntity> = files.toMutableMap()
    val files: Map<String, EmbedFileEntity> get() = children

    override val path: String = if (parent == null) name else "${parent.path}/$name"
    override val isFile: Boolean get() = false
    override val isDirectory: Boolean get() = true

    fun insert(path: Array<String>, contents: ByteArray): EmbedFolder {
        if (path.isEmpty()) throw IllegalArgumentException("Path cannot be empty")
        if (path.size == 1) {
            children[path[0]] = EmbedFile(path[0], this, contents)
        } else {
            val folder = children[path[0]] as? EmbedFolder ?: EmbedFolder(path[0], this, emptyMap()).also {
                children[path[0]] = it
            }
            folder.insert(path.sliceArray(1 until path.size), contents)
        }
        return this
    }

    fun insert(path: String, contents: ByteArray) = insert(path.split("/").toTypedArray(), contents)
    fun insert(path: String, contents: String) = insert(path, contents.encodeToByteArray())
}

class EmbedFile(
    override val name: String,
    override val parent: EmbedFolder,
    val contents: ByteArray,
) : EmbedFileEntity {
    override val path: String = "${parent.path}/$name"
    override val isFile: Boolean get() = true
    override val isDirectory: Boolean get() = false
    fun contentsAsString(): String = contents.decodeToString()
    fun contentsAsByteArray(): ByteArray = contents
}

fun file(name: String, contents: ByteArray, parent: EmbedFolder) = EmbedFile(name, parent, contents)

fun folder(name: String, vararg files: EmbedFileEntity, parent: EmbedFolder? = null) =
    EmbedFolder(name, parent, files.associateBy { it.name })

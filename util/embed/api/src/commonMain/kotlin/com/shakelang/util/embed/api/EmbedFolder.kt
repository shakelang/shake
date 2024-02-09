package com.shakelang.util.embed.api

/**
 * A virtual folder in the file tree
 * @since 0.1.0
 * @version 0.1.1
 */
open class EmbedFolder(

    /**
     * The name of the folder
     * @since 0.1.0
     */
    final override val name: String,

    /**
     * The parent of the folder
     * @since 0.1.0
     */
    final override val parent: EmbedFolder?,

    /**
     * The files in the folder
     * @since 0.1.0
     */
    files: Map<String, EmbedFileEntity>,
) : EmbedFileEntity {

    /**
     * The children of the folder (mutable variant of [files])
     * @since 0.1.0
     */
    val children: MutableMap<String, EmbedFileEntity> = files.toMutableMap()

    /**
     * The files in the folder (immutable variant of [children])
     * @since 0.1.0
     */
    val files: Map<String, EmbedFileEntity> get() = children

    /**
     * The folders in the folder (immutable variant of [children])
     * @since 0.1.0
     */
    override val path: String = if (parent == null) name else "${parent.path}/$name"

    /**
     * The folders in the folder (immutable variant of [children])
     * @since 0.1.0
     */
    override val isFile: Boolean get() = false

    /**
     * The folders in the folder (immutable variant of [children])
     * @since 0.1.0
     */
    override val isDirectory: Boolean get() = true

    /**
     * Insert a file into the folder
     * @since 0.1.0
     */
    private fun insert(path: Array<String>, contents: ByteArray): EmbedFolder {
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

    /**
     * Insert a file into the folder
     * @since 0.1.0
     */
    private fun insert(path: String, contents: ByteArray) = insert(path.split("/").toTypedArray(), contents)

    /**
     * Get a list of all files in the folder (recursive)
     */
    fun allFiles(): List<EmbedFile> {
        val files = mutableListOf<EmbedFile>()
        for (child in children.values) {
            if (child.isFile) {
                files.add(child as EmbedFile)
            } else {
                files.addAll((child as EmbedFolder).allFiles())
            }
        }
        return files
    }

    /**
     * Iterate over all files in the folder (recursive)
     */
    fun forEachFile(action: (EmbedFile) -> Unit) {
        for (child in children.values) {
            if (child.isFile) {
                action(child as EmbedFile)
            } else {
                (child as EmbedFolder).forEachFile(action)
            }
        }
    }

    private fun globToRegex(glob: String): Regex {
        val regex = StringBuilder("^")
        var escaping = false
        for (c in glob) {
            when (c) {
                '.', '$', '(', ')', '|', '+', '{', '[', '\\' -> {
                    if (!escaping) {
                        regex.append("\\")
                        escaping = true
                    }
                    regex.append(c)
                }
                '*' -> {
                    if (escaping) {
                        regex.append("\\*")
                        escaping = false
                    } else {
                        regex.append(".*")
                    }
                }
                '?' -> {
                    if (escaping) {
                        regex.append("\\?")
                        escaping = false
                    } else {
                        regex.append('.')
                    }
                }
                '}' -> {
                    if (escaping) {
                        regex.append('}')
                        escaping = false
                    } else {
                        regex.append("\\}")
                    }
                }
                ',' -> {
                    if (escaping) {
                        regex.append(',')
                        escaping = false
                    } else {
                        regex.append('|')
                    }
                }
                else -> {
                    regex.append(c)
                    escaping = false
                }
            }
        }
        regex.append('$')
        return regex.toString().toRegex()
    }

    fun glob(pattern: String): List<EmbedFileEntity> {
        val files = mutableListOf<EmbedFileEntity>()
        for (child in children.values) {
            if (globToRegex(pattern).matches(child.name)) files.add(child)
            if (child.isDirectory) files.addAll((child as EmbedFolder).glob(pattern))
        }
        return files
    }

    fun globFiles(pattern: String): List<EmbedFile> {
        val files = mutableListOf<EmbedFile>()
        for (child in children.values) {
            if (globToRegex(pattern).matches(child.name) && child.isFile) files.add(child as EmbedFile)
            if (child.isDirectory) files.addAll((child as EmbedFolder).globFiles(pattern))
        }
        return files
    }

    fun globFolders(pattern: String): List<EmbedFolder> {
        val files = mutableListOf<EmbedFolder>()
        for (child in children.values) {
            if (child.isDirectory) {
                if (globToRegex(pattern).matches(child.name)) files.add(child as EmbedFolder)
                files.addAll((child as EmbedFolder).globFolders(pattern))
            }
        }
        return files
    }

    /**
     * Get a file from the folder
     * @since 0.1.0
     */
    operator fun get(path: String): EmbedFileEntity? {
        val parts = path.split("/").toTypedArray()
        var folder: EmbedFolder? = this
        for (i in 0 until parts.size - 1) {
            folder = folder?.children?.get(parts[i]) as? EmbedFolder
        }
        return folder?.children?.get(parts.last())
    }

    companion object {

        /**
         * Insert a file into a folder
         * @since 0.1.1
         */
        fun insert(folder: EmbedFolder, path: String, contents: ByteArray) = folder.insert(path, contents)

        /**
         * Insert a file into a folder
         * @since 0.1.1
         */
        fun insert(folder: EmbedFolder, path: String, contents: String) = folder.insert(path, contents.encodeToByteArray())
    }
}

/**
 * Create a file
 * @since 0.1.0
 */
fun folder(name: String, vararg files: EmbedFileEntity, parent: EmbedFolder? = null) =
    EmbedFolder(name, parent, files.associateBy { it.name })

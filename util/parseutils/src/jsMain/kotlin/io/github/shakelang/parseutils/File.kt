package io.github.shakelang.parseutils

import io.github.shakelang.environment.Environment

external fun require(name: String): dynamic
external val require: dynamic

val nodeJsAvailable = Environment.getEnvironment().isJavaScriptNode
external val process: dynamic

val node_fs = if(nodeJsAvailable) require("fs") else null
val node_path = if(nodeJsAvailable) require("path") else null

@Suppress("unused")
actual class File actual constructor(
    actual val path: String
) {

    actual val name: String
        get() = node_path.basename(path) as String

    actual val absolutePath: String
        get() = node_path.resolve(path) as String

    actual val isAbsolute: Boolean
        get() = node_path.isAbsolute(path) as Boolean

    actual val isFile: Boolean
        get() = this.exists && this.lstats.isFile() as Boolean

    actual val isDirectory: Boolean
        get() = this.exists && this.lstats.isDirectory() as Boolean

    actual val parent: File
        get() = File(this.parentPath)

    actual val parentPath: String
        get() = node_path.resolve(node_path.join(path, "..")) as String

    val lstats: dynamic
        get() = node_fs.lstatSync(path)

    actual val contents: CharArray
        get() = contentsString.toCharArray()

    actual val contentsString: String
        get() = node_fs.readFileSync(path) as String

    actual val exists: Boolean
        get() = node_fs.existsSync(path) as Boolean

    init {
        if(node_fs == null || node_path == null) throw Error(
            "Can't use file API because node seems to be not available (you are probably in browser)")
    }

    actual fun mkdir() { node_fs.mkdirSync(path) }
    actual fun mkdirs() { node_fs.mkdirSync(path, mapOf("recursive" to true)) }
    actual fun write(content: String) { node_fs.writeFileSync(path, content) }
    actual fun write(content: CharArray) = this.write(content.concatToString())

}

actual fun resourceFile(path: String): String = require(path) as String
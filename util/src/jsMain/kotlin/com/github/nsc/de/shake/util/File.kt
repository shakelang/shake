package com.github.nsc.de.shake.util

external fun require(name: String): dynamic
external val process: dynamic

fun jsTypeOf(o: dynamic): String {
    return js("typeof o") as String
}

val node_fs = if(jsTypeOf(process) == "object") require("fs") else null
val node_path = if(jsTypeOf(process) == "object") require("path") else null

@Suppress("unused")
actual class File(
    actual val path: String
) {

    actual val name: String
        get() = node_path.basename(path) as String

    actual val absolutePath: String
        get() = node_path.resolve(path) as String

    actual val isAbsolute: Boolean
        get() = node_path.isAbsolute(path) as Boolean

    actual val isFile: Boolean
        get() = this.lstats.isFile() as Boolean

    actual val isDirectory: Boolean
        get() = this.lstats.isDirectory() as Boolean

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

    init {
        if(node_fs == null || node_path == null) throw Error(
            "Can't use file API because node seems to be not available (you are probably in browser)")
    }

}
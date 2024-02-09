package com.shakelang.shake.parser.api

import com.shakelang.shake.parser.test.ShakeParserTestResources
import io.kotest.core.spec.Spec

class Template (
    val name: String,
) {
    val path: String
        get() = "templates/$name"

    val shakePath: String
        get() = "$path.shake"

    val jsonPath: String
        get() = "$path.json"

    val errorPath: String
        get() = "$path.error"

    val shake: String
        get() = (ShakeParserTestResources[shakePath] ?:error("Template $path has no shake file")).toFile().contentsAsString()

    val json: String
        get() = (ShakeParserTestResources[jsonPath] ?:error("Template $path has no json file")).toFile().contentsAsString()

    val error: String
        get() = (ShakeParserTestResources[errorPath] ?:error("Template $path has no error file")).toFile().contentsAsString()

}

fun template(name: String) = Template(name)

class ReplaceTemplate(
    val entries: Map<String, String>
) {
    fun apply(s: String): String = entries.entries.fold(s) { acc, (k, v) -> acc.replace(k, v) }
}

fun replaceTemplate(vararg entries: Pair<String, String>) = ReplaceTemplate(entries.toMap())

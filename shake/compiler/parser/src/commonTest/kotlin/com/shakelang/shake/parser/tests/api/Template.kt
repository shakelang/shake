package com.shakelang.shake.parser.tests.api

import com.shakelang.shake.parser.test.ShakeParserTestResources

class Template(
    val name: String,
) {

    val path = "templates/$name"

    val shakePath = "$path.shake"
    val jsonPath = "$path.json"
    val errorPath = "$path.error"

    private var _shake: String? = ShakeParserTestResources[shakePath]?.toFile()?.contentsAsString()
    private var _json: String? = ShakeParserTestResources[jsonPath]?.toFile()?.contentsAsString()
    private var _error: String? = ShakeParserTestResources[errorPath]?.toFile()?.contentsAsString()

    val code: String
        get() = _shake ?: ""

    val json: String
        get() = _json ?: ""

    val error: String
        get() = _error ?: ""

    fun apply(replace: ReplaceTemplate): Template {
        this._shake = this._shake?.let { replace.apply(it) }
        this._json = this._json?.let { replace.apply(it) }
        this._error = this._error?.let { replace.apply(it) }
        return this
    }

    fun applied(replace: ReplaceTemplate): Template {
        val template = Template(name)
        template._shake = this._shake?.let { replace.apply(it) }
        template._json = this._json?.let { replace.apply(it) }
        template._error = this._error?.let { replace.apply(it) }
        return template
    }
}

fun template(name: String) = Template(name)

class ReplaceTemplate(
    val entries: Map<String, String>,
) {
    fun apply(s: String): String = entries.entries.fold(s) { acc, (k, v) -> acc.replace(k, v) }
}

fun replaceTemplate(vararg entries: Pair<String, String>) = ReplaceTemplate(entries.toMap())

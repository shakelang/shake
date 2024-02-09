package com.shakelang.shake.parser.api

import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.FreeSpec

val attributes = listOf(
    listOf("final"),
    listOf("static"),
    listOf("public", "protected", "private"),
)

val attributesNoStatic = listOf(
    listOf("final"),
    listOf("public", "protected", "private"),
)

class AttributeInfo(
    val attributeString: String,
) {
    val isFinal: Boolean
        get() = attributeString.contains("final")

    val isStatic: Boolean
        get() = attributeString.contains("static")

    val isPublic: Boolean
        get() = attributeString.contains("public")

    val isProtected: Boolean
        get() = attributeString.contains("protected")

    val isPrivate: Boolean
        get() = attributeString.contains("private")

    val isPackagePrivate: Boolean
        get() = !isPublic && !isProtected && !isPrivate

    val accessModifier: String
        get() = when {
            isPublic -> "public"
            isProtected -> "protected"
            isPrivate -> "private"
            else -> "package"
        }
}

/**
 * Combine given tokens in every possible way
 * @param words List of tokens where each token can be a string or an array of strings
 * @return List of combinations as strings
 */
fun combineTokens(words: List<List<String>>): List<String> {
    val combinations = mutableListOf("")

    // Get all possible combinations of the given length
    fun choose(words: List<List<String>>, length: Int): List<List<String>> {
        val combs = mutableListOf<List<String>>()
        for (i in words.indices) {
            val first = words[i]
            val rest = words.take(i) + words.drop(i + 1)
            if (length == 1) {
                for (f in first) {
                    combs.add(listOf(f))
                }
                continue
            }
            for (other in choose(rest, length - 1)) {
                for (f in first) {
                    combs.add(listOf(f) + other)
                }
            }
        }
        return combs
    }

    for (i in 1..words.size) {
        combinations.addAll(choose(words, i).map { it.joinToString(" ") + " " })
    }

    return combinations
}

class TestProviderInit(
    val parent: TestProviderInit?,
) {

    var name: String = ""
    val path: String
        get() = if (parent != null) "${parent.path}/$name" else name ?: error("Name is not set")

    fun template(name: String) = Template(name)
    fun provider(init: TestProviderInit.() -> Unit) = TestProviderInit(this).apply(init)
    fun replaceTemplate(vararg entries: Pair<String, String>) = ReplaceTemplate(entries.toMap())
}

fun FreeSpec.generateTests(
    init: TestProviderInit.() -> Unit,
) {
    val provider = TestProviderInit(null)
    val names = provider.init()
    val path = provider.path
}

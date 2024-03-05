package com.shakelang.shake.parser.tests.api

import com.shakelang.shake.parser.ParserTestUtil
import com.shakelang.shake.parser.impl.ShakeParserHelper
import com.shakelang.util.io.streaming.AppendableStream
import com.shakelang.util.io.streaming.Stream
import com.shakelang.util.shason.json
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

val attributes = listOf(
    listOf("final"),
    listOf("static"),
    listOf("public", "protected", "private"),
)

val primitiveTypes = listOf(
    "byte" to "byte",
    "shorts" to "shorts",
    "int" to "integers",
    "long" to "long",
    "float" to "float",
    "doubles" to "doubles",
    "char" to "char",
    "boolean" to "boolean",
    "ubyte" to "unsigned_byte",
    "ushort" to "unsigned_short",
    "uint" to "unsigned_integer",
    "ulong" to "unsigned_long",
)

val primitiveTypesNoUnsigned = listOf(
    "byte" to "byte",
    "shorts" to "shorts",
    "int" to "integers",
    "long" to "long",
    "float" to "float",
    "doubles" to "doubles",
    "char" to "char",
    "boolean" to "boolean",
)

val primitiveTypesIncludingVoid = listOf(
    "byte" to "byte",
    "shorts" to "shorts",
    "int" to "integers",
    "long" to "long",
    "float" to "float",
    "doubles" to "doubles",
    "char" to "char",
    "boolean" to "boolean",
    "void" to "void",
    "unsigned byte" to "unsigned_byte",
    "unsigned shorts" to "unsigned_short",
    "unsigned int" to "unsigned_integer",
    "unsigned long" to "unsigned_long",
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

fun combineAttributes(
    includeStatic: Boolean = true,
    includeAccessModifiers: Boolean = true,
    includeFinal: Boolean = true,
): List<AttributeInfo> {
    val attributes = mutableListOf<List<String>>()
    if (includeStatic) attributes.add(listOf("static"))
    if (includeAccessModifiers) {
        attributes.add(
            listOf(
                "public",
                "protected",
                "private",
            ),
        )
    }
    if (includeFinal) attributes.add(listOf("final"))

    return combineTokens(attributes).map { AttributeInfo(it) }
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
        get() = if (parent != null) "${parent.path}/$name" else name

    private val tests = AppendableStream<TestGenerator>()

    fun template(name: String) = Template(name)
    fun provider(init: TestProviderInit.() -> Unit) = TestProviderInit(this).apply(init).stream().forEach {
        tests.append(it)
    }
    fun replaceTemplate(vararg entries: Pair<String, String>) = ReplaceTemplate(entries.toMap())

    fun stream(): Stream<TestGenerator> = tests

    fun test(
        name: String,
        isIgnored: Boolean = false,
        init: SuccessTestGenerator.() -> Unit,
    ) {
        val generator = SuccessTestGenerator(this@TestProviderInit, name, isIgnored, init)
        tests.append(generator)
    }

    fun error(
        name: String,
        isIgnored: Boolean = false,
        init: ErrorTestGenerator.() -> Unit,
    ) {
        val generator = ErrorTestGenerator(this@TestProviderInit, name, isIgnored, init)
        tests.append(generator)
    }
}

abstract class Test(
    val name: String,
    val path: String,
    val input: String,
    val isIgnored: Boolean,
) {
    abstract val expectedJson: String?
    abstract val expectedError: String?
    abstract val isFailure: Boolean
    val isSuccessful: Boolean get() = !isFailure

    fun toSuccessful(): SuccessTest = this as SuccessTest
    fun toFailure(): ErrorTest = this as ErrorTest
}

class SuccessTest(
    name: String,
    path: String,
    input: String,
    override val expectedJson: String,
    isIgnored: Boolean,
) : Test(name, path, input, isIgnored) {
    override val expectedError: Nothing? get() = null
    override val isFailure: Boolean get() = false
}

class ErrorTest(
    name: String,
    path: String,
    input: String,
    isIgnored: Boolean,
    override val expectedError: String,
) : Test(name, path, input, isIgnored) {
    override val expectedJson: Nothing? get() = null
    override val isFailure: Boolean get() = true
}

abstract class TestGenerator(
    val provider: TestProviderInit,
    val name: String,
    val isIgnored: Boolean,
) {
    val path: String get() = provider.path + if (name.isNotEmpty()) "/$name" else ""

    var input: String = ""

    abstract fun generate(): Test

    abstract fun init()
}

class SuccessTestGenerator(
    provider: TestProviderInit,
    name: String,
    isIgnored: Boolean,
    private val init: SuccessTestGenerator.() -> Unit,
) : TestGenerator(provider, name, isIgnored) {

    var expectedJson: String = ""
    private var initCalled = false

    override fun generate(): Test {
        if (!initCalled) {
            init()
        }
        return SuccessTest(name, path, input, expectedJson, isIgnored)
    }

    override fun init() {
        if (initCalled) throw IllegalStateException("init() called twice")
        initCalled = true
        init.invoke(this)
    }
}

class ErrorTestGenerator(
    provider: TestProviderInit,
    name: String,
    isIgnored: Boolean,
    private val init: ErrorTestGenerator.() -> Unit,
) : TestGenerator(provider, name, isIgnored) {

    var expectedError: String = ""
    private var initCalled = false

    override fun generate(): Test {
        if (!initCalled) {
            init()
        }
        return ErrorTest(name, path, input, isIgnored, expectedError)
    }

    override fun init() {
        if (initCalled) throw IllegalStateException("init() called twice")
        init()
    }
}

fun FreeSpec.generateTests(
    init: TestProviderInit.() -> Unit,
) {
    val provider = TestProviderInit(null)
    provider.init()
    provider.stream().forEach {
        it.path.config(enabled = !it.isIgnored) {
            val generated = it.generate()
            if (generated.isFailure) {
                val test = generated.toFailure()

                val error = shouldThrow<ShakeParserHelper.ParserError> {
                    ParserTestUtil.parse("${test.path}.shake", test.input)
                }

                try {
                    error.message shouldBe test.expectedError
                } catch (e: Throwable) {
                    println("Source: \n${test.input}")
                    println()
                    println("Error: ${error.message}")
                    println("Expected: ${test.expectedError}")
                    error.printStackTrace()
                    throw e
                }
            }

            if (generated.isSuccessful) {
                val test = generated.toSuccessful()
                try {
                    val ast = json.stringify(ParserTestUtil.parse("${test.path}.shake", test.input).json)
                    val expected = json.stringify(json.parse(test.expectedJson))

                    try {
                        ast shouldBe expected
                    } catch (e: Throwable) {
                        println("Source: \n${test.input}")
                        println()
                        println("AST: $ast")
                        println("Expected: $expected")
                        throw e
                    }
                } catch (e: Throwable) {
                    println("Source: \n${test.input}")
                    println()
                    println("Error: ${e.message}")
                    throw e
                }
            }
        }
    }
}

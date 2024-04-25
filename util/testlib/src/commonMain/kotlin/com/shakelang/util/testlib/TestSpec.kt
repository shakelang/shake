package com.shakelang.util.testlib

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.spec.style.scopes.DescribeSpecContainerScope
import io.kotest.core.test.TestScope
import kotlin.reflect.KClass

abstract class TestSpec(

    body: TestSpecContext.() -> Unit,
    flatten: Boolean = false,

) : DescribeSpec({
    fun createTestSpecContext(
        prefix: String?,
        flatten: Boolean,
        describe: (name: String, test: suspend DescribeSpecContainerScope.() -> Unit) -> Unit,
        it: (name: String, test: suspend TestScope.() -> Unit) -> Unit,
    ): TestSpecContext {
        if (flatten) {
            return object : TestSpecContext {
                override fun describe(name: String, init: TestSpecContext.() -> Unit) {
                    createTestSpecContext(
                        "${prefix?.plus(" - ") ?: ""}$name",
                        true,
                        describe,
                        it,
                    ).init()
                }
                override fun it(name: String, test: suspend TestScope.() -> Unit, enabled: Boolean) {
                    it(prefix?.plus(" - $name") ?: name, test)
                }
            }
        } else {
            return object : TestSpecContext {
                override fun describe(name: String, init: TestSpecContext.() -> Unit) {
                    describe("${prefix?.plus(" - ") ?: ""}$name") {
                        createTestSpecContext(null, false, describe, it).init()
                    }
                }
                override fun it(name: String, test: suspend TestScope.() -> Unit, enabled: Boolean) {
                    it("${prefix?.plus(" - ") ?: ""}$name", test)
                }
            }
        }
    }

    val context = createTestSpecContext(null, flatten, ::describe, ::it)
    context.body()
})

abstract class FlatTestSpec(
    body: TestSpecContext.() -> Unit,
) : TestSpec(body, flatten = true)

interface TestSpecContext {
    fun describe(name: String, init: TestSpecContext.() -> Unit)
    fun describe(clazz: KClass<*>, init: TestSpecContext.() -> Unit) = describe(clazz.simpleName ?: "", init)
    fun it(name: String, test: suspend TestScope.() -> Unit, enabled: Boolean = true)
    fun xit(name: String, test: suspend TestScope.() -> Unit) = it(name, test, false)
    fun it(name: String, test: suspend TestScope.() -> Unit) = it(name, test, true)
}

inline fun <reified T> TestSpecContext.describe(noinline init: TestSpecContext.() -> Unit, s: String) = describe(T::class, init)

package com.shakelang.util.testlib

import com.shakelang.util.environment.getRunningEnvironment
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
        it: (name: String, test: suspend TestScope.() -> Unit, enabled: Boolean) -> Unit,
        allContentsDisabled: Boolean,
    ): TestSpecContext {
        if (flatten) {
            return object : TestSpecContext {
                override fun describe(name: String, init: TestSpecContext.() -> Unit) {
                    createTestSpecContext(
                        "${prefix?.plus(" - ") ?: ""}$name",
                        true,
                        describe,
                        it,
                        allContentsDisabled,
                    ).init()
                }
                override fun it(name: String, test: suspend TestScope.() -> Unit, enabled: Boolean) {
                    it(prefix?.plus(" - $name") ?: name, test, enabled && !allContentsDisabled)
                }
                override fun xcontents(cond: Boolean, init: TestSpecContext.() -> Unit) {
                    if (!cond) {
                        createTestSpecContext(
                            prefix,
                            flatten,
                            describe,
                            it,
                            true,
                        ).init()
                    } else {
                        init()
                    }
                }
            }
        } else {
            return object : TestSpecContext {
                override fun describe(name: String, init: TestSpecContext.() -> Unit) {
                    describe("${prefix?.plus(" - ") ?: ""}$name") {
                        createTestSpecContext(
                            null,
                            false,
                            describe,
                            it,
                            allContentsDisabled,
                        ).init()
                    }
                }
                override fun it(name: String, test: suspend TestScope.() -> Unit, enabled: Boolean) {
                    it("${prefix?.plus(" - ") ?: ""}$name", test, enabled && allContentsDisabled)
                }
                override fun xcontents(cond: Boolean, init: TestSpecContext.() -> Unit) {
                    if (!cond) {
                        createTestSpecContext(
                            prefix,
                            flatten,
                            describe,
                            it,
                            true,
                        ).init()
                    } else {
                        init()
                    }
                }
            }
        }
    }

    val context = createTestSpecContext(null, flatten, ::describe, {
            name, test, enabled ->
        if (enabled) {
            it(name, test)
        } else {
            xit(name, test)
        }
    }, false)
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
    fun xcontents(cond: Boolean, init: TestSpecContext.() -> Unit)
    fun xcontents(cond: () -> Boolean, init: TestSpecContext.() -> Unit) = xcontents(cond(), init)
    fun runInEnvironments(env: List<TestEnvironment>, init: TestSpecContext.() -> Unit) {
        xcontents(
            {
                if (getRunningEnvironment().isJava && env.contains(TestEnvironment.JVM)) return@xcontents true
                if (getRunningEnvironment().isJavaScript && env.contains(TestEnvironment.JS)) return@xcontents true
                if (getRunningEnvironment().isJavaScriptNode && env.contains(TestEnvironment.JS_NODE)) return@xcontents true
                if (getRunningEnvironment().isJavaScriptBrowser && env.contains(TestEnvironment.JS_BROWSER)) return@xcontents true
                return@xcontents false
            },
            init,
        )
    }
}

enum class TestEnvironment {
    JVM,
    JS,
    JS_NODE,
    JS_BROWSER,
    Native,
}

inline fun <reified T> TestSpecContext.describe(noinline init: TestSpecContext.() -> Unit, s: String) = describe(T::class, init)

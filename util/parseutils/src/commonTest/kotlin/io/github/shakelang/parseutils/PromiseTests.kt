package io.github.shakelang.parseutils

import io.github.shakelang.testlib.CallCounter
import kotlin.test.Test
import kotlin.test.assertEquals

class PromiseTests {

    @Test
    fun testPromise() {
        val promise = Promise<String> { resolve, _ ->
            resolve("Hello World")
        }
        promise.then { value ->
            assertEquals("Hello World", value)
        }
    }


    @Test
    fun testPromise2() {
        val fn = CallCounter()

        var rs : ResolveFunction<String>? = null

        val promise = Promise.resolve("Hello World")

        promise.then { fn() }
        promise.then { fn() }

        rs?.let { it("Hello World") }

        assertEquals(2, fn.calls)
    }

}
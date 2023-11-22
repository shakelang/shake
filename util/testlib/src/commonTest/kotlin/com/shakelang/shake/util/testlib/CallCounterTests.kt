package com.shakelang.shake.util.testlib

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

@Suppress("unused")
class CallCounterTests : FreeSpec({

    "call counter" {
        val counter = CallCounter()
        counter.calls shouldBe 0
        counter.call()
        counter.calls shouldBe 1
        counter.call()
        counter.calls shouldBe 2

        1.rangeTo(100).forEach { _ -> counter.call() }
        counter.calls shouldBe 102

        counter.reset()

        counter.calls shouldBe 0

        counter.call()
        counter.calls shouldBe 1
    }

    "should be called" {
        val counter = CallCounter()
        counter shouldBeCalled 0
        counter.calls shouldBe 0
        counter.call()
        counter shouldBeCalled 1
        counter.calls shouldBe 1
        counter.call()
        counter shouldBeCalled 2
        counter.calls shouldBe 2

        1.rangeTo(100).forEach { _ -> counter.call() }
        counter shouldBeCalled 102
        counter.calls shouldBe 102

        counter.reset()

        counter shouldBeCalled 0
        counter.calls shouldBe 0

        counter.call()
        counter shouldBeCalled 1
        counter.calls shouldBe 1
    }
})

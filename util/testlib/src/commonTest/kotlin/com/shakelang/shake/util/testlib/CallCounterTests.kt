package com.shakelang.shake.util.testlib

import kotlin.test.Test
import kotlin.test.expect

class CallCounterTests {

    @Test
    fun testCallCounter() {
        val counter = CallCounter()
        expect(0) { counter.calls }
        counter.call()
        expect(1) { counter.calls }
        counter.call()
        expect(2) { counter.calls }

        1.rangeTo(100).forEach { _ -> counter.call() }
        expect(102) { counter.calls }

        counter.reset()

        expect(0) { counter.calls }

        counter.call()
        expect(1) { counter.calls }
    }

    @Test
    fun testShouldBeCalled() {
        val counter = CallCounter()
        counter shouldBeCalled 0
        counter.call()
        counter shouldBeCalled 1
        counter.call()
        counter shouldBeCalled 2

        1.rangeTo(100).forEach { _ -> counter.call() }
        counter shouldBeCalled 102

        counter.reset()

        counter shouldBeCalled 0

        counter.call()
        counter shouldBeCalled 1
    }
}

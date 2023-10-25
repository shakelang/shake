package io.github.shakelang.testlib

class CallCounter {

    var calls = 0
        private set

    fun call() {
        calls++
    }

    fun reset() {
        calls = 0
    }

    operator fun invoke() = call()

}

infix fun CallCounter.shouldBeCalled(times: Int) {
    if (calls != times) throw AssertionError("Expected $times calls, but got $calls")
}
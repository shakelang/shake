package com.shakelang.shake.util.testlib

/**
 * A class to count calls
 *
 * @since 0.1.0
 * @version 0.1.0
 * @see shouldBeCalled
 */
class CallCounter {

    /**
     * The amount of calls already done
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    var calls = 0
        private set

    /**
     * Tell the counter that a call was done
     *
     * @return the amount of calls after this call
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun call() = ++calls

    /**
     * Reset the counter
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    fun reset() {
        calls = 0
    }

    /**
     * Invoke the [call] function
     *
     * @return the amount of calls after this call
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    operator fun invoke() = call()

    /**
     * Check if the counter was called [times] times
     * @param times the amount of calls
     * @throws AssertionError if the counter was not called [times] times
     *
     * @since 0.1.0
     * @version 0.1.0
     */
    infix fun shouldBeCalled(times: Int) {
        if (calls != times) throw AssertionError("Expected $times calls, but got $calls")
    }
}

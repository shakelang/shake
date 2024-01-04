package com.shakelang.shake.cli

/**
 * A function that returns a boolean as a Condition for [RecursiveWhile] loops
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
typealias ConditionFunction = () -> Boolean

/**
 * The body of a [RecursiveWhile] loop
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
typealias RecursiveWhileBody = LoopControls.() -> Unit

class LoopControls(val wBreak: () -> Unit, val wContinue: () -> Unit)

/**
 * Run recursive while loop
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun recursiveWhile(condition: ConditionFunction, body: RecursiveWhileBody) =
    RecursiveWhile(condition, body).checkIteration()

/**
 * Run a recursive do-while loop
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun recursiveDoWhile(condition: ConditionFunction, body: RecursiveWhileBody) =
    RecursiveWhile(condition, body).iteration()

fun recursiveWhile(body: RecursiveWhileBody) = recursiveWhile({ true }, body)
fun recursiveDoWhile(body: RecursiveWhileBody) = recursiveWhile(body)


/**
 * [RecursiveWhile] loop for class for executing [recursiveDoWhile] and [recursiveWhile]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
private class RecursiveWhile(

    /**
     * The condition of the [RecursiveWhile] loop
     */
    val condition: ConditionFunction,

    /**
     * The body of the [RecursiveWhile] loop
     */
    val body: RecursiveWhileBody

) {

    var continueCalled = false
    var breakCalled = false

    val loopControls = LoopControls(
        this::wContinue,
        this::wBreak
    )

    fun wContinue() {
        if (continueCalled) throw RuntimeException("Continue was called multiple times")
        continueCalled = true
    }

    fun wBreak() {
        if (breakCalled) throw RuntimeException("Break was called multiple times")
        continueCalled = true

    }

    /**
     * Execute the iteration if the condition is true
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun checkIteration() {
        if (this.condition()) this.iteration()
    }

    /**
     * Execute the iteration
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun iteration() {
        body(loopControls)
    }

}
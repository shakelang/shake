package io.github.shakelang.parseutils

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
typealias RecursiveWhileBody = (wBreak: () -> Unit, wContinue: () -> Unit) -> Unit


/**
 * Run a recursive while loop
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun recursiveWhile(condition: ConditionFunction, body: RecursiveWhileBody) = RecursiveWhile(condition, body).checkIteration()

/**
 * Run a recursive do-while loop
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
fun recursiveDoWhile(condition: ConditionFunction, body: RecursiveWhileBody)  = RecursiveWhile(condition, body).iteration()


/**
 * [RecursiveWhile] loop for class for executing [recursiveDoWhile] and [recursiveWhile]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
private class RecursiveWhile (

    /**
     * The condition of the [RecursiveWhile] loop
     */
    val condition: ConditionFunction,

    /**
     * The body of the [RecursiveWhile] loop
     */
    val body: RecursiveWhileBody

) {

    /**
     * Execute the iteration if the condition is true
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun checkIteration() {
        if(this.condition()) this.iteration()
    }

    /**
     * Execute the iteration
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun iteration() {
        var executed = false
        body({  }) {
            if(executed) throw Error("Continue must only be called once")
            executed = true
            this.checkIteration()
        }
    }

}
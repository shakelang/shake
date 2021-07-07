package com.github.shakelang.shake.util

typealias Condition = () -> Boolean
typealias RecursiveWhileBody = (wBreak: () -> Unit, wContinue: () -> Unit) -> Unit

fun recursiveWhile(condition: Condition, body: RecursiveWhileBody) = RecursiveWhile(condition, body).checkIteration()
fun recursiveDoWhile(condition: Condition, body: RecursiveWhileBody)  = RecursiveWhile(condition, body).iteration()

private class RecursiveWhile(val condition: Condition, val body: RecursiveWhileBody) {

    fun checkIteration() { if(this.condition()) this.iteration() }
    fun iteration() {
        var executed = false
        body({  }) {
            if(executed) throw Error("Continue must only be called once")
            executed = true
            this.checkIteration()
        }
    }

}
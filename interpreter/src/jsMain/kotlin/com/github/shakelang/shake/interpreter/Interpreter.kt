package com.github.shakelang.shake.interpreter

import com.github.shakelang.shake.interpreter.values.InterpreterValue
import com.github.shakelang.shake.interpreter.values.StringValue
import com.github.shakelang.shake.parser.node.functions.FunctionCallNode


actual fun applyDefaults(interpreter: Interpreter) {
    // TODO implement js defaults
    interpreter.global.getVariables().declare(Variable.finalOf("js", DefaultFunctions.Js(interpreter)))
}


object DefaultFunctions {
    class Js(
        private val interpreter: Interpreter
    ) : InterpreterValue {

        override fun invoke(node: FunctionCallNode, scope: Scope): InterpreterValue {
            if (node.args.size != 1) throw Error("Expecting 1 exactly one argument!")
            val v = this.interpreter.visit(node.args[0], scope)
            if (v !is StringValue) throw Error("Expecting an integer as argument for the exit function")
            return InterpreterValue.of(eval(v.value))
        }

        override val name: String = "Function"
    }
}
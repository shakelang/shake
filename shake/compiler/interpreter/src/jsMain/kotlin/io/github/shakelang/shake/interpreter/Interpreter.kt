package io.github.shakelang.shake.interpreter

import io.github.shakelang.shake.interpreter.values.InterpreterValue
import io.github.shakelang.shake.interpreter.values.StringValue
import io.github.shakelang.shake.parser.node.ShakeAccessDescriber
import io.github.shakelang.shake.parser.node.ShakeVariableType
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionCallNode

import kotlin.reflect.KFunction3

actual fun applyDefaults(interpreter: Interpreter) {
    // TODO implement js defaults
    interpreter.global.getVariables().declare(Variable.finalOf("js", DefaultFunctions.Js(interpreter)))
}

@Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
fun jsEvalCode(contents: String, scope: Scope): InterpreterValue {
    val handler = JsShakeScopeProxyHandler
    val variableNames = scope.scopeVariables.children.filter { contents.contains(it) }
    val argumentNames = mutableSetOf("s", "variable").union(variableNames).toTypedArray()
    val function = js("new Function(argumentNames, contents)")
            as KFunction3<dynamic, (name: String) -> dynamic, Array<out dynamic>, Unit>

    try {
        return InterpreterValue.of(function(
            js("new Proxy(scope, handler)"),
            { scope.scopeVariables[it]?.value?.toJava() },
            *variableNames.map { scope.scopeVariables[it]?.value?.toJava() }.toTypedArray()
        )
        )
    } catch (e: Error) {
        e.printStackTrace()
        TODO()
    }
}


object DefaultFunctions {
    class Js(
        private val interpreter: Interpreter
    ) : InterpreterValue {

        override fun invoke(node: ShakeFunctionCallNode, scope: Scope): InterpreterValue {
            if (node.args.size != 1) throw Error("Expecting 1 exactly one argument!")
            val v = this.interpreter.visit(node.args[0], scope)
            if (v !is StringValue) throw Error("Expecting an integer as argument for the exit function")
            return jsEvalCode(v.value, scope)
        }

        override val name: String = "Function"
    }
}

private object JsShakeScopeProxyHandler {

    fun get(target: Scope, path: String): dynamic = target.scopeVariables[path]?.toJava()

    fun set(target: Scope, path: String, assignment_value: dynamic) = target.scopeVariables[path].let {
        val value = InterpreterValue.of(assignment_value)
        if (it != null) it.value = value
        else target.scopeVariables.declare(
            Variable.create(
                path,
                ShakeVariableType.DYNAMIC,
                ShakeAccessDescriber.PRIVATE,
                false,
                value
            )
        )
    }

}
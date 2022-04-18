package io.github.shakelang.shake.processor.program.code

import io.github.shakelang.shake.parser.node.ShakeTree
import io.github.shakelang.shake.processor.program.*
import io.github.shakelang.shake.processor.program.code.values.ShakeVariableUsage

interface ShakeStatement
interface ShakeValue {
    val type: ShakeType
}

open class ShakeCode(
    open val statements: List<ShakeStatement>
) {

    open class ShakeLateProcessCode (
        open val tree: ShakeTree
    ) : ShakeCode(emptyList()) {
        override lateinit var statements: List<ShakeStatement>
        fun process(program: ShakeScope) {
            statements = tree.process(program)
        }
    }

    companion object {

        fun empty() = ShakeCode(emptyList())

        fun fromTree(tree: ShakeTree): ShakeCode {
            return ShakeLateProcessCode(tree)
        }
    }

}

interface ShakeScope {
    fun get(name: String): ShakeDeclaration?
    fun set(name: String, value: ShakeDeclaration)
    fun getFunctions(name: String): List<ShakeFunction>
    fun setFunctions(name: String, function: ShakeFunction)
    fun getClass(name: String): List<ShakeClass>
    fun setClass(name: String, klass: ShakeClass)
    fun use(name: String) {
        val declaration = get(name)
    }
}

open class ShakeVariableDeclaration : ShakeDeclaration, ShakeStatement {
    val scope: ShakeScope
    final override val name: String
    val initialValue: ShakeValue?
    final override var type: ShakeType

    constructor(scope: ShakeScope,name: String, initialValue: ShakeValue) {
        this.scope = scope
        this.name = name
        this.initialValue = initialValue
        this.type = initialValue.type
    }

    constructor(scope: ShakeScope, name: String, type: ShakeType) {
        this.scope = scope
        this.name = name
        this.initialValue = null
        this.type = type
    }

    constructor(scope: ShakeScope, name: String, type: ShakeType, initialValue: ShakeValue) {
        this.scope = scope
        this.name = name
        this.initialValue = initialValue
        this.type = type
    }

    fun valueCompatible(value: ShakeValue): Boolean {
        return type.compatibleTo(value.type)
    }

    override fun use(scope: ShakeScope): ShakeVariableUsage {
        return ShakeVariableUsage(scope, this)
    }
}

open class ShakeLambdaDeclaration : ShakeExecutable(), ShakeValue {



}

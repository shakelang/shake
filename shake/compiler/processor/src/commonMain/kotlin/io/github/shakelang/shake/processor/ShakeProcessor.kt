package io.github.shakelang.shake.processor

import io.github.shakelang.parseutils.File
import io.github.shakelang.parseutils.characters.streaming.CharacterInputStream
import io.github.shakelang.parseutils.characters.streaming.SourceCharacterInputStream
import io.github.shakelang.shake.lexer.ShakeLexer
import io.github.shakelang.shake.parser.ShakeParser
import io.github.shakelang.shake.parser.node.*
import io.github.shakelang.shake.parser.node.expression.*
import io.github.shakelang.shake.parser.node.factor.ShakeDoubleNode
import io.github.shakelang.shake.parser.node.factor.ShakeIntegerNode
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionCallNode
import io.github.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import io.github.shakelang.shake.parser.node.logical.*
import io.github.shakelang.shake.parser.node.loops.ShakeDoWhileNode
import io.github.shakelang.shake.parser.node.loops.ShakeForNode
import io.github.shakelang.shake.parser.node.loops.ShakeWhileNode
import io.github.shakelang.shake.parser.node.objects.ShakeClassConstructionNode
import io.github.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import io.github.shakelang.shake.parser.node.variables.*
import io.github.shakelang.shake.processor.program.ShakeProject
import io.github.shakelang.shake.processor.program.code.ShakeCode
import io.github.shakelang.shake.processor.program.code.ShakeScope
import io.github.shakelang.shake.processor.program.code.ShakeStatement
import io.github.shakelang.shake.processor.program.code.ShakeValue

class ShakeProcessorOptions {
    var precalculate: Boolean = true
}

abstract class ShakeProcessor <T> {

    val options: ShakeProcessorOptions = ShakeProcessorOptions()

    abstract val src: T

    open fun parseFile(src: String): ShakeTree {

        val file = File(src).contents
        val chars: CharacterInputStream = SourceCharacterInputStream(src, file)

        val lexer = ShakeLexer(chars)
        val tokens = lexer.makeTokens()
        val parser = ShakeParser(tokens)
        return parser.parse()

    }

    abstract fun loadFile(directory: String, src: String)
    fun <O> generate(f: (T) -> O): O {
        return f(src)
    }


}
open class ShakePackageBasedProcessor : ShakeProcessor<ShakeProject>() {

    open val project = ShakeProject()
    override val src: ShakeProject
        get() = project

    open fun loadSynthetic(src: String, contents: ShakeTree) {
        val reformatted = src.replace("\\", "/").replace(".", "/")
        project.putFile(reformatted.split("/").toTypedArray(), contents)
    }

    override fun loadFile(directory: String, src: String) {

        val reformatted = src.replace("\\", "/").replace(".", "/")
        val contents = parseFile("$directory/$src")
        project.putFile(reformatted.split("/").toTypedArray(), contents)

    }
}

open class ShakeCodeProcessor {

    fun visit(scope: ShakeScope, value: ShakeValuedNodeImpl): ShakeValue {

    }

    fun visit(scope: ShakeScope, statement: ShakeStatement): ShakeStatement {

    }

    fun visitTree(scope: ShakeScope, t: ShakeTree): Any {
        return ShakeCode(t.children.map {
            visit(scope, it)
        })
    }

    fun visitDoubleNode(scope: ShakeScope, n: ShakeDoubleNode): Any {
        TODO("Not yet implemented")
    }

    fun visitIntegerNode(scope: ShakeScope, n: ShakeIntegerNode): Any {
        TODO("Not yet implemented")
    }

    fun visitAddNode(scope: ShakeScope, n: ShakeAddNode): Any {
        TODO("Not yet implemented")
    }

    fun visitSubNode(scope: ShakeScope, n: ShakeSubNode): Any {
        TODO("Not yet implemented")
    }

    fun visitMulNode(scope: ShakeScope, n: ShakeMulNode): Any {
        TODO("Not yet implemented")
    }

    fun visitDivNode(scope: ShakeScope, n: ShakeDivNode): Any {
        TODO("Not yet implemented")
    }

    fun visitModNode(scope: ShakeScope, n: ShakeModNode): Any {
        TODO("Not yet implemented")
    }

    fun visitPowNode(scope: ShakeScope, n: ShakePowNode): Any {
        TODO("Not yet implemented")
    }

    fun visitVariableDeclarationNode(scope: ShakeScope, n: ShakeVariableDeclarationNode): Any {
        TODO("Not yet implemented")
    }

    fun visitVariableAssignmentNode(scope: ShakeScope, n: ShakeVariableAssignmentNode): Any {
        TODO("Not yet implemented")
    }

    fun visitVariableAddAssignmentNode(scope: ShakeScope, n: ShakeVariableAddAssignmentNode): Any {
        TODO("Not yet implemented")
    }

    fun visitVariableSubAssignmentNode(scope: ShakeScope, n: ShakeVariableSubAssignmentNode): Any {
        TODO("Not yet implemented")
    }

    fun visitVariableMulAssignmentNode(scope: ShakeScope, n: ShakeVariableMulAssignmentNode): Any {
        TODO("Not yet implemented")
    }

    fun visitVariableDivAssignmentNode(scope: ShakeScope, n: ShakeVariableDivAssignmentNode): Any {
        TODO("Not yet implemented")
    }

    fun visitVariableModAssignmentNode(scope: ShakeScope, n: ShakeVariableModAssignmentNode): Any {
        TODO("Not yet implemented")
    }

    fun visitVariablePowAssignmentNode(scope: ShakeScope, n: ShakeVariablePowAssignmentNode): Any {
        TODO("Not yet implemented")
    }

    fun visitVariableIncreaseNode(scope: ShakeScope, n: ShakeVariableIncreaseNode): Any {
        TODO("Not yet implemented")
    }

    fun visitVariableDecreaseNode(scope: ShakeScope, n: ShakeVariableDecreaseNode): Any {
        TODO("Not yet implemented")
    }

    fun visitVariableUsageNode(scope: ShakeScope, n: ShakeVariableUsageNode): Any {
        TODO("Not yet implemented")
    }

    fun visitEqEqualsNode(scope: ShakeScope, n: ShakeLogicalEqEqualsNode): Any {
        TODO("Not yet implemented")
    }

    fun visitBiggerEqualsNode(scope: ShakeScope, n: ShakeLogicalBiggerEqualsNode): Any {
        TODO("Not yet implemented")
    }

    fun visitSmallerEqualsNode(scope: ShakeScope, n: ShakeLogicalSmallerEqualsNode): Any {
        TODO("Not yet implemented")
    }

    fun visitBiggerNode(scope: ShakeScope, n: ShakeLogicalBiggerNode): Any {
        TODO("Not yet implemented")
    }

    fun visitSmallerNode(scope: ShakeScope, n: ShakeLogicalSmallerNode): Any {
        TODO("Not yet implemented")
    }

    fun visitLogicalAndNode(scope: ShakeScope, n: ShakeLogicalAndNode): Any {
        TODO("Not yet implemented")
    }

    fun visitLogicalOrNode(scope: ShakeScope, n: ShakeLogicalOrNode): Any {
        TODO("Not yet implemented")
    }

    fun visitLogicalXOrNode(scope: ShakeScope, n: ShakeLogicalXOrNode): Any {
        TODO("Not yet implemented")
    }

    fun visitWhileNode(scope: ShakeScope, n: ShakeWhileNode): Any {
        TODO("Not yet implemented")
    }

    fun visitDoWhileNode(scope: ShakeScope, n: ShakeDoWhileNode): Any {
        TODO("Not yet implemented")
    }

    fun visitForNode(scope: ShakeScope, n: ShakeForNode): Any {
        TODO("Not yet implemented")
    }

    fun visitIfNode(scope: ShakeScope, n: ShakeIfNode): Any {
        TODO("Not yet implemented")
    }

    fun visitFunctionDeclarationNode(scope: ShakeScope, n: ShakeFunctionDeclarationNode): Any {
        TODO("Not yet implemented")
    }

    fun visitClassDeclarationNode(scope: ShakeScope, n: ShakeClassDeclarationNode): Any {
        TODO("Not yet implemented")
    }

    fun visitClassConstruction(scope: ShakeScope, n: ShakeClassConstructionNode): Any {
        TODO("Not yet implemented")
    }

    fun visitFunctionCallNode(scope: ShakeScope, n: ShakeFunctionCallNode): Any {
        TODO("Not yet implemented")
    }

    fun visitIdentifierNode(scope: ShakeScope, n: ShakeIdentifierNode): Any {
        TODO("Not yet implemented")
    }

    fun visitLogicalTrueNode(scope: ShakeScope, n: ShakeLogicalTrueNode): Any {
        TODO("Not yet implemented")
    }

    fun visitLogicalFalseNode(scope: ShakeScope, n: ShakeLogicalFalseNode): Any {
        TODO("Not yet implemented")
    }

    fun visitImportNode(scope: ShakeScope, n: ShakeImportNode): Any {
        TODO("Not yet implemented")
    }

    fun visitCastNode(scope: ShakeScope, n: ShakeCastNode): Any {
        TODO("Not yet implemented")
    }

}
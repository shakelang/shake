@file:Suppress("unused")
package io.github.shakelang.shake.shasambly.shasp.parser.nodes

interface ShasPNode
interface ShasPStatement : ShasPNode

class ShasPProgram (val children: Array<ShasPProgChild>) : ShasPNode
interface ShasPProgChild : ShasPNode
open class ShasPVariableDeclaration (val name: String, val type: ShasPType, val value: ShasPValuedNode? = null) : ShasPProgChild, ShasPStatement
open class ShasPFunctionDeclaration(
    val type: ShasPType,
    val name: String,
    val args: Array<ShasPArgument>,
    val body: ShasPCode
) : ShasPProgChild
open class ShasPCode(val children: Array<ShasPStatement>) : ShasPStatement
open class ShasPArgument (val name: String, val type: ShasPType)

interface ShasPValuedNode : ShasPNode
interface ShasPValuedStatement : ShasPStatement, ShasPValuedNode
abstract class ExpressionNode (val left: ShasPValuedNode, val right: ShasPValuedNode) : ShasPValuedNode
open class ShasPAdd (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right)
open class ShasPSub (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right)
open class ShasPMul (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right)
open class ShasPDiv (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right)
open class ShasPMod (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right)
open class ShasPAnd (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right)
open class ShasPOr (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right)
open class ShasPXor (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right)
open class ShasPShiftLeft (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right)
open class ShasPShiftRight (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right)
open class ShasPEqual (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right)
open class ShasPNotEqual (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right)
open class ShasPLess (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right)
open class ShasPGreater (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right)
open class ShasPLessEqual (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right)
open class ShasPGreaterEqual (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right)
open class ShasPNot (val child: ShasPValuedNode) : ShasPValuedNode
open class ShasPLogicalTrueNode : ShasPValuedNode
open class ShasPLogicalFalseNode : ShasPValuedNode
open class ShasPCast (val type: ShasPType, val child: ShasPValuedNode) : ShasPValuedNode

open class ShasPPosNode (val child: ShasPValuedNode) : ShasPValuedNode
open class ShasPNegNode (val child: ShasPValuedNode) : ShasPValuedNode

open class ShasPIntegerLiteral (val value: String) : ShasPValuedNode
open class ShasPDoubleLiteral (val value: String) : ShasPValuedNode
open class ShasPStringLiteral (val value: String) : ShasPValuedNode
open class ShasPCharLiteral (val value: Char) : ShasPValuedNode
open class ShasPIdentifier (val name: String) : ShasPValuedNode
open class ShasPFunctionCall (val name: String, val args: Array<ShasPValuedNode>) : ShasPValuedStatement

open class ShasPVariableAssignment (val name: String, val value: ShasPValuedNode) : ShasPValuedStatement
open class ShasPVariableAddAssignment (val name: String, val value: ShasPValuedNode) : ShasPValuedStatement
open class ShasPVariableSubAssignment (val name: String, val value: ShasPValuedNode) : ShasPValuedStatement
open class ShasPVariableMulAssignment (val name: String, val value: ShasPValuedNode) : ShasPValuedStatement
open class ShasPVariableDivAssignment (val name: String, val value: ShasPValuedNode) : ShasPValuedStatement
open class ShasPVariableModAssignment (val name: String, val value: ShasPValuedNode) : ShasPValuedStatement

open class ShasPIf (val condition: ShasPValuedNode, val then: ShasPStatement, val orElse: ShasPStatement? = null) : ShasPValuedStatement
open class ShasPWhile (val condition: ShasPValuedNode, val body: ShasPStatement) : ShasPValuedStatement
open class ShasPDoWhile (val condition: ShasPValuedNode, val body: ShasPStatement) : ShasPValuedStatement
open class ShasPFor (val init: ShasPStatement, val condition: ShasPValuedNode, val step: ShasPStatement, val body: ShasPStatement) : ShasPValuedStatement
open class ShasPForEach (val name: String, val collection: ShasPValuedNode, val body: ShasPStatement) : ShasPValuedStatement
open class ShasPBreak : ShasPValuedStatement
open class ShasPContinue : ShasPValuedStatement
open class ShasPReturn (val value: ShasPValuedNode?) : ShasPValuedStatement

open class ShasPType (val name: String, val byteSize: Int) {
    companion object {
        val BYTE = ShasPType("byte", 1)
        val SHORT = ShasPType("short", 2)
        val INT = ShasPType("int", 4)
        val LONG = ShasPType("long", 8)
        val FLOAT = ShasPType("float", 4)
        val DOUBLE = ShasPType("double", 8)
        val UBYTE = ShasPType("unsigned byte", 1)
        val USHORT = ShasPType("unsigned short", 2)
        val UINT = ShasPType("unsigned int", 4)
        val ULONG = ShasPType("unsigned long", 8)
        val BOOLEAN = ShasPType("boolean", 1)
        val CHAR = ShasPType("char", 2)
    }
}
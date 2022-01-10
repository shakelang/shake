package io.github.shakelang.shake.shasambly.shasp.parser.nodes

interface ShasPNode
interface ShasPStatement : ShasPNode

class ShasPProgram (val children: Array<ShasPProgChild>) : ShasPNode
interface ShasPProgChild : ShasPNode
open class ShasPFunctionDeclaration(
    val type: ShasPType,
    val name: String,
    val args: Array<ShasPArgument>,
    val body: ShasPStatement
) : ShasPProgChild
open class ShasPCode (val children: List<ShasPStatement>) : ShasPStatement
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
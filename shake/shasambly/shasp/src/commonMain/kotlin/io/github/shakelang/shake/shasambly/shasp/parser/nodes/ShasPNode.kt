@file:Suppress("unused")
package io.github.shakelang.shake.shasambly.shasp.parser.nodes

import io.github.shakelang.shason.json

interface ShasPNode {
    fun toJson(): Map<String, Any?>?
    fun toJsonString(): String = json.stringify(toJson())
    fun toJsonString(indent: Int) = json.stringify(toJson(), indent)
}
interface ShasPStatement : ShasPNode

class ShasPProgram (val children: Array<ShasPProgChild>) : ShasPNode {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "program",
            "children" to children.map { it.toJson() }
        )
    }
}
interface ShasPProgChild : ShasPNode
open class ShasPVariableDeclaration (val name: String, val type: ShasPType, val value: ShasPValuedNode? = null) : ShasPProgChild, ShasPStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "variable_declaration",
            "name" to name,
            "type" to type.toJson(),
            "value" to value?.toJson()
        )
    }
}
open class ShasPFunctionDeclaration(
    val type: ShasPType,
    val name: String,
    val args: Array<ShasPArgument>,
    val body: ShasPCode
) : ShasPProgChild {
    override fun toJson(): Map<String, Any> {
        return mapOf(
            "type" to "function_declaration",
            "type" to type.toJson(),
            "name" to name,
            "args" to args.map { it.toJson() },
            "body" to body.toJson()
        )
    }
}
open class ShasPCode(val children: Array<ShasPStatement>) : ShasPStatement {
    override fun toJson(): Map<String, Any> {
        return mapOf(
            "type" to "code",
            "children" to children.map { it.toJson() }
        )
    }
}
open class ShasPArgument (val name: String, val type: ShasPType) {
     fun toJson(): Map<String, Any> {
        return mapOf(
            "name" to name,
            "type" to type.toJson()
        )
    }

    fun toJsonString(): String = json.stringify(toJson())
    fun toJsonString(indent: Int) = json.stringify(toJson(), indent)
}

interface ShasPValuedNode : ShasPNode
interface ShasPValuedStatement : ShasPStatement, ShasPValuedNode
abstract class ExpressionNode (val left: ShasPValuedNode, val right: ShasPValuedNode) : ShasPValuedNode
open class ShasPAdd (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right) {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "add",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}
open class ShasPSub (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right) {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "sub",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}
open class ShasPMul (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right) {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "mul",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}
open class ShasPDiv (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right) {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "div",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}
open class ShasPMod (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right) {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "mod",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}
open class ShasPAnd (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right) {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "and",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}
open class ShasPOr (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right) {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "or",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}
open class ShasPXor (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right) {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "xor",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}
open class ShasPShiftLeft (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right) {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "shift_left",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}
open class ShasPShiftRight (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right) {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "shift_right",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}
open class ShasPEqual (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right) {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "equal",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}
open class ShasPNotEqual (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right) {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "not_equal",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}
open class ShasPLess (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right) {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "less",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}
open class ShasPGreater (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right) {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "greater",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}
open class ShasPLessEqual (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right) {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "less_equal",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}
open class ShasPGreaterEqual (left: ShasPValuedNode, right: ShasPValuedNode) : ExpressionNode(left, right) {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "greater_equal",
            "left" to left.toJson(),
            "right" to right.toJson()
        )
    }
}
open class ShasPNot (val child: ShasPValuedNode) : ShasPValuedNode {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "not",
            "child" to child.toJson()
        )
    }
}
open class ShasPLogicalTrueNode : ShasPValuedNode {
    override fun toJson(): Map<String, Any> {
        return mapOf(
            "type" to "logical_true"
        )
    }
}
open class ShasPLogicalFalseNode : ShasPValuedNode {
    override fun toJson(): Map<String, Any> {
        return mapOf(
            "type" to "logical_false"
        )
    }
}
open class ShasPCast (val type: ShasPType, val child: ShasPValuedNode) : ShasPValuedNode {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "cast",
            "type" to type.toJson(),
            "child" to child.toJson()
        )
    }
}

open class ShasPPosNode (val child: ShasPValuedNode) : ShasPValuedNode {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "pos",
            "child" to child.toJson()
        )
    }
}
open class ShasPNegNode (val child: ShasPValuedNode) : ShasPValuedNode {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "neg",
            "child" to child.toJson()
        )
    }
}

open class ShasPIntegerLiteral (val value: String) : ShasPValuedNode {
    override fun toJson(): Map<String, Any> {
        return mapOf(
            "type" to "integer_literal",
            "value" to value
        )
    }
}
open class ShasPDoubleLiteral (val value: String) : ShasPValuedNode {
    override fun toJson(): Map<String, Any> {
        return mapOf(
            "type" to "double_literal",
            "value" to value
        )
    }
}
open class ShasPStringLiteral (val value: String) : ShasPValuedNode {
    override fun toJson(): Map<String, Any> {
        return mapOf(
            "type" to "string_literal",
            "value" to value
        )
    }
}
open class ShasPCharLiteral (val value: Char) : ShasPValuedNode {
    override fun toJson(): Map<String, Any> {
        return mapOf(
            "type" to "char_literal",
            "value" to value
        )
    }
}
open class ShasPIdentifier (val name: String) : ShasPValuedNode {
    override fun toJson(): Map<String, Any> {
        return mapOf(
            "type" to "identifier",
            "name" to name
        )
    }
}
open class ShasPFunctionCall (val name: String, val args: Array<ShasPValuedNode>) : ShasPValuedStatement {
    override fun toJson(): Map<String, Any> {
        return mapOf(
            "type" to "function_call",
            "name" to name,
            "args" to args.map { it.toJson() }
        )
    }
}

open class ShasPVariableAssignment (val name: String, val value: ShasPValuedNode) : ShasPValuedStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "variable_assignment",
            "name" to name,
            "value" to value.toJson()
        )
    }
}
open class ShasPVariableAddAssignment (val name: String, val value: ShasPValuedNode) : ShasPValuedStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "variable_add_assignment",
            "name" to name,
            "value" to value.toJson()
        )
    }
}
open class ShasPVariableSubAssignment (val name: String, val value: ShasPValuedNode) : ShasPValuedStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "variable_sub_assignment",
            "name" to name,
            "value" to value.toJson()
        )
    }
}
open class ShasPVariableMulAssignment (val name: String, val value: ShasPValuedNode) : ShasPValuedStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "variable_mul_assignment",
            "name" to name,
            "value" to value.toJson()
        )
    }
}
open class ShasPVariableDivAssignment (val name: String, val value: ShasPValuedNode) : ShasPValuedStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "variable_div_assignment",
            "name" to name,
            "value" to value.toJson()
        )
    }
}
open class ShasPVariableModAssignment (val name: String, val value: ShasPValuedNode) : ShasPValuedStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "variable_mod_assignment",
            "name" to name,
            "value" to value.toJson()
        )
    }
}
open class ShasPVariableIncr (val name: String) : ShasPValuedStatement {
    override fun toJson(): Map<String, Any> {
        return mapOf(
            "type" to "variable_incr",
            "name" to name
        )
    }
}
open class ShasPVariableDecr (val name: String) : ShasPValuedStatement {
    override fun toJson(): Map<String, Any> {
        return mapOf(
            "type" to "variable_decr",
            "name" to name
        )
    }
}


open class ShasPIf (val condition: ShasPValuedNode, val then: ShasPCode, val orElse: ShasPCode? = null) : ShasPValuedStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "if",
            "condition" to condition.toJson(),
            "then" to then.toJson(),
            "orElse" to orElse?.toJson()
        )
    }
}
open class ShasPWhile (val condition: ShasPValuedNode, val body: ShasPCode) : ShasPValuedStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "while",
            "condition" to condition.toJson(),
            "body" to body.toJson()
        )
    }
}
open class ShasPDoWhile (val condition: ShasPValuedNode, val body: ShasPCode) : ShasPValuedStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "do_while",
            "condition" to condition.toJson(),
            "body" to body.toJson()
        )
    }
}
open class ShasPFor (val init: ShasPStatement, val condition: ShasPValuedNode, val step: ShasPStatement, val body: ShasPCode) : ShasPValuedStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "for",
            "init" to init.toJson(),
            "condition" to condition.toJson(),
            "step" to step.toJson(),
            "body" to body.toJson()
        )
    }
}
open class ShasPForEach (val name: String, val collection: ShasPValuedNode, val body: ShasPCode) : ShasPValuedStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "for_each",
            "name" to name,
            "collection" to collection.toJson(),
            "body" to body.toJson()
        )
    }
}
open class ShasPBreak : ShasPValuedStatement {
    override fun toJson(): Map<String, Any> {
        return mapOf(
            "type" to "break"
        )
    }
}
open class ShasPContinue : ShasPValuedStatement {
    override fun toJson(): Map<String, Any> {
        return mapOf(
            "type" to "continue"
        )
    }
}
open class ShasPReturn (val value: ShasPValuedNode?) : ShasPValuedStatement {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "return",
            "value" to value?.toJson()
        )
    }
}

open class ShasPArrayInitializer (
    val type: ShasPType.ShasPArrayType,
    val values: List<ShasPValuedNode>? = null
) : ShasPValuedNode {
    override fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "array_initializer",
            "type" to type.toJson(),
            "values" to values?.map { it.toJson() }
        )
    }
}

open class ShasPType (val name: String, val byteSize: Int) {

    open fun toJson(): Map<String, Any?> {
        return mapOf(
            "type" to "type",
            "name" to name,
            "byteSize" to byteSize
        )
    }

    open fun toJsonString(): String = json.stringify(toJson())
    open fun toJsonString(indent: Int) = json.stringify(toJson(), indent)

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
        val UNKNOWN_INTEGER_LITERAL = ShasPType("int", 4)
        val UNKNOWN_DOUBLE_LITERAL = ShasPType("double", 8)

        fun arrayOf(type: ShasPType, size: ShasPValuedNode? = null): ShasPArrayType {
            return ShasPArrayType("$type[]", 4, type, size)
        }
    }

    class ShasPArrayType(
        name: String,
        byteSize: Int,
        val subType: ShasPType,
        val size: ShasPValuedNode? = null
    ) : ShasPType(name, byteSize) {

        override fun toJson(): Map<String, Any?> {
            return mapOf(
                "type" to "sub_typed_type",
                "name" to name,
                "byteSize" to byteSize,
                "subType" to subType.toJson(),
                "size" to size?.toJson()
            )
        }

        override fun equals(other: Any?): Boolean {
            return other is ShasPArrayType && other.subType == subType && other.size == size
        }

        override fun hashCode(): Int {
            var result = subType.hashCode()
            result = 31 * result + (size?.hashCode() ?: 0)
            return result
        }

    }
}
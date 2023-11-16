package com.shakelang.shake.parser.node.functions

import com.shakelang.shake.parser.node.*
import com.shakelang.shake.util.parseutils.characters.position.PositionMap
import kotlin.jvm.JvmOverloads


class ShakeFunctionParameterNode @JvmOverloads constructor(
    map: PositionMap,
    val name: String,
    val type: ShakeVariableType = ShakeVariableType.DYNAMIC,
    val defaultValue: ShakeValuedNode? = null
) : ShakeNodeImpl(map) {

    override fun toJson(): Map<String, *> =
        mapOf("name" to nodeName, "argument_name" to name, "type" to type.toString())

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeFunctionParameterNode) return false

        if (name != other.name) return false
        if (type != other.type) return false
        if (defaultValue != other.defaultValue) return false

        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeFunctionParameterNode) return false

        if (name != other.name) return false
        if (type != other.type) return false
        if (defaultValue != other.defaultValue) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + (defaultValue?.hashCode() ?: 0)
        return result
    }
}

class ShakeFunctionDeclarationNode(
    map: PositionMap,
    val expandedType: ShakeVariableType?,
    val name: String,
    val body: ShakeBlockNode?,
    val args: Array<ShakeFunctionParameterNode>,
    val type: ShakeVariableType,
    val access: ShakeAccessDescriber?,
    val isStatic: Boolean,
    val isFinal: Boolean,
    val isAbstract: Boolean,
    val isOverride: Boolean,
    val isSynchronized: Boolean,
    val isNative: Boolean,
    val isOperator: Boolean,
    val isInline: Boolean
) : ShakeFileChildNodeImpl(map) {

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to nodeName,
            "function_name" to name,
            "args" to args.map { it.json },
            "body" to body?.json,
            "type" to type.toString(),
            "access" to access.toString(),
            "is_static" to isStatic,
            "is_final" to isFinal
        )

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeFunctionDeclarationNode) return false

        if (expandedType != other.expandedType) return false
        if (name != other.name) return false
        if (body != other.body) return false
        if (!args.contentEquals(other.args)) return false
        if (type != other.type) return false
        if (access != other.access) return false
        if (isStatic != other.isStatic) return false
        if (isFinal != other.isFinal) return false
        if (isAbstract != other.isAbstract) return false
        if (isOverride != other.isOverride) return false
        if (isSynchronized != other.isSynchronized) return false
        if (isNative != other.isNative) return false
        if (isOperator != other.isOperator) return false
        if (isInline != other.isInline) return false

        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeFunctionDeclarationNode) return false

        if (expandedType != other.expandedType) return false
        if (name != other.name) return false
        if (body != other.body) return false
        if (!args.contentEquals(other.args)) return false
        if (type != other.type) return false
        if (access != other.access) return false
        if (isStatic != other.isStatic) return false
        if (isFinal != other.isFinal) return false
        if (isAbstract != other.isAbstract) return false
        if (isOverride != other.isOverride) return false
        if (isSynchronized != other.isSynchronized) return false
        if (isNative != other.isNative) return false
        if (isOperator != other.isOperator) return false
        if (isInline != other.isInline) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (expandedType?.hashCode() ?: 0)
        result = 31 * result + name.hashCode()
        result = 31 * result + (body?.hashCode() ?: 0)
        result = 31 * result + args.contentHashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + (access?.hashCode() ?: 0)
        result = 31 * result + isStatic.hashCode()
        result = 31 * result + isFinal.hashCode()
        result = 31 * result + isAbstract.hashCode()
        result = 31 * result + isOverride.hashCode()
        result = 31 * result + isSynchronized.hashCode()
        result = 31 * result + isNative.hashCode()
        result = 31 * result + isOperator.hashCode()
        result = 31 * result + isInline.hashCode()
        return result
    }
}

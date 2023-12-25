package com.shakelang.shake.parser.node.variables

import com.shakelang.shake.parser.node.*
import com.shakelang.util.parseutils.characters.position.PositionMap
import kotlin.jvm.JvmOverloads

class ShakeVariableDeclarationNode @JvmOverloads constructor(
    map: PositionMap,
    val expandedType: ShakeVariableType?,
    val name: String,
    val type: ShakeVariableType = ShakeVariableType.DYNAMIC,
    val value: ShakeValuedNode? = null,
    val access: ShakeAccessDescriber = ShakeAccessDescriber.PACKAGE,
    val isStatic: Boolean,
    val isFinal: Boolean,
    val isNative: Boolean,
    val isConst: Boolean,
    val isOverride: Boolean,
    val isInline: Boolean
) : ShakeValuedStatementNodeImpl(map), ShakeFileChildNode {

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to nodeName,
            "variable_name" to name,
            "type" to type.json,
            "access" to access.name.lowercase(),
            "assignment" to this.value?.json,
            "is_static" to isStatic,
            "is_final" to isFinal
        )

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeVariableDeclarationNode) return false
        if (expandedType != other.expandedType) return false
        if (name != other.name) return false
        if (type != other.type) return false
        if (value != other.value) return false
        if (access != other.access) return false
        if (isStatic != other.isStatic) return false
        if (isFinal != other.isFinal) return false
        if (isNative != other.isNative) return false
        if (isConst != other.isConst) return false
        if (isOverride != other.isOverride) return false
        return isInline == other.isInline
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeVariableDeclarationNode) return false
        if (expandedType != other.expandedType) return false
        if (name != other.name) return false
        if (type != other.type) return false
        if (value != other.value) return false
        if (access != other.access) return false
        if (isStatic != other.isStatic) return false
        if (isFinal != other.isFinal) return false
        if (isNative != other.isNative) return false
        if (isConst != other.isConst) return false
        if (isOverride != other.isOverride) return false
        if (isInline != other.isInline) return false
        if (map != other.map) return false
        return true
    }

    override fun hashCode(): Int {
        var result = expandedType?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + (value?.hashCode() ?: 0)
        result = 31 * result + access.hashCode()
        result = 31 * result + isStatic.hashCode()
        result = 31 * result + isFinal.hashCode()
        result = 31 * result + isNative.hashCode()
        result = 31 * result + isConst.hashCode()
        result = 31 * result + isOverride.hashCode()
        result = 31 * result + isInline.hashCode()
        result = 31 * result + map.hashCode()
        return result
    }
}

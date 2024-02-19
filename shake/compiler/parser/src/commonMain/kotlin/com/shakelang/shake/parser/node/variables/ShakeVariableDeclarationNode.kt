package com.shakelang.shake.parser.node.variables

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.shake.parser.node.*
import com.shakelang.util.parseutils.characters.position.PositionMap
import kotlin.jvm.JvmOverloads

class ShakeVariableDeclarationNode @JvmOverloads constructor(
    map: PositionMap,
    val expandedType: ShakeVariableType?,
    val name: String,
    val type: ShakeVariableType = ShakeVariableType.DYNAMIC,
    val value: ShakeValuedNode? = null,
    val access: ShakeAccessDescriber,
    val varToken: ShakeToken,
) : ShakeValuedStatementNodeImpl(map), ShakeFileChildNode {

    val isVar: Boolean get() = varToken.type == ShakeTokenType.KEYWORD_VAR
    val isVal: Boolean get() = varToken.type == ShakeTokenType.KEYWORD_VAL

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to nodeName,
            "variable_name" to name,
            "type" to type.json,
            "access" to access.type.name.lowercase(),
            "assignment" to this.value?.json,
        )

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeVariableDeclarationNode) return false
        if (expandedType != other.expandedType) return false
        if (name != other.name) return false
        if (type != other.type) return false
        if (value != other.value) return false
        if (access != other.access) return false
        return isVal == other.isVal
    }

    override fun equals(other: Any?): Boolean {
        return equalsIgnorePosition(other)
    }

    override fun hashCode(): Int {
        var result = expandedType?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + (value?.hashCode() ?: 0)
        result = 31 * result + access.hashCode()
        result = 31 * result + map.hashCode()
        result = 31 * result + isVal.hashCode()
        return result
    }
}

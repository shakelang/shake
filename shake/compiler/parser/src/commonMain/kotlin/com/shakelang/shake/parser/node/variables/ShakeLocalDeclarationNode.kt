package com.shakelang.shake.parser.node.variables

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.shake.parser.node.*
import com.shakelang.util.parseutils.characters.position.PositionMap

@Suppress("MemberVisibilityCanBePrivate")
class ShakeLocalDeclarationNode(
    map: PositionMap,
    val nameToken: ShakeToken,
    val colonToken: ShakeToken?,
    val type: ShakeVariableType?,
    val assignmentToken: ShakeToken?,
    val value: ShakeValuedNode?,
    val varToken: ShakeToken,
) : ShakeValuedStatementNodeImpl(map), ShakeFileChildNode {

    val name: String get() = nameToken.value!!

    val isVar: Boolean get() = varToken.type == ShakeTokenType.KEYWORD_VAR
    val isVal: Boolean get() = varToken.type == ShakeTokenType.KEYWORD_VAL

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to nodeName,
            "variable_name" to name,
            "type" to type?.json,
            "assignment" to this.value?.json,
        )

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeLocalDeclarationNode) return false
        if (name != other.name) return false
        if (type != other.type) return false
        if (value != other.value) return false
        return isVal == other.isVal
    }

    override fun equals(other: Any?): Boolean {
        return equalsIgnorePosition(other)
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + (value?.hashCode() ?: 0)
        result = 31 * result + map.hashCode()
        result = 31 * result + isVal.hashCode()
        return result
    }
}

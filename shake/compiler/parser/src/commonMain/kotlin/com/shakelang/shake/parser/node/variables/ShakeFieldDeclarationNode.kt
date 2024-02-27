package com.shakelang.shake.parser.node.variables

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.shake.parser.node.*
import com.shakelang.util.parseutils.characters.position.PositionMap

@Suppress("MemberVisibilityCanBePrivate")
class ShakeFieldDeclarationNode(
    map: PositionMap,
    val expandedType: ShakeVariableType?,
    val expandingDot: ShakeToken?,
    val nameToken: ShakeToken,
    val type: ShakeVariableType?,
    val value: ShakeValuedNode?,
    val access: ShakeAccessDescriber,
    val varToken: ShakeToken,
    val staticToken: ShakeToken?,
    val finalToken: ShakeToken?,
    val nativeToken: ShakeToken?,
    val constToken: ShakeToken?,
    val overrideToken: ShakeToken?,
    val inlineToken: ShakeToken?,
) : ShakeValuedStatementNodeImpl(map), ShakeFileChildNode {

    val name: String get() = nameToken.value ?: throw NullPointerException("nameToken.value is null")

    val isVar: Boolean get() = varToken.type == ShakeTokenType.KEYWORD_VAR
    val isVal: Boolean get() = varToken.type == ShakeTokenType.KEYWORD_VAL
    val isStatic: Boolean get() = staticToken != null
    val isFinal: Boolean get() = finalToken != null
    val isNative: Boolean get() = nativeToken != null
    val isConst: Boolean get() = constToken != null
    val isOverride: Boolean get() = overrideToken != null
    val isInline: Boolean get() = inlineToken != null

    val isPublic: Boolean get() = access.type == ShakeAccessDescriber.ShakeAccessDescriberType.PUBLIC
    val isProtected: Boolean get() = access.type == ShakeAccessDescriber.ShakeAccessDescriberType.PROTECTED
    val isPrivate: Boolean get() = access.type == ShakeAccessDescriber.ShakeAccessDescriberType.PRIVATE
    val isPackage: Boolean get() = access.type == ShakeAccessDescriber.ShakeAccessDescriberType.PACKAGE

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to nodeName,
            "variable_name" to name,
            "type" to type?.json,
            "access" to access.type.name.lowercase(),
            "assignment" to this.value?.json,
            "is_static" to isStatic,
            "is_final" to isFinal,
            "is_native" to isNative,
            "is_const" to isConst,
            "is_override" to isOverride,
            "is_inline" to isInline,
        )

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeFieldDeclarationNode) return false
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
        if (isVal != other.isVal) return false
        return isInline == other.isInline
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeFieldDeclarationNode) return false
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
        if (isVal != other.isVal) return false
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

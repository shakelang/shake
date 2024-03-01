package com.shakelang.shake.parser.node.objects

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.parser.node.ShakeValuedNodeImpl
import com.shakelang.shake.parser.node.misc.ShakeAccessDescriber
import com.shakelang.shake.parser.node.outer.ShakeParameterNode
import com.shakelang.shake.parser.node.statements.ShakeBlockNode
import com.shakelang.util.parseutils.characters.position.PositionMap

@Suppress("unused", "MemberVisibilityCanBePrivate")
class ShakeConstructorDeclarationNode(

    map: PositionMap,
    val nameToken: ShakeToken?,
    val body: ShakeBlockNode,
    val args: Array<ShakeParameterNode>,
    val access: ShakeAccessDescriber,
    val constructorToken: ShakeToken,
    val lparenToken: ShakeToken,
    val rparenToken: ShakeToken,
    val nativeToken: ShakeToken?,
    val synchronizedToken: ShakeToken?,
    val commaTokens: Array<ShakeToken>,

) : ShakeValuedNodeImpl(map) {

    val name: String?
        get() = nameToken?.value

    val isNative: Boolean
        get() = nativeToken != null

    val isSynchronized: Boolean
        get() = synchronizedToken != null

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to nodeName,
            "function_name" to name,
            "args" to args.map { it.json },
            "body" to body.json,
            "access" to access.toString(),
        )

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeConstructorDeclarationNode) return false
        if (name != other.name) return false
        if (body != other.body) return false
        if (!args.contentEquals(other.args)) return false
        if (access != other.access) return false
        if (isNative != other.isNative) return false
        if (isSynchronized != other.isSynchronized) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeConstructorDeclarationNode) return false
        if (name != other.name) return false
        if (body != other.body) return false
        if (!args.contentEquals(other.args)) return false
        if (access != other.access) return false
        if (isNative != other.isNative) return false
        if (isSynchronized != other.isSynchronized) return false
        if (map != other.map) return false
        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + body.hashCode()
        result = 31 * result + args.contentHashCode()
        result = 31 * result + access.hashCode()
        result = 31 * result + map.hashCode()
        result = 31 * result + isNative.hashCode()
        result = 31 * result + isSynchronized.hashCode()
        return result
    }
}

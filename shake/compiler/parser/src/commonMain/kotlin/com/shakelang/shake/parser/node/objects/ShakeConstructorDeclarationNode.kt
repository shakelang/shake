package com.shakelang.shake.parser.node.objects

import com.shakelang.shake.parser.node.ShakeAccessDescriber
import com.shakelang.shake.parser.node.ShakeBlockNode
import com.shakelang.shake.parser.node.ShakeValuedNodeImpl
import com.shakelang.shake.parser.node.functions.ShakeFunctionParameterNode
import com.shakelang.util.parseutils.characters.position.PositionMap
import kotlin.jvm.JvmOverloads

@Suppress("unused")
class ShakeConstructorDeclarationNode
@JvmOverloads constructor(

    map: PositionMap,
    val name: String?,
    val body: ShakeBlockNode,
    val args: Array<ShakeFunctionParameterNode>,
    val access: ShakeAccessDescriber? = ShakeAccessDescriber.PACKAGE,
    val isNative: Boolean,
    val isSynchronized: Boolean,

    ) : ShakeValuedNodeImpl(map) {

    @JvmOverloads
    constructor(
        map: PositionMap,
        body: ShakeBlockNode,
        args: Array<ShakeFunctionParameterNode>,
        access: ShakeAccessDescriber? = ShakeAccessDescriber.PACKAGE,
        isNative: Boolean,
        isSynchronized: Boolean,
    ) : this(map, null, body, args, access, isNative, isSynchronized)

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

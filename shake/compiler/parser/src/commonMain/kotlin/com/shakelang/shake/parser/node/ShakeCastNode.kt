package com.shakelang.shake.parser.node

import com.shakelang.util.parseutils.characters.position.PositionMap
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads

@Suppress("unused")
class ShakeCastNode(map: PositionMap, val value: ShakeValuedNode, val castTarget: CastTarget) :
    ShakeValuedNodeImpl(map) {

    class CastTarget @JvmOverloads constructor(val type: CastTargetType, val subtype: ShakeNamespaceNode? = null) {

        constructor(type: ShakeNamespaceNode?) : this(CastTargetType.OBJECT, type)

        enum class CastTargetType {
            BYTE,
            SHORT,
            INT,
            LONG,
            FLOAT,
            DOUBLE,
            BOOLEAN,
            CHAR,
            STRING,
            OBJECT,
        }

        override fun toString(): String {
            return subtype?.toString() ?: type.toString()
        }

        companion object {
            @JvmField
            val BYTE = CastTarget(CastTargetType.BYTE)

            @JvmField
            val SHORT = CastTarget(CastTargetType.SHORT)

            @JvmField
            val INTEGER = CastTarget(CastTargetType.INT)

            @JvmField
            val LONG = CastTarget(CastTargetType.LONG)

            @JvmField
            val FLOAT = CastTarget(CastTargetType.FLOAT)

            @JvmField
            val DOUBLE = CastTarget(CastTargetType.DOUBLE)

            @JvmField
            val BOOLEAN = CastTarget(CastTargetType.BOOLEAN)

            @JvmField
            val CHAR = CastTarget(CastTargetType.CHAR)

            @JvmField
            val STRING = CastTarget(CastTargetType.STRING)
        }
    }

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to nodeName,
            "value" to value.json,
            "cast_target" to castTarget.toString(),
        )

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeCastNode) return false
        if (value != other.value) return false
        return castTarget == other.castTarget
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeCastNode) return false
        if (value != other.value) return false
        if (castTarget != other.castTarget) return false
        if (map != other.map) return false
        return true
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + castTarget.hashCode()
        result = 31 * result + map.hashCode()
        return result
    }
}

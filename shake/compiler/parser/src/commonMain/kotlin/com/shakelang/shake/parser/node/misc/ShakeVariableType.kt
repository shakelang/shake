package com.shakelang.shake.parser.node.misc

import com.shakelang.shake.lexer.ShakeLexer
import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.parser.node.ShakeNode
import com.shakelang.util.parseutils.characters.position.PositionMap
import com.shakelang.util.parseutils.characters.source.CharacterSource
import com.shakelang.util.parseutils.characters.streaming.SourceCharacterInputStream
import com.shakelang.util.shason.JSON

@Suppress("unused", "MemberVisibilityCanBePrivate")
open class ShakeVariableType(
    val namespace: ShakeNamespaceNode,
    val genericOpen: ShakeToken?,
    val genericClose: ShakeToken?,
    val genericCommas: List<ShakeToken>?,
    val genericTypes: List<ShakeVariableType>?,
) : ShakeNode {

    val isGeneric get() = genericOpen != null

    val type = if (namespace.parent == null) {
        when (namespace.name) {
            "dynamic" -> Type.DYNAMIC
            "byte" -> Type.BYTE
            "short" -> Type.SHORT
            "int" -> Type.INTEGER
            "long" -> Type.LONG
            "float" -> Type.FLOAT
            "double" -> Type.DOUBLE
            "ubyte" -> Type.UNSIGNED_BYTE
            "ushort" -> Type.UNSIGNED_SHORT
            "uint" -> Type.UNSIGNED_INTEGER
            "ulong" -> Type.UNSIGNED_LONG
            "boolean" -> Type.BOOLEAN
            "char" -> Type.CHAR
            "object" -> Type.OBJECT
            "void" -> Type.VOID
            else -> Type.OBJECT
        }
    } else {
        Type.OBJECT
    }

    val name get() = namespace.name

    override fun toString(): String = JSON.stringify(this.json)

    override val json: Map<String, *>
        get() = toJson()

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to nodeName,
            "type" to type.toString(),
        )

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeVariableType) return false
        if (type != other.type) return false

        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeVariableType) return false
        if (type != other.type) return false
        return true
    }

    override fun hashCode(): Int = type.hashCode()

    enum class Type(val tname: String) {
        DYNAMIC("dynamic"),
        BYTE("byte"),
        SHORT("short"),
        INTEGER("int"),
        LONG("long"),
        FLOAT("float"),
        DOUBLE("double"),
        UNSIGNED_BYTE("ubyte"),
        UNSIGNED_SHORT("ushort"),
        UNSIGNED_INTEGER("uint"),
        UNSIGNED_LONG("ulong"),
        BOOLEAN("boolean"),
        CHAR("char"),
        OBJECT("object"),
        VOID("void"),
        ;

        override fun toString(): String = tname
    }

    companion object {
        val IMPLICIT_VOID = ShakeVariableType(
            ShakeNamespaceNode(
                PositionMap.PositionMapImplementation(
                    CharacterSource.Companion.from("", ""),
                    intArrayOf(),
                ),
                ShakeLexer(SourceCharacterInputStream(CharacterSource.from("void", "implicit_void"))).stream().next(),
                null,
                null,
            ),
            null,
            null,
            null,
            null,
        )
    }
}

package com.shakelang.shake.parser.node

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.util.parseutils.characters.position.PositionMap

class
ShakeImportNode(
    map: PositionMap,
    val import: Array<ShakeToken>,
    importToken: ShakeToken,
    dotTokens: Array<ShakeToken>,
) : ShakeFileChildNodeImpl(map) {

    val importStrings: Array<String> = import.map {
        if (it.type == ShakeTokenType.MUL) "*" else it.value!!
    }.toTypedArray()

    override fun toJson(): Map<String, *> =
        mapOf(
            "name" to nodeName,
            "import" to importStrings,
        )

    override fun equalsIgnorePosition(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeImportNode) return false
        if (!import.contentEquals(other.import)) return false
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ShakeImportNode) return false
        if (!import.contentEquals(other.import)) return false
        if (map != other.map) return false
        return true
    }

    override fun hashCode(): Int {
        var result = import.contentHashCode()
        result = 31 * result + map.hashCode()
        return result
    }
}

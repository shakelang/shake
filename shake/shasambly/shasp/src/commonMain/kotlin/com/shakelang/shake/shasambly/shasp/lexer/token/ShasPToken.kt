package com.shakelang.shake.lexer.token

import com.shakelang.shake.shasambly.shasp.lexer.token.ShasPTokenType

/**
 * A token representing a ShasP token.
 */
@Suppress("unused")
class ShasPToken : Token<ShasPTokenType> {

    constructor(type: ShasPTokenType, value: String?, start: Int, end: Int) : super(type, value, start, end)
    constructor(type: ShasPTokenType, start: Int, end: Int) : super(type, start, end)

    override fun toString(): String {
        return if (start == end) if (value != null) {
            "" +
                    "Token{" + "type=" + type + ", value=" + value + ", position=" + start + '}'
        } else {
            "Token{type=$type, position=$start}"
        } else if (value != null) {
            "" +
                    "Token{" + "type=" + type + ", value=" + value + ", start=" + start + ", end=" + end + '}'
        } else {
            "Token{type=$type, position=$start, end=$end}"
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other is Byte) return other == this.type
        if (other == null || other !is ShasPToken) return false
        return type == other.type &&
                value == other.value
    }

    override fun hashCode(): Int = hashAll(type, value)

    private fun hashAll(vararg vals: Any?): Int {
        var res = 0
        for (v in vals) {
            res += v.hashCode()
            res *= 31
        }
        return res
    }
}

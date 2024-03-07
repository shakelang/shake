package com.shakelang.shake.shakespeare.nodes.spec

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.lexer.token.ShakeTokenContext
import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.util.io.streaming.output.bytes.CountingOutputStream
import com.shakelang.util.io.streaming.output.bytes.OutputStream
import com.shakelang.util.parseutils.characters.source.CharacterSource

@Suppress("unused", "MemberVisibilityCanBePrivate")
class NodeContext {

    val ctx = ShakeTokenContext()
    val built = StringBuilder()

    val characterSource = object : CharacterSource {
        override val all: CharArray
            get() = built.toString().toCharArray()
        override val length: Int
            get() = built.length
        override val location: String
            get() = "unknown"

        override fun get(start: Int, end: Int): CharArray {
            return built.subSequence(start, end).toString().toCharArray()
        }
    }

    val stream = CountingOutputStream(
        object : OutputStream() {
            override fun write(b: Int) {
                built.append(b.toChar())
            }
        },
    )

    fun createToken(type: ShakeTokenType, start: Int, end: Int, value: String? = null): ShakeToken {
        return ShakeToken(type, value ?: type.value ?: throw IllegalArgumentException("Value must be set"), start, end, ctx)
    }

    fun createToken(type: ShakeTokenType, end: Int, value: String? = null): ShakeToken {
        val realValue = value ?: type.value ?: throw IllegalArgumentException("Value must be set")
        return createToken(type, end - realValue.length, end, realValue)
    }

    fun createToken(type: ShakeTokenType, start: Long, end: Long, value: String? = null): ShakeToken {
        return createToken(type, start.toInt(), end.toInt(), value)
    }

    fun createToken(type: ShakeTokenType, end: Long, value: String? = null): ShakeToken {
        return createToken(type, end.toInt(), value)
    }

    fun createToken(type: ShakeTokenType, value: String? = null): ShakeToken {
        return createToken(type, stream.count - 1, value)
    }
}

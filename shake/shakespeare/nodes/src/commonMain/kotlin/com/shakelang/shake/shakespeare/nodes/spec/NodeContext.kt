package com.shakelang.shake.shakespeare.nodes.spec

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.lexer.token.ShakeTokenContext
import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.util.io.streaming.output.bytes.CountingOutputStream
import com.shakelang.util.io.streaming.output.bytes.OutputStream
import com.shakelang.util.parseutils.characters.position.PositionMaker
import com.shakelang.util.parseutils.characters.position.PositionMap
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

    val positionMaker = PositionMaker(characterSource)

    val stream = CountingOutputStream(
        object : OutputStream() {
            override fun write(b: Int) {
                val c = b.toChar()
                built.append(c)
                if (c == '\n') {
                    positionMaker.nextLine()
                } else {
                    positionMaker.nextColumn()
                }
            }
        },
    )

    val map: PositionMap get() = positionMaker

    fun createToken(type: ShakeTokenType, start: Int, end: Int, value: String? = null): ShakeToken {
        this.print(value ?: type.value ?: throw IllegalArgumentException("Value must be set"))
        return ShakeToken(type, value ?: type.value ?: throw IllegalArgumentException("Value must be set"), start, end, ctx)
    }

    fun createToken(type: ShakeTokenType, end: Int, value: String? = null): ShakeToken {
        val realValue = value ?: type.value ?: throw IllegalArgumentException("Value must be set")
        return createToken(type, end - realValue.length + 1, end, realValue)
    }

    fun createToken(type: ShakeTokenType, start: Long, end: Long, value: String? = null): ShakeToken {
        return createToken(type, start.toInt(), end.toInt(), value)
    }

    fun createToken(type: ShakeTokenType, end: Long, value: String? = null): ShakeToken {
        return createToken(type, end.toInt(), value)
    }

    fun createToken(type: ShakeTokenType, value: String? = null): ShakeToken {
        val realValue = value ?: type.value ?: throw IllegalArgumentException("Value must be set")
        return createToken(type, stream.count + realValue.length - 1, realValue)
    }

    fun print(str: String) = stream.write(str.encodeToByteArray())
    fun println(str: String) = print("$str\n")
    fun println() = print("\n")
    fun space() = print(" ")
}

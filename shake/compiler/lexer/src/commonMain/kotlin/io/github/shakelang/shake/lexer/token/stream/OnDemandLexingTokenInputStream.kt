package io.github.shakelang.shake.lexer.token.stream

import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.parseutils.characters.streaming.CharacterInputStream
import io.github.shakelang.shake.lexer.LexingBase
import io.github.shakelang.shake.lexer.token.Token

class OnDemandLexingTokenInputStream(inputStream: CharacterInputStream) : LexingBase(inputStream), TokenInputStream {

    val buffer: MutableList<Token> = mutableListOf()
    override lateinit var actual: Token
        private set

    override val source: String
        get() = input.source.location

    override val map: PositionMap
        get() = input.positionMaker

    override var position: Int = -1
        private set


    override fun has(num: Int): Boolean {
        return try {
            fillBuffer(num)
            true
        } catch (e: IndexOutOfBoundsException) {
            false
        }
    }

    override fun skip() {
        fillBuffer(1)
        actual = buffer.removeAt(0)
    }

    override fun skip(amount: Int) {
        for(i in 0 until amount) skip()
    }

    override fun peek(offset: Int): Token {
        fillBuffer(offset)
        return buffer[offset]
    }

    private fun generateToken(): Boolean {
        if (!this.hasNext()) return false
        buffer.add(this.makeToken())
        return true
    }

    private fun fillBuffer(minAmount: Int) {
        while (buffer.size < minAmount) {
            if (!generateToken()) {
                throw IllegalStateException("Not enough tokens left (${buffer.size}/$minAmount)")
            }
        }
    }


}
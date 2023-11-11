package com.shakelang.shake.util.parseutils.lexer.token.stream

import io.github.shakelang.shake.lexer.token.Token
import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap
import io.github.shakelang.shake.util.parseutils.lexer.LexingBase
import io.github.shakelang.shake.util.parseutils.lexer.token.TokenType

open class OnDemandLexingTokenInputStream<TT : TokenType, T : Token<TT>>(
    private val lexingBase: LexingBase<TT, T>
) : LexingBase<TT, T>(lexingBase.input), TokenInputStream<TT, T> {

    override val size: Int get() = throw UnsupportedOperationException()

    val buffer: MutableList<T> = mutableListOf()
    override lateinit var actual: T

    override val source: String
        get() = input.source.location

    override val map: PositionMap
        get() = input.positionMaker

    override var position: Int = -1

    override fun has(num: Int): Boolean {
        return try {
            fillBuffer(num)
            true
        } catch (e: IndexOutOfBoundsException) {
            false
        } catch (e: IllegalStateException) {
            false
        }
    }

    override fun skip() {
        try {
            fillBuffer(1)
        } catch (e: IndexOutOfBoundsException) {
            throw Error("Input already finished", e)
        } catch (e: IllegalStateException) {
            throw Error("Input already finished", e)
        }

        position++
        actual = buffer.removeAt(0)
    }

    override fun skip(amount: Int) {
        for (i in 0 until amount) skip()
    }

    override fun peek(offset: Int): T {
        try {
            fillBuffer(offset)
        } catch (e: IndexOutOfBoundsException) {
            throw Error("Not enough tokens left", e)
        } catch (e: IllegalStateException) {
            throw Error("Not enough tokens left", e)
        }
        return buffer[offset - 1]
    }

    private fun generateToken(): Boolean {
        if (!this.input.hasNext()) return false
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

    override fun makeToken(): T {
        return lexingBase.makeToken()
    }

    override fun toString(): String = "OnDemandLexingTokenInputStream(lexer=$lexingBase)"
}

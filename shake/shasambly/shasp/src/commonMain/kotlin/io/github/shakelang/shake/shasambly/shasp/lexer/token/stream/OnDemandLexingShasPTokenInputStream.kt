package io.github.shakelang.shake.lexer.token.stream

import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.parseutils.characters.streaming.CharacterInputStream
import io.github.shakelang.shake.shasambly.shasp.lexer.ShasPLexingBase
import io.github.shakelang.shake.lexer.token.ShasPToken

class OnDemandLexingShasPTokenInputStream(inputStream: CharacterInputStream) : ShasPLexingBase(inputStream), ShasPTokenInputStream {

    val buffer: MutableList<ShasPToken> = mutableListOf()
    override lateinit var actual: ShasPToken
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
        } catch (e: IllegalStateException) {
            false
        }
    }

    override fun skip() {
        try {
            fillBuffer(1)
        } catch (e: IndexOutOfBoundsException) {
            throw Error("Input already finished")
        } catch (e: IllegalStateException) {
            throw Error("Input already finished")
        }

        position++
        actual = buffer.removeAt(0)
    }

    override fun skip(amount: Int) {
        for(i in 0 until amount) skip()
    }

    override fun peek(offset: Int): ShasPToken {
        try {
            fillBuffer(offset)
        }  catch (e: IndexOutOfBoundsException) {
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


}
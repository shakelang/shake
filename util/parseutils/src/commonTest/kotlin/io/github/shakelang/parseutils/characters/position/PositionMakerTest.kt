package io.github.shakelang.parseutils.characters.position

import io.github.shakelang.parseutils.characters.source.CharacterSource
import kotlin.test.Test
import kotlin.test.assertSame


/**
 * Tests for [PositionMaker]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)w
 */
class PositionMakerTest {

    @Test
    fun testPositionMakerNext() {
        val src = CharacterSource.from(
            "he\nllo",
            "PositionMakerTest#testPositionMakerNext()"
        )

        val maker = PositionMaker(src)

        assertSame(-1, maker.index)
        assertSame(0, maker.column)
        assertSame(1, maker.line)

        maker.nextColumn()

        assertSame(0, maker.index)
        assertSame(1, maker.column)
        assertSame(1, maker.line)

        maker.nextColumn()

        assertSame(1, maker.index)
        assertSame(2, maker.column)
        assertSame(1, maker.line)

        maker.nextColumn()

        assertSame(2, maker.index)
        assertSame(3, maker.column)
        assertSame(1, maker.line)

        maker.nextLine()

        assertSame(3, maker.index)
        assertSame(1, maker.column)
        assertSame(2, maker.line)

        maker.nextColumn()

        assertSame(4, maker.index)
        assertSame(2, maker.column)
        assertSame(2, maker.line)
    }

    @Test
    fun testPositionMakerLineSeparators() {
        val src = CharacterSource.from(
            "he\nllo",
            "PositionMakerTest#testPositionMakerNext()"
        )

        val maker = PositionMaker(src)
        maker.nextColumn()
        maker.nextColumn()
        maker.nextColumn()
        maker.nextLine()
        maker.nextColumn()

        assertSame(1, maker.lineSeparators.size)
        assertSame(3, maker.lineSeparators[0])
    }

}
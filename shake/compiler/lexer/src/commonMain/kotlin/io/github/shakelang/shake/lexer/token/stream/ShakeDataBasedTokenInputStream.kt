package io.github.shakelang.shake.lexer.token.stream

import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.parseutils.lexer.token.stream.DataBasedTokenInputStream
import io.github.shakelang.shake.lexer.token.ShakeToken
import io.github.shakelang.shake.lexer.token.ShakeTokenType

/**
 * A [ShakeDataBasedTokenInputStream] provides the [ShakeToken]s for a Parser. It is
 * created by the [io.github.shakelang.shake.lexer.ShakeLexer]
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
@Suppress("unused")
class ShakeDataBasedTokenInputStream

/**
 * Create a [ShakeDataBasedTokenInputStream] giving the [ShakeDataBasedTokenInputStream.source] and [ShakeDataBasedTokenInputStream.tokenTypes]
 *
 * @param source value for field [ShakeDataBasedTokenInputStream.source] (The source (mostly file) of the tokens)
 * @param tokenTypes value for field [ShakeDataBasedTokenInputStream.tokenTypes] (The tokens that the [ShakeDataBasedTokenInputStream] should give)
 * @param values The values for the tokens that have values in the [ShakeDataBasedTokenInputStream]
 * @param positions The positions of the tokens of the [ShakeDataBasedTokenInputStream]
 * (We just store the end-indexes of each token. We can calculate the start-index out of the token type &amp; value)
 * @param map  value for field [ShakeDataBasedTokenInputStream.map] (The position map of the [ShakeDataBasedTokenInputStream])
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
(

    /**
     * The source (mostly filename) of the [ShakeDataBasedTokenInputStream]
     */
    source: String,
    /**
     * The tokenTypes that are contained in the [ShakeDataBasedTokenInputStream]
     */
    tokenTypes: Array<ShakeTokenType>,
    /**
     * The values for the tokens that have values in the [ShakeDataBasedTokenInputStream]
     */
    values: Array<String>,
    /**
     * The positions of the tokens of the [ShakeDataBasedTokenInputStream]
     * (We just store the end-indexes of each token. We can calculate the start-index out of the token type &amp; value)
     */
    positions: IntArray,
    /**
     * The map for the token-positions
     * We have this map to resolve the column / line of an index. This is useful for error-generation.
     */
    map: PositionMap

) : ShakeTokenInputStream, DataBasedTokenInputStream<ShakeTokenType, ShakeToken> (
    source,
    tokenTypes,
    values,
    positions,
    map,
    { type, value, start, end -> ShakeToken(type, value, start, end) }
)
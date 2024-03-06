package com.shakelang.util.shason.processing

import com.shakelang.util.parseutils.characters.position.PositionMap
import com.shakelang.util.parseutils.lexer.token.Token
import com.shakelang.util.parseutils.lexer.token.TokenFactory
import com.shakelang.util.parseutils.lexer.token.TokenType
import com.shakelang.util.parseutils.lexer.token.stream.FactoryTokenInputStream
import com.shakelang.util.parseutils.lexer.token.stream.TokenInputStream

/**
 * A [JsonToken] is a Token that should be parsed by the [JsonParser] generated by the [JsonLexer]
 * @since 0.1.0
 * @version 0.1.0
 */
@Suppress("unused")
class JsonToken : Token<JsonToken, JsonTokenType, JsonTokenInputStream> {

    /**
     * Constructor for [JsonToken]
     * @since 0.1.0
     * @version 0.1.0
     */
    constructor(
        type: JsonTokenType,
        start: Int,
        end: Int = start,
        value: String? = null,
    ) : super(type, value, start, end)

    /**
     * Constructor for [JsonToken]
     * @since 0.1.0
     * @version 0.1.0
     */
    constructor(type: JsonTokenType, start: Int, value: String? = null) : this(type, start, start, value)

    /**
     * Has the [JsonToken] a value?
     * @since 0.1.0
     * @version 0.1.0
     */
    val hasValue: Boolean get() = this.value != null
}

/**
 * Token-types for the [JsonToken]s
 * @since 0.1.0
 * @version 0.1.0
 */
enum class JsonTokenType(
    private val length: Int,
) : TokenType {

    /**
     * A [LCURL] [JsonTokenType] represents a '{' in the source
     * @since 0.1.0
     * @version 0.1.0
     */
    LCURL(1), // '{'

    /**
     * A [RCURL] [JsonTokenType] represents a '}' in the source
     * @since 0.1.0
     * @version 0.1.0
     */
    RCURL(1), // '}'

    /**
     * A [LSQUARE] [JsonTokenType] represents a '[' in the source
     * @since 0.1.0
     * @version 0.1.0
     */
    LSQUARE(1), // '['

    /**
     * A [RSQUARE] [JsonTokenType] represents a ']' in the source
     * @since 0.1.0
     * @version 0.1.0
     */
    RSQUARE(1), // ']'

    /**
     * A [COMMA] [JsonTokenType] represents a ',' in the source
     * @since 0.1.0
     * @version 0.1.0
     */
    COMMA(1), // ','

    /**
     * A [COLON] [JsonTokenType] represents a ':' in the source
     * @since 0.1.0
     * @version 0.1.0
     */
    COLON(1), // ':'

    /**
     * A [TRUE] [JsonTokenType] represents a 'true' in the source
     * @since 0.1.0
     * @version 0.1.0
     */
    TRUE(4),

    /**
     * A [FALSE] [JsonTokenType] represents a 'false' in the source
     * @since 0.1.0
     * @version 0.1.0
     */
    FALSE(5),

    /**
     * A [NULL] [JsonTokenType] represents a 'null' in the source
     * @since 0.1.0
     * @version 0.1.0
     */
    NULL(4),

    /**
     * A [STRING] [JsonTokenType] represents a string (e.g. "hello world") in the source
     * @since 0.1.0
     * @version 0.1.0
     */
    STRING(-1),

    /**
     * A [DOUBLE] [JsonTokenType] represents a doubles (e.g. '0.1') in the source
     * @since 0.1.0
     * @version 0.1.0
     */
    DOUBLE(-1),

    /**
     * A [INT] [JsonTokenType] represents a integers (e.g. '42') in the source
     * @since 0.1.0
     * @version 0.1.0
     */
    INT(-1),

    /**
     * A [EOF] [JsonTokenType] represents the end of the file
     * @since 0.1.0
     * @version 0.1.0
     */
    EOF(0),
    ;

    /**
     * Get the length of the [JsonTokenType]
     * @since 0.1.0
     * @version 0.1.0
     */
    override fun length(value: String?): Int = if (hasValue) value!!.length else length

    /**
     * Does the [JsonTokenType] has a value?
     * @since 0.1.0
     * @version 0.1.0
     */
    override val hasValue: Boolean get() = this.length < 0
}

/**
 * A [JsonTokenInputStream] provides the [JsonToken]s for a Parser. It is
 * created by the [JsonLexer]
 * @since 0.1.0
 * @version 0.1.0
 */
interface JsonTokenInputStream : TokenInputStream<
    JsonTokenInputStream,
    JsonTokenType,
    JsonToken,
    >

/**
 * Implementation of [JsonTokenInputStream]
 * @since 0.1.0
 * @version 0.1.0
 */
class JsonTokenInputStreamImpl(
    tokens: TokenFactory<JsonToken>,
    map: PositionMap,
) : JsonTokenInputStream, FactoryTokenInputStream<JsonTokenInputStream, JsonTokenType, JsonToken>(tokens, map, JsonTokenType.EOF)

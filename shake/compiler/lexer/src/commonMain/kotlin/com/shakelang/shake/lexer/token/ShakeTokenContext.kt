package com.shakelang.shake.lexer.token

import com.shakelang.shake.lexer.token.stream.ShakeTokenInputStream
import com.shakelang.util.parseutils.lexer.token.TokenContext

class ShakeTokenContext : TokenContext<
    ShakeTokenContext,
    ShakeTokenType,
    ShakeToken,
    ShakeTokenInputStream,
    >()

package com.shakelang.shake.parser.impl

import com.shakelang.shake.lexer.token.ShakeToken
import com.shakelang.shake.parser.node.misc.ShakeAccessDescriber

enum class DeclarationScope {
    FILE,
    CLASS,
    OBJECT,
    INTERFACE,
    ENUM,
    BLOCK,
}

class DeclarationContextInformation(
    val scope: DeclarationScope,
) {
    var access: ShakeAccessDescriber? = null
    var staticToken: ShakeToken? = null
    var finalToken: ShakeToken? = null
    var constToken: ShakeToken? = null
    var abstractToken: ShakeToken? = null
    var overrideToken: ShakeToken? = null
    var operatorToken: ShakeToken? = null
    var nativeToken: ShakeToken? = null
    var synchronizedToken: ShakeToken? = null
    var inlineToken: ShakeToken? = null

    val isStatic: Boolean
        get() = staticToken != null

    val isFinal: Boolean
        get() = finalToken != null

    val isConst: Boolean
        get() = constToken != null

    val isAbstract: Boolean
        get() = abstractToken != null

    val isOverride: Boolean
        get() = overrideToken != null

    val isOperator: Boolean
        get() = operatorToken != null

    val isNative: Boolean
        get() = nativeToken != null

    val isSynchronized: Boolean
        get() = synchronizedToken != null

    val isInline: Boolean
        get() = inlineToken != null
}

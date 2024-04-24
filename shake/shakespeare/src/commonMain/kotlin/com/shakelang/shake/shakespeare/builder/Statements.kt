@file:Suppress("ktlint:standard:function-naming", "FunctionName")

package com.shakelang.shake.shakespeare.builder

import com.shakelang.shake.shakespeare.spec.code.*

class VariableDeclarationBuilder
internal constructor(init: VariableDeclarationBuilder.() -> Unit) :
    VariableDeclarationSpec.VariableDeclarationSpecBuilder(),
    Builder {
    init {
        init()
    }
}

class WhileBuilder
internal constructor(init: WhileBuilder.() -> Unit) : WhileSpec.WhileSpecBuilder(), Builder {
    init {
        init()
    }

    fun body(init: CodeBuilder.() -> Unit) {
        val builder = CodeBuilder(init)
        body = builder.build()
    }
}

class DoWhileBuilder
internal constructor(init: DoWhileBuilder.() -> Unit) : DoWhileSpec.DoWhileSpecBuilder(), Builder {
    init {
        init()
    }

    fun body(init: CodeBuilder.() -> Unit) {
        val builder = CodeBuilder(init)
        body = builder.build()
    }
}

class ForBuilder
internal constructor(init: ForBuilder.() -> Unit) : ForSpec.ForSpecBuilder(), Builder {
    init {
        init()
    }

    fun body(init: CodeBuilder.() -> Unit) {
        val builder = CodeBuilder(init)
        body = builder.build()
    }
}

class IfBuilder
internal constructor(init: IfBuilder.() -> Unit) : IfSpec.IfSpecBuilder(), Builder {
    init {
        init()
    }

    fun body(init: CodeBuilder.() -> Unit) {
        val builder = CodeBuilder(init)
        body = builder.build()
    }

    fun elseBody(init: CodeBuilder.() -> Unit) {
        val builder = CodeBuilder(init)
        elseBody = builder.build()
    }

    fun elseIf(init: IfBuilder.() -> Unit) {
        elseBody {
            val builder = IfBuilder(init)
            statements.add(builder.build())
        }
    }
}

class ReturnBuilder
internal constructor(init: ReturnBuilder.() -> Unit) : ReturnSpec.ReturnSpecBuilder(), Builder {
    init {
        init()
    }
}

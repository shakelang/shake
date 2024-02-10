package com.shakelang.shake.shakespeare.spec.code

import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.shake.shakespeare.spec.Identifier

interface StatementSpec {
    fun generate(ctx: GenerationContext): String
}

class VariableDeclarationSpec(
    val name: Identifier,
    val type: String,
    val value: String,
) : StatementSpec {
    override fun generate(ctx: GenerationContext): String {
        return "val $name: $type = $value"
    }

    class VariableDeclarationSpecBuilder
    internal constructor(
        var name: Identifier? = null,
        var type: String? = null,
        var value: String? = null,
    ) {
        fun build(): VariableDeclarationSpec {
            return VariableDeclarationSpec(
                name ?: throw IllegalStateException("Name not set"),
                type ?: throw IllegalStateException("Type not set"),
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }

    companion object {
        fun builder() = VariableDeclarationSpecBuilder()
    }
}

class WhileSpec(
    val condition: ValueSpec,
    val body: CodeSpec,
) : StatementSpec {
    override fun generate(ctx: GenerationContext): String {
        return "while(${condition.generate(ctx)}) ${body.generate(ctx.indent())}"
    }

    class WhileSpecBuilder
    internal constructor(
        var condition: ValueSpec? = null,
        var body: CodeSpec? = null,
    ) {
        fun build(): WhileSpec {
            return WhileSpec(
                condition ?: throw IllegalStateException("Condition not set"),
                body ?: throw IllegalStateException("Body not set"),
            )
        }
    }

    companion object {
        fun builder() = WhileSpecBuilder()
    }
}

class DoWhileSpec(
    val body: CodeSpec,
    val condition: ValueSpec,
) : StatementSpec {
    override fun generate(ctx: GenerationContext): String {
        return "do ${body.generate(ctx.indent())} while(${condition.generate(ctx)})"
    }

    class DoWhileSpecBuilder
    internal constructor(
        var body: CodeSpec? = null,
        var condition: ValueSpec? = null,
    ) {
        fun build(): DoWhileSpec {
            return DoWhileSpec(
                body ?: throw IllegalStateException("Body not set"),
                condition ?: throw IllegalStateException("Condition not set"),
            )
        }
    }

    companion object {
        fun builder() = DoWhileSpecBuilder()
    }
}

class ForSpec(
    val init: StatementSpec,
    val condition: ValueSpec,
    val update: StatementSpec,
    val body: CodeSpec,
) : StatementSpec {
    override fun generate(ctx: GenerationContext): String {
        return "for(${init.generate(ctx)}; ${condition.generate(ctx)}; ${update.generate(ctx)}) ${body.generate(ctx.indent())}"
    }

    class ForSpecBuilder
    internal constructor(
        var init: StatementSpec? = null,
        var condition: ValueSpec? = null,
        var update: StatementSpec? = null,
        var body: CodeSpec? = null,
    ) {
        fun build(): ForSpec {
            return ForSpec(
                init ?: throw IllegalStateException("Init not set"),
                condition ?: throw IllegalStateException("Condition not set"),
                update ?: throw IllegalStateException("Update not set"),
                body ?: throw IllegalStateException("Body not set"),
            )
        }
    }

    companion object {
        fun builder() = ForSpecBuilder()
    }
}

class IfSpec(
    val condition: ValueSpec,
    val body: CodeSpec,
    val elseBody: CodeSpec?,
) : StatementSpec {
    override fun generate(ctx: GenerationContext): String {
        val elsePart = if (elseBody != null) " else ${elseBody.generate(ctx.indent())}" else ""
        return "if(${condition.generate(ctx)}) ${body.generate(ctx.indent())}$elsePart"
    }

    class IfSpecBuilder
    internal constructor(
        var condition: ValueSpec? = null,
        var body: CodeSpec? = null,
        var elseBody: CodeSpec? = null,
    ) {
        fun build(): IfSpec {
            return IfSpec(
                condition ?: throw IllegalStateException("Condition not set"),
                body ?: throw IllegalStateException("Body not set"),
                elseBody,
            )
        }
    }

    companion object {
        fun builder() = IfSpecBuilder()
    }
}

class ReturnSpec(
    val value: ValueSpec,
) : StatementSpec {
    override fun generate(ctx: GenerationContext): String {
        return "return ${value.generate(ctx)}"
    }

    class ReturnSpecBuilder
    internal constructor(
        var value: ValueSpec? = null,
    ) {
        fun build(): ReturnSpec {
            return ReturnSpec(
                value ?: throw IllegalStateException("Value not set"),
            )
        }
    }
}

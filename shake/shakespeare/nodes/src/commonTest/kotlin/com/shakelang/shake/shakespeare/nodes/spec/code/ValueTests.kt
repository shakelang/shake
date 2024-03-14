package com.shakelang.shake.shakespeare.nodes.spec.code

import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.shake.parser.node.values.ShakeVariableUsageNode
import com.shakelang.shake.parser.node.values.expression.ShakeUnaryMinusNode
import com.shakelang.shake.parser.node.values.factor.ShakeFalseLiteralNode
import com.shakelang.shake.parser.node.values.factor.ShakeFloatLiteralNode
import com.shakelang.shake.parser.node.values.factor.ShakeIntegerLiteralNode
import com.shakelang.shake.parser.node.values.factor.ShakeTrueLiteralNode
import com.shakelang.shake.shakespeare.nodes.spec.NamespaceNodeSpec
import com.shakelang.shake.shakespeare.nodes.spec.NodeContext
import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.shake.shakespeare.spec.code.*
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class ValueTests : FreeSpec({

    "create StringLiteralNodeSpec from string" {
        val value = "Hello, World!"
        val node = StringLiteralNodeSpec(value)
        node.value shouldBe value
    }

    "create StringLiteralNodeSpec from StringLiteralSpec" {
        val value = "Hello, World!"
        val node = StringLiteralNodeSpec.of(StringLiteralSpec(value))
        node.value shouldBe value
    }

    "convert StringLiteralSpec to StringLiteralNodeSpec" {
        val value = "Hello, World!"
        val node = StringLiteralSpec(value).toNodeSpec()
        node.value shouldBe value
    }

    "dump StringLiteralNodeSpec" {
        val value = "Hello, World!"
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = StringLiteralNodeSpec(value)
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)
        node.value shouldBe value
        node.valueToken.type shouldBe ShakeTokenType.STRING
        node.valueToken.value shouldBe "\"$value\""
        node.valueToken.start shouldBe 0
        node.valueToken.end shouldBe value.length + 1
    }

    "dump StringLiteralNodeSpec with escape characters" {
        val value = "Hello, World!\n"
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = StringLiteralNodeSpec(value)
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)
        node.value shouldBe value
        node.valueToken.type shouldBe ShakeTokenType.STRING
        node.valueToken.value shouldBe "\"Hello, World!\\n\""
        node.valueToken.start shouldBe 0
        node.valueToken.end shouldBe value.length + 2
    }

    "create CharacterLiteralNodeSpec from char" {
        val value = 'a'
        val node = CharacterLiteralNodeSpec(value)
        node.value shouldBe value
    }

    "create CharacterLiteralNodeSpec from CharacterLiteralSpec" {
        val value = 'a'
        val node = CharacterLiteralNodeSpec.of(CharacterLiteralSpec(value))
        node.value shouldBe value
    }

    "convert CharacterLiteralSpec to CharacterLiteralNodeSpec" {
        val value = 'a'
        val node = CharacterLiteralSpec(value).toNodeSpec()
        node.value shouldBe value
    }

    "dump CharacterLiteralNodeSpec" {
        val value = 'a'
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = CharacterLiteralNodeSpec(value)
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)
        node.value shouldBe value
        node.valueToken.type shouldBe ShakeTokenType.CHARACTER
        node.valueToken.value shouldBe "'$value'"
        node.valueToken.start shouldBe 0
        node.valueToken.end shouldBe 2
    }

    "dump CharacterLiteralNodeSpec with escape characters" {
        val value = '\n'
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = CharacterLiteralNodeSpec(value)
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)
        node.value shouldBe value
        node.valueToken.type shouldBe ShakeTokenType.CHARACTER
        node.valueToken.value shouldBe "'\\n'"
        node.valueToken.start shouldBe 0
        node.valueToken.end shouldBe 3
    }

    "create IntLiteralNodeSpec from int" {
        val value = 123
        val node = IntLiteralNodeSpec(value)
        node.value shouldBe value
    }

    "create IntLiteralNodeSpec from IntLiteralSpec" {
        val value = 123
        val node = IntLiteralNodeSpec.of(IntLiteralSpec(value))
        node.value shouldBe value
    }

    "convert IntLiteralSpec to IntLiteralNodeSpec" {
        val value = 123
        val node = IntLiteralSpec(value).toNodeSpec()
        node.value shouldBe value
    }

    "dump IntLiteralNodeSpec" {
        val value = 123
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = IntLiteralNodeSpec(value)
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)
        (node is ShakeIntegerLiteralNode) shouldBe true
        (node as ShakeIntegerLiteralNode).value shouldBe value
        node.valueToken.type shouldBe ShakeTokenType.INTEGER
        node.valueToken.value shouldBe value.toString()
        node.valueToken.start shouldBe 0
        node.valueToken.end shouldBe 2
    }

    "dump IntLiteralNodeSpec with negative value" {
        val value = -123
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = IntLiteralNodeSpec(value)
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)
        (node is ShakeUnaryMinusNode) shouldBe true
        (node as ShakeUnaryMinusNode).operatorToken.type shouldBe ShakeTokenType.SUB
        node.operatorToken.value shouldBe "-"
        node.operatorToken.start shouldBe 0
        node.operatorToken.end shouldBe 0

        (node.value is ShakeIntegerLiteralNode) shouldBe true
        val valueNode = node.value as ShakeIntegerLiteralNode
        valueNode.value shouldBe -value
        valueNode.valueToken.type shouldBe ShakeTokenType.INTEGER
        valueNode.valueToken.value shouldBe (-value).toString()
        valueNode.valueToken.start shouldBe 1
        valueNode.valueToken.end shouldBe 3
    }

    "dump IntLiteralNodeSpec with zero value" {
        val value = 0
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = IntLiteralNodeSpec(value)
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)
        (node is ShakeIntegerLiteralNode) shouldBe true
        (node as ShakeIntegerLiteralNode).value shouldBe value
        node.valueToken.type shouldBe ShakeTokenType.INTEGER
        node.valueToken.value shouldBe value.toString()
        node.valueToken.start shouldBe 0
        node.valueToken.end shouldBe 0
    }

    "create FloatLiteralNodeSpec from float" {
        val value = 123.456
        val node = FloatLiteralNodeSpec(value)
        node.value shouldBe value
    }

    "create FloatLiteralNodeSpec from FloatLiteralSpec" {
        val value = 123.456
        val node = FloatLiteralNodeSpec.of(FloatLiteralSpec(value))
        node.value shouldBe value
    }

    "convert FloatLiteralSpec to FloatLiteralNodeSpec" {
        val value = 123.456
        val node = FloatLiteralSpec(value).toNodeSpec()
        node.value shouldBe value
    }

    "dump FloatLiteralNodeSpec" {
        val value = 123.456
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = FloatLiteralNodeSpec(value)
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)
        (node is ShakeFloatLiteralNode) shouldBe true
        (node as ShakeFloatLiteralNode).value shouldBe value
        node.valueToken.type shouldBe ShakeTokenType.FLOAT
        node.valueToken.value shouldBe value.toString()
        node.valueToken.start shouldBe 0
        node.valueToken.end shouldBe 6
    }

    "dump FloatLiteralNodeSpec with negative value" {
        val value = -123.456
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = FloatLiteralNodeSpec(value)
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)
        (node is ShakeUnaryMinusNode) shouldBe true
        (node as ShakeUnaryMinusNode).operatorToken.type shouldBe ShakeTokenType.SUB
        node.operatorToken.value shouldBe "-"
        node.operatorToken.start shouldBe 0
        node.operatorToken.end shouldBe 0

        (node.value is ShakeFloatLiteralNode) shouldBe true
        val valueNode = node.value as ShakeFloatLiteralNode
        valueNode.value shouldBe (-value)
        valueNode.valueToken.type shouldBe ShakeTokenType.FLOAT
        valueNode.valueToken.value shouldBe (-value).toString()
        valueNode.valueToken.start shouldBe 1
        valueNode.valueToken.end shouldBe 7
    }

    "dump FloatLiteralNodeSpec with zero value" {
        val value = 0.0
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = FloatLiteralNodeSpec(value)
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)
        (node is ShakeFloatLiteralNode) shouldBe true
        (node as ShakeFloatLiteralNode).value shouldBe value
        node.valueToken.type shouldBe ShakeTokenType.FLOAT
        node.valueToken.value shouldBe value.toString()
        node.valueToken.start shouldBe 0
        node.valueToken.end shouldBe 2
    }

    "dump FloatLiteralNodeSpec with integer value" {
        val value = 123.0
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = FloatLiteralNodeSpec(value)
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)
        (node is ShakeFloatLiteralNode) shouldBe true
        (node as ShakeFloatLiteralNode).value shouldBe value
        node.valueToken.type shouldBe ShakeTokenType.FLOAT
        node.valueToken.value shouldBe value.toString()
        node.valueToken.start shouldBe 0
        node.valueToken.end shouldBe 4
    }

    "create BooleanLiteralNodeSpec from boolean" {
        val value = true
        val node = BooleanLiteralNodeSpec(value)
        node.value shouldBe value
    }

    "create BooleanLiteralNodeSpec from BooleanLiteralSpec" {
        val value = true
        val node = BooleanLiteralNodeSpec.of(BooleanLiteralSpec(value))
        node.value shouldBe value
    }

    "convert BooleanLiteralSpec to BooleanLiteralNodeSpec" {
        val value = true
        val node = BooleanLiteralSpec(value).toNodeSpec()
        node.value shouldBe value
    }

    "dump BooleanLiteralNodeSpec with true value" {
        val value = true
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = BooleanLiteralNodeSpec(value)
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)
        (node is ShakeTrueLiteralNode) shouldBe true
        (node as ShakeTrueLiteralNode).valueToken.type shouldBe ShakeTokenType.KEYWORD_TRUE
        node.valueToken.value shouldBe "true"
        node.valueToken.start shouldBe 0
        node.valueToken.end shouldBe 3
    }

    "dump BooleanLiteralNodeSpec with false value" {
        val value = false
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = BooleanLiteralNodeSpec(value)
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)
        (node is ShakeFalseLiteralNode) shouldBe true
        (node as ShakeFalseLiteralNode).valueToken.type shouldBe ShakeTokenType.KEYWORD_FALSE
        node.valueToken.value shouldBe "false"
        node.valueToken.start shouldBe 0
        node.valueToken.end shouldBe 4
    }

    "create NullLiteralNodeSpec" {
        NullLiteralNodeSpec()
    }

    "create NullLiteralNodeSpec from NullLiteralSpec" {
        NullLiteralNodeSpec.of(NullLiteralSpec())
    }

    "convert NullLiteralSpec to NullLiteralNodeSpec" {
        NullLiteralSpec().toNodeSpec()
    }

    "dump NullLiteralNodeSpec" {
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = NullLiteralNodeSpec()
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)
        node.valueToken.type shouldBe ShakeTokenType.KEYWORD_NULL
        node.valueToken.value shouldBe "null"
        node.valueToken.start shouldBe 0
        node.valueToken.end shouldBe 3
    }

    "create VariableReferenceNodeSpec" {
        val value = "test"
        val namespace = NamespaceNodeSpec(value)
        val node = VariableReferenceNodeSpec(namespace)
        node.name shouldBe namespace
    }

    "create VariableReferenceNodeSpec from String" {
        val value = "test"
        val node = VariableReferenceNodeSpec(value)
        node.name.name shouldBe arrayOf(value)
    }

    "create VariableReferenceNodeSpec from VariableReferenceSpec" {
        val value = "test"
        val node = VariableReferenceNodeSpec.of(VariableReferenceSpec(value))
        node.name.name shouldBe arrayOf(value)
    }

    "convert VariableReferenceSpec to VariableReferenceNodeSpec" {
        val value = "test"
        val node = VariableReferenceSpec(value).toNodeSpec()
        node.name.name shouldBe arrayOf(value)
    }

    "dump VariableReferenceNodeSpec" {
        val value = "test"
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = VariableReferenceNodeSpec(value)
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)
        node.identifier.name shouldBe value
        node.identifier.parent shouldBe null
        node.identifier.nameToken.type shouldBe ShakeTokenType.IDENTIFIER
        node.identifier.nameToken.value shouldBe value
        node.identifier.nameToken.start shouldBe 0
        node.identifier.nameToken.end shouldBe 3
        node.identifier.dotToken shouldBe null
    }

    "dump VariableReferenceNodeSpec with dot" {
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = VariableReferenceNodeSpec("test", "test2")
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)
        node.identifier.name shouldBe "test2"
        node.identifier.parent shouldNotBe null
        node.identifier.nameToken.type shouldBe ShakeTokenType.IDENTIFIER
        node.identifier.nameToken.value shouldBe "test2"
        node.identifier.nameToken.start shouldBe 5
        node.identifier.nameToken.end shouldBe 9
        node.identifier.dotToken!!.type shouldBe ShakeTokenType.DOT
        node.identifier.dotToken!!.value shouldBe "."
        node.identifier.dotToken!!.start shouldBe 4
        node.identifier.dotToken!!.end shouldBe 4

        val parent = node.identifier.parent
        (parent is ShakeVariableUsageNode) shouldBe true
        val parentNamespace = (parent as ShakeVariableUsageNode).identifier
        parentNamespace.name shouldBe "test"
        parentNamespace.parent shouldBe null
        parentNamespace.nameToken.type shouldBe ShakeTokenType.IDENTIFIER
        parentNamespace.nameToken.value shouldBe "test"
        parentNamespace.nameToken.start shouldBe 0
        parentNamespace.nameToken.end shouldBe 3
        parentNamespace.dotToken shouldBe null
    }
})

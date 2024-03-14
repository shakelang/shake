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

    "create AdditionNodeSpec" {
        val left = IntLiteralNodeSpec(1)
        val right = IntLiteralNodeSpec(2)
        AdditionNodeSpec(left, right)
    }

    "create AdditionNodeSpec from AdditionSpec" {
        val left = IntLiteralSpec(1)
        val right = IntLiteralSpec(2)
        AdditionNodeSpec.of(AdditionSpec(left, right))
    }

    "convert AdditionSpec to AdditionNodeSpec" {
        val left = IntLiteralSpec(1)
        val right = IntLiteralSpec(2)
        AdditionSpec(left, right).toNodeSpec()
    }

    "dump AdditionNodeSpec" {
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = AdditionNodeSpec(IntLiteralNodeSpec(1), IntLiteralNodeSpec(2))
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)

        (node.left is ShakeIntegerLiteralNode) shouldBe true
        (node.right is ShakeIntegerLiteralNode) shouldBe true

        val left = node.left as ShakeIntegerLiteralNode
        left.value shouldBe 1
        left.valueToken.type shouldBe ShakeTokenType.INTEGER
        left.valueToken.value shouldBe "1"
        left.valueToken.start shouldBe 0
        left.valueToken.end shouldBe 0

        val right = node.right as ShakeIntegerLiteralNode
        right.value shouldBe 2
        right.valueToken.type shouldBe ShakeTokenType.INTEGER
        right.valueToken.value shouldBe "2"
        right.valueToken.start shouldBe 4
        right.valueToken.end shouldBe 4

        node.operatorToken.type shouldBe ShakeTokenType.ADD
        node.operatorToken.value shouldBe "+"
        node.operatorToken.start shouldBe 2
        node.operatorToken.end shouldBe 2
    }

    "create SubtractionNodeSpec" {
        val left = IntLiteralNodeSpec(1)
        val right = IntLiteralNodeSpec(2)
        SubtractionNodeSpec(left, right)
    }

    "create SubtractionNodeSpec from SubtractionSpec" {
        val left = IntLiteralSpec(1)
        val right = IntLiteralSpec(2)
        SubtractionNodeSpec.of(SubtractionSpec(left, right))
    }

    "convert SubtractionSpec to SubtractionNodeSpec" {
        val left = IntLiteralSpec(1)
        val right = IntLiteralSpec(2)
        SubtractionSpec(left, right).toNodeSpec()
    }

    "dump SubtractionNodeSpec" {
        val left = IntLiteralNodeSpec(1)
        val right = IntLiteralNodeSpec(2)
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = SubtractionNodeSpec(left, right)
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)

        (node.left is ShakeIntegerLiteralNode) shouldBe true
        (node.right is ShakeIntegerLiteralNode) shouldBe true

        val leftNode = node.left as ShakeIntegerLiteralNode
        leftNode.value shouldBe 1
        leftNode.valueToken.type shouldBe ShakeTokenType.INTEGER
        leftNode.valueToken.value shouldBe "1"
        leftNode.valueToken.start shouldBe 0
        leftNode.valueToken.end shouldBe 0

        val rightNode = node.right as ShakeIntegerLiteralNode
        rightNode.value shouldBe 2
        rightNode.valueToken.type shouldBe ShakeTokenType.INTEGER
        rightNode.valueToken.value shouldBe "2"
        rightNode.valueToken.start shouldBe 4
        rightNode.valueToken.end shouldBe 4

        node.operatorToken.type shouldBe ShakeTokenType.SUB
        node.operatorToken.value shouldBe "-"
        node.operatorToken.start shouldBe 2
        node.operatorToken.end shouldBe 2
    }

    "create MultiplicationNodeSpec" {
        val left = IntLiteralNodeSpec(1)
        val right = IntLiteralNodeSpec(2)
        MultiplicationNodeSpec(left, right)
    }

    "create MultiplicationNodeSpec from MultiplicationSpec" {
        val left = IntLiteralSpec(1)
        val right = IntLiteralSpec(2)
        MultiplicationNodeSpec.of(MultiplicationSpec(left, right))
    }

    "convert MultiplicationSpec to MultiplicationNodeSpec" {
        val left = IntLiteralSpec(1)
        val right = IntLiteralSpec(2)
        MultiplicationSpec(left, right).toNodeSpec()
    }

    "dump MultiplicationNodeSpec" {
        val left = IntLiteralNodeSpec(1)
        val right = IntLiteralNodeSpec(2)
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = MultiplicationNodeSpec(left, right)
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)

        (node.left is ShakeIntegerLiteralNode) shouldBe true
        (node.right is ShakeIntegerLiteralNode) shouldBe true

        val leftNode = node.left as ShakeIntegerLiteralNode
        leftNode.value shouldBe 1
        leftNode.valueToken.type shouldBe ShakeTokenType.INTEGER
        leftNode.valueToken.value shouldBe "1"
        leftNode.valueToken.start shouldBe 0
        leftNode.valueToken.end shouldBe 0

        val rightNode = node.right as ShakeIntegerLiteralNode
        rightNode.value shouldBe 2
        rightNode.valueToken.type shouldBe ShakeTokenType.INTEGER
        rightNode.valueToken.value shouldBe "2"
        rightNode.valueToken.start shouldBe 4
        rightNode.valueToken.end shouldBe 4

        node.operatorToken.type shouldBe ShakeTokenType.MUL
        node.operatorToken.value shouldBe "*"
        node.operatorToken.start shouldBe 2
        node.operatorToken.end shouldBe 2
    }

    "create DivisionNodeSpec" {
        val left = IntLiteralNodeSpec(1)
        val right = IntLiteralNodeSpec(2)
        DivisionNodeSpec(left, right)
    }

    "create DivisionNodeSpec from DivisionSpec" {
        val left = IntLiteralSpec(1)
        val right = IntLiteralSpec(2)
        DivisionNodeSpec.of(DivisionSpec(left, right))
    }

    "convert DivisionSpec to DivisionNodeSpec" {
        val left = IntLiteralSpec(1)
        val right = IntLiteralSpec(2)
        DivisionSpec(left, right).toNodeSpec()
    }

    "dump DivisionNodeSpec" {
        val left = IntLiteralNodeSpec(1)
        val right = IntLiteralNodeSpec(2)
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = DivisionNodeSpec(left, right)
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)

        (node.left is ShakeIntegerLiteralNode) shouldBe true
        (node.right is ShakeIntegerLiteralNode) shouldBe true

        val leftNode = node.left as ShakeIntegerLiteralNode
        leftNode.value shouldBe 1
        leftNode.valueToken.type shouldBe ShakeTokenType.INTEGER
        leftNode.valueToken.value shouldBe "1"
        leftNode.valueToken.start shouldBe 0
        leftNode.valueToken.end shouldBe 0

        val rightNode = node.right as ShakeIntegerLiteralNode
        rightNode.value shouldBe 2
        rightNode.valueToken.type shouldBe ShakeTokenType.INTEGER
        rightNode.valueToken.value shouldBe "2"
        rightNode.valueToken.start shouldBe 4
        rightNode.valueToken.end shouldBe 4

        node.operatorToken.type shouldBe ShakeTokenType.DIV
        node.operatorToken.value shouldBe "/"
        node.operatorToken.start shouldBe 2
        node.operatorToken.end shouldBe 2
    }

    "create ModuloNodeSpec" {
        val left = IntLiteralNodeSpec(1)
        val right = IntLiteralNodeSpec(2)
        ModuloNodeSpec(left, right)
    }

    "create ModuloNodeSpec from ModuloSpec" {
        val left = IntLiteralSpec(1)
        val right = IntLiteralSpec(2)
        ModuloNodeSpec.of(ModuloSpec(left, right))
    }

    "convert ModuloSpec to ModuloNodeSpec" {
        val left = IntLiteralSpec(1)
        val right = IntLiteralSpec(2)
        ModuloSpec(left, right).toNodeSpec()
    }

    "dump ModuloNodeSpec" {
        val left = IntLiteralNodeSpec(1)
        val right = IntLiteralNodeSpec(2)
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = ModuloNodeSpec(left, right)
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)

        (node.left is ShakeIntegerLiteralNode) shouldBe true
        (node.right is ShakeIntegerLiteralNode) shouldBe true

        val leftNode = node.left as ShakeIntegerLiteralNode
        leftNode.value shouldBe 1
        leftNode.valueToken.type shouldBe ShakeTokenType.INTEGER
        leftNode.valueToken.value shouldBe "1"
        leftNode.valueToken.start shouldBe 0
        leftNode.valueToken.end shouldBe 0

        val rightNode = node.right as ShakeIntegerLiteralNode
        rightNode.value shouldBe 2
        rightNode.valueToken.type shouldBe ShakeTokenType.INTEGER
        rightNode.valueToken.value shouldBe "2"
        rightNode.valueToken.start shouldBe 4
        rightNode.valueToken.end shouldBe 4

        node.operatorToken.type shouldBe ShakeTokenType.MOD
        node.operatorToken.value shouldBe "%"
        node.operatorToken.start shouldBe 2
        node.operatorToken.end shouldBe 2
    }

    "create PowerNodeSpec" {
        val left = IntLiteralNodeSpec(1)
        val right = IntLiteralNodeSpec(2)
        PowerNodeSpec(left, right)
    }

    "create PowerNodeSpec from PowerSpec" {
        val left = IntLiteralSpec(1)
        val right = IntLiteralSpec(2)
        PowerNodeSpec.of(PowerSpec(left, right))
    }

    "convert PowerSpec to PowerNodeSpec" {
        val left = IntLiteralSpec(1)
        val right = IntLiteralSpec(2)
        PowerSpec(left, right).toNodeSpec()
    }

    "dump PowerNodeSpec" {
        val left = IntLiteralNodeSpec(1)
        val right = IntLiteralNodeSpec(2)
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = PowerNodeSpec(left, right)
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)

        (node.left is ShakeIntegerLiteralNode) shouldBe true
        (node.right is ShakeIntegerLiteralNode) shouldBe true

        val leftNode = node.left as ShakeIntegerLiteralNode
        leftNode.value shouldBe 1
        leftNode.valueToken.type shouldBe ShakeTokenType.INTEGER
        leftNode.valueToken.value shouldBe "1"
        leftNode.valueToken.start shouldBe 0
        leftNode.valueToken.end shouldBe 0

        val rightNode = node.right as ShakeIntegerLiteralNode
        rightNode.value shouldBe 2
        rightNode.valueToken.type shouldBe ShakeTokenType.INTEGER
        rightNode.valueToken.value shouldBe "2"
        rightNode.valueToken.start shouldBe 5
        rightNode.valueToken.end shouldBe 5

        node.operatorToken.type shouldBe ShakeTokenType.POW
        node.operatorToken.value shouldBe "**"
        node.operatorToken.start shouldBe 2
        node.operatorToken.end shouldBe 3
    }

    "create UnaryMinusNodeSpec" {
        val value = IntLiteralNodeSpec(1)
        UnaryMinusNodeSpec(value)
    }

    "create UnaryMinusNodeSpec from UnaryMinusSpec" {
        val value = IntLiteralSpec(1)
        UnaryMinusNodeSpec.of(UnaryMinusSpec(value))
    }

    "convert UnaryMinusSpec to UnaryMinusNodeSpec" {
        val value = IntLiteralSpec(1)
        UnaryMinusSpec(value).toNodeSpec()
    }

    "dump UnaryMinusNodeSpec" {
        val value = IntLiteralNodeSpec(1)
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = UnaryMinusNodeSpec(value)
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)

        (node.value is ShakeIntegerLiteralNode) shouldBe true

        val valueNode = node.value as ShakeIntegerLiteralNode
        valueNode.value shouldBe 1
        valueNode.valueToken.type shouldBe ShakeTokenType.INTEGER
        valueNode.valueToken.value shouldBe "1"
        valueNode.valueToken.start shouldBe 1
        valueNode.valueToken.end shouldBe 1

        node.operatorToken.type shouldBe ShakeTokenType.SUB
        node.operatorToken.value shouldBe "-"
        node.operatorToken.start shouldBe 0
        node.operatorToken.end shouldBe 0
    }

    "create UnaryPlusNodeSpec" {
        val value = IntLiteralNodeSpec(1)
        UnaryPlusNodeSpec(value)
    }

    "create UnaryPlusNodeSpec from UnaryPlusSpec" {
        val value = IntLiteralSpec(1)
        UnaryPlusNodeSpec.of(UnaryPlusSpec(value))
    }

    "convert UnaryPlusSpec to UnaryPlusNodeSpec" {
        val value = IntLiteralSpec(1)
        UnaryPlusSpec(value).toNodeSpec()
    }

    "dump UnaryPlusNodeSpec" {
        val value = IntLiteralNodeSpec(1)
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = UnaryPlusNodeSpec(value)
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)

        (node.value is ShakeIntegerLiteralNode) shouldBe true

        val valueNode = node.value as ShakeIntegerLiteralNode
        valueNode.value shouldBe 1
        valueNode.valueToken.type shouldBe ShakeTokenType.INTEGER
        valueNode.valueToken.value shouldBe "1"
        valueNode.valueToken.start shouldBe 1
        valueNode.valueToken.end shouldBe 1

        node.operatorToken.type shouldBe ShakeTokenType.ADD
        node.operatorToken.value shouldBe "+"
        node.operatorToken.start shouldBe 0
        node.operatorToken.end shouldBe 0
    }

    "create LogicalAndNodeSpec" {
        val left = BooleanLiteralNodeSpec(true)
        val right = BooleanLiteralNodeSpec(false)
        LogicalAndNodeSpec(left, right)
    }

    "create LogicalAndNodeSpec from LogicalAndSpec" {
        val left = BooleanLiteralSpec(true)
        val right = BooleanLiteralSpec(false)
        LogicalAndNodeSpec.of(LogicalAndSpec(left, right))
    }

    "convert LogicalAndSpec to LogicalAndNodeSpec" {
        val left = BooleanLiteralSpec(true)
        val right = BooleanLiteralSpec(false)
        LogicalAndSpec(left, right).toNodeSpec()
    }

    "dump LogicalAndNodeSpec" {
        val left = BooleanLiteralNodeSpec(true)
        val right = BooleanLiteralNodeSpec(false)
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = LogicalAndNodeSpec(left, right)
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)

        (node.left is ShakeTrueLiteralNode) shouldBe true
        (node.right is ShakeFalseLiteralNode) shouldBe true

        val leftNode = node.left as ShakeTrueLiteralNode
        leftNode.valueToken.type shouldBe ShakeTokenType.KEYWORD_TRUE
        leftNode.valueToken.value shouldBe "true"
        leftNode.valueToken.start shouldBe 0
        leftNode.valueToken.end shouldBe 3

        val rightNode = node.right as ShakeFalseLiteralNode
        rightNode.valueToken.type shouldBe ShakeTokenType.KEYWORD_FALSE
        rightNode.valueToken.value shouldBe "false"
        rightNode.valueToken.start shouldBe 8
        rightNode.valueToken.end shouldBe 12

        node.operatorToken.type shouldBe ShakeTokenType.LOGICAL_AND
        node.operatorToken.value shouldBe "&&"
        node.operatorToken.start shouldBe 5
        node.operatorToken.end shouldBe 6
    }

    "create LogicalOrNodeSpec" {
        val left = BooleanLiteralNodeSpec(true)
        val right = BooleanLiteralNodeSpec(false)
        LogicalOrNodeSpec(left, right)
    }

    "create LogicalOrNodeSpec from LogicalOrSpec" {
        val left = BooleanLiteralSpec(true)
        val right = BooleanLiteralSpec(false)
        LogicalOrNodeSpec.of(LogicalOrSpec(left, right))
    }

    "convert LogicalOrSpec to LogicalOrNodeSpec" {
        val left = BooleanLiteralSpec(true)
        val right = BooleanLiteralSpec(false)
        LogicalOrSpec(left, right).toNodeSpec()
    }

    "dump LogicalOrNodeSpec" {
        val left = BooleanLiteralNodeSpec(true)
        val right = BooleanLiteralNodeSpec(false)
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = LogicalOrNodeSpec(left, right)
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)

        (node.left is ShakeTrueLiteralNode) shouldBe true
        (node.right is ShakeFalseLiteralNode) shouldBe true

        val leftNode = node.left as ShakeTrueLiteralNode
        leftNode.valueToken.type shouldBe ShakeTokenType.KEYWORD_TRUE
        leftNode.valueToken.value shouldBe "true"
        leftNode.valueToken.start shouldBe 0
        leftNode.valueToken.end shouldBe 3

        val rightNode = node.right as ShakeFalseLiteralNode
        rightNode.valueToken.type shouldBe ShakeTokenType.KEYWORD_FALSE
        rightNode.valueToken.value shouldBe "false"
        rightNode.valueToken.start shouldBe 8
        rightNode.valueToken.end shouldBe 12

        node.operatorToken.type shouldBe ShakeTokenType.LOGICAL_OR
        node.operatorToken.value shouldBe "||"
        node.operatorToken.start shouldBe 5
        node.operatorToken.end shouldBe 6
    }

    "create LogicalNotNodeSpec" {
        val value = BooleanLiteralNodeSpec(true)
        LogicalNotNodeSpec(value)
    }

    "create LogicalNotNodeSpec from LogicalNotSpec" {
        val value = BooleanLiteralSpec(true)
        LogicalNotNodeSpec.of(LogicalNotSpec(value))
    }

    "convert LogicalNotSpec to LogicalNotNodeSpec" {
        val value = BooleanLiteralSpec(true)
        LogicalNotSpec(value).toNodeSpec()
    }

    "dump LogicalNotNodeSpec" {
        val value = BooleanLiteralNodeSpec(true)
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = LogicalNotNodeSpec(value)
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)

        (node.value is ShakeTrueLiteralNode) shouldBe true

        val valueNode = node.value as ShakeTrueLiteralNode
        valueNode.valueToken.type shouldBe ShakeTokenType.KEYWORD_TRUE
        valueNode.valueToken.value shouldBe "true"
        valueNode.valueToken.start shouldBe 1
        valueNode.valueToken.end shouldBe 4

        node.operatorToken.type shouldBe ShakeTokenType.LOGICAL_NOT
        node.operatorToken.value shouldBe "!"
        node.operatorToken.start shouldBe 0
        node.operatorToken.end shouldBe 0
    }

    "create EqualsNodeSpec" {
        val left = IntLiteralNodeSpec(1)
        val right = IntLiteralNodeSpec(2)
        val spec = EqualityNodeSpec(left, right)
        spec.left shouldBe left
        spec.right shouldBe right
    }

    "create EqualsNodeSpec from EqualsSpec" {
        val left = IntLiteralSpec(1)
        val right = IntLiteralSpec(2)
        EqualityNodeSpec.of(EqualitySpec(left, right))
    }

    "convert EqualsSpec to EqualsNodeSpec" {
        val left = IntLiteralSpec(1)
        val right = IntLiteralSpec(2)
        EqualitySpec(left, right).toNodeSpec()
    }

    "dump EqualsNodeSpec" {
        val left = IntLiteralNodeSpec(1)
        val right = IntLiteralNodeSpec(2)
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = EqualityNodeSpec(left, right)
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)

        (node.left is ShakeIntegerLiteralNode) shouldBe true
        (node.right is ShakeIntegerLiteralNode) shouldBe true

        val leftNode = node.left as ShakeIntegerLiteralNode
        leftNode.value shouldBe 1
        leftNode.valueToken.type shouldBe ShakeTokenType.INTEGER
        leftNode.valueToken.value shouldBe "1"
        leftNode.valueToken.start shouldBe 0
        leftNode.valueToken.end shouldBe 0

        val rightNode = node.right as ShakeIntegerLiteralNode
        rightNode.value shouldBe 2
        rightNode.valueToken.type shouldBe ShakeTokenType.INTEGER
        rightNode.valueToken.value shouldBe "2"
        rightNode.valueToken.start shouldBe 5
        rightNode.valueToken.end shouldBe 5

        node.operatorToken.type shouldBe ShakeTokenType.EQ_EQUALS
        node.operatorToken.value shouldBe "=="
        node.operatorToken.start shouldBe 2
        node.operatorToken.end shouldBe 3
    }

    "create InequalityNodeSpec" {
        val left = IntLiteralNodeSpec(1)
        val right = IntLiteralNodeSpec(2)
        val spec = InequalityNodeSpec(left, right)
        spec.left shouldBe left
        spec.right shouldBe right
    }

    "create InequalityNodeSpec from InequalitySpec" {
        val left = IntLiteralSpec(1)
        val right = IntLiteralSpec(2)
        InequalityNodeSpec.of(InequalitySpec(left, right))
    }

    "convert InequalitySpec to InequalityNodeSpec" {
        val left = IntLiteralSpec(1)
        val right = IntLiteralSpec(2)
        InequalitySpec(left, right).toNodeSpec()
    }

    "dump InequalityNodeSpec" {
        val left = IntLiteralNodeSpec(1)
        val right = IntLiteralNodeSpec(2)
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = InequalityNodeSpec(left, right)
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)

        (node.left is ShakeIntegerLiteralNode) shouldBe true
        (node.right is ShakeIntegerLiteralNode) shouldBe true

        val leftNode = node.left as ShakeIntegerLiteralNode
        leftNode.value shouldBe 1
        leftNode.valueToken.type shouldBe ShakeTokenType.INTEGER
        leftNode.valueToken.value shouldBe "1"
        leftNode.valueToken.start shouldBe 0
        leftNode.valueToken.end shouldBe 0

        val rightNode = node.right as ShakeIntegerLiteralNode
        rightNode.value shouldBe 2
        rightNode.valueToken.type shouldBe ShakeTokenType.INTEGER
        rightNode.valueToken.value shouldBe "2"
        rightNode.valueToken.start shouldBe 5
        rightNode.valueToken.end shouldBe 5

        node.operatorToken.type shouldBe ShakeTokenType.NOT_EQUALS
        node.operatorToken.value shouldBe "!="
        node.operatorToken.start shouldBe 2
        node.operatorToken.end shouldBe 3
    }

    "create LessThanNodeSpec" {
        val left = IntLiteralNodeSpec(1)
        val right = IntLiteralNodeSpec(2)
        val spec = LessThanNodeSpec(left, right)
        spec.left shouldBe left
        spec.right shouldBe right
    }

    "create LessThanNodeSpec from LessThanSpec" {
        val left = IntLiteralSpec(1)
        val right = IntLiteralSpec(2)
        LessThanNodeSpec.of(LessThanSpec(left, right))
    }

    "convert LessThanSpec to LessThanNodeSpec" {
        val left = IntLiteralSpec(1)
        val right = IntLiteralSpec(2)
        LessThanSpec(left, right).toNodeSpec()
    }

    "dump LessThanNodeSpec" {
        val left = IntLiteralNodeSpec(1)
        val right = IntLiteralNodeSpec(2)
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = LessThanNodeSpec(left, right)
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)

        (node.left is ShakeIntegerLiteralNode) shouldBe true
        (node.right is ShakeIntegerLiteralNode) shouldBe true

        val leftNode = node.left as ShakeIntegerLiteralNode
        leftNode.value shouldBe 1
        leftNode.valueToken.type shouldBe ShakeTokenType.INTEGER
        leftNode.valueToken.value shouldBe "1"
        leftNode.valueToken.start shouldBe 0
        leftNode.valueToken.end shouldBe 0

        val rightNode = node.right as ShakeIntegerLiteralNode
        rightNode.value shouldBe 2
        rightNode.valueToken.type shouldBe ShakeTokenType.INTEGER
        rightNode.valueToken.value shouldBe "2"
        rightNode.valueToken.start shouldBe 4
        rightNode.valueToken.end shouldBe 4

        node.operatorToken.type shouldBe ShakeTokenType.SMALLER
        node.operatorToken.value shouldBe "<"
        node.operatorToken.start shouldBe 2
        node.operatorToken.end shouldBe 2
    }

    "create LessThanOrEqualNodeSpec" {
        val left = IntLiteralNodeSpec(1)
        val right = IntLiteralNodeSpec(2)
        val spec = LessThanOrEqualNodeSpec(left, right)
        spec.left shouldBe left
        spec.right shouldBe right
    }

    "create LessThanOrEqualNodeSpec from LessThanOrEqualSpec" {
        val left = IntLiteralSpec(1)
        val right = IntLiteralSpec(2)
        LessThanOrEqualNodeSpec.of(LessThanOrEqualSpec(left, right))
    }

    "convert LessThanOrEqualSpec to LessThanOrEqualNodeSpec" {
        val left = IntLiteralSpec(1)
        val right = IntLiteralSpec(2)
        LessThanOrEqualSpec(left, right).toNodeSpec()
    }

    "dump LessThanOrEqualNodeSpec" {
        val left = IntLiteralNodeSpec(1)
        val right = IntLiteralNodeSpec(2)
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = LessThanOrEqualNodeSpec(left, right)
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)

        (node.left is ShakeIntegerLiteralNode) shouldBe true
        (node.right is ShakeIntegerLiteralNode) shouldBe true

        val leftNode = node.left as ShakeIntegerLiteralNode
        leftNode.value shouldBe 1
        leftNode.valueToken.type shouldBe ShakeTokenType.INTEGER
        leftNode.valueToken.value shouldBe "1"
        leftNode.valueToken.start shouldBe 0
        leftNode.valueToken.end shouldBe 0

        val rightNode = node.right as ShakeIntegerLiteralNode
        rightNode.value shouldBe 2
        rightNode.valueToken.type shouldBe ShakeTokenType.INTEGER
        rightNode.valueToken.value shouldBe "2"
        rightNode.valueToken.start shouldBe 5
        rightNode.valueToken.end shouldBe 5

        node.operatorToken.type shouldBe ShakeTokenType.SMALLER_EQUALS
        node.operatorToken.value shouldBe "<="
        node.operatorToken.start shouldBe 2
        node.operatorToken.end shouldBe 3
    }

    "create GreaterThanNodeSpec" {
        val left = IntLiteralNodeSpec(1)
        val right = IntLiteralNodeSpec(2)
        val spec = GreaterThanNodeSpec(left, right)
        spec.left shouldBe left
        spec.right shouldBe right
    }

    "create GreaterThanNodeSpec from GreaterThanSpec" {
        val left = IntLiteralSpec(1)
        val right = IntLiteralSpec(2)
        GreaterThanNodeSpec.of(GreaterThanSpec(left, right))
    }

    "convert GreaterThanSpec to GreaterThanNodeSpec" {
        val left = IntLiteralSpec(1)
        val right = IntLiteralSpec(2)
        GreaterThanSpec(left, right).toNodeSpec()
    }

    "dump GreaterThanNodeSpec" {
        val left = IntLiteralNodeSpec(1)
        val right = IntLiteralNodeSpec(2)
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = GreaterThanNodeSpec(left, right)
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)

        (node.left is ShakeIntegerLiteralNode) shouldBe true
        (node.right is ShakeIntegerLiteralNode) shouldBe true

        val leftNode = node.left as ShakeIntegerLiteralNode
        leftNode.value shouldBe 1
        leftNode.valueToken.type shouldBe ShakeTokenType.INTEGER
        leftNode.valueToken.value shouldBe "1"
        leftNode.valueToken.start shouldBe 0
        leftNode.valueToken.end shouldBe 0

        val rightNode = node.right as ShakeIntegerLiteralNode
        rightNode.value shouldBe 2
        rightNode.valueToken.type shouldBe ShakeTokenType.INTEGER
        rightNode.valueToken.value shouldBe "2"
        rightNode.valueToken.start shouldBe 4
        rightNode.valueToken.end shouldBe 4

        node.operatorToken.type shouldBe ShakeTokenType.BIGGER
        node.operatorToken.value shouldBe ">"
        node.operatorToken.start shouldBe 2
        node.operatorToken.end shouldBe 2
    }

    "create GreaterThanOrEqualNodeSpec" {
        val left = IntLiteralNodeSpec(1)
        val right = IntLiteralNodeSpec(2)
        val spec = GreaterThanOrEqualNodeSpec(left, right)
        spec.left shouldBe left
        spec.right shouldBe right
    }

    "create GreaterThanOrEqualNodeSpec from GreaterThanOrEqualSpec" {
        val left = IntLiteralSpec(1)
        val right = IntLiteralSpec(2)
        GreaterThanOrEqualNodeSpec.of(GreaterThanOrEqualSpec(left, right))
    }

    "convert GreaterThanOrEqualSpec to GreaterThanOrEqualNodeSpec" {
        val left = IntLiteralSpec(1)
        val right = IntLiteralSpec(2)
        GreaterThanOrEqualSpec(left, right).toNodeSpec()
    }

    "dump GreaterThanOrEqualNodeSpec" {
        val left = IntLiteralNodeSpec(1)
        val right = IntLiteralNodeSpec(2)
        val nctx = NodeContext()
        val ctx = GenerationContext()
        val spec = GreaterThanOrEqualNodeSpec(left, right)
        val node = spec.dump(ctx, nctx)

        nctx.built.toString() shouldBe spec.generate(ctx)

        (node.left is ShakeIntegerLiteralNode) shouldBe true
        (node.right is ShakeIntegerLiteralNode) shouldBe true

        val leftNode = node.left as ShakeIntegerLiteralNode
        leftNode.value shouldBe 1
        leftNode.valueToken.type shouldBe ShakeTokenType.INTEGER
        leftNode.valueToken.value shouldBe "1"
        leftNode.valueToken.start shouldBe 0
        leftNode.valueToken.end shouldBe 0

        val rightNode = node.right as ShakeIntegerLiteralNode
        rightNode.value shouldBe 2
        rightNode.valueToken.type shouldBe ShakeTokenType.INTEGER
        rightNode.valueToken.value shouldBe "2"
        rightNode.valueToken.start shouldBe 5
        rightNode.valueToken.end shouldBe 5

        node.operatorToken.type shouldBe ShakeTokenType.BIGGER_EQUALS
        node.operatorToken.value shouldBe ">="
        node.operatorToken.start shouldBe 2
        node.operatorToken.end shouldBe 3
    }
})

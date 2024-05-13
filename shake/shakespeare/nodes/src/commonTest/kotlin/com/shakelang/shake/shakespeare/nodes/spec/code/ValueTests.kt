package com.shakelang.shake.shakespeare.nodes.spec.code

import com.shakelang.shake.parser.node.values.ShakeVariableUsageNode
import com.shakelang.shake.parser.node.values.expression.ShakeUnaryMinusNode
import com.shakelang.shake.parser.node.values.factor.*
import com.shakelang.shake.shakespeare.nodes.spec.NodeContext
import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.shake.shakespeare.spec.code.*
import com.shakelang.util.testlib.FlatTestSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class ValueTests : FlatTestSpec({

    describe("ValueNodeSpec") {

        describe("of()") {
            it("should not change ValueNodeSpec") {
                val value = IntLiteralNodeSpec(5)
                (value === ValueNodeSpec.of(value as ValueSpec)) shouldBe true
            }

            it("should convert valued statement") {
                val name = "name"
                val value = IntLiteralSpec(5)
                val assignment = VariableAssignmentSpec.builder().name(name).value(value).build()
                val valueNode = ValueNodeSpec.of(assignment)
                (valueNode is VariableAssignmentNodeSpec) shouldBe true
            }

            it("should convert valued statement (anonymous)") {
                val name = "name"
                val value = IntLiteralSpec(5)
                val assignment = VariableAssignmentSpec.builder().name(name).value(value).build()
                val valueNode = ValueNodeSpec.of(assignment as ValueSpec)
                (valueNode is VariableAssignmentNodeSpec) shouldBe true
            }

            it("should convert string literal") {
                val value = StringLiteralSpec("Hello World")
                val valueNode = ValueNodeSpec.of(value)
                valueNode.value shouldBe "Hello World"
            }

            it("should convert string literal (anonymous)") {
                val value = StringLiteralSpec("Hello World")
                val valueNode = ValueNodeSpec.of(value as ValueSpec)
                (valueNode is StringLiteralNodeSpec) shouldBe true
                (valueNode as StringLiteralNodeSpec).value shouldBe "Hello World"
            }

            it("should not change StringLiteralNodeSpec") {
                val value = StringLiteralNodeSpec("Hello World")
                (value === ValueNodeSpec.of(value)) shouldBe true
            }

            it("should convert int literal") {
                val value = IntLiteralSpec(5)
                val valueNode = ValueNodeSpec.of(value)
                valueNode.value shouldBe 5
            }

            it("should convert int literal (anonymous)") {
                val value = IntLiteralSpec(5)
                val valueNode = ValueNodeSpec.of(value as ValueSpec)
                (valueNode is IntLiteralNodeSpec) shouldBe true
                (valueNode as IntLiteralNodeSpec).value shouldBe 5
            }

            it("should not change IntLiteralNodeSpec") {
                val value = IntLiteralNodeSpec(5)
                (value === ValueNodeSpec.of(value)) shouldBe true
            }

            it("should convert character literal") {
                val value = CharacterLiteralSpec('a')
                val valueNode = ValueNodeSpec.of(value)
                valueNode.value shouldBe 'a'
            }

            it("should convert character literal (anonymous)") {
                val value = CharacterLiteralSpec('a')
                val valueNode = ValueNodeSpec.of(value as ValueSpec)
                (valueNode is CharacterLiteralNodeSpec) shouldBe true
                (valueNode as CharacterLiteralNodeSpec).value shouldBe 'a'
            }

            it("should not change CharacterLiteralNodeSpec") {
                val value = CharacterLiteralNodeSpec('a')
                (value === ValueNodeSpec.of(value)) shouldBe true
            }

            it("should convert float literal") {
                val value = FloatLiteralSpec(5.5)
                val valueNode = ValueNodeSpec.of(value)
                valueNode.value shouldBe 5.5
            }

            it("should convert float literal (anonymous)") {
                val value = FloatLiteralSpec(5.5)
                val valueNode = ValueNodeSpec.of(value as ValueSpec)
                (valueNode is FloatLiteralNodeSpec) shouldBe true
                (valueNode as FloatLiteralNodeSpec).value shouldBe 5.5
            }

            it("should not change FloatLiteralNodeSpec") {
                val value = FloatLiteralNodeSpec(5.5)
                (value === ValueNodeSpec.of(value)) shouldBe true
            }

            it("should convert boolean literal") {
                val value = BooleanLiteralSpec(true)
                val valueNode = ValueNodeSpec.of(value)
                valueNode.value shouldBe true
            }

            it("should convert boolean literal (anonymous)") {
                val value = BooleanLiteralSpec(true)
                val valueNode = ValueNodeSpec.of(value as ValueSpec)
                (valueNode is BooleanLiteralNodeSpec) shouldBe true
                (valueNode as BooleanLiteralNodeSpec).value shouldBe true
            }

            it("should not change BooleanLiteralNodeSpec") {
                val value = BooleanLiteralNodeSpec(true)
                (value === ValueNodeSpec.of(value)) shouldBe true
            }

            it("should convert null literal") {
                val value = NullLiteralSpec()
                ValueNodeSpec.of(value)
            }

            it("should convert null literal (anonymous)") {
                val value = NullLiteralSpec()
                val valueNode = ValueNodeSpec.of(value as ValueSpec)
                (valueNode is NullLiteralNodeSpec) shouldBe true
            }

            it("should not change NullLiteralNodeSpec") {
                val value = NullLiteralNodeSpec()
                (value === ValueNodeSpec.of(value)) shouldBe true
            }

            it("should convert variable reference") {
                val value = VariableReferenceSpec("name")
                val valueNode = ValueNodeSpec.of(value)
                valueNode.name.name shouldBe arrayOf("name")
            }

            it("should convert variable reference (anonymous)") {
                val value = VariableReferenceSpec("name")
                val valueNode = ValueNodeSpec.of(value as ValueSpec)
                (valueNode is VariableReferenceNodeSpec) shouldBe true
                (valueNode as VariableReferenceNodeSpec).name.name shouldBe arrayOf("name")
            }

            it("should not change VariableReferenceNodeSpec") {
                val value = VariableReferenceNodeSpec("name")
                (value === ValueNodeSpec.of(value)) shouldBe true
            }

            it("should convert addition") {
                val value = AdditionSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value)
                (valueNode.left is IntLiteralNodeSpec) shouldBe true
                (valueNode.right is IntLiteralNodeSpec) shouldBe true
            }

            it("should convert addition (anonymous)") {
                val value = AdditionSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value as ValueSpec)
                (valueNode is AdditionNodeSpec) shouldBe true
                ((valueNode as AdditionNodeSpec).left is IntLiteralNodeSpec) shouldBe true
                (valueNode.right is IntLiteralNodeSpec) shouldBe true
            }

            it("should not change AdditionNodeSpec") {
                val value = AdditionNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === ValueNodeSpec.of(value)) shouldBe true
            }

            it("should convert subtraction") {
                val value = SubtractionSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value)
                (valueNode.left is IntLiteralNodeSpec) shouldBe true
                (valueNode.right is IntLiteralNodeSpec) shouldBe true
            }

            it("should convert subtraction (anonymous)") {
                val value = SubtractionSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value as ValueSpec)
                (valueNode is SubtractionNodeSpec) shouldBe true
                ((valueNode as SubtractionNodeSpec).left is IntLiteralNodeSpec) shouldBe true
                (valueNode.right is IntLiteralNodeSpec) shouldBe true
            }

            it("should not change SubtractionNodeSpec") {
                val value = SubtractionNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === ValueNodeSpec.of(value)) shouldBe true
            }

            it("should convert multiplication") {
                val value = MultiplicationSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value)
                (valueNode.left is IntLiteralNodeSpec) shouldBe true
                (valueNode.right is IntLiteralNodeSpec) shouldBe true
            }

            it("should convert multiplication (anonymous)") {
                val value = MultiplicationSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value as ValueSpec)
                (valueNode is MultiplicationNodeSpec) shouldBe true
                ((valueNode as MultiplicationNodeSpec).left is IntLiteralNodeSpec) shouldBe true
                (valueNode.right is IntLiteralNodeSpec) shouldBe true
            }

            it("should not change MultiplicationNodeSpec") {
                val value = MultiplicationNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === ValueNodeSpec.of(value)) shouldBe true
            }

            it("should convert division") {
                val value = DivisionSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value)
                (valueNode.left is IntLiteralNodeSpec) shouldBe true
                (valueNode.right is IntLiteralNodeSpec) shouldBe true
            }

            it("should convert division (anonymous)") {
                val value = DivisionSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value as ValueSpec)
                (valueNode is DivisionNodeSpec) shouldBe true
                ((valueNode as DivisionNodeSpec).left is IntLiteralNodeSpec) shouldBe true
                (valueNode.right is IntLiteralNodeSpec) shouldBe true
            }

            it("should not change DivisionNodeSpec") {
                val value = DivisionNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === ValueNodeSpec.of(value)) shouldBe true
            }

            it("should convert modulo") {
                val value = ModuloSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value)
                (valueNode.left is IntLiteralNodeSpec) shouldBe true
                (valueNode.right is IntLiteralNodeSpec) shouldBe true
            }

            it("should convert modulo (anonymous)") {
                val value = ModuloSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value as ValueSpec)
                (valueNode is ModuloNodeSpec) shouldBe true
                ((valueNode as ModuloNodeSpec).left is IntLiteralNodeSpec) shouldBe true
                (valueNode.right is IntLiteralNodeSpec) shouldBe true
            }

            it("should not change ModuloNodeSpec") {
                val value = ModuloNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === ValueNodeSpec.of(value)) shouldBe true
            }

            it("should convert power") {
                val value = PowerSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value)
                (valueNode.left is IntLiteralNodeSpec) shouldBe true
                (valueNode.right is IntLiteralNodeSpec) shouldBe true
            }

            it("should convert power (anonymous)") {
                val value = PowerSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value as ValueSpec)
                (valueNode is PowerNodeSpec) shouldBe true
                ((valueNode as PowerNodeSpec).left is IntLiteralNodeSpec) shouldBe true
                (valueNode.right is IntLiteralNodeSpec) shouldBe true
            }

            it("should not change PowerNodeSpec") {
                val value = PowerNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === ValueNodeSpec.of(value)) shouldBe true
            }

            it("should convert unary minus") {
                val value = UnaryMinusSpec(IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value)
                (valueNode.value is IntLiteralNodeSpec) shouldBe true
            }

            it("should convert unary minus (anonymous)") {
                val value = UnaryMinusSpec(IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value as ValueSpec)
                (valueNode is UnaryMinusNodeSpec) shouldBe true
                ((valueNode as UnaryMinusNodeSpec).value is IntLiteralNodeSpec) shouldBe true
            }

            it("should not change UnaryMinusNodeSpec") {
                val value = UnaryMinusNodeSpec(IntLiteralNodeSpec(5))
                (value === ValueNodeSpec.of(value)) shouldBe true
            }

            it("should convert unary plus") {
                val value = UnaryPlusSpec(IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value)
                (valueNode.value is IntLiteralNodeSpec) shouldBe true
            }

            it("should convert unary plus (anonymous)") {
                val value = UnaryPlusSpec(IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value as ValueSpec)
                (valueNode is UnaryPlusNodeSpec) shouldBe true
                ((valueNode as UnaryPlusNodeSpec).value is IntLiteralNodeSpec) shouldBe true
            }

            it("should not change UnaryPlusNodeSpec") {
                val value = UnaryPlusNodeSpec(IntLiteralNodeSpec(5))
                (value === ValueNodeSpec.of(value)) shouldBe true
            }

            it("should convert logical and") {
                val value = LogicalAndSpec(BooleanLiteralSpec(true), BooleanLiteralSpec(false))
                val valueNode = ValueNodeSpec.of(value)
                (valueNode.left is BooleanLiteralNodeSpec) shouldBe true
                (valueNode.right is BooleanLiteralNodeSpec) shouldBe true
            }

            it("should convert logical and (anonymous)") {
                val value = LogicalAndSpec(BooleanLiteralSpec(true), BooleanLiteralSpec(false))
                val valueNode = ValueNodeSpec.of(value as ValueSpec)
                (valueNode is LogicalAndNodeSpec) shouldBe true
                ((valueNode as LogicalAndNodeSpec).left is BooleanLiteralNodeSpec) shouldBe true
                (valueNode.right is BooleanLiteralNodeSpec) shouldBe true
            }

            it("should not change LogicalAndNodeSpec") {
                val value = LogicalAndNodeSpec(BooleanLiteralNodeSpec(true), BooleanLiteralNodeSpec(false))
                (value === ValueNodeSpec.of(value)) shouldBe true
            }

            it("should convert logical or") {
                val value = LogicalOrSpec(BooleanLiteralSpec(true), BooleanLiteralSpec(false))
                val valueNode = ValueNodeSpec.of(value)
                (valueNode.left is BooleanLiteralNodeSpec) shouldBe true
                (valueNode.right is BooleanLiteralNodeSpec) shouldBe true
            }

            it("should convert logical or (anonymous)") {
                val value = LogicalOrSpec(BooleanLiteralSpec(true), BooleanLiteralSpec(false))
                val valueNode = ValueNodeSpec.of(value as ValueSpec)
                (valueNode is LogicalOrNodeSpec) shouldBe true
                ((valueNode as LogicalOrNodeSpec).left is BooleanLiteralNodeSpec) shouldBe true
                (valueNode.right is BooleanLiteralNodeSpec) shouldBe true
            }

            it("should not change LogicalOrNodeSpec") {
                val value = LogicalOrNodeSpec(BooleanLiteralNodeSpec(true), BooleanLiteralNodeSpec(false))
                (value === ValueNodeSpec.of(value)) shouldBe true
            }

            it("should convert logical xor") {
                val value = LogicalXorSpec(BooleanLiteralSpec(true), BooleanLiteralSpec(false))
                val valueNode = ValueNodeSpec.of(value)
                (valueNode.left is BooleanLiteralNodeSpec) shouldBe true
                (valueNode.right is BooleanLiteralNodeSpec) shouldBe true
            }

            it("should convert logical xor (anonymous)") {
                val value = LogicalXorSpec(BooleanLiteralSpec(true), BooleanLiteralSpec(false))
                val valueNode = ValueNodeSpec.of(value as ValueSpec)
                (valueNode is LogicalXorNodeSpec) shouldBe true
                ((valueNode as LogicalXorNodeSpec).left is BooleanLiteralNodeSpec) shouldBe true
                (valueNode.right is BooleanLiteralNodeSpec) shouldBe true
            }

            it("should not change LogicalXorNodeSpec") {
                val value = LogicalXorNodeSpec(BooleanLiteralNodeSpec(true), BooleanLiteralNodeSpec(false))
                (value === ValueNodeSpec.of(value)) shouldBe true
            }

            it("should convert logical not") {
                val value = LogicalNotSpec(BooleanLiteralSpec(true))
                val valueNode = ValueNodeSpec.of(value)
                (valueNode.value is BooleanLiteralNodeSpec) shouldBe true
            }

            it("should convert logical not (anonymous)") {
                val value = LogicalNotSpec(BooleanLiteralSpec(true))
                val valueNode = ValueNodeSpec.of(value as ValueSpec)
                (valueNode is LogicalNotNodeSpec) shouldBe true
                ((valueNode as LogicalNotNodeSpec).value is BooleanLiteralNodeSpec) shouldBe true
            }

            it("should not change LogicalNotNodeSpec") {
                val value = LogicalNotNodeSpec(BooleanLiteralNodeSpec(true))
                (value === ValueNodeSpec.of(value)) shouldBe true
            }

            it("should convert equality") {
                val value = EqualitySpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value)
                (valueNode.left is IntLiteralNodeSpec) shouldBe true
                (valueNode.right is IntLiteralNodeSpec) shouldBe true
            }

            it("should convert equality (anonymous)") {
                val value = EqualitySpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value as ValueSpec)
                (valueNode is EqualityNodeSpec) shouldBe true
                ((valueNode as EqualityNodeSpec).left is IntLiteralNodeSpec) shouldBe true
                (valueNode.right is IntLiteralNodeSpec) shouldBe true
            }

            it("should not change EqualityNodeSpec") {
                val value = EqualityNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === ValueNodeSpec.of(value)) shouldBe true
            }

            it("should convert inequality") {
                val value = InequalitySpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value)
                (valueNode.left is IntLiteralNodeSpec) shouldBe true
                (valueNode.right is IntLiteralNodeSpec) shouldBe true
            }

            it("should convert inequality (anonymous)") {
                val value = InequalitySpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value as ValueSpec)
                (valueNode is InequalityNodeSpec) shouldBe true
                ((valueNode as InequalityNodeSpec).left is IntLiteralNodeSpec) shouldBe true
                (valueNode.right is IntLiteralNodeSpec) shouldBe true
            }

            it("should not change InequalityNodeSpec") {
                val value = InequalityNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === ValueNodeSpec.of(value)) shouldBe true
            }

            it("should convert greater than") {
                val value = GreaterThanSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value)
                (valueNode.left is IntLiteralNodeSpec) shouldBe true
                (valueNode.right is IntLiteralNodeSpec) shouldBe true
            }

            it("should convert greater than (anonymous)") {
                val value = GreaterThanSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value as ValueSpec)
                (valueNode is GreaterThanNodeSpec) shouldBe true
                ((valueNode as GreaterThanNodeSpec).left is IntLiteralNodeSpec) shouldBe true
                (valueNode.right is IntLiteralNodeSpec) shouldBe true
            }

            it("should not change GreaterThanNodeSpec") {
                val value = GreaterThanNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === ValueNodeSpec.of(value)) shouldBe true
            }

            it("should convert greater than or equal") {
                val value = GreaterThanOrEqualSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value)
                (valueNode.left is IntLiteralNodeSpec) shouldBe true
                (valueNode.right is IntLiteralNodeSpec) shouldBe true
            }

            it("should convert greater than or equal (anonymous)") {
                val value = GreaterThanOrEqualSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value as ValueSpec)
                (valueNode is GreaterThanOrEqualNodeSpec) shouldBe true
                ((valueNode as GreaterThanOrEqualNodeSpec).left is IntLiteralNodeSpec) shouldBe true
                (valueNode.right is IntLiteralNodeSpec) shouldBe true
            }

            it("should not change GreaterThanOrEqualityNodeSpec") {
                val value = GreaterThanOrEqualNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === ValueNodeSpec.of(value)) shouldBe true
            }

            it("should convert less than") {
                val value = LessThanSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value)
                (valueNode.left is IntLiteralNodeSpec) shouldBe true
                (valueNode.right is IntLiteralNodeSpec) shouldBe true
            }

            it("should convert less than (anonymous)") {
                val value = LessThanSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value as ValueSpec)
                (valueNode is LessThanNodeSpec) shouldBe true
                ((valueNode as LessThanNodeSpec).left is IntLiteralNodeSpec) shouldBe true
                (valueNode.right is IntLiteralNodeSpec) shouldBe true
            }

            it("should not change LessThanNodeSpec") {
                val value = LessThanNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === ValueNodeSpec.of(value)) shouldBe true
            }

            it("should convert less than or equal") {
                val value = LessThanOrEqualSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value)
                (valueNode.left is IntLiteralNodeSpec) shouldBe true
                (valueNode.right is IntLiteralNodeSpec) shouldBe true
            }

            it("should convert less than or equal (anonymous)") {
                val value = LessThanOrEqualSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value as ValueSpec)
                (valueNode is LessThanOrEqualNodeSpec) shouldBe true
                ((valueNode as LessThanOrEqualNodeSpec).left is IntLiteralNodeSpec) shouldBe true
                (valueNode.right is IntLiteralNodeSpec) shouldBe true
            }

            it("should not change LessThanOrEqualityNodeSpec") {
                val value = LessThanOrEqualNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === ValueNodeSpec.of(value)) shouldBe true
            }

            it("should convert bitwise and") {
                val value = BitwiseAndSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value)
                (valueNode.left is IntLiteralNodeSpec) shouldBe true
                (valueNode.right is IntLiteralNodeSpec) shouldBe true
            }

            it("should convert bitwise and (anonymous)") {
                val value = BitwiseAndSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value as ValueSpec)
                (valueNode is BitwiseAndNodeSpec) shouldBe true
                ((valueNode as BitwiseAndNodeSpec).left is IntLiteralNodeSpec) shouldBe true
                (valueNode.right is IntLiteralNodeSpec) shouldBe true
            }

            it("should not change BitwiseAndNodeSpec") {
                val value = BitwiseAndNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === ValueNodeSpec.of(value)) shouldBe true
            }

            it("should convert bitwise or") {
                val value = BitwiseOrSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value)
                (valueNode.left is IntLiteralNodeSpec) shouldBe true
                (valueNode.right is IntLiteralNodeSpec) shouldBe true
            }

            it("should convert bitwise or (anonymous)") {
                val value = BitwiseOrSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value as ValueSpec)
                (valueNode is BitwiseOrNodeSpec) shouldBe true
                ((valueNode as BitwiseOrNodeSpec).left is IntLiteralNodeSpec) shouldBe true
                (valueNode.right is IntLiteralNodeSpec) shouldBe true
            }

            it("should not change BitwiseOrNodeSpec") {
                val value = BitwiseOrNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === ValueNodeSpec.of(value)) shouldBe true
            }

            it("should convert bitwise xor") {
                val value = BitwiseXorSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value)
                (valueNode.left is IntLiteralNodeSpec) shouldBe true
                (valueNode.right is IntLiteralNodeSpec) shouldBe true
            }

            it("should convert bitwise xor (anonymous)") {
                val value = BitwiseXorSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value as ValueSpec)
                (valueNode is BitwiseXorNodeSpec) shouldBe true
                ((valueNode as BitwiseXorNodeSpec).left is IntLiteralNodeSpec) shouldBe true
                (valueNode.right is IntLiteralNodeSpec) shouldBe true
            }

            it("should not change BitwiseXorNodeSpec") {
                val value = BitwiseXorNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === ValueNodeSpec.of(value)) shouldBe true
            }

            it("should convert bitwise not") {
                val value = BitwiseNotSpec(IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value)
                (valueNode.value is IntLiteralNodeSpec) shouldBe true
            }

            it("should convert bitwise not (anonymous)") {
                val value = BitwiseNotSpec(IntLiteralSpec(5))
                val valueNode = ValueNodeSpec.of(value as ValueSpec)
                (valueNode is BitwiseNotNodeSpec) shouldBe true
                ((valueNode as BitwiseNotNodeSpec).value is IntLiteralNodeSpec) shouldBe true
            }

            it("should not change BitwiseNotNodeSpec") {
                val value = BitwiseNotNodeSpec(IntLiteralNodeSpec(5))
                (value === ValueNodeSpec.of(value)) shouldBe true
            }
        }
    }

    describe("StringLiteralNodeSpec") {

        describe("constructor") {
            it("should create StringLiteralNodeSpec") {
                val value = StringLiteralNodeSpec("Hello World")
                value.value shouldBe "Hello World"
            }
        }

        describe("of()") {
            it("should not change StringLiteralNodeSpec") {
                val value = StringLiteralNodeSpec("Hello World")
                (value === StringLiteralNodeSpec.of(value)) shouldBe true
            }

            it("should convert string literal") {
                val value = StringLiteralSpec("Hello World")
                val valueNode = StringLiteralNodeSpec.of(value)
                valueNode.value shouldBe "Hello World"
            }
        }

        describe("dump") {
            it("should dump the correct output") {
                val value = StringLiteralNodeSpec("Hello World")
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "\"Hello World\""
            }

            it("should dump the correct output (escaped)") {
                val value = StringLiteralNodeSpec("Hello \"World\"")
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "\"Hello \\\"World\\\"\""
            }

            it("should output correct node") {
                val value = StringLiteralNodeSpec("Hello World")
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                node.value shouldBe "Hello World"
                node.valueToken.start shouldBe 0
                node.valueToken.end shouldBe 12
                node.valueToken.value shouldBe "\"Hello World\""
            }

            it("should output correct node (escaped)") {
                val value = StringLiteralNodeSpec("Hello \"World\"")
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                node.value shouldBe "Hello \"World\""
                node.valueToken.start shouldBe 0
                node.valueToken.end shouldBe 16
                node.valueToken.value shouldBe "\"Hello \\\"World\\\"\""
            }
        }
    }

    describe("IntLiteralNodeSpec") {

        describe("constructor") {
            it("should create IntLiteralNodeSpec") {
                val value = IntLiteralNodeSpec(5)
                value.value shouldBe 5
            }
        }

        describe("of()") {
            it("should not change IntLiteralNodeSpec") {
                val value = IntLiteralNodeSpec(5)
                (value === IntLiteralNodeSpec.of(value)) shouldBe true
            }

            it("should convert int literal") {
                val value = IntLiteralSpec(5)
                val valueNode = IntLiteralNodeSpec.of(value)
                valueNode.value shouldBe 5
            }
        }

        describe("dump") {
            it("should dump the correct output") {
                val value = IntLiteralNodeSpec(5)
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "5"
            }

            it("should output correct node") {
                val value = IntLiteralNodeSpec(5)
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                (node is ShakeIntegerLiteralNode) shouldBe true
                (node as ShakeIntegerLiteralNode).value shouldBe 5
                node.valueToken.start shouldBe 0
                node.valueToken.end shouldBe 0
                node.valueToken.value shouldBe "5"
            }

            it("should dump the correct output (negative)") {
                val value = IntLiteralNodeSpec(-5)
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "-5"
            }

            it("should output correct node (negative)") {
                val value = IntLiteralNodeSpec(-5)
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                (node is ShakeUnaryMinusNode) shouldBe true
                (node as ShakeUnaryMinusNode).operatorToken.start shouldBe 0
                node.operatorToken.end shouldBe 0
                node.operatorToken.value shouldBe "-"

                (node.value is ShakeIntegerLiteralNode) shouldBe true
                val valueNode = node.value as ShakeIntegerLiteralNode
                valueNode.value shouldBe 5
                valueNode.valueToken.start shouldBe 1
                valueNode.valueToken.end shouldBe 1
                valueNode.valueToken.value shouldBe "5"
            }
        }
    }

    describe("FloatLiteralNodeSpec") {

        describe("constructor") {
            it("should create FloatLiteralNodeSpec") {
                val value = FloatLiteralNodeSpec(5.5)
                value.value shouldBe 5.5
            }
        }

        describe("of()") {
            it("should not change FloatLiteralNodeSpec") {
                val value = FloatLiteralNodeSpec(5.5)
                (value === FloatLiteralNodeSpec.of(value)) shouldBe true
            }

            it("should convert float literal") {
                val value = FloatLiteralSpec(5.5)
                val valueNode = FloatLiteralNodeSpec.of(value)
                valueNode.value shouldBe 5.5
            }
        }

        describe("dump") {
            it("should dump the correct output") {
                val value = FloatLiteralNodeSpec(5.5)
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "5.5"
            }

            it("should output correct node") {
                val value = FloatLiteralNodeSpec(5.5)
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                (node is ShakeFloatLiteralNode) shouldBe true
                (node as ShakeFloatLiteralNode).value shouldBe 5.5
                node.valueToken.start shouldBe 0
                node.valueToken.end shouldBe 2
                node.valueToken.value shouldBe "5.5"
            }

            it("should dump the correct output (negative)") {
                val value = FloatLiteralNodeSpec(-5.5)
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "-5.5"
            }

            it("should output correct node (negative)") {
                val value = FloatLiteralNodeSpec(-5.5)
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                (node is ShakeUnaryMinusNode) shouldBe true
                (node as ShakeUnaryMinusNode).operatorToken.start shouldBe 0
                node.operatorToken.end shouldBe 0
                node.operatorToken.value shouldBe "-"

                (node.value is ShakeFloatLiteralNode) shouldBe true
                val valueNode = node.value as ShakeFloatLiteralNode
                valueNode.value shouldBe 5.5
                valueNode.valueToken.start shouldBe 1
                valueNode.valueToken.end shouldBe 3
                valueNode.valueToken.value shouldBe "5.5"
            }
        }
    }

    describe("CharacterLiteralNodeSpec") {

        describe("constructor") {
            it("should create CharacterLiteralNodeSpec") {
                val value = CharacterLiteralNodeSpec('a')
                value.value shouldBe 'a'
            }
        }

        describe("of()") {
            it("should not change CharacterLiteralNodeSpec") {
                val value = CharacterLiteralNodeSpec('a')
                (value === CharacterLiteralNodeSpec.of(value)) shouldBe true
            }

            it("should convert character literal") {
                val value = CharacterLiteralSpec('a')
                val valueNode = CharacterLiteralNodeSpec.of(value)
                valueNode.value shouldBe 'a'
            }
        }

        describe("dump") {
            it("should dump the correct output") {
                val value = CharacterLiteralNodeSpec('a')
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "'a'"
            }

            it("should output correct node") {
                val value = CharacterLiteralNodeSpec('a')
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                node.value shouldBe 'a'
                node.valueToken.start shouldBe 0
                node.valueToken.end shouldBe 2
                node.valueToken.value shouldBe "'a'"
            }

            it("should dump the correct output (escaped)") {
                val value = CharacterLiteralNodeSpec('\'')
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "'\\''"
            }

            it("should output correct node (escaped)") {
                val value = CharacterLiteralNodeSpec('\'')
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                node.value shouldBe '\''
                node.valueToken.start shouldBe 0
                node.valueToken.end shouldBe 3
                node.valueToken.value shouldBe "'\\''"
            }
        }
    }

    describe("BooleanLiteralNodeSpec") {

        describe("constructor") {
            it("should create BooleanLiteralNodeSpec") {
                val value = BooleanLiteralNodeSpec(true)
                value.value shouldBe true
            }
        }

        describe("of()") {
            it("should not change BooleanLiteralNodeSpec") {
                val value = BooleanLiteralNodeSpec(true)
                (value === BooleanLiteralNodeSpec.of(value)) shouldBe true
            }

            it("should convert boolean literal") {
                val value = BooleanLiteralSpec(true)
                val valueNode = BooleanLiteralNodeSpec.of(value)
                valueNode.value shouldBe true
            }
        }

        describe("dump") {
            it("should dump the correct output (true)") {
                val value = BooleanLiteralNodeSpec(true)
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "true"
            }

            it("should output correct node (true)") {
                val value = BooleanLiteralNodeSpec(true)
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                (node is ShakeTrueLiteralNode) shouldBe true
                (node as ShakeTrueLiteralNode).valueToken.start shouldBe 0
                node.valueToken.end shouldBe 3
                node.valueToken.value shouldBe "true"
            }

            it("should dump the correct output (false)") {
                val value = BooleanLiteralNodeSpec(false)
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "false"
            }

            it("should output correct node (false)") {
                val value = BooleanLiteralNodeSpec(false)
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                (node is ShakeFalseLiteralNode) shouldBe true
                (node as ShakeFalseLiteralNode).valueToken.start shouldBe 0
                node.valueToken.end shouldBe 4
                node.valueToken.value shouldBe "false"
            }
        }
    }

    describe("NullLiteralNodeSpec") {

        describe("constructor") {
            it("should create NullLiteralNodeSpec") {
                NullLiteralNodeSpec()
            }
        }

        describe("of()") {
            it("should not change NullLiteralNodeSpec") {
                val value = NullLiteralNodeSpec()
                (value === NullLiteralNodeSpec.of(value)) shouldBe true
            }

            it("should convert null literal") {
                val value = NullLiteralSpec()
                NullLiteralNodeSpec.of(value)
            }
        }

        describe("dump") {
            it("should dump the correct output") {
                val value = NullLiteralNodeSpec()
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "null"
            }

            it("should output correct node") {
                val value = NullLiteralNodeSpec()
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                node.valueToken.start shouldBe 0
                node.valueToken.end shouldBe 3
                node.valueToken.value shouldBe "null"
            }
        }
    }

    describe("VariableReferenceNodeSpec") {

        describe("constructor") {
            it("should create VariableReferenceNodeSpec") {
                val value = VariableReferenceNodeSpec("name")
                value.name.name shouldBe arrayOf("name")
            }

            it("should create VariableReferenceNodeSpec (multiple)") {
                val value = VariableReferenceNodeSpec("name", "name2")
                value.name.name shouldBe arrayOf("name", "name2")
            }
        }

        describe("of()") {
            it("should not change VariableReferenceNodeSpec") {
                val value = VariableReferenceNodeSpec("name")
                (value === VariableReferenceNodeSpec.of(value)) shouldBe true
            }

            it("should convert variable reference") {
                val value = VariableReferenceSpec("name")
                val valueNode = VariableReferenceNodeSpec.of(value)
                valueNode.name.name shouldBe arrayOf("name")
            }
        }

        describe("dump") {
            it("should dump the correct output") {
                val value = VariableReferenceNodeSpec("name")
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "name"
            }

            it("should output correct node") {
                val value = VariableReferenceNodeSpec("name")
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                node.identifier.name shouldBe "name"
                node.identifier.nameToken.start shouldBe 0
                node.identifier.nameToken.end shouldBe 3
                node.identifier.nameToken.value shouldBe "name"
                node.identifier.parent shouldBe null
                node.identifier.dotToken shouldBe null
            }

            it("should dump the correct output (multiple)") {
                val value = VariableReferenceNodeSpec("name", "name2")
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "name.name2"
            }

            it("should output correct node (multiple)") {
                val value = VariableReferenceNodeSpec("name", "name2")
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                node.identifier.name shouldBe "name2"
                node.identifier.nameToken.start shouldBe 5
                node.identifier.nameToken.end shouldBe 9
                node.identifier.nameToken.value shouldBe "name2"
                node.identifier.dotToken shouldNotBe null
                node.identifier.dotToken!!.start shouldBe 4
                node.identifier.dotToken!!.end shouldBe 4
                node.identifier.dotToken!!.value shouldBe "."
                node.identifier.parent shouldNotBe null
                (node.identifier.parent is ShakeVariableUsageNode) shouldBe true
                val parent = node.identifier.parent as ShakeVariableUsageNode
                parent.identifier.name shouldBe "name"
                parent.identifier.nameToken.start shouldBe 0
                parent.identifier.nameToken.end shouldBe 3
                parent.identifier.nameToken.value shouldBe "name"
                parent.identifier.dotToken shouldBe null
                parent.identifier.parent shouldBe null
            }
        }
    }

    describe("AdditionNodeSpec") {

        describe("constructor") {
            it("should create AdditionNodeSpec") {
                val value = AdditionNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value.left as IntLiteralNodeSpec).value shouldBe 5
                (value.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("of()") {
            it("should not change AdditionNodeSpec") {
                val value = AdditionNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === AdditionNodeSpec.of(value)) shouldBe true
            }

            it("should convert addition") {
                val value = AdditionSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = AdditionNodeSpec.of(value)
                (valueNode.left as IntLiteralNodeSpec).value shouldBe 5
                (valueNode.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("dump") {
            it("should dump the correct output") {
                val value = AdditionNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "5 + 5"
            }

            it("should output correct node") {
                val value = AdditionNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                (node.left is ShakeIntegerLiteralNode) shouldBe true
                (node.left as ShakeIntegerLiteralNode).value shouldBe 5
                node.operatorToken.start shouldBe 2
                node.operatorToken.end shouldBe 2
                node.operatorToken.value shouldBe "+"
                (node.right is ShakeIntegerLiteralNode) shouldBe true
                (node.right as ShakeIntegerLiteralNode).value shouldBe 5
            }
        }
    }

    describe("SubtractionNodeSpec") {

        describe("constructor") {
            it("should create SubtractionNodeSpec") {
                val value = SubtractionNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value.left as IntLiteralNodeSpec).value shouldBe 5
                (value.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("of()") {
            it("should not change SubtractionNodeSpec") {
                val value = SubtractionNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === SubtractionNodeSpec.of(value)) shouldBe true
            }

            it("should convert subtraction") {
                val value = SubtractionSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = SubtractionNodeSpec.of(value)
                (valueNode.left as IntLiteralNodeSpec).value shouldBe 5
                (valueNode.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("dump") {
            it("should dump the correct output") {
                val value = SubtractionNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "5 - 5"
            }

            it("should output correct node") {
                val value = SubtractionNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                (node.left is ShakeIntegerLiteralNode) shouldBe true
                (node.left as ShakeIntegerLiteralNode).value shouldBe 5
                node.operatorToken.start shouldBe 2
                node.operatorToken.end shouldBe 2
                node.operatorToken.value shouldBe "-"
                (node.right is ShakeIntegerLiteralNode) shouldBe true
                (node.right as ShakeIntegerLiteralNode).value shouldBe 5
            }
        }
    }

    describe("MultiplicationNodeSpec") {

        describe("constructor") {
            it("should create MultiplicationNodeSpec") {
                val value = MultiplicationNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value.left as IntLiteralNodeSpec).value shouldBe 5
                (value.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("of()") {
            it("should not change MultiplicationNodeSpec") {
                val value = MultiplicationNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === MultiplicationNodeSpec.of(value)) shouldBe true
            }

            it("should convert multiplication") {
                val value = MultiplicationSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = MultiplicationNodeSpec.of(value)
                (valueNode.left as IntLiteralNodeSpec).value shouldBe 5
                (valueNode.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("dump") {
            it("should dump the correct output") {
                val value = MultiplicationNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "5 * 5"
            }

            it("should output correct node") {
                val value = MultiplicationNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                (node.left is ShakeIntegerLiteralNode) shouldBe true
                (node.left as ShakeIntegerLiteralNode).value shouldBe 5
                node.operatorToken.start shouldBe 2
                node.operatorToken.end shouldBe 2
                node.operatorToken.value shouldBe "*"
                (node.right is ShakeIntegerLiteralNode) shouldBe true
                (node.right as ShakeIntegerLiteralNode).value shouldBe 5
            }
        }
    }

    describe("DivisionNodeSpec") {

        describe("constructor") {
            it("should create DivisionNodeSpec") {
                val value = DivisionNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value.left as IntLiteralNodeSpec).value shouldBe 5
                (value.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("of()") {
            it("should not change DivisionNodeSpec") {
                val value = DivisionNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === DivisionNodeSpec.of(value)) shouldBe true
            }

            it("should convert division") {
                val value = DivisionSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = DivisionNodeSpec.of(value)
                (valueNode.left as IntLiteralNodeSpec).value shouldBe 5
                (valueNode.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("dump") {
            it("should dump the correct output") {
                val value = DivisionNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "5 / 5"
            }

            it("should output correct node") {
                val value = DivisionNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                (node.left is ShakeIntegerLiteralNode) shouldBe true
                (node.left as ShakeIntegerLiteralNode).value shouldBe 5
                node.operatorToken.start shouldBe 2
                node.operatorToken.end shouldBe 2
                node.operatorToken.value shouldBe "/"
                (node.right is ShakeIntegerLiteralNode) shouldBe true
                (node.right as ShakeIntegerLiteralNode).value shouldBe 5
            }
        }
    }

    describe("ModuloNodeSpec") {

        describe("constructor") {
            it("should create ModuloNodeSpec") {
                val value = ModuloNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value.left as IntLiteralNodeSpec).value shouldBe 5
                (value.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("of()") {
            it("should not change ModuloNodeSpec") {
                val value = ModuloNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === ModuloNodeSpec.of(value)) shouldBe true
            }

            it("should convert modulo") {
                val value = ModuloSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = ModuloNodeSpec.of(value)
                (valueNode.left as IntLiteralNodeSpec).value shouldBe 5
                (valueNode.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("dump") {
            it("should dump the correct output") {
                val value = ModuloNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "5 % 5"
            }

            it("should output correct node") {
                val value = ModuloNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                (node.left is ShakeIntegerLiteralNode) shouldBe true
                (node.left as ShakeIntegerLiteralNode).value shouldBe 5
                node.operatorToken.start shouldBe 2
                node.operatorToken.end shouldBe 2
                node.operatorToken.value shouldBe "%"
                (node.right is ShakeIntegerLiteralNode) shouldBe true
                (node.right as ShakeIntegerLiteralNode).value shouldBe 5
            }
        }
    }

    describe("UnaryMinusNodeSpec") {

        describe("constructor") {
            it("should create UnaryMinusNodeSpec") {
                val value = UnaryMinusNodeSpec(IntLiteralNodeSpec(5))
                (value.value as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("of()") {
            it("should not change UnaryMinusNodeSpec") {
                val value = UnaryMinusNodeSpec(IntLiteralNodeSpec(5))
                (value === UnaryMinusNodeSpec.of(value)) shouldBe true
            }

            it("should convert unary minus") {
                val value = UnaryMinusSpec(IntLiteralSpec(5))
                val valueNode = UnaryMinusNodeSpec.of(value)
                (valueNode.value as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("dump") {
            it("should dump the correct output") {
                val value = UnaryMinusNodeSpec(IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "-5"
            }

            it("should output correct node") {
                val value = UnaryMinusNodeSpec(IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                node.operatorToken.start shouldBe 0
                node.operatorToken.end shouldBe 0
                node.operatorToken.value shouldBe "-"
                (node.value is ShakeIntegerLiteralNode) shouldBe true
                (node.value as ShakeIntegerLiteralNode).value shouldBe 5
            }
        }
    }

    describe("LogicalAndNodeSpec") {

        describe("constructor") {
            it("should create LogicalAndNodeSpec") {
                val value = LogicalAndNodeSpec(BooleanLiteralNodeSpec(true), BooleanLiteralNodeSpec(false))
                (value.left as BooleanLiteralNodeSpec).value shouldBe true
                (value.right as BooleanLiteralNodeSpec).value shouldBe false
            }
        }

        describe("of()") {
            it("should not change LogicalAndNodeSpec") {
                val value = LogicalAndNodeSpec(BooleanLiteralNodeSpec(true), BooleanLiteralNodeSpec(false))
                (value === LogicalAndNodeSpec.of(value)) shouldBe true
            }

            it("should convert logical and") {
                val value = LogicalAndSpec(BooleanLiteralSpec(true), BooleanLiteralSpec(false))
                val valueNode = LogicalAndNodeSpec.of(value)
                (valueNode.left as BooleanLiteralNodeSpec).value shouldBe true
                (valueNode.right as BooleanLiteralNodeSpec).value shouldBe false
            }
        }

        describe("dump") {
            it("should dump the correct output") {
                val value = LogicalAndNodeSpec(BooleanLiteralNodeSpec(true), BooleanLiteralNodeSpec(false))
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "true && false"
            }

            it("should output correct node") {
                val value = LogicalAndNodeSpec(BooleanLiteralNodeSpec(true), BooleanLiteralNodeSpec(false))
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                (node.left is ShakeTrueLiteralNode) shouldBe true
                (node.left as ShakeTrueLiteralNode).valueToken.start shouldBe 0
                node.operatorToken.start shouldBe 5
                node.operatorToken.end shouldBe 6
                node.operatorToken.value shouldBe "&&"
                (node.right is ShakeFalseLiteralNode) shouldBe true
                (node.right as ShakeFalseLiteralNode).valueToken.start shouldBe 8
            }
        }
    }

    describe("LogicalOrNodeSpec") {

        describe("constructor") {
            it("should create LogicalOrNodeSpec") {
                val value = LogicalOrNodeSpec(BooleanLiteralNodeSpec(true), BooleanLiteralNodeSpec(false))
                (value.left as BooleanLiteralNodeSpec).value shouldBe true
                (value.right as BooleanLiteralNodeSpec).value shouldBe false
            }
        }

        describe("of()") {
            it("should not change LogicalOrNodeSpec") {
                val value = LogicalOrNodeSpec(BooleanLiteralNodeSpec(true), BooleanLiteralNodeSpec(false))
                (value === LogicalOrNodeSpec.of(value)) shouldBe true
            }

            it("should convert logical or") {
                val value = LogicalOrSpec(BooleanLiteralSpec(true), BooleanLiteralSpec(false))
                val valueNode = LogicalOrNodeSpec.of(value)
                (valueNode.left as BooleanLiteralNodeSpec).value shouldBe true
                (valueNode.right as BooleanLiteralNodeSpec).value shouldBe false
            }
        }

        describe("dump") {
            it("should dump the correct output") {
                val value = LogicalOrNodeSpec(BooleanLiteralNodeSpec(true), BooleanLiteralNodeSpec(false))
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "true || false"
            }

            it("should output correct node") {
                val value = LogicalOrNodeSpec(BooleanLiteralNodeSpec(true), BooleanLiteralNodeSpec(false))
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                (node.left is ShakeTrueLiteralNode) shouldBe true
                (node.left as ShakeTrueLiteralNode).valueToken.start shouldBe 0
                node.operatorToken.start shouldBe 5
                node.operatorToken.end shouldBe 6
                node.operatorToken.value shouldBe "||"
                (node.right is ShakeFalseLiteralNode) shouldBe true
                (node.right as ShakeFalseLiteralNode).valueToken.start shouldBe 8
            }
        }
    }

    describe("LogicalXorNodeSpec") {

        describe("constructor") {
            it("should create LogicalXorNodeSpec") {
                val value = LogicalXorNodeSpec(BooleanLiteralNodeSpec(true), BooleanLiteralNodeSpec(false))
                (value.left as BooleanLiteralNodeSpec).value shouldBe true
                (value.right as BooleanLiteralNodeSpec).value shouldBe false
            }
        }

        describe("of()") {
            it("should not change LogicalXorNodeSpec") {
                val value = LogicalXorNodeSpec(BooleanLiteralNodeSpec(true), BooleanLiteralNodeSpec(false))
                (value === LogicalXorNodeSpec.of(value)) shouldBe true
            }

            it("should convert logical xor") {
                val value = LogicalXorSpec(BooleanLiteralSpec(true), BooleanLiteralSpec(false))
                val valueNode = LogicalXorNodeSpec.of(value)
                (valueNode.left as BooleanLiteralNodeSpec).value shouldBe true
                (valueNode.right as BooleanLiteralNodeSpec).value shouldBe false
            }
        }

        describe("dump") {
            it("should dump the correct output") {
                val value = LogicalXorNodeSpec(BooleanLiteralNodeSpec(true), BooleanLiteralNodeSpec(false))
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "true ^^ false"
            }

            it("should output correct node") {
                val value = LogicalXorNodeSpec(BooleanLiteralNodeSpec(true), BooleanLiteralNodeSpec(false))
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                (node.left is ShakeTrueLiteralNode) shouldBe true
                (node.left as ShakeTrueLiteralNode).valueToken.start shouldBe 0
                node.operatorToken.start shouldBe 5
                node.operatorToken.end shouldBe 6
                node.operatorToken.value shouldBe "^^"
                (node.right is ShakeFalseLiteralNode) shouldBe true
                (node.right as ShakeFalseLiteralNode).valueToken.start shouldBe 8
            }
        }
    }

    describe("EqualityNodeSpec") {

        describe("constructor") {
            it("should create EqualityNodeSpec") {
                val value = EqualityNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value.left as IntLiteralNodeSpec).value shouldBe 5
                (value.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("of()") {
            it("should not change EqualityNodeSpec") {
                val value = EqualityNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === EqualityNodeSpec.of(value)) shouldBe true
            }

            it("should convert equal") {
                val value = EqualitySpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = EqualityNodeSpec.of(value)
                (valueNode.left as IntLiteralNodeSpec).value shouldBe 5
                (valueNode.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("dump") {
            it("should dump the correct output") {
                val value = EqualityNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "5 == 5"
            }

            it("should output correct node") {
                val value = EqualityNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                (node.left is ShakeIntegerLiteralNode) shouldBe true
                (node.left as ShakeIntegerLiteralNode).value shouldBe 5
                node.operatorToken.start shouldBe 2
                node.operatorToken.end shouldBe 3
                node.operatorToken.value shouldBe "=="
                (node.right is ShakeIntegerLiteralNode) shouldBe true
                (node.right as ShakeIntegerLiteralNode).value shouldBe 5
            }
        }
    }

    describe("InequalityNodeSpec") {

        describe("constructor") {
            it("should create InequalityNodeSpec") {
                val value = InequalityNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value.left as IntLiteralNodeSpec).value shouldBe 5
                (value.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("of()") {
            it("should not change InequalityNodeSpec") {
                val value = InequalityNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === InequalityNodeSpec.of(value)) shouldBe true
            }

            it("should convert not equal") {
                val value = InequalitySpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = InequalityNodeSpec.of(value)
                (valueNode.left as IntLiteralNodeSpec).value shouldBe 5
                (valueNode.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("dump") {
            it("should dump the correct output") {
                val value = InequalityNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "5 != 5"
            }

            it("should output correct node") {
                val value = InequalityNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                (node.left is ShakeIntegerLiteralNode) shouldBe true
                (node.left as ShakeIntegerLiteralNode).value shouldBe 5
                node.operatorToken.start shouldBe 2
                node.operatorToken.end shouldBe 3
                node.operatorToken.value shouldBe "!="
                (node.right is ShakeIntegerLiteralNode) shouldBe true
                (node.right as ShakeIntegerLiteralNode).value shouldBe 5
            }
        }
    }

    describe("LessThanNodeSpec") {

        describe("constructor") {
            it("should create LessThanNodeSpec") {
                val value = LessThanNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value.left as IntLiteralNodeSpec).value shouldBe 5
                (value.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("of()") {
            it("should not change LessThanNodeSpec") {
                val value = LessThanNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === LessThanNodeSpec.of(value)) shouldBe true
            }

            it("should convert less than") {
                val value = LessThanSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = LessThanNodeSpec.of(value)
                (valueNode.left as IntLiteralNodeSpec).value shouldBe 5
                (valueNode.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("dump") {
            it("should dump the correct output") {
                val value = LessThanNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "5 < 5"
            }

            it("should output correct node") {
                val value = LessThanNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                (node.left is ShakeIntegerLiteralNode) shouldBe true
                (node.left as ShakeIntegerLiteralNode).value shouldBe 5
                node.operatorToken.start shouldBe 2
                node.operatorToken.end shouldBe 2
                node.operatorToken.value shouldBe "<"
                (node.right is ShakeIntegerLiteralNode) shouldBe true
                (node.right as ShakeIntegerLiteralNode).value shouldBe 5
            }
        }
    }

    describe("LessThanOrEqualNodeSpec") {

        describe("constructor") {
            it("should create LessThanOrEqualNodeSpec") {
                val value = LessThanOrEqualNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value.left as IntLiteralNodeSpec).value shouldBe 5
                (value.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("of()") {
            it("should not change LessThanOrEqualNodeSpec") {
                val value = LessThanOrEqualNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === LessThanOrEqualNodeSpec.of(value)) shouldBe true
            }

            it("should convert less than or equal") {
                val value = LessThanOrEqualSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = LessThanOrEqualNodeSpec.of(value)
                (valueNode.left as IntLiteralNodeSpec).value shouldBe 5
                (valueNode.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("dump") {
            it("should dump the correct output") {
                val value = LessThanOrEqualNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "5 <= 5"
            }

            it("should output correct node") {
                val value = LessThanOrEqualNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                (node.left is ShakeIntegerLiteralNode) shouldBe true
                (node.left as ShakeIntegerLiteralNode).value shouldBe 5
                node.operatorToken.start shouldBe 2
                node.operatorToken.end shouldBe 3
                node.operatorToken.value shouldBe "<="
                (node.right is ShakeIntegerLiteralNode) shouldBe true
                (node.right as ShakeIntegerLiteralNode).value shouldBe 5
            }
        }
    }

    describe("GreaterThanNodeSpec") {

        describe("constructor") {
            it("should create GreaterThanNodeSpec") {
                val value = GreaterThanNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value.left as IntLiteralNodeSpec).value shouldBe 5
                (value.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("of()") {
            it("should not change GreaterThanNodeSpec") {
                val value = GreaterThanNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === GreaterThanNodeSpec.of(value)) shouldBe true
            }

            it("should convert greater than") {
                val value = GreaterThanSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = GreaterThanNodeSpec.of(value)
                (valueNode.left as IntLiteralNodeSpec).value shouldBe 5
                (valueNode.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("dump") {
            it("should dump the correct output") {
                val value = GreaterThanNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "5 > 5"
            }

            it("should output correct node") {
                val value = GreaterThanNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                (node.left is ShakeIntegerLiteralNode) shouldBe true
                (node.left as ShakeIntegerLiteralNode).value shouldBe 5
                node.operatorToken.start shouldBe 2
                node.operatorToken.end shouldBe 2
                node.operatorToken.value shouldBe ">"
                (node.right is ShakeIntegerLiteralNode) shouldBe true
                (node.right as ShakeIntegerLiteralNode).value shouldBe 5
            }
        }
    }

    describe("GreaterThanOrEqualNodeSpec") {

        describe("constructor") {
            it("should create GreaterThanOrEqualNodeSpec") {
                val value = GreaterThanOrEqualNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value.left as IntLiteralNodeSpec).value shouldBe 5
                (value.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("of()") {
            it("should not change GreaterThanOrEqualNodeSpec") {
                val value = GreaterThanOrEqualNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === GreaterThanOrEqualNodeSpec.of(value)) shouldBe true
            }

            it("should convert greater than or equal") {
                val value = GreaterThanOrEqualSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = GreaterThanOrEqualNodeSpec.of(value)
                (valueNode.left as IntLiteralNodeSpec).value shouldBe 5
                (valueNode.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("dump") {
            it("should dump the correct output") {
                val value = GreaterThanOrEqualNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "5 >= 5"
            }

            it("should output correct node") {
                val value = GreaterThanOrEqualNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                (node.left is ShakeIntegerLiteralNode) shouldBe true
                (node.left as ShakeIntegerLiteralNode).value shouldBe 5
                node.operatorToken.start shouldBe 2
                node.operatorToken.end shouldBe 3
                node.operatorToken.value shouldBe ">="
                (node.right is ShakeIntegerLiteralNode) shouldBe true
                (node.right as ShakeIntegerLiteralNode).value shouldBe 5
            }
        }
    }

    describe("BitwiseAndNodeSpec") {

        describe("constructor") {
            it("should create BitwiseAndNodeSpec") {
                val value = BitwiseAndNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value.left as IntLiteralNodeSpec).value shouldBe 5
                (value.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("of()") {
            it("should not change BitwiseAndNodeSpec") {
                val value = BitwiseAndNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === BitwiseAndNodeSpec.of(value)) shouldBe true
            }

            it("should convert bitwise and") {
                val value = BitwiseAndSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = BitwiseAndNodeSpec.of(value)
                (valueNode.left as IntLiteralNodeSpec).value shouldBe 5
                (valueNode.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("dump") {
            it("should dump the correct output") {
                val value = BitwiseAndNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "5 & 5"
            }

            it("should output correct node") {
                val value = BitwiseAndNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                (node.left is ShakeIntegerLiteralNode) shouldBe true
                (node.left as ShakeIntegerLiteralNode).value shouldBe 5
                node.operatorToken.start shouldBe 2
                node.operatorToken.end shouldBe 2
                node.operatorToken.value shouldBe "&"
                (node.right is ShakeIntegerLiteralNode) shouldBe true
                (node.right as ShakeIntegerLiteralNode).value shouldBe 5
            }
        }
    }

    describe("BitwiseOrNodeSpec") {

        describe("constructor") {
            it("should create BitwiseOrNodeSpec") {
                val value = BitwiseOrNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value.left as IntLiteralNodeSpec).value shouldBe 5
                (value.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("of()") {
            it("should not change BitwiseOrNodeSpec") {
                val value = BitwiseOrNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === BitwiseOrNodeSpec.of(value)) shouldBe true
            }

            it("should convert bitwise or") {
                val value = BitwiseOrSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = BitwiseOrNodeSpec.of(value)
                (valueNode.left as IntLiteralNodeSpec).value shouldBe 5
                (valueNode.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("dump") {
            it("should dump the correct output") {
                val value = BitwiseOrNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "5 | 5"
            }

            it("should output correct node") {
                val value = BitwiseOrNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                (node.left is ShakeIntegerLiteralNode) shouldBe true
                (node.left as ShakeIntegerLiteralNode).value shouldBe 5
                node.operatorToken.start shouldBe 2
                node.operatorToken.end shouldBe 2
                node.operatorToken.value shouldBe "|"
                (node.right is ShakeIntegerLiteralNode) shouldBe true
                (node.right as ShakeIntegerLiteralNode).value shouldBe 5
            }
        }
    }

    describe("BitwiseXorNodeSpec") {

        describe("constructor") {
            it("should create BitwiseXorNodeSpec") {
                val value = BitwiseXorNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value.left as IntLiteralNodeSpec).value shouldBe 5
                (value.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("of()") {
            it("should not change BitwiseXorNodeSpec") {
                val value = BitwiseXorNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === BitwiseXorNodeSpec.of(value)) shouldBe true
            }

            it("should convert bitwise xor") {
                val value = BitwiseXorSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = BitwiseXorNodeSpec.of(value)
                (valueNode.left as IntLiteralNodeSpec).value shouldBe 5
                (valueNode.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("dump") {
            it("should dump the correct output") {
                val value = BitwiseXorNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "5 ^ 5"
            }

            it("should output correct node") {
                val value = BitwiseXorNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                (node.left is ShakeIntegerLiteralNode) shouldBe true
                (node.left as ShakeIntegerLiteralNode).value shouldBe 5
                node.operatorToken.start shouldBe 2
                node.operatorToken.end shouldBe 2
                node.operatorToken.value shouldBe "^"
                (node.right is ShakeIntegerLiteralNode) shouldBe true
                (node.right as ShakeIntegerLiteralNode).value shouldBe 5
            }
        }
    }

    describe("BitwiseNotNodeSpec") {

        describe("constructor") {
            it("should create BitwiseNotNodeSpec") {
                val value = BitwiseNotNodeSpec(IntLiteralNodeSpec(5))
                (value.value as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("of()") {
            it("should not change BitwiseNotNodeSpec") {
                val value = BitwiseNotNodeSpec(IntLiteralNodeSpec(5))
                (value === BitwiseNotNodeSpec.of(value)) shouldBe true
            }

            it("should convert bitwise not") {
                val value = BitwiseNotSpec(IntLiteralSpec(5))
                val valueNode = BitwiseNotNodeSpec.of(value)
                (valueNode.value as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("dump") {
            it("should dump the correct output") {
                val value = BitwiseNotNodeSpec(IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "~5"
            }

            it("should output correct node") {
                val value = BitwiseNotNodeSpec(IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                node.operatorToken.start shouldBe 0
                node.operatorToken.end shouldBe 0
                node.operatorToken.value shouldBe "~"
                (node.value is ShakeIntegerLiteralNode) shouldBe true
                (node.value as ShakeIntegerLiteralNode).value shouldBe 5
            }
        }
    }

    describe("LeftShiftNodeSpec") {

        describe("constructor") {
            it("should create LeftShiftNodeSpec") {
                val value = LeftShiftNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value.left as IntLiteralNodeSpec).value shouldBe 5
                (value.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("of()") {
            it("should not change LeftShiftNodeSpec") {
                val value = LeftShiftNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === LeftShiftNodeSpec.of(value)) shouldBe true
            }

            it("should convert left shift") {
                val value = LeftShiftSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = LeftShiftNodeSpec.of(value)
                (valueNode.left as IntLiteralNodeSpec).value shouldBe 5
                (valueNode.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("dump") {
            it("should dump the correct output") {
                val value = LeftShiftNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "5 << 5"
            }

            it("should output correct node") {
                val value = LeftShiftNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                (node.left is ShakeIntegerLiteralNode) shouldBe true
                (node.left as ShakeIntegerLiteralNode).value shouldBe 5
                node.operatorToken.start shouldBe 2
                node.operatorToken.end shouldBe 3
                node.operatorToken.value shouldBe "<<"
                (node.right is ShakeIntegerLiteralNode) shouldBe true
                (node.right as ShakeIntegerLiteralNode).value shouldBe 5
            }
        }
    }

    describe("RightShiftNodeSpec") {

        describe("constructor") {
            it("should create RightShiftNodeSpec") {
                val value = RightShiftNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value.left as IntLiteralNodeSpec).value shouldBe 5
                (value.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("of()") {
            it("should not change RightShiftNodeSpec") {
                val value = RightShiftNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === RightShiftNodeSpec.of(value)) shouldBe true
            }

            it("should convert right shift") {
                val value = RightShiftSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = RightShiftNodeSpec.of(value)
                (valueNode.left as IntLiteralNodeSpec).value shouldBe 5
                (valueNode.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("dump") {
            it("should dump the correct output") {
                val value = RightShiftNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "5 >> 5"
            }

            it("should output correct node") {
                val value = RightShiftNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                (node.left is ShakeIntegerLiteralNode) shouldBe true
                (node.left as ShakeIntegerLiteralNode).value shouldBe 5
                node.operatorToken.start shouldBe 2
                node.operatorToken.end shouldBe 3
                node.operatorToken.value shouldBe ">>"
                (node.right is ShakeIntegerLiteralNode) shouldBe true
                (node.right as ShakeIntegerLiteralNode).value shouldBe 5
            }
        }
    }

    describe("UnsignedRightShiftNodeSpec") {

        describe("constructor") {
            it("should create UnsignedRightShiftNodeSpec") {
                val value = UnsignedRightShiftNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value.left as IntLiteralNodeSpec).value shouldBe 5
                (value.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("of()") {
            it("should not change UnsignedRightShiftNodeSpec") {
                val value = UnsignedRightShiftNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                (value === UnsignedRightShiftNodeSpec.of(value)) shouldBe true
            }

            it("should convert unsigned right shift") {
                val value = UnsignedRightShiftSpec(IntLiteralSpec(5), IntLiteralSpec(5))
                val valueNode = UnsignedRightShiftNodeSpec.of(value)
                (valueNode.left as IntLiteralNodeSpec).value shouldBe 5
                (valueNode.right as IntLiteralNodeSpec).value shouldBe 5
            }
        }

        describe("dump") {
            it("should dump the correct output") {
                val value = UnsignedRightShiftNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                value.dump(GenerationContext(), nodeContext)
                nodeContext.built.toString() shouldBe "5 >>> 5"
            }

            it("should output correct node") {
                val value = UnsignedRightShiftNodeSpec(IntLiteralNodeSpec(5), IntLiteralNodeSpec(5))
                val nodeContext = NodeContext()
                val node = value.dump(GenerationContext(), nodeContext)
                (node.left is ShakeIntegerLiteralNode) shouldBe true
                (node.left as ShakeIntegerLiteralNode).value shouldBe 5
                node.operatorToken.start shouldBe 2
                node.operatorToken.end shouldBe 4
                node.operatorToken.value shouldBe ">>>"
                (node.right is ShakeIntegerLiteralNode) shouldBe true
                (node.right as ShakeIntegerLiteralNode).value shouldBe 5
            }
        }
    }

    describe("ValueKt") {
        it("ValueSpec.toNodeSpec()") {
            val intLiteral = IntLiteralSpec(5)
            val intLiteralNode = (intLiteral as ValueSpec).toNodeSpec()
            intLiteralNode shouldBe IntLiteralNodeSpec.of(intLiteral)
        }

        it("StringLiteralSpec.toNodeSpec()") {
            val stringLiteral = StringLiteralSpec("test")
            val stringLiteralNode = stringLiteral.toNodeSpec()
            stringLiteralNode shouldBe StringLiteralNodeSpec.of(stringLiteral)
        }

        it("BooleanLiteralSpec.toNodeSpec()") {
            val booleanLiteral = BooleanLiteralSpec(true)
            val booleanLiteralNode = booleanLiteral.toNodeSpec()
            booleanLiteralNode shouldBe BooleanLiteralNodeSpec.of(booleanLiteral)
        }

        it("CharacterLiteralSpec.toNodeSpec()") {
            val charLiteral = CharacterLiteralSpec('a')
            val charLiteralNode = charLiteral.toNodeSpec()
            charLiteralNode shouldBe CharacterLiteralNodeSpec.of(charLiteral)
        }

        it("FloatLiteralSpec.toNodeSpec()") {
            val floatLiteral = FloatLiteralSpec(5.0)
            val floatLiteralNode = floatLiteral.toNodeSpec()
            floatLiteralNode shouldBe FloatLiteralNodeSpec.of(floatLiteral)
        }

        it("BooleanLiteralSpec.toNodeSpec()") {
            val booleanLiteral = BooleanLiteralSpec(true)
            val booleanLiteralNode = booleanLiteral.toNodeSpec()
            booleanLiteralNode shouldBe BooleanLiteralNodeSpec.of(booleanLiteral)
        }

        it("NullLiteralSpec.toNodeSpec()") {
            val nullLiteral = NullLiteralSpec.INSTANCE
            val nullLiteralNode = nullLiteral.toNodeSpec()
            nullLiteralNode shouldBe NullLiteralNodeSpec.INSTANCE
        }

        it("IntLiteralSpec.toNodeSpec()") {
            val intLiteral = IntLiteralSpec(5)
            val intLiteralNode = intLiteral.toNodeSpec()
            intLiteralNode shouldBe IntLiteralNodeSpec.of(intLiteral)
        }

        it("VariableReferenceSpec.toNodeSpec()") {
            val variableReference = VariableReferenceSpec("test")
            val variableReferenceNode = variableReference.toNodeSpec()
            variableReferenceNode shouldBe VariableReferenceNodeSpec.of(variableReference)
        }

        it("AdditionSpec.toNodeSpec()") {
            val addition = AdditionSpec(IntLiteralSpec(5), IntLiteralSpec(5))
            val additionNode = addition.toNodeSpec()
            additionNode shouldBe AdditionNodeSpec.of(addition)
        }

        it("SubtractionSpec.toNodeSpec()") {
            val subtraction = SubtractionSpec(IntLiteralSpec(5), IntLiteralSpec(5))
            val subtractionNode = subtraction.toNodeSpec()
            subtractionNode shouldBe SubtractionNodeSpec.of(subtraction)
        }

        it("MultiplicationSpec.toNodeSpec()") {
            val multiplication = MultiplicationSpec(IntLiteralSpec(5), IntLiteralSpec(5))
            val multiplicationNode = multiplication.toNodeSpec()
            multiplicationNode shouldBe MultiplicationNodeSpec.of(multiplication)
        }

        it("DivisionSpec.toNodeSpec()") {
            val division = DivisionSpec(IntLiteralSpec(5), IntLiteralSpec(5))
            val divisionNode = division.toNodeSpec()
            divisionNode shouldBe DivisionNodeSpec.of(division)
        }

        it("ModuloSpec.toNodeSpec()") {
            val modulo = ModuloSpec(IntLiteralSpec(5), IntLiteralSpec(5))
            val moduloNode = modulo.toNodeSpec()
            moduloNode shouldBe ModuloNodeSpec.of(modulo)
        }

        it("PowerSpec.toNodeSpec()") {
            val power = PowerSpec(IntLiteralSpec(5), IntLiteralSpec(5))
            val powerNode = power.toNodeSpec()
            powerNode shouldBe PowerNodeSpec.of(power)
        }

        it("UnaryMinusSpec.toNodeSpec()") {
            val unaryMinus = UnaryMinusSpec(IntLiteralSpec(5))
            val unaryMinusNode = unaryMinus.toNodeSpec()
            unaryMinusNode shouldBe UnaryMinusNodeSpec.of(unaryMinus)
        }

        it("UnaryPlusSpec.toNodeSpec()") {
            val unaryPlus = UnaryPlusSpec(IntLiteralSpec(5))
            val unaryPlusNode = unaryPlus.toNodeSpec()
            unaryPlusNode shouldBe UnaryPlusNodeSpec.of(unaryPlus)
        }

        it("LogicalAndSpec.toNodeSpec()") {
            val logicalAnd = LogicalAndSpec(BooleanLiteralSpec(true), BooleanLiteralSpec(false))
            val logicalAndNode = logicalAnd.toNodeSpec()
            logicalAndNode shouldBe LogicalAndNodeSpec.of(logicalAnd)
        }

        it("LogicalOrSpec.toNodeSpec()") {
            val logicalOr = LogicalOrSpec(BooleanLiteralSpec(true), BooleanLiteralSpec(false))
            val logicalOrNode = logicalOr.toNodeSpec()
            logicalOrNode shouldBe LogicalOrNodeSpec.of(logicalOr)
        }

        it("LogicalXorSpec.toNodeSpec()") {
            val logicalXor = LogicalXorSpec(BooleanLiteralSpec(true), BooleanLiteralSpec(false))
            val logicalXorNode = logicalXor.toNodeSpec()
            logicalXorNode shouldBe LogicalXorNodeSpec.of(logicalXor)
        }

        it("LogicalNotSpec.toNodeSpec()") {
            val logicalNot = LogicalNotSpec(BooleanLiteralSpec(true))
            val logicalNotNode = logicalNot.toNodeSpec()
            logicalNotNode shouldBe LogicalNotNodeSpec.of(logicalNot)
        }

        it("EqualitySpec.toNodeSpec()") {
            val equality = EqualitySpec(IntLiteralSpec(5), IntLiteralSpec(5))
            val equalityNode = equality.toNodeSpec()
            equalityNode shouldBe EqualityNodeSpec.of(equality)
        }

        it("InequalitySpec.toNodeSpec()") {
            val inequality = InequalitySpec(IntLiteralSpec(5), IntLiteralSpec(5))
            val inequalityNode = inequality.toNodeSpec()
            inequalityNode shouldBe InequalityNodeSpec.of(inequality)
        }

        it("LessThanSpec.toNodeSpec()") {
            val lessThan = LessThanSpec(IntLiteralSpec(5), IntLiteralSpec(5))
            val lessThanNode = lessThan.toNodeSpec()
            lessThanNode shouldBe LessThanNodeSpec.of(lessThan)
        }

        it("LessThanOrEqualSpec.toNodeSpec()") {
            val lessThanOrEqual = LessThanOrEqualSpec(IntLiteralSpec(5), IntLiteralSpec(5))
            val lessThanOrEqualNode = lessThanOrEqual.toNodeSpec()
            lessThanOrEqualNode shouldBe LessThanOrEqualNodeSpec.of(lessThanOrEqual)
        }

        it("GreaterThanSpec.toNodeSpec()") {
            val greaterThan = GreaterThanSpec(IntLiteralSpec(5), IntLiteralSpec(5))
            val greaterThanNode = greaterThan.toNodeSpec()
            greaterThanNode shouldBe GreaterThanNodeSpec.of(greaterThan)
        }

        it("GreaterThanOrEqualSpec.toNodeSpec()") {
            val greaterThanOrEqual = GreaterThanOrEqualSpec(IntLiteralSpec(5), IntLiteralSpec(5))
            val greaterThanOrEqualNode = greaterThanOrEqual.toNodeSpec()
            greaterThanOrEqualNode shouldBe GreaterThanOrEqualNodeSpec.of(greaterThanOrEqual)
        }

        it("BitwiseAndSpec.toNodeSpec()") {
            val bitwiseAnd = BitwiseAndSpec(IntLiteralSpec(5), IntLiteralSpec(5))
            val bitwiseAndNode = bitwiseAnd.toNodeSpec()
            bitwiseAndNode shouldBe BitwiseAndNodeSpec.of(bitwiseAnd)
        }

        it("BitwiseOrSpec.toNodeSpec()") {
            val bitwiseOr = BitwiseOrSpec(IntLiteralSpec(5), IntLiteralSpec(5))
            val bitwiseOrNode = bitwiseOr.toNodeSpec()
            bitwiseOrNode shouldBe BitwiseOrNodeSpec.of(bitwiseOr)
        }

        it("BitwiseXorSpec.toNodeSpec()") {
            val bitwiseXor = BitwiseXorSpec(IntLiteralSpec(5), IntLiteralSpec(5))
            val bitwiseXorNode = bitwiseXor.toNodeSpec()
            bitwiseXorNode shouldBe BitwiseXorNodeSpec.of(bitwiseXor)
        }

        it("BitwiseNotSpec.toNodeSpec()") {
            val bitwiseNot = BitwiseNotSpec(IntLiteralSpec(5))
            val bitwiseNotNode = bitwiseNot.toNodeSpec()
            bitwiseNotNode shouldBe BitwiseNotNodeSpec.of(bitwiseNot)
        }

        it("ShiftLeftSpec.toNodeSpec()") {
            val shiftLeft = LeftShiftSpec(IntLiteralSpec(5), IntLiteralSpec(5))
            val shiftLeftNode = shiftLeft.toNodeSpec()
            shiftLeftNode shouldBe LeftShiftNodeSpec.of(shiftLeft)
        }

        it("ShiftRightSpec.toNodeSpec()") {
            val shiftRight = RightShiftSpec(IntLiteralSpec(5), IntLiteralSpec(5))
            val shiftRightNode = shiftRight.toNodeSpec()
            shiftRightNode shouldBe RightShiftNodeSpec.of(shiftRight)
        }

        it("BitwiseUnsignedShiftRightSpec.toNodeSpec()") {
            val unsignedShiftRight = UnsignedRightShiftSpec(IntLiteralSpec(5), IntLiteralSpec(5))
            val unsignedShiftRightNode = unsignedShiftRight.toNodeSpec()
            unsignedShiftRightNode shouldBe UnsignedRightShiftNodeSpec.of(unsignedShiftRight)
        }
    }
})

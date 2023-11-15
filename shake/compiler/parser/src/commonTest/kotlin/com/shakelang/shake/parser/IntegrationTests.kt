package com.shakelang.shake.parser

import kotlin.test.Test
import kotlin.test.assertEquals

// This file holds many tests that test the parser and the lexer together
// It should test every possible syntax of the language
// We just test the correctness of the parsed AST, by exporting it as json object
class IntegrationTests {

    // Literals
    @Test
    fun testIntegerLiteral() = testCodeSnippetValue("42", mapOf("name" to "ShakeIntegerNode", "value" to 42))

    @Test
    fun testDoubleLiteral() = testCodeSnippetValue("42.0", mapOf("name" to "ShakeDoubleNode", "value" to 42.0))

    @Test
    fun testStringLiteral() =
        testCodeSnippetValue("\"Hello World\"", mapOf("name" to "ShakeStringNode", "value" to "Hello World"))

    @Test
    fun testTrueLiteral() = testCodeSnippetValue("true", mapOf("name" to "ShakeLogicalTrueNode"))

    @Test
    fun testFalseLiteral() = testCodeSnippetValue("false", mapOf("name" to "ShakeLogicalFalseNode"))

    @Test
    fun testNullLiteral() = testCodeSnippetValue("null", mapOf("name" to "ShakeNullNode"))

    @Test
    fun testVariableLiteral() = testCodeSnippetValue(
        "a",
        mapOf("name" to "VariableUsageNode", "variable" to mapOf("name" to "a", "parent" to null))
    )

    @Test
    fun testFieldLiteral() = testCodeSnippetValue(
        "a.b", mapOf(
            "name" to "VariableUsageNode",
            "variable" to mapOf(
                "name" to "b",
                "parent" to mapOf(
                    "name" to "VariableUsageNode",
                    "variable" to mapOf("name" to "a", "parent" to null)
                )
            )
        )
    )

    @Test
    fun testFunctionCallLiteral() = testCodeSnippetValue(
        "a()", mapOf(
            "name" to "ShakeInvocationNode",
            "function" to mapOf(
                "name" to "VariableUsageNode",
                "variable" to mapOf("name" to "a", "parent" to null)
            ),
            "args" to emptyList<Any>()
        )
    )

    @Test
    fun testPriorityExpression() = testCodeSnippetValue(
        "(1 + 2)", mapOf(
            "name" to "ShakePriorityNode",
            "value" to mapOf(
                "name" to "ShakeAddNode",
                "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
                "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
            ),
        )
    )


    // Expressions
    @Test
    fun testAddExpression() = testCodeSnippetValue(
        "1 + 2", mapOf(
            "name" to "ShakeAddNode",
            "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
            "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
        )
    )

    @Test
    fun testSubtractExpression() = testCodeSnippetValue(
        "1 - 2", mapOf(
            "name" to "ShakeSubNode",
            "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
            "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
        )
    )

    @Test
    fun testMultiplyExpression() = testCodeSnippetValue(
        "1 * 2", mapOf(
            "name" to "ShakeMulNode",
            "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
            "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
        )
    )

    @Test
    fun testDivideExpression() = testCodeSnippetValue(
        "1 / 2", mapOf(
            "name" to "ShakeDivNode",
            "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
            "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
        )
    )

    @Test
    fun testModuloExpression() = testCodeSnippetValue(
        "1 % 2", mapOf(
            "name" to "ShakeModNode",
            "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
            "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
        )
    )

    @Test
    fun testPowerExpression() = testCodeSnippetValue(
        "1 ** 2", mapOf(
            "name" to "ShakePowNode",
            "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
            "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
        )
    )

    @Test
    fun testLogicalAndExpression() = testCodeSnippetValue(
        "true && false", mapOf(
            "name" to "ShakeLogicalAndNode",
            "left" to mapOf("name" to "ShakeLogicalTrueNode"),
            "right" to mapOf("name" to "ShakeLogicalFalseNode")
        )
    )

    @Test
    fun testLogicalOrExpression() = testCodeSnippetValue(
        "true || false", mapOf(
            "name" to "ShakeLogicalOrNode",
            "left" to mapOf("name" to "ShakeLogicalTrueNode"),
            "right" to mapOf("name" to "ShakeLogicalFalseNode")
        )
    )

    @Test
    fun testLogicalXOrExpression() = testCodeSnippetValue(
        "true ^^ false", mapOf(
            "name" to "ShakeLogicalXOrNode",
            "left" to mapOf("name" to "ShakeLogicalTrueNode"),
            "right" to mapOf("name" to "ShakeLogicalFalseNode")
        )
    )

    @Test
    fun testEqualsExpression() = testCodeSnippetValue(
        "1 == 2", mapOf(
            "name" to "ShakeEqualNode",
            "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
            "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
        )
    )

    @Test
    fun testNotEqualsExpression() = testCodeSnippetValue(
        "1 != 2", mapOf(
            "name" to "ShakeNotEqualNode",
            "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
            "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
        )
    )

    @Test
    fun testLessThanExpression() = testCodeSnippetValue(
        "1 < 2", mapOf(
            "name" to "ShakeLessThanNode",
            "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
            "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
        )
    )

    @Test
    fun testLessThanOrEqualsExpression() = testCodeSnippetValue(
        "1 <= 2", mapOf(
            "name" to "ShakeLessThanOrEqualNode",
            "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
            "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
        )
    )

    @Test
    fun testGreaterThanExpression() = testCodeSnippetValue(
        "1 > 2", mapOf(
            "name" to "ShakeGreaterThanNode",
            "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
            "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
        )
    )

    @Test
    fun testGreaterThanOrEqualsExpression() = testCodeSnippetValue(
        "1 >= 2", mapOf(
            "name" to "ShakeGreaterThanOrEqualNode",
            "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
            "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
        )
    )

    @Test
    fun testUnaryMinusExpression() = testCodeSnippetValue(
        "-1", mapOf(
            "name" to "ShakeUnaryMinusNode",
            "value" to mapOf("name" to "ShakeIntegerNode", "value" to 1)
        )
    )

    @Test
    fun testUnaryPlusExpression() = testCodeSnippetValue(
        "+1", mapOf(
            "name" to "ShakeUnaryPlusNode",
            "value" to mapOf("name" to "ShakeIntegerNode", "value" to 1)
        )
    )

    @Test
    fun testNotExpression() = testCodeSnippetValue(
        "!true", mapOf(
            "name" to "ShakeLogicalNotNode",
            "value" to mapOf("name" to "ShakeLogicalTrueNode")
        )
    )

    @Test
    fun testBitwiseOrExpression() = testCodeSnippetValue(
        "1 | 2", mapOf(
            "name" to "ShakeBitwiseOrNode",
            "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
            "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
        )
    )

    @Test
    fun testBitwiseAndExpression() = testCodeSnippetValue(
        "1 & 2", mapOf(
            "name" to "ShakeBitwiseAndNode",
            "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
            "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
        )
    )

    @Test
    fun testBitwiseXOrExpression() = testCodeSnippetValue(
        "1 ^ 2", mapOf(
            "name" to "ShakeBitwiseXOrNode",
            "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
            "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
        )
    )

    @Test
    fun testBitwiseLeftShiftExpression() = testCodeSnippetValue(
        "1 << 2", mapOf(
            "name" to "ShakeBitwiseShiftLeftNode",
            "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
            "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
        )
    )

    @Test
    fun testBitwiseRightShiftExpression() = testCodeSnippetValue(
        "1 >> 2", mapOf(
            "name" to "ShakeBitwiseShiftRightNode",
            "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
            "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
        )
    )

    // Test order of operations
    @Test
    fun testParenthesesBeforeUnary() = testCodeSnippetValue(
        "-(1 + 2)", mapOf(
            "name" to "ShakeUnaryMinusNode",
            "value" to mapOf(
                "name" to "ShakePriorityNode",
                "value" to mapOf(
                    "name" to "ShakeAddNode",
                    "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
                    "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
                )
            )
        )
    )

    @Test
    fun testUnaryBeforeCast() = testCodeSnippetValue(
        "-1 as double", mapOf(
            "name" to "ShakeCastNode",
            "value" to mapOf(
                "name" to "ShakeUnaryMinusNode",
                "value" to mapOf("name" to "ShakeIntegerNode", "value" to 1)
            ),
            "cast_target" to "DOUBLE"
        )
    )

    @Test
    fun testCastBeforePower() = testCodeSnippetValue(
        "1 ** 2 as double", mapOf(
            "name" to "ShakePowNode",
            "left" to mapOf("name" to "ShakeIntegerNode", "value" to 1),
            "right" to mapOf(
                "name" to "ShakeCastNode",
                "value" to mapOf("name" to "ShakeIntegerNode", "value" to 2),
                "cast_target" to "DOUBLE"
            )
        )
    )

    @Test
    fun testPowerBeforeMultiply() = testCodeSnippetValue(
        "2 ** 3 * 2", mapOf(
            "name" to "ShakeMulNode",
            "left" to mapOf(
                "name" to "ShakePowNode",
                "left" to mapOf("name" to "ShakeIntegerNode", "value" to 2),
                "right" to mapOf("name" to "ShakeIntegerNode", "value" to 3)
            ),
            "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
        )
    )

    @Test
    fun testPowerBeforeDivide() = testCodeSnippetValue(
        "2 ** 3 / 2", mapOf(
            "name" to "ShakeDivNode",
            "left" to mapOf(
                "name" to "ShakePowNode",
                "left" to mapOf("name" to "ShakeIntegerNode", "value" to 2),
                "right" to mapOf("name" to "ShakeIntegerNode", "value" to 3)
            ),
            "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
        )
    )

    @Test
    fun testPowerBeforeModulo() = testCodeSnippetValue(
        "2 ** 3 % 2", mapOf(
            "name" to "ShakeModNode",
            "left" to mapOf(
                "name" to "ShakePowNode",
                "left" to mapOf("name" to "ShakeIntegerNode", "value" to 2),
                "right" to mapOf("name" to "ShakeIntegerNode", "value" to 3)
            ),
            "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
        )
    )

    @Test
    fun testMultiplyBeforeDivide() = testCodeSnippetValue(
        "2 * 3 / 2", mapOf(
            "name" to "ShakeDivNode",
            "left" to mapOf(
                "name" to "ShakeMulNode",
                "left" to mapOf("name" to "ShakeIntegerNode", "value" to 2),
                "right" to mapOf("name" to "ShakeIntegerNode", "value" to 3)
            ),
            "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
        )
    )

    @Test
    fun testMultiplyBeforeModulo() = testCodeSnippetValue(
        "2 * 3 % 2", mapOf(
            "name" to "ShakeModNode",
            "left" to mapOf(
                "name" to "ShakeMulNode",
                "left" to mapOf("name" to "ShakeIntegerNode", "value" to 2),
                "right" to mapOf("name" to "ShakeIntegerNode", "value" to 3)
            ),
            "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
        )
    )

    @Test
    fun testMultiplyBeforeAdd() = testCodeSnippetValue(
        "2 * 3 + 2", mapOf(
            "name" to "ShakeAddNode",
            "left" to mapOf(
                "name" to "ShakeMulNode",
                "left" to mapOf("name" to "ShakeIntegerNode", "value" to 2),
                "right" to mapOf("name" to "ShakeIntegerNode", "value" to 3)
            ),
            "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
        )
    )

    @Test
    fun testMultiplyBeforeSubtract() = testCodeSnippetValue(
        "2 * 3 - 2", mapOf(
            "name" to "ShakeSubNode",
            "left" to mapOf(
                "name" to "ShakeMulNode",
                "left" to mapOf("name" to "ShakeIntegerNode", "value" to 2),
                "right" to mapOf("name" to "ShakeIntegerNode", "value" to 3)
            ),
            "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
        )
    )

    @Test
    fun testAddBeforeShiftLeft() {
        testCodeSnippetValue(
            "2 + 3 << 2", mapOf(
                "name" to "ShakeBitwiseShiftLeftNode",
                "left" to mapOf(
                    "name" to "ShakeAddNode",
                    "left" to mapOf("name" to "ShakeIntegerNode", "value" to 2),
                    "right" to mapOf("name" to "ShakeIntegerNode", "value" to 3)
                ),
                "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
            )
        )
    }

    @Test
    fun testAddBeforeShiftRight() {
        testCodeSnippetValue(
            "2 + 3 >> 2", mapOf(
                "name" to "ShakeBitwiseShiftRightNode",
                "left" to mapOf(
                    "name" to "ShakeAddNode",
                    "left" to mapOf("name" to "ShakeIntegerNode", "value" to 2),
                    "right" to mapOf("name" to "ShakeIntegerNode", "value" to 3)
                ),
                "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
            )
        )
    }

    @Test
    fun testSubtractBeforeShiftLeft() {
        testCodeSnippetValue(
            "2 - 3 << 2", mapOf(
                "name" to "ShakeBitwiseShiftLeftNode",
                "left" to mapOf(
                    "name" to "ShakeSubNode",
                    "left" to mapOf("name" to "ShakeIntegerNode", "value" to 2),
                    "right" to mapOf("name" to "ShakeIntegerNode", "value" to 3)
                ),
                "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
            )
        )
    }

    @Test
    fun testSubtractBeforeShiftRight() {
        testCodeSnippetValue(
            "2 - 3 >> 2", mapOf(
                "name" to "ShakeBitwiseShiftRightNode",
                "left" to mapOf(
                    "name" to "ShakeSubNode",
                    "left" to mapOf("name" to "ShakeIntegerNode", "value" to 2),
                    "right" to mapOf("name" to "ShakeIntegerNode", "value" to 3)
                ),
                "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
            )
        )
    }

    @Test
    fun testShiftLeftBeforeBitwiseAnd() {
        testCodeSnippetValue(
            "2 << 3 & 2", mapOf(
                "name" to "ShakeBitwiseAndNode",
                "left" to mapOf(
                    "name" to "ShakeBitwiseShiftLeftNode",
                    "left" to mapOf("name" to "ShakeIntegerNode", "value" to 2),
                    "right" to mapOf("name" to "ShakeIntegerNode", "value" to 3)
                ),
                "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
            )
        )
    }

    @Test
    fun testShiftRightBeforeLargerThan() {
        testCodeSnippetValue(
            "2 >> 3 > 2", mapOf(
                "name" to "ShakeGreaterThanNode",
                "left" to mapOf(
                    "name" to "ShakeBitwiseShiftRightNode",
                    "left" to mapOf("name" to "ShakeIntegerNode", "value" to 2),
                    "right" to mapOf("name" to "ShakeIntegerNode", "value" to 3)
                ),
                "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
            )
        )
    }

    @Test
    fun testShiftRightBeforeLargerThanOrEquals() {
        testCodeSnippetValue(
            "2 >> 3 >= 2", mapOf(
                "name" to "ShakeGreaterThanOrEqualNode",
                "left" to mapOf(
                    "name" to "ShakeBitwiseShiftRightNode",
                    "left" to mapOf("name" to "ShakeIntegerNode", "value" to 2),
                    "right" to mapOf("name" to "ShakeIntegerNode", "value" to 3)
                ),
                "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
            )
        )
    }

    @Test
    fun testShiftRightBeforeLessThan() {
        testCodeSnippetValue(
            "2 >> 3 < 2", mapOf(
                "name" to "ShakeLessThanNode",
                "left" to mapOf(
                    "name" to "ShakeBitwiseShiftRightNode",
                    "left" to mapOf("name" to "ShakeIntegerNode", "value" to 2),
                    "right" to mapOf("name" to "ShakeIntegerNode", "value" to 3)
                ),
                "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
            )
        )
    }

    @Test
    fun testShiftRightBeforeLessThanOrEquals() {
        testCodeSnippetValue(
            "2 >> 3 <= 2", mapOf(
                "name" to "ShakeLessThanOrEqualNode",
                "left" to mapOf(
                    "name" to "ShakeBitwiseShiftRightNode",
                    "left" to mapOf("name" to "ShakeIntegerNode", "value" to 2),
                    "right" to mapOf("name" to "ShakeIntegerNode", "value" to 3)
                ),
                "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
            )
        )
    }

    @Test
    fun testShiftRightBeforeGreaterThanOrEquals() {
        testCodeSnippetValue(
            "2 >> 3 >= 2", mapOf(
                "name" to "ShakeGreaterThanOrEqualNode",
                "left" to mapOf(
                    "name" to "ShakeBitwiseShiftRightNode",
                    "left" to mapOf("name" to "ShakeIntegerNode", "value" to 2),
                    "right" to mapOf("name" to "ShakeIntegerNode", "value" to 3)
                ),
                "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
            )
        )
    }

    @Test
    fun testShiftRightBeforeBitwiseAnd() {
        testCodeSnippetValue(
            "2 >> 3 & 2", mapOf(
                "name" to "ShakeBitwiseAndNode",
                "left" to mapOf(
                    "name" to "ShakeBitwiseShiftRightNode",
                    "left" to mapOf("name" to "ShakeIntegerNode", "value" to 2),
                    "right" to mapOf("name" to "ShakeIntegerNode", "value" to 3)
                ),
                "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
            )
        )
    }

    @Test
    fun testBitwiseAndBeforeBitwiseXOr() {
        testCodeSnippetValue(
            "2 & 3 ^ 2", mapOf(
                "name" to "ShakeBitwiseXOrNode",
                "left" to mapOf(
                    "name" to "ShakeBitwiseAndNode",
                    "left" to mapOf("name" to "ShakeIntegerNode", "value" to 2),
                    "right" to mapOf("name" to "ShakeIntegerNode", "value" to 3)
                ),
                "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
            )
        )
    }

    @Test
    fun testBitwiseXOrBeforeBitwiseOr() {
        testCodeSnippetValue(
            "2 ^ 3 | 2", mapOf(
                "name" to "ShakeBitwiseOrNode",
                "left" to mapOf(
                    "name" to "ShakeBitwiseXOrNode",
                    "left" to mapOf("name" to "ShakeIntegerNode", "value" to 2),
                    "right" to mapOf("name" to "ShakeIntegerNode", "value" to 3)
                ),
                "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
            )
        )
    }

    @Test
    fun testBitwiseOrBeforeLogicalAnd() {
        testCodeSnippetValue(
            "2 | 3 && 2", mapOf(
                "name" to "ShakeLogicalAndNode",
                "left" to mapOf(
                    "name" to "ShakeBitwiseOrNode",
                    "left" to mapOf("name" to "ShakeIntegerNode", "value" to 2),
                    "right" to mapOf("name" to "ShakeIntegerNode", "value" to 3)
                ),
                "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
            )
        )
    }

    @Test
    fun testBitwiseOrBeforeLogicalOr() {
        testCodeSnippetValue(
            "2 | 3 || 2", mapOf(
                "name" to "ShakeLogicalOrNode",
                "left" to mapOf(
                    "name" to "ShakeBitwiseOrNode",
                    "left" to mapOf("name" to "ShakeIntegerNode", "value" to 2),
                    "right" to mapOf("name" to "ShakeIntegerNode", "value" to 3)
                ),
                "right" to mapOf("name" to "ShakeIntegerNode", "value" to 2)
            )
        )
    }

    @Test
    fun testLogicalAndBeforeLogicalXOr() {
        testCodeSnippetValue(
            "true && false ^^ true", mapOf(
                "name" to "ShakeLogicalXOrNode",
                "left" to mapOf(
                    "name" to "ShakeLogicalAndNode",
                    "left" to mapOf("name" to "ShakeLogicalTrueNode"),
                    "right" to mapOf("name" to "ShakeLogicalFalseNode")
                ),
                "right" to mapOf("name" to "ShakeLogicalTrueNode")
            )
        )
    }

    @Test
    fun testLogicalXOrBeforeLogicalOr() {
        testCodeSnippetValue(
            "true ^^ false || true", mapOf(
                "name" to "ShakeLogicalOrNode",
                "left" to mapOf(
                    "name" to "ShakeLogicalXOrNode",
                    "left" to mapOf("name" to "ShakeLogicalTrueNode"),
                    "right" to mapOf("name" to "ShakeLogicalFalseNode")
                ),
                "right" to mapOf("name" to "ShakeLogicalTrueNode")
            )
        )
    }



    // Utils

    private fun testCodeSnippetValue(code: String, expected: Any) {
        val sourceLocation = "TestInput"
        val parsed = ParserTestUtil.parseValue(sourceLocation, code)
        val json = parsed.json

        assertEquals(expected, json, "The parsed value is not correct")

    }

}
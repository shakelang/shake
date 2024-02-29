package com.shakelang.shake.parser

import com.shakelang.shake.parser.node.ShakeValuedNode
import com.shakelang.shake.parser.node.expression.*
import com.shakelang.shake.parser.node.values.expression.*
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import kotlin.reflect.KClass

// This file holds many tests that test the parser and the lexer together
// It should test every possible syntax of the language
// We just test the correctness of the parsed AST, by exporting it as json object
class IntegrationTestValues : FreeSpec(
    {

        fun testCodeSnippetValue(code: String, expected: Any) {
            val sourceLocation = "TestInput"
            val parsed = ParserTestUtil.parseValue(sourceLocation, code)
            val json = parsed.json

            json shouldBe expected
        }

        "integer literal" {
            testCodeSnippetValue("42", mapOf("name" to "ShakeIntegerLiteralNode", "value" to 42))
        }

        "double literal" {
            testCodeSnippetValue("42.0", mapOf("name" to "ShakeDoubleLiteralNode", "value" to 42.0))
        }

        "string literal" {
            testCodeSnippetValue("\"Hello World\"", mapOf("name" to "ShakeStringLiteralNode", "value" to "Hello World"))
        }

        "true literal" {
            testCodeSnippetValue("true", mapOf("name" to "ShakeTrueLiteralNode"))
        }

        "false literal" {
            testCodeSnippetValue("false", mapOf("name" to "ShakeFalseLiteralNode"))
        }

        "null literal" {
            testCodeSnippetValue("null", mapOf("name" to "ShakeNullLiteralNode"))
        }

        "variable literal" {
            testCodeSnippetValue(
                "a",
                mapOf("name" to "ShakeVariableUsageNode", "variable" to mapOf("name" to "a", "parent" to null)),
            )
        }

        "field" {
            testCodeSnippetValue(
                "a.b",
                mapOf(
                    "name" to "ShakeVariableUsageNode",
                    "variable" to mapOf(
                        "name" to "b",
                        "parent" to mapOf(
                            "name" to "ShakeVariableUsageNode",
                            "variable" to mapOf("name" to "a", "parent" to null),
                        ),
                    ),
                ),
            )
        }

        "function call" {
            testCodeSnippetValue(
                "a()",
                mapOf(
                    "name" to "ShakeInvocationNode",
                    "function" to mapOf(
                        "name" to "ShakeVariableUsageNode",
                        "variable" to mapOf("name" to "a", "parent" to null),
                    ),
                    "args" to emptyList<Any>(),
                ),
            )
        }

        "field of call" {
            testCodeSnippetValue(
                "a().b",
                mapOf(
                    "name" to "ShakeVariableUsageNode",
                    "variable" to mapOf(
                        "name" to "b",
                        "parent" to mapOf(
                            "name" to "ShakeInvocationNode",
                            "function" to mapOf(
                                "name" to "ShakeVariableUsageNode",
                                "variable" to mapOf("name" to "a", "parent" to null),
                            ),
                            "args" to emptyList<Any>(),
                        ),
                    ),
                ),
            )
        }

        "function call with arguments" {
            testCodeSnippetValue(
                "a(1, 2)",
                mapOf(
                    "name" to "ShakeInvocationNode",
                    "function" to mapOf(
                        "name" to "ShakeVariableUsageNode",
                        "variable" to mapOf("name" to "a", "parent" to null),
                    ),
                    "args" to listOf(
                        mapOf("name" to "ShakeIntegerLiteralNode", "value" to 1),
                        mapOf("name" to "ShakeIntegerLiteralNode", "value" to 2),
                    ),
                ),
            )
        }
        "call of return value" {
            testCodeSnippetValue(
                "a()()",
                mapOf(
                    "name" to "ShakeInvocationNode",
                    "function" to mapOf(
                        "name" to "ShakeInvocationNode",
                        "function" to mapOf(
                            "name" to "ShakeVariableUsageNode",
                            "variable" to mapOf("name" to "a", "parent" to null),
                        ),
                        "args" to emptyList<Any>(),
                    ),
                    "args" to emptyList<Any>(),
                ),
            )
        }

        class OperatorTestSpec(
            val operator: String,
            val name: String,
            val expected: KClass<out ShakeValuedNode>,
        )

        val toTest = listOf(
            listOf(
                OperatorTestSpec("**", "power", ShakePowNode::class),
            ),
            listOf(
                OperatorTestSpec("*", "multiplication", ShakeMulNode::class),
                OperatorTestSpec("/", "division", ShakeDivNode::class),
                OperatorTestSpec("%", "modulo", ShakeModNode::class),
            ),
            listOf(
                OperatorTestSpec("+", "addition", ShakeAddNode::class),
                OperatorTestSpec("-", "subtraction", ShakeSubNode::class),
            ),
            listOf(
                OperatorTestSpec("<<", "bitwise shift left", ShakeBitwiseShiftLeftNode::class),
                OperatorTestSpec(">>", "bitwise shift right", ShakeBitwiseShiftRightNode::class),
            ),
            listOf(
                OperatorTestSpec("<", "less than", ShakeLessThanNode::class),
                OperatorTestSpec("<=", "less than or equals", ShakeLessThanOrEqualNode::class),
                OperatorTestSpec(">", "greater than", ShakeGreaterThanNode::class),
                OperatorTestSpec(">=", "greater than or equals", ShakeGreaterThanOrEqualNode::class),
            ),
            listOf(
                OperatorTestSpec("==", "equals", ShakeEqualNode::class),
                OperatorTestSpec("!=", "not equals", ShakeNotEqualNode::class),
            ),
            listOf(
                OperatorTestSpec("&", "bitwise and", ShakeBitwiseAndNode::class),
                OperatorTestSpec("~&", "bitwise nand", ShakeBitwiseNAndNode::class),
            ),
            listOf(
                OperatorTestSpec("^", "bitwise xor", ShakeBitwiseXOrNode::class),
                OperatorTestSpec("~^", "bitwise xnor", ShakeBitwiseXNOrNode::class),
            ),
            listOf(
                OperatorTestSpec("|", "bitwise or", ShakeBitwiseOrNode::class),
                OperatorTestSpec("~|", "bitwise nor", ShakeBitwiseNOrNode::class),
            ),
            listOf(
                OperatorTestSpec("&&", "logical and", ShakeLogicalAndNode::class),
            ),
            listOf(
                OperatorTestSpec("^^", "logical xor", ShakeLogicalXOrNode::class),
            ),
            listOf(
                OperatorTestSpec("||", "logical or", ShakeLogicalOrNode::class),
            ),
//        listOf(
//            OperatorTestSpec("=", "assignment", ShakeVariableAssignmentNode::class),
//            OperatorTestSpec("+=", "addition assignment", ShakeVariableAddAssignmentNode::class),
//            OperatorTestSpec("-=", "subtraction assignment", ShakeVariableSubAssignmentNode::class),
//            OperatorTestSpec("*=", "multiplication assignment", ShakeVariableMulAssignmentNode::class),
//            OperatorTestSpec("/=", "division assignment", ShakeVariableDivAssignmentNode::class),
//            OperatorTestSpec("%=", "modulo assignment", ShakeVariableModAssignmentNode::class),
//        ),
        )
        toTest.forEachIndexed { levelIndex, level ->
            level.forEach { testSpec ->
                "test ${testSpec.name} operator" {
                    testCodeSnippetValue(
                        "1 ${testSpec.operator} 2",
                        mapOf(
                            "name" to testSpec.expected.simpleName,
                            "left" to mapOf("name" to "ShakeIntegerLiteralNode", "value" to 1),
                            "right" to mapOf("name" to "ShakeIntegerLiteralNode", "value" to 2),
                        ),
                    )
                }

                for (i in 0 until levelIndex) {
                    val previousLevel = toTest[i]
                    previousLevel.forEach { previousTestSpec ->
                        "test ${previousTestSpec.name} before ${testSpec.name} operator" {
                            testCodeSnippetValue(
                                "1 ${previousTestSpec.operator} 2 ${testSpec.operator} 3",
                                mapOf(
                                    "name" to testSpec.expected.simpleName,
                                    "left" to mapOf(
                                        "name" to previousTestSpec.expected.simpleName,
                                        "left" to mapOf("name" to "ShakeIntegerLiteralNode", "value" to 1),
                                        "right" to mapOf("name" to "ShakeIntegerLiteralNode", "value" to 2),
                                    ),
                                    "right" to mapOf("name" to "ShakeIntegerLiteralNode", "value" to 3),
                                ),
                            )
                        }
                    }
                }
            }
        }
    },
)

class Statements : FreeSpec(
    {

        fun testCodeSnippet(code: String, expected: Any) {
            val sourceLocation = "TestInput"
            val parsed = ParserTestUtil.parseStatement(sourceLocation, code)
            val json = parsed.json
            json shouldBe expected
        }

        "test variable declaration" {
            testCodeSnippet(
                "var a = 2",
                mapOf(
                    "name" to "ShakeBlockNode",
                    "children" to listOf(
                        mapOf(
                            "name" to "ShakeLocalDeclarationNode",
                            "variable_name" to "a",
                            "type" to null,
                            "assignment" to mapOf("name" to "ShakeIntegerLiteralNode", "value" to 2),
                        ),
                    ),
                ),
            )
        }
    },
)

package com.shakelang.shake.parser

import com.shakelang.shake.parser.node.ShakeAccessDescriber
import com.shakelang.shake.parser.node.ShakeVariableType
import com.shakelang.shake.parser.node.expression.ShakeAddNode
import com.shakelang.shake.parser.node.factor.ShakeIntegerLiteralNode
import com.shakelang.shake.parser.node.variables.*
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlin.reflect.KClass

class VariableTests : FreeSpec(
    {

        // // *************************************************************
        // // Assignments

        class AssignmentTestDescriptor(
            val name: String,
            val operator: String,
            val nodeClass: KClass<out ShakeCommonVariableAssignmentNode>,
        )

        listOf(
            AssignmentTestDescriptor("variable assignment", "=", ShakeVariableAssignmentNode::class),
            AssignmentTestDescriptor("variable add assignment", "+=", ShakeVariableAddAssignmentNode::class),
            AssignmentTestDescriptor("variable sub assignment", "-=", ShakeVariableSubAssignmentNode::class),
            AssignmentTestDescriptor("variable mul assignment", "*=", ShakeVariableMulAssignmentNode::class),
            AssignmentTestDescriptor("variable div assignment", "/=", ShakeVariableDivAssignmentNode::class),
            AssignmentTestDescriptor("variable mod assignment", "%=", ShakeVariableModAssignmentNode::class),
            AssignmentTestDescriptor("variable pow assignment", "**=", ShakeVariablePowAssignmentNode::class),
        ).forEach {
            it.name {
                val node = ParserTestUtil.parseStatement("<${it.name}>", "i ${it.operator} 0", it.nodeClass)
                node shouldBeOfType it.nodeClass
                (node.variable as ShakeVariableUsageNode).identifier.name shouldBe "i"
                (node.variable as ShakeVariableUsageNode).identifier.parent shouldBe null
                node.value shouldNotBe null
                node.value shouldBeOfType ShakeIntegerLiteralNode::class
                (node.value as ShakeIntegerLiteralNode).number shouldBe 0
            }

            "${it.name} with expression" {
                val node = ParserTestUtil.parseStatement("<${it.name}>", "i ${it.operator} 11 + 2", it.nodeClass)
                node shouldBeOfType it.nodeClass
                (node.variable as ShakeVariableUsageNode).identifier.name shouldBe "i"
                (node.variable as ShakeVariableUsageNode).identifier.parent shouldBe null
                node.value shouldNotBe null
                node.value shouldBeOfType ShakeAddNode::class
                (node.value as ShakeAddNode).left shouldBeOfType ShakeIntegerLiteralNode::class
                (node.value as ShakeAddNode).right shouldBeOfType ShakeIntegerLiteralNode::class
                ((node.value as ShakeAddNode).left as ShakeIntegerLiteralNode).number shouldBe 11
                ((node.value as ShakeAddNode).right as ShakeIntegerLiteralNode).number shouldBe 2
            }
        }

        "variable incr" {
            val node = ParserTestUtil.parseStatement("<VariableIncreaseTest>", "i++", ShakeVariableIncrementAfterNode::class)
            node.variable shouldBeOfType ShakeVariableUsageNode::class
            (node.variable as ShakeVariableUsageNode).identifier.name shouldBe "i"
            (node.variable as ShakeVariableUsageNode).identifier.parent shouldBe null
        }

        "variable decr" {
            val node = ParserTestUtil.parseStatement("<VariableDecreaseTest>", "i--", ShakeVariableDecrementAfterNode::class)
            node.variable shouldBeOfType ShakeVariableUsageNode::class
            (node.variable as ShakeVariableUsageNode).identifier.name shouldBe "i"
            (node.variable as ShakeVariableUsageNode).identifier.parent shouldBe null
        }

        // // *************************************************************
        // // Variable Declaration C-Style

        class VariableDeclarationDescriptor(
            val declarationType: String,
            val typeClass: ShakeVariableType.Type,
        ) {
            val name get() = "$declarationType declaration"
        }

        listOf(
//        VariableDeclarationDescriptor("var", ShakeVariableType.UNKNOWN),
            VariableDeclarationDescriptor("byte", ShakeVariableType.Type.BYTE),
            VariableDeclarationDescriptor("short", ShakeVariableType.Type.SHORT),
            VariableDeclarationDescriptor("int", ShakeVariableType.Type.INTEGER),
            VariableDeclarationDescriptor("long", ShakeVariableType.Type.LONG),
            VariableDeclarationDescriptor("ubyte", ShakeVariableType.Type.UNSIGNED_BYTE),
            VariableDeclarationDescriptor("ushort", ShakeVariableType.Type.UNSIGNED_SHORT),
            VariableDeclarationDescriptor("uint", ShakeVariableType.Type.UNSIGNED_INTEGER),
            VariableDeclarationDescriptor("ulong", ShakeVariableType.Type.UNSIGNED_LONG),
            VariableDeclarationDescriptor("float", ShakeVariableType.Type.FLOAT),
            VariableDeclarationDescriptor("double", ShakeVariableType.Type.DOUBLE),
            VariableDeclarationDescriptor("char", ShakeVariableType.Type.CHAR),
            VariableDeclarationDescriptor("boolean", ShakeVariableType.Type.BOOLEAN),
        ).forEach {

            ShakeAccessDescriber.types.forEach { access ->

                val accessPrefix = access.realPrefix
                val baseList = listOfNotNull(access.prefix)

                "$accessPrefix${it.name}" {
                    val node = ParserTestUtil.parseSingle(
                        "<${it.name}>",
                        "${accessPrefix}val i: ${it.declarationType}",
                        ShakeFieldDeclarationNode::class,
                    )
                    node.name shouldBe "i"
                    node.type!!.type shouldBe it.typeClass
                    node.value shouldBe null
                    node.access.type shouldBe access
                    node.isStatic shouldBe false
                    node.isFinal shouldBe false
                }

                "$accessPrefix${it.name} with value" {
                    val node = ParserTestUtil.parseSingle(
                        "<${it.name} with value>",
                        "${accessPrefix}val i: ${it.declarationType} = 0",
                        ShakeFieldDeclarationNode::class,
                    )
                    node.name shouldBe "i"
                    node.type!!.type shouldBe it.typeClass
                    node.value shouldNotBe null
                    node.value shouldBeOfType ShakeIntegerLiteralNode::class
                    (node.value as ShakeIntegerLiteralNode).number shouldBe 0
                    node.access.type shouldBe access
                    node.isStatic shouldBe false
                    node.isFinal shouldBe false
                }

                "$accessPrefix final ${it.name}" {
                    val list = baseList + listOf("final")

                    // We want to test with each combination.
                    // e.g. `public final` should work as well as `final public`
                    list.allCombinations().forEach { creationParams ->

                        val node = ParserTestUtil.parseSingle(
                            "<${it.name}>",
                            "${creationParams.joinToString(" ")} val i: ${it.declarationType}",
                            ShakeFieldDeclarationNode::class,
                        )

                        node.name shouldBe "i"
                        node.type!!.type shouldBe it.typeClass
                        node.value shouldBe null
                        node.access.type shouldBe access
                        node.isStatic shouldBe false
                        node.isFinal shouldBe true
                    }
                }

                "$accessPrefix final ${it.name} with value" {
                    val list = baseList + listOf("final")

                    // We want to test with each combination.
                    // e.g. `public final` should work as well as `final public`
                    list.allCombinations().forEach { creationParams ->

                        val node = ParserTestUtil.parseSingle(
                            "<${it.name} with value>",
                            "${creationParams.joinToString(" ")} val i: ${it.declarationType} = 0",
                            ShakeFieldDeclarationNode::class,
                        )

                        node.name shouldBe "i"
                        node.type!!.type shouldBe it.typeClass
                        node.value shouldNotBe null
                        node.value shouldBeOfType ShakeIntegerLiteralNode::class
                        (node.value as ShakeIntegerLiteralNode).number shouldBe 0
                        node.access.type shouldBe access
                        node.isStatic shouldBe false
                        node.isFinal shouldBe true
                    }
                }
            }
        }
    },
)

package com.shakelang.shake.parser

import com.shakelang.shake.parser.node.ShakeAccessDescriber
import com.shakelang.shake.parser.node.ShakeVariableType
import com.shakelang.shake.parser.node.expression.ShakeAddNode
import com.shakelang.shake.parser.node.factor.ShakeIntegerNode
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
                node.value shouldBeOfType ShakeIntegerNode::class
                (node.value as ShakeIntegerNode).number shouldBe 0
            }

            "${it.name} with expression" {
                val node = ParserTestUtil.parseStatement("<${it.name}>", "i ${it.operator} 11 + 2", it.nodeClass)
                node shouldBeOfType it.nodeClass
                (node.variable as ShakeVariableUsageNode).identifier.name shouldBe "i"
                (node.variable as ShakeVariableUsageNode).identifier.parent shouldBe null
                node.value shouldNotBe null
                node.value shouldBeOfType ShakeAddNode::class
                (node.value as ShakeAddNode).left shouldBeOfType ShakeIntegerNode::class
                (node.value as ShakeAddNode).right shouldBeOfType ShakeIntegerNode::class
                ((node.value as ShakeAddNode).left as ShakeIntegerNode).number shouldBe 11
                ((node.value as ShakeAddNode).right as ShakeIntegerNode).number shouldBe 2
            }
        }

        "variable incr" {
            val node = ParserTestUtil.parseStatement("<VariableIncreaseTest>", "i ++", ShakeVariableIncreaseNode::class)
            node.variable shouldBeOfType ShakeVariableUsageNode::class
            (node.variable as ShakeVariableUsageNode).identifier.name shouldBe "i"
            (node.variable as ShakeVariableUsageNode).identifier.parent shouldBe null
        }

        "variable decr" {
            val node = ParserTestUtil.parseStatement("<VariableDecreaseTest>", "i --", ShakeVariableDecreaseNode::class)
            node.variable shouldBeOfType ShakeVariableUsageNode::class
            (node.variable as ShakeVariableUsageNode).identifier.name shouldBe "i"
            (node.variable as ShakeVariableUsageNode).identifier.parent shouldBe null
        }

        // // *************************************************************
        // // Variable Declaration C-Style

        class VariableDeclarationDescriptor(
            val declarationType: String,
            val typeClass: ShakeVariableType,
        ) {
            val name get() = "$declarationType declaration"
        }

        listOf(
//        VariableDeclarationDescriptor("var", ShakeVariableType.UNKNOWN),
            VariableDeclarationDescriptor("byte", ShakeVariableType.BYTE),
            VariableDeclarationDescriptor("short", ShakeVariableType.SHORT),
            VariableDeclarationDescriptor("int", ShakeVariableType.INTEGER),
            VariableDeclarationDescriptor("long", ShakeVariableType.LONG),
            VariableDeclarationDescriptor("unsigned byte", ShakeVariableType.UNSIGNED_BYTE),
            VariableDeclarationDescriptor("unsigned short", ShakeVariableType.UNSIGNED_SHORT),
            VariableDeclarationDescriptor("unsigned int", ShakeVariableType.UNSIGNED_INTEGER),
            VariableDeclarationDescriptor("unsigned long", ShakeVariableType.UNSIGNED_LONG),
            VariableDeclarationDescriptor("float", ShakeVariableType.FLOAT),
            VariableDeclarationDescriptor("double", ShakeVariableType.DOUBLE),
            VariableDeclarationDescriptor("char", ShakeVariableType.CHAR),
            VariableDeclarationDescriptor("boolean", ShakeVariableType.BOOLEAN),
        ).forEach {

            ShakeAccessDescriber.entries.forEach { access ->

                val accessPrefix = access.prefix?.plus(" ") ?: ""
                val baseList = listOfNotNull(access.prefix)

                "$accessPrefix${it.name}" {
                    val node = ParserTestUtil.parseSingle(
                        "<${it.name}>",
                        "$accessPrefix${it.declarationType} i",
                        ShakeVariableDeclarationNode::class,
                    )
                    node.name shouldBe "i"
                    node.type shouldBe it.typeClass
                    node.value shouldBe null
                    node.access shouldBe access
                    node.isStatic shouldBe false
                    node.isFinal shouldBe false
                }

                "$accessPrefix${it.name} with value" {
                    val node = ParserTestUtil.parseSingle(
                        "<${it.name} with value>",
                        "$accessPrefix${it.declarationType} i = 0",
                        ShakeVariableDeclarationNode::class,
                    )
                    node.name shouldBe "i"
                    node.type shouldBe it.typeClass
                    node.value shouldNotBe null
                    node.value shouldBeOfType ShakeIntegerNode::class
                    (node.value as ShakeIntegerNode).number shouldBe 0
                    node.access shouldBe access
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
                            "${creationParams.joinToString(" ")} ${it.declarationType} i",
                            ShakeVariableDeclarationNode::class,
                        )

                        node.name shouldBe "i"
                        node.type shouldBe it.typeClass
                        node.value shouldBe null
                        node.access shouldBe access
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
                            "${creationParams.joinToString(" ")} ${it.declarationType} i = 0",
                            ShakeVariableDeclarationNode::class,
                        )

                        node.name shouldBe "i"
                        node.type shouldBe it.typeClass
                        node.value shouldNotBe null
                        node.value shouldBeOfType ShakeIntegerNode::class
                        (node.value as ShakeIntegerNode).number shouldBe 0
                        node.access shouldBe access
                        node.isStatic shouldBe false
                        node.isFinal shouldBe true
                    }
                }
            }
        }
    },
)

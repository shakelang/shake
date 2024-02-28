package com.shakelang.shake.parser

import com.shakelang.shake.parser.node.ShakeAccessDescriber
import com.shakelang.shake.parser.node.ShakeVariableType
import com.shakelang.shake.parser.node.factor.ShakeIntegerLiteralNode
import com.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import com.shakelang.shake.parser.node.functions.ShakeReturnNode
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class FunctionTests : FreeSpec(
    {

        class FunctionDeclarationDescriptor(
            val declarationType: String,
            val typeClass: ShakeVariableType.Type,
        ) {
            val name get() = "$declarationType declaration"
        }

        listOf(
            FunctionDeclarationDescriptor("void", ShakeVariableType.Type.VOID),
            FunctionDeclarationDescriptor("byte", ShakeVariableType.Type.BYTE),
            FunctionDeclarationDescriptor("short", ShakeVariableType.Type.SHORT),
            FunctionDeclarationDescriptor("int", ShakeVariableType.Type.INTEGER),
            FunctionDeclarationDescriptor("long", ShakeVariableType.Type.LONG),
            FunctionDeclarationDescriptor("ubyte", ShakeVariableType.Type.UNSIGNED_BYTE),
            FunctionDeclarationDescriptor("ushort", ShakeVariableType.Type.UNSIGNED_SHORT),
            FunctionDeclarationDescriptor("uint", ShakeVariableType.Type.UNSIGNED_INTEGER),
            FunctionDeclarationDescriptor("ulong", ShakeVariableType.Type.UNSIGNED_LONG),
            FunctionDeclarationDescriptor("float", ShakeVariableType.Type.FLOAT),
            FunctionDeclarationDescriptor("double", ShakeVariableType.Type.DOUBLE),
            FunctionDeclarationDescriptor("char", ShakeVariableType.Type.CHAR),
            FunctionDeclarationDescriptor("boolean", ShakeVariableType.Type.BOOLEAN),
        ).forEach {
            ShakeAccessDescriber.types.forEach { access ->

                val accessPrefix = access.realPrefix
                val baseList = listOfNotNull(access.prefix)

                "$accessPrefix${it.name}" {

                    val fn = ParserTestUtil.parseSingle(
                        "<FunctionTest>",
                        "$accessPrefix${it.declarationType} test() { return 10; }",
                        ShakeFunctionDeclarationNode::class,
                    )
                    fn.access shouldBe access
                    fn.name shouldBe "test"
                    fn.type shouldBe it.typeClass
                    fn.args.size shouldBe 0
                    fn.body shouldNotBe null
                    fn.body!!.children.size shouldBe 1
                    fn.body!!.children[0] shouldBeOfType ShakeReturnNode::class
                    val ret = fn.body!!.children[0] as ShakeReturnNode
                    ret.value shouldBeOfType ShakeIntegerLiteralNode::class
                    val int = ret.value as ShakeIntegerLiteralNode
                    int.number shouldBe 10
                }

                "${accessPrefix}final ${it.name}" {
                    (baseList + listOf("final")).allCombinations().forEach { creationParams ->
                        val fn = ParserTestUtil.parseSingle(
                            "<FunctionTest>",
                            "${creationParams.joinToString(" ")} ${it.declarationType} test() { return 10; }",
                            ShakeFunctionDeclarationNode::class,
                        )
                        fn.access shouldBe access
                        fn.name shouldBe "test"
                        fn.type shouldBe it.typeClass
                        fn.args.size shouldBe 0
                        fn.body shouldNotBe null
                        fn.body!!.children.size shouldBe 1
                        fn.body!!.children[0] shouldBeOfType ShakeReturnNode::class
                        val ret = fn.body!!.children[0] as ShakeReturnNode
                        ret.value shouldBeOfType ShakeIntegerLiteralNode::class
                        val int = ret.value as ShakeIntegerLiteralNode
                        int.number shouldBe 10
                    }
                }
            }
        }

        // ********************************************************************
        // C-Style

        "return" {
            val tree = ParserTestUtil.parseStatement("<FunctionTest>", "return 10;")
            tree.children.size shouldBe 1
            tree.children[0] shouldBeOfType ShakeReturnNode::class
            val node = tree.children[0] as ShakeReturnNode
            node.value shouldBeOfType ShakeIntegerLiteralNode::class
            val int = node.value as ShakeIntegerLiteralNode
            int.number shouldBe 10
        }
    },
)

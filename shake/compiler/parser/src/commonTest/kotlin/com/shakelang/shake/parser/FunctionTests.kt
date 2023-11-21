package com.shakelang.shake.parser

import com.shakelang.shake.allCombinations
import com.shakelang.shake.parser.node.ShakeAccessDescriber
import com.shakelang.shake.parser.node.ShakeVariableType
import com.shakelang.shake.parser.node.factor.ShakeIntegerNode
import com.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import com.shakelang.shake.parser.node.functions.ShakeReturnNode
import com.shakelang.shake.shouldBeOfType
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class FunctionTests : FreeSpec({

    class FunctionDeclarationDescriptor(
        val declarationType: String,
        val typeClass: ShakeVariableType
    ) {
        val name get() = "$declarationType declaration"
    }

    listOf(
        FunctionDeclarationDescriptor("void", ShakeVariableType.VOID),
        FunctionDeclarationDescriptor("byte", ShakeVariableType.BYTE),
        FunctionDeclarationDescriptor("short", ShakeVariableType.SHORT),
        FunctionDeclarationDescriptor("int", ShakeVariableType.INTEGER),
        FunctionDeclarationDescriptor("long", ShakeVariableType.LONG),
        FunctionDeclarationDescriptor("unsigned byte", ShakeVariableType.UNSIGNED_BYTE),
        FunctionDeclarationDescriptor("unsigned short", ShakeVariableType.UNSIGNED_SHORT),
        FunctionDeclarationDescriptor("unsigned int", ShakeVariableType.UNSIGNED_INTEGER),
        FunctionDeclarationDescriptor("unsigned long", ShakeVariableType.UNSIGNED_LONG),
        FunctionDeclarationDescriptor("float", ShakeVariableType.FLOAT),
        FunctionDeclarationDescriptor("double", ShakeVariableType.DOUBLE),
        FunctionDeclarationDescriptor("char", ShakeVariableType.CHAR),
        FunctionDeclarationDescriptor("boolean", ShakeVariableType.BOOLEAN)
    ).forEach {
        ShakeAccessDescriber.entries.forEach { access ->

            val accessPrefix = access.prefix?.plus(" ") ?: ""
            val baseList = listOfNotNull(access.prefix)

            "$accessPrefix${it.name}" {

                val fn = ParserTestUtil.parseSingle(
                    "<FunctionTest>",
                    "$accessPrefix${it.declarationType} test() { return 10; }",
                    ShakeFunctionDeclarationNode::class
                )
                fn.access shouldBe access
                fn.name shouldBe "test"
                fn.type shouldBe it.typeClass
                fn.args.size shouldBe 0
                fn.body shouldNotBe null
                fn.body!!.children.size shouldBe 1
                fn.body!!.children[0] shouldBeOfType ShakeReturnNode::class
                val ret = fn.body!!.children[0] as ShakeReturnNode
                ret.value shouldBeOfType ShakeIntegerNode::class
                val int = ret.value as ShakeIntegerNode
                int.number shouldBe 10
            }

            "${accessPrefix}final ${it.name}" {
                (baseList + listOf("final")).allCombinations().forEach { creationParams ->
                    val fn = ParserTestUtil.parseSingle(
                        "<FunctionTest>",
                        "${creationParams.joinToString(" ")} ${it.declarationType} test() { return 10; }",
                        ShakeFunctionDeclarationNode::class
                    )
                    fn.access shouldBe access
                    fn.name shouldBe "test"
                    fn.type shouldBe it.typeClass
                    fn.args.size shouldBe 0
                    fn.body shouldNotBe null
                    fn.body!!.children.size shouldBe 1
                    fn.body!!.children[0] shouldBeOfType ShakeReturnNode::class
                    val ret = fn.body!!.children[0] as ShakeReturnNode
                    ret.value shouldBeOfType ShakeIntegerNode::class
                    val int = ret.value as ShakeIntegerNode
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
        node.value shouldBeOfType ShakeIntegerNode::class
        val int = node.value as ShakeIntegerNode
        int.number shouldBe 10
    }
})

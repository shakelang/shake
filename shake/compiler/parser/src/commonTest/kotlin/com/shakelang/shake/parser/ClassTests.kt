package com.shakelang.shake.parser

import com.shakelang.shake.parser.node.ShakeAccessDescriber
import com.shakelang.shake.parser.node.ShakeVariableType
import com.shakelang.shake.parser.node.factor.ShakeIntegerNode
import com.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import com.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import com.shakelang.shake.parser.node.objects.ShakeConstructorDeclarationNode
import com.shakelang.shake.parser.node.variables.ShakeVariableDeclarationNode
import com.shakelang.shake.shouldBeOfType
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class ClassTests : FreeSpec({

    ShakeAccessDescriber.entries.forEach { access ->

        val accessPrefix = access.prefix?.plus(" ") ?: ""
        val baseList = listOfNotNull(access.prefix)

        "${accessPrefix}class" {

            val tree = ParserTestUtil.parse("<${accessPrefix}class test>", "${accessPrefix}class test {}")
            tree.children.size shouldBe 1
            tree.children[0] shouldBeOfType ShakeClassDeclarationNode::class
            val node = tree.children[0] as ShakeClassDeclarationNode
            node.access shouldBe access
            node.isStatic shouldBe false
            node.isFinal shouldBe false
            node.name shouldBe "test"
            node.fields.size shouldBe 0
            node.methods.size shouldBe 0
            node.classes.size shouldBe 0

        }

        "${accessPrefix}final class" {

            val tree = ParserTestUtil.parse("<${accessPrefix}final class test>", "${accessPrefix}final class test {}")
            tree.children.size shouldBe 1
            tree.children[0] shouldBeOfType ShakeClassDeclarationNode::class
            val node = tree.children[0] as ShakeClassDeclarationNode
            node.access shouldBe access
            node.isStatic shouldBe false
            node.isFinal shouldBe true
            node.name shouldBe "test"
            node.fields.size shouldBe 0
            node.methods.size shouldBe 0
            node.classes.size shouldBe 0

        }

        ShakeAccessDescriber.entries.forEach { access2 ->
            val accessPrefix2 = access2.prefix?.plus(" ") ?: ""
            val baseList2 = listOfNotNull(access2.prefix)
            "${accessPrefix}class with a ${accessPrefix2}field" {

                val tree =
                    ParserTestUtil.parse(
                        "<${accessPrefix}class test>",
                        "${accessPrefix}class test { ${accessPrefix2}int i = 0; }"
                    )
                tree.children.size shouldBe 1
                tree.children[0] shouldBeOfType ShakeClassDeclarationNode::class
                val node = tree.children[0] as ShakeClassDeclarationNode
                node.access shouldBe access
                node.isStatic shouldBe false
                node.isFinal shouldBe false
                node.name shouldBe "test"
                node.fields.size shouldBe 1
                node.methods.size shouldBe 0
                node.classes.size shouldBe 0
                node.fields[0] shouldBeOfType ShakeVariableDeclarationNode::class
                val variable = node.fields[0]
                variable.type.type shouldBe ShakeVariableType.Type.INTEGER
                variable.access shouldBe access2
                variable.value shouldNotBe null
                variable.value shouldBeOfType ShakeIntegerNode::class
                (variable.value as ShakeIntegerNode).number shouldBe 0
                variable.name shouldBe "i"
                variable.isStatic shouldBe false
                variable.isFinal shouldBe false

            }

            "${accessPrefix}class with a ${accessPrefix2}method" {

                val tree =
                    ParserTestUtil.parse(
                        "<${accessPrefix}class test>",
                        "${accessPrefix}class test { ${accessPrefix2}void f() {} }"
                    )
                tree.children.size shouldBe 1
                tree.children[0] shouldBeOfType ShakeClassDeclarationNode::class
                val node = tree.children[0] as ShakeClassDeclarationNode
                node.access shouldBe access
                node.isStatic shouldBe false
                node.isFinal shouldBe false
                node.name shouldBe "test"
                node.fields.size shouldBe 0
                node.methods.size shouldBe 1
                node.classes.size shouldBe 0
                node.methods[0] shouldBeOfType ShakeFunctionDeclarationNode::class
                val function = node.methods[0]
                function.type.type shouldBe ShakeVariableType.Type.VOID
                function.access shouldBe access2
                function.args.size shouldBe 0
                function.name shouldBe "f"
                function.isStatic shouldBe false
                function.isFinal shouldBe false

            }

            "${accessPrefix}class with a ${accessPrefix2}class" {

                val tree =
                    ParserTestUtil.parse(
                        "<${accessPrefix}class test>",
                        "${accessPrefix}class test { ${accessPrefix2}class Test {} }"
                    )
                tree.children.size shouldBe 1
                tree.children[0] shouldBeOfType ShakeClassDeclarationNode::class
                val node = tree.children[0] as ShakeClassDeclarationNode
                node.access shouldBe access
                node.isStatic shouldBe false
                node.isFinal shouldBe false
                node.name shouldBe "test"
                node.fields.size shouldBe 0
                node.methods.size shouldBe 0
                node.classes.size shouldBe 1
                node.classes[0] shouldBeOfType ShakeClassDeclarationNode::class
                val clazz = node.classes[0]
                clazz.access shouldBe access2
                clazz.isStatic shouldBe false
                clazz.isFinal shouldBe false
                clazz.name shouldBe "Test"
                clazz.fields.size shouldBe 0
                clazz.methods.size shouldBe 0
                clazz.classes.size shouldBe 0

            }

            "${accessPrefix}class with a ${accessPrefix2}constructor" {

                val tree =
                    ParserTestUtil.parse(
                        "<${accessPrefix}class test>",
                        "${accessPrefix}class test { ${accessPrefix2}constructor() {} }"
                    )
                tree.children.size shouldBe 1
                tree.children[0] shouldBeOfType ShakeClassDeclarationNode::class
                val node = tree.children[0] as ShakeClassDeclarationNode
                node.access shouldBe access
                node.isStatic shouldBe false
                node.isFinal shouldBe false
                node.name shouldBe "test"
                node.fields.size shouldBe 0
                node.methods.size shouldBe 0
                node.classes.size shouldBe 0
                node.constructors.size shouldBe 1
                node.constructors[0] shouldBeOfType ShakeConstructorDeclarationNode::class
                val constructor = node.constructors[0]
                constructor.access shouldBe access2
                constructor.args.size shouldBe 0
                constructor.name shouldBe null
            }
        }
    }
})
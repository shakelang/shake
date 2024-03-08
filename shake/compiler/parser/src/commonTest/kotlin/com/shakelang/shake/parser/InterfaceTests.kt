package com.shakelang.shake.parser

import com.shakelang.shake.parser.node.misc.ShakeAccessDescriber
import com.shakelang.shake.parser.node.misc.ShakeVariableType
import com.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import com.shakelang.shake.parser.node.outer.ShakeFieldDeclarationNode
import com.shakelang.shake.parser.node.outer.ShakeMethodDeclarationNode
import com.shakelang.shake.parser.node.values.factor.ShakeIntegerLiteralNode
import com.shakelang.util.parseutils.parser.AbstractParser
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class InterfaceTests : FreeSpec(
    {
        "final interface" {
            shouldThrow<AbstractParser.ParserError> {
                ParserTestUtil.parse("<InterfaceTest>", "final interface test {}")
            }
        }

        ShakeAccessDescriber.types.forEach { access ->

            val accessPrefix = access.realPrefix

            "${accessPrefix}interface" {

                val tree = ParserTestUtil.parse("<${accessPrefix}interface test>", "${accessPrefix}interface test {}")
                tree.children.size shouldBe 1
                tree.children[0] shouldBeOfType ShakeClassDeclarationNode::class
                val node = tree.children[0] as ShakeClassDeclarationNode
                node.access.type shouldBe access
                node.isStatic shouldBe false
                node.isFinal shouldBe false
                node.name shouldBe "test"
                node.fields.size shouldBe 0
                node.methods.size shouldBe 0
                node.classes.size shouldBe 0
            }

            "${accessPrefix}final interface" {

                shouldThrow<AbstractParser.ParserError> {
                    ParserTestUtil.parse("<${accessPrefix}interface test>", "${accessPrefix}final interface test {}")
                }
            }

            ShakeAccessDescriber.types.forEach { access2 ->
                val accessPrefix2 = access2.realPrefix
                "${accessPrefix}class with a ${accessPrefix2}field" {

                    val tree =
                        ParserTestUtil.parse(
                            "<${accessPrefix}interface test>",
                            "${accessPrefix}interface test { ${accessPrefix2}val i: int = 0; }",
                        )
                    tree.children.size shouldBe 1
                    tree.children[0] shouldBeOfType ShakeClassDeclarationNode::class
                    val node = tree.children[0] as ShakeClassDeclarationNode
                    node.access.type shouldBe access
                    node.isStatic shouldBe false
                    node.isFinal shouldBe false
                    node.name shouldBe "test"
                    node.fields.size shouldBe 1
                    node.methods.size shouldBe 0
                    node.classes.size shouldBe 0
                    node.fields[0] shouldBeOfType ShakeFieldDeclarationNode::class
                    val variable = node.fields[0]
                    variable.type!!.type shouldBe ShakeVariableType.Type.INTEGER
                    variable.access.type shouldBe access2
                    variable.value shouldNotBe null
                    variable.value shouldBeOfType ShakeIntegerLiteralNode::class
                    (variable.value as ShakeIntegerLiteralNode).value shouldBe 0
                    variable.name shouldBe "i"
                    variable.isStatic shouldBe false
                    variable.isFinal shouldBe false
                }

                "${accessPrefix}interface with a ${accessPrefix2}method" {

                    val tree =
                        ParserTestUtil.parse(
                            "<${accessPrefix}interface test>",
                            "${accessPrefix}interface test { ${accessPrefix2}fun f() {} }",
                        )
                    tree.children.size shouldBe 1
                    tree.children[0] shouldBeOfType ShakeClassDeclarationNode::class
                    val node = tree.children[0] as ShakeClassDeclarationNode
                    node.access.type shouldBe access
                    node.isStatic shouldBe false
                    node.isFinal shouldBe false
                    node.name shouldBe "test"
                    node.fields.size shouldBe 0
                    node.methods.size shouldBe 1
                    node.classes.size shouldBe 0
                    node.methods[0] shouldBeOfType ShakeMethodDeclarationNode::class
                    val function = node.methods[0]
                    function.type.type shouldBe ShakeVariableType.Type.VOID
                    function.access.type shouldBe access2
                    function.args.size shouldBe 0
                    function.name shouldBe "f"
                    function.isStatic shouldBe false
                    function.isFinal shouldBe false
                }

                "${accessPrefix}interface with a ${accessPrefix2}interface" {

                    val tree =
                        ParserTestUtil.parse(
                            "<${accessPrefix}interface test>",
                            "${accessPrefix}interface test { ${accessPrefix2}class Test {} }",
                        )
                    tree.children.size shouldBe 1
                    tree.children[0] shouldBeOfType ShakeClassDeclarationNode::class
                    val node = tree.children[0] as ShakeClassDeclarationNode
                    node.access.type shouldBe access
                    node.isStatic shouldBe false
                    node.isFinal shouldBe false
                    node.name shouldBe "test"
                    node.fields.size shouldBe 0
                    node.methods.size shouldBe 0
                    node.classes.size shouldBe 1
                    node.classes[0] shouldBeOfType ShakeClassDeclarationNode::class
                    val clazz = node.classes[0]
                    clazz.access.type shouldBe access2
                    clazz.isStatic shouldBe false
                    clazz.isFinal shouldBe false
                    clazz.name shouldBe "Test"
                    clazz.fields.size shouldBe 0
                    clazz.methods.size shouldBe 0
                    clazz.classes.size shouldBe 0
                }
            }
        }
    },
)

package com.shakelang.shake.parser

import com.shakelang.shake.parser.node.mixed.ShakeInvocationNode
import com.shakelang.shake.parser.node.outer.ShakeClassDeclarationNode
import com.shakelang.shake.parser.node.outer.ShakeFieldDeclarationNode
import com.shakelang.shake.parser.node.outer.ShakeMethodDeclarationNode
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class GenericTests :
    FreeSpec(
        {

            "test variable with generic type" {
                val code = "val a: List<int>"
                val field = ParserTestUtil.parseSingle("<GenericTest>", code, ShakeFieldDeclarationNode::class)
                field.name shouldBe "a"
                field.type shouldNotBe null

                val type = field.type!!
                type.namespace.stringify() shouldBe "List"

                type.genericTypes?.openToken shouldNotBe null
                type.genericTypes?.openToken!!.value shouldBe "<"
                type.genericTypes?.closeToken shouldNotBe null
                type.genericTypes?.closeToken!!.value shouldBe ">"

                type.genericTypes?.commaTokens shouldNotBe null
                type.genericTypes?.commaTokens!!.size shouldBe 0

                type.genericTypes?.arguments shouldNotBe null
                type.genericTypes?.arguments!!.size shouldBe 1
                type.genericTypes?.arguments!![0].namespace.stringify() shouldBe "int"

                type.isGeneric shouldBe true
            }

            "test variable with generic type with multiple types" {
                val code = "val a: Map<int,string>"
                val field = ParserTestUtil.parseSingle("<GenericTest>", code, ShakeFieldDeclarationNode::class)
                field.name shouldBe "a"
                field.type shouldNotBe null

                val type = field.type!!
                type.namespace.stringify() shouldBe "Map"

                type.genericTypes?.openToken shouldNotBe null
                type.genericTypes?.openToken!!.value shouldBe "<"
                type.genericTypes?.closeToken shouldNotBe null
                type.genericTypes?.closeToken!!.value shouldBe ">"

                type.genericTypes?.commaTokens shouldNotBe null
                type.genericTypes?.commaTokens!!.size shouldBe 1
                type.genericTypes?.commaTokens!![0].value shouldBe ","

                type.genericTypes shouldNotBe null
                type.genericTypes!!.arguments.size shouldBe 2
                type.genericTypes!!.arguments[0].namespace.stringify() shouldBe "int"
                type.genericTypes!!.arguments[1].namespace.stringify() shouldBe "string"

                type.isGeneric shouldBe true
            }

            "test variable with generic type with multiple types and spaces" {
                val code = "val a: Map<int, string>"
                val field = ParserTestUtil.parseSingle("<GenericTest>", code, ShakeFieldDeclarationNode::class)
                field.name shouldBe "a"
                field.type shouldNotBe null

                val type = field.type!!
                type.namespace.stringify() shouldBe "Map"

                type.genericTypes?.openToken shouldNotBe null
                type.genericTypes?.openToken!!.value shouldBe "<"
                type.genericTypes?.closeToken shouldNotBe null
                type.genericTypes?.closeToken!!.value shouldBe ">"

                type.genericTypes?.commaTokens shouldNotBe null
                type.genericTypes?.commaTokens!!.size shouldBe 1
                type.genericTypes?.commaTokens!![0].value shouldBe ","

                type.genericTypes shouldNotBe null
                type.genericTypes!!.arguments.size shouldBe 2
                type.genericTypes!!.arguments[0].namespace.stringify() shouldBe "int"
                type.genericTypes!!.arguments[1].namespace.stringify() shouldBe "string"

                type.isGeneric shouldBe true
            }

            "test variable with nested generic type" {
                val code = "val a: List<List<int>>"
                val field = ParserTestUtil.parseSingle("<GenericTest>", code, ShakeFieldDeclarationNode::class)
                field.name shouldBe "a"
                field.type shouldNotBe null

                val type = field.type!!
                type.namespace.stringify() shouldBe "List"

                type.genericTypes?.openToken shouldNotBe null
                type.genericTypes?.openToken!!.value shouldBe "<"
                type.genericTypes?.closeToken shouldNotBe null
                type.genericTypes?.closeToken!!.value shouldBe ">"

                type.genericTypes?.commaTokens shouldNotBe null
                type.genericTypes?.commaTokens!!.size shouldBe 0

                type.isGeneric shouldBe true

                type.genericTypes shouldNotBe null
                type.genericTypes!!.arguments.size shouldBe 1

                val innerType = type.genericTypes!!.arguments[0]
                innerType.namespace.stringify() shouldBe "List"

                innerType.genericTypes?.openToken shouldNotBe null
                innerType.genericTypes?.openToken!!.value shouldBe "<"
                innerType.genericTypes?.closeToken shouldNotBe null
                innerType.genericTypes?.closeToken!!.value shouldBe ">"

                innerType.genericTypes?.commaTokens shouldNotBe null
                innerType.genericTypes?.commaTokens!!.size shouldBe 0

                innerType.genericTypes shouldNotBe null
                innerType.genericTypes!!.arguments.size shouldBe 1
                innerType.genericTypes!!.arguments[0].namespace.stringify() shouldBe "int"

                innerType.isGeneric shouldBe true
            }

            "test class with generic type (no extends)" {
                val code = """
                    class Test<T> {
                        val a: T
                    }
                """.trimIndent()

                val clazz = ParserTestUtil.parseSingle("<GenericTest>", code, ShakeClassDeclarationNode::class)
                clazz.name shouldBe "Test"
                clazz.generics shouldNotBe null

                val typeArgs = clazz.generics!!
                typeArgs.openToken shouldNotBe null
                typeArgs.openToken.value shouldBe "<"
                typeArgs.closeToken shouldNotBe null
                typeArgs.closeToken.value shouldBe ">"

                typeArgs.commaTokens shouldNotBe null
                typeArgs.commaTokens.size shouldBe 0

                typeArgs.generics shouldNotBe null
                typeArgs.generics.size shouldBe 1

                val arg = typeArgs.generics[0]
                arg.nameToken shouldNotBe null
                arg.nameToken.value shouldBe "T"

                arg.type shouldBe null
                arg.colonToken shouldBe null
            }

            "test class with generic type (extends)" {
                val code = """
                    class Test<T : Object> {
                        val a: T
                    }
                """.trimIndent()

                val clazz = ParserTestUtil.parseSingle("<GenericTest>", code, ShakeClassDeclarationNode::class)
                clazz.name shouldBe "Test"
                clazz.generics shouldNotBe null

                val typeArgs = clazz.generics!!
                typeArgs.openToken shouldNotBe null
                typeArgs.openToken.value shouldBe "<"
                typeArgs.closeToken shouldNotBe null
                typeArgs.closeToken.value shouldBe ">"

                typeArgs.commaTokens shouldNotBe null
                typeArgs.commaTokens.size shouldBe 0

                typeArgs.generics shouldNotBe null
                typeArgs.generics.size shouldBe 1

                val arg = typeArgs.generics[0]
                arg.nameToken shouldNotBe null
                arg.nameToken.value shouldBe "T"

                arg.type shouldNotBe null

                arg.type!!.namespace.stringify() shouldBe "Object"

                arg.colonToken shouldNotBe null
                arg.colonToken!!.value shouldBe ":"
            }

            "test class extending generic" {
                val code = """
                    class Test : List<int> {
                    }
                """.trimIndent()

                val clazz = ParserTestUtil.parseSingle("<GenericTest>", code, ShakeClassDeclarationNode::class)
                clazz.name shouldBe "Test"
                clazz.superClasses shouldNotBe null
                clazz.superClasses.size shouldBe 1

                val superClass = clazz.superClasses[0]
                superClass.type.namespace.stringify() shouldBe "List"
                superClass.type.genericTypes?.openToken shouldNotBe null
                superClass.type.genericTypes?.openToken!!.value shouldBe "<"
                superClass.type.genericTypes?.closeToken shouldNotBe null
                superClass.type.genericTypes?.closeToken!!.value shouldBe ">"
                superClass.type.genericTypes shouldNotBe null
                superClass.type.genericTypes!!.arguments.size shouldBe 1
                superClass.type.genericTypes!!.arguments[0].namespace.stringify() shouldBe "int"
            }

            "test interface with generic type" {
                val code = """
                    interface Test<T> {
                        fun test(a: T): T
                    }
                """.trimIndent()

                val clazz = ParserTestUtil.parseSingle("<GenericTest>", code, ShakeClassDeclarationNode::class)
                clazz.name shouldBe "Test"
                clazz.generics shouldNotBe null

                val typeArgs = clazz.generics!!
                typeArgs.openToken shouldNotBe null
                typeArgs.openToken.value shouldBe "<"
                typeArgs.closeToken shouldNotBe null
                typeArgs.closeToken.value shouldBe ">"

                typeArgs.commaTokens shouldNotBe null
                typeArgs.commaTokens.size shouldBe 0

                typeArgs.generics shouldNotBe null
                typeArgs.generics.size shouldBe 1

                val arg = typeArgs.generics[0]
                arg.nameToken shouldNotBe null
                arg.nameToken.value shouldBe "T"

                arg.type shouldBe null
                arg.colonToken shouldBe null
            }

            "test interface with generic type (extends)" {
                val code = """
                    interface Test<T : Object> {
                        fun test(a: T): T
                    }
                """.trimIndent()

                val clazz = ParserTestUtil.parseSingle("<GenericTest>", code, ShakeClassDeclarationNode::class)
                clazz.name shouldBe "Test"
                clazz.generics shouldNotBe null

                val typeArgs = clazz.generics!!
                typeArgs.openToken shouldNotBe null
                typeArgs.openToken.value shouldBe "<"
                typeArgs.closeToken shouldNotBe null
                typeArgs.closeToken.value shouldBe ">"

                typeArgs.commaTokens shouldNotBe null
                typeArgs.commaTokens.size shouldBe 0

                typeArgs.generics shouldNotBe null
                typeArgs.generics.size shouldBe 1

                val arg = typeArgs.generics[0]
                arg.nameToken shouldNotBe null
                arg.nameToken.value shouldBe "T"

                arg.type shouldNotBe null

                arg.type!!.namespace.stringify() shouldBe "Object"

                arg.colonToken shouldNotBe null
                arg.colonToken!!.value shouldBe ":"
            }

            "test interface extending generic" {
                val code = """
                    interface Test : List<int> {
                        fun test(a: int): int
                    }
                """.trimIndent()

                val clazz = ParserTestUtil.parseSingle("<GenericTest>", code, ShakeClassDeclarationNode::class)
                clazz.name shouldBe "Test"
                clazz.superClasses shouldNotBe null
                clazz.superClasses.size shouldBe 1

                val superClass = clazz.superClasses[0]
                superClass.type.namespace.stringify() shouldBe "List"
                superClass.type.genericTypes?.openToken shouldNotBe null
                superClass.type.genericTypes?.openToken!!.value shouldBe "<"
                superClass.type.genericTypes?.closeToken shouldNotBe null
                superClass.type.genericTypes?.closeToken!!.value shouldBe ">"
                superClass.type.genericTypes shouldNotBe null
                superClass.type.genericTypes!!.arguments.size shouldBe 1
                superClass.type.genericTypes!!.arguments[0].namespace.stringify() shouldBe "int"
            }

            "test declaration of a generic function" {

                val code = """
                    fun <T> test(a: T): T {
                        return a
                    }
                """.trimIndent()

                val method = ParserTestUtil.parseSingle("<GenericTest>", code, ShakeMethodDeclarationNode::class)
                method.name shouldBe "test"
                method.generics shouldNotBe null

                val typeArgs = method.generics!!
                typeArgs.openToken shouldNotBe null
                typeArgs.openToken.value shouldBe "<"
                typeArgs.closeToken shouldNotBe null
                typeArgs.closeToken.value shouldBe ">"

                typeArgs.commaTokens shouldNotBe null
                typeArgs.commaTokens.size shouldBe 0

                typeArgs.generics shouldNotBe null
                typeArgs.generics.size shouldBe 1

                val arg = typeArgs.generics[0]
                arg.nameToken shouldNotBe null
                arg.nameToken.value shouldBe "T"

                arg.type shouldBe null
                arg.colonToken shouldBe null
            }

            "test declaration of a generic function with extends" {

                val code = """
                    fun <T : Object> test(a: T): T {
                        return a
                    }
                """.trimIndent()

                val method = ParserTestUtil.parseSingle("<GenericTest>", code, ShakeMethodDeclarationNode::class)
                method.name shouldBe "test"
                method.generics shouldNotBe null

                val typeArgs = method.generics!!
                typeArgs.openToken shouldNotBe null
                typeArgs.openToken.value shouldBe "<"
                typeArgs.closeToken shouldNotBe null
                typeArgs.closeToken.value shouldBe ">"

                typeArgs.commaTokens shouldNotBe null
                typeArgs.commaTokens.size shouldBe 0

                typeArgs.generics shouldNotBe null
                typeArgs.generics.size shouldBe 1

                val arg = typeArgs.generics[0]
                arg.nameToken shouldNotBe null
                arg.nameToken.value shouldBe "T"

                arg.type shouldNotBe null
                arg.type!!.namespace.stringify() shouldBe "Object"
                arg.colonToken shouldNotBe null
                arg.colonToken!!.value shouldBe ":"
            }

            "test function call with generic args" {

                val code = """
                    val a = test<int>(5)
                """.trimIndent()

                val field = ParserTestUtil.parseSingle("<GenericTest>", code, ShakeFieldDeclarationNode::class)
                field.name shouldBe "a"
                field.type shouldBe null
                field.value shouldNotBe null
                (field.value!! is ShakeInvocationNode) shouldBe true
                val invocation = field.value!! as ShakeInvocationNode
                invocation.typeArguments shouldNotBe null
                invocation.typeArguments!!.openToken shouldNotBe null
                invocation.typeArguments!!.openToken.value shouldBe "<"
                invocation.typeArguments!!.closeToken shouldNotBe null
                invocation.typeArguments!!.closeToken.value shouldBe ">"
                invocation.typeArguments!!.commaTokens shouldNotBe null
                invocation.typeArguments!!.commaTokens.size shouldBe 0
                invocation.typeArguments!!.arguments.size shouldBe 1
                invocation.typeArguments!!.arguments[0].namespace.stringify() shouldBe "int"
            }

            "test function call with deep generic args" {

                val code = """
                    val a = test<List<int>>(5)
                """.trimIndent()

                val field = ParserTestUtil.parseSingle("<GenericTest>", code, ShakeFieldDeclarationNode::class)
                field.name shouldBe "a"
                field.type shouldBe null
                field.value shouldNotBe null
                (field.value!! is ShakeInvocationNode) shouldBe true
                val invocation = field.value!! as ShakeInvocationNode
                invocation.typeArguments shouldNotBe null
                invocation.typeArguments!!.openToken shouldNotBe null
                invocation.typeArguments!!.openToken.value shouldBe "<"
                invocation.typeArguments!!.closeToken shouldNotBe null
                invocation.typeArguments!!.closeToken.value shouldBe ">"
                invocation.typeArguments!!.commaTokens shouldNotBe null
                invocation.typeArguments!!.commaTokens.size shouldBe 0
                invocation.typeArguments!!.arguments.size shouldBe 1
                invocation.typeArguments!!.arguments[0].namespace.stringify() shouldBe "List"
                invocation.typeArguments!!.arguments[0].genericTypes shouldNotBe null
                invocation.typeArguments!!.arguments[0].genericTypes!!.openToken shouldNotBe null
                invocation.typeArguments!!.arguments[0].genericTypes!!.openToken.value shouldBe "<"
                invocation.typeArguments!!.arguments[0].genericTypes!!.closeToken shouldNotBe null
                invocation.typeArguments!!.arguments[0].genericTypes!!.closeToken.value shouldBe ">"
                invocation.typeArguments!!.arguments[0].genericTypes!!.commaTokens shouldNotBe null
                invocation.typeArguments!!.arguments[0].genericTypes!!.commaTokens.size shouldBe 0
                invocation.typeArguments!!.arguments[0].genericTypes!!.arguments.size shouldBe 1
                invocation.typeArguments!!.arguments[0].genericTypes!!.arguments[0].namespace.stringify() shouldBe "int"
            }
        },
    )

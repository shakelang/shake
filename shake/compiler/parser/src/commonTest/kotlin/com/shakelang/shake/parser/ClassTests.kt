package com.shakelang.shake.parser

import com.shakelang.shake.assertType
import com.shakelang.shake.parser.node.ShakeAccessDescriber
import com.shakelang.shake.parser.node.ShakeVariableType
import com.shakelang.shake.parser.node.functions.ShakeFunctionDeclarationNode
import com.shakelang.shake.parser.node.objects.ShakeClassDeclarationNode
import com.shakelang.shake.parser.node.variables.ShakeVariableDeclarationNode
import io.kotest.core.spec.style.FreeSpec
import kotlin.test.*

class ClassTests: FreeSpec({

    ShakeAccessDescriber.entries.forEach { access ->

        val accessPrefix = access.prefix?.plus(" ") ?: ""
        val baseList = listOfNotNull(access.prefix)

        "$accessPrefix class" {

            val tree = ParserTestUtil.parse("<${accessPrefix}class test>", "$accessPrefix class test {}")
            assertEquals(1, tree.children.size)
            assertType(ShakeClassDeclarationNode::class, tree.children[0])
            val node = tree.children[0] as ShakeClassDeclarationNode
            assertSame(access, node.access)
            assertFalse(node.isStatic)
            assertFalse(node.isFinal)
            assertEquals("test", node.name)
            assertSame(0, node.fields.size)
            assertSame(0, node.methods.size)
            assertSame(0, node.classes.size)

        }

        "$accessPrefix final class" {

            val tree = ParserTestUtil.parse("<${accessPrefix}final class test>", "$accessPrefix final class test {}")
            assertEquals(1, tree.children.size)
            assertType(ShakeClassDeclarationNode::class, tree.children[0])
            val node = tree.children[0] as ShakeClassDeclarationNode
            assertSame(access, node.access)
            assertFalse(node.isStatic)
            assertTrue(node.isFinal)
            assertEquals("test", node.name)
            assertSame(0, node.fields.size)
            assertSame(0, node.methods.size)
            assertSame(0, node.classes.size)

        }

        ShakeAccessDescriber.entries.forEach { access2 ->
            val accessPrefix2 = access2.prefix?.plus(" ") ?: ""
            val baseList2 = listOfNotNull(access2.prefix)
            "$accessPrefix class with a ${access2}field" {

                val tree =
                    ParserTestUtil.parse("<${accessPrefix}class test>", "$accessPrefix class test { int i = 0; }")
                assertEquals(1, tree.children.size)
                assertType(ShakeClassDeclarationNode::class, tree.children[0])
                val node = tree.children[0] as ShakeClassDeclarationNode
                assertSame(access, node.access)
                assertFalse(node.isStatic)
                assertFalse(node.isFinal)
                assertEquals("test", node.name)
                assertSame(1, node.fields.size)
                assertSame(0, node.methods.size)
                assertSame(0, node.classes.size)
                assertType(ShakeVariableDeclarationNode::class, node.fields[0])
                val variable = node.fields[0]
                assertSame(ShakeVariableType.Type.INTEGER, variable.type.type)
                assertSame(access, variable.access)
                assertNotNull(variable.value)
                assertEquals("i", variable.name)
                assertFalse(variable.isStatic)
                assertFalse(variable.isFinal)

            }

            "$accessPrefix class with a ${access2}method" {

                val tree =
                    ParserTestUtil.parse("<${accessPrefix}class test>", "$accessPrefix class test { void f() {} }")
                assertEquals(1, tree.children.size)
                assertType(ShakeClassDeclarationNode::class, tree.children[0])
                val node = tree.children[0] as ShakeClassDeclarationNode
                assertSame(access, node.access)
                assertFalse(node.isStatic)
                assertFalse(node.isFinal)
                assertEquals("test", node.name)
                assertSame(0, node.fields.size)
                assertSame(1, node.methods.size)
                assertSame(0, node.classes.size)
                assertType(ShakeFunctionDeclarationNode::class, node.methods[0])
                val function = node.methods[0]
                assertSame(ShakeVariableType.Type.VOID, function.type.type)
                assertSame(access, function.access)
                assertSame(0, function.args.size)
                assertEquals("f", function.name)
                assertFalse(function.isStatic)
                assertFalse(function.isFinal)

            }
        }
    }
})
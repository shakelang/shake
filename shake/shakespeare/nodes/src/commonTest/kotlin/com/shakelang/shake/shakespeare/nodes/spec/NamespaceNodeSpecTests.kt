package com.shakelang.shake.shakespeare.nodes.spec

import com.shakelang.shake.lexer.token.ShakeTokenType
import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.shake.shakespeare.spec.NamespaceSpec
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class NamespaceNodeSpecTests : FreeSpec(
    {

        "create a NamespaceNodeSpec (single part)" {
            val node = NamespaceNodeSpec("test")
            node.name shouldBe arrayOf("test")
        }

        "create a NamespaceNodeSpec (multi part)" {
            val node = NamespaceNodeSpec("test", "test2")
            node.name shouldBe arrayOf("test", "test2")
        }

        "create a NamespaceNodeSpec (dot separated)" {
            val node = NamespaceNodeSpec("test.test2", "test3.test4")
            node.name shouldBe arrayOf("test", "test2", "test3", "test4")
        }

        "create NamespaceNodeSpec from NamespaceSpec" {
            val spec = NamespaceSpec("test", "test2")
            val node = NamespaceNodeSpec.of(spec)
            node.name shouldBe arrayOf("test", "test2")
        }

        "dump a NamespaceNodeSpec" {
            val node = NamespaceNodeSpec("test")
            val ctx = GenerationContext()
            val nctx = NodeContext()
            val n = node.dump(ctx, nctx)
            nctx.built.toString() shouldBe "test"
            n.name shouldBe "test"
            n.parent shouldBe null
            n.dotToken shouldBe null
            n.nameToken.value shouldBe "test"
            n.nameToken.start shouldBe 0
            n.nameToken.end shouldBe 3
            n.nameToken.type shouldBe ShakeTokenType.IDENTIFIER
        }

        "dump a NamespaceNodeSpec (multi part)" {
            val node = NamespaceNodeSpec("test", "test2")
            val ctx = GenerationContext()
            val nctx = NodeContext()
            val n = node.dump(ctx, nctx)
            nctx.built.toString() shouldBe "test.test2"
            n.name shouldBe "test2"
            n.parent!!.name shouldBe "test"
            n.parent!!.dotToken shouldBe null
            n.parent!!.nameToken.value shouldBe "test"
            n.parent!!.nameToken.start shouldBe 0
            n.parent!!.nameToken.end shouldBe 3
            n.parent!!.nameToken.type shouldBe ShakeTokenType.IDENTIFIER
            n.nameToken.value shouldBe "test2"
            n.nameToken.start shouldBe 5
            n.nameToken.end shouldBe 9
            n.nameToken.type shouldBe ShakeTokenType.IDENTIFIER
            n.dotToken shouldNotBe null
            n.dotToken!!.value shouldBe "."
            n.dotToken!!.start shouldBe 4
            n.dotToken!!.end shouldBe 4
            n.dotToken!!.type shouldBe ShakeTokenType.DOT
        }
    },
)

package com.shakelang.shake.shakespeare.nodes

import com.shakelang.shake.shakespeare.nodes.spec.NodeContext
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class NodeContextTests : FreeSpec({

    "output stream should pipe into string builder" {
        val context = NodeContext()
        context.stream.write("Hello, World!".encodeToByteArray())
        context.built.toString() shouldBe "Hello, World!"
    }

    "character source should source from string builder" {
        val context = NodeContext()
        context.built.append("Hello, World!")
        context.characterSource.all shouldBe "Hello, World!".toCharArray()
        context.characterSource.length shouldBe 13
        context.characterSource[0, 5] shouldBe "Hello".toCharArray()
    }

    "position maker should track position" {
        val context = NodeContext()
        context.print("Hello, World!")
        context.positionMaker.line shouldBe 1
        context.positionMaker.column shouldBe 13
        context.positionMaker.index shouldBe 12
        context.print("\n")
        context.positionMaker.line shouldBe 2
        context.positionMaker.column shouldBe 1
        context.positionMaker.index shouldBe 13
        context.print("Hello, World!")
        context.positionMaker.line shouldBe 2
        context.positionMaker.column shouldBe 14
        context.positionMaker.index shouldBe 26
    }
})

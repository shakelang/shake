package com.shakelang.shake.shakespeare.spec

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class IdentifierTests : FreeSpec({
    "should be constructed correctly" {
        val identifier = NamespaceSpec("test")
        identifier.name shouldBe arrayOf("test")
    }

    "should be converted to string correctly" {
        val identifier = NamespaceSpec("test")
        identifier.toString() shouldBe "test"
    }
})

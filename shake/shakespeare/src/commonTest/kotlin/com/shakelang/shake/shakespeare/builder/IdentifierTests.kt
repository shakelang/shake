package com.shakelang.shake.shakespeare.builder

import com.shakelang.shake.shakespeare.spec.Identifier
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class IdentifierTests : FreeSpec({
    "should be constructed correctly" {
        val identifier = Identifier("test")
        identifier.name shouldBe "test"
    }

    "should be converted to string correctly" {
        val identifier = Identifier("test")
        identifier.toString() shouldBe "test"
    }
})

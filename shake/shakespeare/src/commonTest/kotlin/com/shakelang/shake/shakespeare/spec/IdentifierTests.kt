package com.shakelang.shake.shakespeare.spec

import com.shakelang.util.testlib.FlatTestSpec
import io.kotest.matchers.shouldBe

class IdentifierTests : FlatTestSpec({
    it("should be constructed correctly") {
        val identifier = NamespaceSpec("test")
        identifier.name shouldBe arrayOf("test")
    }

    it("should be converted to string correctly") {
        val identifier = NamespaceSpec("test")
        identifier.toString() shouldBe "test"
    }
})

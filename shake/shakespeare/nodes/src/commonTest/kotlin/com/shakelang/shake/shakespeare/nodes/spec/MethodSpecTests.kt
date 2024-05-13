package com.shakelang.shake.shakespeare.nodes.spec

import com.shakelang.shake.shakespeare.spec.MethodSpec
import com.shakelang.shake.shakespeare.spec.ObjectTypeSpec
import com.shakelang.shake.shakespeare.spec.PrimitiveTypeSpec
import com.shakelang.util.testlib.FlatTestSpec
import io.kotest.matchers.shouldBe

class MethodSpecTests : FlatTestSpec({

    describe("of") {

        it("create MethodNodeSpec from MethodSpec") {
            val method = MethodSpec.builder()
                .name("test")
                .returnType("String")
                .build()

            val node = MethodNodeSpec.of(method)

            node.name shouldBe "test"
            node.returnType shouldBe TypeNodeSpec.of(ObjectTypeSpec("String"))
        }

        it("create MethodNodeSpec from MethodSpec with parameters") {
            val method = MethodSpec.builder()
                .name("test")
                .returnType("String")
                .parameter("param1", PrimitiveTypeSpec.INT)
                .parameter("param2", ObjectTypeSpec("String"))
                .build()

            val node = MethodNodeSpec.of(method)

            node.name shouldBe "test"
            node.returnType shouldBe TypeNodeSpec.of(ObjectTypeSpec("String"))
            node.parameters.size shouldBe 2
            node.parameters[0].name shouldBe "param1"
            node.parameters[0].type shouldBe TypeNodeSpec.of(PrimitiveTypeSpec.INT)
            node.parameters[1].name shouldBe "param2"
            node.parameters[1].type shouldBe TypeNodeSpec.of(ObjectTypeSpec("String"))
        }
    }
})

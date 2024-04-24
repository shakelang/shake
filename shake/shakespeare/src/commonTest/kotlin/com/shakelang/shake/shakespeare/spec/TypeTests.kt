package com.shakelang.shake.shakespeare.spec

import com.shakelang.util.testlib.FlatTestSpec
import io.kotest.matchers.shouldBe

class TypeTests : FlatTestSpec({

    describe("Companion.of") {
        it("should return BYTE for 'byte'") {
            TypeSpec.of("byte") shouldBe PrimitiveTypeSpec.BYTE
        }

        it("should return SHORT for 'shorts'") {
            TypeSpec.of("shorts") shouldBe PrimitiveTypeSpec.SHORT
        }

        it("should return INT for 'int'") {
            TypeSpec.of("int") shouldBe PrimitiveTypeSpec.INT
        }

        it("should return LONG for 'long'") {
            TypeSpec.of("long") shouldBe PrimitiveTypeSpec.LONG
        }

        it("should return UNSIGNED_BYTE for 'ubyte'") {
            TypeSpec.of("ubyte") shouldBe PrimitiveTypeSpec.UNSIGNED_BYTE
        }

        it("should return UNSIGNED_SHORT for 'ushort'") {
            TypeSpec.of("ushort") shouldBe PrimitiveTypeSpec.UNSIGNED_SHORT
        }

        it("should return UNSIGNED_INT for 'uint'") {
            TypeSpec.of("uint") shouldBe PrimitiveTypeSpec.UNSIGNED_INT
        }

        it("should return UNSIGNED_LONG for 'ulong'") {
            TypeSpec.of("ulong") shouldBe PrimitiveTypeSpec.UNSIGNED_LONG
        }

        it("should return FLOAT for 'float'") {
            TypeSpec.of("float") shouldBe PrimitiveTypeSpec.FLOAT
        }

        it("should return DOUBLE for 'double'") {
            TypeSpec.of("double") shouldBe PrimitiveTypeSpec.DOUBLE
        }

        it("should return CHAR for 'char'") {
            TypeSpec.of("char") shouldBe PrimitiveTypeSpec.CHAR
        }

        it("should return BOOLEAN for 'boolean'") {
            TypeSpec.of("boolean") shouldBe PrimitiveTypeSpec.BOOLEAN
        }

        it("should return SimpleType for non-primitive 'String'") {
            TypeSpec.of("String") shouldBe ObjectTypeSpec("String")
        }

        it("should return SimpleType for non-primitive 'CustomType'") {
            TypeSpec.of("CustomType") shouldBe ObjectTypeSpec("CustomType")
        }
    }

    describe("SimpleType.generate") {

        it("should return type name for 'TestType'") {
            val simpleType = ObjectTypeSpec("TestType")
            simpleType.generate(GenerationContext()) shouldBe "TestType"
        }
    }

    describe("PrimitiveType") {
        it("BYTE.generate should return 'byte'") {
            PrimitiveTypeSpec.BYTE.generate(GenerationContext()) shouldBe "byte"
        }

        it("SHORT.generate should return 'short'") {
            PrimitiveTypeSpec.SHORT.generate(GenerationContext()) shouldBe "short"
        }

        it("INT.generate should return 'int'") {
            PrimitiveTypeSpec.INT.generate(GenerationContext()) shouldBe "int"
        }

        it("LONG.generate should return 'long'") {
            PrimitiveTypeSpec.LONG.generate(GenerationContext()) shouldBe "long"
        }

        it("UNSIGNED_BYTE.generate should return 'ubyte'") {
            PrimitiveTypeSpec.UNSIGNED_BYTE.generate(GenerationContext()) shouldBe "ubyte"
        }

        it("UNSIGNED_SHORT.generate should return 'ushort'") {
            PrimitiveTypeSpec.UNSIGNED_SHORT.generate(GenerationContext()) shouldBe "ushort"
        }

        it("UNSIGNED_INT.generate should return 'uint'") {
            PrimitiveTypeSpec.UNSIGNED_INT.generate(GenerationContext()) shouldBe "uint"
        }

        it("UNSIGNED_LONG.generate should return 'ulong'") {
            PrimitiveTypeSpec.UNSIGNED_LONG.generate(GenerationContext()) shouldBe "ulong"
        }

        it("FLOAT.generate should return 'float'") {
            PrimitiveTypeSpec.FLOAT.generate(GenerationContext()) shouldBe "float"
        }

        it("DOUBLE.generate should return 'double'") {
            PrimitiveTypeSpec.DOUBLE.generate(GenerationContext()) shouldBe "double"
        }

        it("CHAR.generate should return 'char'") {
            PrimitiveTypeSpec.CHAR.generate(GenerationContext()) shouldBe "char"
        }

        it("BOOLEAN.generate should return 'boolean'") {
            PrimitiveTypeSpec.BOOLEAN.generate(GenerationContext()) shouldBe "boolean"
        }
    }
})

package com.shakelang.shake.shakespeare.spec

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class TypeTests : FreeSpec({

    "Type.Companion.of should return PrimitiveType.BYTE for 'byte'" {
        TypeSpec.of("byte") shouldBe PrimitiveType.BYTE
    }

    "Type.Companion.of should return PrimitiveType.SHORT for 'shorts'" {
        TypeSpec.of("shorts") shouldBe PrimitiveType.SHORT
    }

    "Type.Companion.of should return PrimitiveType.INT for 'int'" {
        TypeSpec.of("int") shouldBe PrimitiveType.INT
    }

    "Type.Companion.of should return PrimitiveType.LONG for 'long'" {
        TypeSpec.of("long") shouldBe PrimitiveType.LONG
    }

    "Type.Companion.of should return PrimitiveType.UNSIGNED_BYTE for 'unsigned byte'" {
        TypeSpec.of("unsigned byte") shouldBe PrimitiveType.UNSIGNED_BYTE
    }

    "Type.Companion.of should return PrimitiveType.UNSIGNED_SHORT for 'unsigned shorts'" {
        TypeSpec.of("unsigned shorts") shouldBe PrimitiveType.UNSIGNED_SHORT
    }

    "Type.Companion.of should return PrimitiveType.UNSIGNED_INT for 'unsigned int'" {
        TypeSpec.of("unsigned int") shouldBe PrimitiveType.UNSIGNED_INT
    }

    "Type.Companion.of should return PrimitiveType.UNSIGNED_LONG for 'unsigned long'" {
        TypeSpec.of("unsigned long") shouldBe PrimitiveType.UNSIGNED_LONG
    }

    "Type.Companion.of should return PrimitiveType.FLOAT for 'float'" {
        TypeSpec.of("float") shouldBe PrimitiveType.FLOAT
    }

    "Type.Companion.of should return PrimitiveType.DOUBLE for 'doubles'" {
        TypeSpec.of("doubles") shouldBe PrimitiveType.DOUBLE
    }

    "Type.Companion.of should return PrimitiveType.CHAR for 'char'" {
        TypeSpec.of("char") shouldBe PrimitiveType.CHAR
    }

    "Type.Companion.of should return PrimitiveType.BOOLEAN for 'boolean'" {
        TypeSpec.of("boolean") shouldBe PrimitiveType.BOOLEAN
    }

    "Type.Companion.of should return SimpleType for non-primitive 'String'" {
        TypeSpec.of("String") shouldBe ObjectType("String")
    }

    "Type.Companion.of should return SimpleType for non-primitive 'CustomType'" {
        TypeSpec.of("CustomType") shouldBe ObjectType("CustomType")
    }

    "SimpleType.generate should return type name for 'TestType'" {
        val simpleType = ObjectType("TestType")
        simpleType.generate(GenerationContext()) shouldBe "TestType"
    }

    "PrimitiveType.BYTE.generate should return 'byte'" {
        PrimitiveType.BYTE.generate(GenerationContext()) shouldBe "byte"
    }

    "PrimitiveType.SHORT.generate should return 'shorts'" {
        PrimitiveType.SHORT.generate(GenerationContext()) shouldBe "shorts"
    }

    "PrimitiveType.INT.generate should return 'int'" {
        PrimitiveType.INT.generate(GenerationContext()) shouldBe "int"
    }

    "PrimitiveType.LONG.generate should return 'long'" {
        PrimitiveType.LONG.generate(GenerationContext()) shouldBe "long"
    }

    "PrimitiveType.UNSIGNED_BYTE.generate should return 'unsigned byte'" {
        PrimitiveType.UNSIGNED_BYTE.generate(GenerationContext()) shouldBe "unsigned byte"
    }

    "PrimitiveType.UNSIGNED_SHORT.generate should return 'unsigned shorts'" {
        PrimitiveType.UNSIGNED_SHORT.generate(GenerationContext()) shouldBe "unsigned shorts"
    }

    "PrimitiveType.UNSIGNED_INT.generate should return 'unsigned int'" {
        PrimitiveType.UNSIGNED_INT.generate(GenerationContext()) shouldBe "unsigned int"
    }

    "PrimitiveType.UNSIGNED_LONG.generate should return 'unsigned long'" {
        PrimitiveType.UNSIGNED_LONG.generate(GenerationContext()) shouldBe "unsigned long"
    }

    "PrimitiveType.FLOAT.generate should return 'float'" {
        PrimitiveType.FLOAT.generate(GenerationContext()) shouldBe "float"
    }

    "PrimitiveType.DOUBLE.generate should return 'doubles'" {
        PrimitiveType.DOUBLE.generate(GenerationContext()) shouldBe "doubles"
    }

    "PrimitiveType.CHAR.generate should return 'char'" {
        PrimitiveType.CHAR.generate(GenerationContext()) shouldBe "char"
    }

    "PrimitiveType.BOOLEAN.generate should return 'boolean'" {
        PrimitiveType.BOOLEAN.generate(GenerationContext()) shouldBe "boolean"
    }
})

package com.shakelang.shake.shakespeare.spec

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class TypeTests : FreeSpec({

    "Type.Companion.of should return PrimitiveType.BYTE for 'byte'" {
        TypeSpec.of("byte") shouldBe PrimitiveTypeSpec.BYTE
    }

    "Type.Companion.of should return PrimitiveType.SHORT for 'shorts'" {
        TypeSpec.of("shorts") shouldBe PrimitiveTypeSpec.SHORT
    }

    "Type.Companion.of should return PrimitiveType.INT for 'int'" {
        TypeSpec.of("int") shouldBe PrimitiveTypeSpec.INT
    }

    "Type.Companion.of should return PrimitiveType.LONG for 'long'" {
        TypeSpec.of("long") shouldBe PrimitiveTypeSpec.LONG
    }

    "Type.Companion.of should return PrimitiveType.UNSIGNED_BYTE for 'ubyte'" {
        TypeSpec.of("ubyte") shouldBe PrimitiveTypeSpec.UNSIGNED_BYTE
    }

    "Type.Companion.of should return PrimitiveType.UNSIGNED_SHORT for 'ushorts'" {
        TypeSpec.of("ushorts") shouldBe PrimitiveTypeSpec.UNSIGNED_SHORT
    }

    "Type.Companion.of should return PrimitiveType.UNSIGNED_INT for 'uint'" {
        TypeSpec.of("uint") shouldBe PrimitiveTypeSpec.UNSIGNED_INT
    }

    "Type.Companion.of should return PrimitiveType.UNSIGNED_LONG for 'ulong'" {
        TypeSpec.of("ulong") shouldBe PrimitiveTypeSpec.UNSIGNED_LONG
    }

    "Type.Companion.of should return PrimitiveType.FLOAT for 'float'" {
        TypeSpec.of("float") shouldBe PrimitiveTypeSpec.FLOAT
    }

    "Type.Companion.of should return PrimitiveType.DOUBLE for 'doubles'" {
        TypeSpec.of("doubles") shouldBe PrimitiveTypeSpec.DOUBLE
    }

    "Type.Companion.of should return PrimitiveType.CHAR for 'char'" {
        TypeSpec.of("char") shouldBe PrimitiveTypeSpec.CHAR
    }

    "Type.Companion.of should return PrimitiveType.BOOLEAN for 'boolean'" {
        TypeSpec.of("boolean") shouldBe PrimitiveTypeSpec.BOOLEAN
    }

    "Type.Companion.of should return SimpleType for non-primitive 'String'" {
        TypeSpec.of("String") shouldBe ObjectTypeSpec("String")
    }

    "Type.Companion.of should return SimpleType for non-primitive 'CustomType'" {
        TypeSpec.of("CustomType") shouldBe ObjectTypeSpec("CustomType")
    }

    "SimpleType.generate should return type name for 'TestType'" {
        val simpleType = ObjectTypeSpec("TestType")
        simpleType.generate(GenerationContext()) shouldBe "TestType"
    }

    "PrimitiveType.BYTE.generate should return 'byte'" {
        PrimitiveTypeSpec.BYTE.generate(GenerationContext()) shouldBe "byte"
    }

    "PrimitiveType.SHORT.generate should return 'shorts'" {
        PrimitiveTypeSpec.SHORT.generate(GenerationContext()) shouldBe "shorts"
    }

    "PrimitiveType.INT.generate should return 'int'" {
        PrimitiveTypeSpec.INT.generate(GenerationContext()) shouldBe "int"
    }

    "PrimitiveType.LONG.generate should return 'long'" {
        PrimitiveTypeSpec.LONG.generate(GenerationContext()) shouldBe "long"
    }

    "PrimitiveType.UNSIGNED_BYTE.generate should return 'unsigned byte'" {
        PrimitiveTypeSpec.UNSIGNED_BYTE.generate(GenerationContext()) shouldBe "unsigned byte"
    }

    "PrimitiveType.UNSIGNED_SHORT.generate should return 'unsigned shorts'" {
        PrimitiveTypeSpec.UNSIGNED_SHORT.generate(GenerationContext()) shouldBe "unsigned shorts"
    }

    "PrimitiveType.UNSIGNED_INT.generate should return 'unsigned int'" {
        PrimitiveTypeSpec.UNSIGNED_INT.generate(GenerationContext()) shouldBe "unsigned int"
    }

    "PrimitiveType.UNSIGNED_LONG.generate should return 'unsigned long'" {
        PrimitiveTypeSpec.UNSIGNED_LONG.generate(GenerationContext()) shouldBe "unsigned long"
    }

    "PrimitiveType.FLOAT.generate should return 'float'" {
        PrimitiveTypeSpec.FLOAT.generate(GenerationContext()) shouldBe "float"
    }

    "PrimitiveType.DOUBLE.generate should return 'doubles'" {
        PrimitiveTypeSpec.DOUBLE.generate(GenerationContext()) shouldBe "doubles"
    }

    "PrimitiveType.CHAR.generate should return 'char'" {
        PrimitiveTypeSpec.CHAR.generate(GenerationContext()) shouldBe "char"
    }

    "PrimitiveType.BOOLEAN.generate should return 'boolean'" {
        PrimitiveTypeSpec.BOOLEAN.generate(GenerationContext()) shouldBe "boolean"
    }
})

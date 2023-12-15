package com.shakelang.shake.bytecode.interpreter.format.descriptor

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class TypeDescriptorTests : FreeSpec({

    "ByteType#descriptor should return \"B\"" {
        TypeDescriptor.ByteType.INSTANCE.descriptor shouldBe "B"
    }

    "ByteType#byteSize should return 1" {
        TypeDescriptor.ByteType.INSTANCE.byteSize shouldBe 1
    }

    "ShortType#descriptor should return \"S\"" {
        TypeDescriptor.ShortType.INSTANCE.descriptor shouldBe "S"
    }

    "ShortType#byteSize should return 2" {
        TypeDescriptor.ShortType.INSTANCE.byteSize shouldBe 2
    }

    "IntType#descriptor should return \"I\"" {
        TypeDescriptor.IntType.INSTANCE.descriptor shouldBe "I"
    }

    "IntType#byteSize should return 4" {
        TypeDescriptor.IntType.INSTANCE.byteSize shouldBe 4
    }

    "LongType#descriptor should return \"J\"" {
        TypeDescriptor.LongType.INSTANCE.descriptor shouldBe "J"
    }

    "LongType#byteSize should return 8" {
        TypeDescriptor.LongType.INSTANCE.byteSize shouldBe 8
    }

    "UnsignedByteType#descriptor should return \"b\"" {
        TypeDescriptor.UnsignedByteType.INSTANCE.descriptor shouldBe "b"
    }

    "UnsignedByteType#byteSize should return 1" {
        TypeDescriptor.UnsignedByteType.INSTANCE.byteSize shouldBe 1
    }

    "UnsignedShortType#descriptor should return \"s\"" {
        TypeDescriptor.UnsignedShortType.INSTANCE.descriptor shouldBe "s"
    }

    "UnsignedShortType#byteSize should return 2" {
        TypeDescriptor.UnsignedShortType.INSTANCE.byteSize shouldBe 2
    }

    "UnsignedIntType#descriptor should return \"i\"" {
        TypeDescriptor.UnsignedIntType.INSTANCE.descriptor shouldBe "i"
    }

    "UnsignedIntType#byteSize should return 4" {
        TypeDescriptor.UnsignedIntType.INSTANCE.byteSize shouldBe 4
    }

    "UnsignedLongType#descriptor should return \"l\"" {
        TypeDescriptor.UnsignedLongType.INSTANCE.descriptor shouldBe "l"
    }

    "UnsignedLongType#byteSize should return 8" {
        TypeDescriptor.UnsignedLongType.INSTANCE.byteSize shouldBe 8
    }

    "FloatType#descriptor should return \"F\"" {
        TypeDescriptor.FloatType.INSTANCE.descriptor shouldBe "F"
    }

    "FloatType#byteSize should return 4" {
        TypeDescriptor.FloatType.INSTANCE.byteSize shouldBe 4
    }

    "DoubleType#descriptor should return \"D\"" {
        TypeDescriptor.DoubleType.INSTANCE.descriptor shouldBe "D"
    }

    "DoubleType#byteSize should return 8" {
        TypeDescriptor.DoubleType.INSTANCE.byteSize shouldBe 8
    }

    "VoidType#descriptor should return \"V\"" {
        TypeDescriptor.VoidType.INSTANCE.descriptor shouldBe "V"
    }

    "VoidType#byteSize should return 0" {
        TypeDescriptor.VoidType.INSTANCE.byteSize shouldBe 0
    }

    "CharType#descriptor should return \"C\"" {
        TypeDescriptor.CharType.INSTANCE.descriptor shouldBe "C"
    }

    "CharType#byteSize should return 2" {
        TypeDescriptor.CharType.INSTANCE.byteSize shouldBe 2
    }

    "BooleanType#descriptor should return \"Z\"" {
        TypeDescriptor.BooleanType.INSTANCE.descriptor shouldBe "Z"
    }

    "BooleanType#byteSize should return 1" {
        TypeDescriptor.BooleanType.INSTANCE.byteSize shouldBe 1
    }

    "ObjectType#descriptor should return \"L\" + name + \";\"" {
        TypeDescriptor.ObjectType("test").descriptor shouldBe "Ltest;"
    }

    "ObjectType#descriptor with with one generic type" {
        TypeDescriptor.ObjectType("test", listOf(TypeDescriptor.ByteType.INSTANCE)).descriptor shouldBe "Ltest@B;"
    }

    "ObjectType#descriptor with with two generic types" {
        TypeDescriptor.ObjectType(
            "test",
            listOf(TypeDescriptor.ByteType.INSTANCE, TypeDescriptor.IntType.INSTANCE)
        ).descriptor shouldBe "Ltest@B+I;"
    }

    "ObjectType#byteSize should return 1" {
        TypeDescriptor.ObjectType("test").byteSize shouldBe 8
    }

    "ObjectType#equals should return true for same object" {
        (TypeDescriptor.ObjectType("test") == TypeDescriptor.ObjectType("test")) shouldBe true
    }

    "ObjectType#equals should return false for different object" {
        (TypeDescriptor.ObjectType("test") == TypeDescriptor.ObjectType("test2")) shouldBe false
    }

    "ObjectType#equals should return false for different object with same name" {
        (TypeDescriptor.ObjectType("test") == TypeDescriptor.ObjectType(
            "test",
            listOf(TypeDescriptor.ByteType.INSTANCE)
        )) shouldBe false
    }

    "ObjectType#equals should return true for different object with same name and same generic types" {
        (TypeDescriptor.ObjectType(
            "test",
            listOf(TypeDescriptor.ByteType.INSTANCE)
        ) == TypeDescriptor.ObjectType("test", listOf(TypeDescriptor.ByteType.INSTANCE))) shouldBe true
    }

    "ArrayType#descriptor should return \"[\" + type.descriptor" {
        TypeDescriptor.ArrayType(TypeDescriptor.ByteType.INSTANCE).descriptor shouldBe "[B"
    }

    "ArrayType#byteSize should return 8" {
        TypeDescriptor.ArrayType(TypeDescriptor.ByteType.INSTANCE).byteSize shouldBe 8
    }

    "Parsing of ByteType should return ByteType.INSTANCE" {
        TypeDescriptor.parse("B") shouldBe TypeDescriptor.ByteType.INSTANCE
    }

    "Parsing of ShortType should return ShortType.INSTANCE" {
        TypeDescriptor.parse("S") shouldBe TypeDescriptor.ShortType.INSTANCE
    }

    "Parsing of IntType should return IntType.INSTANCE" {
        TypeDescriptor.parse("I") shouldBe TypeDescriptor.IntType.INSTANCE
    }

    "Parsing of LongType should return LongType.INSTANCE" {
        TypeDescriptor.parse("J") shouldBe TypeDescriptor.LongType.INSTANCE
    }

    "Parsing of UnsignedByteType should return UnsignedByteType.INSTANCE" {
        TypeDescriptor.parse("b") shouldBe TypeDescriptor.UnsignedByteType.INSTANCE
    }

    "Parsing of UnsignedShortType should return UnsignedShortType.INSTANCE" {
        TypeDescriptor.parse("s") shouldBe TypeDescriptor.UnsignedShortType.INSTANCE
    }

    "Parsing of UnsignedIntType should return UnsignedIntType.INSTANCE" {
        TypeDescriptor.parse("i") shouldBe TypeDescriptor.UnsignedIntType.INSTANCE
    }

    "Parsing of UnsignedLongType should return UnsignedLongType.INSTANCE" {
        TypeDescriptor.parse("l") shouldBe TypeDescriptor.UnsignedLongType.INSTANCE
    }

    "Parsing of FloatType should return FloatType.INSTANCE" {
        TypeDescriptor.parse("F") shouldBe TypeDescriptor.FloatType.INSTANCE
    }

    "Parsing of DoubleType should return DoubleType.INSTANCE" {
        TypeDescriptor.parse("D") shouldBe TypeDescriptor.DoubleType.INSTANCE
    }

    "Parsing of VoidType should return VoidType.INSTANCE" {
        TypeDescriptor.parse("V") shouldBe TypeDescriptor.VoidType.INSTANCE
    }

    "Parsing of CharType should return CharType.INSTANCE" {
        TypeDescriptor.parse("C") shouldBe TypeDescriptor.CharType.INSTANCE
    }

    "Parsing of BooleanType should return BooleanType.INSTANCE" {
        TypeDescriptor.parse("Z") shouldBe TypeDescriptor.BooleanType.INSTANCE
    }

    "Parsing of ObjectType should return ObjectType" {
        TypeDescriptor.parse("Ltest;") shouldBe TypeDescriptor.ObjectType("test")
    }

    "Parsing of ObjectType with one generic type should return ObjectType" {
        TypeDescriptor.parse("Ltest@B;") shouldBe TypeDescriptor.ObjectType(
            "test",
            listOf(TypeDescriptor.ByteType.INSTANCE)
        )
    }

    "Parsing of ObjectType with two generic types should return ObjectType" {
        TypeDescriptor.parse("Ltest@B+I;") shouldBe TypeDescriptor.ObjectType(
            "test",
            listOf(TypeDescriptor.ByteType.INSTANCE, TypeDescriptor.IntType.INSTANCE)
        )
    }

    "Parsing of ArrayType should return ArrayType" {
        TypeDescriptor.parse("[B") shouldBe TypeDescriptor.ArrayType(TypeDescriptor.ByteType.INSTANCE)
    }

    "Parsing of ArrayType with ObjectType should return ArrayType" {
        TypeDescriptor.parse("[Ltest;") shouldBe TypeDescriptor.ArrayType(TypeDescriptor.ObjectType("test"))
    }

    "Parsing of ArrayType with ObjectType with one generic type should return ArrayType" {
        TypeDescriptor.parse("[Ltest@B;") shouldBe TypeDescriptor.ArrayType(
            TypeDescriptor.ObjectType(
                "test",
                listOf(TypeDescriptor.ByteType.INSTANCE)
            )
        )
    }

    "Parsing of ArrayType with ObjectType with two generic types should return ArrayType" {
        TypeDescriptor.parse("[Ltest@B+I;") shouldBe TypeDescriptor.ArrayType(
            TypeDescriptor.ObjectType(
                "test",
                listOf(TypeDescriptor.ByteType.INSTANCE, TypeDescriptor.IntType.INSTANCE)
            )
        )
    }

    "Parsing of ArrayType with ArrayType should return ArrayType" {
        TypeDescriptor.parse("[[B") shouldBe TypeDescriptor.ArrayType(TypeDescriptor.ArrayType(TypeDescriptor.ByteType.INSTANCE))
    }

    "Parsing of ArrayType with ArrayType with ObjectType should return ArrayType" {
        TypeDescriptor.parse("[[Ltest;") shouldBe TypeDescriptor.ArrayType(
            TypeDescriptor.ArrayType(
                TypeDescriptor.ObjectType(
                    "test"
                )
            )
        )
    }

    "Parsing of ObjectType with generic object type" {
        TypeDescriptor.parse("Ltest@Ltest2;;") shouldBe TypeDescriptor.ObjectType(
            "test",
            listOf(TypeDescriptor.ObjectType("test2"))
        )
    }

})
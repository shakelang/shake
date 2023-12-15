package com.shakelang.shake.bytecode.interpreter.format

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class TypeTests : FreeSpec({

    "ByteType#descriptor should return \"B\"" {
        Type.ByteType.INSTANCE.descriptor shouldBe "B"
    }

    "ByteType#byteSize should return 1" {
        Type.ByteType.INSTANCE.byteSize shouldBe 1
    }

    "ShortType#descriptor should return \"S\"" {
        Type.ShortType.INSTANCE.descriptor shouldBe "S"
    }

    "ShortType#byteSize should return 2" {
        Type.ShortType.INSTANCE.byteSize shouldBe 2
    }

    "IntType#descriptor should return \"I\"" {
        Type.IntType.INSTANCE.descriptor shouldBe "I"
    }

    "IntType#byteSize should return 4" {
        Type.IntType.INSTANCE.byteSize shouldBe 4
    }

    "LongType#descriptor should return \"J\"" {
        Type.LongType.INSTANCE.descriptor shouldBe "J"
    }

    "LongType#byteSize should return 8" {
        Type.LongType.INSTANCE.byteSize shouldBe 8
    }

    "UnsignedByteType#descriptor should return \"b\"" {
        Type.UnsignedByteType.INSTANCE.descriptor shouldBe "b"
    }

    "UnsignedByteType#byteSize should return 1" {
        Type.UnsignedByteType.INSTANCE.byteSize shouldBe 1
    }

    "UnsignedShortType#descriptor should return \"s\"" {
        Type.UnsignedShortType.INSTANCE.descriptor shouldBe "s"
    }

    "UnsignedShortType#byteSize should return 2" {
        Type.UnsignedShortType.INSTANCE.byteSize shouldBe 2
    }

    "UnsignedIntType#descriptor should return \"i\"" {
        Type.UnsignedIntType.INSTANCE.descriptor shouldBe "i"
    }

    "UnsignedIntType#byteSize should return 4" {
        Type.UnsignedIntType.INSTANCE.byteSize shouldBe 4
    }

    "UnsignedLongType#descriptor should return \"l\"" {
        Type.UnsignedLongType.INSTANCE.descriptor shouldBe "l"
    }

    "UnsignedLongType#byteSize should return 8" {
        Type.UnsignedLongType.INSTANCE.byteSize shouldBe 8
    }

    "FloatType#descriptor should return \"F\"" {
        Type.FloatType.INSTANCE.descriptor shouldBe "F"
    }

    "FloatType#byteSize should return 4" {
        Type.FloatType.INSTANCE.byteSize shouldBe 4
    }

    "DoubleType#descriptor should return \"D\"" {
        Type.DoubleType.INSTANCE.descriptor shouldBe "D"
    }

    "DoubleType#byteSize should return 8" {
        Type.DoubleType.INSTANCE.byteSize shouldBe 8
    }

    "VoidType#descriptor should return \"V\"" {
        Type.VoidType.INSTANCE.descriptor shouldBe "V"
    }

    "VoidType#byteSize should return 0" {
        Type.VoidType.INSTANCE.byteSize shouldBe 0
    }

    "CharType#descriptor should return \"C\"" {
        Type.CharType.INSTANCE.descriptor shouldBe "C"
    }

    "CharType#byteSize should return 2" {
        Type.CharType.INSTANCE.byteSize shouldBe 2
    }

    "BooleanType#descriptor should return \"Z\"" {
        Type.BooleanType.INSTANCE.descriptor shouldBe "Z"
    }

    "BooleanType#byteSize should return 1" {
        Type.BooleanType.INSTANCE.byteSize shouldBe 1
    }

    "ObjectType#descriptor should return \"L\" + name + \";\"" {
        Type.ObjectType("test").descriptor shouldBe "Ltest;"
    }

    "ObjectType#descriptor with with one generic type" {
        Type.ObjectType("test", listOf(Type.ByteType.INSTANCE)).descriptor shouldBe "Ltest@B;"
    }

    "ObjectType#descriptor with with two generic types" {
        Type.ObjectType("test", listOf(Type.ByteType.INSTANCE, Type.IntType.INSTANCE)).descriptor shouldBe "Ltest@B+I;"
    }

    "ObjectType#byteSize should return 1" {
        Type.ObjectType("test").byteSize shouldBe 8
    }

    "ObjectType#equals should return true for same object" {
        (Type.ObjectType("test") == Type.ObjectType("test")) shouldBe true
    }

    "ObjectType#equals should return false for different object" {
        (Type.ObjectType("test") == Type.ObjectType("test2")) shouldBe false
    }

    "ObjectType#equals should return false for different object with same name" {
        (Type.ObjectType("test") == Type.ObjectType("test", listOf(Type.ByteType.INSTANCE))) shouldBe false
    }

    "ObjectType#equals should return true for different object with same name and same generic types" {
        (Type.ObjectType("test", listOf(Type.ByteType.INSTANCE)) == Type.ObjectType("test", listOf(Type.ByteType.INSTANCE))) shouldBe true
    }

    "ArrayType#descriptor should return \"[\" + type.descriptor" {
        Type.ArrayType(Type.ByteType.INSTANCE).descriptor shouldBe "[B"
    }

    "ArrayType#byteSize should return 8" {
        Type.ArrayType(Type.ByteType.INSTANCE).byteSize shouldBe 8
    }

    "Parsing of ByteType should return ByteType.INSTANCE" {
        Type.parse("B") shouldBe Type.ByteType.INSTANCE
    }

    "Parsing of ShortType should return ShortType.INSTANCE" {
        Type.parse("S") shouldBe Type.ShortType.INSTANCE
    }

    "Parsing of IntType should return IntType.INSTANCE" {
        Type.parse("I") shouldBe Type.IntType.INSTANCE
    }

    "Parsing of LongType should return LongType.INSTANCE" {
        Type.parse("J") shouldBe Type.LongType.INSTANCE
    }

    "Parsing of UnsignedByteType should return UnsignedByteType.INSTANCE" {
        Type.parse("b") shouldBe Type.UnsignedByteType.INSTANCE
    }

    "Parsing of UnsignedShortType should return UnsignedShortType.INSTANCE" {
        Type.parse("s") shouldBe Type.UnsignedShortType.INSTANCE
    }

    "Parsing of UnsignedIntType should return UnsignedIntType.INSTANCE" {
        Type.parse("i") shouldBe Type.UnsignedIntType.INSTANCE
    }

    "Parsing of UnsignedLongType should return UnsignedLongType.INSTANCE" {
        Type.parse("l") shouldBe Type.UnsignedLongType.INSTANCE
    }

    "Parsing of FloatType should return FloatType.INSTANCE" {
        Type.parse("F") shouldBe Type.FloatType.INSTANCE
    }

    "Parsing of DoubleType should return DoubleType.INSTANCE" {
        Type.parse("D") shouldBe Type.DoubleType.INSTANCE
    }

    "Parsing of VoidType should return VoidType.INSTANCE" {
        Type.parse("V") shouldBe Type.VoidType.INSTANCE
    }

    "Parsing of CharType should return CharType.INSTANCE" {
        Type.parse("C") shouldBe Type.CharType.INSTANCE
    }

    "Parsing of BooleanType should return BooleanType.INSTANCE" {
        Type.parse("Z") shouldBe Type.BooleanType.INSTANCE
    }

    "Parsing of ObjectType should return ObjectType" {
        Type.parse("Ltest;") shouldBe Type.ObjectType("test")
    }

    "Parsing of ObjectType with one generic type should return ObjectType" {
        Type.parse("Ltest@B;") shouldBe Type.ObjectType("test", listOf(Type.ByteType.INSTANCE))
    }

    "Parsing of ObjectType with two generic types should return ObjectType" {
        Type.parse("Ltest@B+I;") shouldBe Type.ObjectType("test", listOf(Type.ByteType.INSTANCE, Type.IntType.INSTANCE))
    }

    "Parsing of ArrayType should return ArrayType" {
        Type.parse("[B") shouldBe Type.ArrayType(Type.ByteType.INSTANCE)
    }

    "Parsing of ArrayType with ObjectType should return ArrayType" {
        Type.parse("[Ltest;") shouldBe Type.ArrayType(Type.ObjectType("test"))
    }

    "Parsing of ArrayType with ObjectType with one generic type should return ArrayType" {
        Type.parse("[Ltest@B;") shouldBe Type.ArrayType(Type.ObjectType("test", listOf(Type.ByteType.INSTANCE)))
    }

    "Parsing of ArrayType with ObjectType with two generic types should return ArrayType" {
        Type.parse("[Ltest@B+I;") shouldBe Type.ArrayType(Type.ObjectType("test", listOf(Type.ByteType.INSTANCE, Type.IntType.INSTANCE)))
    }

    "Parsing of ArrayType with ArrayType should return ArrayType" {
        Type.parse("[[B") shouldBe Type.ArrayType(Type.ArrayType(Type.ByteType.INSTANCE))
    }

    "Parsing of ArrayType with ArrayType with ObjectType should return ArrayType" {
        Type.parse("[[Ltest;") shouldBe Type.ArrayType(Type.ArrayType(Type.ObjectType("test")))
    }

    "Parsing of ObjectType with generic object type" {
        Type.parse("Ltest@Ltest2;;") shouldBe Type.ObjectType("test", listOf(Type.ObjectType("test2")))
    }
})

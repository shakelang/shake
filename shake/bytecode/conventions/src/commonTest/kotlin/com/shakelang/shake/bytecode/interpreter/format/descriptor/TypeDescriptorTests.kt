package com.shakelang.shake.bytecode.interpreter.format.descriptor

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class TypeDescriptorTests : FreeSpec(
    {

        "ByteType#descriptor should return \"B\"" {
            TypeDescriptor.ByteType.descriptor shouldBe "B"
        }

        "ByteType#byteSize should return 1" {
            TypeDescriptor.ByteType.byteSize shouldBe 1
        }

        "ShortType#descriptor should return \"S\"" {
            TypeDescriptor.ShortType.descriptor shouldBe "S"
        }

        "ShortType#byteSize should return 2" {
            TypeDescriptor.ShortType.byteSize shouldBe 2
        }

        "IntType#descriptor should return \"I\"" {
            TypeDescriptor.IntType.descriptor shouldBe "I"
        }

        "IntType#byteSize should return 4" {
            TypeDescriptor.IntType.byteSize shouldBe 4
        }

        "LongType#descriptor should return \"J\"" {
            TypeDescriptor.LongType.descriptor shouldBe "J"
        }

        "LongType#byteSize should return 8" {
            TypeDescriptor.LongType.byteSize shouldBe 8
        }

        "UnsignedByteType#descriptor should return \"b\"" {
            TypeDescriptor.UnsignedByteType.descriptor shouldBe "b"
        }

        "UnsignedByteType#byteSize should return 1" {
            TypeDescriptor.UnsignedByteType.byteSize shouldBe 1
        }

        "UnsignedShortType#descriptor should return \"s\"" {
            TypeDescriptor.UnsignedShortType.descriptor shouldBe "s"
        }

        "UnsignedShortType#byteSize should return 2" {
            TypeDescriptor.UnsignedShortType.byteSize shouldBe 2
        }

        "UnsignedIntType#descriptor should return \"i\"" {
            TypeDescriptor.UnsignedIntType.descriptor shouldBe "i"
        }

        "UnsignedIntType#byteSize should return 4" {
            TypeDescriptor.UnsignedIntType.byteSize shouldBe 4
        }

        "UnsignedLongType#descriptor should return \"l\"" {
            TypeDescriptor.UnsignedLongType.descriptor shouldBe "j"
        }

        "UnsignedLongType#byteSize should return 8" {
            TypeDescriptor.UnsignedLongType.byteSize shouldBe 8
        }

        "FloatType#descriptor should return \"F\"" {
            TypeDescriptor.FloatType.descriptor shouldBe "F"
        }

        "FloatType#byteSize should return 4" {
            TypeDescriptor.FloatType.byteSize shouldBe 4
        }

        "DoubleType#descriptor should return \"D\"" {
            TypeDescriptor.DoubleType.descriptor shouldBe "D"
        }

        "DoubleType#byteSize should return 8" {
            TypeDescriptor.DoubleType.byteSize shouldBe 8
        }

        "VoidType#descriptor should return \"V\"" {
            TypeDescriptor.VoidType.descriptor shouldBe "V"
        }

        "VoidType#byteSize should return 0" {
            TypeDescriptor.VoidType.byteSize shouldBe 0
        }

        "CharType#descriptor should return \"C\"" {
            TypeDescriptor.CharType.descriptor shouldBe "C"
        }

        "CharType#byteSize should return 2" {
            TypeDescriptor.CharType.byteSize shouldBe 2
        }

        "BooleanType#descriptor should return \"Z\"" {
            TypeDescriptor.BooleanType.descriptor shouldBe "Z"
        }

        "BooleanType#byteSize should return 1" {
            TypeDescriptor.BooleanType.byteSize shouldBe 1
        }

        "ObjectType#descriptor should return \"L\" + name + \";\"" {
            TypeDescriptor.ObjectType("test").descriptor shouldBe "Ltest;"
        }

        "ObjectType#descriptor with with one generic type" {
            TypeDescriptor.ObjectType("test", listOf(TypeDescriptor.ByteType)).descriptor shouldBe "Ltest@B;"
        }

        "ObjectType#descriptor with with two generic types" {
            TypeDescriptor.ObjectType(
                "test",
                listOf(TypeDescriptor.ByteType, TypeDescriptor.IntType),
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
            (
                TypeDescriptor.ObjectType("test") == TypeDescriptor.ObjectType(
                    "test",
                    listOf(TypeDescriptor.ByteType),
                )
                ) shouldBe false
        }

        "ObjectType#equals should return true for different object with same name and same generic types" {
            (
                TypeDescriptor.ObjectType(
                    "test",
                    listOf(TypeDescriptor.ByteType),
                ) == TypeDescriptor.ObjectType("test", listOf(TypeDescriptor.ByteType))
                ) shouldBe true
        }

        "ArrayType#descriptor should return \"[\" + type.descriptor" {
            TypeDescriptor.ArrayType(TypeDescriptor.ByteType).descriptor shouldBe "[B"
        }

        "ArrayType#byteSize should return 8" {
            TypeDescriptor.ArrayType(TypeDescriptor.ByteType).byteSize shouldBe 8
        }

        "Parsing of ByteType should return ByteType" {
            TypeDescriptor.parse("B") shouldBe TypeDescriptor.ByteType
        }

        "Parsing of ShortType should return ShortType" {
            TypeDescriptor.parse("S") shouldBe TypeDescriptor.ShortType
        }

        "Parsing of IntType should return IntType" {
            TypeDescriptor.parse("I") shouldBe TypeDescriptor.IntType
        }

        "Parsing of LongType should return LongType" {
            TypeDescriptor.parse("J") shouldBe TypeDescriptor.LongType
        }

        "Parsing of UnsignedByteType should return UnsignedByteType" {
            TypeDescriptor.parse("b") shouldBe TypeDescriptor.UnsignedByteType
        }

        "Parsing of UnsignedShortType should return UnsignedShortType" {
            TypeDescriptor.parse("s") shouldBe TypeDescriptor.UnsignedShortType
        }

        "Parsing of UnsignedIntType should return UnsignedIntType" {
            TypeDescriptor.parse("i") shouldBe TypeDescriptor.UnsignedIntType
        }

        "Parsing of UnsignedLongType should return UnsignedLongType" {
            TypeDescriptor.parse("l") shouldBe TypeDescriptor.UnsignedLongType
        }

        "Parsing of FloatType should return FloatType" {
            TypeDescriptor.parse("F") shouldBe TypeDescriptor.FloatType
        }

        "Parsing of DoubleType should return DoubleType" {
            TypeDescriptor.parse("D") shouldBe TypeDescriptor.DoubleType
        }

        "Parsing of VoidType should return VoidType" {
            TypeDescriptor.parse("V") shouldBe TypeDescriptor.VoidType
        }

        "Parsing of CharType should return CharType" {
            TypeDescriptor.parse("C") shouldBe TypeDescriptor.CharType
        }

        "Parsing of BooleanType should return BooleanType" {
            TypeDescriptor.parse("Z") shouldBe TypeDescriptor.BooleanType
        }

        "Parsing of ObjectType should return ObjectType" {
            TypeDescriptor.parse("Ltest;") shouldBe TypeDescriptor.ObjectType("test")
        }

        "Parsing of ObjectType with one generic type should return ObjectType" {
            TypeDescriptor.parse("Ltest@B;") shouldBe TypeDescriptor.ObjectType(
                "test",
                listOf(TypeDescriptor.ByteType),
            )
        }

        "Parsing of ObjectType with two generic types should return ObjectType" {
            TypeDescriptor.parse("Ltest@B+I;") shouldBe TypeDescriptor.ObjectType(
                "test",
                listOf(TypeDescriptor.ByteType, TypeDescriptor.IntType),
            )
        }

        "Parsing of ArrayType should return ArrayType" {
            TypeDescriptor.parse("[B") shouldBe TypeDescriptor.ArrayType(TypeDescriptor.ByteType)
        }

        "Parsing of ArrayType with ObjectType should return ArrayType" {
            TypeDescriptor.parse("[Ltest;") shouldBe TypeDescriptor.ArrayType(TypeDescriptor.ObjectType("test"))
        }

        "Parsing of ArrayType with ObjectType with one generic type should return ArrayType" {
            TypeDescriptor.parse("[Ltest@B;") shouldBe TypeDescriptor.ArrayType(
                TypeDescriptor.ObjectType(
                    "test",
                    listOf(TypeDescriptor.ByteType),
                ),
            )
        }

        "Parsing of ArrayType with ObjectType with two generic types should return ArrayType" {
            TypeDescriptor.parse("[Ltest@B+I;") shouldBe TypeDescriptor.ArrayType(
                TypeDescriptor.ObjectType(
                    "test",
                    listOf(TypeDescriptor.ByteType, TypeDescriptor.IntType),
                ),
            )
        }

        "Parsing of ArrayType with ArrayType should return ArrayType" {
            TypeDescriptor.parse("[[B") shouldBe TypeDescriptor.ArrayType(TypeDescriptor.ArrayType(TypeDescriptor.ByteType))
        }

        "Parsing of ArrayType with ArrayType with ObjectType should return ArrayType" {
            TypeDescriptor.parse("[[Ltest;") shouldBe TypeDescriptor.ArrayType(
                TypeDescriptor.ArrayType(
                    TypeDescriptor.ObjectType(
                        "test",
                    ),
                ),
            )
        }

        "Parsing of ObjectType with generic object type" {
            TypeDescriptor.parse("Ltest@Ltest2;;") shouldBe TypeDescriptor.ObjectType(
                "test",
                listOf(TypeDescriptor.ObjectType("test2")),
            )
        }
    },
)

package com.shakelang.shake.bytecode.interpreter.format

import com.shakelang.shake.bytecode.interpreter.format.attribute.AnonymousAttributeImpl
import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.util.io.streaming.input.bytes.dataStream
import com.shakelang.util.io.streaming.output.bytes.ByteArrayOutputStream
import com.shakelang.util.io.streaming.output.bytes.DataOutputStream
import com.shakelang.util.primitives.bytes.toBytes
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class MethodTests : FreeSpec(
    {

        "name constant" {

            val pool = MutableConstantPool()
            val method = Method(pool, 1, 0, emptyList())
            method.qualifiedNameConstant shouldBe 1
        }

        "qualified name constant" {

            val pool = MutableConstantPool()
            val method = Method(pool, 0, 0, emptyList())
            method.qualifiedNameConstant shouldBe 0
        }

        "name getter" {

            val pool = MutableConstantPool()
            val method = Method(pool, pool.resolveUtf8("name(I)V"), 0, emptyList())
            method.name shouldBe "name"
        }

        "qualified name getter" {

            val pool = MutableConstantPool()
            val method = Method(pool, pool.resolveUtf8("name(I)V"), 0, emptyList())
            method.qualifiedName shouldBe "name(I)V"
        }

        "flags" {

            val pool = MutableConstantPool()
            val method = Method(pool, 0, 0b00000000_00000001, emptyList())
            method.isPublic shouldBe true
            method.isPrivate shouldBe false
            method.isProtected shouldBe false
            method.isStatic shouldBe false
            method.isFinal shouldBe false
        }

        "is public (true)" {

            val pool = MutableConstantPool()
            val method = Method(pool, 0, 0b00000000_00000001, emptyList())
            method.isPublic shouldBe true
        }

        "is public (false)" {

            val pool = MutableConstantPool()
            val method = Method(pool, 0, 0b00000000_00000000, emptyList())
            method.isPublic shouldBe false
        }

        "is private (true)" {

            val pool = MutableConstantPool()
            val method = Method(pool, 0, 0b00000000_00000010, emptyList())
            method.isPrivate shouldBe true
        }

        "is private (false)" {

            val pool = MutableConstantPool()
            val method = Method(pool, 0, 0b00000000_00000000, emptyList())
            method.isPrivate shouldBe false
        }

        "is protected (true)" {

            val pool = MutableConstantPool()
            val method = Method(pool, 0, 0b00000000_00000100, emptyList())
            method.isProtected shouldBe true
        }

        "is protected (false)" {

            val pool = MutableConstantPool()
            val method = Method(pool, 0, 0b00000000_00000000, emptyList())
            method.isProtected shouldBe false
        }

        "is static (true)" {

            val pool = MutableConstantPool()
            val method = Method(pool, 0, 0b00000000_00001000, emptyList())
            method.isStatic shouldBe true
        }

        "is static (false)" {

            val pool = MutableConstantPool()
            val method = Method(pool, 0, 0b00000000_00000000, emptyList())
            method.isStatic shouldBe false
        }

        "is final (true)" {

            val pool = MutableConstantPool()
            val method = Method(pool, 0, 0b00000000_00010000, emptyList())
            method.isFinal shouldBe true
        }

        "is final (false)" {

            val pool = MutableConstantPool()
            val method = Method(pool, 0, 0b00000000_00000000, emptyList())
            method.isFinal shouldBe false
        }

        "equals" {

            val pool = MutableConstantPool()
            val method = Method(pool, 0, 0b00000000_00010000, emptyList())
            val method2 = Method(pool, 0, 0b00000000_00010000, emptyList())
            val method3 =
                Method(pool, 0, 0b00000000_00010000, listOf(AnonymousAttributeImpl(pool, 0, byteArrayOf(1, 2, 3))))
            val method4 =
                Method(pool, 0, 0b00000000_00010000, listOf(AnonymousAttributeImpl(pool, 0, byteArrayOf(1, 2, 3, 4))))
            val method5 = Method(pool, 1, 0b00000000_00010000, emptyList())
            val method6 = Method(pool, 1, 0b00000000_00001000, emptyList())

            method shouldBe method
            method shouldBe method2
            method shouldNotBe method3
            method shouldNotBe method4
            method shouldNotBe method5
            method shouldNotBe method6
            method3 shouldNotBe method4
        }

        "hashcode" {

            val pool = MutableConstantPool()
            val method = Method(pool, 0, 0b00000000_00010000, emptyList())
            val method2 = Method(pool, 0, 0b00000000_00010000, emptyList())
            val method3 =
                Method(pool, 0, 0b00000000_00010000, listOf(AnonymousAttributeImpl(pool, 0, byteArrayOf(1, 2, 3))))
            val method4 =
                Method(pool, 0, 0b00000000_00010000, listOf(AnonymousAttributeImpl(pool, 0, byteArrayOf(1, 2, 3, 4))))
            val method5 = Method(pool, 1, 0b00000000_00010000, emptyList())
            val method6 = Method(pool, 1, 0b00000000_00001000, emptyList())

            method.hashCode() shouldBe method.hashCode()
            method.hashCode() shouldBe method2.hashCode()
            method.hashCode() shouldNotBe method3.hashCode()
            method.hashCode() shouldNotBe method4.hashCode()
            method.hashCode() shouldNotBe method5.hashCode()
            method.hashCode() shouldNotBe method6.hashCode()
            method3.hashCode() shouldNotBe method4.hashCode()
        }

        "dump" {

            val pool = MutableConstantPool()
            val stream = ByteArrayOutputStream()
            val method = Method(pool, 0, 0b00000000_00010000, emptyList())
            method.dump(DataOutputStream(stream))

            stream.toByteArray() shouldBe byteArrayOf(
                // qualified name constant
                *0.toBytes(),
                // flags
                *0x10.toShort().toBytes(),
                // attributes size
                *0.toShort().toBytes(),
            )
        }

        "dump to byte array" {

            val pool = MutableConstantPool()
            val method = Method(pool, 0, 0b00000000_00010000, emptyList())
            method.dump() shouldBe byteArrayOf(
                // name constant
                *0.toBytes(),
                // flags
                *0x10.toShort().toBytes(),
                // attributes size
                *0.toShort().toBytes(),
            )
        }

        "dump with attributes" {

            val pool = MutableConstantPool()
            val stream = ByteArrayOutputStream()
            val method = Method(
                pool,
                0,
                0b00000000_00010000,
                listOf(AnonymousAttributeImpl(pool, 0, byteArrayOf(1, 2, 3))),
            )
            method.dump(DataOutputStream(stream))

            stream.toByteArray() shouldBe byteArrayOf(
                // name constant
                *0.toBytes(),
                // flags
                *0x10.toShort().toBytes(),
                // attributes size
                *1.toShort().toBytes(),
                // attribute name constant
                *0.toBytes(),
                // attribute data size
                *3.toBytes(),
                // attribute data
                1,
                2,
                3,
            )
        }

        "dump to byte array with attributes" {

            val pool = MutableConstantPool()
            val method = Method(
                pool,
                0,
                0b00000000_00010000,
                listOf(AnonymousAttributeImpl(pool, 0, byteArrayOf(1, 2, 3))),
            )
            method.dump() shouldBe byteArrayOf(
                // name constant
                *0.toBytes(),
                // flags
                *0x10.toShort().toBytes(),
                // attributes size
                *1.toShort().toBytes(),
                // attribute name constant
                *0.toBytes(),
                // attribute data size
                *3.toBytes(),
                // attribute data
                1,
                2,
                3,
            )
        }

        "from stream" {

            val pool = MutableConstantPool()
            val stream = byteArrayOf(
                // qualified name constant
                *0.toBytes(),
                // flags
                *0x10.toShort().toBytes(),
                // attributes size
                *0.toShort().toBytes(),
            ).dataStream()

            val method = Method.fromStream(pool, stream)
            method.qualifiedNameConstant shouldBe 0
            method.flags shouldBe 0x10.toShort()
            method.attributes.size shouldBe 0
        }

        "from stream with attributes" {

            val pool = MutableConstantPool()
            pool.resolveUtf8("SomeUnknownAttributeType")
            val stream = byteArrayOf(
                // name constant
                *0.toBytes(),
                // flags
                *0x10.toShort().toBytes(),
                // attributes size
                *1.toShort().toBytes(),
                // attribute name constant
                *0.toBytes(),
                // attribute data size
                *3.toBytes(),
                // attribute data
                1,
                2,
                3,
            ).dataStream()

            val method = Method.fromStream(pool, stream)
            method.qualifiedNameConstant shouldBe 0
            method.flags shouldBe 0x10.toShort()
            method.attributes.size shouldBe 1
            method.attributes[0].nameConstant shouldBe 0
            method.attributes[0].value contentEquals byteArrayOf(1, 2, 3) shouldBe true
        }
    },
)

class MutableMethodTests : FreeSpec(
    {
        "qualified name constant" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, 0, 0, mutableListOf())
            method.qualifiedNameConstant shouldBe 0
        }

        "set qualified name constant" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, 0, 0, mutableListOf())
            method.qualifiedNameConstant = 1
            method.qualifiedNameConstant shouldBe 1
        }

        "name getter" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, pool.resolveUtf8("name()V"), 0, mutableListOf())
            method.name shouldBe "name"
        }

        "qualified name getter" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, pool.resolveUtf8("qualifiedName"), 0, mutableListOf())
            method.qualifiedName shouldBe "qualifiedName"
        }

        "qualified name setter" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, pool.resolveUtf8("qualifiedName"), 0, mutableListOf())
            method.qualifiedName = "qualifiedName2"
            method.qualifiedName shouldBe "qualifiedName2"
        }

        "flags" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, 0, 0b00000000_00000001, mutableListOf())
            method.isPublic shouldBe true
            method.isPrivate shouldBe false
            method.isProtected shouldBe false
            method.isStatic shouldBe false
            method.isFinal shouldBe false
        }

        "set flags" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, 0, 0, mutableListOf())
            method.isPublic = true
            method.isPrivate = true
            method.isProtected = true
            method.isStatic = true
            method.isFinal = true
            method.flags shouldBe 0b00000000_00011111.toShort()
        }

        "is public (true)" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, 0, 0b00000000_00000001, mutableListOf())
            method.isPublic shouldBe true
        }

        "set is public (true)" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, 0, 0, mutableListOf())
            method.isPublic = true
            method.isPublic shouldBe true
        }

        "is public (false)" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, 0, 0b1111111_11111110, mutableListOf())
            method.isPublic shouldBe false
        }

        "set public (true)" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, 0, 0, mutableListOf())
            method.isPublic = true
            method.isPublic shouldBe true
        }

        "set public (false)" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, 0, 0b1111111_11111111, mutableListOf())
            method.isPublic = false
            method.isPublic shouldBe false
        }

        "is private (true)" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, 0, 0b00000000_00000010, mutableListOf())
            method.isPrivate shouldBe true
        }

        "is private (false)" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, 0, 0b00000000_00000000, mutableListOf())
            method.isPrivate shouldBe false
        }

        "set is private (true)" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, 0, 0, mutableListOf())
            method.isPrivate = true
            method.isPrivate shouldBe true
        }

        "set is private (false)" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, 0, 0b1111111_11111101, mutableListOf())
            method.isPrivate = false
            method.isPrivate shouldBe false
        }

        "is protected (true)" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, 0, 0b00000000_00000100, mutableListOf())
            method.isProtected shouldBe true
        }

        "is protected (false)" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, 0, 0b00000000_00000000, mutableListOf())
            method.isProtected shouldBe false
        }

        "set is protected (true)" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, 0, 0, mutableListOf())
            method.isProtected = true
            method.isProtected shouldBe true
        }

        "set is protected (false)" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, 0, 0b1111111_11111011, mutableListOf())
            method.isProtected = false
            method.isProtected shouldBe false
        }

        "is static (true)" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, 0, 0b00000000_00001000, mutableListOf())
            method.isStatic shouldBe true
        }

        "is static (false)" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, 0, 0b00000000_00000000, mutableListOf())
            method.isStatic shouldBe false
        }

        "set is static (true)" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, 0, 0, mutableListOf())
            method.isStatic = true
            method.isStatic shouldBe true
        }

        "set is static (false)" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, 0, 0b1111111_11110111, mutableListOf())
            method.isStatic = false
            method.isStatic shouldBe false
        }

        "is final (true)" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, 0, 0b00000000_00010000, mutableListOf())
            method.isFinal shouldBe true
        }

        "is final (false)" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, 0, 0b00000000_00000000, mutableListOf())
            method.isFinal shouldBe false
        }

        "set is final (true)" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, 0, 0, mutableListOf())
            method.isFinal = true
            method.isFinal shouldBe true
        }

        "set is final (false)" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, 0, 0b1111111_11101111, mutableListOf())
            method.isFinal = false
            method.isFinal shouldBe false
        }

        "equals" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, 0, 0b00000000_00010000, mutableListOf())
            val method2 = MutableMethod(pool, 0, 0b00000000_00010000, mutableListOf())
            val method3 = MutableMethod(
                pool,
                0,
                0b00000000_00010000,
                mutableListOf(AnonymousAttributeImpl(pool, 0, byteArrayOf(1, 2, 3))),
            )
            val method4 = MutableMethod(
                pool,
                0,
                0b00000000_00010000,
                mutableListOf(AnonymousAttributeImpl(pool, 0, byteArrayOf(1, 2, 3, 4))),
            )
            val method5 = MutableMethod(pool, 1, 0b00000000_00010000, mutableListOf())
            val method6 = MutableMethod(pool, 0, 0b00000000_00001000, mutableListOf())

            method shouldBe method
            method shouldBe method2
            method shouldNotBe method3
            method shouldNotBe method4
            method shouldNotBe method5
            method shouldNotBe method6
            method3 shouldNotBe method4
        }

        "hashcode" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, 0, 0b00000000_00010000, mutableListOf())
            val method2 = MutableMethod(pool, 0, 0b00000000_00010000, mutableListOf())
            val method3 = MutableMethod(
                pool,
                0,
                0b00000000_00010000,
                mutableListOf(AnonymousAttributeImpl(pool, 0, byteArrayOf(1, 2, 3))),
            )
            val method4 = MutableMethod(
                pool,
                0,
                0b00000000_00010000,
                mutableListOf(AnonymousAttributeImpl(pool, 0, byteArrayOf(1, 2, 3, 4))),
            )
            val method5 = MutableMethod(pool, 1, 0b00000000_00010000, mutableListOf())
            val method6 = MutableMethod(pool, 0, 0b00000000_00001000, mutableListOf())

            method.hashCode() shouldBe method.hashCode()
            method.hashCode() shouldBe method2.hashCode()
            method.hashCode() shouldNotBe method3.hashCode()
            method.hashCode() shouldNotBe method4.hashCode()
            method.hashCode() shouldNotBe method5.hashCode()
            method.hashCode() shouldNotBe method6.hashCode()
            method3.hashCode() shouldNotBe method4.hashCode()
        }

        "dump" {

            val pool = MutableConstantPool()
            val stream = ByteArrayOutputStream()
            val method = MutableMethod(pool, 0, 0b00000000_00010000, mutableListOf())
            method.dump(DataOutputStream(stream))

            stream.toByteArray() shouldBe byteArrayOf(
                // name constant
                *0.toBytes(),
                // flags
                *0x10.toShort().toBytes(),
                // attributes size
                *0.toShort().toBytes(),
            )
        }

        "dump to byte array" {

            val pool = MutableConstantPool()
            val method = MutableMethod(pool, 0, 0b00000000_00010000, mutableListOf())
            method.dump() shouldBe byteArrayOf(
                // name constant
                *0.toBytes(),
                // flags
                *0x10.toShort().toBytes(),
                // attributes size
                *0.toShort().toBytes(),
            )
        }

        "dump with attributes" {

            val pool = MutableConstantPool()
            val stream = ByteArrayOutputStream()
            val method = MutableMethod(
                pool,
                0,
                0b00000000_00010000,
                mutableListOf(AnonymousAttributeImpl(pool, 0, byteArrayOf(1, 2, 3))),
            )
            method.dump(DataOutputStream(stream))

            stream.toByteArray() shouldBe byteArrayOf(
                // name constant
                *0.toBytes(),
                // flags
                *0x10.toShort().toBytes(),
                // attributes size
                *1.toShort().toBytes(),
                // attribute name constant
                *0.toBytes(),
                // attribute data size
                *3.toBytes(),
                // attribute data
                1,
                2,
                3,
            )
        }

        "dump to byte array with attributes" {

            val pool = MutableConstantPool()
            val method = MutableMethod(
                pool,
                0,
                0b00000000_00010000,
                mutableListOf(AnonymousAttributeImpl(pool, 0, byteArrayOf(1, 2, 3))),
            )
            method.dump() shouldBe byteArrayOf(
                // qualified name constant
                *0.toBytes(),
                // flags
                *0x10.toShort().toBytes(),
                // attributes size
                *1.toShort().toBytes(),
                // attribute name constant
                *0.toBytes(),
                // attribute data size
                *3.toBytes(),
                // attribute data
                1,
                2,
                3,
            )
        }

        "from stream" {

            val pool = MutableConstantPool()
            val stream = byteArrayOf(
                // qualified name constant
                *0.toBytes(),
                // flags
                *0x10.toShort().toBytes(),
                // attributes size
                *0.toShort().toBytes(),
            ).dataStream()

            val method = MutableMethod.fromStream(pool, stream)
            method.qualifiedNameConstant shouldBe 0
            method.flags shouldBe 0x10.toShort()
            method.attributes.size shouldBe 0
        }

        "from stream with attributes" {

            val pool = MutableConstantPool()
            pool.resolveUtf8("SomeUnknownAttributeType")
            val stream = byteArrayOf(
                // qualified name constant
                *1.toBytes(),
                // flags
                *0x10.toShort().toBytes(),
                // attributes size
                *1.toShort().toBytes(),
                // attribute name constant
                *0.toBytes(),
                // attribute data size
                *3.toBytes(),
                // attribute data
                1,
                2,
                3,
            ).dataStream()

            val method = MutableMethod.fromStream(pool, stream)
            method.qualifiedNameConstant shouldBe 1
            method.flags shouldBe 0x10.toShort()
            method.attributes.size shouldBe 1
            method.attributes[0].nameConstant shouldBe 0
            method.attributes[0].value contentEquals byteArrayOf(1, 2, 3) shouldBe true
        }

        "from method" {

            val pool = MutableConstantPool()
            val method = Method(
                pool,
                1,
                0b00000000_00010000,
                mutableListOf(AnonymousAttributeImpl(pool, 0, byteArrayOf(1, 2, 3))),
            )
            val method2 = MutableMethod.fromMethod(pool, method)
            method2.qualifiedNameConstant shouldBe 1
            method2.flags shouldBe 0b00000000_00010000.toShort()
            method2.attributes.size shouldBe 1
            method2.attributes[0].nameConstant shouldBe 0
            method2.attributes[0].value contentEquals byteArrayOf(1, 2, 3) shouldBe true
        }
    },
)

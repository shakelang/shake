package com.shakelang.shake.bytecode.interpreter.format

import com.shakelang.shake.bytecode.interpreter.format.pool.MutableConstantPool
import com.shakelang.shake.util.io.streaming.input.dataStream
import com.shakelang.shake.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.shake.util.io.streaming.output.DataOutputStream
import com.shakelang.shake.util.primitives.bytes.toBytes
import com.shakelang.shake.util.testlib.shouldContainExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class StorageFormatTests : FreeSpec({

    "Magic should be 0x4a16a478" {
        val format = StorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = MutableConstantPool(),
            classes = listOf(),
            fields = listOf(),
            methods = listOf()
        )

        format.magic shouldBe 0x4a16a478
    }

    "Major should be 0" {
        val format = StorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = MutableConstantPool(),
            classes = listOf(),
            fields = listOf(),
            methods = listOf()
        )

        format.major shouldBe 0
    }

    "Minor should be 0" {
        val format = StorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = MutableConstantPool(),
            classes = listOf(),
            fields = listOf(),
            methods = listOf()
        )

        format.minor shouldBe 0
    }

    "ConstantPool should be MutableConstantPool" {
        val format = StorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = MutableConstantPool(),
            classes = listOf(),
            fields = listOf(),
            methods = listOf()
        )

        format.constantPool shouldBe MutableConstantPool()
    }

    "Classes (empty)" {
        val format = StorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = MutableConstantPool(),
            classes = listOf(),
            fields = listOf(),
            methods = listOf()
        )

        format.classes shouldBe listOf()
    }

    "Classes (not empty)" {
        val pool = MutableConstantPool()

        val clazz = Class(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            0,
            listOf(),
            listOf(),
            listOf(),
            listOf(),
            listOf()
        )

        val format = StorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = MutableConstantPool(),
            classes = listOf(clazz),
            fields = listOf(),
            methods = listOf()
        )

        format.classes shouldBe listOf(clazz)
    }

    "Fields (empty)" {
        val format = StorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = MutableConstantPool(),
            classes = listOf(),
            fields = listOf(),
            methods = listOf()
        )

        format.fields shouldBe listOf()
    }

    "Fields (not empty)" {
        val pool = MutableConstantPool()

        val field = Field(
            pool,
            pool.resolveUtf8("test"),
            0,
            10,
            listOf()
        )

        val format = StorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = MutableConstantPool(),
            classes = listOf(),
            fields = listOf(field),
            methods = listOf()
        )

        format.fields shouldBe listOf(field)
    }

    "Methods (empty)" {
        val format = StorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = MutableConstantPool(),
            classes = listOf(),
            fields = listOf(),
            methods = listOf()
        )

        format.methods shouldBe listOf()
    }

    "Methods (not empty)" {
        val pool = MutableConstantPool()

        val method = Method(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            10.toShort(),
            listOf()
        )

        val format = StorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = MutableConstantPool(),
            classes = listOf(),
            fields = listOf(),
            methods = listOf(method)
        )

        format.methods shouldBe listOf(method)
    }

    "equals" {
        val pool = MutableConstantPool()

        val clazz = Class(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            0,
            listOf(),
            listOf(),
            listOf(),
            listOf(),
            listOf()
        )

        val field = Field(
            pool,
            pool.resolveUtf8("test"),
            0,
            10,
            listOf()
        )

        val method = Method(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            10.toShort(),
            listOf()
        )

        val format = StorageFormat(
            major = 0,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = listOf(clazz),
            fields = listOf(field),
            methods = listOf(method)
        )

        val format2 = StorageFormat(
            major = 0,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = listOf(clazz),
            fields = listOf(field),
            methods = listOf(method)
        )

        val format3 = StorageFormat(
            major = 0,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = listOf(clazz),
            fields = listOf(field),
            methods = listOf()
        )

        val format4 = StorageFormat(
            major = 0,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = listOf(clazz),
            fields = listOf(),
            methods = listOf(method)
        )

        val format5 = StorageFormat(
            major = 0,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = listOf(),
            fields = listOf(field),
            methods = listOf(method)
        )

        val format6 = StorageFormat(
            major = 1,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = listOf(clazz),
            fields = listOf(field),
            methods = listOf(method)
        )

        val format7 = StorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = pool,
            classes = listOf(clazz),
            fields = listOf(field),
            methods = listOf(method)
        )

        format shouldBe format
        format shouldBe format2
        format shouldNotBe format3
        format shouldNotBe format4
        format shouldNotBe format5
        format shouldNotBe format6
        format shouldNotBe format7
    }

    "hashCode" {
        val pool = MutableConstantPool()

        val clazz = Class(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            0,
            listOf(),
            listOf(),
            listOf(),
            listOf(),
            listOf()
        )

        val field = Field(
            pool,
            pool.resolveUtf8("test"),
            0,
            10,
            listOf()
        )

        val method = Method(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            10.toShort(),
            listOf()
        )

        val format = StorageFormat(
            major = 0,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = listOf(clazz),
            fields = listOf(field),
            methods = listOf(method)
        )

        val format2 = StorageFormat(
            major = 0,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = listOf(clazz),
            fields = listOf(field),
            methods = listOf(method)
        )

        val format3 = StorageFormat(
            major = 0,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = listOf(clazz),
            fields = listOf(field),
            methods = listOf()
        )

        val format4 = StorageFormat(
            major = 0,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = listOf(clazz),
            fields = listOf(),
            methods = listOf(method)
        )

        val format5 = StorageFormat(
            major = 0,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = listOf(),
            fields = listOf(field),
            methods = listOf(method)
        )

        val format6 = StorageFormat(
            major = 1,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = listOf(clazz),
            fields = listOf(field),
            methods = listOf(method)
        )

        val format7 = StorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = pool,
            classes = listOf(clazz),
            fields = listOf(field),
            methods = listOf(method)
        )

        format.hashCode() shouldBe format.hashCode()
        format.hashCode() shouldBe format2.hashCode()
        format.hashCode() shouldNotBe format3.hashCode()
        format.hashCode() shouldNotBe format4.hashCode()
        format.hashCode() shouldNotBe format5.hashCode()
        format.hashCode() shouldNotBe format6.hashCode()
        format.hashCode() shouldNotBe format7.hashCode()
    }

    "dump" {

        val stream = ByteArrayOutputStream()
        val format = StorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = MutableConstantPool(),
            classes = listOf(),
            fields = listOf(),
            methods = listOf()
        )

        format.dump(DataOutputStream(stream))

        stream.toByteArray() shouldContainExactly byteArrayOf(
            *0x4a16a478.toBytes(),
            *0.toShort().toBytes(),
            *0.toShort().toBytes(),
            *0.toBytes(),
            *0.toBytes(),
            *0.toShort().toBytes(),
            *0.toShort().toBytes(),
            *0.toShort().toBytes()
        )
    }

    "dump with data" {
        val pool = MutableConstantPool()

        val clazz = Class(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            0,
            listOf(),
            listOf(),
            listOf(),
            listOf(),
            listOf()
        )

        val field = Field(
            pool,
            pool.resolveUtf8("test"),
            0,
            10,
            listOf()
        )

        val method = Method(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            10.toShort(),
            listOf()
        )

        val stream = ByteArrayOutputStream()

        val format = StorageFormat(
            major = 0,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = listOf(clazz),
            fields = listOf(field),
            methods = listOf(method)
        )

        format.dump(DataOutputStream(stream))

        stream.toByteArray() shouldContainExactly byteArrayOf(
            *0x4a16a478.toBytes(),
            *0.toShort().toBytes(),
            *1.toShort().toBytes(),
            *0.toBytes(),
            *pool.dump(),
            *1.toShort().toBytes(),
            *clazz.dump(),
            *1.toShort().toBytes(),
            *method.dump(),
            *1.toShort().toBytes(),
            *field.dump(),
        )
    }

    "dump to byte array" {
        val pool = MutableConstantPool()

        val storageFormat = StorageFormat(
            major = 0,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = listOf(),
            fields = listOf(),
            methods = listOf()
        )

        val format = storageFormat.dump()

        format shouldContainExactly byteArrayOf(
            *0x4a16a478.toBytes(),
            *0.toShort().toBytes(),
            *1.toShort().toBytes(),
            *0.toBytes(),
            *0.toBytes(),
            *0.toShort().toBytes(),
            *0.toShort().toBytes(),
            *0.toShort().toBytes()
        )
    }

    "dump to byte array with data" {
        val pool = MutableConstantPool()

        val clazz = Class(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            0,
            listOf(),
            listOf(),
            listOf(),
            listOf(),
            listOf()
        )

        val field = Field(
            pool,
            pool.resolveUtf8("test"),
            0,
            10,
            listOf()
        )

        val method = Method(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            10.toShort(),
            listOf()
        )

        val storageFormat = StorageFormat(
            major = 0,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = listOf(clazz),
            fields = listOf(field),
            methods = listOf(method)
        )

        val format = storageFormat.dump()

        format shouldContainExactly byteArrayOf(
            *0x4a16a478.toBytes(),
            *0.toShort().toBytes(),
            *1.toShort().toBytes(),
            *0.toBytes(),
            *pool.dump(),
            *1.toShort().toShort().toBytes(),
            *clazz.dump(),
            *1.toShort().toBytes(),
            *method.dump(),
            *1.toShort().toBytes(),
            *field.dump(),
        )
    }

    "from stream" {
        val pool = MutableConstantPool()

        pool.resolveUtf8("test")

        val stream = byteArrayOf(
            *0x4a16a478.toBytes(),
            *0.toShort().toBytes(),
            *1.toShort().toBytes(),
            *0.toBytes(),
            *pool.dump(),
            *0.toShort().toBytes(),
            *0.toShort().toBytes(),
            *0.toShort().toBytes()
        ).dataStream()

        val format = StorageFormat.fromStream(stream)

        format shouldBe StorageFormat(
            major = 0,
            minor = 1,
            constantPool = pool,
            classes = listOf(),
            fields = listOf(),
            packageNameConstant = 0,
            methods = listOf()
        )
    }

    "from stream with data" {
        val pool = MutableConstantPool()

        val clazz = Class(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            0,
            listOf(),
            listOf(),
            listOf(),
            listOf(),
            listOf()
        )

        val field = Field(
            pool,
            pool.resolveUtf8("test"),
            0,
            10,
            listOf()
        )

        val method = Method(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            10.toShort(),
            listOf()
        )

        val stream = byteArrayOf(
            *0x4a16a478.toBytes(),
            *0.toShort().toBytes(),
            *1.toShort().toBytes(),
            *0.toBytes(),
            *pool.dump(),
            *1.toShort().toBytes(),
            *clazz.dump(),
            *1.toShort().toBytes(),
            *field.dump(),
            *1.toShort().toBytes(),
            *method.dump()
        ).dataStream()

        val format = StorageFormat.fromStream(stream)

        format.classes shouldHaveSize 1
        format.fields shouldHaveSize 1
        format.methods shouldHaveSize 1
    }
})

class MutableStorageFormatTests : FreeSpec({

    "Magic should be 0x4a16a478" {
        val format = MutableStorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = MutableConstantPool(),
            classes = mutableListOf(),
            fields = mutableListOf(),
            methods = mutableListOf()
        )

        format.magic shouldBe 0x4a16a478
    }

    "Major should be 0" {
        val format = MutableStorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = MutableConstantPool(),
            classes = mutableListOf(),
            fields = mutableListOf(),
            methods = mutableListOf()
        )

        format.major shouldBe 0
    }

    "set major" {
        val format = MutableStorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = MutableConstantPool(),
            classes = mutableListOf(),
            fields = mutableListOf(),
            methods = mutableListOf()
        )
        format.major = 1
        format.major shouldBe 1
    }

    "Minor should be 0" {
        val format = MutableStorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = MutableConstantPool(),
            classes = mutableListOf(),
            fields = mutableListOf(),
            methods = mutableListOf()
        )

        format.minor shouldBe 0
    }

    "set minor" {
        val format = MutableStorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = MutableConstantPool(),
            classes = mutableListOf(),
            fields = mutableListOf(),
            methods = mutableListOf()
        )
        format.minor = 1
        format.minor shouldBe 1
    }

    "ConstantPool should be MutableConstantPool" {
        val format = MutableStorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = MutableConstantPool(),
            classes = mutableListOf(),
            fields = mutableListOf(),
            methods = mutableListOf()
        )

        format.constantPool shouldBe MutableConstantPool()
    }

    "set constantPool" {
        val format = MutableStorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = MutableConstantPool(),
            classes = mutableListOf(),
            fields = mutableListOf(),
            methods = mutableListOf()
        )
        val pool = MutableConstantPool()
        format.constantPool = pool
        format.constantPool shouldBe pool
    }

    "Classes (empty)" {
        val format = MutableStorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = MutableConstantPool(),
            classes = mutableListOf(),
            fields = mutableListOf(),
            methods = mutableListOf()
        )

        format.classes shouldBe mutableListOf()
    }

    "Classes (not empty)" {
        val pool = MutableConstantPool()

        val clazz = MutableClass(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            0,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        val format = MutableStorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = MutableConstantPool(),
            classes = mutableListOf(clazz),
            fields = mutableListOf(),
            methods = mutableListOf()
        )

        format.classes shouldBe mutableListOf(clazz)
    }

    "set classes" {
        val pool = MutableConstantPool()

        val clazz = MutableClass(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            0,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        val format = MutableStorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = MutableConstantPool(),
            classes = mutableListOf(),
            fields = mutableListOf(),
            methods = mutableListOf()
        )
        format.classes = mutableListOf(clazz)
        format.classes shouldBe mutableListOf(clazz)
    }

    "Fields (empty)" {
        val format = MutableStorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = MutableConstantPool(),
            classes = mutableListOf(),
            fields = mutableListOf(),
            methods = mutableListOf()
        )
    }

    "Fields (not empty)" {
        val pool = MutableConstantPool()

        val field = MutableField(
            pool,
            pool.resolveUtf8("test"),
            0,
            10,
            mutableListOf()
        )

        val format = MutableStorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = MutableConstantPool(),
            classes = mutableListOf(),
            fields = mutableListOf(field),
            methods = mutableListOf()
        )

        format.fields shouldBe mutableListOf(field)
    }

    "set fields" {
        val pool = MutableConstantPool()

        val field = MutableField(
            pool,
            pool.resolveUtf8("test"),
            0,
            10,
            mutableListOf()
        )

        val format = MutableStorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = MutableConstantPool(),
            classes = mutableListOf(),
            fields = mutableListOf(),
            methods = mutableListOf()
        )
        format.fields = mutableListOf(field)
        format.fields shouldBe mutableListOf(field)
    }

    "Methods (empty)" {
        val format = MutableStorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = MutableConstantPool(),
            classes = mutableListOf(),
            fields = mutableListOf(),
            methods = mutableListOf()
        )
    }

    "Methods (not empty)" {
        val pool = MutableConstantPool()

        val method = MutableMethod(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            10.toShort(),
            mutableListOf()
        )

        val format = MutableStorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = MutableConstantPool(),
            classes = mutableListOf(),
            fields = mutableListOf(),
            methods = mutableListOf(method)
        )

        format.methods shouldBe mutableListOf(method)
    }

    "set methods" {
        val pool = MutableConstantPool()

        val method = MutableMethod(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            10.toShort(),
            mutableListOf()
        )

        val format = MutableStorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = MutableConstantPool(),
            classes = mutableListOf(),
            fields = mutableListOf(),
            methods = mutableListOf()
        )
        format.methods = mutableListOf(method)
        format.methods shouldBe mutableListOf(method)
    }

    "equals" {
        val pool = MutableConstantPool()

        val clazz = MutableClass(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            0,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        val field = MutableField(
            pool,
            pool.resolveUtf8("test"),
            0,
            10,
            mutableListOf()
        )

        val method = MutableMethod(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            10.toShort(),
            mutableListOf()
        )

        val format = MutableStorageFormat(
            major = 0,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = mutableListOf(clazz),
            fields = mutableListOf(field),
            methods = mutableListOf(method)
        )

        val format2 = MutableStorageFormat(
            major = 0,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = mutableListOf(clazz),
            fields = mutableListOf(field),
            methods = mutableListOf(method)
        )

        val format3 = MutableStorageFormat(
            major = 0,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = mutableListOf(clazz),
            fields = mutableListOf(field),
            methods = mutableListOf()
        )

        val format4 = MutableStorageFormat(
            major = 0,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = mutableListOf(clazz),
            fields = mutableListOf(),
            methods = mutableListOf(method)
        )

        val format5 = MutableStorageFormat(
            major = 0,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = mutableListOf(),
            fields = mutableListOf(field),
            methods = mutableListOf(method)
        )

        val format6 = MutableStorageFormat(
            major = 1,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = mutableListOf(clazz),
            fields = mutableListOf(field),
            methods = mutableListOf(method)
        )

        val format7 = MutableStorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = pool,
            classes = mutableListOf(clazz),
            fields = mutableListOf(field),
            methods = mutableListOf(method)
        )

        format shouldBe format
        format shouldBe format2
        format shouldNotBe format3
        format shouldNotBe format4
        format shouldNotBe format5
        format shouldNotBe format6
        format shouldNotBe format7
    }

    "hashCode" {
        val pool = MutableConstantPool()

        val clazz = MutableClass(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            0,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        val field = MutableField(
            pool,
            pool.resolveUtf8("test"),
            0,
            10,
            mutableListOf()
        )

        val method = MutableMethod(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            10.toShort(),
            mutableListOf()
        )

        val format = MutableStorageFormat(
            major = 0,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = mutableListOf(clazz),
            fields = mutableListOf(field),
            methods = mutableListOf(method)
        )

        val format2 = MutableStorageFormat(
            major = 0,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = mutableListOf(clazz),
            fields = mutableListOf(field),
            methods = mutableListOf(method)
        )

        val format3 = MutableStorageFormat(
            major = 0,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = mutableListOf(clazz),
            fields = mutableListOf(field),
            methods = mutableListOf()
        )

        val format4 = MutableStorageFormat(
            major = 0,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = mutableListOf(clazz),
            fields = mutableListOf(),
            methods = mutableListOf(method)
        )

        val format5 = MutableStorageFormat(
            major = 0,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = mutableListOf(),
            fields = mutableListOf(field),
            methods = mutableListOf(method)
        )

        val format6 = MutableStorageFormat(
            major = 1,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = mutableListOf(clazz),
            fields = mutableListOf(field),
            methods = mutableListOf(method)
        )

        val format7 = MutableStorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = pool,
            classes = mutableListOf(clazz),
            fields = mutableListOf(field),
            methods = mutableListOf(method)
        )

        format.hashCode() shouldBe format.hashCode()
        format.hashCode() shouldBe format2.hashCode()
        format.hashCode() shouldNotBe format3.hashCode()
        format.hashCode() shouldNotBe format4.hashCode()
        format.hashCode() shouldNotBe format5.hashCode()
        format.hashCode() shouldNotBe format6.hashCode()
        format.hashCode() shouldNotBe format7.hashCode()
    }

    "dump" {

        val stream = ByteArrayOutputStream()
        val format = MutableStorageFormat(
            major = 0,
            minor = 0,
            packageNameConstant = 0,
            constantPool = MutableConstantPool(),
            classes = mutableListOf(),
            fields = mutableListOf(),
            methods = mutableListOf()
        )

        format.dump(DataOutputStream(stream))

        stream.toByteArray() shouldContainExactly byteArrayOf(
            *0x4a16a478.toBytes(),
            *0.toShort().toBytes(),
            *0.toShort().toBytes(),
            *0.toBytes(),
            *0.toBytes(),
            *0.toShort().toBytes(),
            *0.toShort().toBytes(),
            *0.toShort().toBytes()
        )
    }

    "dump with data" {
        val pool = MutableConstantPool()

        val clazz = MutableClass(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            0,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        val field = MutableField(
            pool,
            pool.resolveUtf8("test"),
            0,
            10,
            mutableListOf()
        )

        val method = MutableMethod(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            10.toShort(),
            mutableListOf()
        )

        val stream = ByteArrayOutputStream()

        val format = MutableStorageFormat(
            major = 0,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = mutableListOf(clazz),
            fields = mutableListOf(field),
            methods = mutableListOf(method)
        )

        format.dump(DataOutputStream(stream))

        stream.toByteArray() shouldContainExactly byteArrayOf(
            *0x4a16a478.toBytes(),
            *0.toShort().toBytes(),
            *1.toShort().toBytes(),
            *0.toBytes(),
            *pool.dump(),
            *1.toShort().toBytes(),
            *clazz.dump(),
            *1.toShort().toBytes(),
            *method.dump(),
            *1.toShort().toBytes(),
            *field.dump(),
        )
    }

    "dump to byte array" {
        val pool = MutableConstantPool()

        val storageFormat = MutableStorageFormat(
            major = 0,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = mutableListOf(),
            fields = mutableListOf(),
            methods = mutableListOf()
        )

        val format = storageFormat.dump()

        format shouldContainExactly byteArrayOf(
            *0x4a16a478.toBytes(),
            *0.toShort().toBytes(),
            *1.toShort().toBytes(),
            *0.toBytes(), // packageNameConstant
            *0.toBytes(), // constantPool
            *0.toShort().toBytes(), // classes
            *0.toShort().toBytes(), // fields
            *0.toShort().toBytes() // methods
        )
    }

    "dump to byte array with data" {
        val pool = MutableConstantPool()

        val clazz = MutableClass(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            0,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        val field = MutableField(
            pool,
            pool.resolveUtf8("test"),
            0,
            10,
            mutableListOf()
        )

        val method = MutableMethod(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            10.toShort(),
            mutableListOf()
        )

        val storageFormat = MutableStorageFormat(
            major = 0,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = mutableListOf(clazz),
            fields = mutableListOf(field),
            methods = mutableListOf(method)
        )

        val format = storageFormat.dump()

        format shouldContainExactly byteArrayOf(
            *0x4a16a478.toBytes(),
            *0.toShort().toBytes(),
            *1.toShort().toBytes(),
            *0.toBytes(),
            *pool.dump(),
            *1.toShort().toShort().toBytes(),
            *clazz.dump(),
            *1.toShort().toBytes(),
            *method.dump(),
            *1.toShort().toBytes(),
            *field.dump(),
        )
    }

    "from stream" {
        val pool = MutableConstantPool()

        val stream = byteArrayOf(
            *0x4a16a478.toBytes(),
            *0.toShort().toBytes(),
            *1.toShort().toBytes(),
            *pool.resolveUtf8("test").toBytes(),
            *pool.dump(),
            *0.toShort().toBytes(),
            *0.toShort().toBytes(),
            *0.toShort().toBytes()
        ).dataStream()

        val format = MutableStorageFormat.fromStream(stream)

        format shouldBe MutableStorageFormat(
            major = 0,
            minor = 1,
            packageNameConstant = 0,
            constantPool = pool,
            classes = mutableListOf(),
            fields = mutableListOf(),
            methods = mutableListOf()
        )
    }

    "from stream with data" {
        val pool = MutableConstantPool()

        val clazz = Class(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            0,
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf(),
            mutableListOf()
        )

        val field = Field(
            pool,
            pool.resolveUtf8("test"),
            0,
            10,
            mutableListOf()
        )

        val method = Method(
            pool,
            pool.resolveUtf8("test"),
            pool.resolveUtf8("test2"),
            10.toShort(),
            mutableListOf()
        )

        val stream = byteArrayOf(
            *0x4a16a478.toBytes(),
            *0.toShort().toBytes(),
            *1.toShort().toBytes(),
            *0.toBytes(),
            *pool.dump(),
            *1.toShort().toBytes(),
            *clazz.dump(),
            *1.toShort().toBytes(),
            *field.dump(),
            *1.toShort().toBytes(),
            *method.dump()
        ).dataStream()

        val format = MutableStorageFormat.fromStream(stream)

        format.classes shouldHaveSize 1
        format.fields shouldHaveSize 1
        format.methods shouldHaveSize 1
    }
})

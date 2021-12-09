package io.github.shakelang.jvmlib

import io.github.shakelang.jvmlib.constants.*
import io.github.shakelang.jvmlib.constants.ConstantTags
import io.github.shakelang.jvmlib.info.AttributeInfo
import io.github.shakelang.jvmlib.info.FieldInfo
import io.github.shakelang.jvmlib.info.MethodInfo
import io.github.shakelang.parseutils.streaming.CountingInputStream
import io.github.shakelang.parseutils.streaming.DataInputStream
import io.github.shakelang.parseutils.streaming.InputStream
import io.github.shakelang.shason.json
import kotlin.properties.Delegates

class JavaClassVisitor(inputStream: InputStream) {

    private val counter = CountingInputStream(inputStream)
    private val inputStream = DataInputStream(counter)

    private var magic by Delegates.notNull<UInt>()
    private var minorVersion by Delegates.notNull<Int>()
    private var majorVersion by Delegates.notNull<Int>()
    private var constantPoolCount by Delegates.notNull<Int>()
    private lateinit var constantPool: ConstantPool
    private var accessFlags by Delegates.notNull<Int>()
    private var thisClass by Delegates.notNull<Int>()
    private var superClass by Delegates.notNull<Int>()
    private var interfacesCount by Delegates.notNull<Int>()
    private lateinit var interfaces: Array<Int>
    private var fieldsCount by Delegates.notNull<Int>()
    private lateinit var fields: Array<FieldInfo>
    private var methodsCount by Delegates.notNull<Int>()
    private lateinit var methods: Array<MethodInfo>
    private var attributesCount by Delegates.notNull<Int>()
    private lateinit var attributes: Array<AttributeInfo>

    fun process(): ClassFile {
        try {
            magic = inputStream.readUnsignedInt()
            if(magic.toLong() != 0xcafebabe) throw IllegalArgumentException("Invalid magic number 0x${magic.toString(16)}")
            println("Magic: 0x${magic.toString(16)}")
            minorVersion = inputStream.readUnsignedShort().toInt()
            println("Minor version: $minorVersion")
            majorVersion = inputStream.readUnsignedShort().toInt()
            println("Major version: $majorVersion")
            constantPoolCount = (inputStream.readUnsignedShort()).toInt()
            println("Constant pool count: $constantPoolCount")
            constantPool = ConstantPool(Array(constantPoolCount-1) { expectConstant() })
            println("Constant pool: ${json.stringify(constantPool.map{it.toJson()})}")
            accessFlags = inputStream.readUnsignedShort().toInt()
            println("Access flags: 0x${accessFlags.toString(16)}")
            thisClass = inputStream.readUnsignedShort().toInt()
            println("This class: $thisClass")
            val thisClassConstant = constantPool[thisClass] as ConstantClassInfo
            println("This class: ${json.stringify(thisClassConstant.toJson())}")
            println("This class name: ${json.stringify(constantPool[thisClassConstant.value.toInt()].toJson())}")
            superClass = inputStream.readUnsignedShort().toInt()
            println("Super class: $superClass")
            val superClassConstant = constantPool[superClass] as ConstantClassInfo
            println("Super class: ${json.stringify(superClassConstant.toJson())}")
            println("Super class name: ${json.stringify(constantPool[superClassConstant.value.toInt()].toJson())}")
            interfacesCount = inputStream.readUnsignedShort().toInt()
            println("Interfaces count: $interfacesCount")
            interfaces = Array(interfacesCount) { inputStream.readUnsignedShort().toInt() }
            println("Interfaces: ${json.stringify(interfaces.map { constantPool[it].toJson() })}")
            fieldsCount = inputStream.readUnsignedShort().toInt()
            println("Fields count: $fieldsCount")
            fields = Array(fieldsCount) { expectField() }
            println("Fields: ${json.stringify(fields.map { it.toJson() })}")
            methodsCount = inputStream.readUnsignedShort().toInt()
            println("Methods count: $methodsCount")
            methods = Array(methodsCount) {
                expectMethod()
            }
            println("Methods: ${json.stringify(methods.map { it.toJson() })}")
            attributesCount = inputStream.readUnsignedShort().toInt()
            println("Attributes count: $attributesCount")
            attributes = Array(attributesCount) { expectAttribute() }
            println("Attributes: ${json.stringify(attributes.map { it.toJson() })}")
            return ClassFile(minorVersion, majorVersion, constantPool, accessFlags, thisClass, superClass, interfaces, fields, methods, attributes)
        } catch (e: Throwable) {
            throw Error("Error at 0x${counter.getCount().toString(16)}", e)
        }
    }

    fun expectConstant(): ConstantInfo {
        val tag = inputStream.read()
        println("received tag: $tag (Address: 0x${(counter.getCount()-1).toString(16)})")
        return when (tag.toByte()) {
            ConstantTags.CONSTANT_UTF8 -> {
                val length = (inputStream.read() shl 8) + inputStream.read()
                val sb = StringBuilder()
                for (c in 0 until length) sb.append(inputStream.read().toChar())
                ConstantUtf8Info(sb.toString())
            }
            ConstantTags.CONSTANT_INTEGER -> ConstantIntegerInfo(inputStream.readInt())
            ConstantTags.CONSTANT_FLOAT -> ConstantFloatInfo(inputStream.readFloat())
            ConstantTags.CONSTANT_LONG -> ConstantLongInfo(inputStream.readLong())
            ConstantTags.CONSTANT_DOUBLE -> ConstantDoubleInfo(inputStream.readDouble())
            ConstantTags.CONSTANT_CLASS -> ConstantClassInfo(inputStream.readUnsignedShort())
            ConstantTags.CONSTANT_STRING -> ConstantStringInfo(inputStream.readUnsignedShort().toInt())
            ConstantTags.CONSTANT_FIELD_REF -> ConstantFieldrefInfo(
                inputStream.readUnsignedShort(),
                inputStream.readUnsignedShort()
            )
            ConstantTags.CONSTANT_METHOD_REF -> ConstantMethodrefInfo(
                inputStream.readUnsignedShort(),
                inputStream.readUnsignedShort()
            )
            ConstantTags.CONSTANT_INTERFACE_METHOD_REF -> ConstantInterfaceMethodrefInfo(
                inputStream.readUnsignedShort(),
                inputStream.readUnsignedShort()
            )
            ConstantTags.CONSTANT_NAME_AND_TYPE -> ConstantNameAndTypeInfo(
                inputStream.readUnsignedShort(),
                inputStream.readUnsignedShort()
            )
            ConstantTags.CONSTANT_METHOD_HANDLE -> ConstantMethodHandleInfo(
                inputStream.read().toByte(),
                inputStream.readUnsignedShort()
            )
            ConstantTags.CONSTANT_METHODTYPE -> ConstantMethodTypeInfo(inputStream.readUnsignedShort())
            17.toByte() -> Dynamic(inputStream.readInt()) // TODO ??
            ConstantTags.CONSTANT_INVOKE_DYNAMIC -> ConstantInvokeDynamicInfo(inputStream.readNBytes(4))
            19.toByte() -> IdentifyModule(inputStream.readShort()) // TODO ??
            20.toByte() -> IdentifyPackage(inputStream.readShort()) // TODO ??
            else -> throw Error("Unknown tag: 0x${tag.toString(16)} at 0x${(counter.getCount()-1).toString(16)}")
        }
    }

    fun expectField(): FieldInfo {
        val accessFlags = inputStream.readUnsignedShort().toInt()
        val nameIndex = inputStream.readUnsignedShort().toInt()
        val descriptorIndex = inputStream.readUnsignedShort().toInt()
        val attributesCount = inputStream.readUnsignedShort().toInt()
        val attributes = Array(attributesCount) { expectAttribute() }
        return FieldInfo(accessFlags, nameIndex, descriptorIndex, attributes)
    }

    fun expectMethod(): MethodInfo {
        val accessFlags = inputStream.readUnsignedShort().toInt()
        val nameIndex = inputStream.readUnsignedShort().toInt()
        val descriptorIndex = inputStream.readUnsignedShort().toInt()
        val attributesCount = inputStream.readUnsignedShort().toInt()
        val attributes = Array(attributesCount) { expectAttribute() }
        return MethodInfo(nameIndex, descriptorIndex, accessFlags, attributes)
    }

    fun expectAttribute(): AttributeInfo {
        val attributeNameIndex = inputStream.readUnsignedShort().toInt()
        val attributeLength = inputStream.readInt()
        val attributeData = inputStream.readNBytes(attributeLength)
        return AttributeInfo(attributeNameIndex, attributeData)
    }


    private fun resolveJavaVersion(v: Int): String {
        if (v in 0x2D..0x30) return "JDK 1." + (v - 0x2D)
        if (v in 0x31..0x32) return "Java SE " + (v - 0x31) + ".0"
        if (v in 0x33..0x3D) return "Java SE " + (v - 0x33)
        throw Error("Unknown java version")
    }
}


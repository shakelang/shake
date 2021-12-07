package io.github.shakelang.jvmlib

import io.github.shakelang.jvmlib.constants.*
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
    private lateinit var constantPool: Array<JavaClassConstant>
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

    fun process() {
        magic = inputStream.readUnsignedInt()
        println("Magic: 0x${magic.toString(16)}")
        minorVersion = inputStream.readUnsignedShort().toInt()
        println("Minor version: $minorVersion")
        majorVersion = inputStream.readUnsignedShort().toInt()
        println("Major version: $majorVersion")
        constantPoolCount = (inputStream.readUnsignedShort()).toInt()-1
        println("Constant pool count: $constantPoolCount")
        constantPool = Array(constantPoolCount) { println(it);expectConstant() }
        println("Constant pool: ${json.stringify(constantPool.map{it.toJson()})}")
        accessFlags = inputStream.readUnsignedShort().toInt()
        println("Access flags: 0x${accessFlags.toString(16)}")
        thisClass = inputStream.readUnsignedShort().toInt()
        println("This class: ${json.stringify(constantPool[thisClass].toJson())}")
        superClass = inputStream.readUnsignedShort().toInt()
        println("Super class: ${json.stringify(constantPool[superClass].toJson())}")
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
            println(it)
            expectMethod()
        }
        //println("Methods: ${json.stringify(methods.map { it.toJson() })}")
        attributesCount = inputStream.readUnsignedShort().toInt()
        //println("Attributes count: $attributesCount")
        attributes = Array(attributesCount) { expectAttribute() }
        //println("Attributes: ${json.stringify(attributes.map { it.toJson() })}")
    }

    fun expectConstant(): JavaClassConstant {
        val tag = inputStream.read()
//        println("received tag: $tag (Address: 0x${(inputStream.position-1).toString(16)})")
        return if (tag == 1) {
            val length = (inputStream.read() shl 8) + inputStream.read()
            val sb = StringBuilder()
            for (c in 0 until length) sb.append(inputStream.read().toChar())
            JavaClassStringConstant(sb.toString())
        } else {
            when (tag) {
                3 -> JavaClassIntegerConstant(inputStream.readInt())
                4 -> JavaClassFloatConstant(inputStream.readFloat())
                5 -> JavaClassLongConstant(inputStream.readLong())
                6 -> JavaClassDoubleConstant(inputStream.readDouble())
                7 -> ClassReference(inputStream.readUnsignedShort().toInt())
                8 -> StringReference(inputStream.readUnsignedShort().toInt())
                9 -> FieldReference(
                        inputStream.readUnsignedShort().toInt(),
                        inputStream.readUnsignedShort().toInt()
                    )
                10 -> MethodReference(
                        inputStream.readUnsignedShort().toInt(),
                        inputStream.readUnsignedShort().toInt()
                    )
                11 -> InterfaceMethodReference(
                        inputStream.readUnsignedShort().toInt(),
                        inputStream.readUnsignedShort().toInt()
                    )
                12 -> NameAndTypeDescriptor(
                        inputStream.readUnsignedShort().toInt(),
                        inputStream.readUnsignedShort().toInt()
                    )
                15 -> MethodHandleDescriptor(
                        inputStream.read().toByte(),
                        inputStream.readUnsignedShort().toInt()
                    )
                16 ->
                    MethodTypeReference(inputStream.readUnsignedShort().toInt())
                17 -> Dynamic(inputStream.readInt().toInt()) // TODO ??
                18 -> InvokeDynamic(inputStream.readNBytes(4))
                19 -> IdentifyModule(inputStream.readShort()) // TODO ??
                20 -> IdentifyPackage(inputStream.readShort()) // TODO ??
                else -> throw Error("Unknown tag: 0x${tag.toString(16)} at 0x${(counter.getCount()-1).toString(16)}")
            }
        }
    }

    fun expectField(): FieldInfo {
        val accessFlags = inputStream.readUnsignedShort().toInt()
        val nameIndex = inputStream.readUnsignedShort().toInt()
        val descriptorIndex = inputStream.readUnsignedShort().toInt()
        val attributesCount = inputStream.readUnsignedShort().toInt()
        val attributes = Array(attributesCount) { inputStream.readUnsignedShort().toInt() }
        return FieldInfo(accessFlags, nameIndex, descriptorIndex, attributes)
    }

    fun expectMethod(): MethodInfo {
        val accessFlags = inputStream.readUnsignedShort().toInt()
        val nameIndex = inputStream.readUnsignedShort().toInt()
        val descriptorIndex = inputStream.readUnsignedShort().toInt()
        val attributesCount = inputStream.readUnsignedShort().toInt()
                //println("Attributes index: 0x${inputStream.}")
        println(attributesCount)
        val attributes = Array(attributesCount) { inputStream.readUnsignedShort().toInt() }
        return MethodInfo(accessFlags, nameIndex, descriptorIndex, attributes)
    }

    fun expectAttribute(): AttributeInfo {
        val attributeNameIndex = inputStream.readUnsignedShort().toInt()
        val attributeLength = inputStream.readInt()
        val attributeData = inputStream.readNBytes(attributeLength)
        return AttributeInfo(attributeNameIndex, attributeData)
    }


    /*
    @Throws(IOException::class)
    fun process() {

        // Default class file start (This identifies the class-file as a class-file. If the
        // stream does not start with these bytes we will throw an Error)
        if (inputStream.read() != 0xCA || inputStream.read() != 0xFE || inputStream.read() != 0xBA || inputStream.read() != 0xBE)
            throw Error("File seems not to be a class file!")

        // Minor class version number
        val minorVersion = (inputStream.read() shl 8) + inputStream.read()

        // Java version number
        val majorVersion = (inputStream.read() shl 8) + inputStream.read()

        // constant pool count
        val constantPoolSize = (inputStream.read() shl 8) + inputStream.read() - 1
        val constantPool = arrayOfNulls<JavaClassConstant>(constantPoolSize)
        println("class-version $minorVersion, java-version $majorVersion, const-pool-size $constantPoolSize")

        for (i in 0 until constantPoolSize) {
            val tag = inputStream.read()
            if (tag == 1) {
                val length = (inputStream.read() shl 8) + inputStream.read()
                val sb = StringBuilder()
                for (c in 0 until length) sb.append(inputStream.read().toChar())
                constantPool[i] = JavaClassStringConstant(constantPool, sb.toString())
            } else {
                when (tag) {
                    3 -> constantPool[i] =
                        JavaClassIntegerConstant(constantPool,ByteBuffer.wrap(readNBytes(inputStream, 4)).int)
                    4 -> constantPool[i] =
                        JavaClassFloatConstant(constantPool,ByteBuffer.wrap(readNBytes(inputStream, 4)).float)
                    5 -> constantPool[i] =
                        JavaClassLongConstant(constantPool,ByteBuffer.wrap(readNBytes(inputStream, 8)).long)
                    6 -> constantPool[i] =
                        JavaClassDoubleConstant(constantPool,ByteBuffer.wrap(readNBytes(inputStream, 8)).double)
                    7 -> constantPool[i] =
                        ClassReference(constantPool,getUnsignedShort(ByteBuffer.wrap(readNBytes(inputStream, 2))))
                    8 -> constantPool[i] =
                        StringReference(constantPool,getUnsignedShort(ByteBuffer.wrap(readNBytes(inputStream, 2))))
                    9 -> constantPool[i] = FieldReference(constantPool,
                        getUnsignedShort(ByteBuffer.wrap(readNBytes(inputStream, 2))),
                        getUnsignedShort(ByteBuffer.wrap(readNBytes(inputStream, 2)))
                    )
                    10 -> constantPool[i] = MethodReference(constantPool,
                        getUnsignedShort(ByteBuffer.wrap(readNBytes(inputStream, 2))),
                        getUnsignedShort(ByteBuffer.wrap(readNBytes(inputStream, 2)))
                    )
                    11 -> constantPool[i] = InterfaceMethodReference(constantPool,
                        getUnsignedShort(ByteBuffer.wrap(readNBytes(inputStream, 2))),
                        getUnsignedShort(ByteBuffer.wrap(readNBytes(inputStream, 2)))
                    )
                    12 -> constantPool[i] = NameAndTypeDescriptor(constantPool,
                        getUnsignedShort(ByteBuffer.wrap(readNBytes(inputStream, 2))),
                        getUnsignedShort(ByteBuffer.wrap(readNBytes(inputStream, 2)))
                    )
                    15 -> constantPool[i] = MethodHandleDescriptor(constantPool,
                        inputStream.read().toByte(),
                        getUnsignedShort(ByteBuffer.wrap(readNBytes(inputStream, 2)))
                    )
                    16 -> constantPool[i] =
                        MethodTypeReference(constantPool,
                            getUnsignedShort(
                                ByteBuffer.wrap(
                                    readNBytes(
                                        inputStream,
                                        2
                                    )
                                )
                            )
                        )
                    17 -> constantPool[i] = Dynamic(constantPool, ByteBuffer.wrap(readNBytes(inputStream, 4)).int)
                    18 -> constantPool[i] = InvokeDynamic(constantPool, readNBytes(inputStream, 4))
                    19 -> constantPool[i] =
                        IdentifyModule(constantPool, ByteBuffer.wrap(readNBytes(inputStream, 2)).short)
                    20 -> constantPool[i] =
                        IdentifyPackage(constantPool, ByteBuffer.wrap(readNBytes(inputStream, 2)).short)
                }
            }
        }

        println("Constant pool: ${json.stringify(constantPool.map{it!!.toJson()})}")

        val accessFlags = (inputStream.read() shl 8) + inputStream.read()
        println("Access Flags: 0x${accessFlags.toString(16)}")

        val classEntry = (inputStream.read() shl 8) + inputStream.read()-1
        val classConstant = constantPool[classEntry] as ClassReference
        val className = classConstant.classValue.value

        println("Class: 0x${classEntry.toString(16)}: $className")

        val superEntry = (inputStream.read() shl 8) + inputStream.read()-1
        val superConstant = constantPool[superEntry] as ClassReference
        val superName = superConstant.classValue.value
        println("Superclass: 0x${superEntry.toString(16)}: $superName")

        val interfaceCount = (inputStream.read() shl 8) + inputStream.read()
        println("Interface Count: $interfaceCount")

        val interfaces = arrayOfNulls<String>(interfaceCount)

        // parse interfaces
        for(i in 0 until interfaceCount) {
            interfaces[i] = (constantPool[(inputStream.read() shl 8) + inputStream.read() - 1]!! as ClassReference).classValue.value
        }


        val fieldCount = (inputStream.read() shl 8) + inputStream.read()
        println("position: 0x${inputStream.positionIdentifier}")
        println("Field Count: $fieldCount")

        val fields = arrayOfNulls<FieldInfo>(fieldCount)

        // parse interfaces
        for(i in 0 until fieldCount) {
            fields[i] = expectField()
            //println(constantPool[fields[i]!!])
        }


        val methodCount = (inputStream.read() shl 8) + inputStream.read()
        println("Method Count: $methodCount")

        val methods = arrayOfNulls<MethodInfo>(methodCount)

        // parse interfaces
        for(i in 0 until methodCount) {
            methods[i] = expectMethod()
        }


        val attributeCount = (inputStream.read() shl 8) + inputStream.read()
        println("Attribute Count: $attributeCount")

        val attributes = arrayOfNulls<AttributeInfo>(attributeCount)

        // parse interfaces
        for(i in 0 until attributeCount) {
            attributes[i] = expectAttribute()
        }

        println(ClassFile(
            minorVersion,
            majorVersion,
            accessFlags,
            className,
            superName,
            interfaces as Array<String>,
            fields.map { it.toString() }.toTypedArray(),
            arrayOf(),
            arrayOf()
        ))
    }

    private fun expectField(): FieldInfo {
        val accessFlags = (inputStream.read() shl 8) + inputStream.read()
        val nameIndex = (inputStream.read() shl 8) + inputStream.read()-1
        val descriptorIndex = (inputStream.read() shl 8) + inputStream.read()-1
        val attributeCount = (inputStream.read() shl 8) + inputStream.read()
        val attributes = arrayOfNulls<Int>(attributeCount)
        for(i in 0 until attributeCount) {
            attributes[i] = (inputStream.read() shl 8) + inputStream.read() - 1
        }
        return FieldInfo(
            accessFlags,
            nameIndex,
            descriptorIndex,
            attributes as Array<Int>
        )
    }

    private fun expectMethod(): MethodInfo {
        val accessFlags = (inputStream.read() shl 8) + inputStream.read()
        val nameIndex = (inputStream.read() shl 8) + inputStream.read()-1
        val descriptorIndex = (inputStream.read() shl 8) + inputStream.read()-1
        val attributeCount = (inputStream.read() shl 8) + inputStream.read()
        val attributes = arrayOfNulls<Int>(attributeCount)
        for(i in 0 until attributeCount) {
            attributes[i] = (inputStream.read() shl 8) + inputStream.read() - 1
        }
        return MethodInfo(
            accessFlags,
            nameIndex,
            descriptorIndex,
            attributes as Array<Int>
        )
    }

    private fun expectAttribute(): AttributeInfo {
        val attributeNameIndex = (inputStream.read() shl 8) + inputStream.read()-1
        val attributeLength = (inputStream.read() shl 24) + (inputStream.read() shl 16) + (inputStream.read() shl 8) + inputStream.read()
        val attributeValue = readNBytes(inputStream, attributeLength)
        return AttributeInfo(
            attributeNameIndex,
            attributeLength,
            attributeValue
        )
    }



    private fun getUnsignedShort(buffer: ByteBuffer): Int {
        return asUnsignedShort(buffer.short)
    }

    private fun asUnsignedShort(s: Short): Int {
        return s.toInt() and 0xFFFF
    }

    @Throws(IOException::class)
    private fun readNBytes(input: InputStream, amount: Int): ByteArray {
        val bytes = ByteArray(amount)
        for (i in 0 until amount) bytes[i] = input.read().toByte()
        return bytes
    }

    private fun resolveJavaVersion(v: Int): String {
        if (v in 0x2D..0x30) return "JDK 1." + (v - 0x2D)
        if (v in 0x31..0x32) return "Java SE " + (v - 0x31) + ".0"
        if (v in 0x33..0x3D) return "Java SE " + (v - 0x33)
        throw Error("Unknown java version")
    }*/
/*
    private fun getUnsignedShort(buffer: ByteBuffer): Int {
        //println(Arrays.toString(buffer.array()))
        //println(inputStream.)
        return asUnsignedShort(buffer.short)
    }

    private fun asUnsignedShort(s: Short): Int {
        return s.toInt() and 0xFFFF
    }

    @Throws(IOException::class)
    private fun InputStream.readNBytes(amount: Int): ByteArray {
        val bytes = ByteArray(amount)
        for (i in 0 until amount) bytes[i] = this.read().toByte()
        return bytes
    }

    private fun InputStream.readInt(): Int = (read() shl 24) + (read() shl 16) + (read() shl 8) + read()
    private fun InputStream.readShort(): Int = (read() shl 8) + read()
    private fun InputStream.readUnsignedShort(): Int = asUnsignedShort(readShort().toShort())
*/


    private fun resolveJavaVersion(v: Int): String {
        if (v in 0x2D..0x30) return "JDK 1." + (v - 0x2D)
        if (v in 0x31..0x32) return "Java SE " + (v - 0x31) + ".0"
        if (v in 0x33..0x3D) return "Java SE " + (v - 0x33)
        throw Error("Unknown java version")
    }
}


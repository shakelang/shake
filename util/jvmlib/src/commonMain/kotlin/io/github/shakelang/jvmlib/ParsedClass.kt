package io.github.shakelang.jvmlib

import io.github.shakelang.jvmlib.constants.ConstantClassInfo
import io.github.shakelang.jvmlib.constants.ConstantPool
import io.github.shakelang.jvmlib.constants.ConstantUtf8Info
import io.github.shakelang.jvmlib.info.AttributeInfo
import io.github.shakelang.jvmlib.info.FieldInfo
import io.github.shakelang.jvmlib.info.MethodInfo
import io.github.shakelang.shason.json

class ClassFile (
    val minorVersion: Int,
    val majorVersion: Int,
    val constantPool: ConstantPool,
    val accessFlags: Int,
    val thisClass: Int,
    val superClass: Int,
    val interfaces: Array<Int>,
    val fieldInfos: Array<FieldInfo>,
    val methodInfos: Array<MethodInfo>,
    val attributeInfos: Array<AttributeInfo>
) {
    val className: String get() = classNameEntry.value
    val superClassName: String? get() = superClassNameEntry?.value
    val interfaceNames: Array<String> get() = interfacesNamesEntries.map { it.value }.toTypedArray()

    val fields: List<FieldContents> get() = fieldInfos.indices.map { FieldContents(it) }
    val methods: List<MethodContents> get() = methodInfos.indices.map { MethodContents(it) }
    val attributes: List<AttributeContents> get() = attributes.indices.map { AttributeContents(it) }

    val classEntry: ConstantClassInfo
        get() = constantPool[thisClass] as ConstantClassInfo

    val classNameEntry: ConstantUtf8Info
        get() = classEntry.getValue(constantPool)

    val superClassEntry: ConstantClassInfo?
        get() = if (superClass == 0) null else constantPool[superClass] as ConstantClassInfo

    val superClassNameEntry: ConstantUtf8Info?
        get() = superClassEntry?.getValue(constantPool)

    val interfacesEntries: Array<ConstantClassInfo>
        get() = interfaces.map { constantPool[it] as ConstantClassInfo }.toTypedArray()

    val interfacesNamesEntries: Array<ConstantUtf8Info>
        get() = interfacesEntries.map { it.getValue(constantPool) }.toTypedArray()


    fun toJson() = mapOf(
        "minorVersion" to "0x${minorVersion.toString(16)}",
        "majorVersion" to "0x${majorVersion.toString(16)}",
        "constantPool" to constantPool.toJson(),
        "accessFlags" to "0x${accessFlags.toString(16)}",
        "thisClass" to thisClass,
        "superClass" to superClass,
        "interfaces" to interfaces.toList(),
        "fields" to fieldInfos.map { it.toJson() },
        "methods" to methodInfos.map { it.toJson() },
        "attributes" to attributeInfos.map { it.toJson() }
    )

    override fun toString(): String {
        return json.stringify(toJson())
    }

    inner class FieldContents(val index: Int) {
        val name: String get() = (this@ClassFile.constantPool[this@ClassFile.fieldInfos[index].name_index] as ConstantUtf8Info).value
        val descriptor: String get() = (this@ClassFile.constantPool[this@ClassFile.fieldInfos[index].descriptor_index] as ConstantUtf8Info).value
        val attributes: Array<AttributeInfo> get() = this@ClassFile.fieldInfos[index].attributes

        fun toJson() = mapOf(
            "name" to name,
            "descriptor" to descriptor,
            "attributes" to attributes.map { it.toJson() }
        )

        override fun toString(): String {
            return json.stringify(toJson())
        }
    }

    inner class MethodContents(val index: Int) {

        val name: String get() = (this@ClassFile.constantPool[this@ClassFile.methodInfos[index].name] as ConstantUtf8Info).value
        val descriptor: String get() = (this@ClassFile.constantPool[this@ClassFile.methodInfos[index].descriptor] as ConstantUtf8Info).value
        val attributes: Array<AttributeInfo> get() = this@ClassFile.methodInfos[index].attributes

        fun toJson() = mapOf(
            "name" to name,
            "descriptor" to descriptor,
            "attributes" to attributes.map { it.toJson() }
        )

        override fun toString(): String {
            return json.stringify(toJson())
        }
    }

    inner class AttributeContents(val index: Int) {
        val name: String get() = (this@ClassFile.constantPool[this@ClassFile.attributeInfos[index].attributeNameIndex] as ConstantUtf8Info).value
        val info: ByteArray get() = this@ClassFile.attributeInfos[index].info

        fun toJson() = mapOf(
            "name" to name,
            "info" to info.map { "0x${it.toString(16)}" }
        )

        override fun toString(): String {
            return json.stringify(toJson())
        }
    }

}

class ParsedMethod {

}

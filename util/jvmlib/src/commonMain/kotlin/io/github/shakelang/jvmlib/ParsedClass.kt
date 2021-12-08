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
    val fields: Array<FieldInfo>,
    val methods: Array<MethodInfo>,
    val attributes: Array<AttributeInfo>
) {

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
        "thisClass" to "0x${thisClass.toString(16)}",
        "superClass" to "0x${superClass.toString(16)}",
        "interfaces" to interfaces.map { "0x${it.toString(16)}" },
        "fields" to fields.map { it.toJson() },
        "methods" to methods.map { it.toJson() },
        "attributes" to attributes.map { it.toJson() }
    )

    override fun toString(): String {
        return json.stringify(toJson())
    }

    inner class ClassContents {
        val className: String get() = classNameEntry.value
        val superClassName: String? get() = superClassNameEntry?.value
        val interfacesNames: Array<String> get() = interfacesNamesEntries.map { it.value }.toTypedArray()

        val fields: List<FieldContents> get() = this@ClassFile.fields.indices.map { FieldContents(it) }
        val methods: List<MethodContents> get() = this@ClassFile.methods.indices.map { MethodContents(it) }
        val attributes: List<AttributeContents> get() = this@ClassFile.attributes.indices.map { AttributeContents(it) }

        fun toJson() = mapOf(
            "className" to className,
            "superClassName" to superClassName,
            "interfacesNames" to interfacesNames
        )

        override fun toString(): String {
            return json.stringify(toJson())
        }

        inner class FieldContents(val index: Int) {
            val name: String get() = (this@ClassFile.constantPool[this@ClassFile.fields[index].name_index] as ConstantUtf8Info).value
            val descriptor: String get() = (this@ClassFile.constantPool[this@ClassFile.fields[index].descriptor_index] as ConstantUtf8Info).value
            val attributes: Array<AttributeInfo> get() = this@ClassFile.fields[index].attributes

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

            val name: String get() = (this@ClassFile.constantPool[this@ClassFile.methods[index].name] as ConstantUtf8Info).value
            val descriptor: String get() = (this@ClassFile.constantPool[this@ClassFile.methods[index].descriptor] as ConstantUtf8Info).value
            val attributes: Array<AttributeInfo> get() = this@ClassFile.methods[index].attributes

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
            val name: String get() = (this@ClassFile.constantPool[this@ClassFile.attributes[index].attributeNameIndex] as ConstantUtf8Info).value
            val info: ByteArray get() = this@ClassFile.attributes[index].info

            fun toJson() = mapOf(
                "name" to name,
                "info" to info.map { "0x${it.toString(16)}" }
            )

            override fun toString(): String {
                return json.stringify(toJson())
            }
        }
    }

}

class ParsedMethod {

}

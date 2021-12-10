package io.github.shakelang.jvmlib

import io.github.shakelang.jvmlib.infos.constants.ConstantClassInfo
import io.github.shakelang.jvmlib.infos.constants.ConstantPool
import io.github.shakelang.jvmlib.infos.constants.ConstantUtf8Info
import io.github.shakelang.jvmlib.infos.AttributeInfo
import io.github.shakelang.jvmlib.infos.FieldInfo
import io.github.shakelang.jvmlib.infos.MethodInfo
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

    val isPublic: Boolean
        get() = accessFlags and 0x0001 != 0

    val isFinal: Boolean
        get() = accessFlags and 0x0010 != 0

    val isSuper: Boolean
        get() = accessFlags and 0x0020 != 0

    val isInterface: Boolean
        get() = accessFlags and 0x0200 != 0

    val isAbstract: Boolean
        get() = accessFlags and 0x0400 != 0

    val isSynthetic: Boolean
        get() = accessFlags and 0x1000 != 0

    val isAnnotation: Boolean
        get() = accessFlags and 0x2000 != 0

    val isEnum: Boolean
        get() = accessFlags and 0x4000 != 0


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
        val accessFlags: Int get() = fieldInfos[index].access_flags
        val name: String get() = (this@ClassFile.constantPool[this@ClassFile.fieldInfos[index].name_index] as ConstantUtf8Info).value
        val descriptor: String get() = (this@ClassFile.constantPool[this@ClassFile.fieldInfos[index].descriptor_index] as ConstantUtf8Info).value
        val attributes: Array<AttributeInfo> get() = this@ClassFile.fieldInfos[index].attributes

        val isPublic: Boolean
            get() = accessFlags and 0x0001 != 0

        val isPrivate: Boolean
            get() = accessFlags and 0x0002 != 0

        val isProtected: Boolean
            get() = accessFlags and 0x0004 != 0

        val isStatic: Boolean
            get() = accessFlags and 0x0008 != 0

        val isFinal: Boolean
            get() = accessFlags and 0x0010 != 0

        val isVolatile: Boolean
            get() = accessFlags and 0x0040 != 0

        val isTransient: Boolean
            get() = accessFlags and 0x0080 != 0

        val isSynthetic: Boolean
            get() = accessFlags and 0x1000 != 0

        val isEnum: Boolean
            get() = accessFlags and 0x4000 != 0

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

        val accessFlags: Int get() = methodInfos[index].accessFlags
        val name: String get() = (this@ClassFile.constantPool[this@ClassFile.methodInfos[index].name] as ConstantUtf8Info).value
        val descriptor: String get() = (this@ClassFile.constantPool[this@ClassFile.methodInfos[index].descriptor] as ConstantUtf8Info).value
        val attributes: Array<AttributeInfo> get() = this@ClassFile.methodInfos[index].attributes

        val isPublic: Boolean
            get() = accessFlags and 0x0001 != 0

        val isPrivate: Boolean
            get() = accessFlags and 0x0002 != 0

        val isProtected: Boolean
            get() = accessFlags and 0x0004 != 0

        val isStatic: Boolean
            get() = accessFlags and 0x0008 != 0

        val isFinal: Boolean
            get() = accessFlags and 0x0010 != 0

        val isSynchronized: Boolean
            get() = accessFlags and 0x0020 != 0

        val isBridge: Boolean
            get() = accessFlags and 0x0040 != 0

        val isVarargs: Boolean
            get() = accessFlags and 0x0080 != 0

        val isNative: Boolean
            get() = accessFlags and 0x0100 != 0

        val isAbstract: Boolean
            get() = accessFlags and 0x0400 != 0

        val isStrict: Boolean
            get() = accessFlags and 0x0800 != 0

        val isSynthetic: Boolean
            get() = accessFlags and 0x1000 != 0

        val isAnnotation: Boolean
            get() = accessFlags and 0x2000 != 0

        val isEnum: Boolean
            get() = accessFlags and 0x4000 != 0

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

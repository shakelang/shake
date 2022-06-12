package io.github.shakelang.shake.processor.map

import io.github.shakelang.parseutils.bytes.toBytes
import io.github.shakelang.parseutils.streaming.input.ByteArrayInputStream
import io.github.shakelang.parseutils.streaming.input.DataInputStream
import io.github.shakelang.parseutils.streaming.input.InputStream
import io.github.shakelang.parseutils.streaming.output.ByteArrayOutputStream
import io.github.shakelang.parseutils.streaming.output.OutputStream
import io.github.shakelang.shake.processor.program.types.*

class ShakeMap(

    val constants: Array<ShakeMapConstant>,
    val packages: Array<ShakeMapPackage>,
    val classes: Array<ShakeMapClass>,
    val methods: Array<ShakeMapMethod>,
    val fields: Array<ShakeMapField>,
    val project_packages: Array<Int>,
    val project_classes: Array<Int>,
    val project_methods: Array<Int>,
    val project_fields: Array<Int>,

) {

    val magic = 0x3082d75f
    val version = 0x00000001

    fun write(output: OutputStream) {
        output.write(magic.toBytes())
        output.write(version.toBytes())
        output.write(constants.size.toBytes())
        constants.forEach { it.write(output) }
        output.write(packages.size.toBytes())
        packages.forEach { it.write(output) }
        output.write(classes.size.toBytes())
        classes.forEach { it.write(output) }
        output.write(methods.size.toBytes())
        methods.forEach { it.write(output) }
        output.write(fields.size.toBytes())
        fields.forEach { it.write(output) }
        output.write(project_packages.size.toBytes())
        project_packages.forEach { output.write(it.toBytes()) }
        output.write(project_classes.size.toBytes())
        project_classes.forEach { output.write(it.toBytes()) }
        output.write(project_methods.size.toBytes())
        project_methods.forEach { output.write(it.toBytes()) }
        output.write(project_fields.size.toBytes())
        project_fields.forEach { output.write(it.toBytes()) }
    }

    fun getBytes(): ByteArray {
        val output = ByteArrayOutputStream()
        write(output)
        return output.toByteArray()
    }

    fun toJson(): Map<String, *> = mapOf(
        "magic" to magic,
        "version" to version,
        "constants" to constants.map { it.toJson() },
        "packages" to packages.map { it.toJson() },
        "classes" to classes.map { it.toJson() },
        "methods" to methods.map { it.toJson() },
        "fields" to fields.map { it.toJson() },
        "project_packages" to project_packages.map { it },
        "project_classes" to project_classes.map { it },
        "project_methods" to project_methods.map { it },
        "project_fields" to project_fields.map { it }
    )

    companion object {
        fun from(project: ShakeProject): ShakeMap {
            val creator = ShakeMapCreator()
            creator.visit(project)
            return creator.export()
        }

        fun fromBytes(bytes: ByteArray): ShakeMap {
            val input = ByteArrayInputStream(bytes)
            return from(input)
        }

        fun from(input: DataInputStream): ShakeMap {
            val magic = input.readInt()
            val version = input.readInt()
            val constants = input.readInt()
            val constants_ = Array(constants) { ShakeMapConstant.from(input) }
            val packages = input.readInt()
            val packages_ = Array(packages) { ShakeMapPackage.from(input) }
            val classes = input.readInt()
            val classes_ = Array(classes) { ShakeMapClass.from(input) }
            val methods = input.readInt()
            val methods_ = Array(methods) { ShakeMapMethod.from(input) }
            val fields = input.readInt()
            val fields_ = Array(fields) { ShakeMapField.from(input) }
            val project_packages = input.readInt()
            val project_packages_ = Array(project_packages) { input.readInt() }
            val project_classes = input.readInt()
            val project_classes_ = Array(project_classes) { input.readInt() }
            val project_methods = input.readInt()
            val project_methods_ = Array(project_methods) { input.readInt() }
            val project_fields = input.readInt()
            val project_fields_ = Array(project_fields) { input.readInt() }
            return ShakeMap(
                constants_,
                packages_,
                classes_,
                methods_,
                fields_,
                project_packages_,
                project_classes_,
                project_methods_,
                project_fields_
            )
        }

        fun from(input: InputStream): ShakeMap {
            return from(DataInputStream(input))
        }

        fun from(bytes: ByteArray) = from(ByteArrayInputStream(bytes))

    }

}


interface ShakeMapConstant {
    val type: ShakeClassConstantTag

    fun getBytes(): ByteArray
    fun write(stream: OutputStream) = stream.write(getBytes())
    fun toJson(): Map<String, *>


    enum class ShakeClassConstantTag(val TAG: Byte) {
        UTF8(0x01),
        ;
    }

    class ShakeUtf8Constant(val value: String) : ShakeMapConstant {
        override val type = ShakeClassConstantTag.UTF8
        override fun getBytes(): ByteArray {
            val stream = ByteArrayOutputStream()
            write(stream)
            return stream.toByteArray()
        }

        override fun write(stream: OutputStream) {
            stream.write(type.TAG.toBytes())
            stream.write(value.length.toBytes())
            stream.write(value.toBytes())
        }

        override fun toJson(): Map<String, *> = mapOf(
            "type" to type.TAG,
            "value" to value
        )

        companion object {
            fun from(input: DataInputStream): ShakeMapConstant {
                val length = input.readInt()
                val chars = input.readUTF(length)
                return ShakeUtf8Constant(chars)
            }
        }
    }

    companion object {
        fun utf8(value: String): ShakeUtf8Constant {
            return ShakeUtf8Constant(value)
        }

        fun from(input: DataInputStream): ShakeMapConstant {
            val type = input.readByte()
            when (type) {
                0x01.toByte() -> return ShakeUtf8Constant.from(input)
                else -> throw IllegalArgumentException("Unknown constant type: $type")
            }
        }
    }
}
class ShakeMapPackage(
    val name: Int,
    val package_references: Array<Int>,
    val class_references: Array<Int>,
    val method_references: Array<Int>,
    val field_references: Array<Int>,
) {
    fun getBytes(): ByteArray {
        val stream = ByteArrayOutputStream()
        write(stream)
        return stream.toByteArray()
    }

    fun write(stream: OutputStream) {
        stream.write(name.toBytes())
        stream.write(package_references.size.toBytes())
        package_references.forEach { stream.write(it.toBytes()) }
        stream.write(class_references.size.toBytes())
        class_references.forEach { stream.write(it.toBytes()) }
        stream.write(method_references.size.toBytes())
        method_references.forEach { stream.write(it.toBytes()) }
        stream.write(field_references.size.toBytes())
        field_references.forEach { stream.write(it.toBytes()) }
    }

    fun toJson(): Map<String, *> = mapOf(
        "name" to name,
        "package_references" to package_references.map { it },
        "class_references" to class_references.map { it },
        "method_references" to method_references.map { it },
        "field_references" to field_references.map { it }
    )

    companion object {
        fun from(input: DataInputStream): ShakeMapPackage {
            val name = input.readInt()
            val package_references = input.readInt()
            val package_references_ = Array(package_references) { input.readInt() }
            val class_references = input.readInt()
            val class_references_ = Array(class_references) { input.readInt() }
            val method_references = input.readInt()
            val method_references_ = Array(method_references) { input.readInt() }
            val field_references = input.readInt()
            val field_references_ = Array(field_references) { input.readInt() }
            return ShakeMapPackage(
                name,
                package_references_,
                class_references_,
                method_references_,
                field_references_
            )
        }
    }
}

class ShakeMapClass(
    val name: Int,
    val attributes: Byte,
    val super_class: Int,
    val interface_references: Array<Int>,
    val subclass_references: Array<Int>,
    val method_references: Array<Int>,
    val field_references: Array<Int>
) {
    val isStatic = attributes.bit7
    val isFinal = attributes.bit6
    val isPublic = attributes.bit4
    val isProtected = attributes.bit3
    val isPrivate = attributes.bit2
    val isAbstract = attributes.bit1

    fun getBytes(): ByteArray {
        val stream = ByteArrayOutputStream()
        write(stream)
        return stream.toByteArray()
    }

    fun write(stream: OutputStream) {
        stream.write(name.toBytes())
        stream.write(attributes.toBytes())
        stream.write(super_class.toBytes())
        stream.write(interface_references.size.toBytes())
        interface_references.forEach { stream.write(it.toBytes()) }
        stream.write(subclass_references.size.toBytes())
        subclass_references.forEach { stream.write(it.toBytes()) }
        stream.write(method_references.size.toBytes())
        method_references.forEach { stream.write(it.toBytes()) }
        stream.write(field_references.size.toBytes())
        field_references.forEach { stream.write(it.toBytes()) }
    }

    fun toJson(): Map<String, *> = mapOf(
        "name" to name,
        "attributes" to attributes,
        "super_class" to super_class,
        "interface_references" to interface_references.map { it },
        "subclass_references" to subclass_references.map { it },
        "method_references" to method_references.map { it },
        "field_references" to field_references.map { it }
    )

    companion object {
        fun from(input: DataInputStream): ShakeMapClass {
            val name = input.readInt()
            val attributes = input.readByte()
            val super_class = input.readInt()
            val interface_references = input.readInt()
            val interface_references_ = Array(interface_references) { input.readInt() }
            val subclass_references = input.readInt()
            val subclass_references_ = Array(subclass_references) { input.readInt() }
            val method_references = input.readInt()
            val method_references_ = Array(method_references) { input.readInt() }
            val field_references = input.readInt()
            val field_references_ = Array(field_references) { input.readInt() }
            return ShakeMapClass(
                name,
                attributes,
                super_class,
                interface_references_,
                subclass_references_,
                method_references_,
                field_references_
            )
        }
    }
}

class ShakeMapMethod(
    val name: Int,
    val attributes: Byte,
    val return_type: Int,
    val parameter_names: Array<Int>,
    val parameter_types: Array<Int>,
) {
    val isStatic = attributes.bit7
    val isFinal = attributes.bit6
    val isPublic = attributes.bit4
    val isProtected = attributes.bit3
    val isPrivate = attributes.bit2
    val isAbstract = attributes.bit1

    fun getBytes(): ByteArray {
        val stream = ByteArrayOutputStream()
        write(stream)
        return stream.toByteArray()
    }

    fun write(stream: OutputStream) {
        stream.write(name.toBytes())
        stream.write(attributes.toBytes())
        stream.write(return_type.toBytes())
        stream.write(parameter_names.size.toBytes())
        parameter_names.forEach { stream.write(it.toBytes()) }
        stream.write(parameter_types.size.toBytes())
        parameter_types.forEach { stream.write(it.toBytes()) }
    }

    fun toJson(): Map<String, *> = mapOf(
        "name" to name,
        "attributes" to attributes,
        "return_type" to return_type,
        "parameter_names" to parameter_names.map { it },
        "parameter_types" to parameter_types.map { it }
    )

    companion object {
        fun from(input: DataInputStream): ShakeMapMethod {
            val name = input.readInt()
            val attributes = input.readByte()
            val return_type = input.readInt()
            val parameter_names = input.readInt()
            val parameter_names_ = Array(parameter_names) { input.readInt() }
            val parameter_types = input.readInt()
            val parameter_types_ = Array(parameter_types) { input.readInt() }
            return ShakeMapMethod(
                name,
                attributes,
                return_type,
                parameter_names_,
                parameter_types_
            )
        }
    }
}

class ShakeMapField(
    val name: Int,
    val attributes: Byte,
    val type: Int,
) {
    val isStatic = attributes.bit7
    val isFinal = attributes.bit6
    val isConst = attributes.bit5
    val isPublic = attributes.bit4
    val isProtected = attributes.bit3
    val isPrivate = attributes.bit2
    val isAbstract = attributes.bit1

    fun getBytes(): ByteArray {
        val stream = ByteArrayOutputStream()
        write(stream)
        return stream.toByteArray()
    }

    fun write(stream: OutputStream) {
        stream.write(name.toBytes())
        stream.write(attributes.toBytes())
        stream.write(type.toBytes())
    }

    fun toJson(): Map<String, *> = mapOf(
        "name" to name,
        "attributes" to attributes,
        "type" to type
    )

    companion object {
        fun from(input: DataInputStream): ShakeMapField {
            val name = input.readInt()
            val attributes = input.readByte()
            val type = input.readInt()
            return ShakeMapField(
                name,
                attributes,
                type
            )
        }
    }
}

val Int.bit0: Boolean get() = (this and 0x01) != 0
val Int.bit1: Boolean get() = (this and 0x02) != 0
val Int.bit2: Boolean get() = (this and 0x04) != 0
val Int.bit3: Boolean get() = (this and 0x08) != 0
val Int.bit4: Boolean get() = (this and 0x10) != 0
val Int.bit5: Boolean get() = (this and 0x20) != 0
val Int.bit6: Boolean get() = (this and 0x40) != 0
val Int.bit7: Boolean get() = (this and 0x80) != 0

val Byte.bit0: Boolean get() = this.toInt().bit0
val Byte.bit1: Boolean get() = this.toInt().bit1
val Byte.bit2: Boolean get() = this.toInt().bit2
val Byte.bit3: Boolean get() = this.toInt().bit3
val Byte.bit4: Boolean get() = this.toInt().bit4
val Byte.bit5: Boolean get() = this.toInt().bit5
val Byte.bit6: Boolean get() = this.toInt().bit6
val Byte.bit7: Boolean get() = this.toInt().bit7



class ShakeMapCreator {

    val constants = mutableListOf<ShakeMapConstant>()
    val packages = mutableListOf<ShakeMapPackage>()
    val classes = mutableListOf<ShakeMapClass>()
    val methods = mutableListOf<ShakeMapMethod>()
    val fields = mutableListOf<ShakeMapField>()
    val project_packages = mutableListOf<Int>()
    val project_classes = mutableListOf<Int>()
    val project_methods = mutableListOf<Int>()
    val project_fields = mutableListOf<Int>()

    fun visit(project: ShakeProject) {
        project.subpackages.forEach { this.project_packages.add(visit(it)) }
        project.classes.forEach { this.project_classes.add(visit(it)) }
        project.functions.forEach { this.project_methods.add(visit(it)) }
        project.fields.forEach { this.project_fields.add(visit(it)) }
    }

    fun visit(package_: ShakePackage): Int {

        val name = utf8(package_.name)

        val packages = mutableListOf<Int>()
        val classes = mutableListOf<Int>()
        val methods = mutableListOf<Int>()
        val fields = mutableListOf<Int>()

        package_.subpackages.forEach { packages.add(visit(it)) }
        package_.classes.forEach { classes.add(visit(it)) }
        package_.functions.forEach { methods.add(visit(it)) }
        package_.fields.forEach { fields.add(visit(it)) }

        this.packages.add(
            ShakeMapPackage(
                name,
                packages.toTypedArray(),
                classes.toTypedArray(),
                methods.toTypedArray(),
                fields.toTypedArray()
            )
        )

        return this.packages.size - 1
    }

    fun visit(class_: ShakeClass): Int {

        val name = utf8(class_.name)
        val superclass = if (class_.superClass != null) utf8(class_.superClass!!.qualifiedName) else -1
        val interfaces = class_.interfaces.map { utf8(it.qualifiedName) }.toTypedArray()
        val attributes = composeAttributes(class_)

        val classes = mutableListOf<Int>()
        val methods = mutableListOf<Int>()
        val fields = mutableListOf<Int>()

        class_.classes.forEach { classes.add(visit(it)) }
        class_.methods.forEach { methods.add(visit(it)) }
        class_.fields.forEach { fields.add(visit(it)) }

        this.classes.add(
            ShakeMapClass(
                name,
                attributes,
                superclass,
                interfaces,
                classes.toTypedArray(),
                methods.toTypedArray(),
                fields.toTypedArray()
            )
        )

        return this.classes.size - 1
    }

    fun visit(method: ShakeMethod): Int {

        val name = utf8(method.name)
        val attributes = composeAttributes(method)
        val return_type = utf8(method.returnType.qualifiedName)
        val parameter_names = method.parameters.map { utf8(it.name) }.toTypedArray()
        val parameter_types = method.parameters.map { utf8(it.type.qualifiedName) }.toTypedArray()

        this.methods.add(
            ShakeMapMethod(
                name,
                attributes,
                return_type,
                parameter_names,
                parameter_types
            )
        )

        return this.methods.size - 1
    }

    fun visit(method: ShakeFunction): Int {

        val name = utf8(method.name)
        val attributes = composeAttributes(method)
        val return_type = utf8(method.returnType.qualifiedName)
        val parameter_names = method.parameters.map { utf8(it.name) }.toTypedArray()
        val parameter_types = method.parameters.map { utf8(it.type.qualifiedName) }.toTypedArray()

        this.methods.add(
            ShakeMapMethod(
                name,
                attributes,
                return_type,
                parameter_names,
                parameter_types
            )
        )

        return this.methods.size - 1
    }

    fun visit(field: ShakeClassField): Int {

        val name = utf8(field.name)
        val attributes = composeAttributes(field)
        val type = utf8(field.type.qualifiedName)

        this.fields.add(
            ShakeMapField(
                name,
                attributes,
                type
            )
        )

        return this.fields.size - 1

    }

    fun visit(field: ShakeField): Int {

        val name = utf8(field.name)
        val attributes = composeAttributes(field)
        val type = utf8(field.type.qualifiedName)

        this.fields.add(
            ShakeMapField(
                name,
                attributes,
                type
            )
        )

        return this.fields.size - 1

    }

    fun utf8(string: String): Int {
        val i = constants.indexOfFirst { it is ShakeMapConstant.ShakeUtf8Constant && it.value == string }
        if (i != -1) return i
        constants.add(ShakeMapConstant.ShakeUtf8Constant(string))
        return constants.size - 1
    }

    fun composeAttributes(class_: ShakeClass): Byte {
        return 0
    }

    fun composeAttributes(method: ShakeMethod): Byte {
        return 0
    }

    fun composeAttributes(function: ShakeFunction): Byte {
        return 0
    }

    fun composeAttributes(field: ShakeClassField): Byte {
        return 0
    }

    fun composeAttributes(field: ShakeField): Byte {
        return 0
    }

    fun export(): ShakeMap {
        return ShakeMap(
            this.constants.toTypedArray(),
            this.packages.toTypedArray(),
            this.classes.toTypedArray(),
            this.methods.toTypedArray(),
            this.fields.toTypedArray(),
            this.project_packages.toTypedArray(),
            this.project_classes.toTypedArray(),
            this.project_methods.toTypedArray(),
            this.project_fields.toTypedArray()
        )
    }
}
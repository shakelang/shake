package com.shakelang.shake.processor.map

import com.shakelang.shake.processor.program.creation.CreationShakeType
import com.shakelang.shake.processor.program.types.*
import com.shakelang.shake.processor.program.types.code.ShakeCode
import com.shakelang.shake.processor.program.types.code.ShakeScope
import com.shakelang.shake.processor.program.types.code.values.ShakeValue
import com.shakelang.util.io.streaming.input.ByteArrayInputStream
import com.shakelang.util.io.streaming.input.DataInputStream
import com.shakelang.util.io.streaming.input.InputStream
import com.shakelang.util.io.streaming.output.ByteArrayOutputStream
import com.shakelang.util.io.streaming.output.OutputStream
import com.shakelang.util.pointer.PointerList
import com.shakelang.util.pointer.latePoint
import com.shakelang.util.pointer.values
import com.shakelang.util.primitives.bytes.toBytes
import com.shakelang.util.shason.json
import com.shakelang.util.primitives.bits.*

class ShakeMap(

    val constants: Array<ShakeMapConstant>,
    val packages: Array<ShakeMapPackage>,
    val classes: Array<ShakeMapClass>,
    val methods: Array<ShakeMapMethod>,
    val constructors: Array<ShakeMapConstructor>,
    val fields: Array<ShakeMapField>,
    val project_packages: Array<Int>,
    val project_classes: Array<Int>,
    val project_methods: Array<Int>,
    val project_fields: Array<Int>

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
        output.write(constructors.size.toBytes())
        constructors.forEach { it.write(output) }
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
        "constructors" to constructors.map { it.toJson() },
        "fields" to fields.map { it.toJson() },
        "project_packages" to project_packages.map { it },
        "project_classes" to project_classes.map { it },
        "project_methods" to project_methods.map { it },
        "project_fields" to project_fields.map { it }
    )

    fun toJsonString(): String = json.stringify(toJson())

    fun assemble(): ShakeProject {
        return ShakeMapAssembler(this).get()
    }

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
            val constructors = input.readInt()
            val constructors_ = Array(constructors) { ShakeMapConstructor.from(input) }
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
                constructors_,
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

        fun fromJson(str: String): ShakeMap {
            val map = json.parse(str) as Map<*, *>
            return fromJson(map)
        }

        fun fromJson(map: Map<*, *>): ShakeMap {
            if (map["constants"] == null) throw IllegalArgumentException("Missing constants")
            if (map["packages"] == null) throw IllegalArgumentException("Missing packages")
            if (map["classes"] == null) throw IllegalArgumentException("Missing classes")
            if (map["methods"] == null) throw IllegalArgumentException("Missing methods")
            if (map["fields"] == null) throw IllegalArgumentException("Missing fields")
            if (map["project_packages"] == null) throw IllegalArgumentException("Missing project_packages")
            if (map["project_classes"] == null) throw IllegalArgumentException("Missing project_classes")
            if (map["project_methods"] == null) throw IllegalArgumentException("Missing project_methods")
            if (map["project_fields"] == null) throw IllegalArgumentException("Missing project_fields")

            if (map["magic"] != 0x3082d75f) throw IllegalArgumentException("Invalid magic")
            if (map["version"] != 0x00000001) throw IllegalArgumentException("Invalid version")

            if (map["constants"] !is List<*>) throw IllegalArgumentException("Invalid constants")
            if (map["packages"] !is List<*>) throw IllegalArgumentException("Invalid packages")
            if (map["classes"] !is List<*>) throw IllegalArgumentException("Invalid classes")
            if (map["methods"] !is List<*>) throw IllegalArgumentException("Invalid methods")
            if (map["fields"] !is List<*>) throw IllegalArgumentException("Invalid fields")
            if (map["project_packages"] !is List<*>) throw IllegalArgumentException("Invalid project_packages")
            if (map["project_classes"] !is List<*>) throw IllegalArgumentException("Invalid project_classes")
            if (map["project_methods"] !is List<*>) throw IllegalArgumentException("Invalid project_methods")
            if (map["project_fields"] !is List<*>) throw IllegalArgumentException("Invalid project_fields")

            val constants_ = map["constants"] as List<*>
            val packages_ = map["packages"] as List<*>
            val classes_ = map["classes"] as List<*>
            val methods_ = map["methods"] as List<*>
            val constructors_ = map["constructors"] as List<*>
            val fields_ = map["fields"] as List<*>
            val project_packages_ = map["project_packages"] as List<*>
            val project_classes_ = map["project_classes"] as List<*>
            val project_methods_ = map["project_methods"] as List<*>
            val project_fields_ = map["project_fields"] as List<*>

            val constants = constants_.map {
                if (it !is Map<*, *>) throw IllegalArgumentException("Invalid constant")
                ShakeMapConstant.fromJson(it)
            }
            val packages = packages_.map {
                if (it !is Map<*, *>) throw IllegalArgumentException("Invalid package")
                ShakeMapPackage.fromJson(it)
            }
            val classes = classes_.map {
                if (it !is Map<*, *>) throw IllegalArgumentException("Invalid class")
                ShakeMapClass.fromJson(it)
            }
            val methods = methods_.map {
                if (it !is Map<*, *>) throw IllegalArgumentException("Invalid method")
                ShakeMapMethod.fromJson(it)
            }
            val constructors = constructors_.map {
                if (it !is Map<*, *>) throw IllegalArgumentException("Invalid constructor")
                ShakeMapConstructor.fromJson(it)
            }
            val fields = fields_.map {
                if (it !is Map<*, *>) throw IllegalArgumentException("Invalid field")
                ShakeMapField.fromJson(it)
            }
            val project_packages = project_packages_.map {
                if (it !is Int) throw IllegalArgumentException("Invalid project_package")
                it
            }
            val project_classes = project_classes_.map {
                if (it !is Int) throw IllegalArgumentException("Invalid project_class")
                it
            }
            val project_methods = project_methods_.map {
                if (it !is Int) throw IllegalArgumentException("Invalid project_method")
                it
            }
            val project_fields = project_fields_.map {
                if (it !is Int) throw IllegalArgumentException("Invalid project_field")
                it
            }
            return ShakeMap(
                constants.toTypedArray(),
                packages.toTypedArray(),
                classes.toTypedArray(),
                methods.toTypedArray(),
                constructors.toTypedArray(),
                fields.toTypedArray(),
                project_packages.toTypedArray(),
                project_classes.toTypedArray(),
                project_methods.toTypedArray(),
                project_fields.toTypedArray()
            )
        }
    }
}

interface ShakeMapConstant {
    val type: ShakeClassConstantTag

    fun getBytes(): ByteArray
    fun write(stream: OutputStream) = stream.write(getBytes())
    fun toJson(): Map<String, *>

    enum class ShakeClassConstantTag(val TAG: Byte) {
        UTF8(0x01)
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

        fun fromJson(it: Map<*, *>): ShakeUtf8Constant {
            if (it["type"] == null) throw IllegalArgumentException("Missing type")
            if (it["value"] == null) throw IllegalArgumentException("Missing value")
            if (it["type"] !is Number) throw IllegalArgumentException("Invalid type: ${it["type"]!!::class.simpleName}")
            if (it["value"] !is String) throw IllegalArgumentException("Invalid value")
            val type = (it["type"] as Number).toInt()
            val value = it["value"] as String
            when (type) {
                0x01 -> return utf8(value)
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
    val field_references: Array<Int>
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

        fun fromJson(str: String) = fromJson(json.parse(str) as Map<*, *>)

        fun fromJson(it: Map<*, *>): ShakeMapPackage {
            if (it["name"] == null) throw IllegalArgumentException("Missing name")
            if (it["package_references"] == null) throw IllegalArgumentException("Missing package_references")
            if (it["class_references"] == null) throw IllegalArgumentException("Missing class_references")
            if (it["method_references"] == null) throw IllegalArgumentException("Missing method_references")
            if (it["field_references"] == null) throw IllegalArgumentException("Missing field_references")
            if (it["name"] !is Int) throw IllegalArgumentException("Invalid name")
            if (it["package_references"] !is List<*>) throw IllegalArgumentException("Invalid package_references")
            if (it["class_references"] !is List<*>) throw IllegalArgumentException("Invalid class_references")
            if (it["method_references"] !is List<*>) throw IllegalArgumentException("Invalid method_references")
            if (it["field_references"] !is List<*>) throw IllegalArgumentException("Invalid field_references")

            val name = it["name"] as Int
            val package_references = it["package_references"] as List<*>
            val class_references = it["class_references"] as List<*>
            val method_references = it["method_references"] as List<*>
            val field_references = it["field_references"] as List<*>

            val package_references_ = package_references.filterIsInstance<Int>().toTypedArray()
            val class_references_ = class_references.filterIsInstance<Int>().toTypedArray()
            val method_references_ = method_references.filterIsInstance<Int>().toTypedArray()
            val field_references_ = field_references.filterIsInstance<Int>().toTypedArray()

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
    val attributes: Short,
    val super_class: Int,
    val interface_references: Array<Int>,
    val subclass_references: Array<Int>,
    val method_references: Array<Int>,
    val constructor_references: Array<Int>,
    val field_references: Array<Int>
) {
    val isNative get() = attributes.bit8
    val isStatic get() = attributes.bit7
    val isFinal get() = attributes.bit6
    val isPublic get() = attributes.bit4
    val isProtected get() = attributes.bit3
    val isPrivate get() = attributes.bit2
    val isAbstract get() = attributes.bit1

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
        stream.write(constructor_references.size.toBytes())
        constructor_references.forEach { stream.write(it.toBytes()) }
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
        "constructor_references" to constructor_references.map { it },
        "field_references" to field_references.map { it }
    )

    companion object {
        fun from(input: DataInputStream): ShakeMapClass {
            val name = input.readInt()
            val attributes = input.readShort()
            val super_class = input.readInt()
            val interface_references = input.readInt()
            val interface_references_ = Array(interface_references) { input.readInt() }
            val subclass_references = input.readInt()
            val subclass_references_ = Array(subclass_references) { input.readInt() }
            val method_references = input.readInt()
            val method_references_ = Array(method_references) { input.readInt() }
            val constructor_references = input.readInt()
            val constructor_references_ = Array(constructor_references) { input.readInt() }
            val field_references = input.readInt()
            val field_references_ = Array(field_references) { input.readInt() }
            return ShakeMapClass(
                name,
                attributes,
                super_class,
                interface_references_,
                subclass_references_,
                method_references_,
                constructor_references_,
                field_references_
            )
        }

        fun fromJson(str: String) = ShakeMapPackage.fromJson(json.parse(str) as Map<*, *>)

        fun fromJson(it: Map<*, *>): ShakeMapClass {
            if (it["name"] == null) throw IllegalArgumentException("Missing name")
            if (it["attributes"] == null) throw IllegalArgumentException("Missing attributes")
            if (it["super_class"] == null) throw IllegalArgumentException("Missing super_class")
            if (it["interface_references"] == null) throw IllegalArgumentException("Missing interface_references")
            if (it["subclass_references"] == null) throw IllegalArgumentException("Missing subclass_references")
            if (it["method_references"] == null) throw IllegalArgumentException("Missing method_references")
            if (it["field_references"] == null) throw IllegalArgumentException("Missing field_references")

            if (it["name"] !is Int) throw IllegalArgumentException("Invalid name")
            if (it["attributes"] !is Short) throw IllegalArgumentException("Invalid attributes")
            if (it["super_class"] !is Int) throw IllegalArgumentException("Invalid super_class")
            if (it["interface_references"] !is List<*>) throw IllegalArgumentException("Invalid interface_references")
            if (it["subclass_references"] !is List<*>) throw IllegalArgumentException("Invalid subclass_references")
            if (it["method_references"] !is List<*>) throw IllegalArgumentException("Invalid method_references")
            if (it["field_references"] !is List<*>) throw IllegalArgumentException("Invalid field_references")

            val name = it["name"] as Int
            val attributes = it["attributes"] as Short
            val super_class = it["super_class"] as Int
            val interface_references = it["interface_references"] as List<*>
            val subclass_references = it["subclass_references"] as List<*>
            val method_references = it["method_references"] as List<*>
            val constructor_references = it["constructor_references"] as List<*>
            val field_references = it["field_references"] as List<*>

            val interface_references_ = interface_references.filterIsInstance<Int>().toTypedArray()
            val subclass_references_ = subclass_references.filterIsInstance<Int>().toTypedArray()
            val method_references_ = method_references.filterIsInstance<Int>().toTypedArray()
            val constructor_references_ = constructor_references.filterIsInstance<Int>().toTypedArray()
            val field_references_ = field_references.filterIsInstance<Int>().toTypedArray()

            return ShakeMapClass(
                name,
                attributes,
                super_class,
                interface_references_,
                subclass_references_,
                method_references_,
                constructor_references_,
                field_references_
            )
        }
    }
}

class ShakeMapMethod(
    val name: Int,
    val attributes: Short,
    val return_type: Int,
    val parameter_names: Array<Int>,
    val parameter_types: Array<Int>,
    val expanding: Int
) {
    val isOperator get() = attributes.bit9
    val isNative get() = attributes.bit8
    val isStatic get() = attributes.bit7
    val isFinal get() = attributes.bit6
    val isStrict get() = attributes.bit5
    val isPublic get() = attributes.bit4
    val isProtected get() = attributes.bit3
    val isPrivate get() = attributes.bit2
    val isAbstract get() = attributes.bit1
    val isSynchronized get() = attributes.bit0

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
        stream.write(expanding.toBytes())
    }

    fun toJson(): Map<String, *> = mapOf(
        "name" to name,
        "attributes" to attributes,
        "return_type" to return_type,
        "parameter_names" to parameter_names.map { it },
        "parameter_types" to parameter_types.map { it },
        "expanding" to expanding
    )

    companion object {
        fun from(input: DataInputStream): ShakeMapMethod {
            val name = input.readInt()
            val attributes = input.readShort()
            val return_type = input.readInt()
            val parameter_names = input.readInt()
            val parameter_names_ = Array(parameter_names) { input.readInt() }
            val parameter_types = input.readInt()
            val parameter_types_ = Array(parameter_types) { input.readInt() }
            val expanding = input.readInt()
            return ShakeMapMethod(
                name,
                attributes,
                return_type,
                parameter_names_,
                parameter_types_,
                expanding
            )
        }

        fun fromJson(str: String) = ShakeMapPackage.fromJson(json.parse(str) as Map<*, *>)

        fun fromJson(it: Map<*, *>): ShakeMapMethod {
            if (it["name"] == null) throw IllegalArgumentException("Missing name")
            if (it["attributes"] == null) throw IllegalArgumentException("Missing attributes")
            if (it["return_type"] == null) throw IllegalArgumentException("Missing return_type")
            if (it["parameter_names"] == null) throw IllegalArgumentException("Missing parameter_names")
            if (it["parameter_types"] == null) throw IllegalArgumentException("Missing parameter_types")
            if (it["expanding"] == null) throw IllegalArgumentException("Missing expanding")

            if (it["name"] !is Int) throw IllegalArgumentException("Invalid name")
            if (it["attributes"] !is Short) throw IllegalArgumentException("Invalid attributes")
            if (it["return_type"] !is Int) throw IllegalArgumentException("Invalid return_type")
            if (it["parameter_names"] !is List<*>) throw IllegalArgumentException("Invalid parameter_names")
            if (it["parameter_types"] !is List<*>) throw IllegalArgumentException("Invalid parameter_types")
            if (it["expanding"] !is Int) throw IllegalArgumentException("Invalid expanding")

            val name = it["name"] as Int
            val attributes = it["attributes"] as Short
            val return_type = it["return_type"] as Int
            val parameter_names = it["parameter_names"] as List<*>
            val parameter_names_ = parameter_names.filterIsInstance<Int>().toTypedArray()
            val parameter_types = it["parameter_types"] as List<*>
            val parameter_types_ = parameter_types.filterIsInstance<Int>().toTypedArray()
            val expanding = it["expanding"] as Int
            return ShakeMapMethod(
                name,
                attributes,
                return_type,
                parameter_names_,
                parameter_types_,
                expanding
            )
        }
    }
}

class ShakeMapConstructor(
    val name: Int,
    val attributes: Short,
    val parameter_names: Array<Int>,
    val parameter_types: Array<Int>
) {
    val isNative get() = attributes.bit8
    val isStrict: Boolean get() = attributes.bit5
    val isPublic get() = attributes.bit4
    val isProtected get() = attributes.bit3
    val isPrivate get() = attributes.bit2

    fun getBytes(): ByteArray {
        val stream = ByteArrayOutputStream()
        write(stream)
        return stream.toByteArray()
    }

    fun write(stream: OutputStream) {
        stream.write(name.toBytes())
        stream.write(attributes.toBytes())
        stream.write(parameter_names.size.toBytes())
        parameter_names.forEach { stream.write(it.toBytes()) }
        stream.write(parameter_types.size.toBytes())
        parameter_types.forEach { stream.write(it.toBytes()) }
    }

    fun toJson(): Map<String, *> = mapOf(
        "name" to name,
        "attributes" to attributes,
        "parameter_names" to parameter_names.map { it },
        "parameter_types" to parameter_types.map { it }
    )

    companion object {
        fun from(input: DataInputStream): ShakeMapConstructor {
            val name = input.readInt()
            val attributes = input.readShort()
            val parameter_names = input.readInt()
            val parameter_names_ = Array(parameter_names) { input.readInt() }
            val parameter_types = input.readInt()
            val parameter_types_ = Array(parameter_types) { input.readInt() }
            return ShakeMapConstructor(
                name,
                attributes,
                parameter_names_,
                parameter_types_
            )
        }

        fun fromJson(str: String) = ShakeMapPackage.fromJson(json.parse(str) as Map<*, *>)

        fun fromJson(it: Map<*, *>): ShakeMapConstructor {
            if (it["name"] == null) throw IllegalArgumentException("Missing name")
            if (it["attributes"] == null) throw IllegalArgumentException("Missing attributes")
            if (it["parameter_names"] == null) throw IllegalArgumentException("Missing parameter_names")
            if (it["parameter_types"] == null) throw IllegalArgumentException("Missing parameter_types")

            if (it["name"] !is Int) throw IllegalArgumentException("Invalid name")
            if (it["attributes"] !is Short) throw IllegalArgumentException("Invalid attributes")
            if (it["parameter_names"] !is List<*>) throw IllegalArgumentException("Invalid parameter_names")
            if (it["parameter_types"] !is List<*>) throw IllegalArgumentException("Invalid parameter_types")

            val name = it["name"] as Int
            val attributes = it["attributes"] as Short
            val parameter_names = it["parameter_names"] as List<*>
            val parameter_names_ = parameter_names.filterIsInstance<Int>().toTypedArray()
            val parameter_types = it["parameter_types"] as List<*>
            val parameter_types_ = parameter_types.filterIsInstance<Int>().toTypedArray()
            return ShakeMapConstructor(
                name,
                attributes,
                parameter_names_,
                parameter_types_
            )
        }
    }
}

class ShakeMapField(
    val name: Int,
    val attributes: Short,
    val type: Int,
    val expanding: Int
) {
    val isNative get() = attributes.bit8
    val isStatic get() = attributes.bit7
    val isFinal get() = attributes.bit6
    val isConst get() = attributes.bit5
    val isPublic get() = attributes.bit4
    val isProtected get() = attributes.bit3
    val isPrivate get() = attributes.bit2
    val isAbstract get() = attributes.bit1

    fun getBytes(): ByteArray {
        val stream = ByteArrayOutputStream()
        write(stream)
        return stream.toByteArray()
    }

    fun write(stream: OutputStream) {
        stream.write(name.toBytes())
        stream.write(attributes.toBytes())
        stream.write(type.toBytes())
        stream.write(expanding.toBytes())
    }

    fun toJson(): Map<String, *> = mapOf(
        "name" to name,
        "attributes" to attributes,
        "type" to type,
        "expanding" to expanding
    )

    companion object {
        fun from(input: DataInputStream): ShakeMapField {
            val name = input.readInt()
            val attributes = input.readShort()
            val type = input.readInt()
            return ShakeMapField(
                name,
                attributes,
                type,
                input.readInt()
            )
        }

        fun fromJson(str: String) = ShakeMapPackage.fromJson(json.parse(str) as Map<*, *>)

        fun fromJson(it: Map<*, *>): ShakeMapField {
            if (it["name"] == null) throw IllegalArgumentException("Missing name")
            if (it["attributes"] == null) throw IllegalArgumentException("Missing attributes")
            if (it["type"] == null) throw IllegalArgumentException("Missing type")
            if (it["expanding"] == null) throw IllegalArgumentException("Missing expanding")

            if (it["name"] !is Int) throw IllegalArgumentException("Invalid name")
            if (it["attributes"] !is Short) throw IllegalArgumentException("Invalid attributes")
            if (it["type"] !is Int) throw IllegalArgumentException("Invalid type")
            if (it["expanding"] !is Int) throw IllegalArgumentException("Invalid expanding")

            val name = it["name"] as Int
            val attributes = it["attributes"] as Short
            val type = it["type"] as Int
            val expanding = it["expanding"] as Int
            return ShakeMapField(
                name,
                attributes,
                type,
                expanding
            )
        }
    }
}

class ShakeMapCreator {

    val constants = mutableListOf<ShakeMapConstant>()
    val packages = mutableListOf<ShakeMapPackage>()
    val classes = mutableListOf<ShakeMapClass>()
    val methods = mutableListOf<ShakeMapMethod>()
    val constructors = mutableListOf<ShakeMapConstructor>()
    val fields = mutableListOf<ShakeMapField>()
    val project_packages = mutableListOf<Int>()
    val project_classes = mutableListOf<Int>()
    val project_methods = mutableListOf<Int>()
    val project_fields = mutableListOf<Int>()

    fun visit(project: ShakeProject) {
        project.subpackages.forEach { this.project_packages.add(visit(it)) }
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
        val constructors = mutableListOf<Int>()
        val fields = mutableListOf<Int>()

        class_.classes.forEach { classes.add(visit(it)) }
        class_.methods.forEach { methods.add(visit(it)) }
        class_.constructors.forEach { constructors.add(visit(it)) }
        class_.fields.forEach { fields.add(visit(it)) }

        this.classes.add(
            ShakeMapClass(
                name,
                attributes,
                superclass,
                interfaces,
                classes.toTypedArray(),
                methods.toTypedArray(),
                constructors.toTypedArray(),
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
        val expanding = method.expanding?.qualifiedName?.let { utf8(it) } ?: -1

        this.methods.add(
            ShakeMapMethod(
                name,
                attributes,
                return_type,
                parameter_names,
                parameter_types,
                expanding
            )
        )

        return this.methods.size - 1
    }

    fun visit(constructor: ShakeConstructor): Int {
        val name = constructor.name?.let { utf8(it) } ?: -1
        val attributes = composeAttributes(constructor)
        val parameter_names = constructor.parameters.map { utf8(it.name) }.toTypedArray()
        val parameter_types = constructor.parameters.map { utf8(it.type.qualifiedName) }.toTypedArray()

        this.constructors.add(
            ShakeMapConstructor(
                name,
                attributes,
                parameter_names,
                parameter_types
            )
        )

        return this.constructors.size - 1
    }

    fun visit(field: ShakeField): Int {
        val name = utf8(field.name)
        val attributes = composeAttributes(field)
        val type = utf8(field.type.qualifiedName)
        val expanding = field.expanding?.qualifiedName?.let { utf8(it) } ?: -1

        this.fields.add(
            ShakeMapField(
                name,
                attributes,
                type,
                expanding
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

    fun composeAttributes(clazz: ShakeClass): Short {
        var attributes = 0
        if (clazz.isNative) attributes = attributes.setBit8(true)
        if (clazz.isStatic) attributes = attributes.setBit7(true)
        if (clazz.isFinal) attributes = attributes.setBit6(true)
        if (clazz.isPublic) attributes = attributes.setBit4(true)
        if (clazz.isProtected) attributes = attributes.setBit3(true)
        if (clazz.isPrivate) attributes = attributes.setBit2(true)
        if (clazz.isAbstract) attributes = attributes.setBit1(true)
        return attributes.toUShort().toShort()
    }

    fun composeAttributes(function: ShakeMethod): Short {
        var attributes = 0
        if (function.isOperator) attributes = attributes.setBit9(true)
        if (function.isNative) attributes = attributes.setBit8(true)
        if (function.isStatic) attributes = attributes.setBit7(true)
        if (function.isFinal) attributes = attributes.setBit6(true)
        if (function.isPublic) attributes = attributes.setBit4(true)
        if (function.isProtected) attributes = attributes.setBit3(true)
        if (function.isPrivate) attributes = attributes.setBit2(true)
        if (function.isAbstract) attributes = attributes.setBit1(true)
        return attributes.toUShort().toShort()
    }

    fun composeAttributes(field: ShakeConstructor): Short {
        var attributes = 0
        if (field.isNative) attributes = attributes.setBit8(true)
        if (field.isPublic) attributes = attributes.setBit4(true)
        if (field.isProtected) attributes = attributes.setBit3(true)
        if (field.isPrivate) attributes = attributes.setBit2(true)
        return attributes.toUShort().toShort()
    }

    fun composeAttributes(field: ShakeField): Short {
        var attributes = 0
        if (field.isNative) attributes = attributes.setBit8(true)
        if (field.isStatic) attributes = attributes.setBit7(true)
        if (field.isFinal) attributes = attributes.setBit6(true)
        if (field.isFinal) attributes = attributes.setBit5(true)
        if (field.isPublic) attributes = attributes.setBit4(true)
        if (field.isProtected) attributes = attributes.setBit3(true)
        if (field.isPrivate) attributes = attributes.setBit2(true)
        if (field.isAbstract) attributes = attributes.setBit1(true)
        return attributes.toShort()
    }

    fun export(): ShakeMap {
        return ShakeMap(
            this.constants.toTypedArray(),
            this.packages.toTypedArray(),
            this.classes.toTypedArray(),
            this.methods.toTypedArray(),
            this.constructors.toTypedArray(),
            this.fields.toTypedArray(),
            this.project_packages.toTypedArray(),
            this.project_classes.toTypedArray(),
            this.project_methods.toTypedArray(),
            this.project_fields.toTypedArray()
        )
    }
}

class MapAssembledProject(
    val packagePointers: PointerList<ShakePackage>,
    val classPointers: PointerList<ShakeClass>,
    val methodPointers: PointerList<ShakeMethod>,
    val fieldPointers: PointerList<ShakeField>,
    override val projectScope: ShakeScope

) : ShakeProject {

    override val subpackages: List<ShakePackage> = packagePointers.values()
    override fun phase1() {
        subpackages.forEach { it.phase1() }
    }
    override fun phase2() {
        subpackages.forEach { it.phase2() }
    }
    override fun phase3() {
        subpackages.forEach { it.phase3() }
    }
    override fun phase4() {
        subpackages.forEach { it.phase4() }
    }
}

class MapAssembledPackage(
    override val scope: ShakeScope,
    override val baseProject: ShakeProject,
    override val name: String,
    override var parent: ShakePackage?,
    val subpackagePointers: PointerList<ShakePackage>,
    val classPointers: PointerList<ShakeClass>,
    val methodPointers: PointerList<ShakeMethod>,
    val fieldPointers: PointerList<ShakeField>
) : ShakePackage {
    override val subpackages: List<ShakePackage> get() = subpackagePointers.values()
    override val classes: List<ShakeClass> get() = classPointers.values()
    override val functions: List<ShakeMethod> get() = methodPointers.values()
    override val fields: List<ShakeField> get() = fieldPointers.values()

    override fun phase1() {}
    override fun phase2() {}
    override fun phase3() {}
    override fun phase4() {}
}

class MapAssembledClass(
    override val prj: ShakeProject,
    override var pkg: ShakePackage?,
    override val clazz: ShakeClass?,
    override val parentScope: ShakeScope,
    override val staticScope: ShakeScope,
    override val instanceScope: ShakeScope,
    override val name: String,
    val classPointers: PointerList<ShakeClass>,
    val methodPointers: PointerList<ShakeMethod>,
    val fieldPointers: PointerList<ShakeField>,
    val constructorPointers: PointerList<ShakeConstructor>,
    override val isAbstract: Boolean,
    override val isFinal: Boolean,
    override val isStatic: Boolean,
    override val isPublic: Boolean,
    override val isProtected: Boolean,
    override val isPrivate: Boolean,
    override val isNative: Boolean
) : ShakeClass {
    override val classes: List<ShakeClass> = classPointers.values()
    override val methods: List<ShakeMethod> = methodPointers.values()
    override val fields: List<ShakeField> = fieldPointers.values()
    override val constructors: List<ShakeConstructor> = constructorPointers.values()

    override lateinit var interfaces: List<ShakeClass>
    override lateinit var superClass: ShakeClass

    override fun phase1() {}
    override fun phase2() {}
    override fun phase3() {}
    override fun phase4() {}
}

class MapAssembledMethod(
    override val prj: ShakeProject,
    override var pkg: ShakePackage?,
    override var clazz: ShakeClass?,
    override val parentScope: ShakeScope,
    override val name: String,
    override val returnType: ShakeType,
    override val parameters: List<ShakeParameter>,
    override val isAbstract: Boolean,
    override val isFinal: Boolean,
    override val isStatic: Boolean,
    override val isPublic: Boolean,
    override val isProtected: Boolean,
    override val isPrivate: Boolean,
    override val isSynchronized: Boolean,
    override val isStrict: Boolean,
    override val scope: ShakeScope,
    override val body: ShakeCode? = null,
    override val isNative: Boolean,
    override val isOperator: Boolean,
    override val expanding: ShakeType?
) : ShakeMethod {
    override fun phase3() {}
    override fun phase4() {}
}

class MapAssembledConstructor(
    var clazz_: ShakeClass?,
    override val name: String?,
    override val scope: ShakeScope,
    override val parameters: List<ShakeParameter>,
    override val isPublic: Boolean,
    override val isProtected: Boolean,
    override val isPrivate: Boolean,
    override val body: ShakeCode? = null,
    override val isStrict: Boolean,
    override val isNative: Boolean
) : ShakeConstructor {
    override var clazz: ShakeClass
        get() = clazz_!!
        set(value) {
            clazz_ = value
        }

    override fun phase3() {}
    override fun phase4() {}
}

class MapAssembledField(
    override val project: ShakeProject,
    override var pkg: ShakePackage?,
    override var clazz: ShakeClass?,
    override val parentScope: ShakeScope,
    override val name: String,
    override val type: ShakeType,
    override val isAbstract: Boolean,
    override val isStatic: Boolean,
    override val isFinal: Boolean,
    override val isPublic: Boolean,
    override val isProtected: Boolean,
    override val isPrivate: Boolean,
    override val isNative: Boolean,
    override val actualValue: ShakeValue? = null,
    override val actualType: ShakeType = type,
    override val qualifiedName: String,
    override val initialValue: ShakeValue?,
    override val expanding: ShakeType?
) : ShakeField {
    override fun phase3() {}
    override fun phase4() {}
}

class ShakeAssembledObjectType(
    override var clazz: ShakeClass
) : ShakeType.Object {
    override val name: String get() = clazz.name
}

class ShakeAssembledArrayType(
    override val elementType: ShakeType
) : ShakeType.Array {
    override val name: String get() = elementType.name
}

class ShakeMapAssembler(val shakeMap: ShakeMap) {

    val scopeUnit: ShakeScope = object : ShakeScope {
        override val parent = null
        override val uniqueName: String
            get() = "scopeUnit"
        override fun get(name: String): ShakeAssignable = error("Should not be called")
        override fun getFunctions(name: String) = error("Should not be called")
        override fun getClass(name: String) = error("Should not be called")
        override fun getInvokable(name: String) = error("Should not be called")
        override fun use(name: String) = error("Should not be called")
    }

    val project: ShakeProject

    val packagePointers: PointerList<MapAssembledPackage>
    val classPointers: PointerList<MapAssembledClass>
    val methodPointers: PointerList<MapAssembledMethod>
    val constructorPointers: PointerList<MapAssembledConstructor>
    val fieldPointers: PointerList<MapAssembledField>

    val packages: List<MapAssembledPackage>
    val classes: List<MapAssembledClass>
    val methods: List<MapAssembledMethod>
    val constructors: List<MapAssembledConstructor>
    val fields: List<MapAssembledField>

    init {
        packagePointers = (0 until shakeMap.packages.size).map { latePoint() }
        classPointers = (0 until shakeMap.classes.size).map { latePoint() }
        methodPointers = (0 until shakeMap.methods.size).map { latePoint() }
        constructorPointers = (0 until shakeMap.constructors.size).map { latePoint() }
        fieldPointers = (0 until shakeMap.fields.size).map { latePoint() }

        packages = packagePointers.values()
        classes = classPointers.values()
        methods = methodPointers.values()
        constructors = constructorPointers.values()
        fields = fieldPointers.values()

        project = MapAssembledProject(
            shakeMap.project_packages.map { packagePointers[it] },
            shakeMap.project_classes.map { classPointers[it] },
            shakeMap.project_methods.map { methodPointers[it] },
            shakeMap.project_fields.map { fieldPointers[it] },
            scopeUnit
        )

        packagePointers.forEachIndexed { index, point ->
            val info = shakeMap.packages[index]
            point.init(
                MapAssembledPackage(
                    scopeUnit,
                    project,
                    getUtf(info.name),
                    null,
                    info.package_references.map { packagePointers[it] },
                    info.class_references.map { classPointers[it] },
                    info.method_references.map { methodPointers[it] },
                    info.field_references.map { fieldPointers[it] }
                )
            )
        }

        // set parent packages
        shakeMap.packages.forEachIndexed { i, pkg ->
            pkg.package_references.forEach {
                packages[it].parent = packages[i]
            }
        }

        classPointers.forEachIndexed { index, point ->
            val info = shakeMap.classes[index]
            point.init(
                MapAssembledClass(
                    project,
                    null,
                    null,
                    scopeUnit,
                    scopeUnit,
                    scopeUnit,
                    getUtf(info.name),
                    info.subclass_references.map { classPointers[it] },
                    info.method_references.map { methodPointers[it] },
                    info.field_references.map { fieldPointers[it] },
                    info.constructor_references.map { constructorPointers[it] },
                    info.isAbstract,
                    info.isFinal,
                    info.isStatic,
                    info.isPublic,
                    info.isProtected,
                    info.isPrivate,
                    info.isNative
                )
            )
        }

        // set parent packages
        shakeMap.packages.forEachIndexed { i, pkg ->
            pkg.class_references.forEach {
                classes[it].pkg = packages[i]
            }
        }

        // set parent classes
        shakeMap.classes.forEachIndexed { i, cls ->
            cls.subclass_references.forEach {
                // classes[i].parent = classes[it]
                // TODO: implement
            }
        }

        // set superclasses and interfaces
        shakeMap.classes.forEachIndexed { i, cls ->
            val superclass = getUtf(cls.super_class)
            classes[i].superClass =
                classes.find { it.qualifiedName == superclass } ?: error("Superclass $superclass not found")

            classes[i].interfaces = cls.interface_references.map { inf ->
                val interfaceName = getUtf(inf)
                classes.find { it.qualifiedName == interfaceName } ?: error("Interface $interfaceName not found")
            }
        }

        methodPointers.forEachIndexed { index, point ->
            val info = shakeMap.methods[index]
            point.init(
                MapAssembledMethod(
                    project,
                    null,
                    null,
                    scopeUnit,
                    getUtf(info.name),
                    findType(info.return_type),
                    info.parameter_names.zip(info.parameter_types).map {
                        object : ShakeParameter {
                            override val name: String = getUtf(it.first)
                            override val type: ShakeType = findType(it.second)
                        }
                    },
                    info.isAbstract,
                    info.isFinal,
                    info.isStatic,
                    info.isPublic,
                    info.isProtected,
                    info.isPrivate,
                    info.isSynchronized,
                    info.isStrict,
                    scopeUnit,
                    null,
                    info.isNative,
                    info.isOperator,
                    if (info.expanding != -1) findType(info.expanding) else null
                )
            )
        }

        // set parent packages
        shakeMap.packages.forEachIndexed { i, pkg ->
            pkg.method_references.forEach {
                methods[it].pkg = packages[i]
            }
        }

        // set parent classes
        shakeMap.classes.forEachIndexed { i, cls ->
            cls.method_references.forEach {
                methods[it].clazz = classes[i]
            }
        }

        constructorPointers.forEachIndexed { index, point ->
            val info = shakeMap.constructors[index]
            point.init(
                MapAssembledConstructor(
                    null,
                    if (info.name != -1) getUtf(info.name) else null,
                    scopeUnit,
                    info.parameter_names.zip(info.parameter_types).map {
                        object : ShakeParameter {
                            override val name: String = getUtf(it.first)
                            override val type: ShakeType = findType(it.second)
                        }
                    },
                    info.isPublic,
                    info.isProtected,
                    info.isPrivate,
                    null,
                    info.isStrict,
                    info.isNative
                )
            )
        }

        // set parent classes
        shakeMap.classes.forEachIndexed { i, cls ->
            cls.constructor_references.forEach {
                constructors[it].clazz = classes[i]
            }
        }

        fieldPointers.forEachIndexed { index, point ->
            val info = shakeMap.fields[index]
            point.init(
                MapAssembledField(
                    project,
                    null,
                    null,
                    scopeUnit,
                    getUtf(info.name),
                    findType(info.type),
                    info.isAbstract,
                    info.isStatic,
                    info.isFinal,
                    info.isPublic,
                    info.isProtected,
                    info.isPrivate,
                    info.isNative,
                    null,
                    findType(info.type),
                    getUtf(info.name),
                    null,
                    if (info.expanding != -1) findType(info.expanding) else null
                )
            )
        }

        // set parent packages
        shakeMap.packages.forEachIndexed { i, pkg ->
            pkg.field_references.forEach {
                fields[it].pkg = packages[i]
            }
        }

        // set parent classes
        shakeMap.classes.forEachIndexed { i, cls ->
            cls.field_references.forEach {
                fields[it].clazz = classes[i]
            }
        }
    }

    private fun findType(type: String): ShakeType {
        return when (type) {
            "V" -> CreationShakeType.Primitives.VOID
            "Z" -> CreationShakeType.Primitives.BOOLEAN
            "B" -> CreationShakeType.Primitives.BYTE
            "S" -> CreationShakeType.Primitives.SHORT
            "C" -> CreationShakeType.Primitives.CHAR
            "I" -> CreationShakeType.Primitives.INT
            "J" -> CreationShakeType.Primitives.LONG
            "F" -> CreationShakeType.Primitives.FLOAT
            "D" -> CreationShakeType.Primitives.DOUBLE
            "b" -> CreationShakeType.Primitives.UBYTE
            "s" -> CreationShakeType.Primitives.USHORT
            "i" -> CreationShakeType.Primitives.UINT
            "j" -> CreationShakeType.Primitives.ULONG
            else -> {
                if (type.startsWith("L")) {
                    val className = type.substring(1, type.length)
                    val classPointer =
                        classes.find { it.qualifiedName == className } ?: error("Class $className not found")
                    ShakeAssembledObjectType(classPointer)
                } else if (type.startsWith("[")) {
                    val componentType = findType(type.substring(1))
                    ShakeAssembledArrayType(componentType)
                } else {
                    error("Unknown type $type")
                }
            }
        }
    }

    private fun findType(name: Int) = findType(getUtf(name))

    private fun getUtf(id: Int): String {
        return (shakeMap.constants[id] as ShakeMapConstant.ShakeUtf8Constant).value
    }

    fun get() = project
}

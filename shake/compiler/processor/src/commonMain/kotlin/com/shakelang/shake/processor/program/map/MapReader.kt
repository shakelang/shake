package com.shakelang.shake.processor.program.map

import com.shakelang.shake.processor.ShakeASTProcessor
import com.shakelang.util.io.streaming.input.bytes.DataInputStream

class PackageInformation(
    val name: String,
    val subpackages: List<PackageInformation>,
    val classInformation: List<ClassInformation>,
    val methodInformation: List<MethodInformation>,
    val fieldInformation: List<FieldInformation>,
) {
    fun json(): Map<String, Any> = mapOf(
        "type" to "package",
        "name" to name,
        "subpackages" to subpackages.map { it.json() },
        "classInformation" to classInformation.map { it.json() },
        "methodInformation" to methodInformation.map { it.json() },
        "fieldInformation" to fieldInformation.map { it.json() },
    )
}

class ClassInformation(
    val name: String,
    val flags: Short,
    val superClass: String,
    val interfaces: List<String>,
    val methodInformation: List<MethodInformation>,
    val fieldInformation: List<FieldInformation>,
    val constructorInformation: List<ConstructorInformation>,
    val classInformation: List<ClassInformation>,
) {
    fun json(): Map<String, Any> = mapOf(
        "type" to "class",
        "name" to name,
        "flags" to flags,
        "superClass" to superClass,
        "interfaces" to interfaces,
        "methodInformation" to methodInformation.map { it.json() },
        "fieldInformation" to fieldInformation.map { it.json() },
        "constructorInformation" to constructorInformation.map { it.json() },
        "classInformation" to classInformation.map { it.json() },
    )
}

class MethodInformation(
    val name: String,
    val flags: Short,
    val parameters: List<ParameterInformation>,
) {
    fun json(): Map<String, Any> = mapOf(
        "type" to "method",
        "name" to name,
        "flags" to flags,
        "parameters" to parameters.map { it.json() },
    )
}

class FieldInformation(
    val name: String,
    val flags: Short,
    val type: String,
) {
    fun json(): Map<String, Any> = mapOf(
        "type" to "field",
        "name" to name,
        "flags" to flags,
        "type" to type,
    )
}

class ConstructorInformation(
    val flags: Short,
    val parameters: List<ParameterInformation>,
) {
    fun json(): Map<String, Any> = mapOf(
        "type" to "constructor",
        "flags" to flags,
        "parameters" to parameters.map { it.json() },
    )
}

class ParameterInformation(
    val name: String,
    val type: String,
) {
    fun json(): Map<String, Any> = mapOf(
        "type" to "parameter",
        "name" to name,
        "type" to type,
    )
}

class ProjectInformation(
    val packages: List<PackageInformation>,
) {
    fun json(): Map<String, Any> = mapOf(
        "type" to "project",
        "packages" to packages.map { it.json() },
    )
}

class MapReader(
    val inputStream: DataInputStream,
) {

    fun readProject(): ProjectInformation {
        // Magic number
        val magicNumber = inputStream.readShort()
        if (magicNumber != MapGenerator.MAGIC_NUMBER) throw Exception("Invalid magic number")

        // Major version
        val majorVersion = inputStream.readByte()
        if (majorVersion != 0.toByte()) throw Exception("Invalid major version")

        // Minor version
        val minorVersion = inputStream.readByte()
        if (minorVersion != 1.toByte()) throw Exception("Invalid minor version")

        // Package count
        val packageCount = inputStream.readUnsignedShort().toInt()

        // Store all packages
        val packages = mutableListOf<PackageInformation>()
        for (i in 0 until packageCount) {
            packages.add(readPackage())
        }

        val codeProcessor = ShakeASTProcessor()
        return ProjectInformation(packages)
    }

    fun readPackage(): PackageInformation {
        val name = inputStream.readUTF8()

        val subpackageCount = inputStream.readUnsignedShort().toInt()
        val subpackages = mutableListOf<PackageInformation>()
        for (i in 0 until subpackageCount) {
            subpackages.add(readPackage())
        }

        val classCount = inputStream.readUnsignedShort().toInt()
        val classInformation = mutableListOf<ClassInformation>()
        for (i in 0 until classCount) {
            classInformation.add(readClass(PackageInformation(name, subpackages, classInformation, mutableListOf(), mutableListOf())))
        }

        val methodCount = inputStream.readUnsignedShort().toInt()
        val methodInformation = mutableListOf<MethodInformation>()
        for (i in 0 until methodCount) {
            methodInformation.add(readMethod(PackageInformation(name, subpackages, classInformation, methodInformation, mutableListOf())))
        }

        val fieldCount = inputStream.readUnsignedShort().toInt()
        val fieldInformation = mutableListOf<FieldInformation>()
        for (i in 0 until fieldCount) {
            fieldInformation.add(readField(PackageInformation(name, subpackages, classInformation, methodInformation, fieldInformation)))
        }

        return PackageInformation(
            name,
            subpackages,
            classInformation,
            methodInformation,
            fieldInformation,
        )
    }

    fun readClass(parent: PackageInformation): ClassInformation {
        val name = inputStream.readUTF8()

        val flags = inputStream.readShort()
        val superClass = inputStream.readUTF8()

        val interfaceCount = inputStream.readUnsignedShort().toInt()
        val interfaces = mutableListOf<String>()
        for (i in 0 until interfaceCount) {
            interfaces.add(inputStream.readUTF8())
        }

        val classCount = inputStream.readUnsignedShort().toInt()
        val classInformation = mutableListOf<ClassInformation>()
        for (i in 0 until classCount) {
            classInformation.add(readClass(parent))
        }

        val constructorCount = inputStream.readUnsignedShort().toInt()
        val constructorInformation = mutableListOf<ConstructorInformation>()
        for (i in 0 until constructorCount) {
            constructorInformation.add(readConstructor(parent))
        }

        val methodCount = inputStream.readUnsignedShort().toInt()
        val methodInformation = mutableListOf<MethodInformation>()
        for (i in 0 until methodCount) {
            methodInformation.add(readMethod(parent))
        }

        val fieldCount = inputStream.readUnsignedShort().toInt()
        val fieldInformation = mutableListOf<FieldInformation>()
        for (i in 0 until fieldCount) {
            fieldInformation.add(readField(parent))
        }

        return ClassInformation(name, flags, superClass, interfaces, methodInformation, fieldInformation, constructorInformation, classInformation)
    }

    fun readMethod(parent: PackageInformation): MethodInformation {
        val name = inputStream.readUTF8()
        val flags = inputStream.readShort()

        val parameterCount = inputStream.readUnsignedShort().toInt()
        val parameters = mutableListOf<ParameterInformation>()
        for (i in 0 until parameterCount) {
            parameters.add(buildParameter())
        }

        return MethodInformation(name, flags, parameters)
    }

    fun readField(parent: PackageInformation): FieldInformation {
        val name = inputStream.readUTF8()
        val flags = inputStream.readShort()
        val type = inputStream.readUTF8()
        return FieldInformation(name, flags, type)
    }

    fun readConstructor(parent: PackageInformation): ConstructorInformation {
        val name = inputStream.readUTF8()
        val flags = inputStream.readShort()
        val parameterCount = inputStream.readUnsignedShort().toInt()
        val parameters = mutableListOf<ParameterInformation>()
        for (i in 0 until parameterCount) {
            parameters.add(buildParameter())
        }
        return ConstructorInformation(flags, parameters)
    }

    fun buildParameter(): ParameterInformation {
        val name = inputStream.readUTF8()
        val type = inputStream.readUTF8()
        return ParameterInformation(name, type)
    }
}

package com.shakelang.shake.processor.program.map

import com.shakelang.shake.processor.program.map.information.*
import com.shakelang.util.io.streaming.input.bytes.DataInputStream

class MapReader(
    val inputStream: DataInputStream,
) {

    fun readProject(): ProjectInformation {
        // Magic number
        val magicNumber = inputStream.readIntBE()
        if (magicNumber != MapGenerator.MAGIC_NUMBER) throw Exception("Invalid magic number")

        // Major version
        val majorVersion = inputStream.readShort()
        if (majorVersion != 0.toShort()) throw Exception("Invalid major version")

        // Minor version
        val minorVersion = inputStream.readShort()
        if (minorVersion != 1.toShort()) throw Exception("Invalid minor version")

        // Package count
        val packageCount = inputStream.readUnsignedShortBE().toInt()

        // Store all packages
        val packages = mutableListOf<PackageInformation>()
        for (i in 0 until packageCount) {
            packages.add(readPackage())
        }
        return ProjectInformation(packages)
    }

    fun readPackage(): PackageInformation {
        val name = inputStream.readUTF8()

        val subpackageCount = inputStream.readUnsignedShortBE().toInt()
        val subpackages = mutableListOf<PackageInformation>()
        for (i in 0 until subpackageCount) {
            subpackages.add(readPackage())
        }

        val classCount = inputStream.readUnsignedShortBE().toInt()
        val classInformation = mutableListOf<ClassInformation>()
        for (i in 0 until classCount) {
            classInformation.add(readClass())
        }

        val methodCount = inputStream.readUnsignedShortBE().toInt()
        val methodInformation = mutableListOf<MethodInformation>()
        for (i in 0 until methodCount) {
            methodInformation.add(readMethod())
        }

        val fieldCount = inputStream.readUnsignedShortBE().toInt()
        val fieldInformation = mutableListOf<FieldInformation>()
        for (i in 0 until fieldCount) {
            fieldInformation.add(readField())
        }

        return PackageInformation(
            name,
            subpackages,
            classInformation,
            methodInformation,
            fieldInformation,
        )
    }

    fun readClass(): ClassInformation {
        val name = inputStream.readUTF8()

        val flags = inputStream.readShortBE()
        val superClass = inputStream.readUTF8()

        val interfaceCount = inputStream.readUnsignedShortBE().toInt()
        val interfaces = mutableListOf<String>()
        for (i in 0 until interfaceCount) {
            interfaces.add(inputStream.readUTF8())
        }

        val genericCount = inputStream.readUnsignedShortBE().toInt()
        val genericInformation = mutableListOf<GenericInformation>()
        for (i in 0 until genericCount) {
            genericInformation.add(readGeneric())
        }

        val classCount = inputStream.readUnsignedShortBE().toInt()
        val classInformation = mutableListOf<ClassInformation>()
        for (i in 0 until classCount) {
            classInformation.add(readClass())
        }

        val constructorCount = inputStream.readUnsignedShortBE().toInt()
        val constructorInformation = mutableListOf<ConstructorInformation>()
        for (i in 0 until constructorCount) {
            constructorInformation.add(readConstructor())
        }

        val methodCount = inputStream.readUnsignedShortBE().toInt()
        val methodInformation = mutableListOf<MethodInformation>()
        for (i in 0 until methodCount) {
            methodInformation.add(readMethod())
        }

        val fieldCount = inputStream.readUnsignedShortBE().toInt()
        val fieldInformation = mutableListOf<FieldInformation>()
        for (i in 0 until fieldCount) {
            fieldInformation.add(readField())
        }

        return ClassInformation(
            name, flags, superClass, interfaces,
            genericInformation,
            classInformation, constructorInformation, methodInformation, fieldInformation,
        )
    }

    private fun readMethod(): MethodInformation {
        val signa = inputStream.readUTF8()

        val parameterCount = inputStream.readUnsignedShortBE().toInt()
        val parameterNames = mutableListOf<String>()
        for (i in 0 until parameterCount) {
            parameterNames.add(inputStream.readUTF8())
        }

        val flags = inputStream.readShortBE()
        return MethodInformation(signa, parameterNames, flags)
    }

    private fun readField(): FieldInformation {
        val name = inputStream.readUTF8()
        val flags = inputStream.readShortBE()
        val type = inputStream.readUTF8()
        val expanding = inputStream.readUTF8()
        return FieldInformation(name, flags, type, expanding)
    }

    fun readConstructor(): ConstructorInformation {
        val signature = inputStream.readUTF8()
        val flags = inputStream.readShortBE()
        return ConstructorInformation(signature, flags)
    }

    private fun readGeneric(): GenericInformation {
        val name = inputStream.readUTF8()
        val type = inputStream.readUTF8()
        return GenericInformation(name, type)
    }
}

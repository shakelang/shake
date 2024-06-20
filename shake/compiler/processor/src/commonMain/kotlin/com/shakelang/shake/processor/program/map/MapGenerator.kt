package com.shakelang.shake.processor.program.map

import com.shakelang.shake.processor.program.map.information.*
import com.shakelang.shake.processor.program.types.ShakeProject
import com.shakelang.util.io.streaming.output.bytes.DataOutputStream
import com.shakelang.util.io.streaming.output.bytes.OutputStream

class StorageConfiguration

class MapGenerator(
    val output: DataOutputStream,
) {

    constructor(output: OutputStream) : this(DataOutputStream(output))

    companion object {
        const val MAGIC_NUMBER: Int = 0x4a16a479
    }

    val configuration = StorageConfiguration()

    fun store(project: ShakeProject) = store(InformationConverter.toInformation(project))

    fun store(project: ProjectInformation) {
        // Magic number
        output.writeIntBE(MAGIC_NUMBER)

        // Major version
        output.writeShort(0)

        // Minor version
        output.writeShort(1)

        // Package count
        output.writeUnsignedShortBE(project.packages.size.toUShort())

        // Store all packages
        for (pkg in project.packages) {
            store(pkg)
        }
    }

    fun store(pkg: PackageInformation) {
        // Package name
        output.writeUTF8(pkg.name)

        // Subpackage count
        output.writeUnsignedShortBE(pkg.subpackages.size.toUShort())

        // Store all subpackages
        for (spkg in pkg.subpackages) {
            store(spkg)
        }

        // Class count
        output.writeUnsignedShortBE(pkg.classInformation.size.toUShort())

        // Store all classes
        for (cls in pkg.classInformation) {
            store(cls)
        }

        // Method count
        output.writeUnsignedShortBE(pkg.methodInformation.size.toUShort())

        // Store all methods
        for (method in pkg.methodInformation) {
            store(method)
        }

        // Field count
        output.writeUnsignedShortBE(pkg.fieldInformation.size.toUShort())

        // Store all fields
        for (field in pkg.fieldInformation) {
            store(field)
        }
    }

    fun store(cls: ClassInformation) {
        // Class name
        output.writeUTF8(cls.name)

        // Class flags
        output.writeShortBE(cls.flags)

        // Superclass name
        output.writeUTF8(cls.superClass)

        // Interface count
        output.writeUnsignedShortBE(cls.interfaces.size.toUShort())

        // Store all interfaces
        for (inter in cls.interfaces) {
            output.writeUTF8(inter)
        }

        // Subclass count
        output.writeUnsignedShortBE(cls.classInformation.size.toUShort())

        // Store all subclasses
        for (subclass in cls.classInformation) {
            store(subclass)
        }

        // Constructor count
        output.writeUnsignedShortBE(cls.constructorInformation.size.toUShort())

        // Store all constructors
        for (constructor in cls.constructorInformation) {
            store(constructor)
        }

        // Method count
        output.writeUnsignedShortBE(cls.methodInformation.size.toUShort())

        // Store all methods
        for (method in cls.methodInformation) {
            store(method)
        }

        // Field count
        output.writeUnsignedShortBE(cls.fieldInformation.size.toUShort())

        // Store all fields
        for (field in cls.fieldInformation) {
            store(field)
        }
    }

    fun store(constructor: ConstructorInformation) {
        // Constructor name
        output.writeUTF8(constructor.signature)

        // Constructor flags
        output.writeShortBE(constructor.flags)
    }

    fun store(method: MethodInformation) {
        // Method name
        output.writeUTF8(method.signature)

        // Parameter count
        output.writeUnsignedShortBE(method.parameterNames.size.toUShort())

        // Store all parameter names
        for (param in method.parameterNames) {
            output.writeUTF8(param)
        }

        // Method flags
        output.writeShortBE(method.flags)
    }

    fun store(field: FieldInformation) {
        // Field name
        output.writeUTF8(field.name)

        // Field flags
        output.writeShortBE(field.flags)

        // Field type
        output.writeUTF8(field.type)

        // Field expanding
        output.writeUTF8(field.expanding)
    }
}

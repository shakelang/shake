package com.shakelang.shake.processor.program.map

import com.shakelang.shake.processor.program.creation.*
import com.shakelang.util.io.streaming.output.bytes.DataOutputStream
import com.shakelang.util.io.streaming.output.bytes.OutputStream

class StorageConfiguration

class MapGenerator(
    val output: DataOutputStream,
) {

    constructor(output: OutputStream) : this(DataOutputStream(output))

    companion object {
        const val MAGIC_NUMBER: Short = 0x5348
    }

    val configuration = StorageConfiguration()

    fun store(project: CreationShakeProject) {
        // Magic number
        output.writeShort(MAGIC_NUMBER)

        // Major version
        output.writeByte(0)

        // Minor version
        output.writeByte(1)

        // Package count
        output.writeUnsignedShort(project.subpackages.size.toUShort())

        // Store all packages
        for (pkg in project.subpackages) {
            store(pkg)
        }
    }

    fun store(pkg: CreationShakePackage) {
        // Package name
        output.writeUTF8(pkg.name)

        // Subpackage count
        output.writeUnsignedShort(pkg.subpackages.size.toUShort())

        // Store all subpackages
        for (spkg in pkg.subpackages) {
            store(spkg)
        }

        // Class count
        output.writeUnsignedShort(pkg.classes.size.toUShort())

        // Store all classes
        for (cls in pkg.classes) {
            store(cls)
        }

        // Method count
        output.writeUnsignedShort(pkg.functions.size.toUShort())

        // Store all methods
        for (method in pkg.functions) {
            store(method)
        }

        // Field count
        output.writeUnsignedShort(pkg.fields.size.toUShort())

        // Store all fields
        for (field in pkg.fields) {
            store(field)
        }
    }

    fun store(cls: CreationShakeClass) {
        // Class name
        output.writeUTF8(cls.name)

        // Class flags
        output.writeShort(cls.flags)

        // Superclass name
        output.writeUTF8(cls.superClass.signature)

        // Interface count
        output.writeUnsignedShort(cls.interfaces.size.toUShort())

        // Store all interfaces
        for (inter in cls.interfaces) {
            output.writeUTF8(inter.signature)
        }

        // Subclass count
        output.writeUnsignedShort((cls.classes.size + cls.staticClasses.size).toUShort())

        // Store all subclasses
        for (subclass in cls.classes) {
            store(subclass)
        }

        for (subclass in cls.staticClasses) {
            store(subclass)
        }

        // Constructor count
        output.writeUnsignedShort(cls.constructors.size.toUShort())

        // Store all constructors
        for (constructor in cls.constructors) {
            store(constructor)
        }

        // Method count
        output.writeUnsignedShort((cls.methods.size + cls.staticMethods.size).toUShort())

        // Store all methods
        for (method in cls.methods) {
            store(method)
        }

        for (method in cls.staticMethods) {
            store(method)
        }

        // Field count
        output.writeUnsignedShort((cls.fields.size + cls.staticFields.size).toUShort())

        // Store all fields
        for (field in cls.fields) {
            store(field)
        }

        for (field in cls.staticFields) {
            store(field)
        }
    }

    fun store(constructor: CreationShakeConstructor) {
        // Constructor name
        output.writeUTF8(constructor.name)

        // Constructor flags
        output.writeShort(constructor.flags)

        // Parameter count
        output.writeUnsignedShort(constructor.parameters.size.toUShort())

        // Store all parameters
        for (parameter in constructor.parameters) {
            store(parameter)
        }
    }

    fun store(method: CreationShakeMethod) {
        // Method name
        output.writeUTF8(method.name)

        // Method flags
        output.writeShort(method.flags)

        // Method parameter count
        output.writeUnsignedShort(method.parameters.size.toUShort())

        // Store all parameters
        for (parameter in method.parameters) {
            store(parameter)
        }
    }

    fun store(field: CreationShakeField) {
        // Field name
        output.writeUTF8(field.name)

        // Field flags
        output.writeShort(field.flags)

        // Field type
        output.writeUTF8(field.type.qualifiedName)
    }

    fun store(parameter: CreationShakeParameter) {
        // Parameter name
        output.writeUTF8(parameter.name)

        // Parameter type
        output.writeUTF8(parameter.type.qualifiedName)
    }
}

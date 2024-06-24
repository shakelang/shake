package com.shakelang.shake.processor.program.map

import com.shakelang.shake.bytecode.interpreter.format.descriptor.MethodDescriptor
import com.shakelang.shake.conventions.descriptor.TypeDescriptor
import com.shakelang.shake.processor.ShakeASTProcessor
import com.shakelang.shake.processor.program.creation.*
import com.shakelang.shake.processor.program.map.information.*
import com.shakelang.shake.processor.program.types.*

object InformationConverter {

    fun toInformation(info: ShakeProject): ProjectInformation =
        ProjectInformation(
            info.subpackages.map {
                toInformation(it)
            },
        )

    fun toInformation(info: ShakePackage): PackageInformation =
        PackageInformation(
            info.name,
            info.subpackages.map {
                toInformation(it)
            },
            info.classes.map {
                toInformation(it)
            },
            info.functions.map {
                toInformation(it)
            },
            info.fields.map {
                toInformation(it)
            },
        )

    fun toInformation(info: ShakeClass): ClassInformation =
        ClassInformation(
            info.name,
            info.flags.toShort(),
            info.superClass.qualifiedName,
            info.interfaces.map {
                it.qualifiedName
            },
            info.generics.map {
                GenericInformation(it.name, it.base?.qualifiedName ?: "")
            },
            (info.classes + info.staticClasses).map {
                toInformation(it)
            },
            info.constructors.map {
                toInformation(it)
            },
            (info.methods + info.staticMethods).map {
                toInformation(it)
            },
            (info.fields + info.staticFields).map {
                toInformation(it)
            },
        )

    fun toInformation(info: ShakeMethod): MethodInformation =
        MethodInformation(
            info.signature,
            info.parameters.map {
                it.name
            },
            info.flags.toShort(),
        )

    fun toInformation(info: ShakeField): FieldInformation =
        FieldInformation(
            info.name,
            info.flags.toShort(),
            info.type.qualifiedName,
            info.expanding?.qualifiedName ?: "",
        )

    fun toInformation(info: ShakeConstructor): ConstructorInformation =
        ConstructorInformation(
            info.signature,
            info.flags,
        )

    fun recreate(info: ProjectInformation): CreationShakeProject {
        val recreator = InformationRecreator()
        return recreator.recreate(info)
    }
}

class InformationRecreator(
    val project: CreationShakeProject,
) {

    private val packageList = mutableListOf<Pair<PackageInformation, CreationShakePackage>>()
    private val classList = mutableListOf<Pair<ClassInformation, CreationShakeClass>>()
    private val methodList = mutableListOf<Pair<MethodInformation, CreationShakeMethod>>()
    private val fieldList = mutableListOf<Pair<FieldInformation, CreationShakeField>>()

    constructor() : this(
        CreationShakeProject(
            ShakeASTProcessor(),
            mutableListOf(),
        ),
    )

    fun recreate(info: ProjectInformation): CreationShakeProject {
        val project = project
        project.subpackages.addAll(
            info.packages.map {
                recreate(it)
            },
        )

        phase1()
        phase2()
        phase3()

        this.packageList.clear()
        this.classList.clear()
        this.methodList.clear()
        this.fieldList.clear()

        return project
    }

    fun recreate(
        info: PackageInformation,
        parent: CreationShakePackage? = null,
    ): CreationShakePackage {
        val pack = CreationShakePackage(
            project,
            info.name,
            parent,
        )

        CreationShakePackage.disablePhases(pack)

        pack.subpackages.addAll(
            info.subpackages.map {
                recreate(it, pack)
            },
        )

        packageList.add(info to pack)

        return pack
    }

    fun phase1() {
        // Load all classes

        for ((info, pack) in packageList) {
            pack.classes.addAll(
                info.classInformation.map {
                    recreate(it, pack)
                },
            )
        }
    }

    fun recreate(
        info: ClassInformation,
        pkg: CreationShakePackage,
        clz: CreationShakeClass? = null,
    ): CreationShakeClass {
        val clazz = CreationShakeClass(
            project,
            info.name,
            pkg,
            clz,
            pkg.scope,
            info.flags,
        )

        CreationShakeClass.disablePhases(clazz)

        classList.add(info to clazz)

        CreationShakeClass.exposeMutableClasses(clazz).addAll(
            info.classInformation.map {
                recreate(it, pkg, clazz)
            },
        )

        return clazz
    }

    fun phase2() {
        // Link superclasses and interfaces

        for ((info, clazz) in classList) {
            CreationShakeClass.initSuper(clazz, TypeStorage.from(info.superClass).resolve(project) as? CreationShakeType.Object ?: error("Superclass '${info.superClass}' not found"))
            CreationShakeClass.initInterfaces(
                clazz,
                info.interfaces.map {
                    TypeStorage.from(it).resolve(project) as? CreationShakeType.Object ?: error("Interface '$it' not found")
                },
            )

            CreationShakeClass.exposeMutableGenerics(clazz).addAll(
                info.genericInformation.map {
                    CreationShakeType.Generic(
                        it.name,
                        TypeStorage.from(it.type).resolve(project) as? CreationShakeType.Object ?: error("Base type '${it.type}' not found"),
                    )
                },
            )
        }
    }

    fun phase3() {
        // Load all methods and fields

        for ((info, pack) in packageList) {
            pack.functions.addAll(
                info.methodInformation.map {
                    recreate(it, pack, null)
                },
            )
            pack.fields.addAll(
                info.fieldInformation.map {
                    recreate(it, pack, null)
                },
            )
        }

        for ((info, clazz) in classList) {
            CreationShakeClass.exposeMutableMethods(clazz).addAll(
                info.methodInformation.map {
                    recreate(it, clazz.pkg, clazz)
                },
            )
            CreationShakeClass.exposeMutableFields(clazz).addAll(
                info.fieldInformation.map {
                    recreate(it, clazz.pkg, clazz)
                },
            )
        }
    }

    fun recreateType(type: TypeDescriptor): CreationShakeType = when (type) {
        is TypeDescriptor.ByteType -> CreationShakeType.Primitives.BYTE
        is TypeDescriptor.ShortType -> CreationShakeType.Primitives.SHORT
        is TypeDescriptor.IntType -> CreationShakeType.Primitives.INT
        is TypeDescriptor.LongType -> CreationShakeType.Primitives.LONG
        is TypeDescriptor.UnsignedByteType -> CreationShakeType.Primitives.UNSIGNED_BYTE
        is TypeDescriptor.UnsignedShortType -> CreationShakeType.Primitives.UNSIGNED_SHORT
        is TypeDescriptor.UnsignedIntType -> CreationShakeType.Primitives.UNSIGNED_INT
        is TypeDescriptor.UnsignedLongType -> CreationShakeType.Primitives.UNSIGNED_LONG
        is TypeDescriptor.FloatType -> CreationShakeType.Primitives.FLOAT
        is TypeDescriptor.DoubleType -> CreationShakeType.Primitives.DOUBLE
        is TypeDescriptor.CharType -> CreationShakeType.Primitives.CHAR
        is TypeDescriptor.BooleanType -> CreationShakeType.Primitives.BOOLEAN
        is TypeDescriptor.VoidType -> CreationShakeType.Primitives.VOID
        is TypeDescriptor.DynamicType -> CreationShakeType.Primitives.DYNAMIC
        is TypeDescriptor.NewType -> throw Exception("NewType not supported")
        is TypeDescriptor.ArrayType -> CreationShakeType.objectType(
            project.getClass("shake/lang/Array") ?: error("Core class 'Array' not found"),
            listOf(recreateType(type.type)),
        )
        is TypeDescriptor.ObjectType -> CreationShakeType.objectType(
            project.getClass(type.className) ?: error("Class '${type.className}' not found"),
            type.genericTypes.map {
                recreateType(it)
            },
        )
        else -> {
            throw Exception("Unknown type descriptor for reconstruction: $type")
        }
    }

    fun recreate(info: MethodInformation, pkg: CreationShakePackage, clz: CreationShakeClass?): CreationShakeMethod {
        val descriptor = MethodDescriptor.parse(info.signature)

        val flags = ShakeMethod.ShakeMethodFlags(
            info.flags.toInt(),
        )

        val returnType = recreateType(descriptor.returnType)
        val parameterTypes = descriptor.parameters.map {
            recreateType(it)
        }.toMutableList()

        val expanding = if (flags.isExtension) {
            parameterTypes.removeFirst()
        } else {
            null
        }

        val parameters = parameterTypes.mapIndexed { index, type ->
            CreationShakeParameter(
                project,
                name = info.parameterNames.getOrNull(index) ?: "arg$index",
                type = type,
            )
        }

        val method = CreationShakeMethod(
            project,
            pkg,
            clz,
            if (flags.isStatic) {
                clz?.staticScope
            } else {
                clz?.instanceScope
            } ?: pkg.scope,
            descriptor.name,
            flags,
            returnType,
            parameters,
            expanding,
        )

        CreationShakeMethod.disablePhases(method)
        methodList.add(info to method)
        return method
    }

    fun recreate(info: FieldInformation, pkg: CreationShakePackage, clz: CreationShakeClass?): CreationShakeField {
        val flags = ShakeField.ShakeFieldFlags(
            info.flags.toInt(),
        )

        val field = CreationShakeField(
            project,
            pkg,
            clz,
            if (flags.isStatic) {
                clz?.staticScope
            } else {
                clz?.instanceScope
            } ?: pkg.scope,
            info.name,
            flags,
            recreateType(TypeDescriptor.parse(info.type)),
            if (flags.isExtension) recreateType(TypeDescriptor.parse(info.type)) else null,
        )

        CreationShakeField.disablePhases(field)
        fieldList.add(info to field)
        return field
    }
}

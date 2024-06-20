package com.shakelang.shake.processor.program.map

import com.shakelang.shake.processor.program.map.information.*
import com.shakelang.shake.processor.program.types.*

object InformationConverter {

    fun toInformation(info: ShakeProject): ProjectInformation =
        ProjectInformation(
            info.subpackages.map {
                InformationConverter.toInformation(it)
            },
        )

    fun toInformation(info: ShakePackage): PackageInformation =
        PackageInformation(
            info.name,
            info.subpackages.map {
                InformationConverter.toInformation(it)
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
            info.flags,
            info.superClass.signature,
            info.interfaces.map {
                it.signature
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
            info.flags,
        )

    fun toInformation(info: ShakeField): FieldInformation =
        FieldInformation(
            info.name,
            info.flags,
            info.type.qualifiedName,
        )

    fun toInformation(info: ShakeConstructor): ConstructorInformation =
        ConstructorInformation(
            info.signature,
            info.flags,
        )
}

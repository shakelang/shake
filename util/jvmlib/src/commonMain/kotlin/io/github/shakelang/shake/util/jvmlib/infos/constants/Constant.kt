package io.github.shakelang.shake.util.jvmlib.infos.constants

object Constant {

    fun utf8(value: String): ConstantUtf8Info = ConstantUtf8Info(value)
    fun integer(value: Int): ConstantIntegerInfo = ConstantIntegerInfo(value)
    fun float(value: Float): ConstantFloatInfo = ConstantFloatInfo(value)
    fun long(value: Long): ConstantLongInfo = ConstantLongInfo(value)
    fun double(value: Double): ConstantDoubleInfo = ConstantDoubleInfo(value)
    fun classRef(classRef: UShort): ConstantClassInfo = ConstantClassInfo(classRef)
    fun string(value: UShort): ConstantStringInfo = ConstantStringInfo(value)
    fun fieldRef(classRef: UShort, nameAndTypeRef: UShort): ConstantFieldrefInfo = ConstantFieldrefInfo(classRef, nameAndTypeRef)
    fun methodRef(classRef: UShort, nameAndTypeRef: UShort): ConstantMethodrefInfo = ConstantMethodrefInfo(classRef, nameAndTypeRef)
    fun interfaceMethodRef(classRef: UShort, nameAndTypeRef: UShort): ConstantInterfaceMethodrefInfo = ConstantInterfaceMethodrefInfo(classRef, nameAndTypeRef)
    fun nameAndType(name: UShort, descriptor: UShort): ConstantNameAndTypeInfo = ConstantNameAndTypeInfo(descriptor, name)
    fun methodHandle(referenceKind: Byte, reference: UShort): ConstantMethodHandleInfo = ConstantMethodHandleInfo(referenceKind, reference)
    fun methodType(descriptor: UShort): ConstantMethodTypeInfo = ConstantMethodTypeInfo(descriptor)
    fun invokeDynamic(bootstrapMethod: UShort, nameAndType: UShort): ConstantInvokeDynamicInfo = ConstantInvokeDynamicInfo(bootstrapMethod, nameAndType)

}
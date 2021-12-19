package io.github.shakelang.jvmlib.infos.constants

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ConstantPoolTests {

    @Test
    fun testGet() {
        val cp = ConstantPool()
        cp.add(Constant.utf8("Hello"))
        cp.add(Constant.utf8("World"))
        assertTrue(cp[1] is ConstantUtf8Info)
        assertEquals("Hello", (cp[1] as ConstantUtf8Info).value)
        assertTrue(cp[2] is ConstantUtf8Info)
        assertEquals("World", (cp[2] as ConstantUtf8Info).value)
    }

    @Test
    fun testGetConstantUtf8Info() {
        val cp = ConstantPool()
        cp.add(Constant.utf8("Hello"))
        cp.add(Constant.string(1u))
        cp.getUtf8(1)
        cp.getUtf8(1u)
    }

    @Test
    fun testGetConstantNameAndTypeInfo() {
        val cp = ConstantPool()
        cp.add(Constant.utf8("Hello"))
        cp.add(Constant.utf8("I"))
        cp.add(Constant.nameAndType(1u, 2u))
        cp.getNameAndType(3)
        cp.getNameAndType(3u)
    }

    @Test
    fun testGetConstantMethodHandleInfo() {
        val cp = ConstantPool()
        cp.add(Constant.utf8("Hello"))
        cp.add(Constant.methodHandle(1, 1u))
        cp.getMethodHandle(2)
        cp.getMethodHandle(2u)
    }

    @Test
    fun testGetConstantMethodTypeInfo() {
        val cp = ConstantPool()
        cp.add(Constant.utf8("I"))
        cp.add(Constant.methodType(1u))
        cp.getMethodType(2)
        cp.getMethodType(2u)
    }

    @Test
    fun testGetConstantInvokeDynamicInfo() {
        val cp = ConstantPool()
        cp.add(Constant.utf8("Hello"))
        cp.add(Constant.utf8("I"))
        cp.add(Constant.nameAndType(1u, 2u))
        cp.add(Constant.invokeDynamic(1u, 3u))
        cp.getInvokeDynamic(4)
        cp.getInvokeDynamic(4u)
    }

    @Test
    fun testGetConstantFieldRefInfo() {
        val cp = ConstantPool()
        cp.add(Constant.utf8("Hello"))
        cp.add(Constant.utf8("I"))
        cp.add(Constant.nameAndType(1u, 2u))
        cp.add(Constant.utf8("java/lang/Object"))
        cp.add(Constant.classRef(4u))
        cp.add(Constant.fieldRef(5u, 3u))
        cp.getFieldRef(6)
        cp.getFieldRef(6u)
    }

    @Test
    fun testGetConstantMethodRefInfo() {
        val cp = ConstantPool()
        cp.add(Constant.utf8("Hello"))
        cp.add(Constant.utf8("I"))
        cp.add(Constant.nameAndType(1u, 2u))
        cp.add(Constant.utf8("java/lang/Object"))
        cp.add(Constant.classRef(4u))
        cp.add(Constant.methodRef(5u, 3u))
        cp.getMethodRef(6)
        cp.getMethodRef(6u)
    }

    @Test
    fun testGetConstantInterfaceMethodRefInfo() {
        val cp = ConstantPool()
        cp.add(Constant.utf8("Hello"))
        cp.add(Constant.utf8("I"))
        cp.add(Constant.nameAndType(1u, 2u))
        cp.add(Constant.utf8("java/lang/Object"))
        cp.add(Constant.classRef(4u))
        cp.add(Constant.interfaceMethodRef(5u, 3u))
        cp.getInterfaceMethodRef(6)
        cp.getInterfaceMethodRef(6u)
    }

    @Test
    fun testGetConstantStringInfo() {
        val cp = ConstantPool()
        cp.add(Constant.utf8("Hello"))
        cp.add(Constant.string(1u))
        cp.getString(2)
        cp.getString(2u)
    }

    @Test
    fun testGetConstantIntegerInfo() {
        val cp = ConstantPool()
        cp.add(Constant.integer(1))
        cp.getInteger(1)
        cp.getInteger(1u)
    }

    @Test
    fun testGetConstantFloatInfo() {
        val cp = ConstantPool()
        cp.add(Constant.float(1.0f))
        cp.getFloat(1)
        cp.getFloat(1u)
    }

    @Test
    fun testGetConstantLongInfo() {
        val cp = ConstantPool()
        cp.add(Constant.long(1L))
        cp.getLong(1)
        cp.getLong(1u)
    }

    @Test
    fun testGetConstantDoubleInfo() {
        val cp = ConstantPool()
        cp.add(Constant.double(1.0))
        cp.getDouble(1)
        cp.getDouble(1u)
    }

    @Test
    fun testGetConstantClassInfo() {
        val cp = ConstantPool()
        cp.add(Constant.utf8("Hello"))
        cp.add(Constant.classRef(1u))
        cp.getClass(2)
        cp.getClass(2u)
        cp.getClassRef(2)
        cp.getClassRef(2u)
    }

}
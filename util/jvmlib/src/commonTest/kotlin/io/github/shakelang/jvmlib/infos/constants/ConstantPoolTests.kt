package io.github.shakelang.jvmlib.infos.constants

import io.github.shakelang.shake.util.io.streaming.output.DataOutputStream
import kotlin.test.*

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

    @Test
    fun testInitialisation() {
        var bool0 = false
        val ci0 = object: ConstantInfo() {

            override val tag: Byte
                get() = 0

            override val tagName: String
                get() = "dummy"

            override fun dump(out: DataOutputStream)
                    = throw UnsupportedOperationException()

            override fun init(pool: ConstantPool) {
                super.init(pool)
                bool0 = true
            }

        }
        var bool1 = false
        val ci1 = object: ConstantInfo() {

            override val tag: Byte
                get() = 0

            override val tagName: String
                get() = "dummy"

            override fun dump(out: DataOutputStream)
                    = throw UnsupportedOperationException()

            override fun init(pool: ConstantPool) {
                super.init(pool)
                bool1 = true
            }

        }

        val cp = ConstantPool(mutableListOf(ci0))
        assertTrue(bool0)
        assertFalse(bool1)
        cp.add(ci1)
        assertTrue(bool0)
        assertTrue(bool1)
    }

    private fun findPool(): ConstantPool {
        val cp = ConstantPool()
        /*01*/cp.add(Constant.utf8("Hello"))
        /*02*/cp.add(Constant.utf8("I"))
        /*03*/cp.add(Constant.nameAndType(1u, 2u))
        /*04*/cp.add(Constant.classRef(1u))
        /*05*/cp.add(Constant.utf8("java/lang/Object"))
        /*06*/cp.add(Constant.classRef(5u))
        /*07*/cp.add(Constant.fieldRef(4u, 3u))
        /*08*/cp.add(Constant.methodRef(4u, 3u))
        /*09*/cp.add(Constant.interfaceMethodRef(4u, 3u))
        /*10*/cp.add(Constant.string(1u))
        /*11*/cp.add(Constant.integer(1))
        /*12*/cp.add(Constant.float(1.0f))
        /*13*/cp.add(Constant.long(1L))
        /*14*/cp.add(Constant.double(1.0))
        /*15*/cp.add(Constant.invokeDynamic(2u, 3u))


        return cp
    }

    @Test
    fun testFindClass() {
        val cp = findPool()
        assertSame(cp[4], cp.findClass(cp.getUtf8(1))!!)
        assertSame(cp[6], cp.findClass("java/lang/Object")!!)
    }

    @Test
    fun testFindDouble() {
        val cp = findPool()
        assertSame(cp[14], cp.findDouble(1.0)!!)
    }

    @Test
    fun testFindFieldRef() {
        val cp = findPool()
        assertSame(cp[7], cp.findFieldRef(cp.getClassRef(4), cp.getNameAndType(3))!!)
        assertSame(cp[7], cp.findFieldRef(cp.getClassRef(4), "Hello", "I")!!)
        assertSame(cp[7], cp.findFieldRef("Hello", "Hello", "I")!!)
    }

    @Test
    fun testFindFloat() {
        val cp = findPool()
        assertSame(cp[12], cp.findFloat(1.0f)!!)
    }

    @Test
    fun testFindInteger() {
        val cp = findPool()
        assertSame(cp[11], cp.findInteger(1)!!)
    }

    @Test
    fun testFindInterfaceMethodRef() {
        val cp = findPool()
        assertSame(cp[9], cp.findInterfaceMethodRef(cp.getClassRef(4), cp.getNameAndType(3))!!)
        assertSame(cp[9], cp.findInterfaceMethodRef(cp.getClassRef(4), "Hello", "I")!!)
        assertSame(cp[9], cp.findInterfaceMethodRef("Hello", "Hello", "I")!!)
    }

    @Test
    fun testFindInvokeDynamic() {
        val cp = findPool()
        assertSame(cp[15], cp.findInvokeDynamic(2u, cp.getNameAndType(3))!!)
        assertSame(cp[15], cp.findInvokeDynamic(2u, "Hello", "I")!!)
    }

    @Test
    fun testFindLong() {
        val cp = findPool()
        assertSame(cp[13], cp.findLong(1L)!!)
    }

    @Test
    fun testFindMethodRef() {
        val cp = findPool()
        assertSame(cp[8], cp.findMethodRef(cp.getClassRef(4), cp.getNameAndType(3))!!)
        assertSame(cp[8], cp.findMethodRef(cp.getClassRef(4), "Hello", "I")!!)
        assertSame(cp[8], cp.findMethodRef("Hello", "Hello", "I")!!)
    }

    @Test
    fun testFindNameAndType() {
        val cp = findPool()
        assertSame(cp[3], cp.findNameAndType(cp.getUtf8(1), cp.getUtf8(2))!!)
        assertSame(cp[3], cp.findNameAndType("Hello", "I")!!)
    }

    @Test
    fun testFindString() {
        val cp = findPool()
        assertSame(cp[10], cp.findString(cp.getUtf8(1))!!)
        assertSame(cp[10], cp.findString("Hello")!!)
    }

    @Test
    fun testFindUtf8() {
        val cp = findPool()
        assertSame(cp[1], cp.findUtf8("Hello")!!)
    }

    @Test
    fun testExpectClass() {
        val cp = findPool()
        assertSame(cp[4], cp.expectClass(cp.getUtf8(1)))
        assertSame(cp[6], cp.expectClass("java/lang/Object"))
        assertFailsWith<Exception> { cp.expectClass(cp.getUtf8(2)) }
        assertFailsWith<Exception> { cp.expectClass("java/lang/String") }
    }

    @Test
    fun testExpectDouble() {
        val cp = findPool()
        assertSame(cp[14], cp.expectDouble(1.0))
        assertFailsWith<Exception> { cp.expectDouble(2.0) }
    }

}
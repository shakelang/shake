package io.github.shakelang.jvmlib

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class ClassFileReaderTests {

    val javaLocations: Map<String, File>;

    init {
        val javaDir = File("src/commonTest/resources/classes/java")

        if (!javaDir.exists()) throw Exception("Java classes directory does not exist")

        val subDirs = javaDir.listFiles() ?: throw Exception("Java classes directory is empty")

        javaLocations = mapOf(
            *(( subDirs.map {
                it.name to it
            }).toTypedArray()
        ))
    }

    fun testHelloWorldClass(java: File) {
        val classFile = File(java, "HelloWorld.class")
        val clazz = ClassFileReader.readClass(classFile.inputStream())

        assertEquals(0u, clazz.minorVersion)
        assertEquals(52u, clazz.majorVersion)
        assertEquals("HelloWorld", clazz.name)
        assertEquals("java/lang/Object", clazz.superClass.className)
        assertEquals(0, clazz.interfaces.size)
        assertEquals(0, clazz.fieldInfos.size)
        assertEquals(2, clazz.methodInfos.size)
        assertEquals(1, clazz.attributeInfos.size)

        assertEquals(true, clazz.isPublic)
        assertEquals(false, clazz.isFinal)
        assertEquals(true, clazz.isSuper)
        assertEquals(false, clazz.isInterface)
        assertEquals(false, clazz.isAbstract)
        assertEquals(false, clazz.isSynthetic)
        assertEquals(false, clazz.isAnnotation)
        assertEquals(false, clazz.isEnum)

    }

    @Test fun testjava6HelloWorld() = testHelloWorldClass(javaLocations["java6"]!!)
    @Test fun testjava7HelloWorld() = testHelloWorldClass(javaLocations["java7"]!!)
    @Test fun testjava8HelloWorld() = testHelloWorldClass(javaLocations["java8"]!!)
    @Test fun testjava9HelloWorld() = testHelloWorldClass(javaLocations["java9"]!!)
    @Test fun testjava10HelloWorld() = testHelloWorldClass(javaLocations["java10"]!!)
    @Test fun testjava11HelloWorld() = testHelloWorldClass(javaLocations["java11"]!!)
    @Test fun testjava12HelloWorld() = testHelloWorldClass(javaLocations["java12"]!!)
    @Test fun testjava13HelloWorld() = testHelloWorldClass(javaLocations["java13"]!!)
    @Test fun testjava14HelloWorld() = testHelloWorldClass(javaLocations["java14"]!!)
    @Test fun testjava15HelloWorld() = testHelloWorldClass(javaLocations["java15"]!!)
    @Test fun testjava16HelloWorld() = testHelloWorldClass(javaLocations["java16"]!!)
    @Test fun testjava17HelloWorld() = testHelloWorldClass(javaLocations["java17"]!!)
    @Test fun testjava18HelloWorld() = testHelloWorldClass(javaLocations["java18"]!!)
    @Test fun testjava19HelloWorld() = testHelloWorldClass(javaLocations["java19"]!!)
    @Test fun testjava20HelloWorld() = testHelloWorldClass(javaLocations["java20"]!!)
    @Test fun testjava21HelloWorld() = testHelloWorldClass(javaLocations["java21"]!!)

}
package io.github.shakelang.jvmlib

import java.io.File
import kotlin.test.BeforeTest
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

        assertEquals(clazz.minorVersion, 0u)
        assertEquals(clazz.majorVersion, 52u)
        assertEquals(clazz.accessFlags, 33u)

    }

    @Test
    fun testjava6HelloWorld() = testHelloWorldClass(javaLocations["java6"]!!)
}
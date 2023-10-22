package io.github.shakelang.parseutils

import io.github.shakelang.parseutils.File
import io.github.shakelang.parseutils.bytes.toBytes
import io.github.shakelang.parseutils.resourceFile
import kotlin.test.*

class FileTests {

    @Test
    fun testAbsolutePath() {
        assertTrue ( File("test").absolutePath.endsWith("test") )
    }

    @Test
    fun testIsFile() {
        assertFalse ( File("./src").isFile )
        println(File("./src/commonTest/resources/test").absolutePath)
        assertTrue ( File("./src/commonTest/resources/test").isFile )
    }

    @Test
    fun testIsDirectory() {
        assertTrue ( File("./src/").isDirectory )
        assertFalse ( File("./src/commonTest/resources/test").isDirectory )
    }

    @Test
    fun testParent() {
        val file = File("./src/commonTest/resources/test")
        assertEquals (file.absolutePath.subSequence(0, file.absolutePath.length-5) , file.parent.absolutePath)
    }

    @Test
    fun testParentPath() {
        val file = File("./src/commonTest/resources/test")
        assertEquals (file.path.subSequence(0, file.path.length-5) , file.parent.path)
    }

    @Test
    fun testRead() {
        val file = File("./src/commonTest/resources/test")
        assertEquals("aaa", file.contentsString)
    }

    @Test
    fun testReadChars() {
        val file = File("./src/commonTest/resources/test")
        assertContentEquals("aaa".toCharArray(),  file.contents)
    }

}
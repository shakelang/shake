package io.github.shakelang.parseutils

import kotlin.test.*

class FileTests {

    @Ignore
    @Test
    fun testAbsolutePath() {
        assertTrue ( File("test").absolutePath.endsWith("test") )
    }

    @Ignore
    @Test
    fun testIsFile() {
        assertFalse ( File("./src").isFile )
        println(File("./src/commonTest/resources/test").absolutePath)
        assertTrue ( File("./src/commonTest/resources/test").isFile )
    }

    @Ignore
    @Test
    fun testIsDirectory() {
        assertTrue ( File("./src/").isDirectory )
        assertFalse ( File("./src/commonTest/resources/test").isDirectory )
    }

    @Ignore
    @Test
    fun testParent() {
        val file = File("./src/commonTest/resources/test")
        assertEquals (file.absolutePath.subSequence(0, file.absolutePath.length-5) , file.parent.absolutePath)
    }

    @Ignore
    @Test
    fun testParentPath() {
        val file = File("./src/commonTest/resources/test")
        assertEquals (file.path.subSequence(0, file.path.length-5) , file.parent.path)
    }

    @Ignore
    @Test
    fun testRead() {
        val file = File("./src/commonTest/resources/test")
        assertEquals("aaa", file.contentsString)
    }

    @Ignore
    @Test
    fun testReadChars() {
        val file = File("./src/commonTest/resources/test")
        assertContentEquals("aaa".toCharArray(),  file.contents)
    }

}
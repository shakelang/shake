package io.github.shakelang.environment

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertSame

class EnvironmentTests {

    @Test
    fun test() {
        val env = getRunningEnvironment()
        assertIs<JavaEnvironment>(env)
        assertEquals(true, env.isJava)
        assertEquals(false, env.isJavaScript)
        assertSame(EnvironmentType.JAVA, env.type)
    }

}
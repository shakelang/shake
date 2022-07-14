package io.github.shakelang.parseutils.environment

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertSame

class Environment {

    @Test
    fun test() {
        val env = getRunningEnvironment()
        assertIs<JavaScriptEnvironment>(env)
        assertEquals(false, env.isJava)
        assertEquals(true, env.isJavaScript)
        assertSame(EnvironmentType.JAVASCRIPT, env.type)
    }

}
package com.shakelang.util.environment

import com.shakelang.util.environment.EnvironmentType
import com.shakelang.util.environment.JavaScriptEnvironment
import com.shakelang.util.environment.getRunningEnvironment
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertSame

class EnvironmentTests {

    @Test
    fun test() {
        val env = getRunningEnvironment()
        assertIs<JavaScriptEnvironment>(env)
        assertEquals(false, env.isJava)
        assertEquals(true, env.isJavaScript)
        assertSame(EnvironmentType.JAVASCRIPT, env.type)
    }
}

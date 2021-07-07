package com.github.shakelang.shake.util.environment

import com.github.shakelang.shake.util.process

actual class JavaEnvironment : Environment(EnvironmentType.JAVA) {
    actual val javaVersion: String get() = throw Error("No java available in javascript!")
}

actual class JavaScriptEnvironment : Environment(EnvironmentType.JAVA) {
    actual val isNodeAvailable: Boolean = jsTypeOf(process) == "object"
}

actual fun getRunningEnvironment(): Environment = JavaScriptEnvironment()
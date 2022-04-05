package io.github.shakelang.parseutils.environment

import io.github.shakelang.parseutils.process

actual class JavaEnvironment : Environment(EnvironmentType.JAVA) {
    actual val javaVersion: String get() = throw Error("No java available in javascript!")
}

actual class JavaScriptEnvironment : Environment(EnvironmentType.JAVASCRIPT) {
    actual val isNodeAvailable: Boolean = jsTypeOf(process) == "object"
}

actual fun getRunningEnvironment(): Environment = JavaScriptEnvironment()
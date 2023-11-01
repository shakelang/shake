package io.github.shakelang.environment

external val process: dynamic

actual class JavaEnvironment : Environment(EnvironmentType.JAVA) {
    actual val javaVersion: String get() = throw Error("No java available in javascript!")
}

actual class JavaScriptEnvironment : Environment(EnvironmentType.JAVASCRIPT) {
    actual val isNodeAvailable: Boolean = jsTypeOf(process) == "object"
}

actual fun getRunningEnvironment(): Environment = JavaScriptEnvironment()
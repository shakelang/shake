package io.github.shakelang.shake.util.environment

external val process: dynamic
external val window: dynamic
external val document: dynamic

actual class JavaEnvironment : Environment(EnvironmentType.JAVA) {
    actual val javaVersion: String get() = throw Error("No java available in javascript!")
}

actual class JavaScriptEnvironment : Environment(EnvironmentType.JAVASCRIPT) {
    actual val isNodeAvailable: Boolean = jsTypeOf(process) == "object"
    actual val nodeVersion: String get() = if(isNodeAvailable) process.version as String else throw Error("No node available in javascript!")
    actual val isBrowser: Boolean get() = jsTypeOf(window) == "object" && jsTypeOf(document) == "object"
}

actual fun getRunningEnvironment(): Environment = JavaScriptEnvironment()
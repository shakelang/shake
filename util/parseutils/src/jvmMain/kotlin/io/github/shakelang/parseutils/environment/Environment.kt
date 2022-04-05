package io.github.shakelang.parseutils.environment

actual class JavaEnvironment : Environment(EnvironmentType.JAVA) {
    actual val javaVersion: String get() = System.getProperty("java.version")
}

actual class JavaScriptEnvironment : Environment(EnvironmentType.JAVASCRIPT) {
    actual val isNodeAvailable: Boolean = throw Error("No javascript available in java!")
}

actual fun getRunningEnvironment(): Environment = JavaEnvironment()
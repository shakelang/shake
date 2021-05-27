package com.github.nsc.de.shake.util.environment

actual class JavaEnvironment : Environment(EnvironmentType.JAVA) {
    actual val javaVersion: String get() = System.getProperty("java.version")
}

actual class JavaScriptEnvironment : Environment(EnvironmentType.JAVA) {
    actual val isNodeAvailable: Boolean = throw Error("No javascript available in java!")
}

actual fun getRunningEnvironment(): Environment = JavaEnvironment()
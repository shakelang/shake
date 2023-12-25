package com.shakelang.util.environment

import com.shakelang.util.environment.Environment
import com.shakelang.util.environment.EnvironmentType

actual class JavaEnvironment : Environment(EnvironmentType.JAVA) {
    actual val javaVersion: String get() = System.getProperty("java.version")
}

actual class JavaScriptEnvironment : Environment(EnvironmentType.JAVASCRIPT) {
    actual val isNodeAvailable: Boolean get() = throw Error("No javascript available in java!")
    actual val nodeVersion: String get() = throw Error("No javascript available in java!")
    actual val isBrowser: Boolean get() = throw Error("No javascript available in java!")
}

actual fun getRunningEnvironment(): Environment = com.shakelang.util.environment.JavaEnvironment()

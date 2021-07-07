package com.github.shakelang.shake.util.environment

import kotlin.jvm.JvmStatic


abstract class Environment(
    val type: EnvironmentType
) {

    val isJavaScript: Boolean get() = this.type == EnvironmentType.JAVASCRIPT
    val isJavaScriptNode: Boolean get() = isJavaScript && toJavaScript().isNodeAvailable
    val isJava: Boolean get() = this.type == EnvironmentType.JAVA

    fun toJavaScript() = if(isJavaScript) this as JavaScriptEnvironment else throw Error("Environment is not javascript!")
    fun toJava() = if(isJava) this as JavaEnvironment else throw Error("Environment is not java!")

    companion object {
        @JvmStatic
        fun getEnvironment(): Environment = getRunningEnvironment()
    }


}

expect class JavaEnvironment : Environment {
    val javaVersion: String
}

expect class JavaScriptEnvironment : Environment {
    val isNodeAvailable: Boolean
}

expect fun getRunningEnvironment(): Environment


enum class EnvironmentType {

    JAVA, JAVASCRIPT

}
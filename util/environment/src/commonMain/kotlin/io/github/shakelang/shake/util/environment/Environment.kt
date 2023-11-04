package io.github.shakelang.shake.util.environment

/**
 * Describes an Environment of the multiplatform project execution
 */
abstract class Environment(

    /**
     * The type of the Environment
     */
    val type: EnvironmentType

) {

    /**
     * Is the Environment a JavaScript Environment
     */
    val isJavaScript: Boolean get() = this.type == EnvironmentType.JAVASCRIPT

    /**
     * Is the Environment a JavaScript Environment that has Node
     * functions available (such as fs or path lib)
     */
    val isJavaScriptNode: Boolean get() = isJavaScript && toJavaScript().isNodeAvailable

    /**
     * Is the Environment a JavaScript Environment inside a browser
     */
    val isJavaScriptBrowser: Boolean get() = isJavaScript && toJavaScript().isBrowser


    /**
     * Is the Environment a JVM Environment
     */
    val isJava: Boolean get() = this.type == EnvironmentType.JAVA


    /**
     * Casts this Environment to a JavaScriptEnvironment
     */
    fun toJavaScript() =
        if (isJavaScript) this as JavaScriptEnvironment else throw Error("Environment is not javascript!")

    /**
     * Casts this Environment to a JavaScriptEnvironment
     */
    fun toJava() = if (isJava) this as JavaEnvironment else throw Error("Environment is not java!")

    companion object {

        /**
         * Returns the running [Environment]
         *
         */
        fun getEnvironment(): Environment = getRunningEnvironment()

    }
}


/**
 * [Environment] of type Java
 */
expect class JavaEnvironment : Environment {

    /**
     * The java version of the [JavaEnvironment]
     */
    val javaVersion: String

}


/**
 * [Environment] of type JavaScript
 */
expect class JavaScriptEnvironment : Environment {

    /**
     * Is Node available in the [JavaScriptEnvironment]
     */
    val isNodeAvailable: Boolean

    /**
     * The node version of the [JavaScriptEnvironment]
     */
    val nodeVersion: String

    /**
     * Is the [JavaScriptEnvironment] running in a browser
     */
    val isBrowser: Boolean

}


/**
 * Returns the running environment
 */
expect fun getRunningEnvironment(): Environment

/**
 * The different types of [Environment]s
 */
enum class EnvironmentType {

    /**
     * The JVM [EnvironmentType]
     *
     * @see JavaEnvironment
     */
    JAVA,

    /**
     * The JavaScript [EnvironmentType]
     *
     * @see JavaScriptEnvironment
     */
    JAVASCRIPT

}

package io.github.shakelang.shake.util.environment

/**
 * Describes an Environment of the multiplatform project execution
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
abstract class Environment (

    /**
     * The type of the Environment
     */
    val type: EnvironmentType

) {

    /**
     * Is the Environment a JavaScript Environment
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    val isJavaScript: Boolean get() = this.type == EnvironmentType.JAVASCRIPT

    /**
     * Is the Environment a JavaScript Environment that has Node
     * functions available (such as fs or path lib)
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    val isJavaScriptNode: Boolean get() = isJavaScript && toJavaScript().isNodeAvailable

    /**
     * Is the Environment a JavaScript Environment inside a browser
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    val isJavaScriptBrowser: Boolean get() = isJavaScript && toJavaScript().isBrowser


    /**
     * Is the Environment a JVM Environment
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    val isJava: Boolean get() = this.type == EnvironmentType.JAVA


    /**
     * Casts this Environment to a JavaScriptEnvironment
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun toJavaScript() = if(isJavaScript) this as JavaScriptEnvironment else throw Error("Environment is not javascript!")

    /**
     * Casts this Environment to a JavaScriptEnvironment
     *
     * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
     */
    fun toJava() = if(isJava) this as JavaEnvironment else throw Error("Environment is not java!")

    companion object {

        /**
         * Returns the running [Environment]
         *
         * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
         */
        fun getEnvironment(): Environment = getRunningEnvironment()

    }
}


/**
 * [Environment] of type Java
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
 */
expect class JavaEnvironment : Environment {

    /**
     * The java version of the [JavaEnvironment]
     */
    val javaVersion: String

}


/**
 * [Environment] of type JavaScript
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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
 *
 * @author [Nicolas Schmidt &lt;@nsc-de&gt;](https://github.com/nsc-de)
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

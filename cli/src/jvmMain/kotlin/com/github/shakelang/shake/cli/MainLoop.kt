package io.github.shakelang.shake.cli

actual fun mainLoop(execute: (String) -> Unit) {
    while(true) {
        try {

            // Print input-request-line-start
            print("\n$ > ")
            execute(kotlin.io.readLine()!!)

        } catch (t: Throwable) {
            // When an error occurs while executing the code, just print it's stack and continue
            t.printStackTrace()
        }
    }
}
package com.shakelang.shake.cli

fun mainLoop(execute: (String) -> Unit) {
    val code = StringBuilder()

    while (true) {
        try {
            // Print input-request-line-start
            print("\n$ > ")

            // Only add code to previous code if it
            // could be loaded successfully
            // Execute will throw if code contains errors
            val line = readln()
            execute(code.toString() + "\n" + line)
            code.append('\n', line)
        } catch (t: Throwable) {
            // When an error occurs while executing the code, just print it's stack and continue
            t.printStackTrace()
        }
    }
}

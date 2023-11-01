package io.github.shakelang.shake.cli

import io.github.shakelang.shake.util.parseutils.recursiveWhile

actual fun mainLoop(execute: (String) -> Unit) {
    recursiveWhile({ true }) { _, wContinue -> run {


        // Try & Catch to prevent the console from crashing when
        // entering wrong code
        try {

            // Print input-request-line-start
            print("\n$ > ")

            readLine().then {

                wContinue()

            }.catch {
                it.printStackTrace()
                wContinue()
            }
        } catch (t: Throwable) {
            // When an error occurs while executing the code, just print it's stack and continue
            t.printStackTrace()
        }
    } }
}
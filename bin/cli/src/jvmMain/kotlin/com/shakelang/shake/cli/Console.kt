package com.shakelang.shake.cli

import kotlinx.coroutines.coroutineScope
import java.util.*

actual suspend fun readLine(message: String) = coroutineScope {
            print(message)
            val s = Scanner(System.`in`)
            val nextLine = s.nextLine()
            s.close()
    nextLine
}


actual suspend fun readLine() = readlnOrNull()

package com.github.shakelang.shake.cli

import com.github.shakelang.shake.util.Promise

actual fun readLine(message: String): Promise<String?> =
    Promise { rs, _ ->
        run {
            print(message)
            rs(kotlin.io.readLine()!!)
        }
    }


actual fun readLine(): Promise<String?> =
    Promise { rs, _ -> rs(kotlin.io.readLine()!!) }
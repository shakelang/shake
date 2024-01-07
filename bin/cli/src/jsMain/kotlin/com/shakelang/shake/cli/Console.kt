package com.shakelang.shake.cli

import com.shakelang.util.parseutils.nodeJsAvailable
import com.shakelang.util.parseutils.process
import com.shakelang.util.parseutils.require
import kotlinx.coroutines.await
import kotlin.js.Promise

val readline = if (nodeJsAvailable) require("readline") else null

var rl: dynamic = null
private fun expectReadline(): dynamic {
    if (!nodeJsAvailable) throw Error("Node is not available, can't use readline'!")
    if (rl == null) rl = readline.createInterface(mapOf("input" to process.stdin, "output" to process.stdout))
    return rl
}

actual suspend fun readLine(message: String): String? =
    Promise { rs, _ ->
        expectReadline().let { rl ->
            rl.question(message) { it ->
                run {
                    rl.pause()
                    rs(it as? String)
                }
            }
        } as Unit
    }.await()

actual suspend fun readLine() = readLine("")

package io.github.shakelang.shake.cli

import io.github.shakelang.shake.util.Promise
import io.github.shakelang.shake.util.nodeJsAvailable
import io.github.shakelang.shake.util.require
import io.github.shakelang.shake.util.process

val readline = if(nodeJsAvailable) require("readline") else null

var rl: dynamic = null
private fun expectReadline(): dynamic {
    if(!nodeJsAvailable) throw Error("Node is not available, can't use readline'!")
    if(rl == null) rl = readline.createInterface(mapOf( "input" to process.stdin, "output" to process.stdout ))
    return rl
}

actual fun readLine(message: String): Promise<String?> =
    Promise { rs, _ ->
        expectReadline().let { rl ->
            rl.question(message) { it ->
                run {
                    rl.pause()
                    rs(it as? String)
                } }
        } as Unit
    }


actual fun readLine(): Promise<String?> = readLine("")
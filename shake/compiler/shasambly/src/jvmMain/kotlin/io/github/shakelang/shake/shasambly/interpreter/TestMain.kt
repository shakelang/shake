@file:Suppress("nothing_to_inline", "unused")
package io.github.shakelang.shake.shasambly.interpreter

import io.github.shakelang.parseutils.bytes.toHexString
import io.github.shakelang.shake.shasambly.generator.shasambly
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream

fun main() {

    val code = shasambly {

        incrStack(0x007f)
        ipush(0x00000000)
        i_store_local(0x0000)

        relative {
            i_get_local(0x0000)
            ipush(1)
            iadd()
            i_store_local(0x0000)
            i_get_local(0x0000)

            natives.printInt()
            natives.printLineEnding()

            i_get_local(0x0000)
            ipush(15)
            ismaller()
            jumpIfTo(0)
        }

        // Hello World
        "Hello World!\n".forEach {
            bpush(it.code.toByte())
            natives.printUtf8()
        }

        natives.printLineEnding()

    }

    val file = File("test.shas")
    println("Write code bytes to ${file.absolutePath}...")
    val os = BufferedOutputStream(FileOutputStream(file))
    os.write(code)
    os.flush()
    os.close()

    println(code.toHexString())
    val interpreter = ShasamblyInterpreter(
        1024, code, 0
    )
    interpreter.finish()
    println(interpreter)
}
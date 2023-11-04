package io.github.shakelang.shake.shasambly

import io.github.shakelang.shake.shasambly.interpreter.ShasamblyInterpreter
import io.github.shakelang.shake.shasambly.interpreter.natives.Natives
import java.io.File
import java.io.FileInputStream
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.size != 1) throw Error("Expecting one argument!")
    if (!args[0].endsWith(".shasx")) throw Error("Input file has to end with .shas")
    val f = File(args[0])
    val inputStream = FileInputStream(f).buffered()
    val contents = inputStream.readAllBytes()
    inputStream.close()
    Natives.initNativeFunctions()
    val interpreter = ShasamblyInterpreter(1024, contents, 0)
    interpreter.finish()
    exitProcess(interpreter.exitCode)
}
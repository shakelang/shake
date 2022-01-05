package io.github.shakelang.shake.shasambly

import io.github.shakelang.shake.shasambly.generator.shas.ShasGenerator
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

fun main(args: Array<String>) {
    if(args.isEmpty()) throw Error("Expecting at least one argument")
    if(args.size > 2) throw Error("Expecting maximum 2 arguments")
    if(!args[0].endsWith(".shasx")) throw Error("Input file has to end with .shas")
    val f = File(args[0])
    val inputStream = FileInputStream(f).buffered()
    val generator = ShasGenerator(inputStream)
    val output = if(args.size == 1) args[0].substring(0, args[0].length - 1) else args[1]
    println("Generating file \"${File(output).absolutePath}\"...")
    val out = FileOutputStream(output).buffered()
    generator.dumpParse(out)
    inputStream.close()
    out.flush()
    out.close()
}
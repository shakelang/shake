package io.github.shakelang.jvmlib

import java.io.*

@Throws(IOException::class)
fun main(args: Array<String>) {
    if (args.size != 1) throw Error("Need class file as first argument, and not more than one argument")
    val f = File(args[0])
    if (!f.exists()) throw Error("This class-file does not exist!")
    val inputStream: InputStream = BufferedInputStream(FileInputStream(f))
    val clazz = ClassFileReader.readClass(inputStream)
    println(clazz.toString())
}
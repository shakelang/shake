package io.github.shakelang.jvmlib

import io.github.shakelang.parseutils.streaming.DataInputStream
import java.io.*

class ClassFileReader (
    val inputStream: InputStream
)
{

    fun parse() {

    }

}

@Throws(IOException::class)
fun main(args: Array<String>) {
    if (args.size != 1) throw Error("Need class file as first argument, and not more than one argument")
    val f = File(args[0])
    if (!f.exists()) throw Error("This class-file does not exist!")
    val inputStream: InputStream = BufferedInputStream(FileInputStream(f))
    JavaClassVisitor(DataInputStream(inputStream)).process()
    inputStream.close()
}
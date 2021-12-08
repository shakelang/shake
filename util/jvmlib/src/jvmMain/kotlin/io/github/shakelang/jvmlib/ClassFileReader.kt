package io.github.shakelang.jvmlib

import java.io.*

@Throws(IOException::class)
fun main(args: Array<String>) {
    if (args.size != 1) throw Error("Need class file as first argument, and not more than one argument")
    val f = File(args[0])
    if (!f.exists()) throw Error("This class-file does not exist!")
    val inputStream: InputStream = BufferedInputStream(FileInputStream(f))
    val clazz = JavaClassVisitor(inputStream).process()

    (0..6).forEach { _ -> println() }
    println("public class ${clazz.className} extends ${clazz.superClassName} implements " +
            "${clazz.interfaceNames.joinToString(", ")} {")

    clazz.fields.forEach {
        println("    var ${it.name}: ${it.descriptor};")
    }

    clazz.methods.forEach {
        println("    fun ${it.name}${it.descriptor};")
    }

    println("}")



    inputStream.close()
}
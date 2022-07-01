package io.github.shakelang.shake

import io.github.shakelang.shake.js.ShakeJsGenerator
import io.github.shakelang.shake.processor.ShakePackageBasedProcessor
import io.github.shakelang.shake.processor.map.ShakeMap
import io.github.shakelang.shason.json
import java.io.File

fun main(args: Array<String>) {

    val processor = ShakePackageBasedProcessor()
    processor.loadFile("shakelib/src/js", "shake/js/base.shake")
    processor.loadFile("shakelib/src/js", "shake/js/print.shake")
    processor.loadFile("shakelib/src/common", "shake/lang/Object.shake")
    processor.loadFile("shakelib/src/common", "shake/lang/String.shake")
    processor.loadFile("shakelib/src/common", "shake/lang/Numbers.shake")
    processor.loadFile("shake/compiler/processor/src/commonTest/resources", "test.shake")
    processor.loadFile("shake/compiler/processor/src/commonTest/resources", "io/github/shakelang/test.shake")
    val project = processor.finish()

    val map = ShakeMap.from(project)
    File("map.smap").writeBytes(map.getBytes())
    File("map.smap.json").writeText(map.toJsonString())

    val generator = ShakeJsGenerator()
    val jsProject = generator.visitProject(project)

    println(json.stringify(jsProject.toMap()))
    val files = jsProject.generatePackageFiles()

    println(json.stringify(files))
    println("Generated ${files.size} files...")
    generateFiles("testOutput", files)


}

fun generateFiles(dir: String, map: Map<String, String>) {
    val dirFile = File(dir)
    if (!dirFile.exists()) {
        dirFile.mkdirs()
    }
    map.forEach { (key, value) ->
        run {
            val file = File(dirFile, key)
            println("Generating ${file.absolutePath}...")
            val parent = file.parentFile
            if (!parent.exists()) parent.mkdirs()
            file.writeText(value)
        }
    }
}
package io.github.shakelang.shake

import io.github.shakelang.shake.js.ShakeJsGenerator
import io.github.shakelang.shake.processor.ShakePackageBasedProcessor
import io.github.shakelang.shason.json

fun main(args: Array<String>) {

    val processor = ShakePackageBasedProcessor()
    processor.loadFile("shake/compiler/processor/src/commonTest/resources", "test.shake")
    val project = processor.finish()

    println(project.toJsonString())

    val generator = ShakeJsGenerator()
    val jsProject = generator.visitProject(project)

    println(json.stringify(jsProject.toMap()))

}
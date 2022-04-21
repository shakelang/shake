package io.github.shakelang.shake

import io.github.shakelang.shake.processor.ShakePackageBasedProcessor

fun main(args: Array<String>) {

    val processor = ShakePackageBasedProcessor()
    processor.loadFile("shake/compiler/processor/src/commonTest/resources", "test.shake")
    val project = processor.finish()

    println(project.toJsonString())


}
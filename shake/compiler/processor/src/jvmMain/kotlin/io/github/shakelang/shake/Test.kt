package io.github.shakelang.shake

import io.github.shakelang.shake.processor.ShakePackageBasedProcessor
import io.github.shakelang.shake.processor.map.ShakeMap

fun main(args: Array<String>) {

    val processor = ShakePackageBasedProcessor()
    processor.loadFile("shake/compiler/processor/src/commonTest/resources", "test.shake")
    processor.loadFile("shake/compiler/processor/src/commonTest/resources", "io/github/shakelang/test.shake")
    val project = processor.finish()

    println(ShakeMap.from(ShakeMap.from(project).getBytes()).toJson())
    println(project.toJsonString())


}
package io.github.shakelang.shake

import io.github.shakelang.shake.processor.ShakePackageBasedProcessor
import io.github.shakelang.shake.processor.map.ShakeMap
import io.github.shakelang.shason.json

fun main(args: Array<String>) {

    val processor = ShakePackageBasedProcessor()
    processor.loadFile("shake/compiler/processor/src/commonTest/resources", "test.shake")
    processor.loadFile("shake/compiler/processor/src/commonTest/resources", "io/github/shakelang/test.shake")
    val project = processor.finish()

    println(json.stringify(ShakeMap.from(project).toJson()))
    println(project.toJsonString())


}
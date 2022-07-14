package io.github.shakelang.shake

import io.github.shakelang.shake.processor.ShakePackageBasedProcessor
import io.github.shakelang.shake.processor.map.ShakeMap
import io.github.shakelang.shason.json

fun main(args: Array<String>) {

    val processor = ShakePackageBasedProcessor()
    processor.loadFile("shake/compiler/processor/src/commonTest/resources", "test.shake")
    processor.loadFile("shake/compiler/processor/src/commonTest/resources", "io/github/shakelang/test.shake")
    processor.loadFile("shakelib/src/common", "shake/lang/String.shake")
    processor.loadFile("shakelib/src/common", "shake/lang/Object.shake")
    processor.loadFile("shakelib/src/common", "shake/js/base.shake")
    val project = processor.finish()

    println(json.stringify(ShakeMap.from(project).toJson()))
    println(project.toJsonString())
    println(ShakeMap.from(ShakeMap.from(project).getBytes()).assemble().toJsonString())
    println(ShakeMap.fromJson(ShakeMap.from(project).toJson()).assemble().toJsonString())


}
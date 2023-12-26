package com.shakelang.shake

import com.shakelang.shake.js.ShakeJsGenerator
import com.shakelang.shake.processor.ShakePackageBasedProcessor
import com.shakelang.shake.processor.map.ShakeMap
import com.shakelang.util.shason.json

fun main(args: Array<String>) {
    val processor = ShakePackageBasedProcessor()
    processor.loadFile("shake/compiler/processor/src/commonTest/resources", "test.shake")
    processor.loadFile("shake/compiler/processor/src/commonTest/resources", "io/github/shakelang/test.shake")
    processor.loadFile("MR/files/src/common", "shake/lang/String.shake")
    processor.loadFile("MR/files/src/common", "shake/lang/Object.shake")
    // processor.loadFile("shakelib/src/js", "shake/js/base.shake")
    val project = processor.finish()

    println(json.stringify(ShakeMap.from(project).toJson()))
    println(project.toJsonString())
    println(ShakeMap.from(ShakeMap.from(project).getBytes()).assemble().toJsonString())
    println(ShakeMap.fromJson(ShakeMap.from(project).toJson()).assemble().toJsonString())

    val generator = ShakeJsGenerator()
    val jsTree = generator.visitProject(project)
    println(json.stringify(jsTree.generatePackageFiles()))
}

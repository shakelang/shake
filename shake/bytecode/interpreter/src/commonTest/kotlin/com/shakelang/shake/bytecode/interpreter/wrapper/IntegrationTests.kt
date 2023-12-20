package com.shakelang.shake.bytecode.interpreter.wrapper

import com.shakelang.shake.bytecode.interpreter.generator.generatePackage
import io.kotest.core.spec.style.FreeSpec

class IntegrationTests : FreeSpec({

    "getClass" {
        val classpath = ShakeClasspath.create()
        classpath.load(generatePackage {
            name = "com/shakelang/shake/test"

            Class {
                name = "Test"
                superName = "com/shakelang/Object"
                isPublic = true
            }
        })

        val testClass = classpath.getClass("com/shakelang/shake/test/Test")
    }

})
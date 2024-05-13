package com.shakelang.shake.parser

import com.shakelang.shake.parser.api.generateTests
import com.shakelang.shake.parser.test.ShakeParserTestResources
import io.kotest.core.spec.style.FreeSpec

class Tests : FreeSpec({

    "test" {
        println("Test")
    }

    generateTests {

        println(ShakeParserTestResources.files.keys)

        ShakeParserTestResources["tests"]!!.toFolder().glob("*.shake").forEach { file ->
            if (!file.isFile) return@forEach
            val shakeFile = file.path
            val name = shakeFile.substring(0, shakeFile.length - 6)
            val jsonFile = "$name.json"
            test(name, isIgnored = false) {
                this.input = file.toFile().contentsAsString()
                this.expectedJson = (
                    ShakeParserTestResources[jsonFile.substring(ShakeParserTestResources.path.length + 1)]
                        ?: throw IllegalStateException("No json file found for $name (expected $jsonFile)")
                    ).toFile().contentsAsString()
            }
        }
    }
})

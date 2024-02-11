package com.shakelang.shake.bytecode

import com.shakelang.shake.bytecode.tools.BytecodeStringGenerator
import com.shakelang.shake.shaketest.ShakeTestInput
import com.shakelang.shake.shaketest.ShakeTestOutput
import com.shakelang.util.logger.debug
import com.shakelang.util.shason.json
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ShakeTests : FreeSpec(
    {

        val debugger = debug("bytecode").child("builder", "tests", "ShakeTests")

        ShakeTestInput.forEachFile {

            val inputFile = it
            // get the output file
            val outputFile = (
                ShakeTestOutput[
                    it.path
                        .replace(".shake", ".out")
                        .split("/", limit = 2).last(),
                ]
                    ?: error("Outfile for ${it.path} not found")
                ).toFile()

            debugger("Generating test for ${json.stringify(inputFile.path)} with output ${json.stringify(outputFile.path)}")

            // execute the test
            inputFile.path {
                codeSpec(inputFile.contentsAsString()) {

                    val debug = debugger.child("test", inputFile.path.split("/").last())
                    debug("Input: ${inputFile.contentsAsString()}")
                    debug("Expected Output: ${json.stringify(outputFile.contentsAsString())}")
                    debug("Real Output: ${json.stringify(consoleOut)}")
                    debug("Compiled Code: ${BytecodeStringGenerator(format).generate().joinToString()}")

                    execute("test/main()V")
                    consoleOut shouldBe outputFile.contentsAsString()
                }
            }
        }
    },
)

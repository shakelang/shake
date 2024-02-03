package com.shakelang.shake.parser

import com.shakelang.shake.parser.test.ShakeParserTestInput
import com.shakelang.shake.parser.test.ShakeParserTestOutput
import com.shakelang.util.logger.debug
import com.shakelang.util.shason.json
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class TestFiles : FreeSpec(
    {

        val debugger = debug("shake").child("parser", "tests")

        ShakeParserTestInput.forEachFile {

            val inputFile = it

            // get the output file
            val outputFile = (
                ShakeParserTestOutput[
                    it.path
                        .replace(".shake", ".json")
                        .split("/", limit = 2).last(),
                ]
                )?.toFile() ?: ShakeParserTestOutput[
                it.path
                    .replace(".shake", ".error")
                    .split("/", limit = 2).last(),
            ]?.toFile()

            debugger("Generating test for ${json.stringify(inputFile.path)} with output ${json.stringify(outputFile?.path)}")

            // execute the test
            inputFile.path {
                if (outputFile == null) error("No output file found for ${inputFile.path}")

                if (outputFile.name.endsWith(".error")) {
                    val error = shouldThrow<ShakeParserImpl.ParserError> {
                        ParserTestUtil.parse(inputFile.path, inputFile.contentsAsString())
                    }

                    error.message shouldBe outputFile.contentsAsString()
                } else {
                    val ast = json.stringify(ParserTestUtil.parse(inputFile.path, inputFile.contentsAsString()).json)
                    val expected = json.stringify(json.parse(outputFile.contentsAsString()).toJsonObject().toMap())
                    ast shouldBe expected
                }
            }
        }
    },
)

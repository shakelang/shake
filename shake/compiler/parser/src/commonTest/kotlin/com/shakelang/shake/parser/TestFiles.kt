package com.shakelang.shake.parser

//
// class TestFiles : FreeSpec(
//    {
//
//        val debugger = debug("shake").child("parser", "tests")
//
//        ShakeParserTestInput.forEachFile {
//
//            val inputFile = it
//
//            // get the output file
//            val outputFile = (
//                ShakeParserTestOutput[
//                    it.path
//                        .replace(".shake", ".json")
//                        .split("/", limit = 2).last(),
//                ]
//                )?.toFile() ?: ShakeParserTestOutput[
//                it.path
//                    .replace(".shake", ".error")
//                    .split("/", limit = 2).last(),
//            ]?.toFile()
//
//            debugger("Generating test for ${json.stringify(inputFile.path)} with output ${json.stringify(outputFile?.path)}")
//
//            // execute the test
//            inputFile.path {
//                if (outputFile == null) error("No output file found for ${inputFile.path}")
//
//                if (outputFile.name.endsWith(".error")) {
//                    val error = shouldThrow<ShakeParserImpl.ParserError> {
//                        ParserTestUtil.parse(inputFile.path, inputFile.contentsAsString())
//                    }
//
//                    try {
//                        error.message shouldBe outputFile.contentsAsString()
//                    } catch (e: Throwable) {
//                        println("Source: \n${inputFile.contentsAsString()}")
//                        println()
//                        println("Error: ${error.message}")
//                        println("Expected: ${outputFile.contentsAsString()}")
//                        error.printStackTrace()
//                        throw e
//                    }
//                } else {
//                    try {
//                        val ast = json.stringify(ParserTestUtil.parse(inputFile.path, inputFile.contentsAsString()).json)
//                        val expected = json.stringify(json.parse(outputFile.contentsAsString()).toJsonObject().toMap())
//
//                        try {
//                            ast shouldBe expected
//                        } catch (e: Throwable) {
//                            println("Source: \n${inputFile.contentsAsString()}")
//                            println()
//                            println("AST: $ast")
//                            println("Expected: $expected")
//                            throw e
//                        }
//                    } catch (e: Throwable) {
//                        println("Source: \n${inputFile.contentsAsString()}")
//                        println()
//                        println("Error: ${e.message}")
//                        throw e
//                    }
//                }
//            }
//        }
//    },
// )

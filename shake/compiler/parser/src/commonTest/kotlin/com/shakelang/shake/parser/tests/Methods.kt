package com.shakelang.shake.parser.tests

import com.shakelang.shake.parser.tests.api.combineAttributes
import com.shakelang.shake.parser.tests.api.generateTests
import com.shakelang.shake.parser.tests.api.primitiveTypes
import io.kotest.core.spec.style.FreeSpec

class AutoMethodTests : FreeSpec(
    {
        generateTests {

            this.provider {

                name = "methods"

                primitiveTypes.forEach { (type, typeName) ->
                    combineAttributes().forEachIndexed { i, attributeInfo ->

                        val templates = listOf(
                            template("methods/method") to "method",
                        )


                        templates.forEach {

                            val template = it.first

                            it.first.apply(
                                com.shakelang.shake.parser.tests.api.replaceTemplate(
                                    "%access%" to attributeInfo.accessModifier,
                                    "%static%" to if (attributeInfo.isStatic) "true" else "false",
                                    "%final%" to if (attributeInfo.isFinal) "true" else "false",
                                    "%attributes%" to attributeInfo.attributeString,
                                    "%type%" to type,
                                    "%type_name%" to typeName,
                                    "%name%" to "m",
                                ),
                            )

                            test("0/${typeName}_${it.second}$i", isIgnored = false) {
                                this.input = template.code
                                this.expectedJson = template.json
                            }
                        }
                    }
                }



                primitiveTypes.forEach { (type, typeName) ->
                    primitiveTypes.forEach { (type0, typeName0) ->
                        combineAttributes().forEachIndexed { i, attributeInfo ->

                            val templates = listOf(
                                template("methods/parameter-method") to "method",
                            )


                            templates.forEach {

                                val template = it.first

                                it.first.apply(
                                    com.shakelang.shake.parser.tests.api.replaceTemplate(
                                        "%access%" to attributeInfo.accessModifier,
                                        "%static%" to if (attributeInfo.isStatic) "true" else "false",
                                        "%final%" to if (attributeInfo.isFinal) "true" else "false",
                                        "%attributes%" to attributeInfo.attributeString,
                                        "%type%" to type,
                                        "%type_name%" to typeName,
                                        "%name%" to "m",
                                        "%type0_name%" to typeName0,
                                        "%type0%" to type0,
                                    ),
                                )

                                test("1/${typeName}/${typeName0}/${it.second}$i", isIgnored = false) {
                                    this.input = template.code
                                    this.expectedJson = template.json
                                }
                            }
                        }
                    }
                }

                primitiveTypes.forEach { (type0, typeName0) ->
                    primitiveTypes.forEach { (type1, typeName1) ->
                        combineAttributes().forEachIndexed { i, attributeInfo ->

                            val templates = listOf(
                                template("methods/parameter2-method") to "method",
                            )


                            templates.forEach {

                                val template = it.first

                                it.first.apply(
                                    com.shakelang.shake.parser.tests.api.replaceTemplate(
                                        "%access%" to attributeInfo.accessModifier,
                                        "%static%" to if (attributeInfo.isStatic) "true" else "false",
                                        "%final%" to if (attributeInfo.isFinal) "true" else "false",
                                        "%attributes%" to attributeInfo.attributeString,
                                        "%type%" to "void",
                                        "%type_name%" to "void",
                                        "%name%" to "m",
                                        "%type0_name%" to typeName0,
                                        "%type0%" to type0,
                                        "%type1_name%" to typeName1,
                                        "%type1%" to type1,
                                    ),
                                )

                                test("2/${typeName0}/${typeName1}/${it.second}$i", isIgnored = false) {
                                    this.input = template.code
                                    this.expectedJson = template.json
                                }
                            }
                        }
                    }
                }
            }
        }
    },
)


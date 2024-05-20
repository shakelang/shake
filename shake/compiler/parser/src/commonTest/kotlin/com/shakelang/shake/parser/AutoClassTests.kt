package com.shakelang.shake.parser

import com.shakelang.shake.parser.api.combineAttributes
import com.shakelang.shake.parser.api.generateTests
import com.shakelang.shake.parser.api.primitiveTypes
import io.kotest.core.spec.style.FreeSpec

class AutoClassTests : FreeSpec(
    {
        generateTests {
            provider {

                name = "classes"

                primitiveTypes.forEach { (type, typeName) ->
                    combineAttributes(includeOverride = true).forEachIndexed { i, attributeInfo ->

                        val templates = listOf(
                            template("classes/class-field") to "class_field",
                            template("classes/initialized-class-field") to "class_field_initialized",
                        )

                        templates.forEach {

                            val template = it.first

                            it.first.apply(
                                replaceTemplate(
                                    "%access%" to attributeInfo.accessModifier,
                                    "%static%" to if (attributeInfo.isStatic) "true" else "false",
                                    "%final%" to if (attributeInfo.isFinal) "true" else "false",
                                    "%override%" to if (attributeInfo.isOverride) "true" else "false",
                                    "%attributes%" to attributeInfo.attributeString,
                                    "%type%" to type,
                                    "%type_name%" to typeName,
                                    "%name%" to "test",
                                ),
                            )

                            test("${typeName}_${it.second}$i", isIgnored = false) {
                                this.input = template.code
                                this.expectedJson = template.json
                            }
                        }

                        val methodTemplates = listOf(
                            template("classes/class-method") to "class_method",
                        )

                        methodTemplates.forEach {

                            val template = it.first

                            it.first.apply(
                                replaceTemplate(
                                    "%access%" to attributeInfo.accessModifier,
                                    "%static%" to if (attributeInfo.isStatic) "true" else "false",
                                    "%final%" to if (attributeInfo.isFinal) "true" else "false",
                                    "%override%" to if (attributeInfo.isOverride) "true" else "false",
                                    "%abstract%" to if (attributeInfo.isAbstract) "true" else "false",
                                    "%attributes%" to attributeInfo.attributeString,
                                    "%type%" to type,
                                    "%type_name%" to typeName,
                                    "%name%" to "test",
                                    "%params%" to "",
                                ),

                            )

                            test("${typeName}_${it.second}$i", isIgnored = false) {
                                this.input = template.code
                                this.expectedJson = template.json
                            }
                        }
                    }
                }

                combineAttributes().forEachIndexed { i, attributeInfo ->

                    val templates = listOf(
                        template("classes/class") to "class",
                        template("classes/inner-class") to "inner_class",
                    )

                    templates.forEach {

                        val template = it.first

                        it.first.apply(
                            replaceTemplate(
                                "%access%" to attributeInfo.accessModifier,
                                "%static%" to if (attributeInfo.isStatic) "true" else "false",
                                "%final%" to if (attributeInfo.isFinal) "true" else "false",
                                "%attributes%" to attributeInfo.attributeString,
                                "%name%" to "test",
                            ),
                        )

                        test("${it.second}$i", isIgnored = false) {
                            this.input = template.code
                            this.expectedJson = template.json
                        }
                    }
                }
            }
        }
    },
)

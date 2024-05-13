package com.shakelang.shake.parser

import com.shakelang.shake.parser.api.combineAttributes
import com.shakelang.shake.parser.api.generateTests
import com.shakelang.shake.parser.api.primitiveTypes
import io.kotest.core.spec.style.FreeSpec

class AutoFieldTests : FreeSpec(
    {

        generateTests {
            provider {

                name = "fields"

                primitiveTypes.forEach { (type, typeName) ->
                    combineAttributes().forEachIndexed { i, attributeInfo ->

                        val templates = listOf(
                            template("fields/field") to "field",
                            template("fields/initialized-field") to "initialized_field",
                        )

                        templates.forEach {

                            val template = it.first

                            it.first.apply(
                                replaceTemplate(
                                    "%access%" to attributeInfo.accessModifier,
                                    "%static%" to if (attributeInfo.isStatic) "true" else "false",
                                    "%final%" to if (attributeInfo.isFinal) "true" else "false",
                                    "%attributes%" to attributeInfo.attributeString,
                                    "%type%" to type,
                                    "%type_name%" to typeName,
                                    "%name%" to "field",
                                ),
                            )

                            test("${typeName}_${it.second}$i", isIgnored = false) {
                                this.input = template.code
                                this.expectedJson = template.json
                            }
                        }
                    }
                }
            }
        }
    },
)

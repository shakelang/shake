package com.shakelang.shake.parser.tests

import com.shakelang.shake.parser.tests.api.Template
import com.shakelang.shake.parser.tests.api.generateTests
import io.kotest.core.spec.style.FreeSpec

class AutoExpressionTests : FreeSpec(
    {

        generateTests {
            provider {

                name = "expr"

                // Templates and Utilities
                val baseTemplate = Template("expr/base")
                val priorityTemplate = Template("expr/priority")
                val literalTemplate = Template("expr/literal")
                val addTemplate = Template("expr/add")
                val subTemplate = Template("expr/sub")
                val mulTemplate = Template("expr/mul")
                val divTemplate = Template("expr/div")
                val modTemplate = Template("expr/mod")
                val powTemplate = Template("expr/pow")
                val logicalOrTemplate = Template("expr/logical-or")
                val logicalAndTemplate = Template("expr/logical-and")
                val logicalNotTemplate = Template("expr/logical-not")
                val logicalXorTemplate = Template("expr/logical-xor")
                val bitwiseOrTemplate = Template("expr/bitwise-or")
                val bitwiseAndTemplate = Template("expr/bitwise-and")
                val bitwiseNotTemplate = Template("expr/bitwise-not")
                val bitwiseXorTemplate = Template("expr/bitwise-xor")
                val unaryMinusTemplate = Template("expr/unary-minus")
                val unaryPlusTemplate = Template("expr/unary-plus")

                fun literal(value: String) = literalTemplate.applied(
                    replaceTemplate(
                        "%value%" to value,
                    ),
                )

                fun operator1(value: Template, template: Template): Template {
                    return template.applied(
                        replaceTemplate(
                            "%value%" to value.code,
                            "%value_json%" to value.json,
                            "%value_error%" to value.error,
                        ),
                    )
                }

                fun gop1(template: Template): (Template) -> Template = { value -> operator1(value, template) }

                fun operator2(left: Template, right: Template, template: Template): Template {
                    return template.applied(
                        replaceTemplate(
                            "%left%" to left.code,
                            "%left_json%" to left.json,
                            "%left_error%" to left.error,
                            "%right%" to right.code,
                            "%right_json%" to right.json,
                            "%right_error%" to right.error,
                        ),
                    )
                }

                fun gop2(template: Template): (Template, Template) -> Template = { left, right -> operator2(left, right, template) }

                fun base(value: Template): Template {
                    return baseTemplate.applied(
                        replaceTemplate(
                            "%expr%" to value.code,
                            "%expr_json%" to value.json,
                            "%expr_error%" to value.error,
                        ),
                    )
                }


                class TestGenerator(
                    val name: String,
                    val generator: Function<Template>,
                    val argNum: Int,
                ) {
                    var level = 0
                }


                val levels = arrayOf(
                    arrayOf(TestGenerator("priority", gop1(priorityTemplate), 1)),
                    arrayOf(
                        TestGenerator("unary_minus", gop1(unaryMinusTemplate), 1),
                        TestGenerator("unary_plus", gop1(unaryPlusTemplate), 1),
                        TestGenerator("logical_not", gop1(logicalNotTemplate), 1),
                        TestGenerator("bitwise_not", gop1(bitwiseNotTemplate), 1),
                    ),
                    arrayOf(TestGenerator("power", gop2(powTemplate), 2)),
                    arrayOf(
                        TestGenerator("multiplication", gop2(mulTemplate), 2),
                        TestGenerator("division", gop2(divTemplate), 2),
                        TestGenerator("modulus", gop2(modTemplate), 2),
                    ),
                    arrayOf(
                        TestGenerator("addition", gop2(addTemplate), 2),
                        TestGenerator("subtraction", gop2(subTemplate), 2),
                    ),
                    // bitwise
                    arrayOf(TestGenerator("bitwise_and", gop2(bitwiseAndTemplate), 2)),
                    arrayOf(TestGenerator("bitwise_xor", gop2(bitwiseXorTemplate), 2)),
                    arrayOf(TestGenerator("bitwise_or", gop2(bitwiseOrTemplate), 2)),
                    // logical
                    arrayOf(TestGenerator("logical_and", gop2(logicalAndTemplate), 2)),
                    arrayOf(TestGenerator("logical_xor", gop2(logicalXorTemplate), 2)),
                    arrayOf(TestGenerator("logical_or", gop2(logicalOrTemplate), 2)),
                )

                levels.forEachIndexed { j, generators ->
                    generators.forEach { generator ->
                        generator.level = j
                    }
                }

                val generators = levels.flatten()

                val literalsList = listOf(
                    listOf("120", "7", "19"),
                    listOf("77", "3", "25"),
                    listOf("1", "2", "3"),
                )
                literalsList.forEachIndexed { i, literals ->
                    generators.forEach { generator ->
                        test(
                            "simple_${generator.name}$i",
                        ) {
                            @Suppress("UNCHECKED_CAST")
                            val generatorResult = base(if (generator.argNum == 1) (generator.generator as Gop1)(literal(literals[0]))
                            else (generator.generator as Gop2)(literal(literals[0]), literal(literals[1])))

                            input = generatorResult.code
                            expectedJson = generatorResult.json
                        }
                        generators.forEach { generator2 ->
                            if (generator.name == "unary_minus" && generator2.name == "unary_minus") return@forEach
                            if (generator.name == "unary_plus" && generator2.name == "unary_plus") return@forEach

                            // The expression should look like this:
                            // an operator1 b operator2 c
                            // we need to know which operator has higher priority
                            test(
                                "nested_${generator.name}_${generator2.name}$i",
                            ) {
                                if (generator.level > generator2.level) {
                                    // an operator1 (b operator2 c)
                                    // The result of the second operator will be put into the last argument of the first operator

                                    @Suppress("UNCHECKED_CAST")
                                    val generator2Result =
                                        if (generator2.argNum == 1) (generator2.generator as Gop1)(literal(literals[generator.argNum - 1]))
                                        else (generator2.generator as Gop2)(literal(literals[0]), literal(literals[generator.argNum]))

                                    @Suppress("UNCHECKED_CAST")
                                    val generator1Result = base(if (generator.argNum == 1) (generator.generator as Gop1)(generator2Result)
                                    else (generator.generator as Gop2)(literal(literals[0]), generator2Result))

                                    input = generator1Result.code
                                    expectedJson = generator1Result.json

                                } else {
                                    // (an operator1 b) operator2 c
                                    // The result of the first operator will be put into the first argument of the second operator
                                    // This can also handle operator1.level === operator2.level (because then we will parse from left to right)

                                    @Suppress("UNCHECKED_CAST")
                                    val generator1Result = if (generator.argNum == 1) (generator.generator as Gop1)(literal(literals[0]))
                                    else (generator.generator as Gop2)(literal(literals[0]), literal(literals[1]))

                                    @Suppress("UNCHECKED_CAST")
                                    val generator2Result = base(if (generator2.argNum == 1) (generator2.generator as Gop1)(literal(literals[generator.argNum + generator2.argNum - 1]))
                                    else (generator2.generator as Gop2)(generator1Result, literal(literals[generator.argNum])))

                                    input = generator2Result.code
                                    expectedJson = generator2Result.json
                                }
                            }
                        }
                    }
                }
            }
        }
    },
)

typealias Gop1 = (Template) -> Template
typealias Gop2 = (Template, Template) -> Template

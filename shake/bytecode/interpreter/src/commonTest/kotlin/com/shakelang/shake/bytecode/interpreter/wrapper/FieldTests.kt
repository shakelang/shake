package com.shakelang.shake.bytecode.interpreter.wrapper

import com.shakelang.shake.bytecode.interpreter.generator.generatePackage
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class FieldTests : FreeSpec({
    "getField" {
        val classpath = ShakeClasspath.create()
        classpath.load(generatePackage {
            name = "com/shakelang/shake/test"

            Field {
                name = "test"
                type = "I"
                isPublic = true
            }
        })

        val testField = classpath.getField("com/shakelang/shake/test/test")
        testField shouldNotBe null
        testField!!.simpleName shouldBe "test"
        testField.qualifiedName shouldBe "com/shakelang/shake/test/test"
    }

    "getField class child" {
        val classpath = ShakeClasspath.create()
        classpath.load(generatePackage {
            name = "com/shakelang/shake/test"

            Class {
                name = "Test"
                superName = "com/shakelang/Object"
                isPublic = true

                Field {
                    name = "test"
                    type = "I"
                    isPublic = true
                }
            }
        })

        val testField = classpath.getField("com/shakelang/shake/test/Test:test")
        testField shouldNotBe null
        testField!!.simpleName shouldBe "test"
        testField.qualifiedName shouldBe "com/shakelang/shake/test/Test:test"
    }

    "getField class child child" {
        val classpath = ShakeClasspath.create()
        classpath.load(generatePackage {
            name = "com/shakelang/shake/test"

            Class {
                name = "Test"
                superName = "com/shakelang/Object"
                isPublic = true

                Class {
                    name = "Test2"
                    superName = "com/shakelang/Object"
                    isPublic = true

                    Field {
                        name = "test"
                        type = "I"
                        isPublic = true
                    }
                }
            }
        })

        val testField = classpath.getField("com/shakelang/shake/test/Test:Test2:test")
        testField shouldNotBe null
        testField!!.simpleName shouldBe "test"
        testField.qualifiedName shouldBe "com/shakelang/shake/test/Test:Test2:test"
    }

    "getField when field does not exist" {
        val classpath = ShakeClasspath.create()
        classpath.load(generatePackage {
            name = "com/shakelang/shake/test"
        })

        val testField = classpath.getField("com/shakelang/shake/test/test")
        testField shouldBe null
    }

    "getField when field child does not exist" {
        val classpath = ShakeClasspath.create()
        classpath.load(generatePackage {
            name = "com/shakelang/shake/test"

            Field {
                name = "test"
                type = "I"
                isPublic = true
            }
        })

        val testField = classpath.getField("com/shakelang/shake/test/test2")
        testField shouldBe null
    }

    "getField when field of child child does not exist" {
        val classpath = ShakeClasspath.create()
        classpath.load(generatePackage {
            name = "com/shakelang/shake/test"
        })

        val testField = classpath.getField("com/shakelang/shake/test/test:test:test")
        testField shouldBe null
    }

    "getField when class of child does not exist" {
        val classpath = ShakeClasspath.create()
        classpath.load(generatePackage {
            name = "com/shakelang/shake/test"
        })

        val testField = classpath.getField("com/shakelang/shake/test/test:test")
        testField shouldBe null
    }

    "getField when package does not exist" {
        val classpath = ShakeClasspath.create()
        classpath.load(generatePackage {
            name = "com/shakelang/shake/test"
        })

        val testField = classpath.getField("com/shakelang/shake/test2/test")
        testField shouldBe null
    }

    "getField with child when package does not exist" {
        val classpath = ShakeClasspath.create()
        classpath.load(generatePackage {
            name = "com/shakelang"
        })

        val testField = classpath.getField("com/shakelang/shake/test/test")
        testField shouldBe null
    }

    "resolve type of field with type object" {
        val classpath = ShakeClasspath.create()
        classpath.load(generatePackage {
            name = "com/shakelang/shake/test"

            Field {
                name = "test"
                type = "Lcom/shakelang/Object;"
                isPublic = true
            }
        })

        classpath.load(generatePackage {
            name = "com/shakelang"

            Class {
                name = "Object"
                superName = "com/shakelang/Object"
                isPublic = true
            }
        })

        val testField = classpath.getField("com/shakelang/shake/test/test")
        testField shouldNotBe null
        testField!!.simpleName shouldBe "test"
        testField.qualifiedName shouldBe "com/shakelang/shake/test/test"

        val type = testField.type

        type shouldNotBe null
        type.type shouldBe ShakeInterpreterType.Type.OBJECT
        (type is ShakeInterpreterType.ObjectType) shouldBe true
        type.name shouldBe "Lcom/shakelang/Object;"
        val clz = (type as ShakeInterpreterType.ObjectType).objectType
        clz shouldNotBe null
        clz.simpleName shouldBe "Object"
        clz.qualifiedName shouldBe "com/shakelang/Object"
    }
})
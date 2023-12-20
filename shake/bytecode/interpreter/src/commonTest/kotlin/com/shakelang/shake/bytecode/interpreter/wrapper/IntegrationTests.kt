package com.shakelang.shake.bytecode.interpreter.wrapper

import com.shakelang.shake.bytecode.interpreter.generator.generatePackage
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

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
        testClass shouldNotBe null
        testClass!!.simpleName shouldBe "Test"
        testClass.qualifiedName shouldBe "com/shakelang/shake/test/Test"

    }

    "getClass class child" {
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
                }
            }
        })

        val testClass = classpath.getClass("com/shakelang/shake/test/Test:Test2")
        testClass shouldNotBe null
        testClass!!.simpleName shouldBe "Test2"
        testClass.qualifiedName shouldBe "com/shakelang/shake/test/Test:Test2"
    }

    "getClass class child child" {
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

                    Class {
                        name = "Test3"
                        superName = "com/shakelang/Object"
                        isPublic = true
                    }
                }
            }
        })

        val testClass = classpath.getClass("com/shakelang/shake/test/Test:Test2:Test3")
        testClass shouldNotBe null
        testClass!!.simpleName shouldBe "Test3"
        testClass.qualifiedName shouldBe "com/shakelang/shake/test/Test:Test2:Test3"
    }

    "getMethod" {
        val classpath = ShakeClasspath.create()
        classpath.load(generatePackage {
            name = "com/shakelang/shake/test"

            Method {
                name = "test(I)I"
                isPublic = true
            }
        })

        val testMethod = classpath.getMethod("com/shakelang/shake/test/test(I)I")
        testMethod shouldNotBe null
        testMethod!!.simpleName shouldBe "test(I)I"
        testMethod.qualifiedName shouldBe "com/shakelang/shake/test/test(I)I"
    }

    "getMethod class child" {
        val classpath = ShakeClasspath.create()
        classpath.load(generatePackage {
            name = "com/shakelang/shake/test"

            Class {
                name = "Test"
                superName = "com/shakelang/Object"
                isPublic = true

                Method {
                    name = "test(I)I"
                    isPublic = true
                }
            }
        })

        val testMethod = classpath.getMethod("com/shakelang/shake/test/Test:test(I)I")
        testMethod shouldNotBe null
        testMethod!!.simpleName shouldBe "test(I)I"
        testMethod.qualifiedName shouldBe "com/shakelang/shake/test/Test:test(I)I"
    }

    "getMethod class child child" {
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

                    Method {
                        name = "test(I)I"
                        isPublic = true
                    }
                }
            }
        })

        val testMethod = classpath.getMethod("com/shakelang/shake/test/Test:Test2:test(I)I")
        testMethod shouldNotBe null
        testMethod!!.simpleName shouldBe "test(I)I"
        testMethod.qualifiedName shouldBe "com/shakelang/shake/test/Test:Test2:test(I)I"
    }

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
})
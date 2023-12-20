package com.shakelang.shake.bytecode.interpreter.wrapper

import com.shakelang.shake.bytecode.interpreter.generator.generatePackage
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class MethodTests : FreeSpec({

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

    "getMethod when method does not exist" {
        val classpath = ShakeClasspath.create()
        classpath.load(generatePackage {
            name = "com/shakelang/shake/test"
        })

        val testMethod = classpath.getMethod("com/shakelang/shake/test/test(I)I")
        testMethod shouldBe null
    }

    "getMethod when method child does not exist" {
        val classpath = ShakeClasspath.create()
        classpath.load(generatePackage {
            name = "com/shakelang/shake/test"

            Method {
                name = "test(I)I"
                isPublic = true
            }
        })

        val testMethod = classpath.getMethod("com/shakelang/shake/test/test2(I)I")
        testMethod shouldBe null
    }

    "getMethod when method of child does not exist" {
        val classpath = ShakeClasspath.create()
        classpath.load(generatePackage {
            name = "com/shakelang/shake/test"
        })

        val testMethod = classpath.getMethod("com/shakelang/shake/test/test:test(I)I")
        testMethod shouldBe null
    }

})
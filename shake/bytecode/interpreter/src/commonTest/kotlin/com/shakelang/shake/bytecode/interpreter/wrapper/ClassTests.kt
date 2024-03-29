package com.shakelang.shake.bytecode.interpreter.wrapper

import com.shakelang.shake.bytecode.interpreter.ShakeInterpreter
import com.shakelang.shake.bytecode.interpreter.generator.generatePackage
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class ClassTests : FreeSpec(
    {

        "getClass" {
            val interpreter = ShakeInterpreter()
            val classpath = interpreter.classPath
            classpath.load(
                generatePackage {
                    name = "com/shakelang/shake/test"

                    Class {
                        isStatic = true
                        name = "Test"
                        superName = "com/shakelang/Object"
                        isPublic = true
                    }
                },
            )

            val testClass = classpath.getClass("com/shakelang/shake/test/Test")
            testClass shouldNotBe null
            testClass!!.simpleName shouldBe "Test"
            testClass.qualifiedName shouldBe "com/shakelang/shake/test/Test"
        }

        "getClass class child" {
            val interpreter = ShakeInterpreter()
            val classpath = interpreter.classPath
            classpath.load(
                generatePackage {
                    name = "com/shakelang/shake/test"

                    Class {
                        name = "Test"
                        superName = "com/shakelang/Object"
                        isPublic = true
                        isStatic = true

                        Class {
                            name = "Test2"
                            superName = "com/shakelang/Object"
                            isPublic = true
                            isStatic = true
                        }
                    }
                },
            )

            val testClass = classpath.getClass("com/shakelang/shake/test/Test:Test2")
            testClass shouldNotBe null
            testClass!!.simpleName shouldBe "Test2"
            testClass.qualifiedName shouldBe "com/shakelang/shake/test/Test:Test2"
        }

        "getClass class child child" {
            val interpreter = ShakeInterpreter()
            val classpath = interpreter.classPath
            classpath.load(
                generatePackage {
                    name = "com/shakelang/shake/test"

                    Class {
                        name = "Test"
                        superName = "com/shakelang/Object"
                        isPublic = true
                        isStatic = true

                        Class {
                            name = "Test2"
                            superName = "com/shakelang/Object"
                            isPublic = true
                            isStatic = true

                            Class {
                                name = "Test3"
                                superName = "com/shakelang/Object"
                                isPublic = true
                                isStatic = true
                            }
                        }
                    }
                },
            )

            val testClass = classpath.getClass("com/shakelang/shake/test/Test:Test2:Test3")
            testClass shouldNotBe null
            testClass!!.simpleName shouldBe "Test3"
            testClass.qualifiedName shouldBe "com/shakelang/shake/test/Test:Test2:Test3"
        }

        "getClass of child when class does not exist" {
            val interpreter = ShakeInterpreter()
            val classpath = interpreter.classPath
            classpath.load(
                generatePackage {
                    name = "com/shakelang/shake/test"
                },
            )

            val testClass = classpath.getClass("com/shakelang/shake/test/Test")
            testClass shouldBe null
        }

        "getClass of child when child does not exist" {
            val interpreter = ShakeInterpreter()
            val classpath = interpreter.classPath
            classpath.load(
                generatePackage {
                    name = "com/shakelang/shake/test"

                    Class {
                        name = "Test"
                        superName = "com/shakelang/Object"
                        isPublic = true
                        isStatic = true
                    }
                },
            )

            val testClass = classpath.getClass("com/shakelang/shake/test/Test:Test2")
            testClass shouldBe null
        }

        "getClass of child when parent class of child does not exist" {
            val interpreter = ShakeInterpreter()
            val classpath = interpreter.classPath
            classpath.load(
                generatePackage {
                    name = "com/shakelang/shake/test"
                },
            )

            val testClass = classpath.getClass("com/shakelang/shake/test/Test:Test2")
            testClass shouldBe null
        }

        "getClass when package does not exist" {
            val interpreter = ShakeInterpreter()
            val classpath = interpreter.classPath
            classpath.load(
                generatePackage {
                    name = "com/shakelang/shake/test"
                },
            )

            val testClass = classpath.getClass("com/shakelang/shake/test2/Test")
            testClass shouldBe null
        }

        "getClass of child when package does not exist" {
            val interpreter = ShakeInterpreter()
            val classpath = interpreter.classPath
            classpath.load(
                generatePackage {
                    name = "com/shakelang/shake/test"
                },
            )

            val testClass = classpath.getClass("com/shakelang/shake/test2/Test:Test2")
            testClass shouldBe null
        }

        "get memory size (no fields)" {
            val interpreter = ShakeInterpreter()
            val classpath = interpreter.classPath
            classpath.load(
                generatePackage {
                    name = "com/shakelang/shake/test"

                    Class {
                        name = "Test"
                        superName = "com/shakelang/Object"
                        isPublic = true
                        isStatic = true
                    }
                },
            )

            val testClass = classpath.getClass("com/shakelang/shake/test/Test")
            testClass shouldNotBe null
            testClass!!.sizeInMemory shouldBe 8
        }

        "get memory size (with fields)" {
            val interpreter = ShakeInterpreter()
            val classpath = interpreter.classPath
            classpath.load(
                generatePackage {
                    name = "com/shakelang/shake/test"

                    Class {
                        name = "Test"
                        superName = "com/shakelang/Object"
                        isPublic = true
                        isStatic = true

                        Field {
                            name = "test"
                            type = "Lcom/shakelang/Object;"
                            isPublic = true
                        }
                    }
                },
            )

            val testClass = classpath.getClass("com/shakelang/shake/test/Test")
            testClass shouldNotBe null
            testClass!!.sizeInMemory shouldBe 16
        }
    },
)

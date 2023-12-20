package com.shakelang.shake.bytecode.interpreter.wrapper

import com.shakelang.shake.bytecode.interpreter.generator.bytecode
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

    "getMethod when class of child does not exist" {
        val classpath = ShakeClasspath.create()
        classpath.load(generatePackage {
            name = "com/shakelang/shake/test"
        })

        val testMethod = classpath.getMethod("com/shakelang/shake/test/test:test(I)I")
        testMethod shouldBe null
    }

    "getMethod when package does not exist" {
        val classpath = ShakeClasspath.create()
        classpath.load(generatePackage {
            name = "com/shakelang/shake/test"
        })

        val testMethod = classpath.getMethod("com/shakelang/shake/test2/test(I)I")
        testMethod shouldBe null
    }

    "getMethod with child when package does not exist" {
        val classpath = ShakeClasspath.create()
        classpath.load(generatePackage {
            name = "com/shakelang/shake/test"
        })

        val testMethod = classpath.getMethod("com/shakelang/shake/test2/Test:test(I)I")
        testMethod shouldBe null
    }

    "resolve return type" {
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
        testMethod!!.returnType shouldBe ShakeInterpreterType.INT
    }

    "resolve return type with object" {
        val classpath = ShakeClasspath.create()
        classpath.load(generatePackage {
            name = "com/shakelang/shake/test"

            Method {
                name = "test()Lcom/shakelang/Object;"
                isPublic = true
            }
        })

        classpath.load(generatePackage {
            name = "com/shakelang"

            Class {
                name = "Object"
                superName = "java/lang/Object"
                isPublic = true
            }
        })

        val testMethod = classpath.getMethod("com/shakelang/shake/test/test()Lcom/shakelang/Object;")
        testMethod shouldNotBe null
        testMethod!!.returnType.type shouldBe ShakeInterpreterType.Type.OBJECT
        testMethod.returnType.name shouldBe "Lcom/shakelang/Object;"
        val clz = (testMethod.returnType as ShakeInterpreterType.ObjectType).objectType
        clz shouldNotBe null
        clz.qualifiedName shouldBe "com/shakelang/Object"
        clz.simpleName shouldBe "Object"
    }

    "resolve return type with array" {
        val classpath = ShakeClasspath.create()
        classpath.load(generatePackage {
            name = "com/shakelang/shake/test"

            Method {
                name = "test()[I"
                isPublic = true
            }
        })

        val testMethod = classpath.getMethod("com/shakelang/shake/test/test()[I")
        testMethod shouldNotBe null
        testMethod!!.returnType.type shouldBe ShakeInterpreterType.Type.ARRAY
        testMethod.returnType.name shouldBe "[I"
        val type = (testMethod.returnType as ShakeInterpreterType.ArrayType).arrayType
        type shouldBe ShakeInterpreterType.INT
    }

    "resolve return type with array of object" {
        val classpath = ShakeClasspath.create()
        classpath.load(generatePackage {
            name = "com/shakelang/shake/test"

            Method {
                name = "test()[Lcom/shakelang/Object;"
                isPublic = true
            }
        })

        classpath.load(generatePackage {
            name = "com/shakelang"

            Class {
                name = "Object"
                superName = "java/lang/Object"
                isPublic = true
            }
        })

        val testMethod = classpath.getMethod("com/shakelang/shake/test/test()[Lcom/shakelang/Object;")
        testMethod shouldNotBe null
        testMethod!!.returnType.type shouldBe ShakeInterpreterType.Type.ARRAY
        testMethod.returnType.name shouldBe "[Lcom/shakelang/Object;"
        val type = (testMethod.returnType as ShakeInterpreterType.ArrayType).arrayType
        type.type shouldBe ShakeInterpreterType.Type.OBJECT
        type.name shouldBe "Lcom/shakelang/Object;"
        val clz = (type as ShakeInterpreterType.ObjectType).objectType
        clz shouldNotBe null
        clz.qualifiedName shouldBe "com/shakelang/Object"
        clz.simpleName shouldBe "Object"
    }

    "resolve parameter types" {
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
        testMethod!!.parameters.size shouldBe 1
        testMethod.parameters[0] shouldBe ShakeInterpreterType.INT
    }

    "resolve parameter types with object" {
        val classpath = ShakeClasspath.create()
        classpath.load(generatePackage {
            name = "com/shakelang/shake/test"

            Method {
                name = "test(Lcom/shakelang/Object;)I"
                isPublic = true
            }
        })

        classpath.load(generatePackage {
            name = "com/shakelang"

            Class {
                name = "Object"
                superName = "java/lang/Object"
                isPublic = true
            }
        })

        val testMethod = classpath.getMethod("com/shakelang/shake/test/test(Lcom/shakelang/Object;)I")
        testMethod shouldNotBe null
        testMethod!!.parameters.size shouldBe 1
        testMethod.parameters[0].type shouldBe ShakeInterpreterType.Type.OBJECT
        testMethod.parameters[0].name shouldBe "Lcom/shakelang/Object;"
        val clz = (testMethod.parameters[0] as ShakeInterpreterType.ObjectType).objectType
        clz shouldNotBe null
        clz.qualifiedName shouldBe "com/shakelang/Object"
        clz.simpleName shouldBe "Object"
    }

    "resolve parameter types with array" {
        val classpath = ShakeClasspath.create()
        classpath.load(generatePackage {
            name = "com/shakelang/shake/test"

            Method {
                name = "test([I)I"
                isPublic = true
            }
        })

        val testMethod = classpath.getMethod("com/shakelang/shake/test/test([I)I")
        testMethod shouldNotBe null
        testMethod!!.parameters.size shouldBe 1
        testMethod.parameters[0].type shouldBe ShakeInterpreterType.Type.ARRAY
        testMethod.parameters[0].name shouldBe "[I"
        val type = (testMethod.parameters[0] as ShakeInterpreterType.ArrayType).arrayType
        type shouldBe ShakeInterpreterType.INT
    }

    "resolve parameter types with array of object" {
        val classpath = ShakeClasspath.create()
        classpath.load(generatePackage {
            name = "com/shakelang/shake/test"

            Method {
                name = "test([Lcom/shakelang/Object;)I"
                isPublic = true
            }
        })

        classpath.load(generatePackage {
            name = "com/shakelang"

            Class {
                name = "Object"
                superName = "java/lang/Object"
                isPublic = true
            }
        })

        val testMethod = classpath.getMethod("com/shakelang/shake/test/test([Lcom/shakelang/Object;)I")
        testMethod shouldNotBe null
        testMethod!!.parameters.size shouldBe 1
        testMethod.parameters[0].type shouldBe ShakeInterpreterType.Type.ARRAY
        testMethod.parameters[0].name shouldBe "[Lcom/shakelang/Object;"
        val type = (testMethod.parameters[0] as ShakeInterpreterType.ArrayType).arrayType
        type.type shouldBe ShakeInterpreterType.Type.OBJECT
        type.name shouldBe "Lcom/shakelang/Object;"
        val clz = (type as ShakeInterpreterType.ObjectType).objectType
        clz shouldNotBe null
        clz.qualifiedName shouldBe "com/shakelang/Object"
        clz.simpleName shouldBe "Object"
    }

    "get method code" {
        val classpath = ShakeClasspath.create()
        classpath.load(generatePackage {
            name = "com/shakelang/shake/test"

            Method {
                name = "test()I"
                isPublic = true
                code {
                    this.bytecode {
                        ipush(4)
                        ipush(5)
                        iadd()
                    }
                    this.maxStack = 32
                    this.maxLocals = 100
                }
            }
        })

        val testMethod = classpath.getMethod("com/shakelang/shake/test/test()I")
        testMethod shouldNotBe null
        testMethod!!.code shouldBe bytecode {
            ipush(4)
            ipush(5)
            iadd()
        }
        testMethod.maxStack shouldBe 32
        testMethod.maxLocals shouldBe 100
    }
})
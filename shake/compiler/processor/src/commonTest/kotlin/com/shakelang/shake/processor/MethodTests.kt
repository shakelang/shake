package com.shakelang.shake.processor

import com.shakelang.shake.processor.program.creation.CreationShakeType
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class MethodTests : FreeSpec(
    {

        "method declaration (no params, void)" {

            val processor = createBaseProcessor()
            processor.loadSynthetic(
                "test.shake",
                """
                package test
                
                fun main() {}
                """.trimIndent(),
            )

            processor.finish()

            val project = processor.project
            val method = project.getFunctions("test.main")[0]

            method.returnType shouldBe CreationShakeType.Primitive.VOID
            method.parameters.size shouldBe 0
            method.parameters shouldBe emptyList()
            method.isFinal shouldBe false
            method.isStatic shouldBe false
            method.isAbstract shouldBe false
            method.isNative shouldBe false
            method.isOperator shouldBe false
            method.isPublic shouldBe false
            method.isProtected shouldBe false
            method.isPrivate shouldBe false

            method.name shouldBe "main"
            method.qualifiedName shouldBe "test/main"
            method.signature shouldBe "main()V"
            method.qualifiedSignature shouldBe "test/main()V"
        }

        "method declaration (no params, void, final)" {

            val processor = createBaseProcessor()
            processor.loadSynthetic(
                "test.shake",
                """
                package test
                
                final fun main() {}
                """.trimIndent(),
            )

            processor.finish()

            val project = processor.project
            val method = project.getFunctions("test.main")[0]

            method.returnType shouldBe CreationShakeType.Primitive.VOID
            method.parameters.size shouldBe 0
            method.parameters shouldBe emptyList()
            method.isFinal shouldBe true
            method.isStatic shouldBe false
            method.isAbstract shouldBe false
            method.isNative shouldBe false
            method.isOperator shouldBe false
            method.isPublic shouldBe false
            method.isProtected shouldBe false
            method.isPrivate shouldBe false

            method.name shouldBe "main"
            method.qualifiedName shouldBe "test/main"
            method.signature shouldBe "main()V"
            method.qualifiedSignature shouldBe "test/main()V"
        }

        // TODO: static test. Should fail, static methods are only allowed in classes

        // TODO: abstract test. Should fail, abstract methods are only allowed in classes

        "method declaration (no params, void, native)" {

            val processor = createBaseProcessor()
            processor.loadSynthetic(
                "test.shake",
                """
                package test
                
                native fun main();
                """.trimIndent(),
            )

            processor.finish()

            val project = processor.project
            val method = project.getFunctions("test.main")[0]

            method.returnType shouldBe CreationShakeType.Primitive.VOID
            method.parameters.size shouldBe 0
            method.parameters shouldBe emptyList()
            method.isFinal shouldBe false
            method.isStatic shouldBe false
            method.isAbstract shouldBe false
            method.isNative shouldBe true
            method.isOperator shouldBe false
            method.isPublic shouldBe false
            method.isProtected shouldBe false
            method.isPrivate shouldBe false

            method.name shouldBe "main"
            method.qualifiedName shouldBe "test/main"
            method.signature shouldBe "main()V"
            method.qualifiedSignature shouldBe "test/main()V"
        }

        // TODO: operator test.
        //  Should fail, operator methods are only allowed in classes or as extension methods
        //  Also add a test for operators with wrong signature

        "method declaration (no params, void, public)" {

            val processor = createBaseProcessor()
            processor.loadSynthetic(
                "test.shake",
                """
            package test
            
            public fun main() {}
                """.trimIndent(),
            )

            processor.finish()

            val project = processor.project
            val method = project.getFunctions("test.main")[0]

            method.returnType shouldBe CreationShakeType.Primitive.VOID
            method.parameters.size shouldBe 0
            method.parameters shouldBe emptyList()
            method.isFinal shouldBe false
            method.isStatic shouldBe false
            method.isAbstract shouldBe false
            method.isNative shouldBe false
            method.isOperator shouldBe false
            method.isPublic shouldBe true
            method.isProtected shouldBe false
            method.isPrivate shouldBe false

            method.name shouldBe "main"
            method.qualifiedName shouldBe "test/main"
            method.signature shouldBe "main()V"
            method.qualifiedSignature shouldBe "test/main()V"
        }

        "method declaration (no params, void, protected)" {

            val processor = createBaseProcessor()
            processor.loadSynthetic(
                "test.shake",
                """
            package test
            
            protected fun main() {}
                """.trimIndent(),
            )

            processor.finish()

            val project = processor.project
            val method = project.getFunctions("test.main")[0]

            method.returnType shouldBe CreationShakeType.Primitive.VOID
            method.parameters.size shouldBe 0
            method.parameters shouldBe emptyList()
            method.isFinal shouldBe false
            method.isStatic shouldBe false
            method.isAbstract shouldBe false
            method.isNative shouldBe false
            method.isOperator shouldBe false
            method.isPublic shouldBe false
            method.isProtected shouldBe true
            method.isPrivate shouldBe false

            method.name shouldBe "main"
            method.qualifiedName shouldBe "test/main"
            method.signature shouldBe "main()V"
            method.qualifiedSignature shouldBe "test/main()V"
        }

        "method declaration (no params, void, private)" {

            val processor = createBaseProcessor()
            processor.loadSynthetic(
                "test.shake",
                """
            package test
            
            private fun main() {}
                """.trimIndent(),
            )

            processor.finish()

            val project = processor.project
            val method = project.getFunctions("test.main")[0]

            method.returnType shouldBe CreationShakeType.Primitive.VOID
            method.parameters.size shouldBe 0
            method.parameters shouldBe emptyList()
            method.isFinal shouldBe false
            method.isStatic shouldBe false
            method.isAbstract shouldBe false
            method.isNative shouldBe false
            method.isOperator shouldBe false
            method.isPublic shouldBe false
            method.isProtected shouldBe false
            method.isPrivate shouldBe true

            method.name shouldBe "main"
            method.qualifiedName shouldBe "test/main"
            method.signature shouldBe "main()V"
            method.qualifiedSignature shouldBe "test/main()V"
        }

        "method declaration (no params, int)" {

            val processor = createBaseProcessor()
            processor.loadSynthetic(
                "test.shake",
                """
                package test
                
                fun main(): int { return 0; }
                """.trimIndent(),
            )

            processor.finish()

            val project = processor.project
            val method = project.getFunctions("test.main")[0]

            method.returnType shouldBe CreationShakeType.Primitive.INT
            method.parameters.size shouldBe 0
            method.parameters shouldBe emptyList()
            method.isFinal shouldBe false
            method.isStatic shouldBe false
            method.isAbstract shouldBe false
            method.isNative shouldBe false
            method.isOperator shouldBe false
            method.isPublic shouldBe false
            method.isProtected shouldBe false
            method.isPrivate shouldBe false

            method.name shouldBe "main"
            method.qualifiedName shouldBe "test/main"
            method.signature shouldBe "main()I"
            method.qualifiedSignature shouldBe "test/main()I"
        }

        "method declaration (one param, int)" {

            val processor = createBaseProcessor()
            processor.loadSynthetic(
                "test.shake",
                """
                package test
                
                fun main(a: int): int { return 0; }
                """.trimIndent(),
            )

            processor.finish()

            val project = processor.project
            val method = project.getFunctions("test.main")[0]

            method.returnType shouldBe CreationShakeType.Primitive.INT
            method.parameters.size shouldBe 1

            val param = method.parameters[0]

            param.name shouldBe "a"
            param.type shouldBe CreationShakeType.Primitive.INT

            method.isFinal shouldBe false
            method.isStatic shouldBe false
            method.isAbstract shouldBe false
            method.isNative shouldBe false
            method.isOperator shouldBe false
            method.isPublic shouldBe false
            method.isProtected shouldBe false
            method.isPrivate shouldBe false

            method.name shouldBe "main"
            method.qualifiedName shouldBe "test/main"
            method.signature shouldBe "main(I)I"
            method.qualifiedSignature shouldBe "test/main(I)I"
        }

        "method declaration (two params, int)" {

            val processor = createBaseProcessor()
            processor.loadSynthetic(
                "test.shake",
                """
                package test
                
                fun main(a: int, b: int): int { return 0; }
                """.trimIndent(),
            )

            processor.finish()

            val project = processor.project
            val method = project.getFunctions("test.main")[0]

            method.returnType shouldBe CreationShakeType.Primitive.INT
            method.parameters.size shouldBe 2

            val param1 = method.parameters[0]
            val param2 = method.parameters[1]

            param1.name shouldBe "a"
            param1.type shouldBe CreationShakeType.Primitive.INT

            param2.name shouldBe "b"
            param2.type shouldBe CreationShakeType.Primitive.INT

            method.isFinal shouldBe false
            method.isStatic shouldBe false
            method.isAbstract shouldBe false
            method.isNative shouldBe false
            method.isOperator shouldBe false
            method.isPublic shouldBe false
            method.isProtected shouldBe false
            method.isPrivate shouldBe false

            method.name shouldBe "main"
            method.qualifiedName shouldBe "test/main"
            method.signature shouldBe "main(I,I)I"
            method.qualifiedSignature shouldBe "test/main(I,I)I"
        }

        // Methods inside classes

        "method declaration (no params, void) inside class" {

            val processor = createBaseProcessor()
            processor.loadSynthetic(
                "test.shake",
                """
            package test
            
            class Test {
                fun main() {}
            }
                """.trimIndent(),
            )

            processor.finish()

            val project = processor.project
            val method = project.getClass("test.Test")!!.getMethods("main")[0]

            method.returnType shouldBe CreationShakeType.Primitive.VOID
            method.parameters.size shouldBe 0
            method.parameters shouldBe emptyList()
            method.isFinal shouldBe false
            method.isStatic shouldBe false
            method.isAbstract shouldBe false
            method.isNative shouldBe false
            method.isOperator shouldBe false
            method.isPublic shouldBe false
            method.isProtected shouldBe false
            method.isPrivate shouldBe false

            method.name shouldBe "main"
            method.qualifiedName shouldBe "test/Test:main"
            method.signature shouldBe "main()V"
            method.qualifiedSignature shouldBe "test/Test:main()V"
        }

        "method declaration (no params, void, final) inside class" {

            val processor = createBaseProcessor()
            processor.loadSynthetic(
                "test.shake",
                """
            package test
            
            class Test {
                final fun main() {}
            }
                """.trimIndent(),
            )

            processor.finish()

            val project = processor.project
            val method = project.getClass("test.Test")!!.getMethods("main")[0]

            method.returnType shouldBe CreationShakeType.Primitive.VOID
            method.parameters.size shouldBe 0
            method.parameters shouldBe emptyList()
            method.isFinal shouldBe true
            method.isStatic shouldBe false
            method.isAbstract shouldBe false
            method.isNative shouldBe false
            method.isOperator shouldBe false
            method.isPublic shouldBe false
            method.isProtected shouldBe false
            method.isPrivate shouldBe false

            method.name shouldBe "main"
            method.qualifiedName shouldBe "test/Test:main"
            method.signature shouldBe "main()V"
            method.qualifiedSignature shouldBe "test/Test:main()V"
        }

        "method declaration (no params, void, static) inside class" {

            val processor = createBaseProcessor()
            processor.loadSynthetic(
                "test.shake",
                """
            package test
            
            class Test {
                static fun main() {}
            }
                """.trimIndent(),
            )

            processor.finish()

            val project = processor.project
            val method = project.getClass("test.Test")!!.getMethods("main")[0]

            method.returnType shouldBe CreationShakeType.Primitive.VOID
            method.parameters.size shouldBe 0
            method.parameters shouldBe emptyList()
            method.isFinal shouldBe false
            method.isStatic shouldBe true
            method.isAbstract shouldBe false
            method.isNative shouldBe false
            method.isOperator shouldBe false
            method.isPublic shouldBe false
            method.isProtected shouldBe false
            method.isPrivate shouldBe false

            method.name shouldBe "main"
            method.qualifiedName shouldBe "test/Test:main"
            method.signature shouldBe "main()V"
            method.qualifiedSignature shouldBe "test/Test:main()V"
        }

        // TODO: Abstract test

        "method declaration (no params, void, native) inside class" {

            val processor = createBaseProcessor()
            processor.loadSynthetic(
                "test.shake",
                """
            package test
            
            class Test {
                native fun main();
            }
                """.trimIndent(),
            )

            processor.finish()

            val project = processor.project
            val method = project.getClass("test.Test")!!.getMethods("main")[0]

            method.returnType shouldBe CreationShakeType.Primitive.VOID
            method.parameters.size shouldBe 0
            method.parameters shouldBe emptyList()
            method.isFinal shouldBe false
            method.isStatic shouldBe false
            method.isAbstract shouldBe false
            method.isNative shouldBe true
            method.isOperator shouldBe false
            method.isPublic shouldBe false
            method.isProtected shouldBe false
            method.isPrivate shouldBe false

            method.name shouldBe "main"
            method.qualifiedName shouldBe "test/Test:main"
            method.signature shouldBe "main()V"
            method.qualifiedSignature shouldBe "test/Test:main()V"
        }

        "method declaration (one param, int, operator) inside class" {

            val processor = createBaseProcessor()
            processor.loadSynthetic(
                "test.shake",
                """
            package test
            
            class Test {
                operator fun add(i: int): int {
                    return i;
                }
            }
                """.trimIndent(),
            )

            processor.finish()

            val project = processor.project
            val method = project.getClass("test.Test")!!.getMethods("add")[0]

            method.returnType shouldBe CreationShakeType.Primitive.INT
            method.parameters.size shouldBe 1
            method.isFinal shouldBe false
            method.isStatic shouldBe false
            method.isAbstract shouldBe false
            method.isNative shouldBe false
            method.isOperator shouldBe true
            method.isPublic shouldBe false
            method.isProtected shouldBe false
            method.isPrivate shouldBe false

            method.name shouldBe "add"
            method.qualifiedName shouldBe "test/Test:add"
            method.signature shouldBe "add(I)I"
            method.qualifiedSignature shouldBe "test/Test:add(I)I"
        }

        "method declaration (no params, void, public) inside class" {

            val processor = createBaseProcessor()
            processor.loadSynthetic(
                "test.shake",
                """
            package test
            
            class Test {
                public fun main() {}
            }
                """.trimIndent(),
            )

            processor.finish()

            val project = processor.project
            val method = project.getClass("test.Test")!!.getMethods("main")[0]

            method.returnType shouldBe CreationShakeType.Primitive.VOID
            method.parameters.size shouldBe 0
            method.parameters shouldBe emptyList()
            method.isFinal shouldBe false
            method.isStatic shouldBe false
            method.isAbstract shouldBe false
            method.isNative shouldBe false
            method.isOperator shouldBe false
            method.isPublic shouldBe true
            method.isProtected shouldBe false
            method.isPrivate shouldBe false

            method.name shouldBe "main"
            method.qualifiedName shouldBe "test/Test:main"
            method.signature shouldBe "main()V"
            method.qualifiedSignature shouldBe "test/Test:main()V"
        }

        "method declaration (no params, void, protected) inside class" {

            val processor = createBaseProcessor()
            processor.loadSynthetic(
                "test.shake",
                """
            package test
            
            class Test {
                protected fun main() {}
            }
                """.trimIndent(),
            )

            processor.finish()

            val project = processor.project
            val method = project.getClass("test.Test")!!.getMethods("main")[0]

            method.returnType shouldBe CreationShakeType.Primitive.VOID
            method.parameters.size shouldBe 0
            method.parameters shouldBe emptyList()
            method.isFinal shouldBe false
            method.isStatic shouldBe false
            method.isAbstract shouldBe false
            method.isNative shouldBe false
            method.isOperator shouldBe false
            method.isPublic shouldBe false
            method.isProtected shouldBe true
            method.isPrivate shouldBe false

            method.name shouldBe "main"
            method.qualifiedName shouldBe "test/Test:main"
            method.signature shouldBe "main()V"
            method.qualifiedSignature shouldBe "test/Test:main()V"
        }

        "method declaration (no params, void, private) inside class" {

            val processor = createBaseProcessor()
            processor.loadSynthetic(
                "test.shake",
                """
            package test
            
            class Test {
                private fun main() {}
            }
                """.trimIndent(),
            )

            processor.finish()

            val project = processor.project
            val method = project.getClass("test.Test")!!.getMethods("main")[0]

            method.returnType shouldBe CreationShakeType.Primitive.VOID
            method.parameters.size shouldBe 0
            method.parameters shouldBe emptyList()
            method.isFinal shouldBe false
            method.isStatic shouldBe false
            method.isAbstract shouldBe false
            method.isNative shouldBe false
            method.isOperator shouldBe false
            method.isPublic shouldBe false
            method.isProtected shouldBe false
            method.isPrivate shouldBe true

            method.name shouldBe "main"
            method.qualifiedName shouldBe "test/Test:main"
            method.signature shouldBe "main()V"
            method.qualifiedSignature shouldBe "test/Test:main()V"
        }

        "method declaration (no params, int) inside class" {

            val processor = createBaseProcessor()
            processor.loadSynthetic(
                "test.shake",
                """
                package test
                
                class Test {
                    fun main(): int { return 0; }
                }
                """.trimIndent(),
            )

            processor.finish()

            val project = processor.project
            val method = project.getClass("test.Test")!!.getMethods("main")[0]

            method.returnType shouldBe CreationShakeType.Primitive.INT
            method.parameters.size shouldBe 0
            method.parameters shouldBe emptyList()
            method.isFinal shouldBe false
            method.isStatic shouldBe false
            method.isAbstract shouldBe false
            method.isOperator shouldBe false
            method.isNative shouldBe false
            method.isPublic shouldBe false
            method.isProtected shouldBe false
            method.isPrivate shouldBe false

            method.name shouldBe "main"
            method.qualifiedName shouldBe "test/Test:main"
            method.signature shouldBe "main()I"
            method.qualifiedSignature shouldBe "test/Test:main()I"
        }

        "method declaration (one param, void) inside class" {

            val processor = createBaseProcessor()
            processor.loadSynthetic(
                "test.shake",
                """
                package test
                
                class Test {
                    fun main(a: int) {}
                }
                """.trimIndent(),
            )

            processor.finish()

            val project = processor.project
            val method = project.getClass("test.Test")!!.getMethods("main")[0]

            method.returnType shouldBe CreationShakeType.Primitive.VOID
            method.parameters.size shouldBe 1

            val param = method.parameters[0]

            param.name shouldBe "a"
            param.type shouldBe CreationShakeType.Primitive.INT

            method.isFinal shouldBe false
            method.isStatic shouldBe false
            method.isAbstract shouldBe false
            method.isOperator shouldBe false
            method.isNative shouldBe false
            method.isPublic shouldBe false
            method.isProtected shouldBe false
            method.isPrivate shouldBe false

            method.name shouldBe "main"
            method.qualifiedName shouldBe "test/Test:main"
            method.signature shouldBe "main(I)V"
            method.qualifiedSignature shouldBe "test/Test:main(I)V"
        }

        "method declaration (two params, void) inside class" {

            val processor = createBaseProcessor()
            processor.loadSynthetic(
                "test.shake",
                """
                package test
                
                class Test {
                    fun main(a: int, b: int) {}
                }
                """.trimIndent(),
            )

            processor.finish()

            val project = processor.project
            val method = project.getClass("test.Test")!!.getMethods("main")[0]

            method.returnType shouldBe CreationShakeType.Primitive.VOID
            method.parameters.size shouldBe 2

            val param1 = method.parameters[0]
            val param2 = method.parameters[1]

            param1.name shouldBe "a"
            param1.type shouldBe CreationShakeType.Primitive.INT

            param2.name shouldBe "b"
            param2.type shouldBe CreationShakeType.Primitive.INT

            method.isFinal shouldBe false
            method.isStatic shouldBe false
            method.isAbstract shouldBe false
            method.isOperator shouldBe false
            method.isNative shouldBe false
            method.isPublic shouldBe false
            method.isProtected shouldBe false
            method.isPrivate shouldBe false

            method.name shouldBe "main"
            method.qualifiedName shouldBe "test/Test:main"
            method.signature shouldBe "main(I,I)V"
            method.qualifiedSignature shouldBe "test/Test:main(I,I)V"
        }
    },
)

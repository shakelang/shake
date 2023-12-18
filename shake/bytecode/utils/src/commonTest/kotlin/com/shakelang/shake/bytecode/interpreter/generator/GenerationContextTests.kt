package com.shakelang.shake.bytecode.interpreter.generator

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class GenerationContextTests : FreeSpec({

    "generation context" {

        val ctx = GenerationContext()
        ctx.classes.size shouldBe 0
        ctx.methods.size shouldBe 0
        ctx.fields.size shouldBe 0
    }

    "field generation" {

        val ctx = GenerationContext()
        ctx.Field {
            name = "test"
            flags = 0b0000000_00010000.toShort()
        }

        ctx.fields.size shouldBe 1
    }

    "method generation" {

        val ctx = GenerationContext()
        ctx.Method {
            name = "test"
            flags = 0b0000000_00010000.toShort()
        }

        ctx.methods.size shouldBe 1
    }

    "class generation" {

        val ctx = GenerationContext()
        ctx.Class {
            name = "test"
            superName = "super"
            flags = 0b0000000_00010000.toShort()
        }

        ctx.classes.size shouldBe 1
    }

    "toStorageFormat" {

        val ctx = GenerationContext()
        ctx.Class {
            name = "test"
            superName = "super"
            flags = 0b0000000_00010000.toShort()
        }

        ctx.Method {
            name = "test"
            flags = 0b0000000_00010000.toShort()
        }

        ctx.Field {
            name = "test"
            flags = 0b0000000_00010000.toShort()
        }

        val format = ctx.toStorageFormat()
        format.classes.size shouldBe 1
        format.methods.size shouldBe 1
        format.fields.size shouldBe 1
    }

    "toMutableStorageFormat" {

        val ctx = GenerationContext()
        ctx.Class {
            name = "test"
            superName = "super"
            flags = 0b0000000_00010000.toShort()
        }

        ctx.Method {
            name = "test"
            flags = 0b0000000_00010000.toShort()
        }

        ctx.Field {
            name = "test"
            flags = 0b0000000_00010000.toShort()
        }

        val format = ctx.toMutableStorageFormat()
        format.classes.size shouldBe 1
        format.methods.size shouldBe 1
        format.fields.size shouldBe 1
    }

    "generate shortcut" {
        val format = generate {
            Class {
                name = "test"
                superName = "super"
                flags = 0b0000000_00010000.toShort()
            }

            Method {
                name = "test"
                flags = 0b0000000_00010000.toShort()
            }

            Field {
                name = "test"
                flags = 0b0000000_00010000.toShort()
            }
        }

        format.classes.size shouldBe 1
        format.methods.size shouldBe 1
        format.fields.size shouldBe 1
    }
})
package com.shakelang.shake.conventions.descriptor

import com.shakelang.shake.bytecode.interpreter.format.descriptor.PathDescriptor
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class PathDescriptorTests :
    FreeSpec(
        {

            "descriptor should return the correct descriptor" {
                val descriptor = PathDescriptor(
                    arrayOf("shake", "lang"),
                    arrayOf("String", "Builder"),
                    "build",
                )
                descriptor.descriptor shouldBe "shake/lang/String:Builder:build"
            }

            "packagePath should return the correct package path" {
                val descriptor = PathDescriptor(
                    arrayOf("shake", "lang"),
                    arrayOf("String", "Builder"),
                    "build",
                )
                descriptor.packagePath shouldBe "shake/lang"
            }

            "classPath should return the correct class path" {
                val descriptor = PathDescriptor(
                    arrayOf("shake", "lang"),
                    arrayOf("String", "Builder"),
                    "build",
                )
                descriptor.classPath shouldBe "String:Builder"
            }

            "parse should parse the descriptor correctly" {
                val descriptor = PathDescriptor.parse("shake/lang/String:Builder:build")
                descriptor.packagePath shouldBe "shake/lang"
                descriptor.classPath shouldBe "String:Builder"
                descriptor.entity shouldBe "build"
            }
        },
    )

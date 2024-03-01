package com.shakelang.shake.shakelib.generator

import com.shakelang.shake.shakespeare.builder.buildShakeFile
import java.io.File

fun main() {
    val numbersShake = File("../src/commonMain/resources/src/common/shake/lang/Numbers.shake").absoluteFile

    numbersShake.writeText(
        buildShakeFile {
            val types = listOf(
                "ubyte" to "byte",
                "byte" to "byte",
                "ushort" to "short",
                "short" to "short",
                "uint" to "int",
                "int" to "int",
                "ulong" to "long",
                "long" to "long",
                "float" to "float",
                "double" to "double",
            )

            val operations = listOf(
                "plus" to null,
                "minus" to null,
                "times" to null,
                "div" to null,
                "rem" to null,
                "pow" to "double",
                "band" to null,
                "bor" to null,
                "bxor" to null,
                "bnand" to null,
                "bnor" to null,
                "bxnor" to null,
                "shl" to null,
                "shr" to null,
                "ushr" to null,
            )

            for (operation in operations) {
                for (type0 in types) {
                    for (type1 in types) {
                        Method {
                            name(operation.first)
                            extending(type0.first)
                            operator()
                            parameter {
                                var c = type1.first.toCharArray().first().lowercase()
                                if (c == "u") c = type1.first.toCharArray()[1].lowercase()
                                name(c)
                                type(type1.first)
                            }

                            val retType = if (types.indexOf(type0) < types.indexOf(type1)) type1 else type0
                            if (type0 == type1) {
                                returnType(operation.second ?: retType.first)
                            } else {
                                returnType(operation.second ?: retType.second)
                            }
                        }
                    }

                    newline()
                }

                newline()
            }

            for (type in types) {
                Method {
                    name("bnot")
                    extending(type.first)
                    returnType(type.first)
                }
            }

            newline()
        },
        Charsets.UTF_8,
    )
}

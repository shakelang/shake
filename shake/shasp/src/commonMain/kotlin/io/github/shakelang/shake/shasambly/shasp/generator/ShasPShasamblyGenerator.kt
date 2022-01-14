package io.github.shakelang.shake.shasambly.shasp.generator

import io.github.shakelang.shake.shasambly.generator.simple.SimpleShasambly
import io.github.shakelang.shake.shasambly.generator.simple.SimpleShasamblyGenerator
import io.github.shakelang.shake.shasambly.generator.simple.util.function.CallableRoutine
import io.github.shakelang.shake.shasambly.generator.simple.util.function.declareRoutine
import io.github.shakelang.shake.shasambly.shasp.parser.nodes.*
import io.github.shakelang.shake.shasambly.shasp.parser.nodes.ShasPType.Companion.BOOLEAN
import io.github.shakelang.shake.shasambly.shasp.parser.nodes.ShasPType.Companion.BYTE
import io.github.shakelang.shake.shasambly.shasp.parser.nodes.ShasPType.Companion.CHAR
import io.github.shakelang.shake.shasambly.shasp.parser.nodes.ShasPType.Companion.DOUBLE
import io.github.shakelang.shake.shasambly.shasp.parser.nodes.ShasPType.Companion.FLOAT
import io.github.shakelang.shake.shasambly.shasp.parser.nodes.ShasPType.Companion.INT
import io.github.shakelang.shake.shasambly.shasp.parser.nodes.ShasPType.Companion.LONG
import io.github.shakelang.shake.shasambly.shasp.parser.nodes.ShasPType.Companion.SHORT

class ShasPShasamblyGenerator(val tree: ShasPProgram) {

    private val functions = mutableListOf<Pair<ShasPFunctionDeclaration, CallableRoutine>>()
    private var localPosCounter: Int = 0
    private lateinit var gen: SimpleShasamblyGenerator

    fun process(): SimpleShasamblyGenerator {
        this.gen = SimpleShasamblyGenerator {}
        process(this.tree)
        return this.gen
    }

    fun process(tree: ShasPProgram) {
        tree.children.forEach {
            if(it is ShasPFunctionDeclaration) process(it)
            if(it is ShasPVariableDeclaration) TODO("Global variables are not implemented for now")
        }
        functions.forEach {

        }
    }

    private fun process(tree: ShasPFunctionDeclaration) {
        gen.run {
            val argSize = tree.args.sumOf { it.type.byteSize }
            localPosCounter = 0
            functions.add(tree to declareRoutine(argSize))
        }
    }

    private inner class ShasPShasamblyFunctionGenerator(
        val function: ShasPFunctionDeclaration,
        val routine: CallableRoutine
    ) {

        val localTable = mutableMapOf<String, Pair<ShasPType, Int>>()

        var lastLocalPointer = 0

        fun create() {
            routine.create {
                function.body.children.forEach {
                    process(it)
                }
            }
        }

        fun SimpleShasambly.process(it: ShasPStatement) {
            if(it is ShasPVariableDeclaration) process(it)
        }

        fun SimpleShasambly.generate(value: ShasPValuedNode): ShasPType {
            return when(value) {
                is ShasPAdd -> generate(value)
                is ShasPSub -> generate(value)
                else -> TODO()
            }
        }

        fun SimpleShasambly.generate(value: ShasPAdd): ShasPType {
            val type0 = generate(value.left)
            val type1 = generate(value.right)
            if(type0 != type1) throw Error("Types $type0 and $type1 are not equal for addition")
            when(type0) {
                BYTE -> badd()
                SHORT -> sadd()
                INT -> iadd()
                LONG -> ladd()
                FLOAT -> fadd()
                DOUBLE -> dadd()
                else -> throw Error("Can't perform additions with value type $type0.")
            }
            return type0
        }

        fun SimpleShasambly.generate(value: ShasPSub): ShasPType {
            val type0 = generate(value.left)
            val type1 = generate(value.right)
            if(type0 != type1) throw Error("Types $type0 and $type1 are not equal for subtraction")
            when(type0) {
                BYTE -> bsub()
                SHORT -> ssub()
                INT -> isub()
                LONG -> lsub()
                FLOAT -> fsub()
                DOUBLE -> dsub()
                else -> throw Error("Can't perform subtractions with value type $type0.")
            }
            return type0
        }

        fun SimpleShasambly.setLocal(addr: Int, type: ShasPType, value: ShasPValuedNode) {
            generate(value)
            when(type) {
                BYTE, BOOLEAN -> b_store_local(addr)
                SHORT, CHAR -> s_store_local(addr)
                INT, FLOAT -> i_store_local(addr)
                LONG, DOUBLE -> l_store_local(addr)
            }
        }

        fun SimpleShasambly.process(it: ShasPVariableDeclaration) {
            if(localTable.containsKey(it.name)) throw Error("Variable $it is already defined in this scope")
            localTable[it.name] = it.type to lastLocalPointer
            lastLocalPointer += it.type.byteSize
            if(it.value != null) {

            }
        }
    }

}
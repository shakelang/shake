package io.github.shakelang.shake.shasambly.shasp.generator

import io.github.shakelang.shake.shasambly.generator.simple.SimpleShasambly
import io.github.shakelang.shake.shasambly.generator.simple.SimpleShasamblyGenerator
import io.github.shakelang.shake.shasambly.generator.simple.util.function.CallableRoutine
import io.github.shakelang.shake.shasambly.generator.simple.util.function.declareRoutine
import io.github.shakelang.shake.shasambly.interpreter.PrimitiveIds
import io.github.shakelang.shake.shasambly.shasp.parser.nodes.*
import io.github.shakelang.shake.shasambly.shasp.parser.nodes.ShasPType.Companion.BOOLEAN
import io.github.shakelang.shake.shasambly.shasp.parser.nodes.ShasPType.Companion.BYTE
import io.github.shakelang.shake.shasambly.shasp.parser.nodes.ShasPType.Companion.CHAR
import io.github.shakelang.shake.shasambly.shasp.parser.nodes.ShasPType.Companion.DOUBLE
import io.github.shakelang.shake.shasambly.shasp.parser.nodes.ShasPType.Companion.FLOAT
import io.github.shakelang.shake.shasambly.shasp.parser.nodes.ShasPType.Companion.INT
import io.github.shakelang.shake.shasambly.shasp.parser.nodes.ShasPType.Companion.LONG
import io.github.shakelang.shake.shasambly.shasp.parser.nodes.ShasPType.Companion.SHORT
import io.github.shakelang.shake.shasambly.shasp.parser.nodes.ShasPType.Companion.UNKNOWN_DOUBLE_LITERAL
import io.github.shakelang.shake.shasambly.shasp.parser.nodes.ShasPType.Companion.UNKNOWN_INTEGER_LITERAL

class ShasPShasamblyGenerator(val tree: ShasPProgram) {

    private val functions = mutableListOf<ShasPShasamblyFunctionGenerator>()
    private var localPosCounter: Int = 0
    private lateinit var gen: SimpleShasamblyGenerator

    fun generate(): SimpleShasamblyGenerator {
        this.gen = SimpleShasamblyGenerator {}
        generate(this.tree)
        return this.gen
    }

    fun generate(tree: ShasPProgram) {
        tree.children.forEach {
            if(it is ShasPFunctionDeclaration) generate(it)
            if(it is ShasPVariableDeclaration) TODO("Global variables are not implemented for now")
        }
        functions.forEach {
            it.create()
        }
    }

    private fun generate(function: ShasPFunctionDeclaration) {
        gen.run {
            val argSize = function.args.sumOf { it.type.byteSize }
            localPosCounter = 0
            functions.add(ShasPShasamblyFunctionGenerator(function, declareRoutine(argSize)))
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
                    this.generate(it)
                }
            }
        }

        fun SimpleShasambly.generate(it: ShasPStatement) {
            if(it is ShasPVariableDeclaration) this.generate(it)
            if(it is ShasPVariableAssignment) this.generate(it)
            if(it is ShasPIf) this.generate(it)
            if(it is ShasPWhile) this.generate(it)
            if(it is ShasPDoWhile) this.generate(it)
            if(it is ShasPFor) this.generate(it)
        }

        fun findType(type0: ShasPType, type1: ShasPType): ShasPType? {
            if(type0 == type1) return type0
            if(type0 == BYTE && type1 == SHORT) return SHORT
            if(type0 == BYTE && type1 == INT) return INT
            if(type0 == BYTE && type1 == LONG) return LONG
            if(type0 == BYTE && type1 == FLOAT) return FLOAT
            if(type0 == BYTE && type1 == DOUBLE) return DOUBLE
            if(type0 == SHORT && type1 == BYTE) return SHORT
            if(type0 == SHORT && type1 == INT) return INT
            if(type0 == SHORT && type1 == LONG) return LONG
            if(type0 == SHORT && type1 == FLOAT) return FLOAT
            if(type0 == SHORT && type1 == DOUBLE) return DOUBLE
            if(type0 == INT && type1 == BYTE) return INT
            if(type0 == INT && type1 == SHORT) return INT
            if(type0 == INT && type1 == LONG) return LONG
            if(type0 == INT && type1 == FLOAT) return FLOAT
            if(type0 == INT && type1 == DOUBLE) return DOUBLE
            if(type0 == LONG && type1 == BYTE) return LONG
            if(type0 == LONG && type1 == SHORT) return LONG
            if(type0 == LONG && type1 == INT) return LONG
            if(type0 == LONG && type1 == FLOAT) return FLOAT
            if(type0 == LONG && type1 == DOUBLE) return DOUBLE
            if(type0 == FLOAT && type1 == BYTE) return FLOAT
            if(type0 == FLOAT && type1 == SHORT) return FLOAT
            if(type0 == FLOAT && type1 == INT) return FLOAT
            if(type0 == FLOAT && type1 == LONG) return FLOAT
            if(type0 == FLOAT && type1 == DOUBLE) return FLOAT
            if(type0 == DOUBLE && type1 == BYTE) return DOUBLE
            if(type0 == DOUBLE && type1 == SHORT) return DOUBLE
            if(type0 == DOUBLE && type1 == INT) return DOUBLE
            if(type0 == DOUBLE && type1 == LONG) return DOUBLE
            if(type0 == DOUBLE && type1 == FLOAT) return DOUBLE

            if(type0 == CHAR && type1 == BYTE || type0 == BYTE && type1 == CHAR) return SHORT
            if(type0 == CHAR && type1 == SHORT || type0 == SHORT && type1 == CHAR) return SHORT
            if(type0 == CHAR && type1 == INT || type0 == INT && type1 == CHAR) return INT
            if(type0 == CHAR && type1 == LONG || type0 == LONG && type1 == CHAR) return LONG
            if(type0 == CHAR && type1 == FLOAT || type0 == FLOAT && type1 == CHAR) return FLOAT
            if(type0 == CHAR && type1 == DOUBLE || type0 == DOUBLE && type1 == CHAR) return DOUBLE


            if(type0 == UNKNOWN_INTEGER_LITERAL && type1 == BYTE || type0 == BYTE && type1 == UNKNOWN_INTEGER_LITERAL) return BYTE
            if(type0 == UNKNOWN_INTEGER_LITERAL && type1 == SHORT || type0 == SHORT && type1 == UNKNOWN_INTEGER_LITERAL) return SHORT
            if(type0 == UNKNOWN_INTEGER_LITERAL && type1 == INT || type0 == INT && type1 == UNKNOWN_INTEGER_LITERAL) return INT
            if(type0 == UNKNOWN_INTEGER_LITERAL && type1 == LONG || type0 == LONG && type1 == UNKNOWN_INTEGER_LITERAL) return LONG
            if(type0 == UNKNOWN_INTEGER_LITERAL && type1 == FLOAT || type0 == FLOAT && type1 == UNKNOWN_INTEGER_LITERAL) return FLOAT
            if(type0 == UNKNOWN_INTEGER_LITERAL && type1 == DOUBLE || type0 == DOUBLE && type1 == UNKNOWN_INTEGER_LITERAL) return DOUBLE
            if(type0 == UNKNOWN_INTEGER_LITERAL && type1 == CHAR || type0 == CHAR && type1 == UNKNOWN_INTEGER_LITERAL) return SHORT
            if(type0 == UNKNOWN_INTEGER_LITERAL && type1 == UNKNOWN_INTEGER_LITERAL) return UNKNOWN_INTEGER_LITERAL
            if(type0 == UNKNOWN_INTEGER_LITERAL && type1 == UNKNOWN_DOUBLE_LITERAL || type0 == UNKNOWN_DOUBLE_LITERAL && type1 == UNKNOWN_INTEGER_LITERAL) return DOUBLE

            if(type0 == UNKNOWN_DOUBLE_LITERAL && type1 == BYTE || type0 == BYTE && type1 == UNKNOWN_DOUBLE_LITERAL) return FLOAT
            if(type0 == UNKNOWN_DOUBLE_LITERAL && type1 == SHORT || type0 == SHORT && type1 == UNKNOWN_DOUBLE_LITERAL) return FLOAT
            if(type0 == UNKNOWN_DOUBLE_LITERAL && type1 == INT || type0 == INT && type1 == UNKNOWN_DOUBLE_LITERAL) return FLOAT
            if(type0 == UNKNOWN_DOUBLE_LITERAL && type1 == LONG || type0 == LONG && type1 == UNKNOWN_DOUBLE_LITERAL) return FLOAT
            if(type0 == UNKNOWN_DOUBLE_LITERAL && type1 == FLOAT || type0 == FLOAT && type1 == UNKNOWN_DOUBLE_LITERAL) return FLOAT
            if(type0 == UNKNOWN_DOUBLE_LITERAL && type1 == DOUBLE || type0 == DOUBLE && type1 == UNKNOWN_DOUBLE_LITERAL) return DOUBLE
            if(type0 == UNKNOWN_DOUBLE_LITERAL && type1 == CHAR || type0 == CHAR && type1 == UNKNOWN_DOUBLE_LITERAL) return FLOAT
            if(type0 == UNKNOWN_DOUBLE_LITERAL && type1 == UNKNOWN_DOUBLE_LITERAL) return UNKNOWN_DOUBLE_LITERAL

            return null
        }

        fun valueType(value: ShasPValuedNode): ShasPType? {
            return when(value) {
                is ShasPAdd -> valueType(value)
                is ShasPSub -> valueType(value)
                is ShasPMul -> valueType(value)
                is ShasPDiv -> valueType(value)
                is ShasPMod -> valueType(value)
                is ShasPIntegerLiteral -> UNKNOWN_INTEGER_LITERAL
                is ShasPDoubleLiteral -> UNKNOWN_DOUBLE_LITERAL
                is ShasPIdentifier -> valueType(value)
                is ShasPVariableAssignment -> valueType(value)
                else -> TODO(value::class.simpleName?:"null")
            }
        }

        fun valueType(value: ShasPAdd): ShasPType? {
            val type0 = valueType(value.left) ?: return null
            val type1 = valueType(value.right) ?: return null
            return findType(type0, type1)
        }

        fun valueType(value: ShasPSub): ShasPType? {
            val type0 = valueType(value.left) ?: return null
            val type1 = valueType(value.right) ?: return null
            return findType(type0, type1)
        }

        fun valueType(value: ShasPMul): ShasPType? {
            val type0 = valueType(value.left) ?: return null
            val type1 = valueType(value.right) ?: return null
            return findType(type0, type1)
        }

        fun valueType(value: ShasPDiv): ShasPType? {
            val type0 = valueType(value.left) ?: return null
            val type1 = valueType(value.right) ?: return null
            return findType(type0, type1)
        }

        fun valueType(value: ShasPMod): ShasPType? {
            val type0 = valueType(value.left) ?: return null
            val type1 = valueType(value.right) ?: return null
            return findType(type0, type1)
        }

        fun valueType(value: ShasPVariableAssignment): ShasPType? {
            val local = localTable[value.name]
            if(local != null) return local.first
            return null
        }

        fun valueType(value: ShasPIdentifier): ShasPType? {
            val local = localTable[value.name]
            if(local != null) return local.first
            return null
        }

        fun SimpleShasambly.generate(value: ShasPValuedNode, expectedType: ShasPType) {
            when(value) {
                is ShasPAdd -> generate(value, expectedType)
                is ShasPSub -> generate(value, expectedType)
                is ShasPMul -> generate(value, expectedType)
                is ShasPDiv -> generate(value, expectedType)
                is ShasPMod -> generate(value, expectedType)
                is ShasPIntegerLiteral -> generate(value, expectedType)
                is ShasPDoubleLiteral -> generate(value, expectedType)
                is ShasPIdentifier -> generate(value, expectedType)
                is ShasPVariableAssignment -> generate(value, expectedType)
                else -> TODO(value::class.simpleName?:"null")
            }
        }

        fun SimpleShasambly.convert(from: ShasPType, to: ShasPType) {

            when(from) {
                BYTE -> {
                    when(to) {
                        SHORT -> cast(PrimitiveIds.PRIMITIVE_BYTE, PrimitiveIds.PRIMITIVE_SHORT)
                        INT -> cast(PrimitiveIds.PRIMITIVE_BYTE, PrimitiveIds.PRIMITIVE_INT)
                        LONG -> cast(PrimitiveIds.PRIMITIVE_BYTE, PrimitiveIds.PRIMITIVE_LONG)
                        FLOAT -> cast(PrimitiveIds.PRIMITIVE_BYTE, PrimitiveIds.PRIMITIVE_FLOAT)
                        DOUBLE -> cast(PrimitiveIds.PRIMITIVE_BYTE, PrimitiveIds.PRIMITIVE_DOUBLE)
                        else -> TODO(to::class.simpleName?:"null")
                    }
                }
                SHORT -> {
                    when(to) {
                        INT -> cast(PrimitiveIds.PRIMITIVE_SHORT, PrimitiveIds.PRIMITIVE_INT)
                        LONG -> cast(PrimitiveIds.PRIMITIVE_SHORT, PrimitiveIds.PRIMITIVE_LONG)
                        FLOAT -> cast(PrimitiveIds.PRIMITIVE_SHORT, PrimitiveIds.PRIMITIVE_FLOAT)
                        DOUBLE -> cast(PrimitiveIds.PRIMITIVE_SHORT, PrimitiveIds.PRIMITIVE_DOUBLE)
                        else -> TODO(to::class.simpleName?:"null")
                    }
                }
                INT -> {
                    when(to) {
                        LONG -> cast(PrimitiveIds.PRIMITIVE_INT, PrimitiveIds.PRIMITIVE_LONG)
                        FLOAT -> cast(PrimitiveIds.PRIMITIVE_INT, PrimitiveIds.PRIMITIVE_FLOAT)
                        DOUBLE -> cast(PrimitiveIds.PRIMITIVE_INT, PrimitiveIds.PRIMITIVE_DOUBLE)
                        else -> TODO(to::class.simpleName?:"null")
                    }
                }
                LONG -> {
                    when(to) {
                        FLOAT -> cast(PrimitiveIds.PRIMITIVE_LONG, PrimitiveIds.PRIMITIVE_FLOAT)
                        DOUBLE -> cast(PrimitiveIds.PRIMITIVE_LONG, PrimitiveIds.PRIMITIVE_DOUBLE)
                        else -> TODO(to::class.simpleName?:"null")
                    }
                }
                FLOAT -> {
                    when(to) {
                        DOUBLE -> cast(PrimitiveIds.PRIMITIVE_FLOAT, PrimitiveIds.PRIMITIVE_DOUBLE)
                        else -> TODO(to::class.simpleName?:"null")
                    }
                }
                DOUBLE -> {
                    TODO(to::class.simpleName?:"null")
                }
                else -> TODO(from::class.simpleName?:"null")
            }
        }

        fun SimpleShasambly.generate(value: ShasPAdd, expectedType: ShasPType) {
            generate(value.left, expectedType)
            generate(value.right, expectedType)
            when(expectedType) {
                BYTE -> badd()
                SHORT -> sadd()
                INT -> iadd()
                LONG -> ladd()
                FLOAT -> fadd()
                DOUBLE -> dadd()
                else -> throw Error("Can't perform additions with value type $expectedType.")
            }
        }

        fun SimpleShasambly.generate(value: ShasPSub, expectedType: ShasPType) {
            generate(value.left, expectedType)
            generate(value.right, expectedType)
            when(expectedType) {
                BYTE -> bsub()
                SHORT -> ssub()
                INT -> isub()
                LONG -> lsub()
                FLOAT -> fsub()
                DOUBLE -> dsub()
                else -> throw Error("Can't perform subtractions with value type $expectedType.")
            }
        }

        fun SimpleShasambly.generate(value: ShasPMul, expectedType: ShasPType) {
            generate(value.left, expectedType)
            generate(value.right, expectedType)
            when(expectedType) {
                BYTE -> bmul()
                SHORT -> smul()
                INT -> imul()
                LONG -> lmul()
                FLOAT -> fmul()
                DOUBLE -> dmul()
                else -> throw Error("Can't perform multiplications with value type $expectedType.")
            }
        }

        fun SimpleShasambly.generate(it: ShasPIntegerLiteral, type: ShasPType) {
            when(type) {
                BYTE -> bpush(it.value.toByte())
                SHORT -> spush(it.value.toShort())
                INT -> ipush(it.value.toInt())
                LONG -> lpush(it.value.toLong())
                FLOAT -> fpush(it.value.toFloat())
                DOUBLE -> dpush(it.value.toDouble())
                else -> throw Error("Can't generate integer literal for value type $type.")
            }
        }

        fun SimpleShasambly.generate(it: ShasPDoubleLiteral, type: ShasPType) {
            when(type) {
                FLOAT -> fpush(it.value.toFloat())
                DOUBLE -> dpush(it.value.toDouble())
                else -> throw Error("Can't generate float literal for value type $type.")
            }
        }

        fun SimpleShasambly.setLocal(addr: Int, type: ShasPType, value: ShasPValuedNode) {
            generate(value, type)
            when(type) {
                BYTE, BOOLEAN -> b_store_local(addr)
                SHORT, CHAR -> s_store_local(addr)
                INT, FLOAT -> i_store_local(addr)
                LONG, DOUBLE -> l_store_local(addr)
            }
        }

        fun SimpleShasambly.getLocal(addr: Int, type: ShasPType) {
            when(type) {
                BYTE, BOOLEAN -> b_get_local(addr)
                SHORT, CHAR -> s_get_local(addr)
                INT, FLOAT -> i_get_local(addr)
                LONG, DOUBLE -> l_get_local(addr)
            }
        }

        fun SimpleShasambly.generate(it: ShasPVariableDeclaration) {
            val addr = lastLocalPointer
            val type = it.type
            val name = it.name
            if(localTable.containsKey(name)) throw Error("Variable $it is already defined in this scope")
            localTable[name] = type to addr
            lastLocalPointer += type.byteSize
            if(it.value != null) setLocal(addr, type, it.value)
        }

        fun SimpleShasambly.generate(it: ShasPVariableAssignment) {
            val addr = localTable[it.name] ?: throw Error("Variable $it is not defined in this scope")
            val type = addr.first
            setLocal(addr.second, type, it.value)
        }

        fun SimpleShasambly.generateValued(it: ShasPVariableAssignment) {
            val addr = localTable[it.name] ?: throw Error("Variable $it is not defined in this scope")
            val type = addr.first
            setLocal(addr.second, type, it.value)
            getLocal(addr.second, type)
        }

        fun SimpleShasambly.generate(it: ShasPIf) {
            if(it.orElse == null) ifElse({ generate(it.condition, BOOLEAN) }) { generate(it.then) }
            else ifElse({ generate(it.condition, BOOLEAN) }, orElse = { generate(it.orElse) }) { generate(it.then) }
        }

        fun SimpleShasambly.generate(it: ShasPWhile) {
            whileLoop({ generate(it.condition, BOOLEAN) }) {
                generate(it.body)
            }
        }

        fun SimpleShasambly.generate(it: ShasPDoWhile) {
            doWhileLoop({ generate(it.condition, BOOLEAN) }) {
                generate(it.body)
            }
        }

        fun SimpleShasambly.generate(it: ShasPFor) {
            forLoop({ generate(it.init) }, { generate(it.condition, BOOLEAN) }, { generate(it.step) }) {
                generate(it.body)
            }
        }

    }

}
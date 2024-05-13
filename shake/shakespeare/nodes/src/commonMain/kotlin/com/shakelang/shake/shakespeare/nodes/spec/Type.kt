@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.nodes.spec

import com.shakelang.shake.parser.node.misc.ShakeVariableType
import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.shake.shakespeare.spec.ObjectTypeSpec
import com.shakelang.shake.shakespeare.spec.PrimitiveTypeSpec
import com.shakelang.shake.shakespeare.spec.TypeSpec

interface TypeNodeSpec : AbstractNodeSpec, TypeSpec {
    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeVariableType

    companion object {
        fun of(type: TypeSpec): TypeNodeSpec {
            return when (type) {
                is ObjectTypeSpec -> ObjectTypeNodeSpec.of(type)
                is PrimitiveTypeSpec -> PrimitiveTypeNodeSpec.of(type)
                else -> throw IllegalArgumentException("Unknown type: $type")
            }
        }

        fun byte() = PrimitiveTypeNodeSpec.BYTE
        fun short() = PrimitiveTypeNodeSpec.SHORT
        fun int() = PrimitiveTypeNodeSpec.INT
        fun long() = PrimitiveTypeNodeSpec.LONG
        fun unsignedByte() = PrimitiveTypeNodeSpec.UNSIGNED_BYTE
        fun unsignedShort() = PrimitiveTypeNodeSpec.UNSIGNED_SHORT
        fun unsignedInt() = PrimitiveTypeNodeSpec.UNSIGNED_INT
        fun unsignedLong() = PrimitiveTypeNodeSpec.UNSIGNED_LONG
        fun ubyte() = PrimitiveTypeNodeSpec.UNSIGNED_BYTE
        fun ushort() = PrimitiveTypeNodeSpec.UNSIGNED_SHORT
        fun uint() = PrimitiveTypeNodeSpec.UNSIGNED_INT
        fun ulong() = PrimitiveTypeNodeSpec.UNSIGNED_LONG
        fun float() = PrimitiveTypeNodeSpec.FLOAT
        fun double() = PrimitiveTypeNodeSpec.DOUBLE
        fun char() = PrimitiveTypeNodeSpec.CHAR
        fun boolean() = PrimitiveTypeNodeSpec.BOOLEAN
        fun string() = ObjectTypeNodeSpec(NamespaceNodeSpec("String"))
    }
}

class ObjectTypeNodeSpec(namespace: NamespaceNodeSpec) : ObjectTypeSpec(namespace), TypeNodeSpec {
    override val namespace get() = super.namespace as NamespaceNodeSpec
    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeVariableType {
        return ShakeVariableType(namespace.dump(ctx, nctx))
    }

    companion object {
        fun of(type: ObjectTypeSpec): ObjectTypeNodeSpec {
            return ObjectTypeNodeSpec(NamespaceNodeSpec.of(type.namespace))
        }
    }
}

enum class PrimitiveTypeNodeSpec(val type: String) : TypeNodeSpec {
    BYTE("byte"),
    SHORT("short"),
    INT("int"),
    LONG("long"),
    UNSIGNED_BYTE("ubyte"),
    UNSIGNED_SHORT("ushort"),
    UNSIGNED_INT("uint"),
    UNSIGNED_LONG("ulong"),
    FLOAT("float"),
    DOUBLE("double"),
    CHAR("char"),
    BOOLEAN("boolean"),
    VOID("void"),
    ;

    val namespace = NamespaceNodeSpec(type)

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeVariableType {
        return ShakeVariableType(namespace.dump(ctx, nctx))
    }

    override fun generate(ctx: GenerationContext): String {
        return type
    }

    companion object {
        fun of(type: PrimitiveTypeSpec): PrimitiveTypeNodeSpec {
            return when (type) {
                PrimitiveTypeSpec.BYTE -> BYTE
                PrimitiveTypeSpec.SHORT -> SHORT
                PrimitiveTypeSpec.INT -> INT
                PrimitiveTypeSpec.LONG -> LONG
                PrimitiveTypeSpec.UNSIGNED_BYTE -> UNSIGNED_BYTE
                PrimitiveTypeSpec.UNSIGNED_SHORT -> UNSIGNED_SHORT
                PrimitiveTypeSpec.UNSIGNED_INT -> UNSIGNED_INT
                PrimitiveTypeSpec.UNSIGNED_LONG -> UNSIGNED_LONG
                PrimitiveTypeSpec.FLOAT -> FLOAT
                PrimitiveTypeSpec.DOUBLE -> DOUBLE
                PrimitiveTypeSpec.CHAR -> CHAR
                PrimitiveTypeSpec.BOOLEAN -> BOOLEAN
                PrimitiveTypeSpec.VOID -> VOID
            }
        }
    }
}

fun PrimitiveTypeSpec.toNodeSpec(): PrimitiveTypeNodeSpec = PrimitiveTypeNodeSpec.of(this)
fun ObjectTypeSpec.toNodeSpec(): ObjectTypeNodeSpec = ObjectTypeNodeSpec.of(this)
fun TypeSpec.toNodeSpec(): TypeNodeSpec = TypeNodeSpec.of(this)

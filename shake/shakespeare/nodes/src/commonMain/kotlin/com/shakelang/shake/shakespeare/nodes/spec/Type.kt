@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.nodes.spec

import com.shakelang.shake.parser.node.misc.ShakeVariableType
import com.shakelang.shake.shakespeare.spec.*

interface TypeNodeSpec : AbstractNodeSpec, TypeSpec {
    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeVariableType
}

class ObjectTypeNodeSpec(namespace: NamespaceNodeSpec) : ObjectType(namespace), TypeNodeSpec {
    override val namespace get() = super.namespace as NamespaceNodeSpec
    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeVariableType {
        return ShakeVariableType(namespace.dump(ctx, nctx))
    }
}

enum class PrimitiveType(val type: String) : TypeNodeSpec {
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
    ;

    val namespace = NamespaceNodeSpec(type)

    override fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeVariableType {
        return ShakeVariableType(namespace.dump(ctx, nctx))
    }

    override fun generate(ctx: GenerationContext): String {
        return type
    }
}

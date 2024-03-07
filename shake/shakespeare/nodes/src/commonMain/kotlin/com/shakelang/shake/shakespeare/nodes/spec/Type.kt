@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.nodes.spec

import com.shakelang.shake.shakespeare.spec.ClassType
import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.shake.shakespeare.spec.SimpleType
import com.shakelang.shake.shakespeare.spec.Type

interface TypeNode : AbstractNodeSpec, Type

class SimpleTypeNode(name: String) : SimpleType(name), TypeNode

enum class PrimitiveType(val type: String) : TypeNode {
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

    override fun generate(ctx: GenerationContext): String {
        return type
    }
}

class ClassTypeNode(name: String) : ClassType(name), TypeNode {
    override fun generate(ctx: GenerationContext) = name
}

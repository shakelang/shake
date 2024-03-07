@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.nodes.spec.code

import com.shakelang.shake.shakespeare.nodes.spec.AbstractNodeSpec
import com.shakelang.shake.shakespeare.nodes.spec.TypeNode
import com.shakelang.shake.shakespeare.spec.Identifier
import com.shakelang.shake.shakespeare.spec.code.*

/**
 * A [StatementSpec] is a specification for a statement in the code
 * @since 0.1.0
 */
interface StatementNodeSpec : AbstractNodeSpec, StatementSpec

/**
 * A [VariableDeclarationSpec] is a specification for a variable declaration in the code
 * @param name The name of the variable
 * @param type The type of the variable
 * @param value The value of the variable
 * @since 0.1.0
 */
open class VariableDeclarationNodeSpec(
    name: Identifier,
    type: TypeNode,
    value: ValueNodeSpec?,
    isVal: Boolean = true,
) : VariableDeclarationSpec(name, type, value, isVal), StatementNodeSpec
open class WhileNodeSpec(
    condition: ValueNodeSpec,
    body: CodeNodeSpec,
) : WhileSpec(condition, body), StatementNodeSpec

open class DoWhileNodeSpec(
    body: CodeNodeSpec,
    condition: ValueNodeSpec,
) : DoWhileSpec(body, condition), StatementNodeSpec

open class ForNodeSpec(
    init: StatementNodeSpec,
    condition: ValueNodeSpec,
    update: StatementNodeSpec,
    body: CodeNodeSpec,
) : ForSpec(init, condition, update, body), StatementNodeSpec

open class IfNodeSpec(
    condition: ValueNodeSpec,
    body: CodeNodeSpec,
    elseBody: CodeNodeSpec?,
) : IfSpec(condition, body, elseBody), StatementNodeSpec

open class ReturnNodeSpec(
    value: ValueNodeSpec,
) : ReturnSpec(value), StatementNodeSpec

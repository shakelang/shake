@file:Suppress(
    "FunctionName",
    "MemberVisibilityCanBePrivate",
    "ktlint:standard:function-naming",
    "ktlint:standard:property-naming",
    "unused",
)

package com.shakelang.shake.shakespeare.nodes.spec.code

import com.shakelang.shake.shakespeare.nodes.spec.AbstractNodeSpec
import com.shakelang.shake.shakespeare.spec.code.CodeSpec

open class CodeNodeSpec(
    statements: List<StatementNodeSpec>,
) : CodeSpec(statements), AbstractNodeSpec {
    override val statements = statements as List<StatementNodeSpec>
}

package com.shakelang.shake.shakespeare.nodes.spec

import com.shakelang.shake.parser.node.ShakeNode
import com.shakelang.shake.shakespeare.AbstractSpec
import com.shakelang.shake.shakespeare.spec.GenerationContext

interface AbstractNodeSpec : AbstractSpec {
    fun dump(ctx: GenerationContext, nctx: NodeContext): ShakeNode
}

package com.shakelang.shake.shakespeare.nodes.spec

import com.shakelang.shake.parser.node.ShakeNode
import com.shakelang.shake.shakespeare.AbstractSpec
import com.shakelang.shake.shakespeare.spec.GenerationContext
import com.shakelang.util.io.streaming.output.bytes.CountingOutputStream

interface AbstractNodeSpec : AbstractSpec {
    fun dump(ctx: GenerationContext, os: CountingOutputStream, nctx: NodeContext): ShakeNode
}

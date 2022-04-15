package io.github.shakelang.shake.processor.program

import io.github.shakelang.shake.parser.node.ShakeTree

open class ShakeFile (
    open val name: String,
    open val contents: ShakeTree,
)
@file:Suppress("unused")

package com.shakelang.util.parseutils

open class LoopController {
    var isBreak = false

    @Suppress("FunctionName", "ktlint:standard:function-naming")
    fun Break() {
        isBreak = true
    }
}

open class IndexedLoopController(
    var index: Int,
) : LoopController()

open class ElementLoopController<E>(
    var element: E,
) : LoopController()

open class IndexedElementLoopController<E>(
    var element: E,
    var index: Int,
) : LoopController()

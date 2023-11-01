@file:Suppress("unused")
package io.github.shakelang.shake.util.parseutils

open class LoopController {
    var isBreak = false

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
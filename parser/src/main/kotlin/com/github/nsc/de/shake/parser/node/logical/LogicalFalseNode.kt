package com.github.nsc.de.shake.parser.node.logical

import com.github.nsc.de.shake.util.characterinput.position.PositionMap

class LogicalFalseNode(map: PositionMap) : LogicalNode(map) {
    override val operator: String?
        get() = null

    override fun toString(): String {
        return "false"
    }
}
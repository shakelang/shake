package io.github.shakelang.shake.parser.node

import io.github.shakelang.parseutils.characters.position.PositionMap
import io.github.shakelang.shason.JSON

interface ShakeNode {

    val json: Map<String, *>
        get() = toJson()
    fun toJson(): Map<String, *>
}

abstract class ShakeNodeImpl protected constructor(val map: PositionMap) : ShakeNode {
    override fun toString(): String = JSON.stringify(this.json)
}

interface ShakeStatementNode : ShakeNode
interface ShakeValuedNode : ShakeNode
interface ShakeValuedStatementNode : ShakeStatementNode, ShakeValuedNode
abstract class ShakeStatementNodeImpl(map: PositionMap) : ShakeNodeImpl(map), ShakeStatementNode
abstract class ShakeValuedNodeImpl(map: PositionMap) : ShakeNodeImpl(map), ShakeValuedNode
abstract class ShakeValuedStatementNodeImpl(map: PositionMap) : ShakeStatementNodeImpl(map), ShakeValuedStatementNode
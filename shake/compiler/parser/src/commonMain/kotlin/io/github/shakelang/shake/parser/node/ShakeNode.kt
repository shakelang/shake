package io.github.shakelang.shake.parser.node

import io.github.shakelang.shake.util.parseutils.characters.position.PositionMap

interface ShakeNode {

    val json: Map<String, *>
        get() = toJson()

    fun toJson(): Map<String, *>
}

abstract class ShakeNodeImpl protected constructor(val map: PositionMap) : ShakeNode {
    override fun toString(): String = io.github.shakelang.shake.util.shason.JSON.stringify(this.json)
}

interface ShakeStatementNode : ShakeNode
interface ShakeValuedNode : ShakeNode
interface ShakeValuedStatementNode : ShakeStatementNode, ShakeValuedNode
interface ShakeFileChildNode : ShakeNode
interface ShakeDeclaration : ShakeStatementNode, ShakeFileChildNode
abstract class ShakeStatementNodeImpl(map: PositionMap) : ShakeNodeImpl(map), ShakeStatementNode
abstract class ShakeValuedNodeImpl(map: PositionMap) : ShakeNodeImpl(map), ShakeValuedNode
abstract class ShakeValuedStatementNodeImpl(map: PositionMap) : ShakeStatementNodeImpl(map), ShakeValuedStatementNode

abstract class ShakeFileChildNodeImpl(map: PositionMap) : ShakeNodeImpl(map), ShakeFileChildNode

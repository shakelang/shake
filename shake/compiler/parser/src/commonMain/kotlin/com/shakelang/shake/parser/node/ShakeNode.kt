package com.shakelang.shake.parser.node

import com.shakelang.util.parseutils.characters.position.PositionMap

interface ShakeNode {

    val json: Map<String, *>
        get() = toJson()

    val nodeName get() = this::class.simpleName!!

    fun toJson(): Map<String, *> = mapOf("name" to nodeName)
    override fun equals(other: Any?): Boolean
    fun equalsIgnorePosition(other: Any?): Boolean
}

abstract class ShakeNodeImpl protected constructor(val map: PositionMap) : ShakeNode {
    override fun toString(): String = com.shakelang.shake.util.shason.JSON.stringify(this.json)
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

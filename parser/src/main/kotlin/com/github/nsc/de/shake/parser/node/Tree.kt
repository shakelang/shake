package com.github.nsc.de.shake.parser.node

import com.github.nsc.de.shake.util.characterinput.position.PositionMap

class Tree(map: PositionMap, val children: Array<Node>) : Node(map) {

    constructor(map: PositionMap, children: List<Node>) : this(map, children.toTypedArray())

    override fun toString(): String = "Tree{children=${ children.contentToString() }}"
}
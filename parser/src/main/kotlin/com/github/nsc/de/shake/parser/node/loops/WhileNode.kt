package com.github.nsc.de.shake.parser.node.loops

import com.github.nsc.de.shake.parser.node.Node
import com.github.nsc.de.shake.parser.node.Tree
import com.github.nsc.de.shake.parser.node.ValuedNode
import com.github.nsc.de.shake.util.characterinput.position.PositionMap

class WhileNode(map: PositionMap, val body: Tree, val condition: ValuedNode) : Node(map) {
    override fun toString(): String = "WhileNode{body=$body, condition=$condition}"
}
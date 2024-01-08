package com.shakelang.shake.parser.nodes.expression

import com.shakelang.shake.parser.node.ShakeValuedNodeImpl
import com.shakelang.shake.parser.node.expression.ShakeBitwiseAndNode
import com.shakelang.shake.parser.node.expression.ShakeBitwiseNotNode
import com.shakelang.shake.parser.node.expression.ShakeBitwiseOrNode
import com.shakelang.shake.parser.node.expression.ShakeBitwiseXOrNode
import com.shakelang.util.parseutils.characters.position.PositionMap
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class BitwiseTest : FreeSpec({

    class DummyValuedNode(
        val uid: Int = 0,
    ) : ShakeValuedNodeImpl(PositionMap.empty()) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is DummyValuedNode) return false

            if (uid != other.uid) return false

            return true
        }

        override fun equalsIgnorePosition(other: Any?): Boolean {
            if (this === other) return true
            if (other !is DummyValuedNode) return false
            if (uid != other.uid) return false
            return true
        }

        override fun hashCode(): Int {
            return uid
        }
    }

    // ShakeBitwiseAndNode
    "${ShakeBitwiseAndNode::class.simpleName} - Should return the correct operator" {
        val map = PositionMap.empty()

        val dummy1 = DummyValuedNode(1)
        val dummy2 = DummyValuedNode(2)

        val node = ShakeBitwiseAndNode(map, dummy1, dummy2, 0)

        node.operator shouldBe "&"
        node.left shouldBe dummy1
        node.right shouldBe dummy2
        node.operatorPosition shouldBe 0
    }

    "${ShakeBitwiseAndNode::class.simpleName} - Should be equal to itself" {
        val map = PositionMap.empty()

        val dummy1 = DummyValuedNode(1)
        val dummy2 = DummyValuedNode(2)

        val node = ShakeBitwiseAndNode(map, dummy1, dummy2, 0)

        node shouldBe node
    }

    "${ShakeBitwiseAndNode::class.simpleName} - Should be equal to another node with the same value" {
        val map = PositionMap.empty()

        val dummy1 = DummyValuedNode(1)
        val dummy2 = DummyValuedNode(2)

        val node1 = ShakeBitwiseAndNode(map, dummy1, dummy2, 0)
        val node2 = ShakeBitwiseAndNode(map, dummy1, dummy2, 0)

        node1 shouldBe node2
    }

    "${ShakeBitwiseAndNode::class.simpleName} - Should not be equal to another node with a different value" {
        val map = PositionMap.empty()

        val dummy1 = DummyValuedNode(1)
        val dummy2 = DummyValuedNode(2)
        val dummy3 = DummyValuedNode(3)

        val node1 = ShakeBitwiseAndNode(map, dummy1, dummy2, 0)
        val node2 = ShakeBitwiseAndNode(map, dummy1, dummy3, 0)

        node1 shouldNotBe node2
    }

    "${ShakeBitwiseAndNode::class.simpleName} - Should be equal to itself (ignoring position)" {
        val map = PositionMap.empty()

        val dummy1 = DummyValuedNode(1)
        val dummy2 = DummyValuedNode(2)
        val dummy3 = DummyValuedNode(3)

        val node1 = ShakeBitwiseAndNode(map, dummy1, dummy2, 0)

        node1.equalsIgnorePosition(node1) shouldBe true
    }

    "${ShakeBitwiseAndNode::class.simpleName} - Should be equal to another node with the same value (ignoring position)" {
        val map = PositionMap.empty()

        val dummy1 = DummyValuedNode(1)
        val dummy2 = DummyValuedNode(2)

        val node1 = ShakeBitwiseAndNode(map, dummy1, dummy2, 0)
        val node2 = ShakeBitwiseAndNode(map, dummy1, dummy2, 0)

        node1.equalsIgnorePosition(node2) shouldBe true
    }

    "${ShakeBitwiseAndNode::class.simpleName} - Should not be equal to another node with a different value (ignoring position)" {
        val map = PositionMap.empty()

        val dummy1 = DummyValuedNode(1)
        val dummy2 = DummyValuedNode(2)
        val dummy3 = DummyValuedNode(3)

        val node1 = ShakeBitwiseAndNode(map, dummy1, dummy2, 0)
        val node2 = ShakeBitwiseAndNode(map, dummy1, dummy3, 0)

        node1.equalsIgnorePosition(node2) shouldBe false
    }

    // ShakeBitwiseOrNode
    "${ShakeBitwiseOrNode::class.simpleName} - Should return the correct operator" {
        val map = PositionMap.empty()

        val dummy1 = DummyValuedNode(1)
        val dummy2 = DummyValuedNode(2)

        val node = ShakeBitwiseOrNode(map, dummy1, dummy2, 0)

        node.operator shouldBe "|"
        node.left shouldBe dummy1
        node.right shouldBe dummy2
        node.operatorPosition shouldBe 0
    }

    "${ShakeBitwiseOrNode::class.simpleName} - Should be equal to another itself" {
        val map = PositionMap.empty()

        val dummy1 = DummyValuedNode(1)
        val dummy2 = DummyValuedNode(2)

        val node = ShakeBitwiseOrNode(map, dummy1, dummy2, 0)

        node shouldBe node
    }

    "${ShakeBitwiseOrNode::class.simpleName} - Should be equal to another node with the same value" {
        val map = PositionMap.empty()

        val dummy1 = DummyValuedNode(1)
        val dummy2 = DummyValuedNode(2)

        val node1 = ShakeBitwiseOrNode(map, dummy1, dummy2, 0)
        val node2 = ShakeBitwiseOrNode(map, dummy1, dummy2, 0)

        node1 shouldBe node2
    }

    "${ShakeBitwiseOrNode::class.simpleName} - Should not be equal to another node with a different value" {
        val map = PositionMap.empty()

        val dummy1 = DummyValuedNode(1)
        val dummy2 = DummyValuedNode(2)
        val dummy3 = DummyValuedNode(3)

        val node1 = ShakeBitwiseOrNode(map, dummy1, dummy2, 0)
        val node2 = ShakeBitwiseOrNode(map, dummy1, dummy3, 0)

        node1 shouldNotBe node2
    }

    "${ShakeBitwiseOrNode::class.simpleName} - Should be equal to itself (ignoring position)" {
        val map = PositionMap.empty()

        val dummy1 = DummyValuedNode(1)
        val dummy2 = DummyValuedNode(2)
        val dummy3 = DummyValuedNode(3)

        val node1 = ShakeBitwiseOrNode(map, dummy1, dummy2, 0)

        node1.equalsIgnorePosition(node1) shouldBe true
    }

    "${ShakeBitwiseOrNode::class.simpleName} - Should be equal to another node with the same value (ignoring position)" {
        val map = PositionMap.empty()

        val dummy1 = DummyValuedNode(1)
        val dummy2 = DummyValuedNode(2)

        val node1 = ShakeBitwiseOrNode(map, dummy1, dummy2, 0)
        val node2 = ShakeBitwiseOrNode(map, dummy1, dummy2, 0)

        node1.equalsIgnorePosition(node2) shouldBe true
    }

    "${ShakeBitwiseOrNode::class.simpleName} - Should not be equal to another node with a different value (ignoring position)" {
        val map = PositionMap.empty()

        val dummy1 = DummyValuedNode(1)
        val dummy2 = DummyValuedNode(2)
        val dummy3 = DummyValuedNode(3)

        val node1 = ShakeBitwiseOrNode(map, dummy1, dummy2, 0)
        val node2 = ShakeBitwiseOrNode(map, dummy1, dummy3, 0)

        node1.equalsIgnorePosition(node2) shouldBe false
    }

    // ShakeBitwiseXorNode
    "${ShakeBitwiseXOrNode::class.simpleName} - Should return the correct operator" {
        val map = PositionMap.empty()

        val dummy1 = DummyValuedNode(1)
        val dummy2 = DummyValuedNode(2)

        val node = ShakeBitwiseXOrNode(map, dummy1, dummy2, 0)

        node.operator shouldBe "^"
        node.left shouldBe dummy1
        node.right shouldBe dummy2
        node.operatorPosition shouldBe 0
    }

    "${ShakeBitwiseXOrNode::class.simpleName} - Should be equal to another itself" {
        val map = PositionMap.empty()

        val dummy1 = DummyValuedNode(1)
        val dummy2 = DummyValuedNode(2)

        val node = ShakeBitwiseXOrNode(map, dummy1, dummy2, 0)

        node shouldBe node
    }

    "${ShakeBitwiseXOrNode::class.simpleName} - Should be equal to another node with the same value" {
        val map = PositionMap.empty()

        val dummy1 = DummyValuedNode(1)
        val dummy2 = DummyValuedNode(2)

        val node1 = ShakeBitwiseXOrNode(map, dummy1, dummy2, 0)
        val node2 = ShakeBitwiseXOrNode(map, dummy1, dummy2, 0)

        node1 shouldBe node2
    }

    "${ShakeBitwiseXOrNode::class.simpleName} - Should not be equal to another node with a different value" {
        val map = PositionMap.empty()

        val dummy1 = DummyValuedNode(1)
        val dummy2 = DummyValuedNode(2)
        val dummy3 = DummyValuedNode(3)

        val node1 = ShakeBitwiseXOrNode(map, dummy1, dummy2, 0)
        val node2 = ShakeBitwiseXOrNode(map, dummy1, dummy3, 0)

        node1 shouldNotBe node2
    }

    "${ShakeBitwiseXOrNode::class.simpleName} - Should be equal to itself (ignoring position)" {
        val map = PositionMap.empty()

        val dummy1 = DummyValuedNode(1)
        val dummy2 = DummyValuedNode(2)
        val dummy3 = DummyValuedNode(3)

        val node1 = ShakeBitwiseXOrNode(map, dummy1, dummy2, 0)

        node1.equalsIgnorePosition(node1) shouldBe true
    }

    "${ShakeBitwiseXOrNode::class.simpleName} - Should be equal to another node with the same value (ignoring position)" {
        val map = PositionMap.empty()

        val dummy1 = DummyValuedNode(1)
        val dummy2 = DummyValuedNode(2)

        val node1 = ShakeBitwiseXOrNode(map, dummy1, dummy2, 0)
        val node2 = ShakeBitwiseXOrNode(map, dummy1, dummy2, 0)

        node1.equalsIgnorePosition(node2) shouldBe true
    }

    "${ShakeBitwiseXOrNode::class.simpleName} - Should not be equal to another node with a different value (ignoring position)" {
        val map = PositionMap.empty()

        val dummy1 = DummyValuedNode(1)
        val dummy2 = DummyValuedNode(2)
        val dummy3 = DummyValuedNode(3)

        val node1 = ShakeBitwiseXOrNode(map, dummy1, dummy2, 0)
        val node2 = ShakeBitwiseXOrNode(map, dummy1, dummy3, 0)

        node1.equalsIgnorePosition(node2) shouldBe false
    }

    // ShakeBitwiseNotNode
    "${ShakeBitwiseNotNode::class.simpleName} - Should return the correct operator" {
        val map = PositionMap.empty()

        val dummy = DummyValuedNode(1)

        val node = ShakeBitwiseNotNode(map, dummy, 0)

        node.operator shouldBe "~"
        node.value shouldBe dummy
        node.operatorPosition shouldBe 0
    }

    "${ShakeBitwiseNotNode::class.simpleName} - Should be equal to another itself" {
        val map = PositionMap.empty()

        val dummy = DummyValuedNode(1)

        val node = ShakeBitwiseNotNode(map, dummy, 0)

        node shouldBe node
    }

    "${ShakeBitwiseNotNode::class.simpleName} - Should be equal to another node with the same value" {
        val map = PositionMap.empty()

        val dummy = DummyValuedNode(1)

        val node1 = ShakeBitwiseNotNode(map, dummy, 0)
        val node2 = ShakeBitwiseNotNode(map, dummy, 0)

        node1 shouldBe node2
    }

    "${ShakeBitwiseNotNode::class.simpleName} - Should not be equal to another node with a different value" {
        val map = PositionMap.empty()

        val dummy1 = DummyValuedNode(1)
        val dummy2 = DummyValuedNode(2)

        val node1 = ShakeBitwiseNotNode(map, dummy1, 0)
        val node2 = ShakeBitwiseNotNode(map, dummy2, 0)

        node1 shouldNotBe node2
    }

    "${ShakeBitwiseNotNode::class.simpleName} - Should be equal to itself (ignoring position)" {
        val map = PositionMap.empty()

        val dummy = DummyValuedNode(1)

        val node1 = ShakeBitwiseNotNode(map, dummy, 0)

        node1.equalsIgnorePosition(node1) shouldBe true
    }

    "${ShakeBitwiseNotNode::class.simpleName} - Should be equal to another node with the same value (ignoring position)" {
        val map = PositionMap.empty()

        val dummy = DummyValuedNode(1)

        val node1 = ShakeBitwiseNotNode(map, dummy, 0)
        val node2 = ShakeBitwiseNotNode(map, dummy, 0)

        node1.equalsIgnorePosition(node2) shouldBe true
    }

    "${ShakeBitwiseNotNode::class.simpleName} - Should not be equal to another node with a different value (ignoring position)" {
        val map = PositionMap.empty()

        val dummy1 = DummyValuedNode(1)
        val dummy2 = DummyValuedNode(2)

        val node1 = ShakeBitwiseNotNode(map, dummy1, 0)
        val node2 = ShakeBitwiseNotNode(map, dummy2, 0)

        node1.equalsIgnorePosition(node2) shouldBe false
    }
})

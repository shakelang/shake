package com.shakelang.shake.util

import com.shakelang.shake.util.pointer.PointingList
import com.shakelang.shake.util.pointer.points
import com.shakelang.shake.util.pointer.values
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class PointerListShortcutTests : FreeSpec({

    "List.points" {

        val list = listOf(1, 2, 3, 4, 5)
        val pointerList = list.points()

        pointerList[0].value shouldBe 1
        pointerList[1].value shouldBe 2
        pointerList[2].value shouldBe 3
        pointerList[3].value shouldBe 4
        pointerList[4].value shouldBe 5

    }

    "PointerList.values" {

        val list = listOf(1, 2, 3, 4, 5)
        val pointerList = list.points()

        val values = pointerList.values()

        values[0] shouldBe 1
        values[1] shouldBe 2
        values[2] shouldBe 3
        values[3] shouldBe 4
        values[4] shouldBe 5

    }
})

class PointingListTests : FreeSpec({

    "pointers" {

        val list = listOf(1, 2, 3, 4, 5)
        val pointerList = list.points()
        val pointingList = PointingList.from(pointerList)

        pointingList.pointers shouldBe pointerList

    }

    "size" {

        val list = listOf(1, 2, 3, 4, 5)
        val pointerList = list.points()
        val pointingList = PointingList.from(pointerList)

        pointingList.size shouldBe 5

    }

    "get" {

        val list = listOf(1, 2, 3, 4, 5)
        val pointerList = list.points()
        val pointingList = PointingList.from(pointerList)

        pointingList[0] shouldBe 1
        pointingList[1] shouldBe 2
        pointingList[2] shouldBe 3
        pointingList[3] shouldBe 4
        pointingList[4] shouldBe 5

    }

    "isEmpty" {

        val list = listOf(1, 2, 3, 4, 5)
        val pointerList = list.points()
        val pointingList = PointingList.from(pointerList)

        pointingList.isEmpty() shouldBe false

        val emptyList = listOf<Int>()
        val emptyPointerList = emptyList.points()
        val emptyPointingList = PointingList.from(emptyPointerList)

        emptyPointingList.isEmpty() shouldBe true

    }

    "isNotEmpty" {

        val list = listOf(1, 2, 3, 4, 5)
        val pointerList = list.points()
        val pointingList = PointingList.from(pointerList)

        pointingList.isNotEmpty() shouldBe true

        val emptyList = listOf<Int>()
        val emptyPointerList = emptyList.points()
        val emptyPointingList = PointingList.from(emptyPointerList)

        emptyPointingList.isNotEmpty() shouldBe false

    }

    "contains" {

        val list = listOf(1, 2, 3, 4, 5)
        val pointerList = list.points()
        val pointingList = PointingList.from(pointerList)

        (1..5).forEach { pointingList.contains(it) shouldBe true }
        (6..10).forEach { pointingList.contains(it) shouldBe false }

    }

    "containsAll" {

        val list = listOf(1, 2, 3, 4, 5)
        val pointerList = list.points()
        val pointingList = PointingList.from(pointerList)

        pointingList.containsAll(list) shouldBe true
        pointingList.containsAll(listOf(1, 2, 3, 4, 5, 6)) shouldBe false

    }

    "iterator" {

        val list = listOf(1, 2, 3, 4, 5)
        val pointerList = list.points()
        val pointingList = PointingList.from(pointerList)

        val iterator = pointingList.iterator()

        (1..5).forEach {
            iterator.hasNext() shouldBe true
            iterator.next() shouldBe it
        }

        iterator.hasNext() shouldBe false

    }

    "listIterator" {

        val list = listOf(1, 2, 3, 4, 5)
        val pointerList = list.points()
        val pointingList = PointingList.from(pointerList)

        val iterator = pointingList.listIterator()

        (1..5).forEach {
            iterator.hasNext() shouldBe true
            iterator.next() shouldBe it
        }

        iterator.hasNext() shouldBe false

    }

    "listIterator(index)" {

        val list = listOf(1, 2, 3, 4, 5)
        val pointerList = list.points()
        val pointingList = PointingList.from(pointerList)

        val iterator = pointingList.listIterator(2)

        (3..5).forEach {
            iterator.hasNext() shouldBe true
            iterator.next() shouldBe it
        }

        iterator.hasNext() shouldBe false

    }

    "subList" {

        val list = listOf(1, 2, 3, 4, 5)
        val pointerList = list.points()
        val pointingList = PointingList.from(pointerList)

        val subList = pointingList.subList(1, 4)

        subList.size shouldBe 3

        subList[0] shouldBe 2
        subList[1] shouldBe 3
        subList[2] shouldBe 4

    }

    "lastIndexOf" {

        val list = listOf(1, 2, 3, 4, 5, 1, 2, 3, 4, 5)
        val pointerList = list.points()
        val pointingList = PointingList.from(pointerList)

        pointingList.lastIndexOf(1) shouldBe 5
        pointingList.lastIndexOf(2) shouldBe 6
        pointingList.lastIndexOf(3) shouldBe 7
        pointingList.lastIndexOf(4) shouldBe 8
        pointingList.lastIndexOf(5) shouldBe 9

    }

    "indexOf" {

        val list = listOf(1, 2, 3, 4, 5, 1, 2, 3, 4, 5)
        val pointerList = list.points()
        val pointingList = PointingList.from(pointerList)

        pointingList.indexOf(1) shouldBe 0
        pointingList.indexOf(2) shouldBe 1
        pointingList.indexOf(3) shouldBe 2
        pointingList.indexOf(4) shouldBe 3
        pointingList.indexOf(5) shouldBe 4

    }

    "first" {

        val list = listOf(1, 2, 3, 4, 5)
        val pointerList = list.points()
        val pointingList = PointingList.from(pointerList)

        pointingList.first() shouldBe 1

    }

    "last" {

        val list = listOf(1, 2, 3, 4, 5)
        val pointerList = list.points()
        val pointingList = PointingList.from(pointerList)

        pointingList.last() shouldBe 5

    }

    "forEach" {

        val list = listOf(1, 2, 3, 4, 5)
        val pointerList = list.points()
        val pointingList = PointingList.from(pointerList)

        var i = 1
        pointingList.forEach {
            it shouldBe i
            i++
        }

    }

    "forEachIndexed" {

        val list = listOf(1, 2, 3, 4, 5)
        val pointerList = list.points()
        val pointingList = PointingList.from(pointerList)

        pointingList.forEachIndexed { index, value ->
            index shouldBe value - 1
        }

    }

    "reduce" {

        val list = listOf(1, 2, 3, 4, 5)
        val pointerList = list.points()
        val pointingList = PointingList.from(pointerList)

        pointingList.reduce { acc, i -> acc + i } shouldBe 15

    }

    "reduceIndexed" {

        val list = listOf(1, 2, 3, 4, 5)
        val pointerList = list.points()
        val pointingList = PointingList.from(pointerList)

        pointingList.reduceIndexed { index, acc, i -> acc + i + index } shouldBe 25

    }

    "reduceRight" {

        val list = listOf(1, 2, 3, 4, 5)
        val pointerList = list.points()
        val pointingList = PointingList.from(pointerList)

        pointingList.reduceRight { acc, i -> acc + i } shouldBe 15

    }

    "reduceRightIndexed" {

        val list = listOf(1, 2, 3, 4, 5)
        val pointerList = list.points()
        val pointingList = PointingList.from(pointerList)

        pointingList.reduceRightIndexed { index, acc, i -> acc + i + index } shouldBe 21

    }

    "fold" {

        val list = listOf(1, 2, 3, 4, 5)
        val pointerList = list.points()

        val pointingList = PointingList.from(pointerList)

        pointingList.fold(0) { acc, i -> acc + i } shouldBe 15

    }

    "foldIndexed" {

        val list = listOf(1, 2, 3, 4, 5)
        val pointerList = list.points()

        val pointingList = PointingList.from(pointerList)

        pointingList.foldIndexed(0) { index, acc, i -> acc + i + index } shouldBe 25

    }
})
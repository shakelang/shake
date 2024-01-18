package com.shakelang.shake.bytecode.interpreter.heap

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class MallocTests : FreeSpec({

    "get a malloc header" {
        val globalMemory = GlobalMemory()
        val malloc = Malloc(globalMemory)
        globalMemory.grow(1)

        globalMemory.setLong(GlobalMemory.POINTER_BASE + 0, 100L)
        globalMemory.setLong(GlobalMemory.POINTER_BASE + 8, 20L)

        val header = malloc.readHeader(GlobalMemory.POINTER_BASE + 0)

        header.size shouldBe 100L
        header.next shouldBe 20L
    }

    "set a malloc header" {
        val globalMemory = GlobalMemory()
        val malloc = Malloc(globalMemory)
        globalMemory.grow(1)

        val header = MallocHeader(100L, 20L)
        malloc.writeHeader(0 + GlobalMemory.POINTER_BASE, header)

        globalMemory.getLong(GlobalMemory.POINTER_BASE + 0) shouldBe 100L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 8) shouldBe 20L
    }

    "malloc (empty free table)" {
        val globalMemory = GlobalMemory()
        val malloc = Malloc(globalMemory)
        globalMemory.grow(1)

        val pointer = malloc.malloc(100L)

        pointer shouldBe 16L + GlobalMemory.POINTER_BASE
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 0) shouldBe 100L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 8) shouldBe -1L

        val pointer2 = malloc.malloc(100L)

        pointer2 shouldBe 132L + GlobalMemory.POINTER_BASE
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 116) shouldBe 100L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 124) shouldBe -1L
    }

    "malloc + free" {
        val globalMemory = GlobalMemory()
        val malloc = Malloc(globalMemory)
        globalMemory.grow(1)

        val pointer = malloc.malloc(100L)

        pointer shouldBe 16L + GlobalMemory.POINTER_BASE
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 0) shouldBe 100L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 8) shouldBe -1L

        val pointer2 = malloc.malloc(100L)

        pointer2 shouldBe 132L + GlobalMemory.POINTER_BASE
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 116) shouldBe 100L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 124) shouldBe -1L

        val pointer3 = malloc.malloc(100L)

        pointer3 shouldBe 248L + GlobalMemory.POINTER_BASE
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 232) shouldBe 100L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 240) shouldBe -1L

        malloc.free(pointer)

        globalMemory.getLong(GlobalMemory.POINTER_BASE + 0) shouldBe 100L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 8) shouldBe -1L
        malloc.freeStartPointer shouldBe 0L + GlobalMemory.POINTER_BASE
        malloc.freeTailPointer shouldBe 0L + GlobalMemory.POINTER_BASE

        malloc.free(pointer3)

        globalMemory.getLong(GlobalMemory.POINTER_BASE + 232) shouldBe 100L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 240) shouldBe -1L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 0) shouldBe 100L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 8) shouldBe 232L + GlobalMemory.POINTER_BASE
        malloc.freeStartPointer shouldBe 0L + GlobalMemory.POINTER_BASE
        malloc.freeTailPointer shouldBe 232L + GlobalMemory.POINTER_BASE

        malloc.free(pointer2)

        globalMemory.getLong(GlobalMemory.POINTER_BASE + 116) shouldBe 100L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 124) shouldBe -1L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 0) shouldBe 100L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 8) shouldBe 232L + GlobalMemory.POINTER_BASE
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 232) shouldBe 100L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 240) shouldBe 116L + GlobalMemory.POINTER_BASE
        malloc.freeStartPointer shouldBe 0L + GlobalMemory.POINTER_BASE
        malloc.freeTailPointer shouldBe 116L + GlobalMemory.POINTER_BASE

        val pointer4 = malloc.malloc(100L)

        pointer4 shouldBe 16L + GlobalMemory.POINTER_BASE
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 0) shouldBe 100L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 8) shouldBe -1L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 116) shouldBe 100L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 124) shouldBe -1L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 232) shouldBe 100L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 240) shouldBe 116L + GlobalMemory.POINTER_BASE
        malloc.freeStartPointer shouldBe 232L + GlobalMemory.POINTER_BASE
        malloc.freeTailPointer shouldBe 116L + GlobalMemory.POINTER_BASE

        val pointer5 = malloc.malloc(100L)

        pointer5 shouldBe 248L + GlobalMemory.POINTER_BASE
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 0) shouldBe 100L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 8) shouldBe 232L + GlobalMemory.POINTER_BASE
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 116) shouldBe 100L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 124) shouldBe -1L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 232) shouldBe 100L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 240) shouldBe -1L
        malloc.freeStartPointer shouldBe 116L + GlobalMemory.POINTER_BASE
        malloc.freeTailPointer shouldBe 116L + GlobalMemory.POINTER_BASE

        malloc.free(pointer4)

        globalMemory.getLong(GlobalMemory.POINTER_BASE + 0) shouldBe 100L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 8) shouldBe -1L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 116) shouldBe 100L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 124) shouldBe 0L + GlobalMemory.POINTER_BASE
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 232) shouldBe 100L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 240) shouldBe -1L
        malloc.freeStartPointer shouldBe 116L + GlobalMemory.POINTER_BASE
        malloc.freeTailPointer shouldBe 0L + GlobalMemory.POINTER_BASE
    }

    "malloc + free with different sizes" {

        val globalMemory = GlobalMemory()
        val malloc = Malloc(globalMemory)
        globalMemory.grow(1)

        val pointer = malloc.malloc(100L)
        val pointer2 = malloc.malloc(200L)
        val pointer3 = malloc.malloc(30L)

        pointer shouldBe 16L + GlobalMemory.POINTER_BASE
        pointer2 shouldBe 132L + GlobalMemory.POINTER_BASE
        pointer3 shouldBe 348L + GlobalMemory.POINTER_BASE

        globalMemory.getLong(GlobalMemory.POINTER_BASE + 0) shouldBe 100L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 8) shouldBe 116L + GlobalMemory.POINTER_BASE
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 116) shouldBe 200L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 124) shouldBe 332L + GlobalMemory.POINTER_BASE
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 332) shouldBe 30L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 340) shouldBe -1L

        malloc.free(pointer)
        malloc.free(pointer2)
        malloc.free(pointer3)

        malloc.malloc(29L) shouldBe 348L + GlobalMemory.POINTER_BASE
        malloc.malloc(200L) shouldBe 132L + GlobalMemory.POINTER_BASE
        malloc.malloc(99L) shouldBe 16L + GlobalMemory.POINTER_BASE

        globalMemory.getLong(GlobalMemory.POINTER_BASE + 0) shouldBe 99L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 8) shouldBe -1L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 116) shouldBe 200L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 124) shouldBe 0L + GlobalMemory.POINTER_BASE
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 332) shouldBe 29L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 340) shouldBe 116L + GlobalMemory.POINTER_BASE
    }

    "test grow overflow" {
        val globalMemory = GlobalMemory()
        val malloc = Malloc(globalMemory)

        for (i in 0..99) {
            malloc.size shouldBe 1016L * i
            malloc.malloc(1000L) shouldBe 1016L * i + GlobalMemory.POINTER_BASE + 16L
        }

        malloc.size shouldBe 1016L * 100L
        globalMemory.size shouldBe 114688L
        globalMemory.outerSize shouldBe 7
    }
})

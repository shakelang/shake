package com.shakelang.shake.bytecode.interpreter.heap

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class MallocTests : FreeSpec({

    "get a malloc header" {
        val globalMemory = GlobalMemory()
        val malloc = Malloc(globalMemory)
        globalMemory.grow(1)

        globalMemory.setLong(0, 100L)
        globalMemory.setLong(8, 20L)

        val header = malloc.readHeader(0)

        header.size shouldBe 100L
        header.next shouldBe 20L
    }

    "set a malloc header" {
        val globalMemory = GlobalMemory()
        val malloc = Malloc(globalMemory)
        globalMemory.grow(1)

        val header = MallocHeader(100L, 20L)
        malloc.writeHeader(0, header)

        globalMemory.getLong(0) shouldBe 100L
        globalMemory.getLong(8) shouldBe 20L
    }

    "malloc (empty free table)" {
        val globalMemory = GlobalMemory()
        val malloc = Malloc(globalMemory)
        globalMemory.grow(1)

        val pointer = malloc.malloc(100L)

        pointer shouldBe 16L
        globalMemory.getLong(0) shouldBe 100L
        globalMemory.getLong(8) shouldBe -1L

        val pointer2 = malloc.malloc(100L)

        pointer2 shouldBe 132L
        globalMemory.getLong(116) shouldBe 100L
        globalMemory.getLong(124) shouldBe -1L
    }

    "malloc + free" {
        val globalMemory = GlobalMemory()
        val malloc = Malloc(globalMemory)
        globalMemory.grow(1)

        val pointer = malloc.malloc(100L)

        pointer shouldBe 16L
        globalMemory.getLong(0) shouldBe 100L
        globalMemory.getLong(8) shouldBe -1L

        val pointer2 = malloc.malloc(100L)

        pointer2 shouldBe 132L
        globalMemory.getLong(116) shouldBe 100L
        globalMemory.getLong(124) shouldBe -1L

        val pointer3 = malloc.malloc(100L)

        pointer3 shouldBe 248L
        globalMemory.getLong(232) shouldBe 100L
        globalMemory.getLong(240) shouldBe -1L

        malloc.free(pointer)

        globalMemory.getLong(0) shouldBe 100L
        globalMemory.getLong(8) shouldBe 116L
        malloc.freeStartPointer shouldBe 0L
        malloc.freeTailPointer shouldBe 0L

        malloc.free(pointer3)

        globalMemory.getLong(232) shouldBe 100L
        globalMemory.getLong(240) shouldBe -1L
        globalMemory.getLong(0) shouldBe 100L
        globalMemory.getLong(8) shouldBe 232L
        malloc.freeStartPointer shouldBe 0L
        malloc.freeTailPointer shouldBe 232L

        malloc.free(pointer2)

        globalMemory.getLong(116) shouldBe 100L
        globalMemory.getLong(124) shouldBe -1L
        globalMemory.getLong(0) shouldBe 100L
        globalMemory.getLong(8) shouldBe 232L
        globalMemory.getLong(232) shouldBe 100L
        globalMemory.getLong(240) shouldBe 116L
        malloc.freeStartPointer shouldBe 0L
        malloc.freeTailPointer shouldBe 116L

        val pointer4 = malloc.malloc(100L)

        pointer4 shouldBe 16L
        globalMemory.getLong(0) shouldBe 100L
        globalMemory.getLong(8) shouldBe -1L
        globalMemory.getLong(116) shouldBe 100L
        globalMemory.getLong(124) shouldBe -1L
        globalMemory.getLong(232) shouldBe 100L
        globalMemory.getLong(240) shouldBe 116L
        malloc.freeStartPointer shouldBe 232L
        malloc.freeTailPointer shouldBe 116L

        val pointer5 = malloc.malloc(100L)

        pointer5 shouldBe 248L
        globalMemory.getLong(0) shouldBe 100L
        globalMemory.getLong(8) shouldBe -1L
        globalMemory.getLong(116) shouldBe 100L
        globalMemory.getLong(124) shouldBe -1L
        globalMemory.getLong(232) shouldBe 100L
        globalMemory.getLong(240) shouldBe -1L
        malloc.freeStartPointer shouldBe 116L
        malloc.freeTailPointer shouldBe 116L

        malloc.free(pointer4)

        globalMemory.getLong(0) shouldBe 100L
        globalMemory.getLong(8) shouldBe -1L
        globalMemory.getLong(116) shouldBe 100L
        globalMemory.getLong(124) shouldBe 0L
        globalMemory.getLong(232) shouldBe 100L
        globalMemory.getLong(240) shouldBe -1L
        malloc.freeStartPointer shouldBe 116L
        malloc.freeTailPointer shouldBe 0L
    }

    "malloc + free with different sizes" {

        val globalMemory = GlobalMemory()
        val malloc = Malloc(globalMemory)
        globalMemory.grow(1)

        val pointer = malloc.malloc(100L)
        val pointer2 = malloc.malloc(200L)
        val pointer3 = malloc.malloc(30L)

        pointer shouldBe 16L
        pointer2 shouldBe 132L
        pointer3 shouldBe 348L

        globalMemory.getLong(0) shouldBe 100L
        globalMemory.getLong(8) shouldBe 116L
        globalMemory.getLong(116) shouldBe 200L
        globalMemory.getLong(124) shouldBe -1L
        globalMemory.getLong(332) shouldBe 30L
        globalMemory.getLong(340) shouldBe -1L

        malloc.free(pointer)
        malloc.free(pointer2)
        malloc.free(pointer3)

        malloc.malloc(29L) shouldBe 348L
        malloc.malloc(200L) shouldBe 132L
        malloc.malloc(99L) shouldBe 16L

        globalMemory.getLong(0) shouldBe 99L
        globalMemory.getLong(8) shouldBe -1L
        globalMemory.getLong(116) shouldBe 200L
        globalMemory.getLong(124) shouldBe -1L
        globalMemory.getLong(332) shouldBe 29L
        globalMemory.getLong(340) shouldBe -1L
    }
})

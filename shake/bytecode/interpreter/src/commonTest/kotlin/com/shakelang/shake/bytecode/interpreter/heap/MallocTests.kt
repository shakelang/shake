package com.shakelang.shake.bytecode.interpreter.heap

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class MallocTests : FreeSpec({

    "get a malloc header" {
        val globalMemory = GlobalMemory()
        val malloc = Malloc(globalMemory)
        globalMemory.grow(1)

        globalMemory.setLong(GlobalMemory.POINTER_BASE + 0, 104L)
        globalMemory.setLong(GlobalMemory.POINTER_BASE + 8, 20L)

        val header = malloc.readHeader(GlobalMemory.POINTER_BASE + 0)

        header.size shouldBe 104L
        header.next shouldBe 20L
    }

    "set a malloc header" {
        val globalMemory = GlobalMemory()
        val malloc = Malloc(globalMemory)
        globalMemory.grow(1)

        val header = MallocHeader(104L, 20L)
        malloc.writeHeader(0 + GlobalMemory.POINTER_BASE, header)

        globalMemory.getLong(GlobalMemory.POINTER_BASE + 0) shouldBe 104L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 8) shouldBe 20L
    }

    "malloc (empty free table)" {
        val globalMemory = GlobalMemory()
        val malloc = Malloc(globalMemory)
        globalMemory.grow(1)

        val pointer = malloc.malloc(104L)

        pointer shouldBe 16L + GlobalMemory.POINTER_BASE
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 0) shouldBe 104L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 8) shouldBe -1L

        val pointer2 = malloc.malloc(104L)

        pointer2 shouldBe 136L + GlobalMemory.POINTER_BASE
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 120) shouldBe 104L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 128) shouldBe -1L
    }

    "malloc + free" {
        val globalMemory = GlobalMemory()
        val malloc = Malloc(globalMemory)
        globalMemory.grow(1)

        val pointer = malloc.malloc(104L)

        pointer shouldBe 16L + GlobalMemory.POINTER_BASE
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 0) shouldBe 104L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 8) shouldBe -1L

        val pointer2 = malloc.malloc(104L)

        pointer2 shouldBe 136L + GlobalMemory.POINTER_BASE
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 120) shouldBe 104L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 128) shouldBe -1L

        val pointer3 = malloc.malloc(104L)

        pointer3 shouldBe 256L + GlobalMemory.POINTER_BASE
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 240) shouldBe 104L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 248) shouldBe -1L

        malloc.freePointer(pointer)

        globalMemory.getLong(GlobalMemory.POINTER_BASE + 0) shouldBe 104L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 8) shouldBe -1L
        malloc.freeStartPointer shouldBe 0L + GlobalMemory.POINTER_BASE
        malloc.freeTailPointer shouldBe 0L + GlobalMemory.POINTER_BASE

        malloc.freePointer(pointer3)

        globalMemory.getLong(GlobalMemory.POINTER_BASE + 240) shouldBe 104L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 248) shouldBe -1L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 0) shouldBe 104L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 8) shouldBe 240L + GlobalMemory.POINTER_BASE
        malloc.freeStartPointer shouldBe 0L + GlobalMemory.POINTER_BASE
        malloc.freeTailPointer shouldBe 240L + GlobalMemory.POINTER_BASE

        malloc.freePointer(pointer2)

        globalMemory.getLong(GlobalMemory.POINTER_BASE + 120) shouldBe 104L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 128) shouldBe -1L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 0) shouldBe 104L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 8) shouldBe 240L + GlobalMemory.POINTER_BASE
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 240) shouldBe 104L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 248) shouldBe 120L + GlobalMemory.POINTER_BASE
        malloc.freeStartPointer shouldBe 0L + GlobalMemory.POINTER_BASE
        malloc.freeTailPointer shouldBe 120L + GlobalMemory.POINTER_BASE

        val pointer4 = malloc.malloc(104L)

        pointer4 shouldBe 16L + GlobalMemory.POINTER_BASE
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 0) shouldBe 104L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 8) shouldBe -1L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 120) shouldBe 104L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 128) shouldBe -1L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 240) shouldBe 104L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 248) shouldBe 120L + GlobalMemory.POINTER_BASE
        malloc.freeStartPointer shouldBe 240L + GlobalMemory.POINTER_BASE
        malloc.freeTailPointer shouldBe 120L + GlobalMemory.POINTER_BASE

        val pointer5 = malloc.malloc(104L)

        pointer5 shouldBe 256L + GlobalMemory.POINTER_BASE
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 0) shouldBe 104L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 8) shouldBe 240L + GlobalMemory.POINTER_BASE
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 120) shouldBe 104L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 128) shouldBe -1L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 240) shouldBe 104L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 248) shouldBe -1L
        malloc.freeStartPointer shouldBe 120L + GlobalMemory.POINTER_BASE
        malloc.freeTailPointer shouldBe 120L + GlobalMemory.POINTER_BASE

        malloc.freePointer(pointer4)

        globalMemory.getLong(GlobalMemory.POINTER_BASE + 0) shouldBe 104L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 8) shouldBe -1L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 120) shouldBe 104L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 128) shouldBe 0L + GlobalMemory.POINTER_BASE
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 240) shouldBe 104L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 248) shouldBe -1L
        malloc.freeStartPointer shouldBe 120L + GlobalMemory.POINTER_BASE
        malloc.freeTailPointer shouldBe 0L + GlobalMemory.POINTER_BASE
    }

    "malloc + free with different sizes" {

        val globalMemory = GlobalMemory()
        val malloc = Malloc(globalMemory)
        globalMemory.grow(1)

        val pointer = malloc.malloc(104L)
        val pointer2 = malloc.malloc(200L)
        val pointer3 = malloc.malloc(30L)

        pointer shouldBe 16L + GlobalMemory.POINTER_BASE
        pointer2 shouldBe 136L + GlobalMemory.POINTER_BASE
        pointer3 shouldBe 352L + GlobalMemory.POINTER_BASE

        globalMemory.getLong(GlobalMemory.POINTER_BASE + 0) shouldBe 104L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 8) shouldBe 120L + GlobalMemory.POINTER_BASE
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 120) shouldBe 200L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 128) shouldBe 336L + GlobalMemory.POINTER_BASE
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 336) shouldBe 32L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 344) shouldBe -1L

        malloc.freePointer(pointer)
        malloc.freePointer(pointer2)
        malloc.freePointer(pointer3)

        malloc.malloc(29L) shouldBe 352L + GlobalMemory.POINTER_BASE
        malloc.malloc(200L) shouldBe 136L + GlobalMemory.POINTER_BASE
        malloc.malloc(99L) shouldBe 16L + GlobalMemory.POINTER_BASE

        globalMemory.getLong(GlobalMemory.POINTER_BASE + 0) shouldBe 104L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 8) shouldBe -1L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 120) shouldBe 200L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 128) shouldBe 0L + GlobalMemory.POINTER_BASE
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 336) shouldBe 32L
        globalMemory.getLong(GlobalMemory.POINTER_BASE + 344) shouldBe 120L + GlobalMemory.POINTER_BASE
    }

    "test grow overflow" {
        val globalMemory = GlobalMemory()
        val malloc = Malloc(globalMemory)

        for (i in 0..99) {
            malloc.size shouldBe 1016L * i
            val pointer = malloc.malloc(1000L) shouldBe 1016L * i + GlobalMemory.POINTER_BASE + 16L
            val header = malloc.readHeaderFor(pointer)
            header.size shouldBe 1000L
            header.next shouldBe -1L
        }

        malloc.size shouldBe 1016L * 100L
        globalMemory.size shouldBe 114688L
        globalMemory.outerSize shouldBe 7
    }
})

package com.shakelang.shake.util

import com.shakelang.shake.util.pointer.Pointer
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class PointerOfTests : FreeSpec({

    "test value" {
        val pointer = Pointer.of(1)
        pointer.value shouldBe 1
    }

    "test transform" {
        val pointer = Pointer.of(1)
        pointer.transform { it + 1 }.value shouldBe 2
    }

    "test chain" {
        val pointer = Pointer.of(1)
        pointer.chain { Pointer.of(it + 1) }.value shouldBe 2
    }

    "test chain with null" {
        val pointer = Pointer.of(1)

        // That's quite ugly and weird, but kotlin is quite clever, and
        // we want to trick it to not automatically understand that the
        // value is null always null and therefore the inner function
        // block will always return null
        val i: Number? = null
        pointer.chainAllowNull {
            if(i == null) null
            else Pointer.of(it + 1)
        }.value shouldBe null
    }

    "test equals" {
        val pointer = Pointer.of(1)
        val pointer2 = Pointer.of(1)

        pointer shouldNotBe pointer2
        pointer shouldBe pointer
        pointer2 shouldBe pointer2
        pointer.value shouldBe pointer2.value
    }

})

class PointerMutableOfTests : FreeSpec({

    "test value" {
        val pointer = Pointer.mutableOf(1)
        pointer.value shouldBe 1
    }

    "test change value" {
        val pointer = Pointer.mutableOf(1)
        pointer.value = 2
        pointer.value shouldBe 2
    }

    "test transform" {
        val pointer = Pointer.mutableOf(1)
        pointer.transform { it + 1 }.value shouldBe 2
    }

    "test transform with changed value" {
        val pointer = Pointer.mutableOf(1)
        val transformed = pointer.transform { it + 1 }
        transformed.value shouldBe 2
        pointer.value = 2
        transformed.value shouldBe 3
    }

    "test chain" {
        val pointer = Pointer.mutableOf(1)
        pointer.chain { Pointer.of(it + 1) }.value shouldBe 2
    }

    "test chain with changed value" {
        val pointer = Pointer.mutableOf(1)
        val chained = pointer.chain { Pointer.of(it + 1) }
        chained.value shouldBe 2
        pointer.value = 2
        chained.value shouldBe 3
    }

    "test chain with null" {
        val pointer = Pointer.mutableOf(1)

        // That's quite ugly and weird, but kotlin is quite clever, and
        // we want to trick it to not automatically understand that the
        // value is null always null and therefore the inner function
        // block will always return null
        val i: Number? = null
        pointer.chainAllowNull {
            if(i == null) null
            else Pointer.of(it + 1)
        }.value shouldBe null
    }

    "test chain with null and changed value" {
        val pointer = Pointer.mutableOf(1)
        val chained = pointer.chainAllowNull {
            if(it == 1) null
            else Pointer.of(it + 1)
        }
        chained.value shouldBe null
        pointer.value = 2
        chained.value shouldBe 3
    }

    "test equals" {
        val pointer = Pointer.mutableOf(1)
        val pointer2 = Pointer.mutableOf(1)

        pointer shouldNotBe pointer2
        pointer shouldBe pointer
        pointer2 shouldBe pointer2
        pointer.value shouldBe pointer2.value
    }

})

class PointerLateTests : FreeSpec({

    "test value & init" {
        val pointer = Pointer.late<Int>()
        shouldThrow<IllegalStateException> {
            pointer.value
        }
        pointer.init(1)
        shouldThrow<IllegalStateException> {
            pointer.init(2)
        }
    }

    "test change value" {
        val pointer = Pointer.late<Int>()
        pointer.init(1)
        pointer.value shouldBe 1
    }

    "test transform" {
        val pointer = Pointer.late<Int>()
        pointer.init(1)
        pointer.transform { it + 1 }.value shouldBe 2
    }

    "test transform before init" {
        val pointer = Pointer.late<Int>()
        val transformed = pointer.transform { it + 1 }
        shouldThrow<IllegalStateException>() {
            transformed.value
        }
        pointer.init(1)
        transformed.value shouldBe 2
    }

    "test chain" {
        val pointer = Pointer.late<Int>()
        pointer.init(1)
        pointer.chain { Pointer.of(it + 1) }.value shouldBe 2
    }

    "test chain before init" {
        val pointer = Pointer.late<Int>()
        val chained = pointer.chain { Pointer.of(it + 1) }
        shouldThrow<IllegalStateException>() {
            chained.value
        }
        pointer.init(1)
        chained.value shouldBe 2
    }

    "test chain with null" {
        val pointer = Pointer.late<Int>()
        pointer.init(1)

        // That's quite ugly and weird, but kotlin is quite clever, and
        // we want to trick it to not automatically understand that the
        // value is null always null and therefore the inner function
        // block will always return null
        val i: Number? = null
        pointer.chainAllowNull {
            if(i == null) null
            else Pointer.of(it + 1)
        }.value shouldBe null
    }

    "test chain with null before init" {
        val pointer = Pointer.late<Int>()
        val chained = pointer.chainAllowNull {
            if(it == 1) null
            else Pointer.of(it + 1)
        }
        shouldThrow<IllegalStateException>() {
            chained.value
        }
        pointer.init(1)
        chained.value shouldBe null
    }

    "test equals" {
        val pointer = Pointer.late<Int>()
        val pointer2 = Pointer.late<Int>()
        pointer.init(1)
        pointer2.init(1)

        pointer shouldNotBe pointer2
        pointer shouldBe pointer
        pointer2 shouldBe pointer2
        pointer.value shouldBe pointer2.value
    }
})

class PointerLateMutableTests : FreeSpec({

    "test value & init" {
        val pointer = Pointer.lateMutable<Int>()
        shouldThrow<IllegalStateException> {
            pointer.value
        }
        pointer.init(1)
        shouldThrow<IllegalStateException> {
            pointer.init(2)
        }
    }

    "test change value" {
        val pointer = Pointer.lateMutable<Int>()
        pointer.init(1)
        pointer.value shouldBe 1
        pointer.value = 2
        pointer.value shouldBe 2
    }

    "test transform" {
        val pointer = Pointer.lateMutable<Int>()
        pointer.init(1)
        pointer.transform { it + 1 }.value shouldBe 2
    }

    "test transform with changed value" {
        val pointer = Pointer.lateMutable<Int>()
        pointer.init(1)
        val transformed = pointer.transform { it + 1 }
        transformed.value shouldBe 2
        pointer.value = 2
        transformed.value shouldBe 3
    }

    "test transform before init" {
        val pointer = Pointer.lateMutable<Int>()
        val transformed = pointer.transform { it + 1 }
        shouldThrow<IllegalStateException>() {
            transformed.value
        }
        pointer.init(1)
        transformed.value shouldBe 2
    }

    "test chain" {
        val pointer = Pointer.lateMutable<Int>()
        pointer.init(1)
        pointer.chain { Pointer.of(it + 1) }.value shouldBe 2
    }

    "test chain with changed value" {
        val pointer = Pointer.lateMutable<Int>()
        pointer.init(1)
        val chained = pointer.chain { Pointer.of(it + 1) }
        chained.value shouldBe 2
        pointer.value = 2
        chained.value shouldBe 3
    }

    "test chain before init" {
        val pointer = Pointer.lateMutable<Int>()
        val chained = pointer.chain { Pointer.of(it + 1) }
        shouldThrow<IllegalStateException>() {
            chained.value
        }
        pointer.init(1)
        chained.value shouldBe 2
    }

    "test chain with null" {
        val pointer = Pointer.lateMutable<Int>()
        pointer.init(1)

        // That's quite ugly and weird, but kotlin is quite clever, and
        // we want to trick it to not automatically understand that the
        // value is null always null and therefore the inner function
        // block will always return null
        val i: Number? = null
        pointer.chainAllowNull {
            if(i == null) null
            else Pointer.of(it + 1)
        }.value shouldBe null
    }

    "test chain with null before init" {
        val pointer = Pointer.lateMutable<Int>()
        val chained = pointer.chainAllowNull {
            if(it == 1) null
            else Pointer.of(it + 1)
        }
        shouldThrow<IllegalStateException>() {
            chained.value
        }
        pointer.init(1)
        chained.value shouldBe null
    }

    "test equals" {
        val pointer = Pointer.lateMutable<Int>()
        val pointer2 = Pointer.lateMutable<Int>()
        pointer.init(1)
        pointer2.init(1)

        pointer shouldNotBe pointer2
        pointer shouldBe pointer
        pointer2 shouldBe pointer2
        pointer.value shouldBe pointer2.value
    }

})
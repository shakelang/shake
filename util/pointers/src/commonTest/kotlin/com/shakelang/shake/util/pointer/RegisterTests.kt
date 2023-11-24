package com.shakelang.shake.util.pointer

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class RegisterTests : FreeSpec({

    "registration/destroy" {

        val register = PointerRegister<Int>()

        PointerRegister.instances.contains(register) shouldBe true
        register.destroy()
        PointerRegister.instances.contains(register) shouldBe false

    }

    "registering" {

        val register = PointerRegister<Int>()

        val pointer = 1.point()
        register.register(pointer) shouldBe 0

        register.destroy()

    }

    "indexOf" {

        val register = PointerRegister<Int>()

        val pointer = 1.point()
        register.register(pointer) shouldBe 0
        register.indexOf(pointer) shouldBe 0

        register.destroy()

    }

    "unregister" {

        val register = PointerRegister<Int>()

        val pointer = 1.point()
        register.register(pointer) shouldBe 0
        register.indexOf(pointer) shouldBe 0

        register.unregister(0)
        register.indexOf(pointer) shouldBe -1

        register.destroy()

    }

    "get" {

        val register = PointerRegister<Int>()

        val pointer = 1.point()
        register.register(pointer) shouldBe 0
        register.indexOf(pointer) shouldBe 0

        register[0] shouldBe pointer

        shouldThrow<IllegalStateException> {
            register[1]
        }

        register.destroy()

    }

    "getOrNull" {

        val register = PointerRegister<Int>()

        val pointer = 1.point()
        register.register(pointer) shouldBe 0
        register.indexOf(pointer) shouldBe 0

        register.getOrNull(0) shouldBe pointer
        register.getOrNull(1) shouldBe null

        register.destroy()

    }

    "createPointer" {

        val register = PointerRegister<Int>()

        val pointer = register.createPointer(1)
        register.indexOf(pointer) shouldBe 0

        register.destroy()

    }

    "createPointer (late)" {

        val register = PointerRegister<Int>()

        val pointer = register.createPointer()
        pointer.init(1)
        register.indexOf(pointer) shouldBe 0

        register.destroy()

    }

    "createMutablePointer" {

        val register = PointerRegister<Int>()

        val pointer = register.createMutablePointer(1)
        register.indexOf(pointer) shouldBe 0

        register.destroy()

    }

    "createMutablePointer (late)" {

        val register = PointerRegister<Int>()

        val pointer = register.createMutablePointer()
        pointer.init(1)
        register.indexOf(pointer) shouldBe 0

        register.destroy()

    }

    "destroyAllContainedPointers" {

        val register = PointerRegister<Int>()
        val register2 = PointerRegister<Int>()

        val pointer = 1.point()
        register.register(pointer) shouldBe 0
        register2.register(pointer) shouldBe 0

        val pointer2 = 2.point()
        register.register(pointer2) shouldBe 1
        register2.register(pointer2) shouldBe 1

        val pointer3 = 3.point()
        register2.register(pointer3) shouldBe 2

        register.indexOf(pointer) shouldBe 0
        register.indexOf(pointer2) shouldBe 1
        register.indexOf(pointer3) shouldBe -1

        register2.indexOf(pointer) shouldBe 0
        register2.indexOf(pointer2) shouldBe 1
        register2.indexOf(pointer3) shouldBe 2

        register.destroyAllContainedPointers()
        register.indexOf(pointer) shouldBe -1
        register.indexOf(pointer2) shouldBe -1
        register.indexOf(pointer3) shouldBe -1

        register2.indexOf(pointer) shouldBe -1
        register2.indexOf(pointer2) shouldBe -1
        register2.indexOf(pointer3) shouldBe 2

        register.destroy()
        register2.destroy()
    }

    "Pointer.register" {

        val register = PointerRegister<Int>()

        val pointer = 1.point()
        pointer.register(register) shouldBe 0
        register.indexOf(pointer) shouldBe 0

        register.destroy()

    }

    "Pointer.unregister(Register)" {

        val register = PointerRegister<Int>()

        val pointer = 1.point()
        pointer.register(register) shouldBe 0
        register.indexOf(pointer) shouldBe 0

        pointer.unregister(register)
        register.indexOf(pointer) shouldBe -1

        register.destroy()

    }

    "Pointer.unregister" {

        val register = PointerRegister<Int>()
        val register2 = PointerRegister<Int>()

        val pointer = 1.point()
        pointer.register(register) shouldBe 0
        pointer.register(register2) shouldBe 0
        register.indexOf(pointer) shouldBe 0

        pointer.unregister()
        register.indexOf(pointer) shouldBe -1
        register2.indexOf(pointer) shouldBe -1

        register.destroy()
        register2.destroy()
    }
})
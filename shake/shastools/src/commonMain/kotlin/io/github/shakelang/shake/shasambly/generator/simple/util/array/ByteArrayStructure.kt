@file:Suppress("unused")
package io.github.shakelang.shake.shasambly.generator.simple.util.array

import io.github.shakelang.shake.shasambly.generator.simple.SimpleShasambly

class StaticallySizedLocalByteArrayStructure(val shasambly: SimpleShasambly, val address: Int, val size: Int = -1) {

    fun getElement() {
        shasambly {
            i_get_local(address)
            isub()
            ineg()
            b_get_global_dynamic()
        }
    }

    fun getElement(index: Int) {
        shasambly {
            i_get_local(address)
            if(index != 0) {
                ipush(index)
                isub()
            }
            b_get_global_dynamic()
        }
    }

    fun storeElement() {
        shasambly {
            i_get_local(address)
            isub()
            ineg()
            b_store_global_dynamic()
        }
    }

    fun storeElement(index: Int) {
        shasambly {
            i_get_local(address)
            if(index != 0) {
                ipush(index)
                isub()
            }
            b_store_global_dynamic()
        }
    }

    fun storeElement(index: Int, value: Byte) {
        shasambly {
            bpush(value)
            i_get_local(address)
            if(index != 0) {
                ipush(index)
                isub()
            }
            b_store_global_dynamic()
        }
    }

    fun free() {
        shasambly {
            i_get_local(address)
            if(size == -1) natives.freeGlobal()
            else natives.freeGlobal(size)
        }
    }
    fun free(size: Int) {
        shasambly {
            i_get_local(address)
            natives.freeGlobal(size)
        }
    }
}

fun SimpleShasambly.createStaticallySizedLocalByteArray(addr: Int, size: Int): StaticallySizedLocalByteArrayStructure {
    natives.declareGlobal(size)
    i_store_local(addr)
    return StaticallySizedLocalByteArrayStructure(this.base, addr, size)
}

fun SimpleShasambly.createStaticallySizedLocalByteArray(addr: Int): StaticallySizedLocalByteArrayStructure {
    natives.declareGlobal()
    i_store_local(addr)
    return StaticallySizedLocalByteArrayStructure(this.base, addr)
}

class SavedSizeLocalByteArrayStructure(val shasambly: SimpleShasambly, val address: Int) {

    fun getSize() {
        shasambly {
            i_get_local(address)
            i_get_global_dynamic()
        }
    }

    fun getElement() {
        shasambly {
            i_get_local(address)
            isub()
            ineg()
            ipush(4)
            isub()
            b_get_global_dynamic()
        }
    }

    fun getElement(index: Int) {
        shasambly {
            i_get_local(address)
            ipush(index + 4)
            isub()
            b_get_global_dynamic()
        }
    }

    fun storeElement() {
        shasambly {
            i_get_local(address)
            isub()
            ineg()
            ipush(4)
            isub()
            b_store_global_dynamic()
        }
    }

    fun storeElement(index: Int) {
        shasambly {
            i_get_local(address)
            ipush(index + 4)
            isub()
            b_store_global_dynamic()
        }
    }

    fun storeElement(index: Int, value: Byte) {
        shasambly {
            bpush(value)
            i_get_local(address)
            ipush(index + 4)
            isub()
            b_store_global_dynamic()
        }
    }

    fun free() {
        shasambly {
            getSize()
            ipush(4)
            iadd()
            i_get_local(address)
            natives.freeGlobal()
        }
    }
}

fun SimpleShasambly.createSavedSizeLocalByteArray(addr: Int, size: Int): SavedSizeLocalByteArrayStructure {
    natives.declareGlobal(size + 4)
    i_store_local(addr)
    ipush(size)
    i_get_local(addr)
    i_store_global_dynamic()
    return SavedSizeLocalByteArrayStructure(this.base, addr)
}

fun SimpleShasambly.createSavedSizeLocalByteArray(addr: Int): SavedSizeLocalByteArrayStructure {
    natives.idup()
    ipush(4)
    iadd()
    natives.declareGlobal()
    natives.idup()
    i_store_local(addr)
    natives.iswap(0u, 4u)
    i_store_global_dynamic()
    return SavedSizeLocalByteArrayStructure(this.base, addr)
}
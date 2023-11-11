package com.shakelang.shake.processor.program.types.code

import com.shakelang.shake.processor.program.types.ShakeAssignable
import com.shakelang.shake.processor.program.types.ShakeClass
import com.shakelang.shake.processor.program.types.ShakeMethod

interface ShakeScope {
    val parent: ShakeScope?
    fun get(name: String): ShakeAssignable?
    fun getFunctions(name: String): List<ShakeMethod>
    fun getClass(name: String): ShakeClass?
    fun getInvokable(name: String): List<ShakeInvokable>
    fun use(name: String)
}

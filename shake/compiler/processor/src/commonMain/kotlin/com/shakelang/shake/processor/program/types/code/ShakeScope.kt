package com.shakelang.shake.processor.program.types.code

import com.shakelang.shake.processor.program.types.ShakeAssignable
import com.shakelang.shake.processor.program.types.ShakeClass
import com.shakelang.shake.processor.program.types.ShakeMethod

interface ShakeScope {
    val uniqueName: String
    val parent: ShakeScope?
    fun getField(name: String): ShakeAssignable?
    fun getFields(name: String): List<ShakeAssignable>
    fun getFunctions(name: String): List<ShakeMethod>
    fun getClass(name: String): ShakeClass?
    fun getClasses(name: String): List<ShakeClass>
    fun getInvokable(name: String): List<ShakeInvokable>
    fun use(name: String)
}

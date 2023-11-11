package com.shakelang.shake.js.native

interface NativeClass {

    val qualifiedName: String
    val functions: List<NativeFunction>
    val fields: List<NativeField>
}

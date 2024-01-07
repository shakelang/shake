package com.shakelang.util.logger

actual fun initGlobalDebugConfiguration() {
    val debug = System.getenv("DEBUG")
    if (debug != null) {
        val debugs = debug.split(",", ";")
        GlobalDebugConfiguration.paths.addAll(debugs)
    }
}
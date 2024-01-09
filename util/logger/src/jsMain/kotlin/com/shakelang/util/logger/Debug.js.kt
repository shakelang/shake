package com.shakelang.util.logger

import com.shakelang.util.environment.getRunningEnvironment
import com.shakelang.util.environment.process

actual fun initGlobalDebugConfiguration() {
    // environment variables are only available in nodejs
    if (getRunningEnvironment().isJavaScriptNode) {
        val debug = process.env.DEBUG as String?
        if (debug != null && debug != undefined) {
            val debugs = debug.split(",", ";")
            GlobalDebugConfiguration.paths.addAll(debugs)
        }
    }
}

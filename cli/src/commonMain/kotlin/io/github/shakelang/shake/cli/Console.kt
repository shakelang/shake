package com.shakelang.shake.cli

import com.shakelang.shake.util.parseutils.Promise

expect fun readLine(message: String): Promise<String?>
expect fun readLine(): Promise<String?>
package com.github.shakelang.shake.cli

import com.github.shakelang.shake.util.Promise

expect fun readLine(message: String): Promise<String?>
expect fun readLine(): Promise<String?>
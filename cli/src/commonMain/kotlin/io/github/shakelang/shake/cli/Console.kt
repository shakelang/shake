package io.github.shakelang.shake.cli

import io.github.shakelang.parseutils.Promise

expect fun readLine(message: String): Promise<String?>
expect fun readLine(): Promise<String?>
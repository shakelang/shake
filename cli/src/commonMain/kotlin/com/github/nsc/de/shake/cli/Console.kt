package com.github.nsc.de.shake.cli

import com.github.nsc.de.shake.util.Promise

expect fun readLine(message: String): Promise<String?>
expect fun readLine(): Promise<String?>
package io.github.shakelang.parseutils.streaming.input

actual typealias InputStream = java.io.InputStream
fun InputStream.from(stream: java.io.InputStream): InputStream = stream
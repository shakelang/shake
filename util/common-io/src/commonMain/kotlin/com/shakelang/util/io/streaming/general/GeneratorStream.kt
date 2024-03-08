package com.shakelang.util.io.streaming.general

/**
 * Create a Stream from a [Sequence]
 * @receiver The sequence to create the stream from
 * @return The created stream
 * @since 0.5.0
 */
class GeneratorStream<T>(
    private val generator: () -> T,
    private val hasNext: () -> Boolean,
) : Stream<T> {
    override fun read(): T = generator()
    override fun hasNext(): Boolean = hasNext()
}

/**
 * Create a Stream from a [Sequence]
 * @receiver The sequence to create the stream from
 * @return The created stream
 * @since 0.5.0
 */
class InfiniteGeneratorStream<T>(
    private val generator: () -> T,
) : Stream<T> {
    override fun read(): T = generator()
    override fun hasNext(): Boolean = true
}

fun <T> (() -> T).stream() = InfiniteGeneratorStream(this)
fun <T> generatorStream(generator: () -> T) = InfiniteGeneratorStream(generator)
fun <T> generatorStream(generator: () -> T, hasNext: () -> Boolean) = GeneratorStream(generator, hasNext)

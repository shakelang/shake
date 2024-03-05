package com.shakelang.util.io.streaming.general

class IndexedMapStream<T, R>(

    private val stream: Stream<T>,
    private val mapper: (Int, T) -> R,

) : Stream<R> {

    private var index = 0

    override fun read(): R {
        return mapper(index++, stream.read())
    }

    override fun hasNext(): Boolean {
        return stream.hasNext()
    }
}

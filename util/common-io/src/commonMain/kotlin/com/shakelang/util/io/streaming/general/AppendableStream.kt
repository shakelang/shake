package com.shakelang.util.io.streaming.general

/**
 * A LinkedList-based stream that can be appended to
 * @param T The type of the elements in the stream
 * @property head The first element in the stream
 * @property tail The last element in the stream
 * @constructor Create a new appendable stream
 * @since 0.5.0
 */
class AppendableStream<T> : Stream<T> {

    /**
     * The first element in the stream
     * @since 0.5.0
     */
    private var head: StreamElement<T>? = null

    /**
     * The last element in the stream
     * @since 0.5.0
     */
    private var tail: StreamElement<T>? = null

    /**
     * Append a new element to the stream
     * @param value The value of the new element
     * @since 0.5.0
     */
    fun append(value: T) {
        val newElement = StreamElement(value)

        if (head == null) {
            head = newElement
        } else {
            tail!!.attachNext(newElement)
        }

        tail = newElement
    }

    /**
     * Append multiple new elements to the stream
     * @param values The values of the new elements
     * @since 0.5.0
     */
    fun append(vararg values: T) {
        for (value in values) {
            this.append(value)
        }
    }

    private fun pop(): StreamElement<T>? {
        if (head == null) return null

        val head = this.head
        this.head = head!!.next
        return head
    }

    /**
     * Read the next element from the stream
     * @return The next element
     * @since 0.5.0
     */
    override fun read(): T {
        val head = this.pop() ?: throw IllegalStateException("Stream is empty")
        return head.value
    }

    /**
     * Check if the stream has a next element
     * @return If the stream has a next element
     * @since 0.5.0
     */
    override fun hasNext(): Boolean {
        return this.head != null
    }

    /**
     * A private class representing an element in the stream
     * @property value The value of the element
     * @since 0.5.0
     */
    private class StreamElement<T>(
        /**
         * The value of the element
         * @since 0.5.0
         */
        val value: T,
    ) {

        /**
         * The next element in the stream
         * @since 0.5.0
         */
        var next: StreamElement<T>? = null
            private set

        /**
         * Attach the next element to this element
         * @since 0.5.0
         */
        fun attachNext(next: StreamElement<T>?) {
            this.next = next
        }
    }
}

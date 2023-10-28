package io.github.shakelang.io.streaming.input

import io.github.shakelang.primitives.bytes.toBytes


/**
 * Returns a DataInputStream that reads from this input stream.
 *
 * @return a DataInputStream that reads from this input stream.
 *
 * @since 0.1.0
 * @version 0.1.0
 * @author Nicolas Schmidt &lt;@nsc-de&gt;
 */
inline fun InputStream.asDataInputStream(): DataInputStream {
    return DataInputStream(this)
}

/**
 * Returns a DataInputStream that reads from this input stream.
 *
 * @return a DataInputStream that reads from this input stream.
 *
 * @since 0.1.0
 * @version 0.1.0
 * @author Nicolas Schmidt &lt;@nsc-de&gt;
 */
inline val InputStream.dataStream: DataInputStream
    get() = DataInputStream(this)

/**
 * Returns a CountingInputStream that reads from this input stream.
 *
 * @return a CountingInputStream that reads from this input stream.
 *
 * @since 0.1.0
 * @version 0.1.0
 * @author Nicolas Schmidt &lt;@nsc-de&gt;
 */
inline fun InputStream.asCountingInputStream(): CountingInputStream {
    return CountingInputStream(this)
}

/**
 * Returns a CountingInputStream that reads from this input stream.
 *
 * @return a CountingInputStream that reads from this input stream.
 *
 * @since 0.1.0
 * @version 0.1.0
 * @author Nicolas Schmidt &lt;@nsc-de&gt;
 */
inline val InputStream.countingStream: CountingInputStream
    get() = CountingInputStream(this)

/**
 * Returns a BufferedInputStream that reads from this input stream.
 *
 * @return a BufferedInputStream that reads from this input stream.
 *
 * @since 0.1.0
 * @version 0.1.0
 * @author Nicolas Schmidt &lt;@nsc-de&gt;
 */
inline fun InputStream.asBufferedInputStream(): BufferedInputStream {
    return BufferedInputStream(this)
}

/**
 * Returns a BufferedInputStream that reads from this input stream.
 *
 * @return a BufferedInputStream that reads from this input stream.
 *
 * @since 0.1.0
 * @version 0.1.0
 * @author Nicolas Schmidt &lt;@nsc-de&gt;
 */
inline val InputStream.bufferedStream: BufferedInputStream
    get() = BufferedInputStream(this)

/**
 * Returns a BufferedInputStream that reads from this input stream.
 *
 * @return a BufferedInputStream that reads from this input stream.
 *
 * @since 0.1.1
 * @version 0.1.1
 * @author Nicolas Schmidt &lt;@nsc-de&gt;
 */
inline val InputStream.readFully: ByteArray
    get() {
        val list = mutableListOf<Byte>()
        while (true) {
            val i = read()
            if (i == -1) break
            list.add(i.toByte())
        }
        return list.toByteArray()
    }

/**
 * Byte array to ByteArrayInputStream
 */
inline fun ByteArray.inputStream(): ByteArrayInputStream = ByteArrayInputStream(this)

/**
 * Byte array to DataInputStream
 */
inline fun ByteArray.dataStream(): DataInputStream = DataInputStream(this.inputStream())

/**
 * Byte array to CountingInputStream
 */
inline fun ByteArray.countingStream(): CountingInputStream = CountingInputStream(this.inputStream())

/**
 * Byte array to BufferedInputStream
 * @deprecated A buffered stream of a byte array is useless
 */
inline fun ByteArray.bufferedStream(): BufferedInputStream = BufferedInputStream(this.inputStream())

/**
 * Byte array to ByteArrayInputStream
 */
inline fun List<Byte>.inputStream(): ByteArrayInputStream = ByteArrayInputStream(this.toByteArray())

/**
 * Byte array to DataInputStream
 */
inline fun List<Byte>.dataStream(): DataInputStream = DataInputStream(this.inputStream())

/**
 * Byte array to CountingInputStream
 */
inline fun List<Byte>.countingStream(): CountingInputStream = CountingInputStream(this.inputStream())

/**
 * Byte array to BufferedInputStream
 * @deprecated A buffered stream of a byte array is useless
 */
inline fun List<Byte>.bufferedStream(): BufferedInputStream = BufferedInputStream(this.inputStream())

/**
 * Byte array to ByteArrayInputStream
 */
inline fun CharSequence.byteStream() = ByteArrayInputStream(this.toBytes())

/**
 * Byte array to ByteArrayInputStream
 */
inline fun CharSequence.byteInputStream() = ByteArrayInputStream(this.toBytes())

/**
 * Byte array to DataInputStream
 */
inline fun CharSequence.byteDataStream() = DataInputStream(this.byteInputStream())

/**
 * Byte array to CountingInputStream
 */
inline fun CharSequence.byteCountingStream() = CountingInputStream(this.byteInputStream())

/**
 * Byte array to BufferedInputStream
 * @deprecated A buffered stream of a byte array is useless
 */
inline fun CharSequence.byteBufferedStream() = BufferedInputStream(this.byteInputStream())

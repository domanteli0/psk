package me.domantelio.psk.util

import jakarta.faces.context.FacesContext
import java.nio.ByteBuffer
import java.util.*
import org.slf4j.Logger as Slf4jLogger

public fun UUID.toByteBuffer(): ByteBuffer {
    val bb: ByteBuffer = ByteBuffer.wrap(ByteArray(16))
    bb.putLong(this.mostSignificantBits)
    bb.putLong(this.leastSignificantBits)
    return bb
}

public fun UUID.toByteArray(): ByteArray {
    return this.toByteBuffer().array()
}

public fun ByteBuffer.toUUID(): UUID? {
    throw NotImplementedError()
}

object Collections {
    fun <A> Iterable<A?>.toStr(): String {
        return this
            .map { "${it ?: "__NULL__"}" }
            .fold("") { s1, s2 -> "$s1 | $s2" }
    }

    fun <K, V> Map<K?, V?>.toStr(): String {
        return this
            .map { "${it.key} = ${it.value ?: "__NULL__"}" }
            .fold("") { s1, s2 -> "$s1 | $s2" }
    }
}


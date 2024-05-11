package me.domantelio.psk.util

import java.nio.ByteBuffer
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

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

fun <V> V?.unwrapOr(ifNull: V): V {
    return this ?: ifNull
}

fun LocalDateTime.toSimpleDateStr(): String {
    return this
        .format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
        )
}

object Date {
    fun toSimpleDateStr(self: LocalDateTime): String {
        return self
            .format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
            )
    }
}

object Collections {
    fun <A> Iterable<A?>.toStr(sep: String = "|"): String {
        return this
            .map { "${it ?: "__NULL__"}" }
            .fold("") { s1, s2 -> "$s1 $sep $s2" }
    }

    fun <K, V> Map<K?, V?>.toStr(sep: String = "|"): String {
        return this
            .map { "${it.key} = ${it.value ?: "__NULL__"}" }
            .fold("") { s1, s2 -> "$s1 $sep $s2" }
    }
}


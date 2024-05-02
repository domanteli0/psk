package me.domantelio.psk.util

import java.nio.ByteBuffer
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
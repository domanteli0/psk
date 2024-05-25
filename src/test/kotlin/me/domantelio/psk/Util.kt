package me.domantelio.psk

import jakarta.ws.rs.core.Response

object Util {
    fun<T> Response.getEntity(): T {
        return this.entity as T
    }
}

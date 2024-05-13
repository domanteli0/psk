/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-05-13T00:14:43.572181+03:00
 */
package me.domantelio.psk.mybatis.model

import java.util.*

data class Invoice(
    var id: String? = UUID.randomUUID().toString(),
    var name: String? = null,
    var dateTime: Date? = null,
    var items: List<Item> = listOf(),
) {
    fun getTotalPrice(): Int {
        return items
            .filter { it.price != null }
            .map { it.price!! }
            .fold(0, Int::plus)
    }
}
/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-05-13T00:14:43.560719+03:00
 */
package me.domantelio.psk.mybatis.model

import java.util.*

data class Item(
    var id: String? = UUID.randomUUID().toString(),
    var description: String? = null,
    var name: String? = null,
    var price: Int? = null,
    var invoiceId: String? = null // TODO: @One, https://codingnomads.com/mybatis-nested-relationships-one-many
)
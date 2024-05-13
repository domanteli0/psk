/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-05-13T00:14:43.571491+03:00
 */
package me.domantelio.psk.mybatis.model

import java.util.*

data class Category(
    var id: String? = UUID.randomUUID().toString(),
    var name: String? = null,
    var belongsTo: Invoice? = null
)
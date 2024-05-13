/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-05-13T00:14:43.562642+03:00
 */
package me.domantelio.psk.mybatis.mapper

import java.sql.JDBCType
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object ItemDynamicSqlSupport {
    val item = Item()

    val id = item.id

    val description = item.description

    val name = item.name

    val price = item.price

    val invoiceId = item.invoiceId

    class Item : AliasableSqlTable<Item>("PUBLIC.ITEM", ::Item) {
        val id = column<String>(name = "ID", jdbcType = JDBCType.VARCHAR)

        val description = column<String>(name = "DESC", jdbcType = JDBCType.VARCHAR)

        val name = column<String>(name = "NAME", jdbcType = JDBCType.VARCHAR)

        val price = column<Int>(name = "PRICE", jdbcType = JDBCType.INTEGER)

        val invoiceId = column<String>(name = "INVOICE_ID", jdbcType = JDBCType.VARCHAR)
    }
}
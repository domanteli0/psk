/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-04-20T18:25:27.569942+03:00
 */
package me.domantelio.psk.mybatis.dao

import java.sql.JDBCType
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object ItemDynamicSqlSupport {
    val item = Item()

    val id = item.id

    val name = item.name

    val price = item.price

    val description = item.description

    class Item : AliasableSqlTable<Item>("PUBLIC.ITEM", ::Item) {
        val id = column<ByteArray>(name = "ID", jdbcType = JDBCType.BINARY)

        val name = column<String>(name = "NAME", jdbcType = JDBCType.VARCHAR)

        val price = column<Int>(name = "PRICE", jdbcType = JDBCType.INTEGER)

        val description = column<String>(name = "DESCRIPTION", jdbcType = JDBCType.VARCHAR)
    }
}
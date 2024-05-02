/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-04-29T01:39:49.925213+03:00
 */
package me.domantelio.psk.mybatis.mapper

import java.sql.JDBCType
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object ItemDynamicSqlSupport {
    val item = Item()

    val id = item.id

    val price = item.price

    val desc = item.desc

    val name = item.name

    class Item : AliasableSqlTable<Item>("PUBLIC.ITEM", ::Item) {
        val id = column<ByteArray>(name = "ID", jdbcType = JDBCType.BINARY)

        val price = column<Int>(name = "PRICE", jdbcType = JDBCType.INTEGER)

        val desc = column<String>(name = "DESC", jdbcType = JDBCType.VARCHAR)

        val name = column<String>(name = "NAME", jdbcType = JDBCType.VARCHAR)
    }
}
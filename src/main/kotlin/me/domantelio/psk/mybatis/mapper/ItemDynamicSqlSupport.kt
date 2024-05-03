/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-05-03T03:11:54.09837+03:00
 */
package me.domantelio.psk.mybatis.mapper

import java.sql.JDBCType
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object ItemDynamicSqlSupport {
    val item = Item()

    val id = item.id

    val descr = item.descr

    val name = item.name

    val price = item.price

    class Item : AliasableSqlTable<Item>("PUBLIC.ITEM", ::Item) {
        val id = column<String>(name = "ID", jdbcType = JDBCType.VARCHAR)

        val descr = column<String>(name = "DESCR", jdbcType = JDBCType.VARCHAR)

        val name = column<String>(name = "NAME", jdbcType = JDBCType.VARCHAR)

        val price = column<Int>(name = "PRICE", jdbcType = JDBCType.INTEGER)
    }
}
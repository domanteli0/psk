/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-04-29T01:39:49.932078+03:00
 */
package me.domantelio.psk.mybatis.mapper

import java.sql.JDBCType
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object CategoryDynamicSqlSupport {
    val category = Category()

    val id = category.id

    val name = category.name

    class Category : AliasableSqlTable<Category>("PUBLIC.CATEGORY", ::Category) {
        val id = column<ByteArray>(name = "ID", jdbcType = JDBCType.BINARY)

        val name = column<String>(name = "NAME", jdbcType = JDBCType.VARCHAR)
    }
}
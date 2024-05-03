/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-05-03T03:11:54.117467+03:00
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
        val id = column<String>(name = "ID", jdbcType = JDBCType.VARCHAR)

        val name = column<String>(name = "NAME", jdbcType = JDBCType.VARCHAR)
    }
}
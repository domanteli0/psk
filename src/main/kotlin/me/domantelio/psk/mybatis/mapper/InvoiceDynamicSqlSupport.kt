/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-05-03T03:11:54.118347+03:00
 */
package me.domantelio.psk.mybatis.mapper

import java.sql.JDBCType
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object InvoiceDynamicSqlSupport {
    val invoice = Invoice()

    val id = invoice.id

    val name = invoice.name

    val price = invoice.price

    class Invoice : AliasableSqlTable<Invoice>("PUBLIC.INVOICE", ::Invoice) {
        val id = column<String>(name = "ID", jdbcType = JDBCType.VARCHAR)

        val name = column<String>(name = "NAME", jdbcType = JDBCType.VARCHAR)

        val price = column<Int>(name = "PRICE", jdbcType = JDBCType.INTEGER)
    }
}
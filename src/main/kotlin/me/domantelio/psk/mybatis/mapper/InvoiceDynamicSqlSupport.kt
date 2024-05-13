/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-05-13T00:14:43.572299+03:00
 */
package me.domantelio.psk.mybatis.mapper

import java.sql.JDBCType
import java.util.Date
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object InvoiceDynamicSqlSupport {
    val invoice = Invoice()

    val id = invoice.id

    val name = invoice.name

    val dateTime = invoice.dateTime

    class Invoice : AliasableSqlTable<Invoice>("PUBLIC.INVOICE", ::Invoice) {
        val id = column<String>(name = "ID", jdbcType = JDBCType.VARCHAR)

        val name = column<String>(name = "NAME", jdbcType = JDBCType.VARCHAR)

        val dateTime = column<Date>(name = "DATE_TIME", jdbcType = JDBCType.TIMESTAMP)
    }
}
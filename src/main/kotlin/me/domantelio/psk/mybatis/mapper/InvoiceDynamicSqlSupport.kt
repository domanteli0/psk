/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-04-29T01:39:49.932618+03:00
 */
package me.domantelio.psk.mybatis.mapper

import java.sql.JDBCType
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object InvoiceDynamicSqlSupport {
    val invoice = Invoice()

    val id = invoice.id

    val price = invoice.price

    val name = invoice.name

    class Invoice : AliasableSqlTable<Invoice>("PUBLIC.INVOICE", ::Invoice) {
        val id = column<ByteArray>(name = "ID", jdbcType = JDBCType.BINARY)

        val price = column<Int>(name = "PRICE", jdbcType = JDBCType.INTEGER)

        val name = column<String>(name = "NAME", jdbcType = JDBCType.VARCHAR)
    }
}
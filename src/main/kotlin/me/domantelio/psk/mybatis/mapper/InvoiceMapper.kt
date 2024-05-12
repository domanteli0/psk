/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-05-12T19:56:53.492092+03:00
 */
package me.domantelio.psk.mybatis.mapper

import me.domantelio.psk.mybatis.mapper.InvoiceDynamicSqlSupport.dateTime
import me.domantelio.psk.mybatis.mapper.InvoiceDynamicSqlSupport.id
import me.domantelio.psk.mybatis.mapper.InvoiceDynamicSqlSupport.invoice
import me.domantelio.psk.mybatis.mapper.InvoiceDynamicSqlSupport.name
import me.domantelio.psk.mybatis.model.Invoice
import org.apache.ibatis.annotations.InsertProvider
import org.mybatis.cdi.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter
import org.mybatis.dynamic.sql.util.kotlin.CountCompleter
import org.mybatis.dynamic.sql.util.kotlin.DeleteCompleter
import org.mybatis.dynamic.sql.util.kotlin.KotlinUpdateBuilder
import org.mybatis.dynamic.sql.util.kotlin.SelectCompleter
import org.mybatis.dynamic.sql.util.kotlin.UpdateCompleter
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.countFrom
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.deleteFrom
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.insert
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.insertMultipleWithGeneratedKeys
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.selectDistinct
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.selectList
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.selectOne
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.update
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper

@Mapper
interface InvoiceMapper : CommonCountMapper, CommonDeleteMapper, CommonUpdateMapper {
    @InsertProvider(type=SqlProviderAdapter::class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="row.id")
    fun insert(insertStatement: InsertStatementProvider<Invoice>): Int

    @InsertProvider(type = SqlProviderAdapter::class, method = "insertMultipleWithGeneratedKeys")
    @Options(useGeneratedKeys=true,keyProperty="records.id")
    fun insertMultiple(@Param("insertStatement") insertStatement: String, @Param("records") records: List<Invoice>): Int

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="InvoiceResult", value = [
        Result(column="ID", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        Result(column="NAME", property="name", jdbcType=JdbcType.VARCHAR),
        Result(column="DATE_TIME", property="dateTime", jdbcType=JdbcType.TIMESTAMP)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<Invoice>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("InvoiceResult")
    fun selectOne(selectStatement: SelectStatementProvider): Invoice?
}

fun InvoiceMapper.count(completer: CountCompleter) =
    countFrom(this::count, invoice, completer)

fun InvoiceMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, invoice, completer)

fun InvoiceMapper.deleteByPrimaryKey(id_: String) =
    delete {
        where { id isEqualTo id_ }
    }

fun InvoiceMapper.insert(row: Invoice) =
    insert(this::insert, row, invoice) {
        map(name) toProperty "name"
        map(dateTime) toProperty "dateTime"
    }

fun InvoiceMapper.insertMultiple(records: Collection<Invoice>) =
    insertMultipleWithGeneratedKeys(this::insertMultiple, records, invoice) {
        map(name) toProperty "name"
        map(dateTime) toProperty "dateTime"
    }

fun InvoiceMapper.insertMultiple(vararg records: Invoice) =
    insertMultiple(records.toList())

fun InvoiceMapper.insertSelective(row: Invoice) =
    insert(this::insert, row, invoice) {
        map(name).toPropertyWhenPresent("name", row::name)
        map(dateTime).toPropertyWhenPresent("dateTime", row::dateTime)
    }

private val columnList = listOf(id, name, dateTime)

fun InvoiceMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, invoice, completer)

fun InvoiceMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, invoice, completer)

fun InvoiceMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, invoice, completer)

fun InvoiceMapper.selectByPrimaryKey(id_: String) =
    selectOne {
        where { id isEqualTo id_ }
    }

fun InvoiceMapper.update(completer: UpdateCompleter) =
    update(this::update, invoice, completer)

fun KotlinUpdateBuilder.updateAllColumns(row: Invoice) =
    apply {
        set(name) equalToOrNull row::name
        set(dateTime) equalToOrNull row::dateTime
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(row: Invoice) =
    apply {
        set(name) equalToWhenPresent row::name
        set(dateTime) equalToWhenPresent row::dateTime
    }

fun InvoiceMapper.updateByPrimaryKey(row: Invoice) =
    update {
        set(name) equalToOrNull row::name
        set(dateTime) equalToOrNull row::dateTime
        where { id isEqualTo row.id!! }
    }

fun InvoiceMapper.updateByPrimaryKeySelective(row: Invoice) =
    update {
        set(name) equalToWhenPresent row::name
        set(dateTime) equalToWhenPresent row::dateTime
        where { id isEqualTo row.id!! }
    }
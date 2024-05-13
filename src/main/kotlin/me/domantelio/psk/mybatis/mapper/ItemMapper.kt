/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-05-13T00:14:43.563649+03:00
 */
package me.domantelio.psk.mybatis.mapper

import me.domantelio.psk.mybatis.mapper.ItemDynamicSqlSupport.description
import me.domantelio.psk.mybatis.mapper.ItemDynamicSqlSupport.id
import me.domantelio.psk.mybatis.mapper.ItemDynamicSqlSupport.invoiceId
import me.domantelio.psk.mybatis.mapper.ItemDynamicSqlSupport.item
import me.domantelio.psk.mybatis.mapper.ItemDynamicSqlSupport.name
import me.domantelio.psk.mybatis.mapper.ItemDynamicSqlSupport.price
import me.domantelio.psk.mybatis.model.Item
import org.apache.ibatis.annotations.InsertProvider
import org.mybatis.cdi.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.SqlBuilder.isIn
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
interface ItemMapper : CommonCountMapper, CommonDeleteMapper, CommonUpdateMapper {
    @InsertProvider(type=SqlProviderAdapter::class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "item.id")
    fun insert(insertStatement: InsertStatementProvider<Item>): Int

    @InsertProvider(type = SqlProviderAdapter::class, method = "insertMultipleWithGeneratedKeys")
    @Options(useGeneratedKeys=true, keyProperty="records.id")
    fun insertMultiple(@Param("insertStatement") insertStatement: String, @Param("records") records: List<Item>): Int

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="ItemResult", value = [
        Result(column="ID", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        Result(column="DESC", property="description", jdbcType=JdbcType.VARCHAR),
        Result(column="NAME", property="name", jdbcType=JdbcType.VARCHAR),
        Result(column="PRICE", property="price", jdbcType=JdbcType.INTEGER),
        Result(column="INVOICE_ID", property="invoiceId", jdbcType=JdbcType.VARCHAR)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<Item>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("ItemResult")
    fun selectOne(selectStatement: SelectStatementProvider): Item?
}

fun ItemMapper.count(completer: CountCompleter) =
    countFrom(this::count, item, completer)

fun ItemMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, item, completer)

fun ItemMapper.deleteByPrimaryKey(id_: String) =
    delete {
        where { id isEqualTo id_ }
    }

fun ItemMapper.insert(row: Item) =
    insert(this::insert, row, item) {
        map(id) toProperty "id"
        map(description) toProperty "description"
        map(name) toProperty "name"
        map(price) toProperty "price"
        map(invoiceId) toProperty "invoiceId"
    }

fun ItemMapper.insertMultiple(records: Collection<Item>) =
    insertMultipleWithGeneratedKeys(this::insertMultiple, records, item) {
        map(id) toProperty "id"
        map(description) toProperty "description"
        map(name) toProperty "name"
        map(price) toProperty "price"
        map(invoiceId) toProperty "invoiceId"
    }

fun ItemMapper.insertMultiple(vararg records: Item) =
    insertMultiple(records.toList())

fun ItemMapper.insertSelective(row: Item) =
    insert(this::insert, row, item) {
        map(id).toPropertyWhenPresent("id", row::id)
        map(description).toPropertyWhenPresent("description", row::description)
        map(name).toPropertyWhenPresent("name", row::name)
        map(price).toPropertyWhenPresent("price", row::price)
        map(invoiceId).toPropertyWhenPresent("invoiceId", row::invoiceId)
    }

private val columnList = listOf(id, description, name, price, invoiceId)

fun ItemMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, item, completer)

fun ItemMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, item, completer)

fun ItemMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, item, completer)

fun ItemMapper.selectByPrimaryKey(id_: String) =
    selectOne {
        where { id isEqualTo id_ }
    }

fun ItemMapper.update(completer: UpdateCompleter): Int =
    update(this::update, item, completer)

fun ItemMapper.setInvoiceId(itemId: String, invoiceId_: String): Int =
    setInvoiceId(listOf(itemId), invoiceId_)

fun ItemMapper.setInvoiceId(ids: Collection<String>, invoiceId_: String): Int =
    update {
        set(invoiceId) equalTo invoiceId_
        where { isIn(id, ids) }
    }

fun KotlinUpdateBuilder.updateAllColumns(row: Item) =
    apply {
        set(description) equalToOrNull row::description
        set(name) equalToOrNull row::name
        set(price) equalToOrNull row::price
        set(invoiceId) equalToOrNull row::invoiceId
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(row: Item) =
    apply {
        set(description) equalToWhenPresent row::description
        set(name) equalToWhenPresent row::name
        set(price) equalToWhenPresent row::price
        set(invoiceId) equalToWhenPresent row::invoiceId
    }

fun ItemMapper.updateByPrimaryKey(row: Item) =
    update {
        set(description) equalToOrNull row::description
        set(name) equalToOrNull row::name
        set(price) equalToOrNull row::price
        set(invoiceId) equalToOrNull row::invoiceId
        where { id isEqualTo row.id!! }
    }

fun ItemMapper.updateByPrimaryKeySelective(row: Item) =
    update {
        set(description) equalToWhenPresent row::description
        set(name) equalToWhenPresent row::name
        set(price) equalToWhenPresent row::price
        set(invoiceId) equalToWhenPresent row::invoiceId
        where { id isEqualTo row.id!! }
    }
/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-05-13T00:14:43.571748+03:00
 */
package me.domantelio.psk.mybatis.mapper

import me.domantelio.psk.mybatis.mapper.CategoryDynamicSqlSupport.category
import me.domantelio.psk.mybatis.mapper.CategoryDynamicSqlSupport.id
import me.domantelio.psk.mybatis.mapper.CategoryDynamicSqlSupport.name
import me.domantelio.psk.mybatis.model.Category
import me.domantelio.psk.mybatis.model.Invoice
import org.apache.ibatis.annotations.*
import org.apache.ibatis.mapping.FetchType
import org.apache.ibatis.type.JdbcType
import org.mybatis.cdi.Mapper
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper


@Mapper
interface CategoryMapper : CommonCountMapper, CommonDeleteMapper, CommonUpdateMapper {
    @InsertProvider(type=SqlProviderAdapter::class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="row.id")
    fun insert(insertStatement: InsertStatementProvider<Category>): Int

    @InsertProvider(type = SqlProviderAdapter::class, method = "insertMultipleWithGeneratedKeys")
    @Options(useGeneratedKeys=true,keyProperty="records.id")
    fun insertMultiple(@Param("insertStatement") insertStatement: String, @Param("records") records: List<Category>): Int

    @SelectProvider(type = SqlProviderAdapter::class, method="select")
    @Results(id="CategoryResult", value = [
        Result(column="ID", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        Result(column="NAME", property="name", jdbcType=JdbcType.VARCHAR),
        Result(column="INVOICE_ID", property="belongsTo", javaType = Invoice::class,
            one = One(
                select = "me.domantelio.psk.mybatis.mapper.InvoiceMapper.selectById",
                fetchType = FetchType.EAGER
            )
        )
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<Category>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("CategoryResult")
    fun selectOne(selectStatement: SelectStatementProvider): Category?

    @Select("SELECT c.* FROM category c INNER JOIN invoice_category ic ON c.id = ic.CATEGORIES_ID WHERE ic.BELONGSTO_ID = #{invoiceId}")
    @ResultMap("CategoryResult")
    fun selectByInvoiceId(invoiceId: String): List<Category>
}

fun CategoryMapper.count(completer: CountCompleter) =
    countFrom(this::count, category, completer)

fun CategoryMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, category, completer)

fun CategoryMapper.deleteByPrimaryKey(id_: String) =
    delete {
        where { id isEqualTo id_ }
    }

fun CategoryMapper.insert(row: Category) =
    insert(this::insert, row, category) {
        map(id) toProperty "id"
        map(name) toProperty "name"
    }

fun CategoryMapper.insertMultiple(records: Collection<Category>) =
    insertMultipleWithGeneratedKeys(this::insertMultiple, records, category) {
        map(id) toProperty "id"
        map(name) toProperty "name"
    }

fun CategoryMapper.insertMultiple(vararg records: Category) =
    insertMultiple(records.toList())

fun CategoryMapper.insertSelective(row: Category) =
    insert(this::insert, row, category) {
        map(id).toPropertyWhenPresent("id", row::id)
        map(name).toPropertyWhenPresent("name", row::name)
    }

private val columnList = listOf(id, name)

fun CategoryMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, category, completer)

fun CategoryMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, category, completer)

fun CategoryMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, category, completer)

fun CategoryMapper.selectByPrimaryKey(id_: String) =
    selectOne {
        where { id isEqualTo id_ }
    }

fun CategoryMapper.update(completer: UpdateCompleter) =
    update(this::update, category, completer)

fun KotlinUpdateBuilder.updateAllColumns(row: Category) =
    apply {
        set(name) equalToOrNull row::name
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(row: Category) =
    apply {
        set(name) equalToWhenPresent row::name
    }

fun CategoryMapper.updateByPrimaryKey(row: Category) =
    update {
        set(name) equalToOrNull row::name
        where { id isEqualTo row.id!! }
    }

fun CategoryMapper.updateByPrimaryKeySelective(row: Category) =
    update {
        set(name) equalToWhenPresent row::name
        where { id isEqualTo row.id!! }
    }
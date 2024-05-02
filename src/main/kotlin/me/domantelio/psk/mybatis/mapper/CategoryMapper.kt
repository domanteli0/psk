/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2024-04-29T01:39:49.932168+03:00
 */
package me.domantelio.psk.mybatis.mapper

import me.domantelio.psk.mybatis.mapper.CategoryDynamicSqlSupport.category
import me.domantelio.psk.mybatis.mapper.CategoryDynamicSqlSupport.id
import me.domantelio.psk.mybatis.mapper.CategoryDynamicSqlSupport.name
import me.domantelio.psk.mybatis.model.Category
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
interface CategoryMapper : CommonCountMapper, CommonDeleteMapper, CommonUpdateMapper {
    @InsertProvider(type=SqlProviderAdapter::class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="row.id")
    fun insert(insertStatement: InsertStatementProvider<Category>): Int

    @InsertProvider(type = SqlProviderAdapter::class, method = "insertMultipleWithGeneratedKeys")
    @Options(useGeneratedKeys=true,keyProperty="records.id")
    fun insertMultiple(@Param("insertStatement") insertStatement: String, @Param("records") records: List<Category>): Int

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="CategoryResult", value = [
        Result(column="ID", property="id", jdbcType=JdbcType.BINARY, id=true),
        Result(column="NAME", property="name", jdbcType=JdbcType.VARCHAR)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<Category>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("CategoryResult")
    fun selectOne(selectStatement: SelectStatementProvider): Category?
}

fun CategoryMapper.count(completer: CountCompleter) =
    countFrom(this::count, category, completer)

fun CategoryMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, category, completer)

fun CategoryMapper.deleteByPrimaryKey(id_: ByteArray) =
    delete {
        where { id isEqualTo id_ }
    }

fun CategoryMapper.insert(row: Category) =
    insert(this::insert, row, category) {
        map(name) toProperty "name"
    }

fun CategoryMapper.insertMultiple(records: Collection<Category>) =
    insertMultipleWithGeneratedKeys(this::insertMultiple, records, category) {
        map(name) toProperty "name"
    }

fun CategoryMapper.insertMultiple(vararg records: Category) =
    insertMultiple(records.toList())

fun CategoryMapper.insertSelective(row: Category) =
    insert(this::insert, row, category) {
        map(name).toPropertyWhenPresent("name", row::name)
    }

private val columnList = listOf(id, name)

fun CategoryMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, category, completer)

fun CategoryMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, category, completer)

fun CategoryMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, category, completer)

fun CategoryMapper.selectByPrimaryKey(id_: ByteArray) =
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
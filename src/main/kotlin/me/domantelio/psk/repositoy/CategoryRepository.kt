package me.domantelio.psk.repositoy

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import me.domantelio.psk.mybatis.mapper.*
import me.domantelio.psk.mybatis.mapper.CategoryDynamicSqlSupport.id
import me.domantelio.psk.mybatis.mapper.ItemDynamicSqlSupport.name
import me.domantelio.psk.mybatis.model.Category
import org.mybatis.dynamic.sql.select.SelectDSLCompleter.allRows
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

@ApplicationScoped
open class CategoryRepository() {

    private val logger: Logger = LoggerFactory.getLogger(javaClass.name)

    @Inject
    private lateinit var mapper: CategoryMapper

    @Transactional
    open fun createCategory(category: Category) {
        mapper.insert(category)
        logger.info("Created Category $category")
    }

    open fun updateCategory(category: Category) {
        mapper.updateByPrimaryKey(category)
    }

    @Transactional
    open fun deleteCategory(categoryId: String) {
        mapper.delete { where { id.isEqualTo(categoryId) } }.let {
            if (it == 1) {
                logger.debug("Deleted Category with id [$categoryId]")
            } else {
                logger.debug("No Category to delete with id [$categoryId]")
            }
        }
    }

    fun findCategoryById(id_: UUID): Category? {
        return mapper.selectByPrimaryKey(id_.toString())
    }

    fun findCategoryByName(name_: String): Category? {
        return mapper.selectOne {
            where { name.isEqualTo(name_) }
        }
    }

    fun findAllCategories(): List<Category> {
        return mapper.select {
            allRows()
        }
    }
}

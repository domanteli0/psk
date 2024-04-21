package me.domantelio.psk.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Model
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.persistence.Query
import jakarta.transaction.Transactional
import jakarta.ws.rs.WebApplicationException
import me.domantelio.psk.entity.Category
import java.util.*
import java.util.logging.Logger

@ApplicationScoped
open class CategoryService() {
    @PersistenceContext
    private lateinit var em: EntityManager

    @Transactional
    open fun createCategory(category: Category) {
        em.persist(category)
        LOGGER.info("Created Category $category")
    }

    @Transactional
    open fun updateCategory(category: Category) {
        em.merge(category)
        LOGGER.info("Updated category $category")
    }

    @Transactional
    open fun deleteCategory(categoryId: UUID) {
        val c: Category = findCategoryById(categoryId)
        em.remove(c)
        LOGGER.info("Deleted Category with id [$categoryId]")
    }

    fun findCategoryById(id: UUID): Category {
        val category: Category = em.find(Category::class.java, id)
            ?: throw WebApplicationException("Category with id of $id does not exist.", 404)
        return category
    }

    fun findAllCategories(): List<Category> {
        val query: Query = em.createQuery("SELECT c FROM Category as c", Category::class.java)
        return query.resultList as MutableList<Category>
    }

    fun findCategoryByName(name: String): Category {
        val query: Query = em
            .createQuery("SELECT c FROM Category as c WHERE c.name = :name")
        query.setParameter("name", name)
        return query.singleResult as Category
    }

    companion object {
        private val LOGGER: Logger = Logger.getLogger(CategoryService::class.java.name)
    }
}

package me.domantelio.psk.repositoy

import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.persistence.Query
import jakarta.transaction.Transactional
import me.domantelio.psk.entity.Category
import java.util.*
import java.util.logging.Logger

@ApplicationScoped
open class CategoryRepository() {
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
        val c: Category = findCategoryById(categoryId) ?: run {
            LOGGER.warning("No Category to delete with id [$categoryId]")
            return
        }
        em.remove(c)
        LOGGER.info("Deleted Category with id [$categoryId]")
    }

    fun findCategoryById(id: UUID): Category? {
        val category: Category? = em.find(Category::class.java, id)
        return category
    }

    fun findCategoryByName(name: String): Category? {
        return em.createNamedQuery("Category.findByName")
            .setParameter("name", name)
            .singleResult as Category?;
    }

    fun findAllCategories(): List<Category> {
        val query: Query = em.createQuery("SELECT c FROM Category as c", Category::class.java)
        return query.resultList as MutableList<Category>
    }

    companion object {
        private val LOGGER: Logger = Logger.getLogger(CategoryRepository::class.java.name)
    }
}

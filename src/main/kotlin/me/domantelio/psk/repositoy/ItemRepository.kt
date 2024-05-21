package me.domantelio.psk.repositoy

import jakarta.enterprise.context.ApplicationScoped
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.persistence.Query
import jakarta.transaction.Transactional
import jakarta.ws.rs.WebApplicationException
import me.domantelio.psk.entity.Item
import java.util.*
import java.util.logging.Logger

@ApplicationScoped
open class ItemRepository() {
    @PersistenceContext
    private lateinit var em: EntityManager

    @Transactional
    open fun createItem(item: Item) {
        em.persist(item)
        logger.info("Created Item $item")
    }

    @Transactional
    open fun updateItem(item: Item) {
        em.merge(item)
        logger.info("Updated item$item")
    }

    @Transactional
    open fun deleteItem(itemId: UUID) {
        val c: Item = findItemById(itemId)
        em.remove(c)
        logger.info("Deleted Item with id$itemId")
    }

    fun findItemById(id: UUID): Item {
        val item: Item = em.find(Item::class.java, id)
            ?: throw WebApplicationException("Item with id of $id does not exist.", 404)
        return item
    }

    fun findAllItems(): List<Item> {
        val query: Query = em.createQuery("SELECT c FROM Item c")
        return query.resultList as MutableList<Item>
    }

    fun findItemsByName(name: String): List<Item> {
        val query: Query = em
            .createQuery("SELECT c FROM Item c WHERE c.name LIKE :name")
        query.setParameter("name", name)
        return query.resultList as MutableList<Item>
    }

    companion object {
        private val logger: Logger = Logger.getLogger(ItemRepository::class.java.name)
    }
}

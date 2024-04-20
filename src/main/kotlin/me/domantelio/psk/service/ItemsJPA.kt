package me.domantelio.psk.service

import jakarta.enterprise.inject.Model
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.persistence.Query
import jakarta.transaction.Transactional
import jakarta.ws.rs.WebApplicationException
import me.domantelio.psk.entity.Item
import java.util.*
import java.util.logging.Logger

@Model
open class ItemService {
    @PersistenceContext
    var em: EntityManager? = null

    @Transactional
    open fun createItem(item: Item) {
        em!!.persist(item)
        LOGGER.info("Created Item $item")
    }

    @Transactional
    open fun updateItem(item: Item) {
        var itemToUpdate: Item = findItemById(item.id)
        itemToUpdate.name = item.name
        itemToUpdate.price = item.price
        LOGGER.info("Updated item$item")
    }

    @Transactional
    open fun deleteItem(itemId: UUID) {
        val c: Item = findItemById(itemId)
        em!!.remove(c)
        LOGGER.info("Deleted Item with id$itemId")
    }

    fun findItemById(id: UUID): Item {
        val item: Item = em?.find(Item::class.java, id)
            ?: throw WebApplicationException("Item with id of $id does not exist.", 404)
        return item
    }

    fun findAllItems(): List<Item> {
        val query: Query = em!!.createQuery("SELECT c FROM Item c")
        return query.getResultList() as MutableList<Item>
    }

    fun findItemByName(name: String): Item {
        val query: Query = em!!
            .createQuery("SELECT c FROM Item c WHERE c.name = :name")
        query.setParameter("name", name)
        return query.getSingleResult() as Item
    }

    companion object {
        private val LOGGER: Logger = Logger.getLogger(ItemService::class.java.name)
    }
}

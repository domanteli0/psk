package me.domantelio.psk.repositoy

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.persistence.Query
import jakarta.transaction.Transactional
import jakarta.ws.rs.WebApplicationException
import me.domantelio.psk.mybatis.mapper.*
import me.domantelio.psk.mybatis.mapper.ItemDynamicSqlSupport.name
import me.domantelio.psk.mybatis.model.Invoice
import me.domantelio.psk.mybatis.model.Item
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

@ApplicationScoped
open class ItemRepository() {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @Inject
    private lateinit var mapper: ItemMapper

    @Transactional
    open fun createItem(item: Item) {
        mapper.insert(item)
        logger.info("Created Item $item")
    }

    @Transactional
    open fun updateItem(item: Item) {
        mapper.updateByPrimaryKey(item)
        logger.info("Updated item$item")
    }

    @Transactional
    open fun deleteItem(itemId: UUID) {
        mapper.deleteByPrimaryKey(itemId.toString())
        logger.info("Deleted Item with id$itemId")
    }

    fun findItemById(id: UUID): Item? {
        return mapper.selectByPrimaryKey(id.toString())
    }

    fun findAllItems(): List<Item> {
        return mapper.select { allRows() }
    }

    fun findItemByName(name_: String): List<Item> {
        return mapper.select {
            where { name.isEqualTo(name_) }
        }
    }

    open fun attachTo(item: Item, invoice: Invoice) {
        mapper.setInvoiceId(item.id!!, invoice.id!!)
    }
}

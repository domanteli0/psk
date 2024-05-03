 package me.domantelio.psk.faces

import jakarta.annotation.PostConstruct
import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import jakarta.inject.Named
import jakarta.transaction.Transactional
import me.domantelio.psk.entity.Item
import me.domantelio.psk.service.ItemService
import java.io.Serializable
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Named
@RequestScoped
open class IndexItemFace(
    private var allItems: List<Item> = listOf(),
    private var itemToCreate: Item = Item(),
) : Serializable {
    public constructor() : this(listOf(), Item())

    @Inject
    private lateinit var service: ItemService

    // @Inject
    // private lateinit var logger: org.slf4j.Logger

    private var logger: Logger = LoggerFactory.getLogger(IndexItemFace::class.java)

    public fun getAllItems(): List<Item> { return allItems }
    public fun setAllItems(items: List<Item>) { this.allItems = items }

    public fun getItemToCreate(): Item { return itemToCreate }
    public fun setItemToCreate(itemToCreate: Item) { this.itemToCreate = itemToCreate }

    @PostConstruct
    fun init() {
        this.loadAllItems()
    }

    private fun loadAllItems() {
        this.allItems = service.findAllItems()
    }

    @Transactional
    open fun createItem(): String {
        logger.info("createdItem: ${itemToCreate}")
        println("createdItem: $itemToCreate")

        service.createItem(itemToCreate)
        return "/myBatis/items?faces-redirect=true"
    }
}

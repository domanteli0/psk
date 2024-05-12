package me.domantelio.psk.faces

import jakarta.annotation.PostConstruct
import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import jakarta.inject.Named
import jakarta.transaction.Transactional
import me.domantelio.psk.mybatis.model.*
import me.domantelio.psk.repositoy.ItemRepository
import java.io.Serializable
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Named
@RequestScoped
open class IndexItemFace(
    var allItems: List<Item> = listOf(),
    var itemToCreate: Item = Item(),
) : Serializable {
    public constructor() : this(listOf(), Item())

    @Inject
    private lateinit var service: ItemRepository

    private var logger: Logger = LoggerFactory.getLogger(IndexItemFace::class.java)

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

        service.createItem(itemToCreate)
        return "/myBatis/items?faces-redirect=true"
    }
}

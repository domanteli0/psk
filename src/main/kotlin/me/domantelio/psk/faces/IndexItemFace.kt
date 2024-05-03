 package me.domantelio.psk.faces

import jakarta.annotation.PostConstruct
import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import jakarta.inject.Named
import jakarta.transaction.Transactional
import me.domantelio.psk.mybatis.mapper.ItemMapper
import me.domantelio.psk.mybatis.mapper.insert
import me.domantelio.psk.mybatis.mapper.select
import me.domantelio.psk.mybatis.model.Item
import me.domantelio.psk.util.toByteArray
import java.io.Serializable
import java.nio.ByteBuffer
import java.util.*
import org.slf4j.Logger

@Named
@RequestScoped
open class IndexItemFace(
    private var allItems: List<Item> = listOf(),
    private var itemToCreate: Item = Item(),
) : Serializable {
    public constructor() : this(listOf(), Item())

    @Inject @Named("myItemMapper")
    private lateinit var itemMapper: ItemMapper

//    @Inject
//    private lateinit var logger: Logger

    public fun getAllItems(): List<Item> { return allItems }
    public fun setAllItems(items: List<Item>) { this.allItems = items }

    public fun getItemToCreate(): Item { return itemToCreate }
    public fun setItemToCreate(itemToCreate: Item) { this.itemToCreate = itemToCreate }

    @PostConstruct
    fun init() {
        this.loadAllItems()
    }

    private fun loadAllItems() {
        this.allItems = itemMapper.select {}
    }

    @Transactional
    open fun createItem(): String {
//        logger.debug("")
        val toCreate = itemToCreate.copy(id =  UUID.randomUUID().toString())
        itemMapper.insert(toCreate)
        return "/myBatis/items?faces-redirect=true"
    }
}

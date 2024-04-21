 package me.domantelio.psk.face

import jakarta.annotation.PostConstruct
import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import jakarta.inject.Named
import jakarta.transaction.Transactional
import lombok.NoArgsConstructor
import me.domantelio.psk.mybatis.mapper.ItemMapper
import me.domantelio.psk.mybatis.mapper.insert
import me.domantelio.psk.mybatis.mapper.select
import me.domantelio.psk.mybatis.model.Item
import java.io.Serializable
import java.nio.ByteBuffer
import java.util.*

@NoArgsConstructor
@Named
@RequestScoped
open class ItemsMyBatis(
    private var allItems: List<Item> = listOf(),
    private var itemToCreate: Item = Item(),
) : Serializable {
    public constructor() : this(listOf(), Item())

    @Inject @Named("myItemMapper")
    private lateinit var itemMapper: ItemMapper

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
        val uuid = UUID.randomUUID()
        val bb: ByteBuffer = ByteBuffer.wrap(ByteArray(16))
        bb.putLong(uuid.mostSignificantBits)
        bb.putLong(uuid.leastSignificantBits)

        itemToCreate.id = bb.array()
        itemMapper.insert(itemToCreate)
        return "/myBatis/items?faces-redirect=true"
    }
}

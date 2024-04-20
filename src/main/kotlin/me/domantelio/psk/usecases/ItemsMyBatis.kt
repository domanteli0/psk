 package me.domantelio.psk.usacases

import jakarta.annotation.PostConstruct
import jakarta.enterprise.inject.Model
import jakarta.inject.Inject
import lombok.Getter
import lombok.Setter
import jakarta.transaction.Transactional

import me.domantelio.psk.mybatis.dao.ItemMapper
import me.domantelio.psk.mybatis.model.Item

@Model
class ItemsMyBatis {
    @Inject
    lateinit private var itemMapper: ItemMapper

    @Getter
    private var allItems: List<Item>? = null

    @Getter
    @Setter
    private val itemToCreate: Item = Item()

    @PostConstruct
    fun init() {
        this.loadAllItems()
    }

    private fun loadAllItems() {
        this.allItems = itemMapper.selectAll()
    }

    @Transactional
    fun createItem(): String {
        itemMapper.insert(itemToCreate)
        return "/myBatis/items?faces-redirect=true"
    }
}

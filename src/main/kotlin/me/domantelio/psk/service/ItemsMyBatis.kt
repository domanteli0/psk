 package me.domantelio.psk.service

import jakarta.annotation.PostConstruct
import jakarta.annotation.Resource
import jakarta.enterprise.inject.Model
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import lombok.Getter
import lombok.Setter
import me.domantelio.psk.mybatis.dao.ItemDynamicSqlSupport
import me.domantelio.psk.mybatis.dao.ItemMapper
import me.domantelio.psk.mybatis.dao.insert
import me.domantelio.psk.mybatis.dao.select
import me.domantelio.psk.mybatis.model.Item
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider
import org.mybatis.dynamic.sql.render.RenderingStrategies
import org.mybatis.dynamic.sql.render.RenderingStrategy
import org.mybatis.dynamic.sql.util.kotlin.model.insert
import org.mybatis.dynamic.sql.util.kotlin.model.insertInto
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.selectList

 @Model
open class ItemsMyBatis {
    @Resource
    private lateinit var itemMapper: ItemMapper

    var allItems: List<Item>? = null

    @Getter
    @Setter
    private val itemToCreate: Item = Item()

    @PostConstruct
    fun init() {
        this.loadAllItems()
    }

     private fun loadAllItems() {
         this.allItems = itemMapper.select {}
     }

    @Transactional
    open fun createItem(): String {
        itemMapper.insert(itemToCreate)
        return "/myBatis/items?faces-redirect=true"
    }
}

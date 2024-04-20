package me.domantelio.psk.persistance

import me.domantelio.psk.entity.Item
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.persistence.EntityManager
import java.util.*

@ApplicationScoped
class ItemsDAO {
    @Inject
    private var entityManager: EntityManager? = null

    fun persist(item: Item) = entityManager!!.persist(item)

    fun findOne(id: UUID): Item = entityManager!!.find(Item::class.java, id)

    fun update(item: Item): Item = entityManager!!.merge(item)
}
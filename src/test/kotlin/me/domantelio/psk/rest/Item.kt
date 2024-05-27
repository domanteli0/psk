package me.domantelio.psk.rest

import jakarta.inject.Inject
import jakarta.persistence.EntityManager
import jakarta.persistence.Query
import jakarta.transaction.Transactional
import me.domantelio.psk.EntityManagerFactoryProducer
import me.domantelio.psk.Util.getEntity
import me.domantelio.psk.entity.Item
import me.domantelio.psk.repositoy.ItemRepository
import org.assertj.core.api.Assertions.assertThat
import org.jboss.weld.junit5.WeldInitiator
import org.jboss.weld.junit5.WeldSetup
import org.jboss.weld.junit5.auto.AddBeanClasses
import org.jboss.weld.junit5.auto.EnableAutoWeld
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@EnableAutoWeld
@AddBeanClasses(ItemRepository::class, Item::class, EntityManagerFactoryProducer::class, ItemController::class)
class ItemControllerTests {

    @WeldSetup
    var weld: WeldInitiator = WeldInitiator
        .from(WeldInitiator.createWeld())
        .build()

    @Inject
    lateinit var em: EntityManager

    @Inject
    private lateinit var itemRepo: ItemRepository

    @Inject
    private lateinit var itemController: ItemController

    @BeforeEach
    fun setUp() {
        itemRepo.setEm(em)
    }

    @Test
    fun testWorks() {
        assertNotNull(itemRepo)
    }

    @Test
    fun repoWorks() {
        itemRepo.createItem(Item().apply { name = "test-item" })
        val item = itemRepo.findItemsByName("test-item").first()

        println(item)
        assertThat(item.name).isEqualTo("test-item")
    }

    @Test
    fun emWorks() {
        em.transaction.begin()
        em.persist(Item().apply { name = "test-item" })
        em.transaction.commit()

        val query: Query = em.createQuery("SELECT i FROM Item i")
        assertThat((query.resultList as List<Item>).first().name).isEqualTo("test-item")
    }

//    @Test
//    @Transactional
//    fun controllerWorks() {
//        val item = itemController.create(Item().apply { name = "test-item" }).getEntity<Item>()
//
//        itemController.update(item.id!!, item.apply { name = "updated-test-item"; version = 1 })
//
//        assertThat(item.name).isEqualTo("updated-test-item")
//    }

//    @Test
//    fun optLock(){
//        em.transaction.begin()
//
//        em.persist(Item().apply { name = "test-item" })
//
//        em.transaction.commit()
//    }

//    @Test
//    fun optimisticLock() {
//        val item = itemController.create(Item().apply { name = "test-item" }).getEntity<Item>()
//
//        itemController.update(item.id!!, item.apply { name = "updated-test-item" })
//
//        itemController.update(item.id!!, item.apply { name = "updated-test-item"; version = 100 })
//
//        assertThat(item.name).isEqualTo("updated-test-item")
//    }

}

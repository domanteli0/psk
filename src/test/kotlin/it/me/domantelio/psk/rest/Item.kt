package it.me.domantelio.psk.rest

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import java.net.http.HttpClient
import kotlin.random.Random
import me.domantelio.psk.entity.Item
import java.util.*

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class ItemController {
    companion object : RestBase() {
        var rng: Random = Random(System.currentTimeMillis())

        @JvmStatic
        @BeforeAll
        fun setUpAll() {
            httpClient = HttpClient
                .newBuilder()
                .build();
        }

        @JvmStatic
        @AfterAll
        fun cleanup() {
            httpClient!!.close()
        }

        const val URL = "http://localhost:8080"
        var itemId1 = UUID.randomUUID()
        var itemId2 = UUID.randomUUID()
    }

    @Test @Order(10)
    fun createsItem() {
        itemId1 = send<Item, Item>(to = "$URL/api/items", withBody = Item(), method = "POST").id
    }

    @Test @Order(20)
    fun createsAnotherItemAndUpdatesIt() {
        val itemToCreate = Item().apply { name = "test-item-name" }
        val item = send<_, Item>(to = "$URL/api/items", withBody = itemToCreate, method = "POST")
        itemId2 = item.id

        println("HERE")

        val itemToUpdate = item.apply { name = "test-item-name-updated" }
//        val itemToUpdate = item.apply { name = "test-item-name-updated"; version = 1 }
        val updatedItem = send<_, Item>(to = "$URL/api/items/$itemId2", withBody = itemToUpdate, method = "PUT")

//        assertThat(updatedItem).isEqualTo(itemToUpdate.apply { version = itemToUpdate.version + 1 })
        assertThat(updatedItem).isEqualTo(itemToUpdate)
    }

    @Test @Order(30)
    fun getAllAndDeleteEach() {
        val deleteItem = { id: String ->
            sendAndReceiveRaw<NoBody>(to = "$URL/api/items/${id}", method = "DELETE")
        }

        val items1 =  send<NoBody, List<Item>>(to = "$URL/api/items", method = "GET")

        assertThat(deleteItem(itemId1.toString()).statusCode()).isEqualTo(200)
        assertThat(deleteItem(itemId2.toString()).statusCode()).isEqualTo(200)

        val items2 = send<NoBody, List<Item>>(to = "$URL/api/items", method = "GET")
        assertThat(items2).hasSize(items1.size - 2)
    }

}
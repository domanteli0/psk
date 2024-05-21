package it.me.domantelio.psk.rest

import com.codeborne.selenide.Selenide.*
import org.junit.jupiter.api.*
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.net.http.HttpResponse.BodyHandlers
import kotlin.random.Random


class Item {
    companion object {
        protected var rng: Random = Random(System.currentTimeMillis())
        protected var httpClient: HttpClient? = null

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

        fun send(to: String, method: String = "GET", withBody: String? = null): HttpResponse<String> {
            val request = HttpRequest.newBuilder().run {
                when (method) {
                    "GET" -> {
                        if (withBody != null) { throw RuntimeException("BODY ON GET REQUEST") }
                        GET()
                    }
                    "POST" -> {
                        POST(HttpRequest.BodyPublishers.ofString(withBody!!))
                    }
                    "DELETE" -> {
                        DELETE()
                    }
                    else -> {
                        throw RuntimeException("UNSUPPORTED METHOD")
                    }
                }
                }
                .uri(URI.create(to))
                .build()
            val response: HttpResponse<String> =
                httpClient!!.send(request, BodyHandlers.ofString())
            return response
        }

        const val URL = "http://localhost:8080/api"
    }

    @Test @Order(10)
    fun createsItem() {
        send(to = "${URL}/api/items", withBody = Item().toString(), method = "POST")
        // TODO: assert ok
    }

    @Test @Order(20)
    fun createsAnotherItemAndUpdatesIt() {
        val response = send(to = "${URL}/api/items", withBody = Item().toString(), method = "POST")
        send(to = "${URL}/api/items/todo-id", withBody = TODO("update response object"), method = "POST")
        // TODO: assert update
    }

    @Test @Order(30)
    fun getAllAndDeleteEach() {
        send(to = "${URL}/api/items", withBody = Item().toString(), method = "POST")

        send(to = "${URL}/api/items/${TODO("item-1-id")}", method = "DELETE")
        send(to = "${URL}/api/items/${TODO("item-2-id")}", method = "DELETE")

        send(to = "${URL}/api/items", method = "GET")
        // TODO: assert that: response == []
    }

}
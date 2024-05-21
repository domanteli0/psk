package it.me.domantelio.psk.rest

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.net.http.HttpResponse.BodyHandlers
import kotlin.random.Random

class NoBody

open class RestBase {

    var httpClient: HttpClient? = null

    inline fun <reified Req, reified Res> send(
        to: String,
        method: String = "GET",
        withBody: Req? = null,
        assertResponse: ((HttpResponse<String>) -> Unit) = ResponseAsserts::is200
    ): Res {
        val response = sendAndReceiveRaw(to, method, withBody, assertResponse)
        val ret = Json.decodeFromString<Res>(response.body())
        println("  -> JSON Success  ")
        return ret
    }

    inline fun <reified T> sendAndReceiveRaw(
        to: String,
        method: String = "GET",
        withBody: T? = null,
        assertResponse: (HttpResponse<String>) -> Unit = ResponseAsserts::is200
    ): HttpResponse<String> {
        println("SENDING REQUEST [$method] TO: $to")
        val request = HttpRequest.newBuilder().run {
            when (method) {
                "GET" -> {
                    if (withBody != null) {
                        throw RuntimeException("BODY ON GET REQUEST")
                    }
                    GET()
                }

                "POST" -> {
                    POST(HttpRequest.BodyPublishers.ofString(Json.encodeToString(withBody!!)))
                }

                "DELETE" -> {
                    if (withBody != null) {
                        throw RuntimeException("BODY ON DELETE REQUEST")
                    }
                    DELETE()
                }

                else -> {
                    throw RuntimeException("UNSUPPORTED METHOD")
                }
            }
        }
            .uri(URI.create(to))
            .setHeader("Content-Type", "application/json")
            .build()
        val response: HttpResponse<String> =
            httpClient!!.send(request, BodyHandlers.ofString())
        assertResponse(response)
        println("  -> HTTP Success  ")
        return response
    }
}

object ResponseAsserts {
    fun <T> is200(self: HttpResponse<T>) {
        assertThat(self.statusCode()).isEqualTo(200)
    }
}
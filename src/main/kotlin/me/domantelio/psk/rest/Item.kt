package me.domantelio.psk.rest

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import me.domantelio.psk.entity.Item
import me.domantelio.psk.repositoy.ItemRepository
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody
import java.util.UUID

@ApplicationScoped
@Path("/items")
class ItemController() {
    @Inject
    private lateinit var itemRepository: ItemRepository

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getById(@PathParam("id") itemId: UUID): Response {
        val item = itemRepository.findItemById(itemId)

        return Response.ok(item).build()
    }

    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getWithQuery(@QueryParam("name") nameQuery: String?): Response {
        val items = nameQuery?.let {
            return@let itemRepository.findItemsByName(it)
        } ?: itemRepository.findAllItems()

        return Response.ok(items).build()
    }

    @Path("")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    fun create(@RequestBody item: Item): Response {
        itemRepository.createItem(item)

        return Response.ok(item).build()
    }

    @Path("/{id}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    fun update(@RequestBody item: Item, @QueryParam("id") itemId: UUID): Response {
        val item = item.apply { id = itemId }
        itemRepository.updateItem(item)

        return Response.ok(item).build()
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    fun delete(@QueryParam("id") itemId: UUID): Response {
        itemRepository.deleteItem(itemId)
        return Response.ok().build()
    }

}
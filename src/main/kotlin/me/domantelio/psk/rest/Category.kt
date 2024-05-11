package me.domantelio.psk.rest

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import me.domantelio.psk.repositoy.CategoryRepository
import java.util.UUID

@ApplicationScoped
@Path("/category")
class CategoryController() {
    @Inject
    private lateinit var service: CategoryRepository

    // crashes on not found, probably malformed id too
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getById(@PathParam("id") categoryId: UUID?): Response {
        val category = service.findCategoryById(categoryId!!)

        return Response.ok(category).build()
    }

    // TODO: this gets overshadowed by getById
    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getById(@QueryParam("name") categoryName: String?): Response {
        val category = service.findCategoryByName(categoryName!!)

        return Response.ok(category).build()
    }

}
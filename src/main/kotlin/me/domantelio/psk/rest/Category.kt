package me.domantelio.psk.rest

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.persistence.EntityManager
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import me.domantelio.psk.entity.Category
import me.domantelio.psk.mybatis.mapper.ItemDynamicSqlSupport.id
import me.domantelio.psk.mybatis.mapper.ItemDynamicSqlSupport.name
import me.domantelio.psk.mybatis.mapper.ItemMapper
import me.domantelio.psk.mybatis.mapper.select
import me.domantelio.psk.service.CategoryService
import java.util.UUID

@ApplicationScoped
@Path("/category")
class CategoryController() {
    @Inject
    private lateinit var service: CategoryService

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getById(@PathParam("id") categoryId: UUID?): Response {
        val category = service.findCategoryById(categoryId!!)

        return Response.ok(category).build()
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getById(@QueryParam("name") categoryName: String?): Response {
        val category = service.findCategoryByName(categoryName!!)

        return Response.ok(category).build()
    }
}
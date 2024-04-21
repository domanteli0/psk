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
import me.domantelio.psk.mybatis.mapper.ItemDynamicSqlSupport.id
import me.domantelio.psk.mybatis.mapper.ItemDynamicSqlSupport.name
import me.domantelio.psk.mybatis.mapper.ItemMapper
import me.domantelio.psk.mybatis.mapper.select

@ApplicationScoped
@Path("/item")
class ItemController() {
    @Inject
    private lateinit var itemMapper: ItemMapper

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getById(@PathParam("id") itemId: ByteArray?): Response {
        val item = itemMapper.select {
            where { id.isEqualTo(itemId!!) }
        }

        return Response.ok(item).build()
    }

    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getWithQuery(@QueryParam("name") nameQuery: String): Response {
        val items = itemMapper.select {
            where { name.name()!!.contentEquals(nameQuery) }
        }

        return Response.ok(items).build()
    }


//    @Path("/{id}")
//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Transactional
//    fun update(
//        @PathParam("id") playerId: Int?,
//        playerData: PlayerDto
//    ): Response {
//        try {
//            val existingPlayer: Player =
//                playersDAO.findOne(playerId) ?: return Response.status(Response.Status.NOT_FOUND).build()
//            existingPlayer.setName(playerData.getName())
//            existingPlayer.setJerseyNumber(playerData.getJerseyNumber())
//            playersDAO.update(existingPlayer)
//            return Response.ok().build()
//        } catch (ole: OptimisticLockException) {
//            return Response.status(Response.Status.CONFLICT).build()
//        }
//    }
}
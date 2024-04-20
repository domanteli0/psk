//package lt.vu.rest
//
//import lombok.*
//import lt.vu.entities.Player
//import lt.vu.persistence.PlayersDAO
////import lt.vu.rest.contracts.PlayerDto
//import jakarta.enterprise.context.ApplicationScoped
//import jakarta.inject.Inject
//import jakarta.persistence.OptimisticLockException
//import jakarta.transaction.Transactional
//import jakarta.ws.rs.*
//import jakarta.ws.rs.Path
//import jakarta.ws.rs.core.MediaType
//import jakarta.ws.rs.core.Response
//
//@ApplicationScoped
//@Path("/players")
//class PlayersController {
//    @Inject
//    @Setter
//    @Getter
//    private val playersDAO: PlayersDAO? = null
//
//    @Path("/{id}")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    fun getById(@PathParam("id") id: Int?): Response {
//        val player: Player = playersDAO.findOne(id) ?: return Response.status(Response.Status.NOT_FOUND).build()
//
//        val playerDto: PlayerDto = PlayerDto()
//        playerDto.setName(player.getName())
//        playerDto.setJerseyNumber(player.getJerseyNumber())
//        playerDto.setTeamName(player.getTeam().getName())
//
//        return Response.ok(playerDto).build()
//    }
//
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
//}
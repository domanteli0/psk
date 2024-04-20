package me.domantelio.psk.persistance

import jakarta.enterprise.context.*
import jakarta.enterprise.inject.*
import jakarta.persistence.*

@ApplicationScoped
open class Resources {

    @PersistenceUnit
    public var emf: EntityManagerFactory? = null

    @Produces
    @Default
    @RequestScoped
    private fun createJTAEntityManager(): EntityManager {
        return emf!!.createEntityManager(SynchronizationType.SYNCHRONIZED)
    }

    private fun closeDefaultEntityManager(@Disposes @Default em: EntityManager) {
        em.close()
    }
}
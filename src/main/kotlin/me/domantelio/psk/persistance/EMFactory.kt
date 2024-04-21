package me.domantelio.psk.persistance

import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.context.RequestScoped
import jakarta.enterprise.inject.Default
import jakarta.enterprise.inject.Disposes
import jakarta.enterprise.inject.Produces
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.PersistenceUnit
import jakarta.persistence.SynchronizationType

@ApplicationScoped
open class EMFactory() {

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
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
    private lateinit var emf: EntityManagerFactory

    @Produces
    @Default
    @RequestScoped
    private fun createJTAEntityManager(): EntityManager {
        return emf.createEntityManager()
    }

    private fun closeDefaultEntityManager(@Disposes @Default em: EntityManager) {
        em.close()
    }
}
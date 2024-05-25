package me.domantelio.psk

import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Disposes
import jakarta.enterprise.inject.Produces
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence
import jakarta.enterprise.context.RequestScoped
import jakarta.enterprise.inject.Alternative
import jakarta.enterprise.inject.Default
import jakarta.inject.Inject
import jakarta.persistence.EntityManager
import jakarta.persistence.SynchronizationType
import me.domantelio.psk.persistance.EMFactory

@ApplicationScoped
class EntityManagerFactoryProducer {
    private val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("test-unit")

    @Produces
    @ApplicationScoped
    fun createEntityManager(): EntityManager {
        return emf.createEntityManager()
    }

    fun closeEntityManager(@Disposes em: EntityManager) {
        em.close()
    }

    @Produces
    @ApplicationScoped
    fun createEntityManagerFactory(): EntityManagerFactory {
        return emf
    }

    fun closeEntityManagerFactory(@Disposes emf: EntityManagerFactory) {
        emf.close()
    }
}

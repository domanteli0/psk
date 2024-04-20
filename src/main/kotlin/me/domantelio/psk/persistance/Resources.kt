package me.domantelio.psk.persistance

import jakarta.enterprise.context.*
import jakarta.enterprise.inject.*
import jakarta.inject.Named
import jakarta.persistence.*
import org.apache.ibatis.io.Resources
import org.apache.ibatis.session.SqlSessionFactory
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import org.mybatis.cdi.SessionFactoryProvider

@ApplicationScoped
open class Resources {
    @Produces
//    @ApplicationScoped
    @SessionFactoryProvider
    @Throws
//    @Dependent
    fun produceSqlSessionFactory(): SqlSessionFactory {
        return SqlSessionFactoryBuilder().build(
            Resources.getResourceAsStream("MyBatisConfig.xml")
        )
    }

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
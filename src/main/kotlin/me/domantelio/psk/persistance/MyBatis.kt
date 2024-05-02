package me.domantelio.psk.persistance

import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Produces
import org.apache.ibatis.io.Resources
import org.apache.ibatis.session.SqlSessionFactory
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import org.mybatis.cdi.SessionFactoryProvider

@ApplicationScoped
open class MyBatis() {
    @Produces
    @ApplicationScoped
    @SessionFactoryProvider
    fun produceSqlSessionFactory(): SqlSessionFactory {
        return SqlSessionFactoryBuilder().build(
            Resources.getResourceAsStream("MyBatisConfig.xml")
        )
    }

}
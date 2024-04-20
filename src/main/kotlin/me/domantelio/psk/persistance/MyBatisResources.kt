package me.domantelio.psk.persistance

import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Produces
import org.apache.ibatis.session.SqlSessionFactory
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import org.apache.ibatis.io.Resources
import org.mybatis.cdi.SessionFactoryProvider
import java.io.IOException

@ApplicationScoped
public class MyBatisResources {
    @Produces
    @ApplicationScoped
    @SessionFactoryProvider
    private fun produceSqlSessionFactory(): SqlSessionFactory {
        try {
            return SqlSessionFactoryBuilder().build(
                Resources.getResourceAsStream("MyBatisConfig.xml")
            )
        } catch (e: IOException) {
            throw RuntimeException("MyBatisResources.produceSqlSessionFactory(): ", e)
        }
    }
}

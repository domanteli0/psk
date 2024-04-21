package me.domantelio.psk.persistance

import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Alternative
import jakarta.enterprise.inject.Default
import jakarta.enterprise.inject.Produces
import jakarta.inject.Inject
import jakarta.inject.Named
import jakarta.inject.Qualifier
import me.domantelio.psk.mybatis.mapper.ItemMapper
import org.apache.ibatis.session.SqlSession

@ApplicationScoped
@Alternative
class MyItemMapper() {
    @Inject
    private lateinit var sqlSession: SqlSession

    @Produces
    @Default
    @Named("myItemMapper")
    fun produceItemMapper(): ItemMapper {
        return sqlSession.getMapper(ItemMapper::class.java)
    }
}
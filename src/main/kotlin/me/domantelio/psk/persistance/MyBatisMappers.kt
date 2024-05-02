package me.domantelio.psk.persistance

import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Alternative
import jakarta.enterprise.inject.Default
import jakarta.enterprise.inject.Produces
import jakarta.inject.Inject
import jakarta.inject.Named
import me.domantelio.psk.mybatis.mapper.CategoryMapper
import me.domantelio.psk.mybatis.mapper.InvoiceMapper
import me.domantelio.psk.mybatis.mapper.ItemMapper
import org.apache.ibatis.session.SqlSession

@ApplicationScoped
@Alternative
class MyBatisMappers() {
    @Inject
    private lateinit var sqlSession: SqlSession

    @Produces
    @Default
    @Named("myCategoryMapper")
    fun produceCategoryMapper(): CategoryMapper {
        return sqlSession.getMapper(CategoryMapper::class.java)
    }

    @Produces
    @Default
    @Named("myInvoiceMapper")
    fun produceInvoiceMapper(): InvoiceMapper {
        return sqlSession.getMapper(InvoiceMapper::class.java)
    }

    @Produces
    @Default
    @Named("myItemMapper")
    fun produceItemMapper(): ItemMapper {
        return sqlSession.getMapper(ItemMapper::class.java)
    }
}
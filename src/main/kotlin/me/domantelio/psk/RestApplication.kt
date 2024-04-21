package me.domantelio.psk

import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Produces
import jakarta.ws.rs.ApplicationPath
import jakarta.ws.rs.core.Application
import me.domantelio.psk.service.CategoryService

@ApplicationPath("/rest")
class RestApplication : Application() {
//    @Produces
//    @ApplicationScoped
//    fun getCategoryService(): CategoryService {
//        return CategoryService()
//    }
}
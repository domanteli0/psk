package me.domantelio.psk.faces

import jakarta.annotation.PostConstruct
import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import jakarta.inject.Named
import jakarta.transaction.Transactional
import me.domantelio.psk.mybatis.model.Category
import me.domantelio.psk.interceptors.LoggedInvocation
import me.domantelio.psk.repositoy.CategoryRepository
import me.domantelio.psk.service.ColorService
import org.slf4j.LoggerFactory
import java.io.Serializable

@Named
@RequestScoped
open class CategoryFace(
     var allCategories: List<Category> = listOf(),
     var categoryToCreate: Category = Category(),
) : Serializable {
    public constructor() : this(listOf(), Category())

    @Inject
    private lateinit var colors: ColorService

    @Inject
    private lateinit var repository: CategoryRepository

    private val logger = LoggerFactory.getLogger(javaClass)

    @PostConstruct
    fun init() {
        this.loadAllCategories()
    }

    private fun loadAllCategories() {
        this.allCategories = repository.findAllCategories()
    }

    @Transactional
    @LoggedInvocation
    open fun createCategory(): String {
        repository.createCategory(categoryToCreate)
        return ""
    }

    @LoggedInvocation
    open fun colorOfCategory(category: Category): String {
        logger.debug("Category hash: ${category.hashCode()}")
        return colors.fromHashCode(category)
    }
}

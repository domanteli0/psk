package me.domantelio.psk.faces

import jakarta.annotation.PostConstruct
import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import jakarta.inject.Named
import jakarta.transaction.Transactional
import me.domantelio.psk.entity.Category
import me.domantelio.psk.interceptors.LoggedInvocation
import me.domantelio.psk.repositoy.CategoryRepository
import me.domantelio.psk.service.ColorService
import org.slf4j.LoggerFactory
import java.io.Serializable

@Named
@RequestScoped
open class CategoryFace(
     private var allCategories: List<Category> = listOf(),
     private var categoryToCreate: Category = Category(),
) : Serializable {
    public constructor() : this(listOf(), Category())

    @Inject
    private lateinit var colors: ColorService

    @Inject
    private lateinit var service: CategoryRepository

    private val logger = LoggerFactory.getLogger(javaClass)

    public fun getAllCategories(): List<Category> { return allCategories }
    public fun setAllCategories(categories: List<Category>) { this.allCategories = categories }

    public fun getCategoryToCreate(): Category { return categoryToCreate }
    public fun setCategoryToCreate(categoryToCreate: Category) { this.categoryToCreate = categoryToCreate }

    @PostConstruct
    fun init() {
        this.loadAllCategories()
    }

    private fun loadAllCategories() {
        this.allCategories = service.findAllCategories()
    }

    @Transactional
    @LoggedInvocation
    open fun createCategory(): String {
        service.createCategory(categoryToCreate)
        return ""
    }

    @LoggedInvocation
    open fun colorOfCategory(category: Category): String {
        logger.debug("Category hash: ${category.hashCode()}")
        return colors.fromHashCode(category)
    }
}

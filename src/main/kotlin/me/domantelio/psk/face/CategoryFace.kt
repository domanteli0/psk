 package me.domantelio.psk.face

import jakarta.annotation.PostConstruct
import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import jakarta.inject.Named
import jakarta.transaction.Transactional
import lombok.NoArgsConstructor
import me.domantelio.psk.entity.Category
import me.domantelio.psk.service.CategoryService
import java.io.Serializable
import java.util.*

@NoArgsConstructor
@Named
@RequestScoped
open class CategoryFace(
     private var allCategories: List<Category> = listOf(),
     private var categoryToCreate: Category = Category(),
) : Serializable {
    public constructor() : this(listOf(), Category())

    @Inject
    private lateinit var service: CategoryService

    public fun getAllCategories(): List<Category> { return allCategories }
    public fun setAllCategories(categories: List<Category>) { this.allCategories = categories }

    public fun getCategoryToCreate(): Category { return categoryToCreate }
    public fun setCategoryToCreate(categoryToCreate: Category) { this.categoryToCreate = categoryToCreate }

    @PostConstruct
    fun init() {
//        this.loadAllCategorys()
    }

    private fun loadAllCategorys() {
        this.allCategories = service.findAllCategories()
    }

    @Transactional
    open fun createCategory(): String {
        categoryToCreate.id = UUID.randomUUID()

        service.createCategory(categoryToCreate)
        return "/myBatis/categories?faces-redirect=true"
    }
}

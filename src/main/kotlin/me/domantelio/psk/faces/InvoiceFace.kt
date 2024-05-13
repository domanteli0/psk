package me.domantelio.psk.faces

import jakarta.annotation.PostConstruct
import jakarta.enterprise.context.RequestScoped
import jakarta.faces.context.FacesContext
import jakarta.inject.Inject
import jakarta.inject.Named
import jakarta.transaction.Transactional
import me.domantelio.psk.mybatis.mapper.ItemDynamicSqlSupport.invoiceId
import me.domantelio.psk.mybatis.model.*
import me.domantelio.psk.repositoy.CategoryRepository
import me.domantelio.psk.repositoy.InvoiceRepository
import me.domantelio.psk.repositoy.ItemRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.Serializable
import java.util.*
import me.domantelio.psk.util.*
import me.domantelio.psk.util.Collections.toStr

@Named
@RequestScoped
open class InvoiceFace(
    open var thisInvoice: Invoice = Invoice(),
    open var categoryToCreate: Category = Category(),
    open var itemToCreate: Item = Item(),
    open var selected: List<String> = emptyList(),
    open var available: List<String> = emptyList(),
) : Serializable {
    constructor() : this(thisInvoice = Invoice())

    @Inject
    private lateinit var categoryRepository: CategoryRepository

    @Inject
    private lateinit var invoiceRepository: InvoiceRepository

    @Inject
    private lateinit var itemRepository: ItemRepository

    private val logger: Logger = LoggerFactory.getLogger(InvoiceFace::class.java)

    @PostConstruct
    @Throws(IllegalArgumentException::class)
    open fun init() {
        val requestParameters = FacesContext.getCurrentInstance().externalContext.requestParameterMap
        logger.debug("REQUEST_PARAMS: ${requestParameters.toStr()}")
        logger.debug("INVOICE_CATS:   ${thisInvoice.items.toStr()}")

        val invoiceId = requestParameters["invoiceId"]
        val invoiceUUID = UUID.fromString(invoiceId)
        thisInvoice = invoiceRepository.findInvoiceById(invoiceUUID)!!

        selected = thisInvoice.categories
            .filter { it.name != null }
            .map { it.name!! }
            .toList()

        available = categoryRepository.findAllCategories()
            .filter { it.name != null }
            .map { it.name!! }
            .toList()

    }

    open fun items(): List<Item> {
        return itemRepository.findByInvoiceId(thisInvoice.id!!)
    }

    @Transactional
    open fun appendItem(): String {
        itemRepository.createItem(
            itemToCreate.apply { invoiceId = thisInvoice.id }
        )

        return "/invoice?faces-redirect=true&amp;invoiceId=${thisInvoice.id}"
    }

    @Transactional
    open fun appendCategory(): String {
        categoryRepository.createCategory(categoryToCreate)
        invoiceRepository.appendCategory(thisInvoice, categoryToCreate)

        return "/invoice?faces-redirect=true&amp;invoiceId=${thisInvoice.id}"
    }

    @Transactional
    open fun changeSelection(): String {
        val oldCategories = thisInvoice.categories

        for (category in oldCategories) {
            invoiceRepository.removeCategory(thisInvoice, category)
        }

        var newCategories = selected
            .map { categoryRepository.findCategoryByName(it)!! }

        for (category in newCategories) {
            invoiceRepository.appendCategory(thisInvoice, category)
        }

        return "/invoice?faces-redirect=true&amp;invoiceId=${thisInvoice.id}"
    }
}

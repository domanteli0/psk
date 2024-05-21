package me.domantelio.psk.faces

import jakarta.annotation.PostConstruct
import jakarta.enterprise.context.RequestScoped
import jakarta.faces.context.FacesContext
import jakarta.inject.Inject
import jakarta.inject.Named
import jakarta.transaction.Transactional
import me.domantelio.psk.entity.Category
import me.domantelio.psk.entity.Invoice
import me.domantelio.psk.entity.Item
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
        thisInvoice =  invoiceRepository.findInvoiceById(invoiceUUID)

        selected = thisInvoice.categories
            .filter { it.name != null }
            .map { it.name!! }
            .toList()

        available = categoryRepository.findAllCategories()
            .filter { it.name != null }
            .map { it.name!! }
            .toList()

    }

    @Transactional
    open fun appendItem(): String {
        thisInvoice.items.add(itemToCreate)
        invoiceRepository.updateInvoice(thisInvoice)

        return ""
    }

    @Transactional
    open fun appendCategory(): String {
        thisInvoice.categories.add(categoryToCreate)
        invoiceRepository.updateInvoice(thisInvoice)

        return ""
    }

    @Transactional
    open fun changeSelection(): String {
        logger.debug("SELECTED: ${selected.toStr()}")

        val oldCategories = thisInvoice.categories

        thisInvoice.categories = selected
            .map { categoryRepository.findCategoryByName(it)!! }
            .toMutableSet()

        invoiceRepository.updateInvoice(thisInvoice)

        logger.debug("OLD: ${oldCategories.toStr()}")
        logger.debug("NEW: ${thisInvoice.categories.toStr()}")

        return ""
    }
}

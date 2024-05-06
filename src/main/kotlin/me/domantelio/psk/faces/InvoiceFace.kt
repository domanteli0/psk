package me.domantelio.psk.faces

import jakarta.annotation.PostConstruct
import jakarta.enterprise.context.RequestScoped
import jakarta.faces.context.FacesContext
import jakarta.inject.Inject
import jakarta.inject.Named
import jakarta.transaction.Transactional
import me.domantelio.psk.entity.Invoice
import me.domantelio.psk.entity.Item
import me.domantelio.psk.interceptors.LoggedInvocation
import me.domantelio.psk.service.InvoiceService
import me.domantelio.psk.service.ItemService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.Serializable
import java.util.*
import me.domantelio.psk.util.*
import me.domantelio.psk.util.Collections.toStr

@Named
@RequestScoped
@LoggedInvocation
open class InvoiceFace(
    open var thisInvoice: Invoice = Invoice(),
    open var itemToCreate: Item = Item(),
) : Serializable {
    constructor() : this(Invoice())

    @Inject
    private lateinit var invoiceService: InvoiceService

    @Inject
    private lateinit var itemService: ItemService

    private val logger: Logger = LoggerFactory.getLogger(InvoiceFace::class.java)

    @PostConstruct
    @Throws(IllegalArgumentException::class)
    open fun init() {
        val requestParameters = FacesContext.getCurrentInstance().externalContext.requestParameterMap
        logger.debug("REQUEST_PARAMS: ${requestParameters.toStr()}")
        logger.debug("INVOICE_CATS:   ${thisInvoice.items.toStr()}")

        val invoiceId = requestParameters["invoiceId"]
        val invoiceUUID = UUID.fromString(invoiceId)
        thisInvoice =  invoiceService.findInvoiceById(invoiceUUID)
    }

    @Transactional
    open fun appendItem(): String {
        thisInvoice.items.add(itemToCreate)
        invoiceService.updateInvoice(thisInvoice)

        return ""
    }
}

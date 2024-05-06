package me.domantelio.psk.faces

import jakarta.annotation.PostConstruct
import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import jakarta.inject.Named
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.PersistenceContext
import jakarta.persistence.PersistenceUnit
import jakarta.transaction.Transactional
import me.domantelio.psk.entity.Invoice
import me.domantelio.psk.service.CategoryService
import me.domantelio.psk.service.InvoiceService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.Serializable
import java.nio.ByteBuffer
import java.util.*

@Named
@RequestScoped
open class IndexInvoiceFace(
    private var allInvoices: List<Invoice> = listOf(),
    private var invoiceToCreate: Invoice = Invoice(),
) : Serializable {
    constructor() : this(listOf(), Invoice())

    private val logger: Logger = LoggerFactory.getLogger(IndexInvoiceFace::class.java)

    @Inject
    private lateinit var service: InvoiceService

    @PersistenceUnit
    private lateinit var emf: EntityManagerFactory

    fun getAllInvoices(): List<Invoice> { return allInvoices }
    fun setAllInvoices(invoices: List<Invoice>) { this.allInvoices = invoices }

    fun getInvoiceToCreate(): Invoice { return invoiceToCreate }
    fun setInvoiceToCreate(invoiceToCreate: Invoice) { this.invoiceToCreate = invoiceToCreate }

    @PostConstruct
    fun init() = this.loadAllInvoices()

    private fun loadAllInvoices() {
        this.allInvoices = service.findAllInvoices()
    }

    @Transactional
    open fun createInvoice(): String {
        service.createInvoice(invoiceToCreate)
        val id: Any? = emf.persistenceUnitUtil.getIdentifier(invoiceToCreate)
        return "/invoice?faces-redirect=true&amp;invoiceId=$id"
    }
}

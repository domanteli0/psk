package me.domantelio.psk.faces

import jakarta.annotation.PostConstruct
import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import jakarta.inject.Named
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.PersistenceUnit
import jakarta.transaction.Transactional
import me.domantelio.psk.mybatis.model.*
import me.domantelio.psk.repositoy.InvoiceRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.Serializable

@Named
@RequestScoped
open class IndexInvoiceFace(
    var allInvoices: List<Invoice> = listOf(),
    var invoiceToCreate: Invoice = Invoice(),
) : Serializable {
    constructor() : this(listOf(), Invoice())

    private val logger: Logger = LoggerFactory.getLogger(IndexInvoiceFace::class.java)

    @Inject
    private lateinit var service: InvoiceRepository

    @PersistenceUnit
    private lateinit var emf: EntityManagerFactory

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

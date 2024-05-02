package me.domantelio.psk.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Model
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.persistence.Query
import jakarta.transaction.Transactional
import jakarta.ws.rs.WebApplicationException
import me.domantelio.psk.entity.Invoice
import java.util.*
import java.util.logging.Logger

@ApplicationScoped
open class InvoiceService() {
    @PersistenceContext
    private lateinit var em: EntityManager

    @Transactional
    open fun createInvoice(invoice: Invoice) {
        em.persist(invoice)
        LOGGER.info("Created Invoice $invoice")
    }

    @Transactional
    open fun updateInvoice(invoice: Invoice) {
        em.merge(invoice)
        LOGGER.info("Updated invoice$invoice")
    }

    @Transactional
    open fun deleteInvoice(invoiceId: UUID) {
        val c: Invoice = findInvoiceById(invoiceId)
        em.remove(c)
        LOGGER.info("Deleted Invoice with id$invoiceId")
    }

    fun findInvoiceById(id: UUID): Invoice {
        val invoice: Invoice = em.find(Invoice::class.java, id)
            ?: throw WebApplicationException("Invoice with id of $id does not exist.", 404)
        return invoice
    }

    fun findAllInvoices(): List<Invoice> {
        val query: Query = em.createQuery("SELECT i FROM Invoice i")
        return query.resultList as MutableList<Invoice>
    }

    fun findInvoiceByName(name: String): Invoice {
        val query: Query = em
            .createQuery("SELECT i FROM Invoice i WHERE i.name = :name")
        query.setParameter("name", name)
        return query.singleResult as Invoice
    }

    companion object {
        private val LOGGER: Logger = Logger.getLogger(InvoiceService::class.java.name)
    }
}

package me.domantelio.psk.repositoy

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import jakarta.ws.rs.WebApplicationException
import me.domantelio.psk.mybatis.mapper.*
import me.domantelio.psk.mybatis.mapper.InvoiceDynamicSqlSupport.id
import me.domantelio.psk.mybatis.model.Invoice
import me.domantelio.psk.mybatis.model.Item
import org.slf4j.*
import java.util.*

@ApplicationScoped
open class InvoiceRepository() {

    private val logger: Logger = LoggerFactory.getLogger(javaClass.name)

    @Inject
    private lateinit var mapper: InvoiceMapper

    @Inject
    private lateinit var itemMapper: ItemMapper

    @Transactional
    open fun createInvoice(invoice: Invoice) {
        mapper.insert(invoice)
        logger.info("Created Invoice $invoice")
    }

    open fun updateInvoice(invoice: Invoice) {
        mapper.updateByPrimaryKey(invoice)

        logger.info("Updated Invoice $invoice")
    }

    @Transactional
    open fun deleteInvoice(invoiceId: UUID) {
        mapper.delete { where { id.isEqualTo(invoiceId.toString()) } }.let {
            if (it == 1) {
                logger.debug("Deleted Invoice with id [$invoiceId]")
            } else {
                logger.debug("No Invoice to delete with id [$invoiceId]")
            }
        }
    }

    fun findInvoiceById(id_: UUID): Invoice? {
        return mapper.selectByPrimaryKey(id_.toString())
    }

    fun findAllInvoices(): List<Invoice> {
        return mapper.select {
            allRows()
        }
    }

    fun findInvoiceByName(name_: String): Invoice? {
        return mapper.selectOne {
            where { InvoiceDynamicSqlSupport.name.isEqualTo(name_) }
        }
    }
}

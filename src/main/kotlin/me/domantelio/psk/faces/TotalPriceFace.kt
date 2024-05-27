package me.domantelio.psk.faces

import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.context.RequestScoped
import java.io.Serializable
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutionException
import java.util.function.Supplier
import jakarta.enterprise.context.SessionScoped
import jakarta.faces.context.FacesContext
import jakarta.faces.view.ViewScoped
import jakarta.inject.Inject
import jakarta.inject.Named
import me.domantelio.psk.entity.Invoice
import me.domantelio.psk.interceptors.LoggedInvocation
import me.domantelio.psk.service.TotalPriceCalculator
import org.slf4j.LoggerFactory
import java.util.*

@ApplicationScoped
@Named
open class TotalPriceFace() : Serializable {
    @Inject
    private lateinit var calculator: TotalPriceCalculator

//    private var calculationTask: CompletableFuture<Int>? = null
    private var calculationsMap: MutableMap<UUID, CompletableFuture<Int>> = mutableMapOf()

    private val logger = LoggerFactory.getLogger(javaClass)

    open fun isCalculating(invoice: Invoice): Boolean {
        return calculationsMap.get(invoice.id)?.isDone?.not() ?: false;
    }

    @LoggedInvocation
    open fun calculate(invoice: Invoice): String {
        val requestParameters: Map<String, String> =
            FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()

        calculationsMap.put(
            invoice.id!!,
            CompletableFuture.supplyAsync(Supplier { calculator.calculate(invoice) })
            )

        return "/invoice.xhtml?faces-redirect=true&invoiceId=" + requestParameters["invoiceId"]
    }

    open fun calculatorStatus(invoice: Invoice): String {
            if (calculationsMap.get(invoice.id!!) == null) {
                return "No price calculation in process"
            } else if (calculationsMap.get(invoice.id!!)!!.isDone.not()) {
                return "Calculation in progress"
            }
            return "Total price: ${calculationsMap.get(invoice.id!!)!!.get()}"
        }
}
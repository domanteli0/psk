package me.domantelio.psk.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.context.SessionScoped
import jakarta.faces.view.ViewScoped
import me.domantelio.psk.entity.Invoice
import me.domantelio.psk.interfaces.PriceCalculator
import java.io.Serializable
import java.util.*

@ApplicationScoped
open class TotalPriceCalculator(): Serializable, PriceCalculator {
    open override fun calculate(invoice: Invoice): Int {
        Thread.sleep(1000)
        return invoice.items.map { it.price!! }.sum()
    }
}

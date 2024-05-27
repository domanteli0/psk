package me.domantelio.psk.decorators

import jakarta.decorator.Decorator
import jakarta.decorator.Delegate
import jakarta.enterprise.inject.Any
import jakarta.inject.Inject
import me.domantelio.psk.entity.Invoice
import me.domantelio.psk.interfaces.PriceCalculator
import me.domantelio.psk.service.TotalPriceCalculator

@Decorator
open abstract class WrongTotalPriceCalculator() : PriceCalculator {
    @Inject
    @Delegate
//    @Any
    private lateinit var calculator: TotalPriceCalculator

    open override fun calculate(invoice: Invoice): Int {
        return -100000 + calculator.calculate(invoice)
    }
}

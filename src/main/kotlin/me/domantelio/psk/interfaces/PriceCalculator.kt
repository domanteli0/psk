package me.domantelio.psk.interfaces

import me.domantelio.psk.entity.Invoice

interface PriceCalculator {
    open fun calculate(invoice: Invoice): Int
}
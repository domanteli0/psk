package me.domantelio.psk.entity

import jakarta.persistence.*
import me.domantelio.psk.interceptors.LoggedInvocation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

@Entity
class Invoice() {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null

    var name: String? = null
        get() = purchaseDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        set(value) {
            field = value
        }

    @Column(columnDefinition = "TIMESTAMP", name = "date_time")
    var purchaseDateTime: LocalDateTime = LocalDateTime.now()

    @OneToMany
    var items: MutableList<Item> = mutableListOf()

    @ManyToMany
    var categories: MutableSet<Category> = mutableSetOf()

    @LoggedInvocation
    fun getTotalPrice(): Int {
        val logger: Logger = LoggerFactory.getLogger(javaClass)
        val msg: String =  items.map(Item::toString).fold("", String::plus)

        logger.debug(msg)

        return items
            .filter { it.price != null }
            .map { it.price!! }
            .fold(0, Int::plus)
    }

}

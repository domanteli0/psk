package me.domantelio.psk.entity

import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Entity
@Table(name = "ITEM")
@NamedQueries(
    NamedQuery(name = "Item.selectAll", query = "select i from Item as i")
)
open class Item() {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = UUID.randomUUID()

    @Version
    var version: Long = 0

    var name: String? = null

    var price: Int? = null

    @Column(name = "desc")
    var description: String? = null
}

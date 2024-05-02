package me.domantelio.psk.entity

import jakarta.persistence.*
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
    var name: String = ""
    var price: Int = 0
    @Column(name = "descr")
    var description: String = ""
}

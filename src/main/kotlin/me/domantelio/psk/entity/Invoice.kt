package me.domantelio.psk.entity

import jakarta.persistence.*
import java.util.*

// TODO: date_and_time
@Entity
class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null

    var name: String = ""

    @Column(name = "price")
    var total_price: Int = 0

    @OneToMany
    var items: MutableList<Item> = mutableListOf()

    @ManyToMany
    var categories: MutableSet<Category> = mutableSetOf()
}

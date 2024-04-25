package me.domantelio.psk.entity

import jakarta.persistence.*
import java.util.*

@Entity
open class Invoice(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID = UUID.randomUUID(),
    var name: String,
    var price: Int,
    @OneToMany(cascade = [CascadeType.MERGE])
    var items: MutableList<Item> = mutableListOf(),
    @ManyToMany(cascade = [CascadeType.MERGE])
    var categories: MutableSet<Category> = mutableSetOf()
)

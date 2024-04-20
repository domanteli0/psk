package me.domantelio.psk.entity

import jakarta.persistence.*
import java.util.*

open class Invoice(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val price: Int,
    @ManyToOne
    val items: List<Item>,
    @ManyToMany
    val categories: Set<Category>,
)

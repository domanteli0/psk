package me.domantelio.psk.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.*

// TODO: add ref to Invoice
@Entity
class Category() {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null

    var name: String? = null
        set(value) { field = value?.lowercase(Locale.US) }
}

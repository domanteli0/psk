package me.domantelio.psk.entity

import jakarta.persistence.*
import java.util.*

@Entity
class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID = UUID.randomUUID()
    var name: String = ""
    var price: Int = 0
    var description: String = ""
}

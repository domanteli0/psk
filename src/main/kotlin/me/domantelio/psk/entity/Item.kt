package me.domantelio.psk.entity

import jakarta.persistence.*
import kotlinx.serialization.Serializable
import me.domantelio.psk.serializers.UUIDSerializer
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Entity
@Table(name = "ITEM")
@NamedQueries(
    NamedQuery(name = "Item.selectAll", query = "select i from Item as i")
)
@Serializable
open class Item() {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Serializable(with = UUIDSerializer::class)
    var id: UUID? = UUID.randomUUID()

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    var version: Int? = null

    var name: String? = null

    var price: Int? = null

    @Column(name = "desc")
    var description: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Item

        if (id != other.id) return false
        if (version != other.version) return false
        if (name != other.name) return false
        if (price != other.price) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + version.hashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (price ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Item(id=$id, version=$version, name=$name, price=$price, description=$description)"
    }
}

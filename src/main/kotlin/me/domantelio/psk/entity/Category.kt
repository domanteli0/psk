package me.domantelio.psk.entity

import jakarta.persistence.*
import me.domantelio.psk.mybatis.model.Category
import java.util.*

@Entity
@Table(
    indexes = [Index(name = "unique_names",  columnList = "name", unique = true)]
)
@NamedQueries(
    NamedQuery(name = "Category.findByName", query = "select c from Category as c where c.name = :name",)
)
class Category() {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null

    @ManyToMany(mappedBy = "categories")
    var belongsTo: MutableSet<Invoice> = emptySet<Invoice>().toMutableSet()

    @Column(nullable = false, unique = true)
    var name: String? = null
        set(value) { field = value?.lowercase(Locale.US) }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as me.domantelio.psk.entity.Category

        if (id != other.id) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        return result
    }
}

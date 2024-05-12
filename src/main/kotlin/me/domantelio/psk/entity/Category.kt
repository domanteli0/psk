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
}

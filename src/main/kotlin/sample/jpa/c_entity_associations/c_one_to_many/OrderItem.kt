package sample.jpa.c_entity_associations.c_one_to_many

import jakarta.persistence.Entity
import sample.jpa.a_entiity.a_id.IncrementId


@Entity
class OrderItem(
    var name: String,
    var count: Long,
    var price: Long
) : IncrementId()
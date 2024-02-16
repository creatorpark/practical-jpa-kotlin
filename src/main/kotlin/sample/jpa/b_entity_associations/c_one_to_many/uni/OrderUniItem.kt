package sample.jpa.b_entity_associations.c_one_to_many.uni

import jakarta.persistence.Entity
import sample.jpa.a_entiity.id.TsId


@Entity
class OrderUniItem(
    var name: String,
    var count: Long,
    var price: Long
) : TsId()
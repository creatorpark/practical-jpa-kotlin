package sample.jpa.b_entity_associations.b_many_to_one

import jakarta.persistence.Entity
import sample.jpa.a_entiity.id.TsId

@Entity
class TsIdEntity(
    val name: String
) : TsId()
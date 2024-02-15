package sample.jpa.b_entity_associations.c_many_to_many

import jakarta.persistence.Entity
import sample.jpa.a_entiity.id.TsId

@Entity
class TsIdEntity(
    val name: String
) : TsId()
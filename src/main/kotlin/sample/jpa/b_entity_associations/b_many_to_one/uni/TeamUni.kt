package sample.jpa.b_entity_associations.b_many_to_one.uni

import jakarta.persistence.Entity
import sample.jpa.a_entiity.id.TsId

@Entity
class TeamUni(
    val name: String
) : TsId()
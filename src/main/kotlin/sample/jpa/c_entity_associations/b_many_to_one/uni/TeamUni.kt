package sample.jpa.c_entity_associations.b_many_to_one.uni

import jakarta.persistence.Entity
import sample.jpa.a_entiity.a_id.IncrementId

@Entity
class TeamUni(
    val name: String
) : IncrementId()
package sample.jpa.b_entity_associations.b_many_to_one.uni

import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import sample.jpa.a_entiity.id.IncrementId


@Entity
class PlayerUni(
    var name: String,
    var uniformNumber: Int,
    @ManyToOne(optional = false)
    var team: TeamUni
) : IncrementId()



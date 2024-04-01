package sample.jpa.a_entiity

import jakarta.persistence.Entity
import sample.jpa.a_entiity.a_id.IncrementId

@Entity
class IncrementIdEntity(
    var name: String
) : IncrementId()
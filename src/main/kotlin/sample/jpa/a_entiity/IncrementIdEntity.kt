package sample.jpa.a_entiity

import jakarta.persistence.Entity
import sample.jpa.a_entiity.id.IncrementId

@Entity
class IncrementIdEntity(
    var name: String
) : IncrementId()
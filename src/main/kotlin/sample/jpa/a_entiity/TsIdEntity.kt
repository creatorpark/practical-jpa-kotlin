package sample.jpa.a_entiity

import jakarta.persistence.Entity
import sample.jpa.a_entiity.id.TsId

@Entity
class TsIdEntity(
    val name: String
) : TsId()
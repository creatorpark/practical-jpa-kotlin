package sample.jpa.a_entiity

import jakarta.persistence.Entity
import sample.jpa.a_entiity.id.TsId

@Entity
class TsIdEntity(
    var name: String
) : TsId()
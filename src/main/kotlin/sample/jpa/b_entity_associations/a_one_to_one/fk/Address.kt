package sample.jpa.b_entity_associations.a_one_to_one.fk

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import sample.jpa.a_entiity.id.TsId


@Entity
class Address(
    val name: String,
    val uniformNumber: Int,
    user: User
) : TsId() {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TEAM_ID", referencedColumnName = "ID")
    var user: User = user
}



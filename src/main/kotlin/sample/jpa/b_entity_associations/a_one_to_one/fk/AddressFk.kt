package sample.jpa.b_entity_associations.a_one_to_one.fk

import jakarta.persistence.Entity
import sample.jpa.a_entiity.id.TsId


@Entity
class AddressFk(
    val name: String,
    val uniformNumber: Int,
    userFk: UserFk
) : TsId() {
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "TEAM_ID", referencedColumnName = "ID")
//    var userFk: UserFk = userFk
}



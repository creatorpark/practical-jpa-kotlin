package sample.jpa.b_entity_associations.b_many_to_one

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import sample.jpa.a_entiity.id.TsId


// 양방향으로 해야하는 이유
//
@Entity
class Player(
    val name: String,
    val uniformNumber: Int,
) : TsId() {

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    lateinit var team: Team


}



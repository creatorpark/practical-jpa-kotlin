package sample.jpa.c_entity_associations.b_many_to_one

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import sample.jpa.a_entiity.a_id.IncrementId

@Entity
class Player(
    var name: String,
    var uniformNumber: Int,
) : IncrementId() {

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "TEAM_ID", referencedColumnName = "ID")
    var team: Team? = null
}
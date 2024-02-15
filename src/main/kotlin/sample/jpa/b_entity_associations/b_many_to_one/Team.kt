package sample.jpa.b_entity_associations.b_many_to_one

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany
import sample.jpa.a_entiity.id.TsId

@Entity
class Team(
    val name: String
) : TsId() {

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], mappedBy = "team")
    var playerList: Set<Player> = mutableSetOf()
}
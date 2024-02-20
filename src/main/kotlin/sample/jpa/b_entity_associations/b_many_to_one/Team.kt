package sample.jpa.b_entity_associations.b_many_to_one

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import sample.jpa.a_entiity.id.IncrementId

/**
 * FK를 Player에서 관리한다. 하지만 조작은 Team에서 한다.
 * 이게 1:
 */
@Entity
class Team(
    var name: String
) : IncrementId() {

    @OneToMany(mappedBy = "team", cascade = [CascadeType.ALL], orphanRemoval = true)
    var players: MutableSet<Player> = mutableSetOf()

    fun addPlayer(player: Player) {
        player.team = this
        players.add(player)
    }

    fun removePlayer(player: Player) {
        players.remove(player)
    }
}
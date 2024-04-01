package sample.jpa.b_entity_associations.b_many_to_one

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import sample.jpa.a_entiity.a_id.IncrementId

/*
JoinColumn, mappedBy는 FK의 위치를 정하고 알려주는 것이다.
라이프 사이클 관리인 Cascade와 상관이 없다.
JoinColum이 OneToMany, ManyToOne마다 Source, Target 위치가 달라 헤깔린다.

orphanRemoval는 MERGE, REMOVE와 관련이 있다.
true이면 삭제시 Child도 삭제.
false이면 Child의 FK만 null로 update하고 그대로 둔다.
다만 반대편 관계 매핑에서 optional이 true면 문제 없지만
false이면 오류가 난다.
 */
@Entity
class Team(
    var name: String
) : IncrementId() {

    @OneToMany(mappedBy = "team", cascade = [CascadeType.ALL], orphanRemoval = false)
    var players: MutableSet<Player> = mutableSetOf()

    fun addPlayer(player: Player) {
        player.team = this
        players.add(player)
    }

    fun removePlayer(player: Player) {
        player.team = null
        players.remove(player)
    }
}
package sample.jpa.b_entity_associations.b_many_to_one.bi

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import sample.jpa.a_entiity.id.IncrementId

/**
 * N:1은 연관 관계의 주인을 N쪽에서 관리하겠다는 뜻이다.
 * https://spoqa.github.io/2022/08/16/kotlin-jpa-entity.html
 */
@Entity
class Player(
    var name: String,
    var uniformNumber: Int,
) : IncrementId() {
    @ManyToOne(optional = false)
    @JoinColumn(name = "TEAM_ID", referencedColumnName = "ID")
    lateinit var team: Team
}
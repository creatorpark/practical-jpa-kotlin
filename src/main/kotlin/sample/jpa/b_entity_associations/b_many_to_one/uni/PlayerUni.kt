package sample.jpa.b_entity_associations.b_many_to_one.uni

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import sample.jpa.a_entiity.id.IncrementId


@Entity
class PlayerUni(
    var name: String,
    var uniformNumber: Int,
) : IncrementId() {

    /**
     * If the join is for a OneToOne or ManyToOne mapping using a foreign key mapping strategy,
     * the foreign key column is in the table of
     * the source entity or embeddable.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TEAM_UNI_ID")
    lateinit var team: TeamUni
}



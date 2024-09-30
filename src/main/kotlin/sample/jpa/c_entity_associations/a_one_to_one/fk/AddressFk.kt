package sample.jpa.c_entity_associations.a_one_to_one.fk

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import sample.commons.toStringField
import sample.jpa.a_entiity.a_id.IncrementId


@Entity
class AddressFk(
    val street: String,
    val city: String
) : IncrementId() {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    var user: UserFk? = null

    override fun toString(): String {
        return this.toStringField("id", "street", "city")
    }
}

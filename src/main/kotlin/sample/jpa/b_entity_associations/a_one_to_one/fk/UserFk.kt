package sample.jpa.b_entity_associations.a_one_to_one.fk

import jakarta.persistence.Entity
import sample.jpa.a_entiity.id.TsId

@Entity
class UserFk(
    val name: String
) : TsId() {

//    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], mappedBy = "user")
//    var addressFks: MutableSet<AddressFk> = mutableSetOf()
}
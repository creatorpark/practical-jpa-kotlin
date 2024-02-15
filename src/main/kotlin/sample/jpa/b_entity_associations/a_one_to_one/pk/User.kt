package sample.jpa.b_entity_associations.a_one_to_one.pk

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany
import sample.jpa.a_entiity.id.TsId

@Entity
class User(
    val name: String
) : TsId() {

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], mappedBy = "user")
    var addresses: MutableSet<Address> = mutableSetOf()
}
package sample.jpa.b_entity_associations.c_one_to_many.uni

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany
import sample.jpa.a_entiity.id.TsId

@Entity
class OrderUni(
    val name: String
) : TsId() {
    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    var orderUniItems: MutableSet<OrderUniItem> = mutableSetOf()
}
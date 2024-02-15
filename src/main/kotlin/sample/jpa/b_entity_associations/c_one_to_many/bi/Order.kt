package sample.jpa.b_entity_associations.c_one_to_many.bi

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany
import sample.jpa.a_entiity.id.TsId

@Entity
class Order(
    val name: String
) : TsId() {

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], mappedBy = "team")
    var orderItemList: List<OrderItem> = mutableListOf()
}
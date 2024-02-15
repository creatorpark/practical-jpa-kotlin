package sample.jpa.b_entity_associations.c_one_to_many.bi

import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import sample.jpa.a_entiity.id.TsId


// 양방향으로 해야하는 이유
// 
@Entity
class OrderItem(
    order: Order,
    var name: String,
    var count: Long,
    var price: Long
) : TsId() {
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    var order: Order = order
}
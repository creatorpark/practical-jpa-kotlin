package sample.jpa.b_entity_associations.a_one_to_one.fk

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToOne
import sample.jpa.a_entiity.id.IncrementId

@Entity
class UserFk(
    val name: String,
    address: AddressFk
) : IncrementId() {

    init {
        address.user = this
    }

    @OneToOne(
        mappedBy = "user", cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY,
        orphanRemoval = true // 이 조건을 줘야 Address가 Clear 된다.
    )
    var address: AddressFk? = address
        set(value) {
            field = value
            value?.user = this
        }
}
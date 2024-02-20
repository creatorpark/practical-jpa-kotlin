package sample.jpa.b_entity_associations.a_one_to_one.pk

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.MapsId
import jakarta.persistence.OneToOne

@Entity
class Address(
    val street: String,
    val city: String
) {
    @Id
    val id: Long? = null

    @OneToOne
    @MapsId // Child PK가 Parent PK와 동일한 경우
    lateinit var user: User
}



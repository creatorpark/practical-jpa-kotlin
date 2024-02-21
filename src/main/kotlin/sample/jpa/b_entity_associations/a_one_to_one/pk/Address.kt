package sample.jpa.b_entity_associations.a_one_to_one.pk

import jakarta.persistence.*

@Entity
class Address(
    val street: String,
    val city: String
) {
    @Id
    val id: Long? = null

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId // Child PK가 Parent PK와 동일한 경우
    lateinit var user: User
}



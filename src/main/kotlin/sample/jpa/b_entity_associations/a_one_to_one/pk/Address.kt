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
    @MapsId // Child PK가 Parent PK와 동일한 경우 == FK(JoinColumn)를 Child의 PK로 매핑하고 싶은 경우
    // 그러나 이름을 변경하고 싶으면 JoinColum을 써서 명시해줘야 한다. 그지같다..?ㅋㅋㅋ
    lateinit var user: User
}



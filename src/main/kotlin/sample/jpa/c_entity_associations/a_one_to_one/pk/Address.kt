package sample.jpa.c_entity_associations.a_one_to_one.pk

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
    // == FK(JoinColumn)를 Child의 PK로 매핑하고 싶은 경우
    // 그러나 칼럼명을 변경하려면 JoinColum을 써서 명시적으로 해줘야한다.
    // 그지같다..?ㅋㅋㅋ
    lateinit var user: User
}



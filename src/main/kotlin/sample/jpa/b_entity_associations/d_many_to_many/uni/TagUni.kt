package sample.jpa.b_entity_associations.d_many_to_many.uni

import jakarta.persistence.Entity
import sample.jpa.a_entiity.id.TsId


// 양방향으로 해야하는 이유
// 
@Entity
class TagUni(
    val name: String
) : TsId() {

//    @ManyToMany
//    @JoinColumn(name = "POST_ID")
//    lateinit var postUni: PostUni

}



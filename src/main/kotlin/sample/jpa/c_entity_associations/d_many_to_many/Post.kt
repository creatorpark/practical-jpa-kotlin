package sample.jpa.c_entity_associations.d_many_to_many

import jakarta.persistence.*
import sample.jpa.a_entiity.a_id.IncrementId

@Entity
class Post(
    var title: String,
    var contents: String,
) : IncrementId() {

    @JoinTable(
        name = "post_tag",
        joinColumns = [JoinColumn(name = "POST_ID")],
        inverseJoinColumns = [JoinColumn(name = "TAG_ID")]
    )
    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    val tags: MutableSet<Tag> = mutableSetOf()
    // M:N에서 List로 만들 경우, DELETE, INSERT로 조인 테이블을 관리한다. 비효율적

    fun addTag(tag: Tag) {
        tags.add(tag)
        tag.posts.add(this)
    }

    fun removeTag(tag: Tag) {
        tags.remove(tag)
        tag.posts.remove(this)
    }
}
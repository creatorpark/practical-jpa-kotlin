package sample.jpa.b_entity_associations.d_many_to_many.bi

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : CrudRepository<Post, Long>

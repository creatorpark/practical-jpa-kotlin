package sample.jpa.b_entity_associations.a_one_to_one.fk

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserFkRepository : JpaRepository<UserFk, Long>

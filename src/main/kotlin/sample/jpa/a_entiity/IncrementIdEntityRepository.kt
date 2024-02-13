package sample.jpa.a_entiity

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface IncrementIdEntityRepository : CrudRepository<IncrementIdEntity, Long>

package sample.jpa

import io.github.oshai.kotlinlogging.KotlinLogging
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
import java.sql.Connection
import javax.sql.DataSource

// https://www.baeldung.com/spring-jdbctemplate-testing
// https://hyuk0309.tistory.com/4
@JdbcTest
class DataSourceTests {

    private val log = KotlinLogging.logger {}

    @Autowired
    lateinit var dataSource: DataSource

    @Test
    fun connectTest(): Unit {
        val conn: Connection = dataSource.connection
        log.debug { "Connection ${conn} " }
        log.debug { "Connection is Closed? ${conn.isClosed} " }
        log.debug { "Connection catalog ${conn.catalog} " }
    }

}

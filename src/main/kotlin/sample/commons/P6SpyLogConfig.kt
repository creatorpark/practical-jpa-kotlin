package sample.commons

import com.p6spy.engine.spy.P6SpyOptions
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration

@Configuration
class P6SpyLogConfig {

    @PostConstruct
    fun setLogMessageFormat() {
        // Spy Option
        P6SpyOptions.getActiveInstance().logMessageFormat = P6SpySqlOption::class.java.name
    }
}
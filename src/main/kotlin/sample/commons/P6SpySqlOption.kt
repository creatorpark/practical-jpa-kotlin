package sample.commons

import com.p6spy.engine.logging.Category
import com.p6spy.engine.spy.appender.MessageFormattingStrategy
import io.github.oshai.kotlinlogging.KotlinLogging
import org.hibernate.engine.jdbc.internal.FormatStyle

class P6SpySqlOption : MessageFormattingStrategy {
    private val log = KotlinLogging.logger { }

    override fun formatMessage(
        connectionId: Int,
        now: String,
        elapsed: Long,
        category: String,
        prepared: String,
        sql: String?,
        url: String
    ): String {
        return "[ID=" + connectionId + "|" + category + "] " + elapsed + " ms" + formatSql(category, sql)
    }

    private fun formatSql(category: String, sql: String?): String? {
        log.debug { "Category : $category" }
        log.debug { "Sql : $sql" }
        if (sql == null || sql.trim { it <= ' ' } == "") return sql
        // Only format Statement, distinguish DDL And DML
        if (Category.STATEMENT.name == category) {
            val tmpsql = sql.trim { it <= ' ' }
                .lowercase()
            var newSql =
                if (tmpsql.startsWith("create") || tmpsql.startsWith("alter") || tmpsql.startsWith("comment")) {
                    FormatStyle.DDL.formatter.format(sql)
                } else {
                    FormatStyle.BASIC.formatter.format(sql)
                }
            return newSql
        } else {
            return sql
        }
    }
}
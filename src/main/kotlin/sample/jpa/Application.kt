package sample.jpa

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport.ConditionAndOutcome
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport.ConditionAndOutcomes
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.function.Consumer

@SpringBootApplication
class SampleJpaApplication

fun main(args: Array<String>) {
    runApplication<SampleJpaApplication>(*args)
}

@Bean
fun run(report: ConditionEvaluationReport): ApplicationRunner {
    return ApplicationRunner { args: ApplicationArguments? ->
        report.conditionAndOutcomesBySource.entries.stream()
            .filter { (_, value): Map.Entry<String?, ConditionAndOutcomes> -> value.isFullMatch() }
            .filter { (key): Map.Entry<String, ConditionAndOutcomes?> ->
                !key.contains(
                    "Jmx"
                )
            }
            .forEach { (key, value): Map.Entry<String?, ConditionAndOutcomes> ->
                println(key)
                value.forEach(Consumer { c: ConditionAndOutcome ->
                    println(
                        "\t" + c.outcome
                    )
                })
                println()
            }
    }
}

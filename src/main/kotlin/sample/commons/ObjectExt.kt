package sample.commons

import kotlin.reflect.full.memberProperties

inline fun <reified T : Any> T.toStringField(
    vararg fieldNames: String = T::class.memberProperties.map { it.name }
        .toTypedArray()
): String {
    val propertiesMap = T::class.memberProperties.associateBy { it.name }

    return buildString {
        append("(")
        val fieldsToAppend = fieldNames
            .mapNotNull { propertiesMap[it] }
            .joinToString(", ") { "${it.name}=${it.get(this@toStringField)}" }
        append(fieldsToAppend)
        append(")")
    }
}
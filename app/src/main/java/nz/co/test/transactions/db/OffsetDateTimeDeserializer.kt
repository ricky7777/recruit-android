package nz.co.test.transactions.db

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import nz.co.test.transactions.utils.DateUtils
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

/**
 * @author Ricky Chen
 * parsing json string to OffsetDateTime when parse fail
 */
class OffsetDateTimeDeserializer : JsonDeserializer<OffsetDateTime> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): OffsetDateTime {
        val dateStr = json.asString
        return try {
            OffsetDateTime.parse(dateStr, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        } catch (e: Exception) {
            val localDateTime = LocalDateTime.parse(dateStr, DateUtils.DATE_LOCAL_FORMATTER)
            OffsetDateTime.of(localDateTime, ZoneOffset.UTC)
        }
    }
}
package nz.co.test.transactions.db

import androidx.room.TypeConverter
import java.math.BigDecimal
import java.time.OffsetDateTime

/**
 * @author Ricky Chen
 * Responsible transactionDate to OffsetDateTime format
 */
class Converters {
    @TypeConverter
    fun fromOffsetDateTime(offsetDateTime: OffsetDateTime?): String? {
        return offsetDateTime?.toString()
    }

    @TypeConverter
    fun toOffsetDateTime(value: String?): OffsetDateTime? {
        return value?.let { OffsetDateTime.parse(it) }
    }

    @TypeConverter
    fun fromBigDecimal(value: BigDecimal?): String? {
        return value?.toPlainString()
    }

    @TypeConverter
    fun toBigDecimal(value: String?): BigDecimal? {
        return value?.let { BigDecimal(it) }
    }

}
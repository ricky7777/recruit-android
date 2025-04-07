package nz.co.test.transactions.room

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * @author Ricky Chen
 * Responsible transactionDate to date format
 */
class Converters {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())

    @TypeConverter
    fun fromDate(date: Date?): String? {
        return date?.let { dateFormat.format(it) }
    }

    @TypeConverter
    fun toDate(dateString: String?): Date? {
        return dateString?.let { dateFormat.parse(it) }
    }
}
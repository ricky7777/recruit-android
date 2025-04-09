package nz.co.test.transactions.utils

import java.time.format.DateTimeFormatter

/**
 * @author Ricky Chen
 * Date related utils
 */
object DateUtils {
    val SHORT_DATE_FORMATTER = DateTimeFormatter.ofPattern("EEE dd MMM yyyy")
    val DATE_FORMATTER_DETAIL = DateTimeFormatter.ofPattern("EEE dd MMM yyyy 'at' h:mma")
    val DATE_LOCAL_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
}
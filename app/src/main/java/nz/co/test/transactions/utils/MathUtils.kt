package nz.co.test.transactions.utils

import java.math.BigDecimal
import java.util.Locale

/**
 * @author Ricky Chen
 * common math share utils
 */
object MathUtils {
    fun BigDecimal.toNZFormattedString(): String {
        return if (this < BigDecimal.ZERO) {
            "-$" + String.format(Locale("en", "NZ"), "%.2f", this.abs())
        } else {
            "$" + String.format(Locale("en", "NZ"), "%.2f", this)
        }
    }
}
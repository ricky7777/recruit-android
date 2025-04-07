package nz.co.test.transactions.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.time.OffsetDateTime

/**
 *  This is data class for transaction data
 *  receiver raw data from api and convert to room entity
 */
@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey val id: Int,
    val transactionDate: OffsetDateTime,
    val summary: String,
    val debit: BigDecimal,
    val credit: BigDecimal
)
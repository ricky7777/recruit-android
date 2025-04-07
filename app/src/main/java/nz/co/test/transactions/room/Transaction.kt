package nz.co.test.transactions.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 * @author Ricky Chen
 * This is data class for transaction data
 * receiver raw data from api and convert to room entity
 */
@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey val id: Int,
    val transactionDate: Date,
    val summary: String,
    val debit: Double,
    val credit: Double
)
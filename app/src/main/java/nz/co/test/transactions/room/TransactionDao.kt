package nz.co.test.transactions.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * @author Ricky Chen
 * Transaction Dao interface, query/insert
 * communicate with room db
 */
@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transaction)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransactions(transactions: List<Transaction>)

    @Query("SELECT * FROM transactions")
    suspend fun getAllTransactions(): List<Transaction>

    @Query("SELECT * FROM transactions WHERE id = :id")
    suspend fun getTransactionById(id: Int): Transaction?

    @Delete
    suspend fun deleteTransaction(transaction: Transaction)
}
package nz.co.test.transactions.di.network

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import nz.co.test.transactions.db.Transaction
import nz.co.test.transactions.db.TransactionDao
import nz.co.test.transactions.services.TransactionsService

/**
 * @author Ricky Chen
 * Transaction Repository
 */
class TransactionsRepositoryImpl(
    private val transactionsService: TransactionsService,
    private val transactionDao: TransactionDao
) : TransactionsRepository {
    override suspend fun getTransactions(): List<Transaction> = withContext(Dispatchers.IO) {
        try {
            val transactionsArray = transactionsService.retrieveTransactions()
            val transactionsList = transactionsArray.toList()
            transactionDao.insertTransactions(transactionsList)
            transactionsList
        } catch (e: Exception) {
            Log.i("error", "error:" + e.printStackTrace().toString())
            transactionDao.getAllTransactions()
        }
    }
}
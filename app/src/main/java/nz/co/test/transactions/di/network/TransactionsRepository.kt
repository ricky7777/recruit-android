package nz.co.test.transactions.di.network

import nz.co.test.transactions.room.Transaction
import nz.co.test.transactions.services.TransactionsService
import javax.inject.Inject

/**
 * @author Ricky Chen
 * Transaction Repository
 */
class TransactionsRepository @Inject constructor(
    private val transactionsService: TransactionsService
) {
    suspend fun getTransactions(): List<Transaction> {
        return transactionsService.retrieveTransactions()
    }
}
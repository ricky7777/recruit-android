package nz.co.test.transactions.di.network

import nz.co.test.transactions.room.Transaction

/**
 * @author Ricky Chen
 * TransactionsRepository interface, use interface is for testing
 */
interface TransactionsRepository {
    suspend fun getTransactions(): List<Transaction>
}
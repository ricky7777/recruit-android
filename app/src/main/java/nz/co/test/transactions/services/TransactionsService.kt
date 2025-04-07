package nz.co.test.transactions.services

import nz.co.test.transactions.room.Transaction
import retrofit2.http.GET

interface TransactionsService {
    @GET("transactions")
    suspend fun retrieveTransactions(): List<Transaction>
}


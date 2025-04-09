package nz.co.test.transactions

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.test.runTest
import nz.co.test.transactions.di.network.TransactionsRepository
import nz.co.test.transactions.db.Transaction
import nz.co.test.transactions.utils.MathUtils.toNZFormattedString
import nz.co.test.transactions.viewmodel.TransactionsViewModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.math.BigDecimal
import java.time.OffsetDateTime

class TransactionsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dummyTransactions = listOf(
        Transaction(
            id = 1,
            transactionDate = OffsetDateTime.now(),
            summary = "Income Transaction",
            debit = BigDecimal.ZERO,
            credit = BigDecimal("100.00")
        ),
        Transaction(
            id = 2,
            transactionDate = OffsetDateTime.now(),
            summary = "Expense Transaction",
            debit = BigDecimal("25.00"),
            credit = BigDecimal.ZERO
        )
    )

    private val fakeRepository = object : TransactionsRepository {
        override suspend fun getTransactions(): List<Transaction> = dummyTransactions
    }

    @Test
    fun testLoadTransactionsAndTotalAmount() = runTest {
        val viewModel = TransactionsViewModel(fakeRepository)
        viewModel.loadTransactions()

        val expectedTotalText = BigDecimal("75.00").toNZFormattedString()
        val actualTotalText = viewModel.getTotalAmountText()

        assertEquals(expectedTotalText, actualTotalText)
    }
}
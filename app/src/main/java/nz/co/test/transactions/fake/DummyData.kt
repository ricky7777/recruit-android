package nz.co.test.transactions.fake

import java.math.BigDecimal
import java.time.OffsetDateTime
import nz.co.test.transactions.db.Transaction

class DummyData {
    companion object {
        val dummyTransactions = listOf(
            Transaction(
                id = 1,
                transactionDate = OffsetDateTime.parse("2021-08-31T15:47:10+00:00"),
                summary = "Payment Received",
                debit = BigDecimal.ZERO,
                credit = BigDecimal("150.50")
            ),
            Transaction(
                id = 2,
                transactionDate = OffsetDateTime.parse("2021-09-01T10:15:20+00:00"),
                summary = "Grocery Shopping",
                debit = BigDecimal("75.80"),
                credit = BigDecimal.ZERO
            ),
            Transaction(
                id = 3,
                transactionDate = OffsetDateTime.parse("2021-09-02T18:30:00+00:00"),
                summary = "Utility Bill",
                debit = BigDecimal("120.00"),
                credit = BigDecimal.ZERO
            ),
            Transaction(
                id = 4,
                transactionDate = OffsetDateTime.parse("2021-09-03T09:45:35+00:00"),
                summary = "Salary",
                debit = BigDecimal.ZERO,
                credit = BigDecimal("2000.00")
            ),
            Transaction(
                id = 5,
                transactionDate = OffsetDateTime.parse("2021-09-04T14:20:15+00:00"),
                summary = "Coffee Shop",
                debit = BigDecimal("5.25"),
                credit = BigDecimal.ZERO
            )
        )
    }
}
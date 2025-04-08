package nz.co.test.transactions.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nz.co.test.transactions.fake.DummyData
import nz.co.test.transactions.room.Transaction
import nz.co.test.transactions.utils.MathUtils.toNZFormattedString
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionDetailsBottomSheet(
    transaction: Transaction,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color(0xFF3F3E3E)
    ) {
        TransactionDetailContent(transaction)
    }
}

@Composable
private fun TransactionDetailContent(transaction: Transaction) {
    val amount: BigDecimal = when {
        transaction.debit > BigDecimal.ZERO -> transaction.debit.negate()
        transaction.credit > BigDecimal.ZERO -> transaction.credit
        else -> BigDecimal.ZERO
    }

    val gst: BigDecimal = if (amount.compareTo(BigDecimal.ZERO) != 0) {
        amount.subtract(amount.divide(BigDecimal("1.15"), 2, RoundingMode.HALF_UP))
    } else {
        BigDecimal.ZERO
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Details",
            style = MaterialTheme.typography.titleLarge.copy(color = Color.White)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Transaction",
            style = MaterialTheme.typography.titleMedium.copy(color = Color.White)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = transaction.summary,
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.LightGray)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Date",
            style = MaterialTheme.typography.titleMedium.copy(color = Color.White)
        )
        Spacer(modifier = Modifier.height(8.dp))
        val dateTimeString = transaction.transactionDate.format(
            DateTimeFormatter.ofPattern("EEE dd MMM yyyy 'at' h:mma")
        )
        Text(
            text = dateTimeString,
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.LightGray)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Summary",
            style = MaterialTheme.typography.titleMedium.copy(color = Color.White)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = transaction.summary,
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.LightGray)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Amount",
            style = MaterialTheme.typography.titleMedium.copy(color = Color.White)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = amount.toNZFormattedString(),
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.LightGray)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // 加入 GST 欄位
        Text(
            text = "GST(15%)",
            style = MaterialTheme.typography.titleMedium.copy(color = Color.White)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = gst.toNZFormattedString(),
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.LightGray)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionDetailContentPreview() {
    TransactionDetailContent(transaction = DummyData.dummyTransactions[0])
}
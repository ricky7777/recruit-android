package nz.co.test.transactions.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import nz.co.test.transactions.R
import nz.co.test.transactions.fake.DummyData
import nz.co.test.transactions.room.Transaction
import nz.co.test.transactions.utils.MathUtils.toNZFormattedString
import nz.co.test.transactions.viewmodel.TransactionsViewModel
import nz.co.test.transactions.widget.LoadingBar
import java.math.BigDecimal
import java.time.format.DateTimeFormatter

/**
 * @author Ricky Chen
 * Transaction Screen
 */
@Composable
fun TransactionScreen(transactionsViewModel: TransactionsViewModel) {
    val transactions by transactionsViewModel.transactions.observeAsState(emptyList())
    val totalAmount by remember(transactions) {
        derivedStateOf { transactionsViewModel.getTotalAmountText() }
    }

    var selectedTransaction by remember { mutableStateOf<Transaction?>(null) }

    Scaffold(backgroundColor = Color.Black) { innerPadding ->
        if (transactions.isEmpty()) {
            LoadingScreen(innerPadding)
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    backgroundColor = Color.Black,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .background(Color.Yellow, shape = CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.logo_asb),
                                contentDescription = "ASB Logo",
                                Modifier.background(Color.Transparent)
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = totalAmount,
                            style = MaterialTheme.typography.h4.copy(color = Color.White)
                        )
                    }
                }

                val groupedByDate = transactions.groupBy {
                    it.transactionDate.toLocalDate()
                }.toSortedMap(reverseOrder())

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {
                    groupedByDate.forEach { (date, transList) ->
                        item {
                            val formattedDate = date.format(
                                DateTimeFormatter.ofPattern("EEE dd MMM yyyy")
                            )
                            Text(
                                text = formattedDate,
                                style = MaterialTheme.typography.subtitle2.copy(color = Color.Gray),
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                        items(transList) { tx ->
                            TransactionRow(tx, onClick = { clickedTx ->
                                selectedTransaction = clickedTx
                            })
                        }
                    }
                }
            }
        }

        selectedTransaction?.let {
            TransactionDetailsBottomSheet(
                transaction = it,
                onDismiss = { selectedTransaction = null }
            )
        }
    }
}

@Composable
private fun LoadingScreen(innerPadding: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {
        LoadingBar(message = "Loading data...")
    }
}

@Composable
private fun TransactionRow(tx: Transaction, onClick: (Transaction) -> Unit) {
    val amount = when {
        tx.debit > BigDecimal.ZERO -> -tx.debit
        tx.credit > BigDecimal.ZERO -> tx.credit
        else -> BigDecimal.ZERO
    }

    val amountColor = if (amount < BigDecimal.ZERO) Color.Red else Color.Green

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(tx) }
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = tx.summary, color = Color.White, style = MaterialTheme.typography.body1
        )
        Text(
            text = amount.toNZFormattedString(),
            color = amountColor,
            style = MaterialTheme.typography.body1
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    val amount = BigDecimal(91236.459123).toNZFormattedString()
//    TransactionScreen(DummyData.dummyTransactions, amount)
}
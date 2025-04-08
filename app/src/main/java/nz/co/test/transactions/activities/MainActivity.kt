package nz.co.test.transactions.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import nz.co.test.transactions.fake.DummyData
import nz.co.test.transactions.screen.TransactionScreen
import nz.co.test.transactions.utils.MathUtils.toNZFormattedString
import nz.co.test.transactions.viewmodel.TransactionsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.math.BigDecimal

/**
 * @author Ricky Chen
 * Main Entry point, use jetpack compose make UI
 */
class MainActivity : AppCompatActivity() {
    private val transactionsViewModel: TransactionsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        transactionsViewModel.loadTransactions()
        setContent {
//            val transactions by transactionsViewModel.transactions.observeAsState()
//            val totalAmount = transactionsViewModel.getTotalAmountText()
            //            setContent { TransactionScreen(transactions, totalAmount) }
            val amount = BigDecimal(91236.459123).toNZFormattedString()
            TransactionScreen(DummyData.dummyTransactions, amount)

        }
    }
}
package nz.co.test.transactions.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import nz.co.test.transactions.screen.TransactionScreen
import nz.co.test.transactions.viewmodel.TransactionsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Ricky Chen
 * Main Entry point, use jetpack compose make UI
 */
class MainActivity : AppCompatActivity() {
    private val transactionsViewModel: TransactionsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        transactionsViewModel.loadTransactions()
        setContent {
            setContent { TransactionScreen(transactionsViewModel) }
        }
    }
}
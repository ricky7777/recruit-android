package nz.co.test.transactions.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import nz.co.test.transactions.manager.LoadingManager
import nz.co.test.transactions.utils.LoadingBar
import nz.co.test.transactions.viewmodel.TransactionsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Ricky Chen
 * Main Entry point, use jetpack compose make UI
 */
class MainActivity : AppCompatActivity() {
    private val transactionsViewModel: TransactionsViewModel by viewModel()

    lateinit var loadingManager: LoadingManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        transactionsViewModel.loadTransactions()
        setContent {
            val transactions by transactionsViewModel.transactions.observeAsState()

            Scaffold { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    if (transactions == null) {
                        LoadingBar(message = "Loading data...")
                    } else {
                        Text(text = "Loaded ${transactions!!.size} transactions")
                    }
                }
            }
        }
    }
}
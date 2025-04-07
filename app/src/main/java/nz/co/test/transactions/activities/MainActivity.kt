package nz.co.test.transactions.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModelProvider
import nz.co.test.transactions.di.DaggerAppComponent
import nz.co.test.transactions.manager.LoadingManager
import nz.co.test.transactions.utils.LoadingBar
import nz.co.test.transactions.viewmodel.TransactionsViewModel
import javax.inject.Inject

/**
 * @author Ricky Chen
 * Main Entry point, use jetpack compose make UI
 */
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var transactionsViewModel: TransactionsViewModel

    @Inject
    lateinit var loadingManager: LoadingManager

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerAppComponent.create().inject(this)
        super.onCreate(savedInstanceState)

        transactionsViewModel = ViewModelProvider(this, viewModelFactory)[TransactionsViewModel::class.java]

        transactionsViewModel.loadTransactions()

        setContent {
            val transactions by transactionsViewModel.transactions.observeAsState()
            if (transactions == null) {
                LoadingBar(message = "Loading data...")
            } else {
                // 資料取得後，隱藏 loading bar 並顯示資料已載入的訊息
                Text(text = "Loaded ${transactions?.size} transactions")
            }
            LoadingBar(message = loadingManager.getLoadingMessage())
        }
    }
}
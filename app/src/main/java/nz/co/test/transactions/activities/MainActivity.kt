package nz.co.test.transactions.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import nz.co.test.transactions.di.DaggerAppComponent
import nz.co.test.transactions.manager.LoadingManager
import nz.co.test.transactions.utils.LoadingBar
import javax.inject.Inject

/**
 * @author Ricky Chen
 * Main Entry point, use jetpack compose make UI
 */
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var loadingManager: LoadingManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAppComponent.create().inject(this)
        setContent {
            LoadingBar(message = loadingManager.getLoadingMessage())
        }
    }
}
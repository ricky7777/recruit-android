package nz.co.test.transactions

import android.app.Application
import nz.co.test.transactions.db.ASBAppDatabase
import nz.co.test.transactions.db.DatabaseProvider
import nz.co.test.transactions.di.network.NetworkProvider
import nz.co.test.transactions.di.network.TransactionsRepositoryImpl
import nz.co.test.transactions.services.TransactionsService
import nz.co.test.transactions.viewmodel.TransactionsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * @author Ricky Chen
 * extends Application for retrofit/room/koin setting
 */
class ASBApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        diSetting()
    }

    private fun diSetting(){
        val networkModule = module {
            single { NetworkProvider.provideRetrofit() }
            single<TransactionsService> { get<Retrofit>().create(TransactionsService::class.java) }
        }

        val roomModule = module {
            single { DatabaseProvider.provideDatabase(androidContext()) }
            single { get<ASBAppDatabase>().transactionDao() }
        }

        val repositoryModule = module {
            single { TransactionsRepositoryImpl(get(), get()) }
        }

        val viewModelModule = module {
            viewModel { TransactionsViewModel(get()) }
        }

        startKoin {
            androidContext(this@ASBApplication)
            modules(networkModule, roomModule, repositoryModule, viewModelModule)
        }
    }
}
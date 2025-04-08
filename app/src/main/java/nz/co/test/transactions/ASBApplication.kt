package nz.co.test.transactions

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import nz.co.test.transactions.di.network.TransactionsRepository
import nz.co.test.transactions.room.ASBAppDatabase
import nz.co.test.transactions.room.OffsetDateTimeDeserializer
import nz.co.test.transactions.services.TransactionsService
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.koin.androidx.viewmodel.dsl.viewModel

import nz.co.test.transactions.viewmodel.TransactionsViewModel
import java.time.OffsetDateTime

class ASBApplication : Application() {
    companion object {
        private const val BASE_URL =
            "https://gist.githubusercontent.com/Josh-Ng/500f2716604dc1e8e2a3c6d31ad01830/raw/4d73acaa7caa1167676445c922835554c5572e82/"
    }

    val gson: Gson = GsonBuilder()
        .registerTypeAdapter(OffsetDateTime::class.java, OffsetDateTimeDeserializer())
        .create()

    override fun onCreate() {
        super.onCreate()
        val networkModule = module {
            single {
                Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }
            single<TransactionsService> { get<Retrofit>().create(TransactionsService::class.java) }
        }

        val roomModule = module {
            single {
                Room.databaseBuilder(
                    androidContext(),
                    ASBAppDatabase::class.java,
                    "transaction_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            single { get<ASBAppDatabase>().transactionDao() }
        }

        val repositoryModule = module {
            single { TransactionsRepository(get(), get()) }
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
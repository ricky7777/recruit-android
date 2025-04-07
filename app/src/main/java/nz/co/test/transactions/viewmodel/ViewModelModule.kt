package nz.co.test.transactions.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import nz.co.test.transactions.di.network.TransactionsRepository
import nz.co.test.transactions.viewmodel.TransactionsViewModel
import nz.co.test.transactions.viewmodel.ViewModelFactory


@Module
abstract class ViewModelModule {

    @Provides
    fun provideTransactionsViewModel(repository: TransactionsRepository): TransactionsViewModel {
        return TransactionsViewModel(repository)
    }

    @Provides
    fun provideViewModelMap(
        transactionsViewModel: TransactionsViewModel
    ): Map<Class<out ViewModel>, Provider<ViewModel>> {
        return mapOf(
            TransactionsViewModel::class.java to object : Provider<ViewModel> {
                override fun get(): ViewModel = transactionsViewModel
            }
        )
    }

    @Provides
    fun provideViewModelFactory(
        viewModelMap: Map<Class<out ViewModel>,
                @JvmSuppressWildcards Provider<ViewModel>>
    ): ViewModelProvider.Factory {
        return ViewModelFactory(viewModelMap)
    }
}
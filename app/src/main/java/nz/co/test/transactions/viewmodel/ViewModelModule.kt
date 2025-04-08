package nz.co.test.transactions.viewmodel


/**
 * @author Ricky Chen
 * use koin to replace
 */
//@Module
//abstract class ViewModelModule {
//
//    @Provides
//    fun provideTransactionsViewModel(repository: TransactionsRepository): TransactionsViewModel {
//        return TransactionsViewModel(repository)
//    }
//
//    @Provides
//    fun provideViewModelMap(
//        transactionsViewModel: TransactionsViewModel
//    ): Map<Class<out ViewModel>, Provider<ViewModel>> {
//        return mapOf(
//            TransactionsViewModel::class.java to object : Provider<ViewModel> {
//                override fun get(): ViewModel = transactionsViewModel
//            }
//        )
//    }
//
//    @Provides
//    fun provideViewModelFactory(
//        viewModelMap: Map<Class<out ViewModel>,
//                @JvmSuppressWildcards Provider<ViewModel>>
//    ): ViewModelProvider.Factory {
//        return ViewModelFactory(viewModelMap)
//    }
//}
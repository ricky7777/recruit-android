package nz.co.test.transactions.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.internal.Provider
import javax.inject.Inject

/**
 * @author Ricky Chen
 * use koin to replace
 */
//class ViewModelFactory @Inject constructor(
//    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
//) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
//            modelClass.isAssignableFrom(it.key)
//        }?.value ?: throw IllegalArgumentException("Unknown model class $modelClass")
//        return try {
//            @Suppress("UNCHECKED_CAST")
//            creator.get() as T
//        } catch (e: Exception) {
//            throw RuntimeException(e)
//        }
//    }
//}
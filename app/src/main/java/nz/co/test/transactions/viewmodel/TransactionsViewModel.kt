package nz.co.test.transactions.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nz.co.test.transactions.di.network.TransactionsRepositoryImpl
import nz.co.test.transactions.db.Transaction
import nz.co.test.transactions.utils.MathUtils.toNZFormattedString
import java.math.BigDecimal

/**
 * @author Ricky Chen
 * transaction view model
 * include main logic
 */
class TransactionsViewModel(
    private val repository: TransactionsRepositoryImpl
) : ViewModel() {

    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> = _transactions

    fun loadTransactions() {
        viewModelScope.launch {
            try {
                _transactions.value = repository.getTransactions()
            } catch (e: Exception) {
                // TODO error handling
                Log.e("Error", Log.getStackTraceString(e))
            }
        }
    }

    fun getTotalAmountText(): String {
        val currentTransactions = _transactions.value
        val total: BigDecimal = currentTransactions.fold(BigDecimal.ZERO) { acc, transaction ->
            acc.add(transaction.credit).subtract(transaction.debit)
        }
        return total.toNZFormattedString()
    }
}
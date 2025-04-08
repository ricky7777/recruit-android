package nz.co.test.transactions.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nz.co.test.transactions.di.network.TransactionsRepository
import nz.co.test.transactions.room.Transaction
import nz.co.test.transactions.utils.MathUtils.toNZFormattedString
import java.math.BigDecimal

class TransactionsViewModel(
    private val repository: TransactionsRepository
) : ViewModel() {

    private val _transactions = MutableLiveData<List<Transaction>>()
    val transactions: LiveData<List<Transaction>> = _transactions

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
        val currentTransactions = _transactions.value ?: return "0"
        val total: BigDecimal = currentTransactions.fold(BigDecimal.ZERO) { acc, transaction ->
            acc.add(transaction.credit).subtract(transaction.debit)
        }
        return total.toNZFormattedString()
    }
}
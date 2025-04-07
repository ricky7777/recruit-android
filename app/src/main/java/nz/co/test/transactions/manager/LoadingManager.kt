package nz.co.test.transactions.manager

import javax.inject.Inject

class LoadingManager @Inject constructor() {
        fun getLoadingMessage(): String = "Loading, please wait..."
}
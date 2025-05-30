package nz.co.test.transactions.di

import android.app.Activity
import android.content.Intent
import androidx.core.app.AppComponentFactory
import javax.inject.Inject
import javax.inject.Provider

@Deprecated("Use Koin DI to replace")
class DaggerAppComponentFactory : AppComponentFactory() {

//    private val component = DaggerAppComponent.create()

//    init {
//        component.inject(this)
//    }

    @Inject
    lateinit var map: Map<Class<out Activity>, @JvmSuppressWildcards Provider<Activity>>

    override fun instantiateActivityCompat(
        cl: ClassLoader,
        className: String,
        intent: Intent?
    ): Activity {
        val activity = super.instantiateActivityCompat(cl, className, intent)
        return map[activity::class.java]?.get() ?: activity
    }
}
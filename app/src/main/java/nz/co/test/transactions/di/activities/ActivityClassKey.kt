package nz.co.test.transactions.di.activities

import android.app.Activity
import dagger.MapKey
import kotlin.reflect.KClass

@Deprecated("Use Koin DI to replace")
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ActivityClassKey(val value: KClass<out Activity>)
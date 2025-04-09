package nz.co.test.transactions.di.activities

import android.app.Activity
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import nz.co.test.transactions.activities.MainActivity


@Deprecated("Use Koin DI to replace")
@Module
class ActivitiesModule {

    @Provides
    @IntoMap
    @ActivityClassKey(MainActivity::class)
    fun providesMainActivity(): Activity = MainActivity()
}
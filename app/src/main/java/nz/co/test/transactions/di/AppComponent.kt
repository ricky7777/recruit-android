package nz.co.test.transactions.di

import dagger.Component
import nz.co.test.transactions.activities.MainActivity
import nz.co.test.transactions.di.activities.ActivitiesModule
import nz.co.test.transactions.di.network.NetworkModule
import nz.co.test.transactions.viewmodel.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        ActivitiesModule::class,
        ViewModelModule::class
    ]
)

interface AppComponent {
    fun inject(appComponent: DaggerAppComponentFactory)
    fun inject(activity: MainActivity)
    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }
}
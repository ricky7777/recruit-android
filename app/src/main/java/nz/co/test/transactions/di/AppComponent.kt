package nz.co.test.transactions.di

import dagger.Component
import nz.co.test.transactions.activities.MainActivity
import nz.co.test.transactions.di.activities.ActivitiesModule
import nz.co.test.transactions.di.network.NetworkModule
import javax.inject.Singleton

/**
 * Use Koin DI to replace
 */
//@Singleton
//@Component(
//    modules = [
//        NetworkModule::class,
//        ActivitiesModule::class
//    ]
//)
//
//interface AppComponent {
//    val ViewModelModule: Any
//
//    fun inject(activity: MainActivity)
//    @Component.Factory
//    interface Factory {
//        fun create(): AppComponent
//    }
//}
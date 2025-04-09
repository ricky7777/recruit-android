package nz.co.test.transactions.db

import android.content.Context
import androidx.room.Room

/**
 * @author Ricky Chen
 * room db provider
 */
object DatabaseProvider {
    fun provideDatabase(context: Context): ASBAppDatabase {
        return Room.databaseBuilder(
            context,
            ASBAppDatabase::class.java,
            "transaction_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
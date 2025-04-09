package nz.co.test.transactions.di.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import nz.co.test.transactions.db.OffsetDateTimeDeserializer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.OffsetDateTime

/**
 * @author Ricky
 * Network provider, provide retrofit
 */
object NetworkProvider {
    private const val BASE_URL =
        "https://gist.githubusercontent.com/Josh-Ng/500f2716604dc1e8e2a3c6d31ad01830/raw/4d73acaa7caa1167676445c922835554c5572e82/"


    private val gson: Gson = GsonBuilder()
        .registerTypeAdapter(OffsetDateTime::class.java, OffsetDateTimeDeserializer())
        .create()

    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}
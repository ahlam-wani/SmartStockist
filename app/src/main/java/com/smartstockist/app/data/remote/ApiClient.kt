package com.smartstockist.app.data.remote
import androidx.databinding.library.baseAdapters.BuildConfig
import com.smartstockist.app.utils.APP_ID
import com.smartstockist.app.utils.APP_KEY
import com.smartstockist.app.utils.BASE_URL
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    companion object {
        val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpBuilder().build())
                .build()
        }

        private fun getOkHttpBuilder(timeout: Int = 120): OkHttpClient.Builder {

            val builder = OkHttpClient.Builder()
            val dispatcher = Dispatcher()
            builder.dispatcher(dispatcher)
            builder.connectTimeout(timeout.toLong(), TimeUnit.SECONDS)
            builder.readTimeout(timeout.toLong(), TimeUnit.SECONDS)
            builder.writeTimeout(timeout.toLong(), TimeUnit.SECONDS)

            builder.addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .addHeader("APPID", APP_ID)
                    .addHeader("APPKEY", APP_KEY)
                    .build()
                chain.proceed(request)
            }

            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                builder.addInterceptor(interceptor)
            }

            return builder
        }
    }
}



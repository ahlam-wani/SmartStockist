package com.smartstockist.app.data.remote

import androidx.databinding.library.baseAdapters.BuildConfig
import com.smartstockist.app.utils.BASE_URL
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class LoginApiClient {
    companion object {
        val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL).client(getOkHttpBuilder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        private fun getOkHttpBuilder(timeout: Int = 600000): OkHttpClient.Builder {

            val builder = OkHttpClient.Builder()
            val dispatcher = Dispatcher()
            builder.dispatcher(dispatcher)
            builder.connectTimeout(timeout.toLong(), TimeUnit.SECONDS)
            builder.readTimeout(timeout.toLong(), TimeUnit.SECONDS)
            builder.writeTimeout(timeout.toLong(), TimeUnit.SECONDS)

            builder.addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .header("Content-Type", "application/json; charset=utf-8")
                    .method(original.method, original.body)
                    .build()
                chain.proceed(request)
            }
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                builder.addInterceptor(interceptor)

            return builder
        }
    }

    }
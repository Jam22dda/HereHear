package com.ssafy.herehear.presentation.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConnection {

    companion object {
//        local URL
//        private const val BASE_URL = "http://10.0.2.2:8080/"

        // server URL
        private const val BASE_URL = "https://k9b202.p.ssafy.io/api/"

        private var INSTANCE: Retrofit? = null

        fun getInstance(): Retrofit {
            if (INSTANCE == null) {
//                val okHttpClient = OkHttpClient.Builder()
//                    .addInterceptor { chain ->
//                        val originalRequest = chain.request()
//                        val newRequest = originalRequest.newBuilder()
//                            .header("personalCode", "1234")
//                            .build()
//                        chain.proceed(newRequest)
//                    }
//                    .build()

                val logging = HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
                val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()

                INSTANCE = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return INSTANCE!!
        }
    }
}
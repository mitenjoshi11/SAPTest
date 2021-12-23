package com.example.testingproject.data.api

import com.example.testingproject.utils.Constants
import com.squareup.okhttp.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {

        private val retrofit by lazy {

            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttp = OkHttpClient.Builder().build()

            Retrofit.Builder().baseUrl(Constants.BASE_URL).client(okHttp)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }

        val api by lazy {
            retrofit.create(APIService::class.java)
        }
    }
}
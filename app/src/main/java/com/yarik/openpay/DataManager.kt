package com.yarik.openpay

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://s3-ap-southeast-2.amazonaws.com/"

class DataManager {

    companion object {
        private var apiInterfaceInstance: ApiInterface? = null

        fun getApiInterface(): ApiInterface {
            if (apiInterfaceInstance == null) {
                initRetrofitClient()
            }
            return apiInterfaceInstance!!
        }

        private fun initRetrofitClient() {
            val rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val builder: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
            val okHttpClient = builder.build()
            val gson = GsonBuilder().create()
            val gsonConverterFactory = GsonConverterFactory.create(gson)
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(rxJava2CallAdapterFactory)
                    .addConverterFactory(gsonConverterFactory)
                    .client(okHttpClient)
                    .build()
            apiInterfaceInstance = retrofit.create(ApiInterface::class.java)
        }
    }
}
package com.yarik.openpay

import android.app.Application
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://s3-ap-southeast-2.amazonaws.com/"

class OpenpayApplication : Application() {

    companion object {

        lateinit var apiInterfaceInstance: ApiInterface
//
//        fun getApiInterface() : ApiInterface {
//            return apiInterfaceInstance
//        }
    }

    init {
        initRetrofitClient()
    }

    override fun onCreate() {
        super.onCreate()
    }

    fun initRetrofitClient() {
        val rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        val level: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY
        httpLoggingInterceptor.level = level
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
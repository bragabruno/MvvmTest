package com.example.mvvmtest.model.common

import com.example.mvvmtest.model.cats.remote.BASE_URL
import com.example.mvvmtest.model.cats.remote.CatsApi
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Network {

    val catsApi: CatsApi by lazy {
        initRetrofit("").create(CatsApi::class.java)
    }
    /**
     * Support the creation of the API from the client
     * CatsApi
     * DogsApi
     * AxolotlApi
     */

    fun initRetrofit(apiClass: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
}
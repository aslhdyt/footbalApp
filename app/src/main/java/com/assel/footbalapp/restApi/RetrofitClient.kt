package com.assel.footbalapp.restApi

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit



object RetrofitClient {


    private val BASE_URL = "https://www.thesportsdb.com/api/v1/json/1"

    fun getInstance(): Retrofit {
        return retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
}
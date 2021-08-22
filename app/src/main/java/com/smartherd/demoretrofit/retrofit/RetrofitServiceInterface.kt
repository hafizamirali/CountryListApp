package com.smartherd.demoretrofit.retrofit

import com.smartherd.demoretrofit.data.CountryModel
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServiceInterface {
    @GET("v2")
    fun getCountryList(): Call<List<CountryModel>>
}
package com.smartherd.demoretrofit.viewModel

import android.telecom.Call
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smartherd.demoretrofit.data.CountryModel
import com.smartherd.demoretrofit.retrofit.RetrofitInstance
import com.smartherd.demoretrofit.retrofit.RetrofitServiceInterface
import retrofit2.Callback
import retrofit2.Response


class MainActivityViewModel: ViewModel() {
    lateinit var liveDataList: MutableLiveData<List<CountryModel>>

    init {
        liveDataList = MutableLiveData()
    }
    fun getLiveDataObserver(): MutableLiveData<List<CountryModel>>{
        return liveDataList
    }
    fun makAPICall() {
        val retroInstance = RetrofitInstance.getRetrofitInstance()
        val retroService = retroInstance.create(RetrofitServiceInterface::class.java)
        val call = retroService.getCountryList()
            call.enqueue(object : Callback<List<CountryModel>>{
                override fun onResponse(
                    call: retrofit2.Call<List<CountryModel>>,
                    response: Response<List<CountryModel>>
                ) {
                    liveDataList.postValue(response.body())
                }

                override fun onFailure(call: retrofit2.Call<List<CountryModel>>, t: Throwable) {
                   liveDataList.postValue(null)
                }

            })
    }
}


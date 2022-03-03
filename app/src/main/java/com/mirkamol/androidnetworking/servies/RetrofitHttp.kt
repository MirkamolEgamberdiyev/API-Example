package com.mirkamol.androidnetworking.servies

import com.mirkamol.androidnetworking.network.VolleyHttp
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHttp {
    private val IS_TESTER = true
    val SERVER_DEVELOPMENT ="http://dummy.restapiexample.com/"
    val SERVER_PRODUCTION = "http://dummy.restapiexample.com/"

    var retrofit = Retrofit.Builder().baseUrl(server()).addConverterFactory(GsonConverterFactory.create()).build()
    private fun server():String{
        return if (IS_TESTER){
            SERVER_DEVELOPMENT
        }else{
            SERVER_PRODUCTION
        }
    }

    val posterService:PosterService = retrofit.create(PosterService::class.java)
}
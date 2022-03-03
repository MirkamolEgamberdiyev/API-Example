package com.mirkamol.androidnetworking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.mirkamol.androidnetworking.model.Poster
import com.mirkamol.androidnetworking.model.PosterResp
import com.mirkamol.androidnetworking.network.VolleyHttp
import com.mirkamol.androidnetworking.network.volley.VolleyHandler
import com.mirkamol.androidnetworking.servies.RetrofitHttp
import com.mirkamol.androidnetworking.utils.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var tvText:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

    }

    private fun initViews() {
        tvText = findViewById(R.id.tv_text)
        workwithVolley()
       // workPut()
       // workPoster()
       // workDelete()
        //workWithRetrofit()
        //createPostRetrofit()
       // updatePostRetrofit()
        //deletePostRetrofit()
    }


    fun workwithVolley(){

        VolleyHttp.get(VolleyHttp.API_LIST_POST, VolleyHttp.paramsEmpty(), object :VolleyHandler{
            override fun onSuccess(response: String?) {
                tvText.text = response
                Logger.d("@@@", response!!)
            }

            override fun onError(error: String?) {
                Logger.d("VolleyHttp", error!!)
            }

        })

    }
    

    fun workPoster(){
        val poster = Poster(1, 1, "PDP", "Online")
        VolleyHttp.post(VolleyHttp.API_CREATE_POST, VolleyHttp.paramsCreate(poster), object :VolleyHandler{
            override fun onSuccess(response: String?) {
                tvText.text = response!!
                Log.d("@@@", response!!)
            }

            override fun onError(error: String?) {
                Log.d("@@@", error!!)
            }

        })
    }


    fun workPut(){
        val poster = Poster(1, 1, "PDP", "Online")
        VolleyHttp.put(VolleyHttp.API_UPDATE_POST + poster.id, VolleyHttp.paramsCreate(poster), object :VolleyHandler{
            override fun onSuccess(response: String?) {
                Log.d("@@@", response!!)
            }

            override fun onError(error: String?) {
               Log.d("@@@", error!!)
            }

        })
    }

    fun workDelete(){
        val poster = Poster(1, 1, "PDP", "Online")
        VolleyHttp.del(VolleyHttp.API_DELETE_POST + poster.id, object :VolleyHandler{
            override fun onSuccess(response: String?) {
                Log.d("@@@", response!!)
            }

            override fun onError(error: String?) {
                Log.d("@@@", error!!)
            }

        })
    }

    private fun workWithRetrofit(){
        RetrofitHttp.posterService.listPost().enqueue(object : Callback<ArrayList<PosterResp>> {
            override fun onResponse(
                call: Call<ArrayList<PosterResp>>,
                response: Response<ArrayList<PosterResp>>
            ) {
                Logger.d("@@@", response.body().toString())
            }

            override fun onFailure(call: Call<ArrayList<PosterResp>>, t: Throwable) {
                Logger.e("@@@", t.message.toString())
            }

        })
    }

    private fun createPostRetrofit(){
        val poster = Poster(1, 1, "PDP", "Onliine")
        RetrofitHttp.posterService.createPost(poster).enqueue(object :Callback<PosterResp>{
            override fun onResponse(call: Call<PosterResp>, response: Response<PosterResp>) {
                Log.d("@@@", response.body().toString())
            }

            override fun onFailure(call: Call<PosterResp>, t: Throwable) {
                Log.d("@@@", t.message.toString())
            }

        })
    }

    fun updatePostRetrofit(){
        val poster = Poster(1, 1, "PDP", "Onliine")
        RetrofitHttp.posterService.updatePost(poster.id, poster).enqueue(object :Callback<PosterResp>{
            override fun onResponse(call: Call<PosterResp>, response: Response<PosterResp>) {
                Log.d("@@@", response.body().toString())
            }

            override fun onFailure(call: Call<PosterResp>, t: Throwable) {
                Log.d("@@@", t.message.toString())
            }

        })
    }

    fun deletePostRetrofit(){
        val poster = Poster(1, 1, "PDP", "Onliine")
        RetrofitHttp.posterService.deletePost(poster.id).enqueue(object :Callback<PosterResp>{
            override fun onResponse(call: Call<PosterResp>, response: Response<PosterResp>) {
                Log.d("@@@", ""+response.body())
            }

            override fun onFailure(call: Call<PosterResp>, t: Throwable) {
                Log.d("@@@", "" + t.message)

            }

        })
    }
}
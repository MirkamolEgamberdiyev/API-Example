package com.mirkamol.androidnetworking

import android.app.Application
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.mirkamol.androidnetworking.network.VolleyHttp.Companion.TAG

class MyApplication:Application() {

        companion object{
            var instance:MyApplication? = null
        }


        override fun onCreate() {
            super.onCreate()
            instance = this
        }

        val requestQueue: RequestQueue? = null
            get() {
                if (field == null) {
                    return Volley.newRequestQueue(applicationContext)
                }
                return field
            }

        fun <T> addToRequestQueue(request: Request<T>) {
            request.tag = TAG
            requestQueue?.add(request)
        }
}
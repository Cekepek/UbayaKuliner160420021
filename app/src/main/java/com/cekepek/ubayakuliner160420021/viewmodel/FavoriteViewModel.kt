package com.cekepek.ubayakuliner160420021.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.cekepek.ubayakuliner160420021.model.Favorite
import com.cekepek.ubayakuliner160420021.model.Restaurant
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FavoriteViewModel (application: Application)
    : AndroidViewModel(application){

    val restaurantLD = MutableLiveData<ArrayList<Restaurant>>()
    val restaurantLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "ckpk"
    private var queue: RequestQueue? = null

    fun refresh() {
        loadingLD.value = true
        restaurantLoadErrorLD.value = false
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://my-json-server.typicode.com/Cekepek/JsonUbayaKuliner/restaurants?favorite=true"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Restaurant>>() { }.type
                val result = Gson().fromJson<ArrayList<Restaurant>>(it, sType)
                restaurantLD.value = result
                loadingLD.value = false

                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                restaurantLoadErrorLD.value = false
                loadingLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}
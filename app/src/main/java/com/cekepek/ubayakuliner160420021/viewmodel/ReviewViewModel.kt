package com.cekepek.ubayakuliner160420021.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.cekepek.ubayakuliner160420021.model.Restaurant
import com.cekepek.ubayakuliner160420021.model.Review
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ReviewViewModel(application: Application):
    AndroidViewModel(application) {
    val reviewLD = MutableLiveData<ArrayList<Review>>()
    val reviewLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "ckpk"
    private var queue: RequestQueue? = null

    fun refresh(id: String) {
        loadingLD.value = true
        reviewLoadErrorLD.value = false
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://my-json-server.typicode.com/Cekepek/JsonUbayaKuliner/reviews?restoId="+id

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Review>>() { }.type
                val result = Gson().fromJson<ArrayList<Review>>(it, sType)
                reviewLD.value = result
                loadingLD.value = false

                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                reviewLoadErrorLD.value = false
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
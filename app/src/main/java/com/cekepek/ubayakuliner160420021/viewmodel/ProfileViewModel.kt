package com.cekepek.ubayakuliner160420021.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.cekepek.ubayakuliner160420021.model.Account
import com.cekepek.ubayakuliner160420021.model.Restaurant
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProfileViewModel(application: Application):
    AndroidViewModel(application) {
    val profileLD = MutableLiveData<Account>()
    val profileLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "ckpk"
    private var queue: RequestQueue? = null

    fun fetch() {
        loadingLD.value = true
        profileLoadErrorLD.value = false
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://my-json-server.typicode.com/Cekepek/JsonUbayaKuliner/profile"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<Account>() { }.type
                val result = Gson().fromJson<Account>(it, sType)
                profileLD.value = result
                loadingLD.value = false

                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
                profileLoadErrorLD.value = false
                loadingLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}
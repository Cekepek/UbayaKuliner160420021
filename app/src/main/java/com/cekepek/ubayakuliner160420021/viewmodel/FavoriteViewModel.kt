package com.cekepek.ubayakuliner160420021.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.RequestQueue
import com.cekepek.ubayakuliner160420021.model.Restaurant

class FavoriteViewModel (application: Application)
    : AndroidViewModel(application){

    val restaurantLD = MutableLiveData<ArrayList<Restaurant>>()
    val restaurantLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "ckpk"
    private var queue: RequestQueue? = null
}
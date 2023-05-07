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
}
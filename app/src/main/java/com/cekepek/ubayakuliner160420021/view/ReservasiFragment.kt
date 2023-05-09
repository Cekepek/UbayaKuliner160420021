package com.cekepek.ubayakuliner160420021.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.cekepek.ubayakuliner160420021.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_reservasi.*
import java.util.concurrent.TimeUnit

class ReservasiFragment : Fragment(), DatePickerDialog.OnDateSetListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reservasi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val restaurantName = ReservasiFragmentArgs.fromBundle(requireArguments()).namaRestaurant
        txtNamaRestoPesan.text = restaurantName
        calendarView.setOnDateChangeListener{
            calendarView, i, i2, i3 ->
            txtWaktu.text = "$i/$i2/$i3"
        }
        btnPesan.setOnClickListener {
            Observable.timer(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d("Messages", "five seconds")
                    MainActivity.showNotification("Reservasi Telah Berhasil!",
                        "Reservasi Anda Telah disampaikan kepada Restoran",
                        R.drawable.ic_baseline_error_24)
                }

            val action = ReservasiFragmentDirections.actionDetailReservasi(restaurantName, txtWaktu.text.toString(), txtPengunjung.text.toString())
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

    }
}
package com.cekepek.ubayakuliner160420021.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.cekepek.ubayakuliner160420021.R
import kotlinx.android.synthetic.main.fragment_detail_reservasi.*
import kotlinx.android.synthetic.main.fragment_reservasi.*

class DetailReservasiFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_reservasi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val restaurantName = DetailReservasiFragmentArgs.fromBundle(requireArguments()).namaResto
        val waktu = DetailReservasiFragmentArgs.fromBundle(requireArguments()).waktuReservasi
        val pengunjung = DetailReservasiFragmentArgs.fromBundle(requireArguments()).totalPengunjung

        txtRestoReservasi.text = restaurantName
        txtWaktuReservasi.text = "Tanggal = "+waktu
        txtTotalPengunjung.text = "Total Pengunjung = "+pengunjung+" orang"

        btnOkDetailReservasi.setOnClickListener {
            val action = DetailReservasiFragmentDirections.actionBack()
            Navigation.findNavController(it).navigate(action)
        }
    }
}
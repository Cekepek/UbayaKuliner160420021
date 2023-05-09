package com.cekepek.ubayakuliner160420021.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.cekepek.ubayakuliner160420021.R
import com.cekepek.ubayakuliner160420021.model.Restaurant
import com.cekepek.ubayakuliner160420021.util.loadImage
import com.cekepek.ubayakuliner160420021.viewmodel.DetailListViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {
    private lateinit var viewModel: DetailListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailListViewModel::class.java)
        if(arguments != null){
            val restaurantId = DetailFragmentArgs.fromBundle(requireArguments()).restaurantId
            viewModel.fetch(restaurantId)
            observeViewModel()
            btnCekReview.setOnClickListener {
                val action = DetailFragmentDirections.actionReviewFragment(restaurantId)
                Navigation.findNavController(it).navigate(action)
            }

        }


    }

    fun observeViewModel(){
        viewModel.restaurantLD.observe(viewLifecycleOwner, Observer {
            val restaurant = ArrayList<Restaurant>()
            restaurant.addAll(it)
            txtDetailNama.setText(restaurant[0].name)
            txtDesc.setText(restaurant[0].description)
            txtAlamat.setText(restaurant[0].alamat)
            txtDetailPhone.setText(restaurant[0].phoneNum)
            ratingBarDetail.rating = restaurant[0].rating
            imgDetailResto.loadImage(restaurant[0].photoUrl,progressBarDetail)
            btnReserve.setOnClickListener {
                val action = DetailFragmentDirections.actionReservasi(restaurant[0].name.toString())
                Navigation.findNavController(it).navigate(action)
            }
        })
    }
}
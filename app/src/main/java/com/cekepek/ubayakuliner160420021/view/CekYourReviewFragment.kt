package com.cekepek.ubayakuliner160420021.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cekepek.ubayakuliner160420021.R
import com.cekepek.ubayakuliner160420021.viewmodel.ReviewViewModel
import com.cekepek.ubayakuliner160420021.viewmodel.ReviewedViewModel
import com.cekepek.ubayakuliner160420021.viewmodel.YourReviewViewModel
import kotlinx.android.synthetic.main.fragment_cek_your_review.*
import kotlinx.android.synthetic.main.fragment_review.*

class CekYourReviewFragment : Fragment() {

    private lateinit var viewModel: YourReviewViewModel
    private val yourReviewListAdapter = ReviewAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cek_your_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(YourReviewViewModel::class.java)
        if(arguments != null){
            val restaurantId = CekYourReviewFragmentArgs.fromBundle(requireArguments()).restaurantId
            val userId = CekYourReviewFragmentArgs.fromBundle(requireArguments()).userId
            viewModel.refresh(restaurantId,userId)
            recViewCekReviewed.layoutManager = LinearLayoutManager(context)
            recViewCekReviewed.adapter = yourReviewListAdapter
            observeViewModel()
        }

    }
    fun observeViewModel() {
        viewModel.reviewLD.observe(viewLifecycleOwner, Observer {
            yourReviewListAdapter.updateReviewList(it)
        })

        viewModel.reviewLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                txtErrorCekReviewed.visibility = View.VISIBLE
            } else {
                txtErrorCekReviewed.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                recViewCekReviewed.visibility = View.GONE
                progressBarCekReviewed.visibility = View.VISIBLE
            } else {
                recViewCekReviewed.visibility = View.VISIBLE
                progressBarCekReviewed.visibility = View.GONE
            }
        })

    }
}
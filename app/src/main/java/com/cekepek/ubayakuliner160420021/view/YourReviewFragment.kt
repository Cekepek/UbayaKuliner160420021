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
import com.cekepek.ubayakuliner160420021.viewmodel.ListViewModel
import com.cekepek.ubayakuliner160420021.viewmodel.ReviewedViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_your_review.*

class YourReviewFragment : Fragment() {

    private lateinit var viewModel: ReviewedViewModel
    private val reviewedRestaurantListAdapter = YourReviewAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_your_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ReviewedViewModel::class.java)
        viewModel.refresh()
        recViewReviewed.layoutManager = LinearLayoutManager(context)
        recViewReviewed.adapter = reviewedRestaurantListAdapter
        var userId = YourReviewFragmentArgs.fromBundle(requireArguments()).userId
        reviewedRestaurantListAdapter.userId = userId
        observeViewModel()

        refreshLayoutReviewed.setOnRefreshListener {
            recViewReviewed.visibility = View.GONE
            txtErrorReviewed.visibility = View.GONE
            progressLoadReviewed.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayoutReviewed.isRefreshing = false
        }
    }

    fun observeViewModel() {
        viewModel.restaurantLD.observe(viewLifecycleOwner, Observer {
            reviewedRestaurantListAdapter.updateRestaurantList(it)
        })

        viewModel.restaurantLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                txtErrorReviewed.visibility = View.VISIBLE
            } else {
                txtErrorReviewed.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                recViewReviewed.visibility = View.GONE
                progressLoadReviewed.visibility = View.VISIBLE
            } else {
                recViewReviewed.visibility = View.VISIBLE
                progressLoadReviewed.visibility = View.GONE
            }
        })

    }
}
package com.cekepek.ubayakuliner160420021.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.cekepek.ubayakuliner160420021.R
import com.cekepek.ubayakuliner160420021.model.Review
import com.cekepek.ubayakuliner160420021.viewmodel.ListViewModel
import com.cekepek.ubayakuliner160420021.viewmodel.ReviewViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_review.*

class ReviewFragment : Fragment() {

    private lateinit var viewModel: ReviewViewModel
    private val reviewAdapter = ReviewAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ReviewViewModel::class.java)
        if(arguments != null){
            val restaurantId = ReviewFragmentArgs.fromBundle(requireArguments()).restaurantId
            viewModel.refresh(restaurantId)
            recViewReview.layoutManager = LinearLayoutManager(context)
            recViewReview.adapter = reviewAdapter
            observeViewModel()
        }
    }

    fun observeViewModel() {
        viewModel.reviewLD.observe(viewLifecycleOwner, Observer {
            reviewAdapter.updateReviewList(it)
        })

        viewModel.reviewLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                txtErrorReview.visibility = View.VISIBLE
            } else {
                txtErrorReview.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                recViewReview.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            } else {
                recViewReview.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
        })

    }
}
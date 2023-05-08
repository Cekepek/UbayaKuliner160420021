package com.cekepek.ubayakuliner160420021.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cekepek.ubayakuliner160420021.R
import com.cekepek.ubayakuliner160420021.model.Restaurant
import com.cekepek.ubayakuliner160420021.model.Review
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.review_fragment_item.view.*

class ReviewAdapter(val reviewList:ArrayList<Review>)
    : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>(){
    class ReviewViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.review_fragment_item, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val txtUsername = holder.view.txtNamaReviewer
        txtUsername.text = reviewList[position].username
        val txtReview = holder.view.txtReview
        txtReview.text = reviewList[position].review
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    fun updateReviewList(newReviewList: ArrayList<Review>) {
        reviewList.clear()
        reviewList.addAll(newReviewList)
        notifyDataSetChanged()
    }
}
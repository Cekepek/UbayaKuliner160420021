package com.cekepek.ubayakuliner160420021.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.cekepek.ubayakuliner160420021.R
import com.cekepek.ubayakuliner160420021.model.Restaurant

class YourReviewAdapter(val reviewedRestaurantList:ArrayList<Restaurant>)
    : RecyclerView.Adapter<YourReviewAdapter.ReviewedRestaurantViewHolder>(){
    class ReviewedRestaurantViewHolder(var view: View) : RecyclerView.ViewHolder(view)
    var userId: String = ""
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ReviewedRestaurantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.yourreview_fragment_item, parent, false)
        return ReviewedRestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewedRestaurantViewHolder, position: Int) {
        val txtNamaResto = holder.view.findViewById<TextView>(R.id.txtReviewedResto)
        txtNamaResto.text = reviewedRestaurantList[position].name
        val btnCekReview = holder.view.findViewById<Button>(R.id.btnCekReviewed)
        btnCekReview.setOnClickListener {
            var id = "0"
            reviewedRestaurantList[position].id?.let{
                id = it
            }
            val action = YourReviewFragmentDirections.actionCekReviewed(userId, id)
            Navigation.findNavController(it).navigate(action)
        }
    }
    override fun getItemCount(): Int {
        return reviewedRestaurantList.size
    }

    fun updateRestaurantList(newRestaurantList: ArrayList<Restaurant>) {
        reviewedRestaurantList.clear()
        reviewedRestaurantList.addAll(newRestaurantList)
        notifyDataSetChanged()
    }

}
package com.cekepek.ubayakuliner160420021.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.cekepek.ubayakuliner160420021.R
import com.cekepek.ubayakuliner160420021.model.Restaurant
import com.cekepek.ubayakuliner160420021.util.loadImage

class RestaurantListAdapter(val restaurantList:ArrayList<Restaurant>)
    : RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder>(){
    class RestaurantViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RestaurantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.main_fragment_item, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int){
        val txtNama = holder.view.findViewById<TextView>(R.id.txtNamaResto) // karena ada 2 id text view yang txtID maka pakai gini dan class nya ga nyambung ke layout apa-apa
        txtNama.text = restaurantList[position].name
        val ratingBar = holder.view.findViewById<RatingBar>(R.id.ratingBarList)
        ratingBar.rating = restaurantList[position].rating
        val txtReview = holder.view.findViewById<TextView>(R.id.txtReviews)
        txtReview.text = restaurantList[position].totalReviews.toString()+" Reviews"
        val image  = holder.view.findViewById<ImageView>(R.id.imgResto)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBar2)
        image.loadImage(restaurantList[position].photoUrl, progressBar)
        val btnDetail = holder.view.findViewById<Button>(R.id.btnDetails)
        btnDetail.setOnClickListener {
            var id = "0"
            restaurantList[position].id?.let{
                id = it
            }
            val action = MainFragmentDirections.actionRestaurantDetail(id)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }

    fun updateRestaurantList(newRestaurantList: ArrayList<Restaurant>) {
        restaurantList.clear()
        restaurantList.addAll(newRestaurantList)
        notifyDataSetChanged()
    }
}
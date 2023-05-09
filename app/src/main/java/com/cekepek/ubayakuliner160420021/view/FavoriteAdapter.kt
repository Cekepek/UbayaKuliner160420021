package com.cekepek.ubayakuliner160420021.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.cekepek.ubayakuliner160420021.R
import com.cekepek.ubayakuliner160420021.model.Restaurant
import com.cekepek.ubayakuliner160420021.util.loadImage

class FavoriteAdapter(val favoriteList:ArrayList<Restaurant>)
    : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    class FavoriteViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.main_fragment_item, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val txtNama = holder.view.findViewById<TextView>(R.id.txtNamaResto) // karena ada 2 id text view yang txtID maka pakai gini dan class nya ga nyambung ke layout apa-apa
        txtNama.text = favoriteList[position].name
        val ratingBar = holder.view.findViewById<RatingBar>(R.id.ratingBarList)
        ratingBar.rating = favoriteList[position].rating
        val txtReview = holder.view.findViewById<TextView>(R.id.txtReviews)
        txtReview.text = favoriteList[position].totalReviews.toString()+" Reviews"
        val image  = holder.view.findViewById<ImageView>(R.id.imgResto)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBar2)
        image.loadImage(favoriteList[position].photoUrl, progressBar)
        val btnDetail = holder.view.findViewById<Button>(R.id.btnDetails)
        btnDetail.setOnClickListener {
            var id = "0"
            favoriteList[position].id?.let{
                id = it
            }
            val action = FavoriteFragmentDirections.actionFavorite(id)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    fun updateRestaurantList(newStudentList: ArrayList<Restaurant>) {
        favoriteList.clear()
        favoriteList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}
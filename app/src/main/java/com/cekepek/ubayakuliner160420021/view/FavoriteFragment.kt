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
import com.cekepek.ubayakuliner160420021.model.Favorite
import com.cekepek.ubayakuliner160420021.model.Restaurant
import com.cekepek.ubayakuliner160420021.viewmodel.FavoriteViewModel
import com.cekepek.ubayakuliner160420021.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_main.*

class FavoriteFragment : Fragment() {

    private lateinit var viewModel: FavoriteViewModel
    private val favoriteAdapter = FavoriteAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        viewModel.refresh()
        recViewFavorite.layoutManager = LinearLayoutManager(context)
        recViewFavorite.adapter = favoriteAdapter
        observeViewModel()

        //untuk menampilkan icon loading
        refreshLayoutFavorite.setOnRefreshListener {
            recViewFavorite.visibility = View.GONE
            txtErrorFavorite.visibility = View.GONE
            progressLoadFavorite.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayoutFavorite.isRefreshing = false
        }
    }

    fun observeViewModel() {
        viewModel.restaurantLD.observe(viewLifecycleOwner, Observer {
            favoriteAdapter.updateRestaurantList(it)
        })

        viewModel.restaurantLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                txtErrorFavorite.visibility = View.VISIBLE
            } else {
                txtErrorFavorite.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                recViewFavorite.visibility = View.GONE
                progressLoadFavorite.visibility = View.VISIBLE
            } else {
                recViewFavorite.visibility = View.VISIBLE
                progressLoadFavorite.visibility = View.GONE
            }
        })

    }
}
package com.cekepek.ubayakuliner160420021.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cekepek.ubayakuliner160420021.R
import com.cekepek.ubayakuliner160420021.util.loadImage
import com.cekepek.ubayakuliner160420021.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import java.util.concurrent.TimeUnit

class ProfileFragment : Fragment() {
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        viewModel.fetch()
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.profileLD.observe(viewLifecycleOwner, Observer {
            txtNama.setText(it.name)
            txtStudentId.setText(it.studentId)
            txtPhone.setText(it.phone)
            imgProfile.loadImage(it.profilePic,progressBarProfile)
        })
    }
}
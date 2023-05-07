package com.cekepek.ubayakuliner160420021.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.cekepek.ubayakuliner160420021.R
import com.cekepek.ubayakuliner160420021.model.Account
import com.cekepek.ubayakuliner160420021.model.Restaurant
import com.cekepek.ubayakuliner160420021.viewmodel.DetailListViewModel
import com.cekepek.ubayakuliner160420021.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        btnLogin.setOnClickListener {
            viewModel.fetch(txtId.text.toString(), txtPassword.text.toString())
            viewModel.accountLD.observe(viewLifecycleOwner, Observer{
                if(it.isNotEmpty()){
                    val action = LoginFragmentDirections.actionMainFragment()
                    Navigation.findNavController(view).navigate(action)
                }
                else{
                    txtErrorMessage.text = "Username/Password Salah"
                }
            })
        }
    }
}
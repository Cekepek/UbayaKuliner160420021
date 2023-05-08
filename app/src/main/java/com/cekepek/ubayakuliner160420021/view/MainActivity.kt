package com.cekepek.ubayakuliner160420021.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.cekepek.ubayakuliner160420021.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawerLayout)

        navController =
            (supportFragmentManager.findFragmentById(R.id.fragmentHost) as
                    NavHostFragment).navController
        NavigationUI.setupActionBarWithNavController(this, navController,drawerLayout)
        bottomNav.setupWithNavController(navController)
        NavigationUI.setupWithNavController(navView, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout)
                || super.onSupportNavigateUp()
    }
}
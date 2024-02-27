package com.pixel.newsapp.ui.home.host

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.pixel.newsapp.R
import com.pixel.newsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        val drawerLayout = binding.drawerLayout
        val navView = binding.sideNavMenu
        val navController = findNavController(R.id.fragment_container_view)
        
        setSupportActionBar(binding.contentMain.homeToolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.categoryFragment,
                R.id.settingsFragment,
            ),
            drawerLayout,
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment_container_view)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}

package com.pixel.newsapp.ui.home.host

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.pixel.newsapp.R
import com.pixel.newsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    private lateinit var listener: NavController.OnDestinationChangedListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()

        listener =
            NavController.OnDestinationChangedListener { controller, destination, arguments ->
                when (destination.id) {
                    R.id.settingsFragment -> {
                        binding.contentMain.homeToolBar.isTitleCentered = false
                    }

                    else -> {
                        binding.contentMain.homeToolBar.isTitleCentered = true
                    }
                }
            }
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(listener)
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)
    }

    private fun initView() {
        val drawerLayout = binding.drawerLayout
        val navView = binding.sideNavMenu
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController

        setSupportActionBar(binding.contentMain.homeToolBar)

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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

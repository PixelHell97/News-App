package com.pixel.newsapp.ui.home.host

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.pixel.newsapp.R
import com.pixel.newsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.homeToolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toggle = ActionBarDrawerToggle(this, binding.root, R.string.open, R.string.close)
        binding.root.addDrawerListener(toggle)
        toggle.syncState()
        binding.btnNavMenu.setOnClickListener {
            binding.sideNavMenu.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.nav_category -> {
                    }
                    R.id.nav_settings -> {
                    }
                    else -> {
                    }
                }
                true
            }
        }
    }
}

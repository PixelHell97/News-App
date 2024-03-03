package com.pixel.newsapp.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.pixel.newsapp.LocaleHelper
import com.pixel.newsapp.R
import com.pixel.newsapp.ui.home.host.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Handler.createAsync(mainLooper).postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                LocaleHelper.setLocale(this)
                startActivity(intent)
                finish()
            }, 2000)
        }
    }
}

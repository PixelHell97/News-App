package com.pixel.newsapp.ui.home.setting

import android.content.Context
import androidx.lifecycle.ViewModel
import com.pixel.newsapp.Constants

class SettingsViewModel : ViewModel() {
    fun isArabicLang(context: Context?): Boolean {
        return when (context?.resources?.configuration?.locales?.get(0)?.language) {
            Constants.ARABIC_KEY -> true
            else -> false
        }
    }
}

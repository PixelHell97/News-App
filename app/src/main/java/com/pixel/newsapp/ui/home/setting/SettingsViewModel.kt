package com.pixel.newsapp.ui.home.setting

import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.ViewModel
import com.pixel.newsapp.Constants
import com.pixel.newsapp.R

class SettingsViewModel : ViewModel() {
    fun updateSettingPreference(
        context: Context?,
        text: CharSequence?,
    ) {
        val settingsPref =
            context?.getSharedPreferences(Constants.SETTINGS, Context.MODE_PRIVATE)
        val editor = settingsPref?.edit()
        when (text) {
            Resources.getSystem().getString(R.string.ar) -> {
                editor?.putString(Constants.LANGUAGE, Constants.ARABIC_KEY)
                    ?.apply()
            }

            else -> {
                editor?.putString(Constants.LANGUAGE, Constants.ENGLISH_KEY)
                    ?.apply()
            }
        }
    }

    fun isArabicLang(context: Context?): Boolean {
        return when (context?.resources?.configuration?.locales?.get(0)?.language) {
            Constants.ARABIC_KEY -> true
            else -> false
        }
    }
}

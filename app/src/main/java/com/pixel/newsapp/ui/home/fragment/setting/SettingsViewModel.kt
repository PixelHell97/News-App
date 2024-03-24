package com.pixel.newsapp.ui.home.fragment.setting

import android.content.Context
import com.pixel.newsapp.Constants
import com.pixel.newsapp.ui.base.BaseViewModel

class SettingsViewModel : BaseViewModel() {
    fun isArabicLang(context: Context?): Boolean {
        return when (context?.resources?.configuration?.locales?.get(0)?.language) {
            Constants.ARABIC_KEY -> true
            else -> false
        }
    }
}

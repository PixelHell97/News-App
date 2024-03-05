package com.pixel.newsapp

import android.content.Context
import java.util.Locale

object LocaleHelper {
    fun setLocale(context: Context) {
        val language: String = getPref(context)
        updateResources(context, language)
    }

    private fun getPref(context: Context): String {
        val sharedPref =
            context.getSharedPreferences(Constants.SETTINGS, Context.MODE_PRIVATE)
        return sharedPref.getString(Constants.LANGUAGE, "")!!
    }

    private fun updateResources(
        context: Context,
        language: String,
    ) {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val config = context.resources?.configuration
        config?.setLocale(locale)
        config?.setLayoutDirection(locale)
        @Suppress("DEPRECATION")
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }
}

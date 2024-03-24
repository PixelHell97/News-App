package com.pixel.newsapp.ui.home.fragment.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.pixel.newsapp.Constants
import com.pixel.newsapp.LocaleHelper
import com.pixel.newsapp.R
import com.pixel.newsapp.databinding.FragmentSettingsBinding
import com.pixel.newsapp.ui.base.BaseFragment
import com.pixel.newsapp.ui.home.MainActivity

class SettingsFragment : BaseFragment<FragmentSettingsBinding, SettingsViewModel>() {
    override fun initViewModel(): SettingsViewModel =
        ViewModelProvider(this)[SettingsViewModel::class.java]

    override fun getLayoutId(): Int = R.layout.fragment_settings

    private lateinit var languageAdapter: ArrayAdapter<String>

    override fun onResume() {
        super.onResume()
        setDropDownMenu()
    }

    private fun setDropDownMenu() {
        val languages = resources.getStringArray(R.array.language)
        languageAdapter =
            ArrayAdapter(requireContext(), R.layout.drop_down_text, languages)
        binding.languageDropDown.setAdapter(languageAdapter)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        val settingsPref =
            context?.getSharedPreferences(Constants.SETTINGS, Context.MODE_PRIVATE)
        val editor = settingsPref?.edit()
        binding.languageDropDown.doOnTextChanged { text, _, _, _ ->
            when (text?.toString()) {
                resources.getString(R.string.ar) -> {
                    editor?.putString(Constants.LANGUAGE, Constants.ARABIC_KEY)
                        ?.apply()
                }

                else -> {
                    editor?.putString(Constants.LANGUAGE, Constants.ENGLISH_KEY)
                        ?.apply()
                }
            }
            LocaleHelper.setLocale(requireContext())
            restartApplication()
        }
    }

    private fun initViews() {
        binding.languageDropDown.setText(
            when (viewModel.isArabicLang(context)) {
                true -> resources.getString(R.string.ar)
                false -> resources.getString(R.string.en)
            },
        )
    }

    private fun restartApplication() {
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }
}

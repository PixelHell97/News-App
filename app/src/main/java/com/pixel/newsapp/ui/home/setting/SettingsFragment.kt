package com.pixel.newsapp.ui.home.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pixel.newsapp.Constants
import com.pixel.newsapp.LocaleHelper
import com.pixel.newsapp.R
import com.pixel.newsapp.databinding.FragmentSettingsBinding
import com.pixel.newsapp.ui.home.host.MainActivity

class SettingsFragment : Fragment() {
    @Suppress("ktlint:standard:property-naming")
    private var _binding: FragmentSettingsBinding? = null
    private lateinit var settingsViewModel: SettingsViewModel

    private val binding get() = _binding!!
    private lateinit var languageAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingsViewModel = ViewModelProvider(this)[SettingsViewModel::class.java]
    }

    override fun onResume() {
        super.onResume()
        setDropDownMenu()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
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
            settingsViewModel.updateSettingPreference(context, text)
            LocaleHelper.setLocale(requireContext())
            restartApplication()
        }
    }

    private fun initViews() {
        binding.languageDropDown.setText(
            when (settingsViewModel.isArabicLang(context)) {
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

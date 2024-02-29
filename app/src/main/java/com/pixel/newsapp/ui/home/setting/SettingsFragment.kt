package com.pixel.newsapp.ui.home.setting

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.pixel.newsapp.Constants
import com.pixel.newsapp.R
import com.pixel.newsapp.databinding.FragmentSettingsBinding
import java.util.Locale

class SettingsFragment : Fragment() {
    private var _viewBinding: FragmentSettingsBinding? = null
    private val binding get() = _viewBinding!!
    private lateinit var languageAdapter: ArrayAdapter<String>

    override fun onResume() {
        super.onResume()
        setDropDownMenu()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _viewBinding = FragmentSettingsBinding.inflate(inflater, container, false)
        
        return binding.root
    }

    private fun setDropDownMenu() {
        val languages = resources.getStringArray(R.array.language)
        languageAdapter =
            ArrayAdapter(requireContext(), R.layout.drop_down_text, languages)
        binding.languageDropDown.setAdapter(languageAdapter)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        binding.languageDropDown.doOnTextChanged { text, _, _, _ ->
            changeLanguage(
                when (text?.toString()) {
                    resources.getString(R.string.ar) -> {
                        true
                    }
                    else -> {
                        false
                    }
                },
            )
        }
    }

    private fun isArabicLang(): Boolean {
        return when (context?.resources?.configuration?.locales?.get(0)?.language) {
            Constants.ARABIC_KEY -> true
            else -> false
        }
    }

    private fun initViews() {
        binding.languageDropDown.setText(
            when (isArabicLang()) {
                true -> resources.getString(R.string.ar)
                false -> resources.getString(R.string.en)
            },
        )
    }

    private fun changeLanguage(boolean: Boolean) {
        setLocal(if (boolean) Constants.ARABIC_KEY else Constants.ENGLISH_KEY)
    }

    private fun setLocal(s: String) {
        val local = Locale(s)
        Locale.setDefault(local)
        val configuration = Configuration()
        configuration.setLocale(local)
        activity?.let {
            @Suppress("DEPRECATION")
            requireActivity().baseContext.resources.updateConfiguration(
                configuration,
                requireActivity().baseContext.resources.displayMetrics,
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }
}

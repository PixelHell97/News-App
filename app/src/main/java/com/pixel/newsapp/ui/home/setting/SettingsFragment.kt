package com.pixel.newsapp.ui.home.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pixel.newsapp.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private var _viewBinding: FragmentSettingsBinding? = null
    private val binding get() = _viewBinding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _viewBinding = FragmentSettingsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }
}

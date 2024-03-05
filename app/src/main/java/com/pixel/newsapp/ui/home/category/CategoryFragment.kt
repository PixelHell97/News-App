package com.pixel.newsapp.ui.home.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.pixel.newsapp.R
import com.pixel.newsapp.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {
    @Suppress("ktlint:standard:backing-property-naming")
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.categorySports.setOnClickListener {
            val categoryType = resources.getString(R.string.sportsCategory)
            val title = resources.getString(R.string.sports)
            navigateNews(it, categoryType, title)
        }
        binding.categoryPolitics.setOnClickListener {
            val categoryType = resources.getString(R.string.politicsCategory)
            val title = resources.getString(R.string.politics)
            navigateNews(it, categoryType, title)
        }
        binding.categoryHealth.setOnClickListener {
            val categoryType = resources.getString(R.string.healthCategory)
            val title = resources.getString(R.string.health)
            navigateNews(it, categoryType, title)
        }
        binding.categoryBusiness.setOnClickListener {
            val categoryType = resources.getString(R.string.businessCategory)
            val title = resources.getString(R.string.business)
            navigateNews(it, categoryType, title)
        }
        binding.categoryEnvironment.setOnClickListener {
            val categoryType = resources.getString(R.string.environmentCategory)
            val title = resources.getString(R.string.environment)
            navigateNews(it, categoryType, title)
        }
        binding.categoryScience.setOnClickListener {
            val categoryType = resources.getString(R.string.scienceCategory)
            val title = resources.getString(R.string.science)
            navigateNews(it, categoryType, title)
        }
    }

    private fun navigateNews(
        view: View,
        categoryType: String,
        toolbarTitle: String,
    ) {
        val action =
            CategoryFragmentDirections.actionCategoryFragmentToNewsFragment(
                categoryType,
                toolbarTitle,
            )
        Navigation.findNavController(view).navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

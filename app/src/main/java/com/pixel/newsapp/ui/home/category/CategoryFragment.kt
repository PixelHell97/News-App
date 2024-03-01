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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.categorySports.setOnClickListener {
            val categoryType = resources.getString(R.string.sportsCategory)
            navigateNews(it, categoryType)
        }
        binding.categoryPolitics.setOnClickListener {
            val categoryType = resources.getString(R.string.politicsCategory)
            navigateNews(it, categoryType)
        }
        binding.categoryHealth.setOnClickListener {
            val categoryType = resources.getString(R.string.healthCategory)
            navigateNews(it, categoryType)
        }
        binding.categoryBusiness.setOnClickListener {
            val categoryType = resources.getString(R.string.businessCategory)
            navigateNews(it, categoryType)
        }
        binding.categoryEnvironment.setOnClickListener {
            val categoryType = resources.getString(R.string.environmentCategory)
            navigateNews(it, categoryType)
        }
        binding.categoryScience.setOnClickListener {
            val categoryType = resources.getString(R.string.scienceCategory)
            navigateNews(it, categoryType)
        }
    }

    private fun navigateNews(view: View, categoryType: String) {
        val action = CategoryFragmentDirections.actionCategoryFragmentToNewsFragment(categoryType)
        Navigation.findNavController(view).navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

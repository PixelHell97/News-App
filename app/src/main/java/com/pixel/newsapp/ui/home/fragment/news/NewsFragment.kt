package com.pixel.newsapp.ui.home.fragment.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayout
import com.pixel.domain.model.Article
import com.pixel.domain.model.Source
import com.pixel.newsapp.databinding.FragmentNewsBinding
import com.pixel.newsapp.ui.home.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null

    private val binding get() = _binding!!
    private val viewModel: NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val args: NewsFragmentArgs by navArgs()
    private var articleAdapter = ArticleAdapter()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = args.toolbarTitle
        observeData()
        initViews()
    }

    private fun observeData() {
        viewModel.newsList.observe(viewLifecycleOwner) { sourcesList ->
            showNewsSources(sourcesList)
        }
        viewModel.articleList.observe(viewLifecycleOwner) { articleList ->
            showArticles(articleList)
        }
        viewModel.showProgressBar.observe(viewLifecycleOwner) { isLoading ->
            showProgressBar(isLoading)
        }
        viewModel.errorDialog.observe(viewLifecycleOwner) {
            if (it != null) {
                showError(it)
            }
        }
    }

    private fun initViews() {
        args.categoryType.let { viewModel.getNewsResponse(it) }
        articleAdapter.onItemClickListener =
            ArticleAdapter.OnItemClickListener { article, _ ->
                beginNextDirection(article)
            }
    }

    private fun beginNextDirection(article: Article) {
        val action = NewsFragmentDirections.actionNewsFragmentToArticleDisplayFragment(article)
        Navigation.findNavController(requireView()).navigate(action)
    }

    private fun showNewsSources(sources: List<Source?>?) {
        showProgressBar(false)
        if (sources?.size == 0) {
            binding.emptyListTv.visibility = View.VISIBLE
            return
        }
        sources?.forEach { source ->
            val tab = binding.sourceTabLayout.newTab()
            tab.setText(source?.name)
            tab.tag = source
            binding.sourceTabLayout.addTab(tab)
        }
        binding.sourceTabLayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as Source
                    viewModel.loadNewResource(source)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as Source
                    viewModel.loadNewResource(source)
                }
            },
        )
        binding.sourceTabLayout.getTabAt(0)?.select()
    }

    private fun showArticles(articles: List<Article?>?) {
        showProgressBar(false)
        articleAdapter.changeData(articles)
        binding.recyclerArticles.adapter = articleAdapter
    }

    private fun showProgressBar(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible
    }

    private fun showError(error: DialogMessage) {
        showProgressBar(false)
        val errorDialog = AlertDialog.Builder(requireContext())
        errorDialog
            .setTitle(error.title)
            .setMessage(error.message)
            .setCancelable(true)
            .setPositiveButton("Try Again") { dialog, _ ->
                args.categoryType.let { viewModel.getNewsResponse(it) }
                dialog.dismiss()
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

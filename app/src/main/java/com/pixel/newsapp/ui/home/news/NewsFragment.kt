package com.pixel.newsapp.ui.home.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayout
import com.pixel.newsapp.api.model.Source
import com.pixel.newsapp.api.model.articleResponse.Article
import com.pixel.newsapp.databinding.FragmentNewsBinding
import com.pixel.newsapp.ui.home.host.MainActivity

class NewsFragment : Fragment() {
    @Suppress("ktlint:standard:property-naming")
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private lateinit var newsViewModel: NewsViewModel
    private val args: NewsFragmentArgs by navArgs()
    private var catType: String? = null
    private var title: String? = null
    private var articleAdapter = ArticleAdapter(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        title = args.toolbarTitle
        (activity as MainActivity).supportActionBar?.title = title.toString()
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        catType = args.categoryType
        observeData()
        initViews()
    }

    private fun observeData() {
        newsViewModel.newsList.observe(viewLifecycleOwner) { sourcesList ->
            showNewsSources(sourcesList)
        }
        newsViewModel.articleList.observe(viewLifecycleOwner) { articleList ->
            showArticles(articleList)
        }
        newsViewModel.showProgressBar.observe(viewLifecycleOwner) { isLoading ->
            showProgressBar(isLoading)
        }
        newsViewModel.errorDialog.observe(viewLifecycleOwner) {
            if (it != null) {
                showError(it)
            }
        }
    }

    private fun initViews() {
        catType?.let { newsViewModel.getNewsResponse(it) }
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
                    newsViewModel.loadNewResource(source)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as Source
                    newsViewModel.loadNewResource(source)
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
                catType?.let { newsViewModel.getNewsResponse(it) }
                dialog.dismiss()
            }
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

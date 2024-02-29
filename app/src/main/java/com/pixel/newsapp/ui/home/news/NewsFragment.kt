package com.pixel.newsapp.ui.home.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.pixel.newsapp.api.ApiManager
import com.pixel.newsapp.api.model.Source
import com.pixel.newsapp.api.model.articleResponse.Article
import com.pixel.newsapp.api.model.articleResponse.ArticlesResponse
import com.pixel.newsapp.api.model.sourcesResponse.SourcesResponse
import com.pixel.newsapp.databinding.FragmentNewsBinding
import com.pixel.newsapp.ui.home.host.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {
    private var _viewBinding: FragmentNewsBinding? = null
    private val binding get() = _viewBinding!!

    // private lateinit var newsViewModel: NewsViewModel
    private val args: NewsFragmentArgs by navArgs()
    private var catType: String? = null
    private var articleAdapter = ArticleAdapter(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // newsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _viewBinding = FragmentNewsBinding.inflate(inflater, container, false)
        catType = args.categoryType
        val catTypeCap = catType?.elementAt(0)?.uppercaseChar()
            ?.plus(catType?.removePrefix(catType?.elementAt(0).toString()).toString())
        (activity as MainActivity).supportActionBar?.title = catTypeCap
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // observeData()
        initViews()
    }

    /*private fun observeData() {
        newsViewModel.newsList.observe(viewLifecycleOwner) { sourcesList ->
            showProgressBar(true)
            showNewsSources(sourcesList)
        }
        newsViewModel.articleList.observe(viewLifecycleOwner) { articleList ->
            showProgressBar(true)
            setUpArticle(articleList)
        }
        newsViewModel.errorDialog.observe(viewLifecycleOwner) {
            if (it != null) {
                showError(it)
            }
        }
    }*/
    private fun getNewsResponse(category: String?) {
        showProgressBar(true)
        ApiManager
            .getServices()
            .getNewsSources(
                category = category,
            ).enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>,
                ) {
                    if (response.isSuccessful) {
                        showNewsSources(response.body()?.sources)
                        Log.e("source->", "GotSources")
                        return
                    } else {
                        val responseJson = response.errorBody()?.string()
                        val errorResponse =
                            Gson().fromJson(responseJson, SourcesResponse::class.java)
                        showError(
                            DialogMessage(
                                "Access Error",
                                errorResponse.message,
                            ),
                        )
                    }
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    t.message?.let {
                        showError(
                            DialogMessage(
                                "Network Error",
                                it,
                            ),
                        )
                    }
                }
            })
    }

    fun loadNewResource(source: Source?) {
        showProgressBar(true)
        source?.id?.let { sourceId ->
            ApiManager
                .getServices()
                .getNewsArticle(sources = sourceId)
                .enqueue(object : Callback<ArticlesResponse> {
                    override fun onResponse(
                        call: Call<ArticlesResponse>,
                        response: Response<ArticlesResponse>,
                    ) {
                        if (response.isSuccessful) {
                            showArticles(response.body()?.articles)
                            return
                        } else {
                            val responseJson = response.errorBody()?.string()
                            val errorResponse =
                                Gson().fromJson(responseJson, ArticlesResponse::class.java)
                            showError(
                                DialogMessage(
                                    "Access Error",
                                    errorResponse.message,
                                ),
                            )
                        }
                    }

                    override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                        t.message?.let {
                            showError(
                                DialogMessage(
                                    "Network Error",
                                    it,
                                ),
                            )
                        }
                    }
                })
        }
    }

    private fun initViews() {
        getNewsResponse(catType)
        articleAdapter.onItemClickListener = ArticleAdapter.OnItemClickListener { article, _ ->
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
        binding.sourceTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val source = tab?.tag as Source
                loadNewResource(source)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val source = tab?.tag as Source
                loadNewResource(source)
            }
        })
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

    private fun showError(
        error: DialogMessage,
    ) {
        showProgressBar(false)
        val errorDialog = AlertDialog.Builder(requireContext())
        errorDialog
            .setTitle(error.title)
            .setMessage(error.message)
            .setCancelable(true)
            .setPositiveButton("Try Again") { dialog, _ ->
                getNewsResponse(catType)
                dialog.dismiss()
            }
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }
}

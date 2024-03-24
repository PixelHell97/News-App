package com.pixel.newsapp.ui.home.fragment.article

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.pixel.newsapp.R
import com.pixel.newsapp.databinding.FragmentArticleDisplayBinding
import com.pixel.newsapp.ui.base.BaseFragment
import com.pixel.newsapp.ui.home.MainActivity

class ArticleDisplayFragment :
    BaseFragment<FragmentArticleDisplayBinding, ArticleDisplayViewModel>() {
    override fun initViewModel(): ArticleDisplayViewModel =
        ViewModelProvider(this)[ArticleDisplayViewModel::class.java]

    override fun getLayoutId(): Int = R.layout.fragment_article_display

    private val args: ArticleDisplayFragmentArgs by navArgs()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = args.ArticleSource?.source?.name
        initView()
    }

    private fun initView() {
        binding.article = args.ArticleSource
        binding.openArticle.setOnClickListener {
            openArticleSource()
        }
    }

    private fun openArticleSource() {
        val url = args.ArticleSource?.url
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}

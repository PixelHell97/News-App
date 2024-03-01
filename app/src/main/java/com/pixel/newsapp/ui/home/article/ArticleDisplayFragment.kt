package com.pixel.newsapp.ui.home.article

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.pixel.newsapp.FragmentExtensions
import com.pixel.newsapp.R
import com.pixel.newsapp.api.model.articleResponse.Article
import com.pixel.newsapp.databinding.FragmentArticleDisplayBinding
import com.pixel.newsapp.ui.home.host.MainActivity

class ArticleDisplayFragment : Fragment() {
    private var _viewBinding: FragmentArticleDisplayBinding? = null
    private val binding get() = _viewBinding!!
    private val args: ArticleDisplayFragmentArgs by navArgs()
    private var article: Article? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _viewBinding = FragmentArticleDisplayBinding.inflate(inflater, container, false)
        article = args.ArticleSource
        (activity as MainActivity).supportActionBar?.title = article?.source?.name
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        setArticleView()
        binding.openArticle.setOnClickListener {
            openArticleSource()
        }
    }

    private fun openArticleSource() {
        val url = article?.url
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun setArticleView() {
        article?.let { article ->
            binding.articleTitle.text = article.title
            binding.articlePublishAt.text = article.publishedAt?.let {
                FragmentExtensions.formatDurationFromNow(it)
            }
            binding.articleBody.text = article.description
            binding.articleSource.text = article.source?.name
            Glide
                .with(this)
                .load(article.urlToImage)
                .placeholder(R.drawable.ic_logo)
                .into(binding.articleImage)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }
}

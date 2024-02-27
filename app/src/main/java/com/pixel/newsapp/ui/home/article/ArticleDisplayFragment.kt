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
import com.pixel.newsapp.R
import com.pixel.newsapp.api.model.articleResponse.Article
import com.pixel.newsapp.databinding.FragmentArticleDisplayBinding

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        setArticleView()
        binding.openArticle.setOnClickListener { 
            val url = article?.url
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    private fun setArticleView() {
        article?.let { 
            binding.articleTitle.text = it.title
            binding.articlePublishAt.text = it.publishedAt
            binding.articleBody.text = it.description
            binding.articleSource.text = it.source?.name
            Glide.with(this)
                .load(it.urlToImage)
                .placeholder(R.drawable.ic_logo)
                .into(binding.articleImage)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }
}

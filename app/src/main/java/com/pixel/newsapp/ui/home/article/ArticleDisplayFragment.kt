package com.pixel.newsapp.ui.home.article

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.pixel.newsapp.api.model.articleResponse.Article
import com.pixel.newsapp.databinding.FragmentArticleDisplayBinding
import com.pixel.newsapp.ui.home.host.MainActivity

class ArticleDisplayFragment : Fragment() {
    private var _binding: FragmentArticleDisplayBinding? = null
    private val binding get() = _binding!!
    private val args: ArticleDisplayFragmentArgs by navArgs()
    private var article: Article? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentArticleDisplayBinding.inflate(inflater, container, false)
        article = args.ArticleSource
        (activity as MainActivity).supportActionBar?.title = article?.source?.name
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.article = article
        binding.openArticle.setOnClickListener {
            openArticleSource()
        }
    }

    private fun openArticleSource() {
        val url = article?.url
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

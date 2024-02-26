package com.pixel.newsapp.ui.home.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pixel.newsapp.R
import com.pixel.newsapp.api.model.articleResponse.Article
import com.pixel.newsapp.databinding.ItemArticleBinding

class ArticleAdapter(private var articleList: List<Article?>?) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    class ViewHolder(private val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article?) {
            binding.articleSource.text = article?.source?.name
            binding.articleTitle.text = article?.title
            binding.articlePublishAt.text = article?.publishedAt
            Glide
                .with(itemView)
                .load(article?.urlToImage)
                .placeholder(R.drawable.ic_logo)
                .into(binding.articleImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemArticleBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articleList?.get(position)
        holder.bind(article)
    }
    override fun getItemCount(): Int = articleList?.size ?: 0
    fun changeData(articles: List<Article?>?) {
        this.articleList = articles
        notifyDataSetChanged()
    }
}

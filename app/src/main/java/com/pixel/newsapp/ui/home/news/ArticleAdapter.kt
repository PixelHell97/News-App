package com.pixel.newsapp.ui.home.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pixel.newsapp.api.model.articleResponse.Article
import com.pixel.newsapp.databinding.ItemArticleBinding

class ArticleAdapter(private var articleList: List<Article?>?) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    class ViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article?) {
            binding.article = article
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding =
            ItemArticleBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        val article = articleList?.get(position)
        holder.bind(article)
        onItemClickListener?.let { listener ->
            holder.itemView.setOnClickListener { _ ->
                article?.let { listener.onItemClick(it, position) }
            }
        }
    }

    override fun getItemCount(): Int = articleList?.size ?: 0

    fun changeData(articles: List<Article?>?) {
        this.articleList = articles
        notifyDataSetChanged()
    }

    var onItemClickListener: OnItemClickListener? = null

    fun interface OnItemClickListener {
        fun onItemClick(
            article: Article,
            position: Int,
        )
    }
}

package com.pixel.newsapp.ui.home.fragment.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pixel.domain.model.Article
import com.pixel.newsapp.databinding.ItemArticleBinding

class ArticleAdapter(private var articleList: MutableList<Article?>? = null) :
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
        if (articleList == null) {
            articleList = mutableListOf()
        }
        articleList?.apply {
            this.clear()
            if (articles != null) {
                this.addAll(articles)
            } else {
                articleList = emptyList<Article>().toMutableList()
            }
        }
        notifyItemRangeInserted(0, articles?.size ?: return)
    }

    var onItemClickListener: OnItemClickListener? = null

    fun interface OnItemClickListener {
        fun onItemClick(
            article: Article,
            position: Int,
        )
    }
}

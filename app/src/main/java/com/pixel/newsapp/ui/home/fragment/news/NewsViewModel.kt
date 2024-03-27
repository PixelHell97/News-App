package com.pixel.newsapp.ui.home.fragment.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pixel.domain.contract.repository.articles.ArticleRepository
import com.pixel.domain.contract.repository.sources.SourcesRepository
import com.pixel.domain.model.Article
import com.pixel.domain.model.Source
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: SourcesRepository,
    private val articleRepository: ArticleRepository,
) : ViewModel() {
    var newsList: MutableLiveData<List<Source?>?> = MutableLiveData()
    var articleList: MutableLiveData<List<Article?>?> = MutableLiveData()
    var errorDialog: MutableLiveData<DialogMessage?> = MutableLiveData()
    var showProgressBar: MutableLiveData<Boolean> = MutableLiveData(false)

    fun getNewsResponse(category: String) {
        showProgressBar.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = newsRepository.getSources(category = category)
                newsList.postValue(response)
            } catch (ex: Exception) {
                ex.message?.let {
                    errorDialog.postValue(
                        DialogMessage(
                            "Network Error",
                            it,
                        ),
                    )
                }
            } finally {
                showProgressBar.postValue(false)
            }
        }
    }

    fun loadNewResource(source: Source?) {
        showProgressBar.value = true
        source?.id?.let { sourceId ->
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response = articleRepository.getArticle(sourceId)
                    articleList.postValue(response)
                } catch (ex: Exception) {
                    ex.message?.let {
                        errorDialog.postValue(
                            DialogMessage(
                                "Network Error",
                                it,
                            ),
                        )
                    }
                } finally {
                    showProgressBar.postValue(false)
                }
            }
        }
    }
}

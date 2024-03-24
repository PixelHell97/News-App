package com.pixel.newsapp.ui.home.fragment.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.pixel.newsapp.data.api.ApiManager
import com.pixel.newsapp.data.api.model.Source
import com.pixel.newsapp.data.api.model.articleResponse.Article
import com.pixel.newsapp.data.api.model.articleResponse.ArticlesResponse
import com.pixel.newsapp.data.api.model.sourcesResponse.SourcesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class NewsViewModel : ViewModel() {
    var newsList: MutableLiveData<List<Source?>?> = MutableLiveData()
    var articleList: MutableLiveData<List<Article?>?> = MutableLiveData()
    var errorDialog: MutableLiveData<DialogMessage?> = MutableLiveData()
    var showProgressBar: MutableLiveData<Boolean> = MutableLiveData(false)

    fun getNewsResponse(category: String) {
        showProgressBar.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ApiManager.getServices().getNewsSources(category = category)
                newsList.postValue(response.sources)
            } catch (httpException: HttpException) {
                val responseJson = httpException.response()?.errorBody().toString()
                val errorResponse =
                    Gson().fromJson(responseJson, SourcesResponse::class.java)
                errorDialog.postValue(
                    DialogMessage(
                        "Access Error",
                        errorResponse.message,
                    ),
                )
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
                    val response = ApiManager.getServices().getNewsArticle(sources = sourceId)
                    articleList.postValue(response.articles)
                } catch (httpException: HttpException) {
                    val responseJson = httpException.response()?.errorBody().toString()
                    val errorResponse =
                        Gson().fromJson(responseJson, ArticlesResponse::class.java)
                    errorDialog.postValue(
                        DialogMessage(
                            "Access Error",
                            errorResponse.message,
                        ),
                    )
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

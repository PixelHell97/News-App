package com.pixel.newsapp.ui.home.news

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.pixel.newsapp.api.ApiManager
import com.pixel.newsapp.api.model.Source
import com.pixel.newsapp.api.model.articleResponse.Article
import com.pixel.newsapp.api.model.articleResponse.ArticlesResponse
import com.pixel.newsapp.api.model.sourcesResponse.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {
    var newsList: MutableLiveData<List<Source?>?> = MutableLiveData()
    var articleList: MutableLiveData<List<Article?>?> = MutableLiveData()
    var errorDialog: MutableLiveData<DialogMessage?> = MutableLiveData()
    var showProgressBar: MutableLiveData<Boolean> = MutableLiveData(false)

    fun getNewsResponse(category: String) {
        showProgressBar.postValue(true)
        ApiManager
            .getServices()
            .getNewsSources(
                category = category,
            ).enqueue(
                object : Callback<SourcesResponse> {
                    override fun onResponse(
                        call: Call<SourcesResponse>,
                        response: Response<SourcesResponse>,
                    ) {
                        if (response.isSuccessful) {
                            newsList.postValue(response.body()?.sources)
                            Log.e("source->", "GotSources")
                            return
                        } else {
                            val responseJson = response.errorBody()?.string()
                            val errorResponse =
                                Gson().fromJson(responseJson, SourcesResponse::class.java)
                            errorDialog.postValue(
                                DialogMessage(
                                    "Access Error",
                                    errorResponse.message,
                                ),
                            )
                        }
                    }

                    override fun onFailure(
                        call: Call<SourcesResponse>,
                        t: Throwable,
                    ) {
                        t.message?.let {
                            errorDialog.postValue(
                                DialogMessage(
                                    "Network Error",
                                    it,
                                ),
                            )
                        }
                    }
                },
            )
    }

    fun loadNewResource(source: Source?) {
        showProgressBar.postValue(true)
        source?.id?.let { sourceId ->
            ApiManager
                .getServices()
                .getNewsArticle(sources = sourceId)
                .enqueue(
                    object : Callback<ArticlesResponse> {
                        override fun onResponse(
                            call: Call<ArticlesResponse>,
                            response: Response<ArticlesResponse>,
                        ) {
                            if (response.isSuccessful) {
                                articleList.postValue(response.body()?.articles)
                                return
                            } else {
                                val responseJson = response.errorBody()?.string()
                                val errorResponse =
                                    Gson().fromJson(responseJson, ArticlesResponse::class.java)
                                errorDialog.postValue(
                                    DialogMessage(
                                        "Access Error",
                                        errorResponse.message,
                                    ),
                                )
                            }
                        }

                        override fun onFailure(
                            call: Call<ArticlesResponse>,
                            t: Throwable,
                        ) {
                            t.message?.let {
                                errorDialog.postValue(
                                    DialogMessage(
                                        "Network Error",
                                        it,
                                    ),
                                )
                            }
                        }
                    },
                )
        }
    }
}

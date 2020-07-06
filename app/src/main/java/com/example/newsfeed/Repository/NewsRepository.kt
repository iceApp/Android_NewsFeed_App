package com.example.newsfeed.Repository

import android.content.Context
import android.icu.text.StringSearch
import androidx.lifecycle.MutableLiveData
import com.example.newsfeed.data.News
import com.example.newsfeed.networking.NewsApiService
import com.example.newsfeed.utills.LoadStatus
import com.example.newsfeed.utills.makeToast

class NewsRepository(private val context: Context) {

    private val newsApiService: NewsApiService by lazy {
        NewsApiService.creatApiService()
    }

    val loadStatus = MutableLiveData<LoadStatus>()

    suspend fun getNews(searchWord: String ): List<News>{
        var returnList = emptyList<News>()
        try {
            val response = newsApiService.getNews(searchWord)
            loadStatus.value = LoadStatus.LOADING
            if (response.isSuccessful){
                loadStatus.value = LoadStatus.DONE
                returnList = response.body()!!.newsList
            } else {
                loadStatus.value = LoadStatus.ERROR
                makeToast(context, response.message())
            }

        } catch (e: Throwable){
            loadStatus.value = LoadStatus.ERROR
            makeToast(context, "Exception: ${e.message}")
        }
        return returnList


    }

}
package com.example.newsfeed.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsfeed.data.News
import com.example.newsfeed.db.NewsDao
import com.example.newsfeed.db.NewsDatabase
import com.example.newsfeed.networking.NewsApiService
import com.example.newsfeed.utills.LoadStatus
import com.example.newsfeed.utills.makeToast

class NewsRepository(private val context: Context) {

    private val newsApiService: NewsApiService by lazy {
        NewsApiService.creatApiService()
    }

    private val dao :NewsDao by lazy {
        NewsDatabase.getDatabase(context).newsDao()
    }

    val isLoading = MutableLiveData<Boolean>()

    val allNews: LiveData<List<News>> = dao.news()

    suspend fun getNews(searchWord: String ){
        //var returnList = emptyList<News>()
        isLoading.postValue(true)

        runCatching {
            newsApiService.getNews(searchWord)
        }.onSuccess { response ->
            if (response.isSuccessful){
                dao.clearAndInsert(response.body()!!.newsList)
                //returnList = response.body()!!.newsList
            } else {
                makeToast(context, response.message().toString())
            }
            isLoading.postValue(false)

        }.onFailure { e ->
            makeToast(context, "Exception: ${e.message}")
            isLoading.postValue(false)
        }

        //return returnList

//        // try{}
//        try {
//            val response = newsApiService.getNews(searchWord)
//            loadStatus.value = LoadStatus.LOADING
//            if (response.isSuccessful){
//                loadStatus.value = LoadStatus.DONE
//                returnList = response.body()!!.newsList
//            } else {
//                loadStatus.value = LoadStatus.ERROR
//                makeToast(context, response.message())
//            }
//
//        } catch (e: Throwable){
//            loadStatus.value = LoadStatus.ERROR
//            makeToast(context, "Exception: ${e.message}")
//        }
    }

}
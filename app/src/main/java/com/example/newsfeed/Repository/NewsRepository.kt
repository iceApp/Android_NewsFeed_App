package com.example.newsfeed.Repository

import android.content.Context
import android.icu.text.StringSearch
import com.example.newsfeed.data.News
import com.example.newsfeed.networking.NewsApiService

class NewsRepository(private val context: Context) {

    private val newsApiService: NewsApiService by lazy {
        NewsApiService.creatApiService()
    }

//    suspend fun getNews(searchWord: String ): List<News>{
//        newsApiService.getNews(searchWord)
//
//        // TODO
//        //return List<News>()
//    }

}
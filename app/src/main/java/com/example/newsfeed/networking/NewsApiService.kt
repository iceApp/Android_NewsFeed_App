package com.example.newsfeed.networking

import android.icu.text.StringSearch
import android.support.v4.media.MediaBrowserCompat
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    companion object {
        const val BASE_URL = "https://newsapi.org/v2"
        const val API_KEY = "1876c9cc626741beafa49b52240b0af2"

        fun creatApiService(): NewsApiService{
            val moshi: Moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            return Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(NewsApiService::class.java)
        }
    }

    @GET("everything")
    suspend fun getNews(
        @Query("q") searchString: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<ApiResponse>




















//    companion object {
//        const val BASE_URL = "https://newsapi.org/v2"
//        const val API_KEY = "1876c9cc626741beafa49b52240b0af2"
//    }

//    @GET("everything")
//    //レスポンスまで待つことができる非同期対応
//    suspend fun getNews(
//            @Query("q") searchString: String,
//            @Query("apiKey") apiKey :String = API_KEY
//    ): Response<ApiResponse>
}
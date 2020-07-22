package com.example.newsfeed.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsfeed.data.News

@Dao
interface NewsDao {

    @Insert
    suspend fun insert(news: List<News>)

    @Query("DELETE FROM news_table")
    suspend fun clear()

    @Query("SELECT * FROM news_table")
    // roomでlivedataを扱う場合、roomで自動で非同期処理させるためsuspendは不要
    fun news(): LiveData<List<News>>

    @Transaction
    suspend fun clearAndInsert(news: List<News>){
        clear()
        insert(news)
    }




}
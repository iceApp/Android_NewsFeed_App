package com.example.newsfeed.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsfeed.data.News

@Database(entities = [News::class], version = 1)
abstract class NewsDatabase: RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        @Volatile // 引数有りのシングルトンを安全に実行するため
        private var INSTANCE: NewsDatabase? = null

        fun getDatabase(context: Context): NewsDatabase = INSTANCE
            ?: synchronized(this){
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                NewsDatabase::class.java,
                "news_database.db"
            ).build().also {
                INSTANCE = it }
        }
    }

}
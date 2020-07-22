package com.example.newsfeed.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "news_table")
data class News (
    val title: String?,
    @ColumnInfo(name = "publish_data") @Json(name = "publishedAt") val publishData: String?,
    val url: String?,
    val description: String?,
    @ColumnInfo(name = "image_url") @Json(name = "urlToImage") val imageUrl: String?
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
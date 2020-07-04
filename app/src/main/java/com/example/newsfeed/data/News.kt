package com.example.newsfeed.data

import com.squareup.moshi.Json

data class News (
    val title: String?,
    @Json(name = "publishedAt") val publishData: String?,
    val url: String?,
    val description: String?,
    @Json(name = "urlToImage") val imageUrl: String?
)
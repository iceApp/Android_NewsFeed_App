package com.example.newsfeed.networking

import com.example.newsfeed.data.News
import com.squareup.moshi.Json

data class ApiResponse (
    @Json(name = "articles") val newsList: List<News>

)
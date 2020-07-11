package com.example.newsfeed.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfeed.R
import com.example.newsfeed.data.News
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.news_item.*

class NewsListAdapter: RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    var newsList: List<News> = emptyList()

    class ViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView!!), LayoutContainer {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return  ViewHolder(view)
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val selectedNews = newsList[position]

        // TODO 画像のセット

        holder.apply {
            txtNewsTitle.text = selectedNews.title
            txtPublishDate.text = selectedNews.publishData
            txtDesc.text = selectedNews.description
        }
    }
}
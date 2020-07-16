package com.example.newsfeed.view

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsfeed.R
import com.example.newsfeed.data.News
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.news_item.*

class NewsListAdapter(private val activity: Activity): RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

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

        selectedNews.imageUrl.let {
            Glide.with(activity).load(selectedNews.imageUrl).into(holder.imgNewsHeader)
        }

        holder.apply {
            txtNewsTitle.text = selectedNews.title
            txtPublishDate.text = selectedNews.publishData
            txtDesc.text = selectedNews.description
        }
    }
}
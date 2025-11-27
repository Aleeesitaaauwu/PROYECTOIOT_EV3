package com.example.prueblot2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.prueblot2.R
import com.example.prueblot2.entity.News

class NewsAdapter(
    private val onItemClick: (News) -> Unit
) : ListAdapter<News, NewsAdapter.NewsViewHolder>(NewsDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = getItem(position)
        holder.bind(news)
        holder.itemView.setOnClickListener { onItemClick(news) }
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.newsItemTitle)
        private val dateTextView: TextView = itemView.findViewById(R.id.newsItemDate)
        private val categoryTextView: TextView = itemView.findViewById(R.id.newsItemCategory)
        private val previewTextView: TextView = itemView.findViewById(R.id.newsItemPreview)

        fun bind(news: News) {
            titleTextView.text = news.title
            dateTextView.text = news.date
            categoryTextView.text = news.category
            previewTextView.text = news.content.take(100) + "..."
        }
    }

    companion object NewsDiffCallback : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }
    }
}
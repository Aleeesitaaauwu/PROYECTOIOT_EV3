package com.example.prueblot2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.prueblot2.database.AppDatabase
import com.example.prueblot2.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsDetailActivity : AppCompatActivity() {

    private lateinit var newsRepository: NewsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        val database = AppDatabase.getDatabase(this)
        newsRepository = NewsRepository(database)

        val newsId = intent.getLongExtra("NEWS_ID", -1)

        if (newsId != -1L) {
            loadNewsDetail(newsId)
        }
    }

    private fun loadNewsDetail(newsId: Long) {
        lifecycleScope.launch {
            val news = newsRepository.getNewsById(newsId)
            news?.let { displayNews(it) }
        }
    }

    private fun displayNews(news: com.example.prueblot2.entity.News) {
        val titleTextView: TextView = findViewById(R.id.newsTitle)
        val dateTextView: TextView = findViewById(R.id.newsDate)
        val authorTextView: TextView = findViewById(R.id.newsAuthor)
        val categoryTextView: TextView = findViewById(R.id.newsCategory)
        val contentTextView: TextView = findViewById(R.id.newsContent)

        titleTextView.text = news.title
        dateTextView.text = "Fecha: ${news.date}"
        authorTextView.text = "Autor: ${news.author}"
        categoryTextView.text = "Categoría: ${news.category}"
        contentTextView.text = news.content
    }
}
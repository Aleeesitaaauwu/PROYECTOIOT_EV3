package com.example.prueblot2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prueblot2.adapter.NewsAdapter
import com.example.prueblot2.database.AppDatabase
import com.example.prueblot2.repository.NewsRepository

class NewsListActivity : AppCompatActivity() {

    private lateinit var newsRepository: NewsRepository
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        val database = AppDatabase.getDatabase(this)
        newsRepository = NewsRepository(database)

        setupRecyclerView()
        loadNews()
    }

    private fun setupRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.newsRecyclerView)
        newsAdapter = NewsAdapter { news ->
            // Cuando se hace click en una noticia
            val intent = Intent(this, NewsDetailActivity::class.java)
            intent.putExtra("NEWS_ID", news.id)
            startActivity(intent)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@NewsListActivity)
            adapter = newsAdapter
        }
    }

    private fun loadNews() {
        // Observar los cambios en la lista de noticias
        newsRepository.getAllNews().observe(this) { newsList ->
            newsAdapter.submitList(newsList)
        }
    }
}
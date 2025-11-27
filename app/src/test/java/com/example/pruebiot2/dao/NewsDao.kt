package com.example.login.dao

import androidx.room.*
import com.example.login.entity.News
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Query("SELECT * FROM news ORDER BY createdAt DESC")
    fun getAllNews(): Flow<List<News>>

    @Query("SELECT * FROM news WHERE id = :newsId")
    suspend fun getNewsById(newsId: Long): News?

    @Insert
    suspend fun insertNews(news: News)

    @Delete
    suspend fun deleteNews(news: News)

    @Query("DELETE FROM news")
    suspend fun deleteAllNews()

    @Query("SELECT * FROM news WHERE id = :newsId")
    suspend fun getNewsById(newsId: Long): News?
}
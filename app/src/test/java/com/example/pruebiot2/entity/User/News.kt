package com.example.login.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class News(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val content: String,
    val author: String,
    val date: String,
    val category: String = "General",
    val createdAt: Long = System.currentTimeMillis()
)
package com.example.login.repository

import com.example.login.database.AppDatabase
import com.example.login.entity.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val database: AppDatabase) {
    private val userDao = database.userDao()

    suspend fun login(username: String, password: String): User? {
        return userDao.login(username, password)
    }

    suspend fun register(user: User) {
        userDao.insertUser(user)
    }

    suspend fun userExists(username: String): Boolean {
        return userDao.getUserByUsername(username) != null
    }

    suspend fun initializeDefaultUsers() {
        if (userDao.getUserCount() == 0) {
            // Usuario por defecto
            val defaultUser = User(
                username = "admin",
                email = "admin@example.com",
                password = "123456"
            )
            userDao.insertUser(defaultUser)
        }
    }
}
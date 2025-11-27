package com.example.prueblot2.repository

import com.example.prueblot2.database.AppDatabase
import com.example.prueblot2.entity.News
import kotlinx.coroutines.flow.Flow

class NewsRepository(private val database: AppDatabase) {
    private val newsDao = database.newsDao()

    fun getAllNews(): Flow<List<News>> {
        return newsDao.getAllNews()
    }

    suspend fun getNewsById(newsId: Long): News? {
        return newsDao.getNewsById(newsId)
    }

    suspend fun addNews(news: News) {
        newsDao.insertNews(news)
    }

    suspend fun deleteNews(news: News) {
        newsDao.deleteNews(news)
    }

    suspend fun initializeSampleNews() {
        // Verificar si ya hay noticias para no duplicar
        val currentNews = newsDao.getAllNews()

        // Solo agregar noticias de ejemplo si no hay ninguna
        val sampleNews = listOf(
            News(
                title = "Bienvenido a nuestra App",
                content = "Estamos emocionados de darte la bienvenida a nuestra aplicación. Aquí encontrarás las últimas noticias y actualizaciones importantes. Esta app está diseñada para mantenerte informado sobre los temas que más te interesan.",
                author = "Equipo de Desarrollo",
                date = "2024-01-15",
                category = "Sistema"
            ),
            News(
                title = "Kotlin se consolida como lenguaje para Android",
                content = "Google ha confirmado que Kotlin seguirá siendo el lenguaje preferido para el desarrollo de aplicaciones Android. Las nuevas características como corrutinas y flow han mejorado significativamente el rendimiento de las apps móviles. Los desarrolladores reportan mayor productividad y menos errores en comparación con Java.",
                author = "Tech News",
                date = "2024-01-14",
                category = "Tecnología"
            ),
            News(
                title = "Nuevas Actualizaciones de Seguridad",
                content = "Se han implementado importantes actualizaciones de seguridad en el sistema. Estas mejoras incluyen: encriptación mejorada de datos, autenticación de dos factores y monitoreo continuo de actividades sospechosas. Recomendamos a todos los usuarios mantener sus contraseñas actualizadas.",
                author = "Departamento de Seguridad",
                date = "2024-01-13",
                category = "Seguridad"
            ),
            News(
                title = "Evento de Desarrollo Mobile 2024",
                content = "No te pierdas el evento anual de desarrollo móvil que se llevará a cabo el próximo mes. Contaremos con expertos de Google, JetBrains y desarrolladores independientes compartiendo sus experiencias. Temas principales: Compose, Room Database, y mejores prácticas de arquitectura MVVM.",
                author = "Comunidad Dev",
                date = "2024-01-12",
                category = "Eventos"
            ),
            News(
                title = "Consejos para Mejorar el Rendimiento",
                content = "Optimiza tu aplicación Android con estos consejos: usa corrutinas para operaciones asíncronas, implementa caché para imágenes, reduce el uso de memoria con paginación y minimiza las operaciones en el hilo principal. Pequeños cambios pueden significar grandes mejoras en la experiencia del usuario.",
                author = "Experto en Performance",
                date = "2024-01-11",
                category = "Desarrollo"
            )
        )

        // Insertar solo si no hay noticias existentes
        sampleNews.forEach { newsDao.insertNews(it) }
    }
}
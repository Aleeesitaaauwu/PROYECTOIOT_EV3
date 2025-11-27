package com.example.prueblot2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.prueblot2.database.AppDatabase
import com.example.prueblot2.repository.NewsRepository

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu) // Crearemos este XML después

        val username = intent.getStringExtra("USERNAME") ?: "Usuario"

        val welcomeText: TextView = findViewById(R.id.welcomeText)
        welcomeText.text = "Bienvenido, $username"

        val newsButton: Button = findViewById(R.id.newsButton)
        val profileButton: Button = findViewById(R.id.profileButton)
        val logoutButton: Button = findViewById(R.id.logoutButton)

        newsButton.setOnClickListener {
            val intent = Intent(this, NewsListActivity::class.java)
            startActivity(intent)
        }

        profileButton.setOnClickListener {
            // Aquí puedes agregar la actividad de perfil después
            Toast.makeText(this, "Funcionalidad de perfil próximamente", Toast.LENGTH_SHORT).show()
        }

        logoutButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Inicializar noticias de ejemplo
        initializeSampleNews()
    }

    private fun initializeSampleNews() {
        val database = AppDatabase.getDatabase(this)
        val newsRepository = NewsRepository(database)

        // Esto se ejecutará en segundo plano
        Thread {
            newsRepository.initializeSampleNews()
        }.start()
    }
}
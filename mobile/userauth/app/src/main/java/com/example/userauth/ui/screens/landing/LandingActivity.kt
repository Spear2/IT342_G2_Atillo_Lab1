package com.example.userauth.ui.screens.landing

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.userauth.R
import com.example.userauth.ui.screens.login.LoginActivity
import com.example.userauth.ui.screens.register.RegisterActivity

class LandingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 1. Link this Kotlin file to your XML layout
        setContentView(R.layout.activity_landing)

        // 2. Find the buttons from your XML using the IDs we gave them
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        // 3. Listen for the "Login" button click
        btnLogin.setOnClickListener {
            // Create the "ticket" to go to LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // 4. Listen for the "Create Account" button click
        btnRegister.setOnClickListener {
            // Create the "ticket" to go to RegisterActivity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
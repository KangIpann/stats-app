package com.example.statsapp.starter

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.statsapp.R

class LoginActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        db = DatabaseHelper(this)
        etEmail = findViewById(R.id.emailLayout)
        etPassword = findViewById(R.id.passET)
        btnLogin = findViewById(R.id.User_Login)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            val user = db.getUser(email)

            if (user != null && user.password == password) {
                // Valid credentials, perform login
                performLogin(email)
            } else {
                // Invalid credentials, show error message
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun performLogin(email: String) {
        // Perform login logic here
        // You can save the logged-in user session or navigate to the next screen
        Toast.makeText(this, "Logged in as $email", Toast.LENGTH_SHORT).show()
        // Example: Navigate to MainActivity
        // val intent = Intent(this, MainActivity::class.java)
        // startActivity(intent)
    }
}

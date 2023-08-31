package com.example.statsapp.starter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.statsapp.R
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity(), OnClickListener {
    private lateinit var tvSignUp: TextView
    private lateinit var et_email: EditText
    private lateinit var et_password: EditText
    private lateinit var btnLogin: Button
    private lateinit var pb_login: ProgressBar
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window.statusBarColor = ContextCompat.getColor(this, com.google.android.material.R.color.mtrl_btn_transparent_bg_color)
        tvSignUp = findViewById(R.id.tvSignUp)
        btnLogin = findViewById(R.id.btn_login)
        pb_login = findViewById(R.id.pb_login)
        et_email = findViewById(R.id.emailEt)
        et_password = findViewById(R.id.passEt)
        tvSignUp.setOnClickListener(this)
        btnLogin.setOnClickListener(this)

        auth = FirebaseAuth.getInstance()
        pb_login.visibility = View.GONE

        // Check if the user is already logged in
        val currentUser = auth.currentUser
        if (currentUser != null) {
            navigateToHome()
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tvSignUp -> {
                val intent = Intent(this, SignUp::class.java)
                startActivity(intent)
            }
            R.id.btn_login -> {
                pb_login.visibility = View.VISIBLE
                val email = et_email.text.toString()
                val password = et_password.text.toString()
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                pb_login.visibility = View.GONE
                                navigateToHome()
                            } else {
                                pb_login.visibility = View.GONE
                                // Login failed
                                Toast.makeText(this, "Email atau Password salah", Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    pb_login.visibility = View.GONE
                    Toast.makeText(this, "Email dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun navigateToHome() {
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
        finish()
    }
}

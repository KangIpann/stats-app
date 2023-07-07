package com.example.statsapp.starter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import androidx.core.content.ContextCompat
import com.example.statsapp.R
import com.google.firebase.auth.FirebaseAuth

class WelcomePage : AppCompatActivity(), OnClickListener {
    private lateinit var signup: Button
    private lateinit var login: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = ContextCompat.getColor(this, com.google.android.material.R.color.mtrl_btn_transparent_bg_color)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)
        signup = findViewById(R.id.Welcome_Signup)
        login = findViewById(R.id.Welcome_Login)
        signup.setOnClickListener(this)
        login.setOnClickListener(this)

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            navigateToHome()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.Welcome_Signup -> {
                val intent = Intent(this, SignUp::class.java)
                startActivity(intent)
            }

            R.id.Welcome_Login -> {
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }
        }
    }

    private fun navigateToHome() {
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
        finish()
    }
}

package com.example.statsapp.starter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import androidx.core.content.ContextCompat
import com.example.statsapp.R

class WelcomePage : AppCompatActivity(), OnClickListener {
    private lateinit var signup : Button
    private lateinit var login : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = ContextCompat.getColor(this, com.google.android.material.R.color.mtrl_btn_transparent_bg_color)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)
        signup = findViewById(R.id.Welcome_Signup)
        login = findViewById(R.id.Welcome_Login)
        signup.setOnClickListener(this)
        login.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
    when(v?.id){
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
}
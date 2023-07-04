package com.example.statsapp.starter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.statsapp.R

class Login : AppCompatActivity(), OnClickListener {
    private lateinit var tvSignUp: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window.statusBarColor = ContextCompat.getColor(this, com.google.android.material.R.color.mtrl_btn_transparent_bg_color)
        tvSignUp = findViewById(R.id.tvSignUp)
        tvSignUp.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tvSignUp -> {
                val intent = Intent(this, SignUp::class.java)
                startActivity(intent)
            }
        }
    }
}
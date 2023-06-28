package com.example.statsapp.starter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.statsapp.R
import java.lang.Exception

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val actionBar = supportActionBar
        actionBar?.title = ""
        actionBar?.setDisplayHomeAsUpEnabled(false)
        window.statusBarColor = ContextCompat.getColor(this, com.google.android.material.R.color.mtrl_btn_transparent_bg_color)
        val background = object :Thread(){
            override fun run() {
                try {
                    Thread.sleep(2500)
                    val intent = Intent(baseContext, WelcomePage::class.java)
                    startActivity(intent)
                    overridePendingTransition(androidx.appcompat.R.anim.abc_fade_in, androidx.appcompat.R.anim.abc_fade_out)
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
}
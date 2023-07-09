package com.example.statsapp.starter

import Handler.SignupHandler
import android.content.Intent
import android.app.AlertDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.statsapp.R
import com.google.android.material.textfield.TextInputEditText

class SignUp : AppCompatActivity(), View.OnClickListener {
    private lateinit var tvLogIn: TextView
    private lateinit var signupHandler: SignupHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        window.statusBarColor = ContextCompat.getColor(this, com.google.android.material.R.color.mtrl_btn_transparent_bg_color)

        signupHandler = SignupHandler()

        tvLogIn = findViewById(R.id.tvLogIn)
        tvLogIn.setOnClickListener(this)

        val signUpButton: Button = findViewById(R.id.User_Login)
        signUpButton.setOnClickListener(this)
    }

    private fun showAlertDialog(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton("Perbaiki") { dialog, _ -> dialog.dismiss() }

        val dialog = builder.create()
        dialog.show()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvLogIn -> {
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }
            R.id.User_Login -> {
                val name = findViewById<TextInputEditText>(R.id.namaEt).text.toString()
                val email = findViewById<TextInputEditText>(R.id.emailEt).text.toString()
                val password = findViewById<TextInputEditText>(R.id.passEt).text.toString()
                val confirmPassword = findViewById<TextInputEditText>(R.id.passConfirmEt).text.toString()

                if (password != confirmPassword) {
                    showAlertDialog("Kesalahan", "Password Harus Sama")
                    return
                }

                signupHandler.registerUserWithEmail(this,email, password, name)
            }
        }
    }
}
